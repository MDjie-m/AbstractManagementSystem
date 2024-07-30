package com.renxin.course.service;

import com.renxin.course.domain.CourOrder;
import com.renxin.course.domain.dto.OrderQueryDTO;
import com.renxin.course.vo.CourseOrderVO;
import com.renxin.course.vo.OrderQueryVO;
import com.renxin.course.vo.OrderVO;

import java.util.List;

/**
 * 课程订单Service接口
 * 
 * @author renxin
 * @date 2023-03-17
 */
public interface ICourOrderService 
{
    /**
     * 查询课程订单
     * 
     * @param id 课程订单主键
     * @return 课程订单
     */
    public CourOrder selectCourOrderById(Integer id);

    /**
     * 查询课程订单
     *
     * @param orderId 课程订单编号
     * @return 课程订单
     */
    public CourOrder selectCourOrderByOrderId(String orderId);

    /**
     * 查询课程订单列表
     * 
     * @param courOrder 课程订单
     * @return 课程订单集合
     */
    public List<CourOrder> selectCourOrderList(CourOrder courOrder);

    public List<CourOrder> getCancelList(int num);

    /**
     * 新增课程订单
     * 
     * @param courOrder 课程订单
     * @return 结果
     */
    public int insertCourOrder(CourOrder courOrder);

    /**
     * 修改课程订单
     * 
     * @param courOrder 课程订单
     * @return 结果
     */
    public int updateCourOrder(CourOrder courOrder);

    /**
     * 批量删除课程订单
     * 
     * @param ids 需要删除的课程订单主键集合
     * @return 结果
     */
    public int deleteCourOrderByIds(Integer[] ids);

    /**
     * 删除课程订单信息
     * 
     * @param id 课程订单主键
     * @return 结果
     */
    public int deleteCourOrderById(Integer id);

    /**
     * c查询用户是否购买该课程
     *
     * @param userId 用户ID
     * @param courseId 课程订单主键
     * @return 结果
     */
    public List<CourOrder> selectCourOrderByUser(Integer userId, Integer courseId);


    /**
     * 根据订单ID查询课程订单详情
     *
     * @param id 课程订单ID
     * @return 课程订单详情
     */
    OrderVO getOrderDetailById(Integer id);


    /**
     * 生成课程订单
     *
     * @param courOrder 课程订单
     * @return 生成的订单对象
     */
    public CourOrder generateCourOrder(CourOrder courOrder);

    /**
     * 根据条件查询课程订单列表
     */
    List<OrderQueryVO> queryOrderList(OrderQueryDTO orderQueryDTO);

    List<CourseOrderVO> getOrderListByUserId(Integer userId,Integer status);
}
