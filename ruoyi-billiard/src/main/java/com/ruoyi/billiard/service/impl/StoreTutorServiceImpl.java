package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.miniappdomain.TutorResVo;
import com.ruoyi.billiard.enums.CalcTimeStatus;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.TutorLevel;
import com.ruoyi.billiard.enums.TutorWorkStatus;
import com.ruoyi.billiard.mapper.OrderTutorTimeMapper;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.mapper.StoreUserMapper;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 门店助教Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-06
 */
@Service
public class StoreTutorServiceImpl implements IStoreTutorService {
    @Autowired
    private StoreTutorMapper storeTutorMapper;

    @Autowired
    private ITutorPunchInService tutorPunchInService;

    @Autowired
    private ITutorWorkPlanService tutorWorkPlanService;

    @Resource
    private StoreUserMapper storeUserMapper;

    @Resource
    private ISysUserService sysUserService;
    @Resource
    private OrderTutorTimeMapper orderTutorTimeMapper;

    @Resource
    private StoreDeskMapper storeDeskMapper;

    @Resource
    private ITutorBookingService tutorBookingService;

    @Resource
    private IOrderService orderService;

    /**
     * 查询门店助教
     *
     * @param storeTutorId 门店助教主键
     * @return 门店助教
     */
    @Override
    public StoreTutor selectStoreTutorByStoreTutorId(Long storeTutorId) {
        StoreTutor user = storeTutorMapper.selectStoreTutorByStoreTutorId(storeTutorId);
        if (Objects.nonNull(user)) {
            user.setRoleIds(storeUserMapper.selectRoleIds(user.getLoginUserId()));
        }
        return user;
    }

    /**
     * 查询门店助教列表
     *
     * @param storeTutor 门店助教
     * @return 门店助教
     */
    @Override
    public List<StoreTutor> selectStoreTutorList(StoreTutor storeTutor) {

        List<StoreTutor> users = storeTutorMapper.selectStoreTutorList(storeTutor);
        if (CollectionUtils.isEmpty(users)) {
            return users;
        }
        List<KeyValueVo<Long, Long>> roleIds = storeUserMapper.selectRoleIdsByUserIds(users.stream()
                .map(StoreTutor::getLoginUserId).collect(Collectors.toList()));
        Map<Long, List<Long>> roleIdMap = ArrayUtil.groupByValue(roleIds, KeyValueVo::getKey, KeyValueVo::getValue);
        users.forEach(u -> {
            u.setRoleIds(roleIdMap.getOrDefault(u.getLoginUserId(), Lists.newArrayList()));
        });

        if (Objects.nonNull(storeTutor.getBookingStart()) && Objects.nonNull(storeTutor.getBookingEnd())) {
            Map<Long, Long> map = ArrayUtil.toMap(tutorBookingService
                            .selectBookingCount(users.stream()
                                            .map(StoreTutor::getStoreTutorId).collect(Collectors.toList()),
                                    storeTutor.getBookingStart(), storeTutor.getBookingEnd()),
                    KeyValueVo::getKey, KeyValueVo::getValue);
            users.forEach(p -> {
                p.setBookingCount(map.getOrDefault(p.getStoreTutorId(), 0L));
            });
        }
        if (Objects.equals(Boolean.TRUE, storeTutor.getQueryLastBooking()) && CollectionUtils.isNotEmpty(users)) {
            Map<Long, TutorBooking> map = ArrayUtil.toMap(tutorBookingService.queryLastBooking(users.stream().map(StoreTutor::getStoreTutorId).collect(Collectors.toList())),
                    TutorBooking::getTutorId, p -> p);
            users.forEach(p -> {
                p.setBooking(map.get(p.getStoreTutorId()));
            });
        }
        if (Objects.nonNull(storeTutor.getScheduleDay()) && CollectionUtils.isNotEmpty(users)) {
            Map<Long, TutorPunchIn> map = ArrayUtil.toMap(tutorPunchInService.queryCurrentPunchIn(storeTutor.getStoreId(),
                            storeTutor.getScheduleDay()),
                    TutorPunchIn::getTutorId, p -> p);
            users.forEach(p -> {
                p.setPunchIn(map.get(p.getStoreTutorId()));
            });

            Map<Long, Integer> planCount = ArrayUtil.toMap(tutorWorkPlanService.selectTutorWorkPlanList(
                            TutorWorkPlan.builder().storeId(storeTutor.getStoreId())
                                    .day(storeTutor.getScheduleDay()).build()),
                    TutorWorkPlan::getTutorId, TutorWorkPlan::getCount);
            users.forEach(p -> {
                p.setPlanCount(planCount.getOrDefault(p.getStoreTutorId(), 0));
            });
        }
        return users;
    }

