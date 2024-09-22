package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderRelation;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单关系Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@Mapper
public interface OrderRelationMapper extends MyBaseMapper<OrderRelation>
{

    /**
     * 查询订单关系列表
     * 
     * @param orderRelation 订单关系
     * @return 订单关系集合
     */
    public List<OrderRelation> selectOrderRelationList(OrderRelation orderRelation);



    /**
     * 删除订单关系
     * 
     * @param orderRelationId 订单关系主键
     * @return 结果
     */
    public int deleteOrderRelationByOrderRelationId(Long orderRelationId);

    /**
     * 批量删除订单关系
     * 
     * @param orderRelationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderRelationByOrderRelationIds(Long[] orderRelationIds);
}
