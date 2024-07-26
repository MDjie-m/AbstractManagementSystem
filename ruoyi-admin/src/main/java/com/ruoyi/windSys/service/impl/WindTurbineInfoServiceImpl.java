package com.ruoyi.windSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.windSys.domain.BladePart;
import com.ruoyi.windSys.mapper.WindTurbineInfoMapper;
import com.ruoyi.windSys.domain.WindTurbineInfo;
import com.ruoyi.windSys.service.IWindTurbineInfoService;

/**
 * 风机管理Service业务层处理
 * 
 * @author GG
 * @date 2024-07-13
 */
@Service
public class WindTurbineInfoServiceImpl implements IWindTurbineInfoService 
{
    @Autowired
    private WindTurbineInfoMapper windTurbineInfoMapper;

    /**
     * 查询风机管理
     * 
     * @param wId 风机管理主键
     * @return 风机管理
     */
    @Override
    public WindTurbineInfo selectWindTurbineInfoByWId(Long wId)
    {
        return windTurbineInfoMapper.selectWindTurbineInfoByWId(wId);
    }

    /**
     * 查询风机管理列表
     * 
     * @param windTurbineInfo 风机管理
     * @return 风机管理
     */
    @Override
    public List<WindTurbineInfo> selectWindTurbineInfoList(WindTurbineInfo windTurbineInfo)
    {
        return windTurbineInfoMapper.selectWindTurbineInfoList(windTurbineInfo);
    }

    /**
     * 新增风机管理
     * 
     * @param windTurbineInfo 风机管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWindTurbineInfo(WindTurbineInfo windTurbineInfo)
    {
        int rows = windTurbineInfoMapper.insertWindTurbineInfo(windTurbineInfo);
        insertBladePart(windTurbineInfo);
        return rows;
    }

    /**
     * 修改风机管理
     * 
     * @param windTurbineInfo 风机管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWindTurbineInfo(WindTurbineInfo windTurbineInfo)
    {
        windTurbineInfoMapper.deleteBladePartByTurbineCode(windTurbineInfo.getwId());
        insertBladePart(windTurbineInfo);
        return windTurbineInfoMapper.updateWindTurbineInfo(windTurbineInfo);
    }

    /**
     * 批量删除风机管理
     * 
     * @param wIds 需要删除的风机管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWindTurbineInfoByWIds(Long[] wIds)
    {
        windTurbineInfoMapper.deleteBladePartByTurbineCodes(wIds);
        return windTurbineInfoMapper.deleteWindTurbineInfoByWIds(wIds);
    }

    /**
     * 删除风机管理信息
     * 
     * @param wId 风机管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWindTurbineInfoByWId(Long wId)
    {
        windTurbineInfoMapper.deleteBladePartByTurbineCode(wId);
        return windTurbineInfoMapper.deleteWindTurbineInfoByWId(wId);
    }

    /**
     * 新增叶片管理信息
     * 
     * @param windTurbineInfo 风机管理对象
     */
    public void insertBladePart(WindTurbineInfo windTurbineInfo)
    {
        List<BladePart> bladePartList = windTurbineInfo.getBladePartList();
        Long wId = windTurbineInfo.getwId();
        if (StringUtils.isNotNull(bladePartList))
        {
            List<BladePart> list = new ArrayList<BladePart>();
            for (BladePart bladePart : bladePartList)
            {
                bladePart.setTurbineCode(wId);
                list.add(bladePart);
            }
            if (list.size() > 0)
            {
                windTurbineInfoMapper.batchBladePart(list);
            }
        }
    }
}
