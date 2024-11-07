package com.tianyi.sim.mapper;

import java.util.List;
import com.tianyi.sim.domain.NonuseWarningList;

/**
 * 套餐长期不使用预警Mapper接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface NonuseWarningListMapper 
{
    /**
     * 查询套餐长期不使用预警
     * 
     * @param provId 套餐长期不使用预警主键
     * @return 套餐长期不使用预警
     */
    public NonuseWarningList selectNonuseWarningListByProvId(Long provId);

    /**
     * 查询套餐长期不使用预警列表
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 套餐长期不使用预警集合
     */
    public List<NonuseWarningList> selectNonuseWarningListList(NonuseWarningList nonuseWarningList);

    /**
     * 新增套餐长期不使用预警
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 结果
     */
    public int insertNonuseWarningList(NonuseWarningList nonuseWarningList);

    /**
     * 修改套餐长期不使用预警
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 结果
     */
    public int updateNonuseWarningList(NonuseWarningList nonuseWarningList);

    /**
     * 删除套餐长期不使用预警
     * 
     * @param provId 套餐长期不使用预警主键
     * @return 结果
     */
    public int deleteNonuseWarningListByProvId(Long provId);

    /**
     * 批量删除套餐长期不使用预警
     * 
     * @param provIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNonuseWarningListByProvIds(Long[] provIds);
}
