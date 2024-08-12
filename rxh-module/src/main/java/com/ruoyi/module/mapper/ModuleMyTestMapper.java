package com.ruoyi.module.mapper;

import java.util.List;
import com.ruoyi.module.domain.ModuleMyTest;

/**
 * 测试Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-12
 */
public interface ModuleMyTestMapper 
{
    /**
     * 查询测试
     * 
     * @param testId 测试主键
     * @return 测试
     */
    ModuleMyTest selectModuleMyTestByTestId(Integer testId);

    /**
     * 查询测试列表
     * 
     * @param moduleMyTest 测试
     * @return 测试集合
     */
    List<ModuleMyTest> selectModuleMyTestList(ModuleMyTest moduleMyTest);

    /**
     * 新增测试
     * 
     * @param moduleMyTest 测试
     * @return 结果
     */
    int insertModuleMyTest(ModuleMyTest moduleMyTest);

    /**
     * 修改测试
     * 
     * @param moduleMyTest 测试
     * @return 结果
     */
    int updateModuleMyTest(ModuleMyTest moduleMyTest);

    /**
     * 删除测试
     * 
     * @param testId 测试主键
     * @return 结果
     */
    int deleteModuleMyTestByTestId(Integer testId);

    /**
     * 批量删除测试
     * 
     * @param testIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteModuleMyTestByTestIds(Integer[] testIds);
}
