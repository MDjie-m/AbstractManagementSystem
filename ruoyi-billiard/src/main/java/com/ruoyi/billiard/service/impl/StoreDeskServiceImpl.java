package com.ruoyi.billiard.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.*;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.enums.LightTimerType;
import com.ruoyi.billiard.enums.TutorWorkStatus;
import com.ruoyi.billiard.mapper.OrderDeskScoreMapper;
import com.ruoyi.billiard.mapper.OrderDeskTimeMapper;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 台桌Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
public class StoreDeskServiceImpl implements IStoreDeskService {
    @Autowired
    private StoreDeskMapper storeDeskMapper;

    @Autowired
    private IDeskBookingService deskBookingService;
    @Autowired
    private OrderDeskTimeMapper orderDeskTimeMapper;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private StoreTutorMapper storeTutorMapper;
    @Autowired
    private IDeskDeviceRelationService deskDeviceRelationService;

    @Autowired
    private ILightTimerService lightTimerService;

    @Resource
    private IDeskPriceService deskPriceService;

    @Resource
    private OrderDeskScoreMapper orderDeskScoreMapper;

    final static String LINE_UP_KEY = "line_up:{}";
    @Resource
    private RedisCache redisCacheService;

    @Resource
    private IOrderDeskScoreService orderDeskScoreService;

    @Resource
    private IDeskImageService deskImageService;

    /**
     * 查询台桌
     *
     * @param deskId 台桌主键
     * @return 台桌
     */
    @Override
    public StoreDesk selectStoreDeskByDeskId(Long deskId) {
        return storeDeskMapper.selectStoreDeskByDeskId(deskId);
    }

