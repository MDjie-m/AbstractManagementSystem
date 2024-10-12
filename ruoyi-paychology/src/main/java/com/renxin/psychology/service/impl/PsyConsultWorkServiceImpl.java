package com.renxin.psychology.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.Constants;
import com.renxin.common.constant.NewConstants;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.NewDateUtil;
import com.renxin.common.utils.StringUtils;
import com.renxin.common.wxMsg.NoticeMessage;
import com.renxin.common.wxMsg.NoticeMethodEnum;
import com.renxin.common.wxMsg.TemplateMessageItemVo;
import com.renxin.common.wxMsg.WxMsgUtils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.dto.HeaderDTO;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.dto.RecentWorkDTO;
import com.renxin.psychology.mapper.PsyConsultWorkMapper;
import com.renxin.psychology.request.PsyConsultWorkReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderItemVO;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import com.renxin.system.service.ISysConfigService;
import com.renxin.wechat.vo.TemplateMessageItemOldVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PsyConsultWorkServiceImpl extends ServiceImpl<PsyConsultWorkMapper, PsyConsultWork> implements IPsyConsultWorkService {
    
    @Resource
    private PsyConsultWorkMapper psyConsultWorkMapper;

    @Resource
    private IPsyConsultOrderItemService orderItemService;

    @Resource
    private IPsyConsultantScheduleService consultantScheduleService;
    
    @Resource
    private IPsyConsultantOrderService consultantOrderService;

    @Resource
    private IPsyConsultOrderService consultOrderService;

    @Resource
    private ISysConfigService configService;
    
    @Resource
    private WxMsgUtils wxMsgUtils;

    @Resource
    private IPsyUserService userService;
    
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
    public List<PsyConsultantSchedule> getTodoList(PsyWorkReq req){
        //Long consultId = req.getConsultId();//本咨询师id
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        req.setDay(today);
        req.setStatus("0");//待办
        //查询面向[来访者]的服务待办清单
        List<OrderItemDTO> orderItemList = orderItemService.getTodoList(req);
        //查询每一条任务, 是该[来访者-咨询师]之间的第几次
        for (OrderItemDTO item : orderItemList) {
            req.setUserId(item.getUserId());
            req.setRealTime(item.getRealTime());
            Integer timeNum = orderItemService.getTimeNumForConsulted(req);
            item.setTimeNum(timeNum);
            item.setScheduleType(12);//咨询
        }
        
        //查询面向[咨询师]的服务待办清单
        PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
            scheduleReq.setConsultId(req.getConsultId());
            scheduleReq.setDay(today);
        List<PsyConsultantSchedule> scheduleList = consultantScheduleService.selectPsyConsultantScheduleList(scheduleReq);
        //若服务为 [个督/体验], 则需查询是[付费咨询师-收费咨询师]之间的第几次
        for (PsyConsultantSchedule sc : scheduleList) {
            if (sc.getScheduleType() == 2){//咨询服务
                req.setPayConsultId(Long.valueOf(sc.getCreateBy()));
                req.setChargeConsultId(sc.getConsultId());
                req.setRealTime(sc.getRealTime());
                int timeNum = consultantScheduleService.getTimeNum(req);
                sc.setTimeNum(timeNum);
                
                //为null时赋值""
                sc.setUserNickName(ObjectUtils.isNotEmpty(sc.getUserNickName()) ? sc.getUserNickName() : "");
                sc.setUserId(ObjectUtils.isNotEmpty(sc.getUserId()) ? sc.getUserId() : "");
            }
        }
        
        
        //清单合并
        List<OrderItemDTO> scheduleToItemList = BeanUtil.copyToList(scheduleList, OrderItemDTO.class);
        List<PsyConsultantSchedule> orderItemToSchedule = BeanUtil.copyToList(orderItemList, PsyConsultantSchedule.class);

        List<PsyConsultantSchedule> mergedList = new ArrayList<>(scheduleList);
        mergedList.addAll(orderItemToSchedule);
        // 按timeStart字段（字符串格式为"HH:mm"）进行正序排序
        mergedList.sort(Comparator.comparing(dto -> LocalTime.parse(dto.getTimeStart())));

        return mergedList;
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

    /**
     * 本咨询师近期任务
     * @param req
     * @return
     */
    @Override
    public List<RecentWorkDTO> recentWorkList(PsyWorkReq req){
        fillStartAndEnd(req);

        //查询面向[来访者]的服务待办清单
        List<OrderItemDTO> orderItemList = orderItemService.getTodoList(req);

        //查询面向[咨询师]的服务待办清单
        PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
        scheduleReq.setConsultId(req.getConsultId());
        scheduleReq.setStart(req.getStart());
        scheduleReq.setEnd(req.getEnd());
        scheduleReq.setWeek(req.getWeek());
        List<PsyConsultantSchedule> scheduleList = consultantScheduleService.selectPsyConsultantScheduleList(scheduleReq);

        //清单合并
        List<PsyConsultantSchedule> itemToScheduleList = BeanUtil.copyToList(orderItemList, PsyConsultantSchedule.class);
        itemToScheduleList.forEach(p -> p.setScheduleType(12));
        List<PsyConsultantSchedule> mergedList = new ArrayList<>(scheduleList);
        mergedList.addAll(itemToScheduleList);
        // 按timeStart字段（字符串格式为"2024-05-05 15:15"）进行正序排序
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //mergedList.sort(Comparator.comparing(dto -> LocalTime.parse(dto.getRealTime())));
       // mergedList.sort(Comparator.comparing(dto -> { return LocalDateTime.parse(dto.getTimeStart(), formatter);}));

        // 使用流按day字段分组
        Map<String, List<PsyConsultantSchedule>> groupedByDay = mergedList.stream()
                .collect(Collectors.groupingBy(PsyConsultantSchedule::getDay));

        // 将分组结果转换为RecentWorkDTO的列表
        List<RecentWorkDTO> recentWorkDTOList = groupedByDay.entrySet().stream()
                .map(entry -> {
                    RecentWorkDTO dto = new RecentWorkDTO();
                    dto.setDate(entry.getKey()); // 设置日期
                    dto.setScheduleList(entry.getValue()); // 设置任务清单
                    return dto;
                })
                .collect(Collectors.toList());
        
        return recentWorkDTOList;
        
    }
    
    //填充起止日期 (默认今天至下个月底)
    private void fillStartAndEnd(PsyWorkReq req){
        String start = req.getStart();
        String end = req.getEnd();
        if (ObjectUtils.isNotEmpty(start) && ObjectUtils.isNotEmpty(end)){
            return;
        }
        
        //获取今天的日期
        LocalDate today = LocalDate.now();
        // 计算下个月的第一天的日期
        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        // 计算下个月的最后一天
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.plusMonths(1).minusDays(1);
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 格式化日期
        String todayStr = today.format(formatter);
        String endOfNextMonthStr = lastDayOfNextMonth.format(formatter);
        
        req.setStart(todayStr);
        req.setEnd(endOfNextMonthStr);
        
    }

    /**
     * 指定预约任务请假
     * @param req
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void scheduleLeave(PsyWorkReq req){
        Integer scheduleType = req.getScheduleType();
        Long scheduleId = req.getScheduleId();
        Long loginConsultId = req.getConsultId();
        Long loginUserId = req.getUserId();

        switch (scheduleType) {
            //个督/体验
            case 22 :
            case 23 :
                PsyConsultantSchedule schedule = consultantScheduleService.selectPsyConsultantScheduleById(scheduleId);
                if (ObjectUtils.isEmpty(schedule)){
                    throw new ServiceException("scheduleId不存在, 找不到指定任务");
                }
                if (!"0".equals(schedule.getStatus())){
                    throw new ServiceException("该预约已完成或已取消, 不可请假");
                }
                
                if ((loginConsultId+"").equals(schedule.getCreateBy())){
                    schedule.setStatus("2");//付费人请假
                    isBeforeTime(schedule.getServerStartTime());//判断请假提前时间是否足够
                }
                else if (loginConsultId.equals(schedule.getConsultId())){
                    schedule.setStatus("3");//收费人请假
                }else{
                    throw new ServiceException("当前用户与该排程任务不相关");
                }
                //排程请假
                consultantScheduleService.updatePsyConsultantSchedule(schedule);
                //订单状态修改为"进行中"
                PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(schedule.getOrderId());
                if (ConsultConstant.CONSULT_ORDER_STATUE_PENDING.equals(consultantOrder.getStatus())){//已完成
                    consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);//进行中
                    consultantOrderService.updatePsyConsultantOrder(consultantOrder);
                }
                break;
            //咨询
            case 12 :
                OrderItemDTO itemReq = new OrderItemDTO();
                itemReq.setId(scheduleId);
                List<OrderItemDTO> orderItemList = orderItemService.queryOrderItemList(itemReq);
                if (ObjectUtils.isEmpty(orderItemList)){
                    throw new ServiceException("scheduleId不存在,找不到指定任务.");
                }
                OrderItemDTO item = orderItemList.get(0);
                if (!"0".equals(item.getStatus())){
                    throw new ServiceException("该预约已完成或已取消, 不可请假");
                }
                
                String explain = "";
                if (item.getUserId().equals(loginUserId)){
                    item.setStatus("2");//付费人请假
                    isBeforeTime(item.getServerStartTime());//判断请假提前时间是否足够
                    explain = "来访者请假，本次预约取消";
                }else if (item.getConsultId().equals(loginConsultId)){
                    item.setStatus("3");//收费人请假
                    explain = "咨询师请假，本次预约取消";
                }else{
                    throw new ServiceException("当前用户与该排程任务不相关.");
                }
                
                //子订单请假
                orderItemService.update(item);
                
                //主订单信息修改
                PsyConsultOrderVO order = consultOrderService.getOne(item.getOrderId());
                order.setNum(order.getNum() + 1);
                order.setBuyNum(order.getBuyNum() - 1);
                if (ConsultConstant.CONSULT_ORDER_STATUE_PENDING.equals(order.getStatus())){//已完成
                    order.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);//进行中
                }
                consultOrderService.update(order);
                
                //若为来访者请假, 则将咨询师在该时段置为空闲
                if (item.getUserId().equals(loginUserId)){
                    PsyConsultWorkVO workVO = new PsyConsultWorkVO();
                    workVO.setConsultId(item.getConsultId());
                    workVO.setDay(item.getDay());
                    workVO.setTimes(item.getTime()+"");
                    idleTime(workVO);
                }
                
                //发送通知
                NoticeMessage notice = new NoticeMessage();
                notice.setMessageType(Constants.MSG_SCHEDULE_LEAVE);//预约取消
                notice.setNoticeMethod(NoticeMethodEnum.WECHAT);
                notice.setReceiverId(userService.getOpenId(item.getUserId()));
                //notice.setReceiverId("oP8146998AoIjkNMZx4s2vK4me5w");

                HashMap<String, TemplateMessageItemVo> msgMap = new HashMap<>();
                msgMap.put("thing1", new TemplateMessageItemVo(order.getServeName()));//主题
                msgMap.put("date2", new TemplateMessageItemVo(item.getDay() + " " + item.getTimeStart()));//时间
                msgMap.put("thing8", new TemplateMessageItemVo(item.getTimeStart() + "-" + item.getTimeEnd()));//时段
                msgMap.put("thing4", new TemplateMessageItemVo(explain));//原因

                notice.setMsgMap(msgMap);
                wxMsgUtils.send(notice);
                break;
                
            //无匹配
            default:
                throw new ServiceException("该类型的预约不存在或不可请假");
        }
    }
    
    //判断请假提前时间是否足够 (默认必须提前12小时)
    private void isBeforeTime(String serverTime){
        //获取系统参数
        Integer leaveAdvanceHour = Integer.valueOf(configService.selectConfigByKey("leave.advance.hour"));

        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 定义目标时间 "2024-05-05 14:00"
        LocalDateTime targetDateTime = LocalDateTime.parse(serverTime, formatter);

        // 当前时间
        LocalDateTime now = LocalDateTime.now();

        // 计算目标时间减去两小时
       // LocalDateTime targetMinusTwoHours = targetDateTime.minusHours(leaveAdvanceHour);
        LocalDateTime targetMinusTwoHours = targetDateTime.minusHours(10000);

        // 判断当前时间是否早于目标时间减去两小时
        if (!now.isBefore(targetMinusTwoHours))  {
            //throw new ServiceException("请假必须提前" + leaveAdvanceHour + "小时申请, 当前已不可发起, 请与咨询师联系协商");
            throw new ServiceException("请联系咨询师");
        }
    }
    
    //将指定咨询师的指定时段置为空闲
    private void idleTime(PsyConsultWorkVO req){
        List<PsyConsultWork> list = psyConsultWorkMapper.getList(req);
        if (ObjectUtils.isEmpty(list)){
            log.warn(req.getConsultId()+ "在" + req.getDay() + "没有排程");
            return;
        }
        Integer time = Integer.valueOf(req.getTimes());
        PsyConsultWork consultWork = list.get(0);
        String live = consultWork.getLive();
        String used = consultWork.getUsed();
        consultWork.setUsed(modifyStringList(live,time,false));//删除工作时间
        psyConsultWorkMapper.updateById(consultWork);
    }

    //向[8,14,15,16,17,18]中添加或删除
    public static String modifyStringList(String str, int number, boolean toAdd) {
        String[] items = str.substring(1, str.length() - 1).split(",");

        List<String> itemList = new ArrayList<>(Arrays.asList(items));

        List<Integer> intList = new ArrayList<>();
        for (String item : itemList) {
            intList.add(Integer.parseInt(item));
        }

        if (toAdd) {
            if (!intList.contains(number)) {
                intList.add(number);
            }
        } else {
            intList.remove((Integer) number);
        }

        List<String> modifiedItemList = new ArrayList<>();
        for (int num : intList) {
            modifiedItemList.add(String.valueOf(num));
        }

        String modifiedString = "[" + String.join(",", modifiedItemList) + "]";
        return modifiedString;
    }


    /**
     * 咨询师指定时间请假
     * @param req
     */
    @Override
    public void dateLeave(PsyWorkReq req){
        List<PsyConsultWork> workList = psyConsultWorkMapper.selectList(new LambdaQueryWrapper<PsyConsultWork>()
                .eq(PsyConsultWork::getDay, req.getDay())
                .eq(PsyConsultWork::getConsultId, req.getConsultId()));
        if (ObjectUtils.isEmpty(workList)){
            throw new ServiceException("当前咨询师在这一天没有排程, 无需请假");
        }

        PsyConsultWork work = workList.get(0);
        String leaveHours = req.getHours();
        //仅live涉及的时间点, 需要填入used中请假
        if (ObjectUtils.isEmpty(leaveHours)){
            work.setUsed(work.getLive());
        }else {
            String commonHours = getCommonHours(leaveHours, work.getLive());
            work.setUsed(commonHours);
        }
        psyConsultWorkMapper.updateById(work);
    }
    
    //获取时间点的交集
    private String getCommonHours(String aHours,String bHours){
        aHours = aHours.replace("[","").replace("]","");
        bHours = bHours.replace("[","").replace("]","");

        Set<String> aSet = new HashSet<>(Arrays.asList(aHours.split(",")));
        Set<String> bSet = new HashSet<>(Arrays.asList(bHours.split(",")));
        aSet.retainAll(bSet);
        
        String result = new TreeSet<String>(aSet).toString();
        return result;
    }

    //收费咨询师针对个督写记录
    @Override
    public void recordSchedule(PsyWorkReq req){
        Integer sourceType = req.getSourceType();
        if (sourceType == 1){//来访者咨询
            PsyConsultOrderItem orderItem = orderItemService.getOne(req.getScheduleId());
            if (!orderItem.getConsultId().equals(req.getConsultId())){
                throw new ServiceException("您并非该预约的收费人, 无发对其填写记录");
            }
            orderItem.setWorkRecord(req.getWorkRecord());
            orderItemService.update(orderItem);
        } else if (sourceType == 2){//个督/体验
            PsyConsultantSchedule schedule = consultantScheduleService.selectPsyConsultantScheduleById(req.getScheduleId());
            if (!schedule.getConsultId().equals(req.getConsultId())){
                throw new ServiceException("您并非该预约的收费人, 无发对其填写记录");
            }
            schedule.setWorkRecord(req.getWorkRecord());
            consultantScheduleService.updatePsyConsultantSchedule(schedule);
        }
        
    }
    
    //付费咨询师针对个督确认完成
    @Override
    public void confirmSchedule(PsyWorkReq req){
        PsyConsultantSchedule schedule = consultantScheduleService.selectPsyConsultantScheduleById(req.getScheduleId());
        if (!schedule.getCreateBy().equals(req.getConsultId()+"")){
            throw new ServiceException("您并非该预约的付费人, 无发确认完成");
        }
        schedule.setCustomerConfirm(1);//1.已确认
        consultantScheduleService.updatePsyConsultantSchedule(schedule);
    }
    
}
