package com.renxin.psychology.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.NewConstants;
import com.renxin.common.utils.NewDateUtil;
import com.renxin.common.utils.StringUtils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.dto.HeaderDTO;
import com.renxin.psychology.mapper.PsyConsultWorkMapper;
import com.renxin.psychology.request.PsyConsultWorkReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.vo.PsyConsultOrderItemVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PsyConsultWorkServiceImpl extends ServiceImpl<PsyConsultWorkMapper, PsyConsultWork> implements IPsyConsultWorkService {
    
    @Resource
    private PsyConsultWorkMapper psyConsultWorkMapper;

    @Override
    public List<PsyConsultWorkVO> getConsultWorks(PsyWorkReq req) {
        return psyConsultWorkMapper.getWorks(req);
    }

    @Override
    public void doSave(PsyConsultWorkReq req) {
        long num = NewDateUtil.getTwoDateDays(req.getStartDay(), req.getEndDay()) + 1;

        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            days.add(i);
        }

        List<PsyConsultWork> list = new ArrayList<>();
        req.getIds().forEach(p -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(req.getStartDay());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            days.forEach(i -> {
                if (i > 1) {
                    calendar.add(Calendar.DATE, 1);
                }

                String fm = sdf.format(calendar.getTime());
                LambdaQueryWrapper<PsyConsultWork> wp = new LambdaQueryWrapper<>();
                wp.eq(PsyConsultWork::getConsultId, p);
                wp.eq(PsyConsultWork::getDay, fm);

                PsyConsultWork work = this.getOne(wp, false);
                if (work == null) {
                    work = new PsyConsultWork();
                    work.setConsultId(p);
                    work.setDay(fm);
                    work.setWeek(NewDateUtil.getWeekOfDate(calendar));
                }

//                work.setTimeStart(req.getTimeStart());
//                work.setTimeEnd(req.getTimeEnd());
                makeTime(work);
                work.setLive(req.getLive());
                work.setTimes(req.getTimes());
                work.setStatus(req.getStatus());
                list.add(work);
            });
        });

        // 先删除,后新增
