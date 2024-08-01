package com.renxin.course.mapper;

import com.renxin.course.domain.CourOrder;
import com.renxin.course.domain.dto.OrderQueryDTO;
import com.renxin.course.vo.CourseOrderVO;
import com.renxin.course.vo.OrderQueryVO;
import com.renxin.course.vo.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 课程订单Mapper接口
 * 
 * @author renxin
 * @date 2023-03-17
 */
public interface CourOrderMapper 
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

    List<CourOrder> getOrderByCancel(@Param("status") Integer status, @Param("time") Date time);

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
     * 删除课程订单
     * 
     * @param id 课程订单主键
     * @return 结果
     */
    public int deleteCourOrderById(Integer id);

    /**
     * 批量删除课程订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourOrderByIds(Integer[] ids);

    /**
     * 根据订单ID查询课程订单详情
     *
     * @param id 课程订单ID
     * @return 课程订单详情
     */
    public OrderVO getOrderDetailById(Integer id);

    /**
     * 根据条件查询课程订单列表
     */
    List<OrderQueryVO> queryOrderList(OrderQueryDTO orderQueryDTO);



    List<CourseOrderVO> getOrderListByUserId(@Param("userId") Integer userId ,  @Param("status") Integer status);
}
