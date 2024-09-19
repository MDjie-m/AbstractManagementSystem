package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.LevelDiscountPermission;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 等级折扣范围Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-20
 */
@Mapper
public interface LevelDiscountPermissionMapper extends MyBaseMapper<LevelDiscountPermission>
{
    /**
     * 查询等级折扣范围
     * 
     * @param levelDiscountPermissionId 等级折扣范围主键
     * @return 等级折扣范围
     */
    public LevelDiscountPermission selectLevelDiscountPermissionByLevelDiscountPermissionId(Long levelDiscountPermissionId);

    /**
     * 查询等级折扣范围列表
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 等级折扣范围集合
     */
    public List<LevelDiscountPermission> selectLevelDiscountPermissionList(LevelDiscountPermission levelDiscountPermission);

    /**
     * 新增等级折扣范围
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 结果
     */
    public int insertLevelDiscountPermission(LevelDiscountPermission levelDiscountPermission);

    /**
     * 修改等级折扣范围
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 结果
     */
    public int updateLevelDiscountPermission(LevelDiscountPermission levelDiscountPermission);

    /**
     * 删除等级折扣范围
     * 
     * @param levelDiscountPermissionId 等级折扣范围主键
     * @return 结果
     */
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionId(Long levelDiscountPermissionId);

    /**
     * 批量删除等级折扣范围
     * 
     * @param levelDiscountPermissionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionIds(Long[] levelDiscountPermissionIds);
}
