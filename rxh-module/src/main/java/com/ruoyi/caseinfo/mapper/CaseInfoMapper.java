package com.ruoyi.caseinfo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.caseinfo.domain.CaseInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * 案件信息Mapper接口
 *
 * @author ysg
 * @date 2024-08-13
 */
@Mapper
public interface CaseInfoMapper extends BaseMapper<CaseInfo> {
    /**
     * 查询案件信息
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    public CaseInfo selectCaseInfoById(Long id);

    /**
     * 查询案件信息列表
     *
     * @param caseInfo 案件信息
     * @return 案件信息集合
     */
    public List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo);

    /**
     * 新增案件信息
     *
     * @param caseInfo 案件信息
     * @return 结果
     */
    public int insertCaseInfo(CaseInfo caseInfo);

    /**
     * 修改案件信息
     *
     * @param caseInfo 案件信息
     * @return 结果
     */
    public int updateCaseInfo(CaseInfo caseInfo);

    /**
     * 删除案件信息
     *
     * @param id 案件信息主键
     * @return 结果
     */
    public int deleteCaseInfoById(Long id);

    /**
     * 批量删除案件信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseInfoByIds(Long[] ids);
}
