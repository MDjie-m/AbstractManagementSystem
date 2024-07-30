package com.renxin.course.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.renxin.common.utils.DateUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.domain.dto.OrderQueryDTO;
import com.renxin.course.mapper.CourOrderMapper;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.vo.CourseOrderVO;
import com.renxin.course.vo.OrderQueryVO;
import com.renxin.course.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程订单Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-17
 */
@Service
public class CourOrderServiceImpl implements ICourOrderService 
{
    @Autowired
    private CourOrderMapper courOrderMapper;

    @Autowired
    private ICourOrderService courOrderService;

    /**
     * 查询课程订单
     * 
     * @param id 课程订单主键
     * @return 课程订单
     */
    @Override
    public CourOrder selectCourOrderById(Integer id)
    {
        return courOrderMapper.selectCourOrderById(id);
    }

    /**
     * 查询课程订单
     *
     * @param orderId 课程订单编号
     * @return 课程订单
     */
    @Override
    public CourOrder selectCourOrderByOrderId(String orderId)
    {
        return courOrderMapper.selectCourOrderByOrderId(orderId);
    }

    /**
     * 查询课程订单列表
     * 
     * @param courOrder 课程订单
     * @return 课程订单
     */
    @Override
    public List<CourOrder> selectCourOrderList(CourOrder courOrder)
    {
        return courOrderMapper.selectCourOrderList(courOrder);
    }

    @Override
    public List<CourOrder> getCancelList(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -num);
        Date time = calendar.getTime();
        return courOrderMapper.getOrderByCancel(CourConstant.COUR_ORDER_STATUE_CREATED, time);
    }

    /**
     * 新增课程订单
     * 
     * @param courOrder 课程订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCourOrder(CourOrder courOrder)
    {
        courOrder.setCreateTime(DateUtils.getNowDate());
        return courOrderMapper.insertCourOrder(courOrder);
    }

    /**
     * 修改课程订单
     * 
     * @param courOrder 课程订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCourOrder(CourOrder courOrder)
    {
        return courOrderMapper.updateCourOrder(courOrder);
    }

    /**
     * 批量删除课程订单
     * 
     * @param ids 需要删除的课程订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCourOrderByIds(Integer[] ids)
    {
        return courOrderMapper.deleteCourOrderByIds(ids);
    }

    /**
     * 删除课程订单信息
     * 
     * @param id 课程订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCourOrderById(Integer id)
    {
        return courOrderMapper.deleteCourOrderById(id);
    }

    /**
     * 查询用户是否购买课程
     *
     * @param userId 用户ID
     * @param courseId 课程订单主键
     * @return 结果
     */
    @Override
    public List<CourOrder> selectCourOrderByUser(Integer userId, Integer courseId) {

        CourOrder courOrder = CourOrder.builder()
                .courseId(courseId)
                .userId(userId)
                .status(CourConstant.COUR_ORDER_STATUE_FINISHED)
                .build();

        return courOrderMapper.selectCourOrderList(courOrder);
    }

    /**
     * 根据订单ID查询课程订单详情
     *
     * @param id 课程订单ID
     * @return 课程订单详情
     */
    @Override
    public OrderVO getOrderDetailById(Integer id) {
        return courOrderMapper.getOrderDetailById(id);
    }

    /**
     * 生成课程订单
     *
     * @param courOrder 课程订单
     * @return 生成的订单对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourOrder generateCourOrder(CourOrder courOrder) {
        // TODO 根据课程查询之前未支付的订单，并取消历史的未支付的订单
        CourOrder orderWithCourseId = new CourOrder();
        orderWithCourseId.setCourseId(courOrder.getCourseId());
        orderWithCourseId.setUserId(courOrder.getUserId());// 只取消本用户的课程
        List<CourOrder> historyCreatedOrderList = courOrderService.selectCourOrderList(orderWithCourseId)
                .stream()
                .filter(item -> item.getStatus() == CourConstant.COUR_ORDER_STATUE_CREATED)
                .collect(Collectors.toList());
        if (historyCreatedOrderList.size() > 0) {
            historyCreatedOrderList.forEach(item -> {
                item.setStatus(CourConstant.COUR_ORDER_STATUE_CANCELED);
                updateCourOrder(item);
            });
        }

        courOrder.setCreateTime(DateUtils.getNowDate()); // 下单时间
        int code = courOrderMapper.insertCourOrder(courOrder);
        if (code == 1) {
            return selectCourOrderByOrderId(courOrder.getOrderId());
        }
        return null;
    }

    /**
     * 根据条件查询课程订单列表
     */
    @Override
    public List<OrderQueryVO> queryOrderList(OrderQueryDTO orderQueryDTO) {
        return courOrderMapper.queryOrderList(orderQueryDTO);
    }

    @Override
    public List<CourseOrderVO> getOrderListByUserId(Integer userId,Integer status) {
        return courOrderMapper.getOrderListByUserId(userId,status);
    }
}
