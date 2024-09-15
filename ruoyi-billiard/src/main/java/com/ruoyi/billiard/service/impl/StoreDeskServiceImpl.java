package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.domain.vo.DeskQueryResVo;
import com.ruoyi.billiard.domain.vo.LineUpVo;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.enums.TutorWorkStatus;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 球桌Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
public class StoreDeskServiceImpl implements IStoreDeskService {
    @Autowired
    private StoreDeskMapper storeDeskMapper;

    @Autowired
    private IOrderService orderService;
    @Autowired
    private StoreTutorMapper storeTutorMapper;
    @Autowired
    private IDeskDeviceRelationService deskDeviceRelationService;

    final static String LINE_UP_KEY = "line_up:{}";
    @Resource
    private RedisCache redisCacheService;

    /**
     * 查询球桌
     *
     * @param deskId 球桌主键
     * @return 球桌
     */
    @Override
    public StoreDesk selectStoreDeskByDeskId(Long deskId) {
        return storeDeskMapper.selectById(deskId);
    }

    /**
     * 查询球桌列表
     *
     * @param storeDesk 球桌
     * @return 球桌
     */
    @Override
    public List<StoreDesk> selectStoreDeskList(StoreDesk storeDesk) {
        return storeDeskMapper.selectStoreDeskList(storeDesk);
    }

    /**
     * 新增球桌
     *
     * @param storeDesk 球桌
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
                .eq(StoreDesk::getDeskNum, storeDesk.getDeskNum())), "球桌编号重复");
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));


        return storeDeskMapper.insertStoreDesk(storeDesk);
    }

    private void checkDevice(Long deviceId, Long deskId, String msg) {
        if (Objects.isNull(deviceId)) {
            return;
        }
        AssertUtil.isTrue(Objects.isNull(storeDeskMapper.checkDeviceBind(deviceId, deskId)), msg);
    }

    /**
     * 修改球桌
     *
     * @param storeDesk 球桌
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStoreDesk(StoreDesk storeDesk) {
        AssertUtil.isTrue(!storeDeskMapper.exists(storeDeskMapper.query().eq(StoreDesk::getStoreId, storeDesk.getStoreId())
                        .eq(StoreDesk::getDeskNum, storeDesk.getDeskNum()).notIn(StoreDesk::getDeskId, storeDesk.getDeskId())),
                "球桌编号重复");
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));
        storeDesk.setStatus(null);
        checkDevice(storeDesk.getCameraDeviceId(), storeDesk.getDeskId(), "摄像头已绑定到其他桌");
        checkDevice(storeDesk.getLightDeviceId(), storeDesk.getDeskId(), "摄像头已绑定到其他桌");
        SecurityUtils.fillUpdateUser(storeDesk);

        return storeDeskMapper.updateStoreDesk(storeDesk);
    }

    /**
     * 批量删除球桌
     *
     * @param deskIds 需要删除的球桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskIds(Long[] deskIds) {
        return storeDeskMapper.deleteStoreDeskByDeskIds(deskIds);
    }

    /**
     * 删除球桌信息
     *
     * @param deskId 球桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskId(Long deskId) {
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
        StoreDesk desk = storeDeskMapper.selectById(deskId);
        AssertUtil.notNullOrEmpty(desk, "球桌不存在");
        AssertUtil.isTrue(Objects.equals(desk.getEnable(), Boolean.TRUE), "球桌未启用");
        DeskQueryResVo resVo = new DeskQueryResVo();
        BeanUtils.copyProperties(desk, resVo);

        Order order = orderService.selectLastActiveOrder(deskId);
        if (Objects.isNull(order)) {
            return resVo;
        }
        resVo.setLastActiveOrder(order);
        //台桌费用
        resVo.setDeskTotalTimeAmount(Optional.ofNullable(order.getOrderDeskTimes()).orElse(Lists.newArrayList())
                .stream().map(p -> {
                    int minutes = DateUtils.deskTimeDiffMinutes(p.getStartTime(), Optional.ofNullable(p.getEndTime()).orElse(new Date()));
                    if (minutes <= 0) {
                        minutes = 1;
                    }
                    return p.getPrice().multiply(new BigDecimal(String.valueOf(minutes)));
                }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

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
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));
        return resVo;

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
