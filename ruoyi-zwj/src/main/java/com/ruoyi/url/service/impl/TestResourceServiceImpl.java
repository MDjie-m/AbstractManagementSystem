package com.ruoyi.url.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.url.mapper.TestResourceMapper;
import com.ruoyi.url.domain.TestResource;
import com.ruoyi.url.service.ITestResourceService;

/**
 * 测试图片上传Service业务层处理
 * 
 * @author zwj
 * @date 2024-08-17
 */
@Service
public class TestResourceServiceImpl implements ITestResourceService 
{
    @Autowired
    private TestResourceMapper testResourceMapper;

    /**
     * 查询测试图片上传
     * 
     * @param id 测试图片上传主键
     * @return 测试图片上传
     */
    @Override
    public TestResource selectTestResourceById(Long id)
    {
        return testResourceMapper.selectTestResourceById(id);
    }

    /**
     * 查询测试图片上传列表
     * 
     * @param testResource 测试图片上传
     * @return 测试图片上传
     */
    @Override
    public List<TestResource> selectTestResourceList(TestResource testResource)
    {
        return testResourceMapper.selectTestResourceList(testResource);
    }

    /**
     * 新增测试图片上传
     * 
     * @param testResource 测试图片上传
     * @return 结果
     */
    @Override
    public int insertTestResource(TestResource testResource)
    {
        return testResourceMapper.insertTestResource(testResource);
    }

    /**
     * 修改测试图片上传
     * 
     * @param testResource 测试图片上传
     * @return 结果
     */
    @Override
    public int updateTestResource(TestResource testResource)
    {
        return testResourceMapper.updateTestResource(testResource);
    }

    /**
     * 批量删除测试图片上传
     * 
     * @param ids 需要删除的测试图片上传主键
     * @return 结果
     */
    @Override
    public int deleteTestResourceByIds(Long[] ids)
    {
        return testResourceMapper.deleteTestResourceByIds(ids);
    }

    /**
     * 删除测试图片上传信息
     * 
     * @param id 测试图片上传主键
     * @return 结果
     */
    @Override
    public int deleteTestResourceById(Long id)
    {
        return testResourceMapper.deleteTestResourceById(id);
    }
}