//        LambdaQueryWrapper<PsyConsultWork> wp = new LambdaQueryWrapper<>();
//        wp.in(PsyConsultWork::getConsultId, req.getIds());
//        wp.in(PsyConsultWork::getDay, dayDelt);
//        this.remove(wp);

        this.saveOrUpdateBatch(list);
    }

    private void makeTime(PsyConsultWork work) {
//        List<Integer> num = new ArrayList<>();
//        if ("0".equals(req.getStatus())) {
//            int s = Integer.parseInt(req.getTimeStart().substring(0, 2));
//            int e = Integer.parseInt(req.getTimeEnd().substring(0, 2));
//            for (int i = s; i <= e; i++) {
//                num.add(i);
//            }
//        }
//
//        Collections.sort(num);
//        req.setLive(JSONObject.toJSONString(num));
        if (work.getId() == null) {// 保留历史记录
            work.setUsed(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    @Override
    public List<HeaderDTO> getWorkHeader(String month) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotEmpty(month)) {
            Date date = NewDateUtil.strToDate(month, NewConstants.DATE_FORMAT_MONTH);
            if (date == null) {
                date = new Date();
            }
            c.setTime(date);
        }

        List<HeaderDTO> headers = new ArrayList<>();
        HeaderDTO header1 = new HeaderDTO();
        header1.setLabel("咨询师");
        header1.setProp("nickName");
        headers.add(header1);

        int max_day_of_month = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 1; i <= max_day_of_month; i++) {
            if (i > 1) {
                c.add(Calendar.DATE, 1);
            }

            String fm = sdf.format(c.getTime());
            HeaderDTO headerItem = new HeaderDTO();
            headerItem.setLabel(StrUtil.format("{}({})", fm.substring(5, 10), NewDateUtil.getWeekOfDate(c)));
            headerItem.setProp(fm);
            headers.add(headerItem);
        }

        return headers;
    }

    private List<Long> getConsultDetailIds(PsyWorkReq req) {
        req.setEnd(req.getStart());
        return psyConsultWorkMapper.getConsultIds(req);
    }

    @Override
    public List<Long> getConsultIds(PsyWorkReq req) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotEmpty(req.getMonth())) {
            Date date = NewDateUtil.strToDate(req.getMonth(), NewConstants.DATE_FORMAT_MONTH);
            if (date == null) {
                date = new Date();
            }
            calendar.setTime(date);
        }

        int max_day_of_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        req.setStart(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, max_day_of_month);
        req.setEnd(sdf.format(calendar.getTime()));

        return psyConsultWorkMapper.getConsultIds(req);
    }

    @Override
    public List<HashMap<String, String>> getWorks(PsyWorkReq req, List<Long> ids) {
        List<HashMap<String, String>> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(ids)) {
            return items;
        }

        ids.forEach(consultId -> {
            PsyWorkReq req1 = new PsyWorkReq();
            req1.setConsultId(consultId);
            req1.setStart(req.getStart());
            req1.setEnd(req.getEnd());
            req1.setStatus(req.getStatus());
            List<PsyConsultWorkVO> works = getConsultWorks(req1);
            HashMap<String, String> node = new HashMap<>();
            node.put("id", String.valueOf(consultId));

            node.put("nickName", works.get(0).getNickName());
            node.put("userName", StrUtil.format("(系统账号:{})", works.get(0).getUserName()));
            req1.setStatus(ConsultConstant.PAY_STATUE_PAID);
            List<PsyConsultOrderItemVO> orderItems = psyConsultWorkMapper.getOrderItems(req1);
            node.put("items", JSONObject.toJSONString(orderItems));

            works.forEach(a -> {
                String v = "未排班";
                if ("0".equals(a.getStatus())) {
//                    v = StrUtil.format("{}-{}", a.getTimeStart(), a.getTimeEnd());
                    v = a.getLive();
                } else if ("1".equals(a.getStatus())) {
                    v = "休息";
                }
                node.put(a.getDay(), v);
            });

            items.add(node);
        });

        return items;
    }

    @Override
    public List<PsyConsultWork> checkDataUnique(PsyConsultWorkVO req) {
        return psyConsultWorkMapper.checkDataUnique(req);
    }

    @Override
    public PsyConsultWorkVO getOne(Long id) {
        return BeanUtil.toBean(psyConsultWorkMapper.selectById(id), PsyConsultWorkVO.class);
    }

    private LambdaQueryWrapper<PsyConsultWork> getWorkWrapper(Long id, Long consultId, Integer time) {
        LambdaQueryWrapper<PsyConsultWork> wp = new LambdaQueryWrapper<>();
        wp.eq(ObjectUtils.isNotEmpty(consultId),PsyConsultWork::getConsultId, consultId);
        wp.eq(ObjectUtils.isNotEmpty(id),PsyConsultWork::getId, id);
        wp.eq(PsyConsultWork::getStatus, "0");
        return wp;
    }

    @Override
    public Boolean checkWork(Long id, Long consultId, Integer time) {
        List<PsyConsultWork> works = psyConsultWorkMapper.selectList(getWorkWrapper(id, consultId, time));
        if (CollectionUtils.isEmpty(works)) {
            return Boolean.FALSE;
        }
        PsyConsultWork work = works.get(0);
        if (StringUtils.isBlank(work.getLive())) {
            return Boolean.FALSE;
        }

        List<Integer> lives = JSON.parseArray(work.getLive(), Integer.class);
        List<Integer> used = JSON.parseArray(work.getUsed(), Integer.class);
        return lives.contains(time) && !used.contains(time);
    }

    /**
     *
     * @param id    排班id
     * @param consultId 咨询师
     * @param time  时间
     * @param type  1-扣减 2-释放 3-不处理
     * @return  排班
     */
    @Override
    public PsyConsultWork handleWork(Long id, Long consultId, Integer time, int type) {
        // 支付、预约加库存,转介释放库存
        List<PsyConsultWork> works = psyConsultWorkMapper.selectList(getWorkWrapper(id, consultId, time));
        PsyConsultWork work = works.get(0);

        // 创建订单时,不处理
        if (type == 3) {
            return work;
        }

        List<Integer> uesd = JSON.parseArray(work.getUsed(), Integer.class);

        if (1 == type) {
            uesd.add(time);
        } else {
            uesd.remove(time);
        }

        Collections.sort(uesd);
        work.setUsed(JSONObject.toJSONString(uesd));
        psyConsultWorkMapper.updateById(work);
        return work;
    }

    @Override
    public HashMap<String, String> getWorkDetail(PsyWorkReq req) {
        List<Long> ids = getConsultDetailIds(req);
        List<HashMap<String, String>> list = getWorks(req, ids);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public List<HashMap<String, String>> getWorks(PsyWorkReq req) {
        List<Long> ids = getConsultIds(req);
        List<HashMap<String, String>> list = getWorks(req, ids);
        return list;
    }

    @Override
    public List<PsyConsultWork> getList(PsyConsultWorkVO req) {
        return psyConsultWorkMapper.getList(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(PsyConsultWorkVO req) {
        if(StringUtils.isBlank(req.getConsultType())){
            req.setConsultType(ConsultConstant.CONSULT_TYPE_1);
        }
        converTime(req);
        return psyConsultWorkMapper.insert(BeanUtil.toBean(req, PsyConsultWork.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(PsyConsultWorkVO req) {
        req.setConsultType(null);
        converTime(req);
        return psyConsultWorkMapper.updateById(BeanUtil.toBean(req, PsyConsultWork.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return psyConsultWorkMapper.deleteById(id);
    }

    private void converTime(PsyConsultWorkVO req) {
//        req.setDay(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, req.getTimeStart()));
//        req.setWeek(getWeekOfDate(req.getTimeStart()));
    }
}
