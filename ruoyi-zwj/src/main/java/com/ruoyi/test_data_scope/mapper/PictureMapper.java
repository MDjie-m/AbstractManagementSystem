package com.ruoyi.test_data_scope.mapper;

import com.ruoyi.test_data_scope.domain.Picture;

import java.util.List;

/**
 * 测试数据权限Mapper接口
 * 
 * @author zwj
 * @date 2024-08-21
 */
public interface PictureMapper 
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
     * 删除测试数据权限
     * 
     * @param id 测试数据权限主键
     * @return 结果
     */
    public int deletePictureById(Long id);

    /**
     * 批量删除测试数据权限
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePictureByIds(Long[] ids);
}
