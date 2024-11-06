package com.tianyi.sim.service;

import java.util.List;
import com.tianyi.sim.domain.PackExpList;

/**
 * 套餐到期预警Service接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface IPackExpListService 
{
    /**
     * 查询套餐到期预警
     * 
     * @param provId 套餐到期预警主键
     * @return 套餐到期预警
     */
    public PackExpList selectPackExpListByProvId(Long provId);

    /**
     * 查询套餐到期预警列表
     * 
     * @param packExpList 套餐到期预警
     * @return 套餐到期预警集合
     */
    public List<PackExpList> selectPackExpListList(PackExpList packExpList);

    /**
     * 新增套餐到期预警
     * 
     * @param packExpList 套餐到期预警
     * @return 结果
     */
    public int insertPackExpList(PackExpList packExpList);

    /**
     * 修改套餐到期预警
     * 
     * @param packExpList 套餐到期预警
     * @return 结果
     */
    public int updatePackExpList(PackExpList packExpList);

    /**
     * 批量删除套餐到期预警
     * 
     * @param provIds 需要删除的套餐到期预警主键集合
     * @return 结果
     */
    public int deletePackExpListByProvIds(Long[] provIds);

    /**
     * 删除套餐到期预警信息
     * 
     * @param provId 套餐到期预警主键
     * @return 结果
     */
    public int deletePackExpListByProvId(Long provId);
}
