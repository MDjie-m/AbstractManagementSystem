package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskType;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 台桌类型Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@Mapper
public interface DeskTypeMapper extends MyBaseMapper<DeskType>
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
     * 删除台桌类型
     * 
     * @param deskTypeId 台桌类型主键
     * @return 结果
     */
    public int deleteDeskTypeByDeskTypeId(Long deskTypeId);

    /**
     * 批量删除台桌类型
     * 
     * @param deskTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskTypeByDeskTypeIds(Long[] deskTypeIds);
}
