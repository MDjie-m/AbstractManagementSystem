package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskPlace;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 台桌区域Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@Mapper
public interface DeskPlaceMapper extends MyBaseMapper<DeskPlace>
{
    /**
     * 查询台桌区域
     * 
     * @param deskPlaceId 台桌区域主键
     * @return 台桌区域
     */
    public DeskPlace selectDeskPlaceByDeskPlaceId(Long deskPlaceId);

    /**
     * 查询台桌区域列表
     * 
     * @param deskPlace 台桌区域
     * @return 台桌区域集合
     */
    public List<DeskPlace> selectDeskPlaceList(DeskPlace deskPlace);


    /**
     * 删除台桌区域
     * 
     * @param deskPlaceId 台桌区域主键
     * @return 结果
     */
    public int deleteDeskPlaceByDeskPlaceId(Long deskPlaceId);

    /**
     * 批量删除台桌区域
     * 
     * @param deskPlaceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskPlaceByDeskPlaceIds(Long[] deskPlaceIds);
}
