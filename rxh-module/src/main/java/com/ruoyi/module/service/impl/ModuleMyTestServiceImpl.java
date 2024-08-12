package com.ruoyi.module.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.module.mapper.ModuleMyTestMapper;
import com.ruoyi.module.domain.ModuleMyTest;
import com.ruoyi.module.service.IModuleMyTestService;

/**
 * 测试Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-12
 */
@Service
public class ModuleMyTestServiceImpl implements IModuleMyTestService 
{
    @Autowired
    private ModuleMyTestMapper moduleMyTestMapper;

    /**
     * 查询测试
     * 
     * @param testId 测试主键
     * @return 测试
     */
    @Override
    public ModuleMyTest selectModuleMyTestByTestId(Integer testId)
    {
        return moduleMyTestMapper.selectModuleMyTestByTestId(testId);
    }

    /**
     * 查询测试列表
     * 
     * @param moduleMyTest 测试
     * @return 测试
     */
    @Override
    public List<ModuleMyTest> selectModuleMyTestList(ModuleMyTest moduleMyTest)
    {
        return moduleMyTestMapper.selectModuleMyTestList(moduleMyTest);
    }

    /**
     * 新增测试
     * 
     * @param moduleMyTest 测试
     * @return 结果
     */
    @Override
    public int insertModuleMyTest(ModuleMyTest moduleMyTest)
    {
        return moduleMyTestMapper.insertModuleMyTest(moduleMyTest);
    }

    /**
     * 修改测试
     * 
     * @param moduleMyTest 测试
     * @return 结果
     */
    @Override
    public int updateModuleMyTest(ModuleMyTest moduleMyTest)
    {
        return moduleMyTestMapper.updateModuleMyTest(moduleMyTest);
    }

    /**
     * 批量删除测试
     * 
     * @param testIds 需要删除的测试主键
     * @return 结果
     */
    @Override
    public int deleteModuleMyTestByTestIds(Integer[] testIds)
    {
        return moduleMyTestMapper.deleteModuleMyTestByTestIds(testIds);
    }

    /**
     * 删除测试信息
     * 
     * @param testId 测试主键
     * @return 结果
     */
    @Override
    public int deleteModuleMyTestByTestId(Integer testId)
    {
        return moduleMyTestMapper.deleteModuleMyTestByTestId(testId);
    }
}
