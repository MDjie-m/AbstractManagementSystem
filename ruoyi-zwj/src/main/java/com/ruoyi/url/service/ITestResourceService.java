package com.ruoyi.url.service;

import java.util.List;
import com.ruoyi.url.domain.TestResource;

/**
 * 测试图片上传Service接口
 * 
 * @author zwj
 * @date 2024-08-17
 */
public interface ITestResourceService 
{
    /**
     * 查询测试图片上传
     * 
     * @param id 测试图片上传主键
     * @return 测试图片上传
     */
    public TestResource selectTestResourceById(Long id);

    /**
     * 查询测试图片上传列表
     * 
     * @param testResource 测试图片上传
     * @return 测试图片上传集合
     */
    public List<TestResource> selectTestResourceList(TestResource testResource);

    /**
     * 新增测试图片上传
     * 
     * @param testResource 测试图片上传
     * @return 结果
     */
    public int insertTestResource(TestResource testResource);

    /**
     * 修改测试图片上传
     * 
     * @param testResource 测试图片上传
     * @return 结果
     */
    public int updateTestResource(TestResource testResource);

    /**
     * 批量删除测试图片上传
     * 
     * @param ids 需要删除的测试图片上传主键集合
     * @return 结果
     */
    public int deleteTestResourceByIds(Long[] ids);

    /**
     * 删除测试图片上传信息
     * 
     * @param id 测试图片上传主键
     * @return 结果
     */
    public int deleteTestResourceById(Long id);
}