    /**
     * 新增门店助教
     *
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int insertStoreTutor(StoreTutor storeTutor) {
        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getMobile, storeTutor.getMobile())
                .eq(StoreTutor::getStoreId, storeTutor.getStoreId())), "手机号已被其他用户使用");

        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getTutorNum, storeTutor.getTutorNum())
                .eq(StoreTutor::getStoreId, storeTutor.getStoreId())), "编号重复");
        //手机号重复加字母
        List<String> nameSubList = Lists.newArrayList();
        nameSubList.add("");
        nameSubList.addAll(Arrays.asList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split("")));

        String nameSub = "";
        for (String s : nameSubList) {
            nameSub = s;
            SysUser sysUser = sysUserService.selectUserByUserName(storeTutor.getMobile() + nameSub);
            if (Objects.isNull(sysUser)) {
                break;
            }
        }

        SysUser sysUser = new SysUser();
        sysUser.setAvatar(storeTutor.getUserImg());
        sysUser.setDeptId(100L);
        sysUser.setUserName(storeTutor.getMobile() + nameSub);
        sysUser.setPhonenumber(storeTutor.getMobile());
        sysUser.setNickName(storeTutor.getRealName());
        sysUser.setPassword(SecurityUtils.encryptPassword(storeTutor.getMobile()));
        sysUser.setRoleIds(storeTutor.getRoleIds());
        sysUser.setSex(storeTutor.getSex());
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUserService.insertUser(sysUser);

        storeTutor.setStoreTutorId(IdUtils.singleNextId());
        storeTutor.setLoginUserId(sysUser.getUserId());
        SecurityUtils.fillCreateUser(storeTutor);
        return storeTutorMapper.insert(storeTutor);
    }

    /**
     * 修改门店助教
     *
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int updateStoreTutor(StoreTutor storeTutor) {
        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getMobile, storeTutor.getMobile())
                        .eq(StoreTutor::getStoreId, storeTutor.getStoreId()).notIn(StoreTutor::getStoreTutorId, storeTutor.getStoreTutorId())),
                "手机号已被其他用户使用");

        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getTutorNum, storeTutor.getTutorNum())
                        .eq(StoreTutor::getStoreId, storeTutor.getStoreId()).notIn(StoreTutor::getStoreTutorId, storeTutor.getStoreTutorId())),
                "编号重复");

        SysUser user = sysUserService.selectUserById(storeTutor.getLoginUserId());
        user.setSex(storeTutor.getSex());
        user.setPhonenumber(storeTutor.getMobile());
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setRoleIds(storeTutor.getRoleIds());

        sysUserService.updateUser(user);

        SecurityUtils.fillUpdateUser(storeTutor);
        storeTutor.setWorkStatus(null);
        storeTutor.setCurrentOrderId(null);
        return storeTutorMapper.updateById(storeTutor);
    }

    /**
     * 批量删除门店助教
     *
     * @param storeTutorIds 需要删除的门店助教主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStoreTutorByStoreTutorIds(Long[] storeTutorIds) {

        for (Long storeTutorId : storeTutorIds) {

            deleteStoreTutorByStoreTutorId(storeTutorId);
        }
        return storeTutorIds.length;
    }

    /**
     * 删除门店助教信息
     *
     * @param storeTutorId 门店助教主键
     * @return 结果
     */
    @Override
    public int deleteStoreTutorByStoreTutorId(Long storeTutorId) {
        AssertUtil.isTrue(!orderTutorTimeMapper.exists(OrderTutorTime::getTutorId, storeTutorId), "教练已被使用,无法删除.");
        return storeTutorMapper.deleteById(storeTutorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void swapToNewDesk(Long tutorId, Long storeId, Long newDeskId) {
        OrderTutorTime tutorTime = orderTutorTimeMapper.selectOne(orderTutorTimeMapper.query()
                .eq(OrderTutorTime::getTutorId, tutorId).in(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue())
                .last(" limit 1"));
        AssertUtil.notNullOrEmpty(tutorTime, "教练不是计费状态");


        StoreTutor tutor = storeTutorMapper.selectById(tutorId);
        AssertUtil.notNullOrEmpty(tutor, "教练不存在");
        AssertUtil.equal(tutor.getStoreId(), storeId, "非法参数");


        StoreDesk newDesk = queryEnableDesk(newDeskId, storeId);
        AssertUtil.isTrue(Objects.equals(newDesk.getStatus(), DeskStatus.BUSY)
                && Objects.nonNull(newDesk.getCurrentOrderId()), "目标台桌不是计费状态，无法更换,请更换到其他台桌。");
        AssertUtil.isTrue(!Objects.equals(newDesk.getDeskId(), tutorTime.getDeskId()), "不能更换到同一桌");


        Date endTime = DateUtils.removeSeconds(new Date());
        tutorTime.setEndTime(endTime);
        tutorTime.setStatus(CalcTimeStatus.STOP.getValue());
        BaseFee.calcFees(Collections.singletonList(tutorTime));
        SecurityUtils.fillUpdateUser(tutorTime);
        orderTutorTimeMapper.updateById(tutorTime);


        BigDecimal price = storeTutorMapper.queryPrice(tutorId);
        OrderTutorTime p = new OrderTutorTime();
        AssertUtil.notNullOrEmpty(price, "未配置教练价格，请联系管理员添加");
        p.setOrderTutorTimeId(IdUtils.singleNextId());
        p.setOrderId(newDesk.getCurrentOrderId());
        p.setStartTime(endTime);
        p.setPrice(price);
        p.setTutorId(tutorId);
        p.setType(tutorTime.getType());
        p.setDeskId(newDeskId);
        p.setStatus(CalcTimeStatus.BUSY.getValue());
        SecurityUtils.fillCreateUser(p);
        orderTutorTimeMapper.insert(p);

        tutor.setCurrentOrderId(newDesk.getCurrentOrderId());
        SecurityUtils.fillUpdateUser(tutor, tutorTime);
        storeTutorMapper.updateById(tutor);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tutorPause(Long tutorId, Long storeId) {

        StoreTutor tutor = storeTutorMapper.selectById(tutorId);
        AssertUtil.notNullOrEmpty(tutor, "教练不存在");
        AssertUtil.equal(tutor.getStoreId(), storeId, "非法参数");
        AssertUtil.notNullOrEmpty(tutor.getCurrentOrderId(), "教练不是计费状态");

        OrderTutorTime tutorTime = orderTutorTimeMapper.selectOne(orderTutorTimeMapper.query().eq(OrderTutorTime::getOrderId, tutor.getCurrentOrderId())
                .eq(OrderTutorTime::getTutorId, tutorId).in(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue())
                .last(" limit 1"));
        AssertUtil.notNullOrEmpty(tutorTime, "教练不是计费状态");


        Date endTime = DateUtils.removeSeconds(new Date());
        tutorTime.setEndTime(endTime);
        tutorTime.setStatus(CalcTimeStatus.PAUSE.getValue());
        BaseFee.calcFees(Collections.singletonList(tutorTime));
        SecurityUtils.fillUpdateUser(tutorTime);
        orderTutorTimeMapper.updateById(tutorTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tutorResume(Long tutorId, Long storeId) {

        StoreTutor tutor = storeTutorMapper.selectById(tutorId);
        AssertUtil.notNullOrEmpty(tutor, "教练不存在");
        AssertUtil.equal(tutor.getStoreId(), storeId, "非法参数");
        AssertUtil.notNullOrEmpty(tutor.getCurrentOrderId(), "教练不是计费状态");

        OrderTutorTime tutorTime = orderTutorTimeMapper.selectOne(orderTutorTimeMapper.query().eq(OrderTutorTime::getOrderId, tutor.getCurrentOrderId())
                .eq(OrderTutorTime::getTutorId, tutorId).in(OrderTutorTime::getStatus, CalcTimeStatus.PAUSE.getValue())
                .orderByDesc(OrderTutorTime::getOrderTutorTimeId)
                .last(" limit 1"));
        AssertUtil.notNullOrEmpty(tutorTime, "教练不是暂停状态");
        tutorTime.setStatus(CalcTimeStatus.STOP.getValue());
        SecurityUtils.fillCreateUser(tutorTime);
        orderTutorTimeMapper.updateById(tutorTime);

        Date startTime = DateUtils.removeSeconds(new Date());
        BigDecimal price = storeTutorMapper.queryPrice(tutorId);
        OrderTutorTime p = new OrderTutorTime();
        AssertUtil.notNullOrEmpty(price, "未配置教练价格，请联系管理员添加");
        p.setOrderTutorTimeId(IdUtils.singleNextId());
        p.setOrderId(tutorTime.getOrderId());
        p.setDeskId(tutorTime.getDeskId());
        p.setTutorId(tutorId);
        p.setStartTime(startTime);
        p.setType(tutorTime.getType());
        p.setPrice(price);
        p.setStatus(CalcTimeStatus.BUSY.getValue());
        SecurityUtils.fillCreateUser(p);
        orderTutorTimeMapper.insert(p);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tutorStop(Long tutorId, Long storeId) {
        StoreTutor tutor = storeTutorMapper.selectById(tutorId);
        AssertUtil.notNullOrEmpty(tutor, "教练不存在");
        AssertUtil.equal(tutor.getStoreId(), storeId, "非法参数");
        AssertUtil.notNullOrEmpty(tutor.getCurrentOrderId(), "教练不是计费状态");

        OrderTutorTime tutorTime = orderTutorTimeMapper.selectOne(orderTutorTimeMapper.query().eq(OrderTutorTime::getOrderId, tutor.getCurrentOrderId())
                .eq(OrderTutorTime::getTutorId, tutorId).in(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue())
                .last(" limit 1"));
        AssertUtil.notNullOrEmpty(tutorTime, "教练不是计费状态");


        Date endTime = DateUtils.removeSeconds(new Date());
        tutorTime.setEndTime(endTime);
        tutorTime.setStatus(CalcTimeStatus.STOP.getValue());
        BaseFee.calcFees(Collections.singletonList(tutorTime));
        SecurityUtils.fillUpdateUser(tutorTime);
        orderTutorTimeMapper.updateById(tutorTime);

        tutor.setWorkStatus(TutorWorkStatus.WAIT );
        tutor.setCurrentOrderId(null);
        SecurityUtils.fillUpdateUser(tutor);
        storeTutorMapper.updateAllWithId(tutor);
    }

    @Override
    public List<TutorResVo> queryByStoreId(Long storeId) {
        Map<Integer, List<StoreTutor>> storeTutors = ArrayUtil.groupBy(selectStoreTutorList(StoreTutor.builder().storeId(storeId).build()), StoreTutor::getLevel);

        return storeTutors.keySet().stream().map(p -> {
            TutorResVo resVo = new TutorResVo();
            resVo.setLevel(EnumUtil.getIEnum(TutorLevel.class, p));
            resVo.setTutors(storeTutors.get(p));
            return resVo;
        }).collect(Collectors.toList());

    }

    private StoreDesk queryEnableDesk(Long deskId, Long storeId) {
        StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query()
                .eq(StoreDesk::getDeskId, deskId).eq(StoreDesk::getStoreId, storeId));

        AssertUtil.notNullOrEmpty(desk, "非法参数");
        AssertUtil.isTrue(Objects.equals(desk.getEnable(), Boolean.TRUE), "台桌未启用");
        return desk;
    }

}