    /**
     * 查询台桌列表
     *
     * @param storeDesk 台桌
     * @return 台桌
     */
    @Override
    public List<StoreDesk> selectStoreDeskList(StoreDesk storeDesk) {
        QueryWrapper<StoreDesk> queryWrapper = this.storeDeskMapper.normalQuery();
        queryWrapper.likeRight(StringUtils.isNotEmpty(storeDesk.getDeskName()), "a.store_name", storeDesk.getStoreName())

                .eq(Objects.nonNull(storeDesk.getDeskNum()), "a.desk_num", storeDesk.getDeskNum())
                .eq(Objects.nonNull(storeDesk.getStoreId()), "a.store_id", storeDesk.getStoreId())
                .in(CollectionUtils.isNotEmpty(storeDesk.getStatusList()), "a.status", storeDesk.getStatusList());
        if (Objects.nonNull(storeDesk.getStatus())) {
            queryWrapper.eq("a.status", storeDesk.getStatus().getValue());
        }
        if (Objects.nonNull(storeDesk.getPlaceType())) {
            queryWrapper.eq("a.place_type", storeDesk.getPlaceType());
        }
        if (Objects.nonNull(storeDesk.getDeskType())) {
            queryWrapper.eq("a.desk_type", storeDesk.getDeskType());
        }
//        if (O
//        bjects.nonNull(storeDesk.getBookingStart())
//                && Objects.nonNull(storeDesk.getBookingEnd())) {
//            String start = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_00, storeDesk.getBookingStart());
//            String end = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_00, storeDesk.getBookingEnd());
//            String startLimit = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, storeDesk.getBookingStart());
//            String existSql = StringUtils.format("(select 1  from t_desk_booking b\n" +
//                    "                 where a.desk_id = b.desk_id\n" +
//                    "                   and ((b.start_time < '{}' and b.end_time > '{}')\n" +
//                    "                     or ('{}' between b.start_time and b.end_time))\n" +
//                    "                   and b.status in (0, 1) and b.start_time >{} )", start, start, end, startLimit);
//            ;
//            queryWrapper.notExists(existSql);
//        }


        List<StoreDesk> res = storeDeskMapper.selectStoreDeskList(queryWrapper);
        if (Objects.nonNull(storeDesk.getBookingCount()) && CollectionUtils.isNotEmpty(res)) {
            Map<Long, Long> map = ArrayUtil.toMap(deskBookingService
                            .selectBookingCount(res.stream().map(StoreDesk::getDeskId).collect(Collectors.toList()),
                                    storeDesk.getBookingStart(), storeDesk.getBookingEnd()),
                    KeyValueVo::getKey, KeyValueVo::getValue);
            res.forEach(p -> {
                p.setBookingCount(map.getOrDefault(p.getDeskId(), 0L));
            });
        }
        if (Objects.equals(Boolean.TRUE, storeDesk.getQueryLastBooking()) && CollectionUtils.isNotEmpty(res)) {
            Map<Long, DeskBooking> map = ArrayUtil.toMap(deskBookingService.queryLastDeskBooking(res.stream().map(StoreDesk::getDeskId).collect(Collectors.toList())),
                    DeskBooking::getDeskId, p -> p);
            res.forEach(p -> {
                p.setBooking(map.get(p.getDeskId()));
            });
        }
        if (Objects.equals(Boolean.TRUE, storeDesk.getQueryTime()) && CollectionUtils.isNotEmpty(res)) {
            List<Long> orderIds = res.stream().map(StoreDesk::getCurrentOrderId).filter(Objects::nonNull).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderIds)) {
                Map<Long, Long> map = ArrayUtil.toMap(orderDeskTimeMapper.getDeskCalcTimes(orderIds),
                        KeyValueVo::getKey, KeyValueVo::getValue);
                res.forEach(p -> {
                    p.setMinutes(map.get(p.getDeskId()));
                });
            }

        }
        return res;
    }

    /**
     * 新增台桌
     *
     * @param storeDesk 台桌
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStoreDesk(StoreDesk storeDesk) {
        storeDesk.setDeskId(IdUtils.singleNextId());
        SecurityUtils.fillCreateUser(storeDesk);
        checkDevice(storeDesk.getCameraDeviceId(), null, "摄像头已绑定到其他桌");
        checkDevice(storeDesk.getLightDeviceId(), null, "摄像头已绑定到其他桌");
        AssertUtil.isTrue(!storeDeskMapper.exists(storeDeskMapper.query().eq(StoreDesk::getStoreId, storeDesk.getStoreId())
                .eq(StoreDesk::getDeskNum, storeDesk.getDeskNum())), "台桌编号重复");
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));


        return storeDeskMapper.insert(storeDesk);
    }

    private void checkDevice(Long deviceId, Long deskId, String msg) {
        if (Objects.isNull(deviceId)) {
            return;
        }
        AssertUtil.isTrue(Objects.isNull(storeDeskMapper.checkDeviceBind(deviceId, deskId)), msg);
    }

    /**
     * 修改台桌
     *
     * @param storeDesk 台桌
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStoreDesk(StoreDesk storeDesk) {
        AssertUtil.isTrue(!storeDeskMapper.exists(storeDeskMapper.query().eq(StoreDesk::getStoreId, storeDesk.getStoreId())
                        .eq(StoreDesk::getDeskNum, storeDesk.getDeskNum()).notIn(StoreDesk::getDeskId, storeDesk.getDeskId())),
                "台桌编号重复");
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));
        storeDesk.setStatus(null);
        storeDesk.setCurrentOrderId(null);
        checkDevice(storeDesk.getCameraDeviceId(), storeDesk.getDeskId(), "摄像头已绑定到其他桌");
        checkDevice(storeDesk.getLightDeviceId(), storeDesk.getDeskId(), "摄像头已绑定到其他桌");
        SecurityUtils.fillUpdateUser(storeDesk);


        return storeDeskMapper.updateById(storeDesk);
    }

    /**
     * 批量删除台桌
     *
     * @param deskIds 需要删除的台桌主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStoreDeskByDeskIds(Long[] deskIds) {
        int res = 0;
        for (Long deskId : deskIds) {
            res += deleteStoreDeskByDeskId(deskId);
        }
        return res;
    }

    /**
     * 删除台桌信息
     *
     * @param deskId 台桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskId(Long deskId) {
        AssertUtil.isTrue(!orderDeskTimeMapper.exists(OrderDeskTime::getDeskId, deskId), "台桌已被使用,无法删除.");
        return storeDeskMapper.deleteStoreDeskByDeskId(deskId);
    }

    @Override
    public List<Store> getByLoginUserId(Long loginUserId) {
        return storeDeskMapper.selectStoreByLoginUserId(loginUserId);
    }

    @Override
    public CashierDeskDashboardResVo getDeskDashboard(Long storeId) {
        CashierDeskDashboardResVo resVo = new CashierDeskDashboardResVo();

        resVo.setDeskCount(storeDeskMapper.queryDeskCountGroupByStatus(storeId));
        resVo.setTutorCount(storeTutorMapper.queryCountGroupByWorkStatus(storeId));

        return resVo;
    }

    private Integer getStatusCount(Long storeId, DeskStatus status) {
        return Math.toIntExact(storeDeskMapper.selectCount(storeDeskMapper.query().eq(StoreDesk::getStoreId, storeId)
                .eq(StoreDesk::getEnable, Boolean.TRUE).eq(StoreDesk::getStatus, status)));
    }

    private Integer getTutorStatusCount(Long storeId, TutorWorkStatus status) {
        return Math.toIntExact(storeTutorMapper.selectCount(storeTutorMapper.query().eq(StoreTutor::getStoreId, storeId)
                .eq(StoreTutor::getStatus, EmployeeStatus.WORK.getValue()).eq(StoreTutor::getWorkStatus, status.getValue())));
    }

    @Override
    public Boolean saveLineUpInfo(Long storeId, Map<Integer, LineUpVo> reqVo) {

        String json = JSON.toJSONString(reqVo);
        redisCacheService.setCacheObject(StringUtils.format(LINE_UP_KEY, storeId), json);
        return true;
    }

    @Override
    public DeskQueryResVo queryDestCurrentInfo(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);
        DeskQueryResVo resVo = new DeskQueryResVo();
        BeanUtils.copyProperties(desk, resVo);
        setTimer(deskId, resVo);
        List<DeskBooking> bookingList = deskBookingService.queryLastDeskBooking(Collections.singletonList(deskId));
        if (CollectionUtils.isNotEmpty(bookingList)) {
            resVo.setBooking(bookingList.get(0));
        }

        Order order = orderService.selectRelationOrderWithDetail(deskId);
        if (Objects.isNull(order)) {
            return resVo;
        }
        resVo.setLastActiveOrder(order);
        resVo.calcFees();
        resVo.setScore(Optional.ofNullable(orderDeskScoreMapper.selectOne(orderDeskScoreMapper.query()
                        .eq(OrderDeskScore::getDeskId, deskId)
                        .eq(OrderDeskScore::getOrderId, order.getOrderId())
                        .orderByDesc(OrderDeskScore::getStartTime).last(" limit 1")))
                .orElse(resVo.getScore()));
        return resVo;

    }

    private void setTimer(Long deskId, DeskQueryResVo resVo) {
        LightTimer timer = lightTimerService.getOne(lightTimerService.lambdaQuery().eq(LightTimer::getDeskId, deskId).last(" limit 1").getWrapper());

        if (Objects.isNull(timer)) {
            return;
        }
        int times = DateUtils.diffSeconds(DateUtils.getNowDate(), timer.getEndTime());
        if (times <= 0) {
            times = 0;
        } else {
            int temp = times % 60 == 0 ? 0 : 1;
            times = (times / 60) + temp;
        }
        if (Objects.equals(timer.getLightType(), LightTimerType.CALC_FEE)) {
            resVo.setLastCalcTime(times);
        } else {
            resVo.setLastTempTime(times);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo startCalcFee(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.WAIT), "当前不是空闲状态，无法开台。");
        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌正在计费中");
        AssertUtil.isNullOrEmpty(desk.getCurrentOrderId(), "台桌正在计费中");

        Order order = orderService.createOrder(deskId);
        desk.setCurrentOrderId(order.getOrderId());
        desk.setStatus(DeskStatus.BUSY);
        this.storeDeskMapper.updateById(desk);

        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(order);

        BeanUtils.copyProperties(desk, resVo);
        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo pauseCalcFee(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.BUSY), "当前不是计费状态，无法暂停。");
        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌没有在计费,无法暂停");

        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(orderService.pauseCalcFee(deskId));

        desk.setStatus(DeskStatus.PAUSE);
        this.storeDeskMapper.updateById(desk);
        BeanUtils.copyProperties(desk, resVo);
        resVo.calcFees();

        return resVo;
    }

    @Override
    public DeskQueryResVo resumeDesk(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.PAUSE), "当前不是暂停状态，无法恢复计费。");
        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌正在计费,无法恢复计费.");

        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(orderService.resumeCalcFee(deskId));

        desk.setStatus(DeskStatus.BUSY);
        this.storeDeskMapper.updateById(desk);
        BeanUtils.copyProperties(desk, resVo);
        resVo.calcFees();

        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo swapToNewDesk(Long deskId, Long storeId, Long orderId, Long newDeskId) {
        StoreDesk currentDesk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(currentDesk.getStatus(), DeskStatus.BUSY), "当前台桌不是计费状态，无法换台。");
        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(deskId), "当前台桌正不是计费中,无法更换。");

        StoreDesk newDesk = queryEnableDesk(newDeskId, storeId);

        AssertUtil.isTrue(Objects.equals(newDesk.getStatus(), DeskStatus.WAIT)
                && Objects.isNull(newDesk.getCurrentOrderId()), "目标台桌不是空闲状态，无法更换,请更换到其他台桌。");

        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(newDeskId), "目标台桌不是空闲状态，无法更换,请更换到其他台桌。");

        Order order = orderService.swapToNewDesk(deskId, orderId, newDeskId);

        DeskQueryResVo resVo = new DeskQueryResVo();
        BeanUtils.copyProperties(storeDeskMapper.selectById(newDeskId), resVo);
        resVo.setLastActiveOrder(order);
        resVo.calcFees();
        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo mergeToNewDesk(Long deskId, Long storeId, Long orderId, Long newDeskId) {
        StoreDesk currentDesk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(currentDesk.getStatus(), DeskStatus.BUSY), "当前台桌不是计费状态，无法并台");
        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(deskId), "当前台桌正不是计费中,无法并台。");

        StoreDesk newDesk = queryEnableDesk(newDeskId, storeId);

        AssertUtil.isTrue(Objects.equals(newDesk.getStatus(), DeskStatus.BUSY)
                && Objects.nonNull(newDesk.getCurrentOrderId()), "目标台桌不是计费中，无法并台,请更换到其他台桌。");

        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(newDeskId), "目标台桌不是计费中，无法并台,请更换到其他台桌。");

        Order order = orderService.mergeToNewDesk(deskId, orderId, newDeskId);

        DeskQueryResVo resVo = new DeskQueryResVo();
        BeanUtils.copyProperties(storeDeskMapper.selectById(newDeskId), resVo);
        resVo.setLastActiveOrder(order);
        resVo.calcFees();

        return resVo;
    }

    @Override
    public Boolean addScore(AddDeskScoreReqVo reqVo) {

        StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query().eq(StoreDesk::getDeskNum, reqVo.getDeskNum())
                .eq(StoreDesk::getStoreId, reqVo.getStoreId()).last(" limit 1"));
        if (Objects.isNull(desk)) {
            return false;
        }
        if (Objects.isNull(desk.getCurrentOrderId())) {
            return false;
        }
        return orderDeskScoreService.addScore(reqVo.getBtnType(), reqVo.getStoreId(), desk.getDeskId(), desk.getCurrentOrderId());
    }

    @Override
    public Boolean addCapture(DeskCaptureReqVo reqVo) {
        StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query().eq(StoreDesk::getDeskNum, reqVo.getDeskNum())
                .eq(StoreDesk::getStoreId, reqVo.getStoreId()).last(" limit 1"));
        if (Objects.isNull(desk)) {
            return false;
        }
        if (Objects.isNull(desk.getCurrentOrderId())) {
            return false;
        }
        return deskImageService.addCapture(reqVo.getStoreId(), desk.getDeskId(), desk.getCurrentOrderId(), desk.getCameraDeviceId());
    }

    private StoreDesk queryEnableDesk(Long deskId, Long storeId) {
        StoreDesk desk = storeDeskMapper.selectStoreDeskByDeskIdAndStoreId(deskId, storeId);
        AssertUtil.notNullOrEmpty(desk, "非法参数");
        AssertUtil.isTrue(Objects.equals(desk.getEnable(), Boolean.TRUE), "台桌未启用");
        desk.setPrice(deskPriceService.queryPriceByType(storeId, desk.getDeskType()));
        return desk;
    }


    @Override
    public Map<Integer, LineUpVo> getLineUpInfo(Long storeId) {
        String res = redisCacheService.getCacheObject(StringUtils.format(LINE_UP_KEY, storeId));
        if (StringUtils.isEmpty(res)) {
            return new HashMap<>();
        }

        return JSON.parseObject(res, new TypeReference<Map<Integer, LineUpVo>>() {
        });
    }
}
