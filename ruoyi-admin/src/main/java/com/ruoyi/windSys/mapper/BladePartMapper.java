package com.ruoyi.windSys.mapper;

import java.util.List;
import com.ruoyi.windSys.domain.BladePart;

/**
 * 叶片管理Mapper接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface BladePartMapper 
{
    /**
     * 查询叶片管理
     * 
     * @param bpId 叶片管理主键
     * @return 叶片管理
     */
    public BladePart selectBladePartByBpId(Long bpId);

    /**
     * 查询叶片管理列表
     * 
     * @param bladePart 叶片管理
     * @return 叶片管理集合
     */
    public List<BladePart> selectBladePartList(BladePart bladePart);

    /**
     * 新增叶片管理
     * 
     * @param bladePart 叶片管理
     * @return 结果
     */
    public int insertBladePart(BladePart bladePart);

    /**
     * 修改叶片管理
     * 
     * @param bladePart 叶片管理
     * @return 结果
     */
    public int updateBladePart(BladePart bladePart);

    /**
     * 删除叶片管理
     * 
     * @param bpId 叶片管理主键
     * @return 结果
     */
    public int deleteBladePartByBpId(Long bpId);

    /**
     * 批量删除叶片管理
     * 
     * @param bpIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBladePartByBpIds(Long[] bpIds);
}
