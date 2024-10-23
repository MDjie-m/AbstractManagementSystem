package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.DeskPlace;

/**
 * 台桌区域Service接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public interface IDeskPlaceService  extends IService<DeskPlace>
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
     * 新增台桌区域
     * 
     * @param deskPlace 台桌区域
     * @return 结果
     */
    public int insertDeskPlace(DeskPlace deskPlace);

    /**
     * 修改台桌区域
     * 
     * @param deskPlace 台桌区域
     * @return 结果
     */
    public int updateDeskPlace(DeskPlace deskPlace);

    /**
     * 批量删除台桌区域
     * 
     * @param deskPlaceIds 需要删除的台桌区域主键集合
     * @return 结果
     */
    public int deleteDeskPlaceByDeskPlaceIds(Long[] deskPlaceIds);

    /**
     * 删除台桌区域信息
     * 
     * @param deskPlaceId 台桌区域主键
     * @return 结果
     */
    public int deleteDeskPlaceByDeskPlaceId(Long deskPlaceId);
}
