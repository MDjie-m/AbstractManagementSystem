package com.ruoyi.test_data_scope.service;

import com.ruoyi.test_data_scope.domain.Picture;

import java.util.List;

/**
 * 测试数据权限Service接口
 * 
 * @author zwj
 * @date 2024-08-21
 */
public interface IPictureService 
{
    /**
     * 查询测试数据权限
     * 
     * @param id 测试数据权限主键
     * @return 测试数据权限
     */
    public Picture selectPictureById(Long id);

    /**
     * 查询测试数据权限列表
     * 
     * @param picture 测试数据权限
     * @return 测试数据权限集合
     */
    public List<Picture> selectPictureList(Picture picture);

    /**
     * 新增测试数据权限
     * 
     * @param picture 测试数据权限
     * @return 结果
     */
    public int insertPicture(Picture picture);

    /**
     * 修改测试数据权限
     * 
     * @param picture 测试数据权限
     * @return 结果
     */
    public int updatePicture(Picture picture);

    /**
     * 批量删除测试数据权限
     * 
     * @param ids 需要删除的测试数据权限主键集合
     * @return 结果
     */
    public int deletePictureByIds(Long[] ids);

    /**
     * 删除测试数据权限信息
     * 
     * @param id 测试数据权限主键
     * @return 结果
     */
    public int deletePictureById(Long id);
}
