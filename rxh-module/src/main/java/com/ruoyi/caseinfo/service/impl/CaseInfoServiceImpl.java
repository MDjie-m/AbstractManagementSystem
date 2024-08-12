package com.ruoyi.caseinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.caseinfo.mapper.CaseInfoMapper;
import com.ruoyi.caseinfo.domain.CaseInfo;
import com.ruoyi.caseinfo.service.ICaseInfoService;

/**
 * 案件信息Service业务层处理
 * 
 * @author ysg
 * @date 2024-08-12
 */
@Service
public class CaseInfoServiceImpl implements ICaseInfoService 
{
    @Autowired
    private CaseInfoMapper caseInfoMapper;

    /**
     * 查询案件信息
     * 
     * @param caseId 案件信息主键
     * @return 案件信息
     */
    @Override
    public CaseInfo selectCaseInfoByCaseId(Long caseId)
    {
        return caseInfoMapper.selectCaseInfoByCaseId(caseId);
    }

    /**
     * 查询案件信息列表
     * 
     * @param caseInfo 案件信息
     * @return 案件信息
     */
    @Override
    public List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo)
    {
        return caseInfoMapper.selectCaseInfoList(caseInfo);
    }

    /**
     * 新增案件信息
     * 
     * @param caseInfo 案件信息
     * @return 结果
     */
    @Override
    public int insertCaseInfo(CaseInfo caseInfo)
    {
        caseInfo.setCreateTime(DateUtils.getNowDate());
        return caseInfoMapper.insertCaseInfo(caseInfo);
    }

    /**
     * 修改案件信息
     * 
     * @param caseInfo 案件信息
     * @return 结果
     */
    @Override
    public int updateCaseInfo(CaseInfo caseInfo)
    {
        caseInfo.setUpdateTime(DateUtils.getNowDate());
        return caseInfoMapper.updateCaseInfo(caseInfo);
    }

    /**
     * 批量删除案件信息
     * 
     * @param caseIds 需要删除的案件信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseInfoByCaseIds(Long[] caseIds)
    {
        return caseInfoMapper.deleteCaseInfoByCaseIds(caseIds);
    }

    /**
     * 删除案件信息信息
     * 
     * @param caseId 案件信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseInfoByCaseId(Long caseId)
    {
        return caseInfoMapper.deleteCaseInfoByCaseId(caseId);
    }
}
