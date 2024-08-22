package com.renxin.psychology.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.renxin.common.constant.UserConstants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.entity.SysUser;
import com.renxin.common.event.publish.ConsultServePublisher;
import com.renxin.common.utils.*;
import com.renxin.common.vo.DateLimitUtilVO;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.dto.PsyConsultInfoDTO;
import com.renxin.psychology.mapper.PsyConsultMapper;
import com.renxin.psychology.request.*;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultServeConfigVO;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import com.renxin.system.service.ISysConfigService;
import com.renxin.system.service.ISysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PsyConsultServiceImpl implements IPsyConsultService {

    @Resource
    private PsyConsultMapper psyConsultMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private ConsultServePublisher consultServePublisher;

    @Resource
    private IPsyConsultServeService psyConsultServeService;

    @Resource
    private IPsyConsultServeConfigService psyConsultServeConfigService;

    @Resource
    private IPsyConsultWorkService psyConsultWorkService;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysConfigService configService;
    
    @Resource
    private IPsyConsultServeConfigService serveConfigService;
    
    @Resource
    private IPsyConsultServeService serveService;
    
    @Resource
    private IPsyConsultantScheduleService scheduleService;
    
    @Resource
    private IPsyConsultantOrderService consultantOrderService;

    @Override
    public List<PsyConsultWorkVO> getConsultWorksById(Long id) {
        PsyWorkReq req = new PsyWorkReq();
        req.setConsultId(id);
        req.setStatus("0");

        // t+6
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        req.setStart(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 7);
        req.setEnd(sdf.format(calendar.getTime()));

        return psyConsultWorkService.getConsultWorks(req);
    }
    
    //指定咨询师近期可约时间
    @Override
    public List<PsyConsultWorkVO> getConsultWorks(PsyWorkReq req){
        req.setStatus("0");
       /* String dayStr = req.getDay();
        //查询近期N天内
        Integer day = ObjectUtils.isNotEmpty(dayStr) ? Integer.valueOf(dayStr) : 15;
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        req.setStart(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, day-1);
        req.setEnd(sdf.format(calendar.getTime()));*/
        List<PsyConsultWorkVO> consultWorks = psyConsultWorkService.getConsultWorks(req);
        return consultWorks;
    }


    @Override
    public PsyConsultInfoDTO getConsultInfoByServe(Long cId, Long sId) {
        PsyConsultInfoDTO vo = new PsyConsultInfoDTO();
        PsyConsultServeConfigVO serve = psyConsultServeConfigService.getOne(sId);
        PsyConsultVO consult = getOne(cId);

        if (ConsultConstant.CONSULT_MODE_SOUND.equals(serve.getMode())) {
            serve.setModeName("语音咨询");
        } else if (ConsultConstant.CONSULT_MODE_VOICE.equals(serve.getMode())) {
            serve.setModeName("视频咨询");
        } else if (ConsultConstant.CONSULT_MODE_FACE.equals(serve.getMode())) {
            serve.setModeName("当面咨询");
        }
        //查询 咨询师-服务 关联id
        PsyConsultServe consultServe = new PsyConsultServe();
            consultServe.setConsultId(cId);
            consultServe.setServeId(sId);
        List<PsyConsultServe> list = serveService.getList(consultServe);
        if (ObjectUtils.isNotEmpty(list)){
            vo.setRelationId(list.get(0).getRelationId());
        }
        
        vo.setWorks(getConsultWorksById(cId));
        vo.setServe(serve);
        vo.setConsult(consult);
        return vo;
    }

    @Override
    public List<PsyConsult> search(PsyConsultReq req) {
        if ("1".equals(req.getBuy())) {
            req.setBuy(null);
        }
        if ("1".equals(req.getSingle())) {
            req.setSingle(null);
        }
        if ("不限".equals(req.getSex())) {
            req.setSex(null);
        }
        if ("不限".equals(req.getServe())) {
            req.setServe(null);
        }
        List<PsyConsult> list = psyConsultMapper.search(req);
        // 处理way
        if (!CollectionUtils.isEmpty(req.getWay()) && !CollectionUtils.isEmpty(list)) {
            list = list.stream().filter(i -> {
                if (StringUtils.isNotEmpty(i.getWayStr())) {
                    Set<String> now = Stream.of(StringUtils.split(i.getWayStr().trim(), ",")).collect(Collectors.toSet());
                    now.retainAll(req.getWay());
                    return !now.isEmpty();
                }
                return true;
            }).collect(Collectors.toList());
        }

        // 默认已经存在排序，price一定会存在值，只需要处理new_price的排序问题
        // 按照价格进行排序，并收集价格相同的元素索引
        Map<BigDecimal, List<PsyConsult>> priceMap = new HashMap<>();
        BigDecimal decimal = new BigDecimal("999999");
        for (PsyConsult psyConsult : list) {
            BigDecimal price = psyConsult.getNewPrice() != null ? psyConsult.getNewPrice() : decimal;
            if (!priceMap.containsKey(price)) {
                priceMap.put(price, new ArrayList<>());
            }
            priceMap.get(price).add(psyConsult);
        }

        // 乱序排列价格相同的元素，并重新构建排序后的产品列表
        List<BigDecimal> sortedPrices = new ArrayList<>(priceMap.keySet());
        sortedPrices.sort(BigDecimal::compareTo);
        for (BigDecimal price : sortedPrices) {
            List<PsyConsult> pricedProducts = priceMap.get(price);
            if (price.compareTo(decimal) < 0) {
                Collections.shuffle(pricedProducts);
            }

            list.removeAll(pricedProducts);
            list.addAll(pricedProducts);
        }

        return list;
    }

    @Override
    public PsyConsultVO getOne(Long id) {
        PsyConsultVO consultVO = BeanUtil.toBean(psyConsultMapper.queryById(id), PsyConsultVO.class);
        //查询工作时长
        PsyWorkTimeRes psyWorkTimeRes = scheduleService.querySumTime(id);
        BeanUtils.copyProperties(psyWorkTimeRes,consultVO);

        return consultVO;
    }

    @Override
    public PsyConsult getByPhone(String phone) {

        LambdaQueryWrapper<PsyConsult> wp = Wrappers.lambdaQuery();
        wp.eq(PsyConsult::getPhonenumber, phone);

        return psyConsultMapper.selectOne(wp);
    }

    /**
     * 根据phone获取咨询师, 若无则insert
     * @param phone
     * @return
     */
    @Override
    public PsyConsult getByPhoneOrInsert(String phone) {

        LambdaQueryWrapper<PsyConsult> wp = Wrappers.lambdaQuery();
        wp.eq(PsyConsult::getPhonenumber, phone);
        PsyConsult psyConsult = psyConsultMapper.selectOne(wp);
        if (ObjectUtils.isNotEmpty(psyConsult)){
            return psyConsult;
        }

        PsyConsult newConsultant = new PsyConsult();
            newConsultant.setId(IDhelper.getNextId());
            newConsultant.setPhonenumber(phone);
        psyConsultMapper.insert(newConsultant);
        return newConsultant;

    }

    @Override
    public List<PsyConsult> getList(PsyConsultVO req) {
        if (SecurityUtils.getUserIdByNotAdmin() != 0L) {
            req.setUserId(SecurityUtils.getUserIdByNotAdmin());
        }
        req.setDelFlag("0");
        return psyConsultMapper.getList(req);
    }

    @Override
    public List<PsyConsult> getList(PsyAdminConsultReq req) {
        LambdaQueryWrapper<PsyConsult> wp = Wrappers.lambdaQuery();
        wp.eq(PsyConsult::getDelFlag, "0");

        if (StringUtils.isNotEmpty(req.getUserName())) {
            wp.eq(PsyConsult::getUserName, req.getUserName());
        }

        if (StringUtils.isNotEmpty(req.getStatus())) {
            wp.eq(PsyConsult::getStatus, req.getStatus());
        }

        if (StrUtil.isNotBlank(req.getDateLimit())) {
            DateLimitUtilVO dateLimit = NewDateUtil.getDateLimit(req.getDateLimit());
            wp.between(PsyConsult::getCreateTime, dateLimit.getStartTime(), dateLimit.getEndTime());
        }

        return psyConsultMapper.selectList(wp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delConsultServeRef(PsyConsultServe req) {
        psyConsultServeService.delete(req);
        PsyConsultServe serve = new PsyConsultServe();
        serve.setConsultId(req.getConsultId());
        List<PsyConsultServe> serveList = psyConsultServeService.getList(serve);

        PsyConsultVO entity = getOne(req.getConsultId());
        entity.setServe(serveList.size());

        return psyConsultMapper.updateById(BeanUtil.toBean(entity, PsyConsult.class));
    }

    @Override
    public AjaxResult refConsultServe(PsyRefConsultServeReq req) {
        PsyConsult consult = psyConsultMapper.selectById(req.getConsultId());
        if (consult == null) {
            return AjaxResult.error("关联服务失败,咨询师信息异常");
        }

        Boolean execute = transactionTemplate.execute(e -> {
            if (psyConsultServeConfigService.refConsultServe(req) == 0 || psyConsultServeService.batchServeRef(req) == 0) {
                return Boolean.FALSE;
            }

            int count = psyConsultServeService.getRefCountByConsultId(req.getConsultId());
            consult.setServe(count);
            psyConsultMapper.updateById(consult);
            return Boolean.TRUE;
        });
        if (!execute) {
            return AjaxResult.error("关联服务失败,服务信息异常");
        }

        consultServePublisher.publish(req);
        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(PsyConsultVO req) {
        // 新增用户
        SysUser user = convertToUser(req);
        user.setPassword(SecurityUtils.encryptPassword(configService.selectConfigByKey("sys.user.initPassword")));

        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }

        // 初始化id
        if (req.getId() == null) {
            req.setId(IDhelper.getNextId());
        }

        // 新增用户
        userService.insertUser(user);
        req.setUserId(user.getUserId());

        converToStr(req);
        return AjaxResult.success(psyConsultMapper.insert(BeanUtil.toBean(req, PsyConsult.class)));
    }

    @Override
    public void updateNum(Long id, int num) {
        PsyConsultVO one = getOne(id);
        int i = one.getWorkNum() + num;
        one.setWorkNum(Math.max(i, 0));
        updateByApp(one);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByApp(PsyConsultVO req) {
        psyConsultMapper.updateById(BeanUtil.toBean(req, PsyConsult.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult update(PsyConsultVO req) {

        SysUser sysUser = userService.selectUserByUserName(req.getUserName());
        if (StringUtils.isNotEmpty(req.getPhonenumber()) && !req.getPhonenumber().equals(sysUser.getPhonenumber())) {
            sysUser.setPhonenumber(req.getPhonenumber());
            if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser))) {
                return AjaxResult.error("修改用户'" + req.getUserName() + "'失败，手机号码已存在");
            }
        }
        else if (StringUtils.isNotEmpty(req.getEmail()) && !req.getEmail().equals(sysUser.getEmail()))
        {
            sysUser.setEmail(req.getEmail());
            if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser))) {
                return AjaxResult.error("修改用户'" + req.getUserName() + "'失败，邮箱账号已存在");
            }
        }
        sysUser.setSex(req.getSex());
        sysUser.setAvatar(req.getAvatar());
        sysUser.setUpdateBy(SecurityUtils.getUsername());
        sysUser.setStatus(req.getStatus());
        userService.updateUserProfile(sysUser);

        converToStr(req);
        return AjaxResult.success(psyConsultMapper.updateById(BeanUtil.toBean(req, PsyConsult.class)));
    }

    private SysUser convertToUser(PsyConsultVO req) {
        SysUser user = new SysUser();
        user.setNickName(req.getUserName());
        user.setUserName(req.getUserName());
        user.setSex(req.getSex());
        user.setEmail(req.getEmail());
        user.setPhonenumber(req.getPhonenumber());
        user.setAvatar(req.getAvatar());
        user.setUpdateTime(DateUtils.getNowDate());
        user.setCreateBy(SecurityUtils.getUsername());
        user.setUpdateBy(SecurityUtils.getUsername());
        Long[] roleIds = { 3L };
        user.setRoleIds(roleIds);
        return user;
    }

    private void converToStr(PsyConsultVO req) {
        HashSet<String> tab = new HashSet<>();
        HashSet<String> way = new HashSet<>();
        if (StringUtils.isNotEmpty(req.getWay())) {
            List<String> jsonArray = JSON.parseArray(req.getWay(), String.class);
            jsonArray.forEach(a -> {
                List<String> json = JSON.parseArray(a, String.class);
                tab.add(json.get(0));
                way.add(json.get(1));
            });
        }
        req.setTabs(String.join(",", tab));
        req.setWayStr(String.join(",", way));
    }

    @Override
    public String getAvailableUserName(String name) {
        if (UserConstants.UNIQUE.equals(userService.checkUserNameUnique(name)))
        {
            return name;
        }

        // 名称重复，则添加后缀
        int count = 1;
        while (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(name))) {
            name = name + "_0" + count; // 后缀
            if (UserConstants.UNIQUE.equals(userService.checkUserNameUnique(name)))
            {
                break;
            }
            count++;
        }

        return name;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAll(Long[] ids) {
        return psyConsultMapper.tombstonedByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return psyConsultMapper.deleteById(id);
    }

    /**
     * 所有咨询师全量关联服务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAllRelation(){
        //清空旧的关联关系
        serveService.deleteAll();
        
        //添加新的关联关系(全量)
        LambdaQueryWrapper<PsyConsult> wp = Wrappers.lambdaQuery();
            wp.eq(PsyConsult::getDelFlag, "0");
        List<PsyConsult> consultantList = psyConsultMapper.selectList(wp);//咨询师清单
        List<PsyConsultServeConfig> serverList = serveConfigService.getList(new PsyConsultServeConfigReq());//服务清单

        for (PsyConsult constant : consultantList) {
            ArrayList<Long> serverConfigIdList = new ArrayList<>();
            for (PsyConsultServeConfig serveConfig : serverList) {
                //判断[级别]与[服务对象]都相符
                if (constant.getLevel().equals(serveConfig.getLevel()) && constant.getServiceObject().contains(serveConfig.getServiceObject())){
                    serverConfigIdList.add(serveConfig.getId());
                }
            }
            if (ObjectUtils.isNotEmpty(serverConfigIdList)){
                //添加关联关系
                PsyRefConsultServeReq addReq = new PsyRefConsultServeReq();
                    addReq.setConsultId(constant.getId());
                    addReq.setIds(serverConfigIdList);
                serveService.batchServeRef(addReq);
            }
        }
        //更新[咨询师表]中, 各咨询师的"关联服务数量"
        psyConsultMapper.refreshServerNum();
    }
    
    //查询指定咨询师的顾客清单
    @Override
    public List<PsyConsult> queryConsultantList(PsyConsultOrderVO req){
        List<PsyConsult> psyConsultList = psyConsultMapper.queryConsultantList(req);
        for (PsyConsult psyConsult : psyConsultList) {
            psyConsult.setServerName(psyConsult.getServerName().split("-")[1]);
            //统计该咨询师的已付款下单次数
            PsyConsultantOrder orderReq = new PsyConsultantOrder();
                orderReq.setPayConsultantId(req.getConsultId()+"");
                orderReq.setPayStatus("2");//已支付
            List<PsyConsultantOrder> orderList = consultantOrderService.selectPsyConsultantOrderList(orderReq);
            if (ObjectUtils.isNotEmpty(orderList)){
                psyConsult.setUserOrderCount(orderList.size());
            }
        }
        return psyConsultList;
    }

    /**
     * 查询来访咨询师详情
     */
    @Override
    public PsyConsultVO queryConsultantDetail(VisitorDetailReq req){
        PsyConsult psyConsult = psyConsultMapper.selectById(req.getPayConsultantId());
        PsyConsultVO psyConsultVO = new PsyConsultVO();
        BeanUtils.copyProperties(psyConsult, psyConsultVO);
        List<PsyConsultantSchedule> scheduleList = new ArrayList<>();
        //若指定了收款人, 则查询其与该付款咨询师的相关咨询记录
        if (ObjectUtils.isNotEmpty(req.getChargeConsultantId())){
            //查询该顾客与我之间的 个督/体验 记录
            PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
            scheduleReq.setCreateBy(req.getPayConsultantId()+"");
            scheduleReq.setConsultId(req.getChargeConsultantId());
            scheduleReq.setOrderBy("sc.real_time");
            scheduleReq.setOrderDir("desc");
            scheduleList = scheduleService.selectPsyConsultantScheduleList(scheduleReq);

            //查询该顾客在本平台的个督预约记录
            scheduleReq.setConsultId(null);
            scheduleReq.setScheduleType(22);//个督
            scheduleReq.setOrderDir("asc");
            List<PsyConsultantSchedule> personSupList = scheduleService.selectPsyConsultantScheduleList(scheduleReq);

            //查询该顾客在本平台的个人体验预约记录
            scheduleReq.setScheduleType(23);//个人体验
            List<PsyConsultantSchedule> personExpList = scheduleService.selectPsyConsultantScheduleList(scheduleReq);

            //标注每一次服务, 分别是该用户在本平台的第几次 个督/体验
            for (PsyConsultantSchedule sc : scheduleList) {
                if (sc.getScheduleType() == 22){//个督
                    for (int i = 1; i <= personSupList.size(); i++) {
                        if(Objects.equals(sc.getId(),personSupList.get(i-1).getId())) {
                            sc.setRowNum(i);
                        }
                    }
                }
            }

            for (PsyConsultantSchedule sc : scheduleList) {
                if (sc.getScheduleType() == 23){//个人体验
                    for (int i = 1; i <= personExpList.size(); i++) {
                        if(Objects.equals(sc.getId(),personExpList.get(i-1).getId())) {
                            sc.setRowNum(i);
                        }
                    }
                }
            }
            
            
        }
        psyConsultVO.setScheduleList(scheduleList);
        return psyConsultVO;
    }
}
