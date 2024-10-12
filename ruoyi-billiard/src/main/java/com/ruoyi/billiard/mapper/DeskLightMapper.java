package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskLight;
import com.ruoyi.billiard.domain.vo.LightStatusResVo;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 灯光Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-12
 */
@Mapper
public interface DeskLightMapper extends MyBaseMapper<DeskLight>
{
    /**
     * 查询灯光
     * 
     * @param lightId 灯光主键
     * @return 灯光
     */
    public DeskLight selectDeskLightByLightId(Long lightId);

    /**
     * 查询灯光列表
     * 
     * @param deskLight 灯光
     * @return 灯光集合
     */
    public List<DeskLight> selectDeskLightList(DeskLight deskLight);


    /**
     * 删除灯光
     * 
     * @param lightId 灯光主键
     * @return 结果
     */
    public int deleteDeskLightByLightId(Long lightId);

    /**
     * 批量删除灯光
     * 
     * @param lightIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskLightByLightIds(Long[] lightIds);

    LightStatusResVo queryLightStatus(@Param("storeId") Long storeId);

}
