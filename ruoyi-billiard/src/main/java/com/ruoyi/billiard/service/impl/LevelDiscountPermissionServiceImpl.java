package com.ruoyi.billiard.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.LevelDiscountPermissionMapper;
import com.ruoyi.billiard.domain.LevelDiscountPermission;
import com.ruoyi.billiard.service.ILevelDiscountPermissionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 等级折扣范围Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-20
 */
@Service
public class LevelDiscountPermissionServiceImpl implements ILevelDiscountPermissionService 
{
    @Autowired
    private LevelDiscountPermissionMapper levelDiscountPermissionMapper;

    /**
     * 查询等级折扣范围
     * 
     * @param levelDiscountPermissionId 等级折扣范围主键
     * @return 等级折扣范围
     */
    @Override
    public LevelDiscountPermission selectLevelDiscountPermissionByLevelDiscountPermissionId(Long levelDiscountPermissionId)
    {
        return levelDiscountPermissionMapper.selectById(levelDiscountPermissionId);
    }

    /**
     * 查询等级折扣范围列表
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 等级折扣范围
     */
    @Override
    public List<LevelDiscountPermission> selectLevelDiscountPermissionList(LevelDiscountPermission levelDiscountPermission)
    {
        return levelDiscountPermissionMapper.selectLevelDiscountPermissionList(levelDiscountPermission);
    }

    /**
     * 新增等级折扣范围
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 结果
     */
    @Override
    public int insertLevelDiscountPermission(LevelDiscountPermission levelDiscountPermission)
    {
        SecurityUtils.fillCreateUser(levelDiscountPermission);
        levelDiscountPermission.setLevelDiscountPermissionId(IdUtils.singleNextId());
        return levelDiscountPermissionMapper.insertLevelDiscountPermission(levelDiscountPermission);
    }

    /**
     * 修改等级折扣范围
     * 
     * @param levelDiscountPermission 等级折扣范围
     * @return 结果
     */
    @Override
    public int updateLevelDiscountPermission(LevelDiscountPermission levelDiscountPermission)
    {
        SecurityUtils.fillUpdateUser(levelDiscountPermission);

        return levelDiscountPermissionMapper.updateLevelDiscountPermission(levelDiscountPermission);
    }

    /**
     * 批量删除等级折扣范围
     * 
     * @param levelDiscountPermissionIds 需要删除的等级折扣范围主键
     * @return 结果
     */
    @Override
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionIds(Long[] levelDiscountPermissionIds)
    {
        return levelDiscountPermissionMapper.deleteLevelDiscountPermissionByLevelDiscountPermissionIds(levelDiscountPermissionIds);
    }

    /**
     * 删除等级折扣范围信息
     * 
     * @param levelDiscountPermissionId 等级折扣范围主键
     * @return 结果
     */
    @Override
    public int deleteLevelDiscountPermissionByLevelDiscountPermissionId(Long levelDiscountPermissionId)
    {
        return levelDiscountPermissionMapper.deleteLevelDiscountPermissionByLevelDiscountPermissionId(levelDiscountPermissionId);
    }

    @Override
    public int batchInsertLevelDiscountPermission(List<LevelDiscountPermission> levelDiscountPermissions) {
        return levelDiscountPermissionMapper.insertBatch(levelDiscountPermissions);
    }

    @Override
    public List<LevelDiscountPermission> selectLevelDiscountPermissionListByMemberLevelId(Long memberLevelId) {

        return Optional.ofNullable(levelDiscountPermissionMapper.selectList(levelDiscountPermissionMapper.query().eq(LevelDiscountPermission::getMemberLevelId, memberLevelId)))
                .orElse(Collections.emptyList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLevelDiscountPermissionByMemberLevelId(Long memberLevelId) {
        levelDiscountPermissionMapper.delete(levelDiscountPermissionMapper.query().eq(LevelDiscountPermission::getMemberLevelId, memberLevelId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLevelDiscountPermissionByMemberLevelIds(Long[] memberLevelIds) {
        levelDiscountPermissionMapper.delete(levelDiscountPermissionMapper.query()
                .in(LevelDiscountPermission::getMemberLevelId, Arrays.asList(memberLevelIds)));
    }
}
