package com.ruoyi.test_data_scope.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.test_data_scope.domain.Picture;
import com.ruoyi.test_data_scope.mapper.PictureMapper;
import com.ruoyi.test_data_scope.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试数据权限Service业务层处理
 * 
 * @author zwj
 * @date 2024-08-21
 */
@Service
public class PictureServiceImpl implements IPictureService 
{
    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 查询测试数据权限
     * 
     * @param id 测试数据权限主键
     * @return 测试数据权限
     */
    @Override
    public Picture selectPictureById(Long id)
    {
        return pictureMapper.selectPictureById(id);
    }

    /**
     * 查询测试数据权限列表
     * 
     * @param picture 测试数据权限
     * @return 测试数据权限
     */
    @Override
    @DataScope(userAlias = "u", deptAlias = "u") // 让部门的范围局限在sys_user表
    public List<Picture> selectPictureList(Picture picture)
    {
        return pictureMapper.selectPictureList(picture);
    }

    /**
     * 新增测试数据权限
     * 
     * @param picture 测试数据权限
     * @return 结果
     */
    @Override
    public int insertPicture(Picture picture)
    {
        picture.setCreateTime(DateUtils.getNowDate());
        picture.setCreateBy(SecurityUtils.getUsername());
        picture.setUserId(SecurityUtils.getUserId());
        return pictureMapper.insertPicture(picture);
    }

    /**
     * 修改测试数据权限
     * 
     * @param picture 测试数据权限
     * @return 结果
     */
    @Override
    public int updatePicture(Picture picture)
    {
        picture.setUpdateTime(DateUtils.getNowDate());
        return pictureMapper.updatePicture(picture);
    }

    /**
     * 批量删除测试数据权限
     * 
     * @param ids 需要删除的测试数据权限主键
     * @return 结果
     */
    @Override
    public int deletePictureByIds(Long[] ids)
    {
        return pictureMapper.deletePictureByIds(ids);
    }

    /**
     * 删除测试数据权限信息
     * 
     * @param id 测试数据权限主键
     * @return 结果
     */
    @Override
    public int deletePictureById(Long id)
    {
        return pictureMapper.deletePictureById(id);
    }
}
