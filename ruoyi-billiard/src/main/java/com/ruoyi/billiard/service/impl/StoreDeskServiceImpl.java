package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.domain.vo.DeskQueryResVo;
import com.ruoyi.billiard.domain.vo.LineUpVo;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.enums.TutorWorkStatus;
import com.ruoyi.billiard.mapper.OrderDeskTimeMapper;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import com.ruoyi.billiard.service.IDeskPriceService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.service.IStoreDeskService;
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
    private OrderDeskTimeMapper orderDeskTimeMapper;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private StoreTutorMapper storeTutorMapper;
    @Autowired
    private IDeskDeviceRelationService deskDeviceRelationService;

    @Resource
    private IDeskPriceService deskPriceService;

    final static String LINE_UP_KEY = "line_up:{}";
    @Resource
    private RedisCache redisCacheService;

    /**
     * 查询台桌
     *
     * @param deskId 台桌主键
     * @return 台桌
     */
    @Override
    public StoreDesk selectStoreDeskByDeskId(Long deskId) {
        return storeDeskMapper.selectById(deskId);
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
                .eq(Objects.nonNull(storeDesk.getStatus()), "a.status", storeDesk.getStatus())
                .eq(Objects.nonNull(storeDesk.getPlaceType()), "a.place_type", storeDesk.getPlaceType())
                .eq(Objects.nonNull(storeDesk.getDeskNum()), "a.desk_num", storeDesk.getDeskNum())
                .eq(Objects.nonNull(storeDesk.getStoreId()), "a.store_id", storeDesk.getStoreId())
                .in(CollectionUtils.isNotEmpty(storeDesk.getStatusList()), "a.status", storeDesk.getStatusList())
        ;


        return storeDeskMapper.selectStoreDeskList(queryWrapper);
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
        int  res=0;
        for (Long deskId : deskIds) {
            res+=deleteStoreDeskByDeskId(deskId);
        }
        return  res;
    }

    /**
     * 删除台桌信息
     *
     * @param deskId 台桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskId(Long deskId) {
        AssertUtil.isTrue(!orderDeskTimeMapper.exists(OrderDeskTime::getDeskId,deskId),"台桌已被使用,无法删除.");
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
                .eq(StoreDesk::getEnable, Boolean.TRUE).eq(StoreDesk::getStatus, status.getValue())));
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

        Order order = orderService.selectLastActiveOrder(deskId);
        if (Objects.isNull(order)) {
            return resVo;
        }
        resVo.setLastActiveOrder(order);
        calcOrderFee(resVo);
        return resVo;

    }

    private static void calcOrderFee(DeskQueryResVo resVo) {
        Order order = resVo.getLastActiveOrder();
        if (Objects.isNull(order)) {
            return;
        }
        //台桌费用
        resVo.setDeskTotalTimeAmount(Optional.ofNullable(order.getOrderDeskTimes()).orElse(Lists.newArrayList())
                .stream().map(p -> {
                    int minutes = DateUtils.deskTimeDiffMinutes(p.getStartTime(), Optional.ofNullable(p.getEndTime()).orElse(new Date()));
                    if (minutes <= 0) {
                        minutes = 1;
                    }
                    return p.getPrice().multiply(new BigDecimal(String.valueOf(minutes)));
                }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_DOWN));

        //教练费用
        resVo.setOtherTotalAmount(Optional.ofNullable(order.getOrderTutorTimes()).orElse(Lists.newArrayList())
                .stream().map(p -> {
                    int minutes = DateUtils.deskTimeDiffMinutes(p.getStartTime(), Optional.ofNullable(p.getEndTime()).orElse(new Date()));
                    if (minutes <= 0) {
                        minutes = 1;
                    }
                    return p.getPrice().multiply(new BigDecimal(String.valueOf(minutes)));
                }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        //商品费用
        resVo.setOtherTotalAmount(resVo.getOtherTotalAmount().add(Optional.ofNullable(order.getOrderGoods()).orElse(Lists.newArrayList())
                .stream().map(p -> p.getPrice().multiply(new BigDecimal(String.valueOf(p.getNum()))))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO)).setScale(2, RoundingMode.HALF_DOWN));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo startCalcFee(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.WAIT.getValue()), "当前不是空闲状态，无法开台。");
        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌正在计费中");
        desk.setStatus(DeskStatus.BUSY.getValue());
        this.storeDeskMapper.updateById(desk);
        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(orderService.createOrder(deskId));

        BeanUtils.copyProperties(desk, resVo);
        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo pauseCalcFee(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.BUSY.getValue()), "当前不是计费状态，无法暂停。");
        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌没有在计费,无法暂停");

        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(orderService.pauseCalcFee(deskId));

        desk.setStatus(DeskStatus.PAUSE.getValue());
        this.storeDeskMapper.updateById(desk);
        BeanUtils.copyProperties(desk, resVo);
        calcOrderFee(resVo);

        return resVo;
    }

    @Override
    public DeskQueryResVo resumeDesk(Long deskId, Long storeId) {
        StoreDesk desk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(desk.getStatus(), DeskStatus.PAUSE.getValue()), "当前不是暂停状态，无法恢复计费。");
        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(deskId), "台桌正在计费,无法恢复计费.");

        DeskQueryResVo resVo = new DeskQueryResVo();
        resVo.setLastActiveOrder(orderService.resumeCalcFee(deskId));

        desk.setStatus(DeskStatus.BUSY.getValue());
        this.storeDeskMapper.updateById(desk);
        BeanUtils.copyProperties(desk, resVo);
        calcOrderFee(resVo);

        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeskQueryResVo swapToNewDesk(Long deskId, Long storeId, Long orderId, Long newDeskId) {
        StoreDesk currentDesk = queryEnableDesk(deskId, storeId);

        AssertUtil.isTrue(Objects.equals(currentDesk.getStatus(), DeskStatus.BUSY.getValue()), "当前台桌不是计费状态，无法换台。");
        AssertUtil.notNullOrEmpty(storeDeskMapper.deskInUse(deskId), "当前台桌正不是计费中,无法更换。");

        StoreDesk newDesk = queryEnableDesk(newDeskId, storeId);

        AssertUtil.isTrue(Objects.equals(newDesk.getStatus(), DeskStatus.WAIT.getValue())
                && Objects.isNull(newDesk.getCurrentOrderId()), "目标台桌不是空闲状态，无法更换,请更换到其他台桌。");

        AssertUtil.isNullOrEmpty(storeDeskMapper.deskInUse(newDeskId), "目标台桌不是空闲状态，无法更换,请更换到其他台桌。");

        Order order = orderService.swapToNewDesk(deskId, orderId, newDeskId);

        DeskQueryResVo resVo = new DeskQueryResVo();
        BeanUtils.copyProperties(storeDeskMapper.selectById(newDeskId), resVo);
        resVo.setLastActiveOrder(order);
        calcOrderFee(resVo);
        return resVo;
    }

    private StoreDesk queryEnableDesk(Long deskId, Long storeId) {
        StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query()
                .eq(StoreDesk::getDeskId, deskId).eq(StoreDesk::getStoreId, storeId));

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
