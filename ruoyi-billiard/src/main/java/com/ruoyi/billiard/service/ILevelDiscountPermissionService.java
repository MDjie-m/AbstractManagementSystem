package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.LevelDiscountPermission;

/**
 * 等级折扣范围Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-20
 */
public interface ILevelDiscountPermissionService 
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
     * 批量删除等级折扣范围
     * 
     * @param levelDiscountPermissionIds 需要删除的等级折扣范围主键集合
     * @return 结果
     */
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionIds(Long[] levelDiscountPermissionIds);

    /**
     * 删除等级折扣范围信息
     * 
     * @param levelDiscountPermissionId 等级折扣范围主键
     * @return 结果
     */
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionId(Long levelDiscountPermissionId);

    /**
     * 批量新增等级折扣范围
     *
     * @param levelDiscountPermissions 等级折扣范围集合
     * @return 结果
     */
    int batchInsertLevelDiscountPermission(List<LevelDiscountPermission> levelDiscountPermissions);

    /**
     * 根据会员等级id查询等级折扣范围
     * @param memberLevelId 会员等级Id
     * @return 折扣范围列表
     */
    List<LevelDiscountPermission> selectLevelDiscountPermissionListByMemberLevelId(Long memberLevelId);

    /**
     * 根据会员等级id删除等级折扣范围
     * @param memberLevelId 会员等级Id
     */
    void deleteLevelDiscountPermissionByMemberLevelId(Long memberLevelId);
}
