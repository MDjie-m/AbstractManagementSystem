package com.ruoyi.caseinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.caseinfo.mapper.CaseInfoMapper;
import com.ruoyi.caseinfo.domain.CaseInfo;
import com.ruoyi.caseinfo.service.CaseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 案件信息Service业务层处理
 *
 * @author ysg
 * @date 2024-08-13
 */
@Service
public class CaseInfoServiceImpl extends ServiceImpl<CaseInfoMapper,CaseInfo> implements CaseInfoService{
    @Autowired
    private CaseInfoMapper caseInfoMapper;

    /**
     * 查询案件信息
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    @Override
    public CaseInfo selectCaseInfoById(Long id){
        return caseInfoMapper.selectCaseInfoById(id);
    }

    /**
     * 查询案件信息列表
     *
     * @param caseInfo 案件信息
     * @return 案件信息
     */
    @Override
    public List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo){
        return caseInfoMapper.selectCaseInfoList(caseInfo);
    }

    /**
     * 新增案件信息
     *
     * @param caseInfo 案件信息
     * @return 结果
     */
    @Override
    public int insertCaseInfo(CaseInfo caseInfo){
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
     * @param ids 需要删除的案件信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseInfoByIds(Long[] ids)
    {
        return caseInfoMapper.deleteCaseInfoByIds(ids);
    }

    /**
     * 删除案件信息信息
     *
     * @param id 案件信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseInfoById(Long id)
    {
        return caseInfoMapper.deleteCaseInfoById(id);
    }
}
