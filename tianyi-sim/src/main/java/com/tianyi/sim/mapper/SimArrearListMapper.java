package com.tianyi.sim.mapper;

import java.util.List;
import com.tianyi.sim.domain.SimArrearList;

/**
 * 欠费停机预警Mapper接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface SimArrearListMapper 
{
    /**
     * 查询欠费停机预警
     * 
     * @param provId 欠费停机预警主键
     * @return 欠费停机预警
     */
    public SimArrearList selectSimArrearListByProvId(Long provId);

    /**
     * 查询欠费停机预警列表
     * 
     * @param simArrearList 欠费停机预警
     * @return 欠费停机预警集合
     */
    public List<SimArrearList> selectSimArrearListList(SimArrearList simArrearList);

    /**
     * 新增欠费停机预警
     * 
     * @param simArrearList 欠费停机预警
     * @return 结果
     */
    public int insertSimArrearList(SimArrearList simArrearList);

    /**
     * 修改欠费停机预警
     * 
     * @param simArrearList 欠费停机预警
     * @return 结果
     */
    public int updateSimArrearList(SimArrearList simArrearList);

    /**
     * 删除欠费停机预警
     * 
     * @param provId 欠费停机预警主键
     * @return 结果
     */
    public int deleteSimArrearListByProvId(Long provId);

    /**
     * 批量删除欠费停机预警
     * 
     * @param provIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSimArrearListByProvIds(Long[] provIds);
}
