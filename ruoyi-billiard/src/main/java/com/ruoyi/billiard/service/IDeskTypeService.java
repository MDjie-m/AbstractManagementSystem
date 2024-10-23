package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.DeskType;

/**
 * 台桌类型Service接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public interface IDeskTypeService  extends IService<DeskType>
{
    /**
     * 查询台桌类型
     * 
     * @param deskTypeId 台桌类型主键
     * @return 台桌类型
     */
    public DeskType selectDeskTypeByDeskTypeId(Long deskTypeId);

    /**
     * 查询台桌类型列表
     * 
     * @param deskType 台桌类型
     * @return 台桌类型集合
     */
    public List<DeskType> selectDeskTypeList(DeskType deskType);

    /**
     * 新增台桌类型
     * 
     * @param deskType 台桌类型
     * @return 结果
     */
    public int insertDeskType(DeskType deskType);

    /**
     * 修改台桌类型
     * 
     * @param deskType 台桌类型
     * @return 结果
     */
    public int updateDeskType(DeskType deskType);

    /**
     * 批量删除台桌类型
     * 
     * @param deskTypeIds 需要删除的台桌类型主键集合
     * @return 结果
     */
    public int deleteDeskTypeByDeskTypeIds(Long[] deskTypeIds);

    /**
     * 删除台桌类型信息
     * 
     * @param deskTypeId 台桌类型主键
     * @return 结果
     */
    public int deleteDeskTypeByDeskTypeId(Long deskTypeId);
}
