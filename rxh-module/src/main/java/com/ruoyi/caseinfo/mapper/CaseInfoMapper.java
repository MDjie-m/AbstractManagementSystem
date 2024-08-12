package com.ruoyi.caseinfo.mapper;

import java.util.List;
import com.ruoyi.caseinfo.domain.CaseInfo;

/**
 * 案件信息Mapper接口
 * 
 * @author ysg
 * @date 2024-08-12
 */
public interface CaseInfoMapper 
{
    /**
     * 查询案件信息
     * 
     * @param caseId 案件信息主键
     * @return 案件信息
     */
        CaseInfo selectCaseInfoByCaseId(Long caseId);

    /**
     * 查询案件信息列表
     * 
     * @param caseInfo 案件信息
     * @return 案件信息集合
     */
    List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo);

    /**
     * 新增案件信息
     * 
     * @param caseInfo 案件信息
     * @return 结果
     */
    int insertCaseInfo(CaseInfo caseInfo);

    /**
     * 修改案件信息
     * 
     * @param caseInfo 案件信息
     * @return 结果
     */
    int updateCaseInfo(CaseInfo caseInfo);

    /**
     * 删除案件信息
     * 
     * @param caseId 案件信息主键
     * @return 结果
     */
    int deleteCaseInfoByCaseId(Long caseId);

    /**
     * 批量删除案件信息
     * 
     * @param caseIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCaseInfoByCaseIds(Long[] caseIds);
}
