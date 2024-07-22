package com.ruoyi.windSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.windSys.mapper.BladePartMapper;
import com.ruoyi.windSys.domain.BladePart;
import com.ruoyi.windSys.service.IBladePartService;

/**
 * 叶片管理Service业务层处理
 * 
 * @author GG
 * @date 2024-07-13
 */
@Service
public class BladePartServiceImpl implements IBladePartService 
{
    @Autowired
    private BladePartMapper bladePartMapper;

    /**
     * 查询叶片管理
     * 
     * @param bpId 叶片管理主键
     * @return 叶片管理
     */
    @Override
    public BladePart selectBladePartByBpId(Long bpId)
    {
        return bladePartMapper.selectBladePartByBpId(bpId);
    }

    @Override
    public List<BladePart> selectBladePartByBcId(Long bcId) {return bladePartMapper.selectBladePartByBcId(bcId);}

    /**
     * 查询叶片管理列表
     * 
     * @param bladePart 叶片管理
     * @return 叶片管理
     */
    @Override
    public List<BladePart> selectBladePartList(BladePart bladePart)
    {
        return bladePartMapper.selectBladePartList(bladePart);
    }

    /**
     * 新增叶片管理
     * 
     * @param bladePart 叶片管理
     * @return 结果
     */
    @Override
    public int insertBladePart(BladePart bladePart)
    {
        return bladePartMapper.insertBladePart(bladePart);
    }

    /**
     * 修改叶片管理
     * 
     * @param bladePart 叶片管理
     * @return 结果
     */
    @Override
    public int updateBladePart(BladePart bladePart)
    {
        return bladePartMapper.updateBladePart(bladePart);
    }

    /**
     * 批量删除叶片管理
     * 
     * @param bpIds 需要删除的叶片管理主键
     * @return 结果
     */
    @Override
    public int deleteBladePartByBpIds(Long[] bpIds)
    {
        return bladePartMapper.deleteBladePartByBpIds(bpIds);
    }

    /**
     * 删除叶片管理信息
     * 
     * @param bpId 叶片管理主键
     * @return 结果
     */
    @Override
    public int deleteBladePartByBpId(Long bpId)
    {
        return bladePartMapper.deleteBladePartByBpId(bpId);
    }
}
