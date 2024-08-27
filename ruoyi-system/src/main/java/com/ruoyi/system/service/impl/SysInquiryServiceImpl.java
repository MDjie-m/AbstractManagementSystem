package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysInquiryMapper;
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.service.ISysInquiryService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author tyc
 * @date 2024-08-26
 */
@Service
public class SysInquiryServiceImpl implements ISysInquiryService 
{
    @Autowired
    private SysInquiryMapper sysInquiryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param inquiryId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysInquiry selectSysInquiryByInquiryId(String inquiryId)
    {
        return sysInquiryMapper.selectSysInquiryByInquiryId(inquiryId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysInquiry> selectSysInquiryList(SysInquiry sysInquiry)
    {
        return sysInquiryMapper.selectSysInquiryList(sysInquiry);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysInquiry(SysInquiry sysInquiry)
    {
        return sysInquiryMapper.insertSysInquiry(sysInquiry);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysInquiry(SysInquiry sysInquiry)
    {
        return sysInquiryMapper.updateSysInquiry(sysInquiry);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param inquiryIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysInquiryByInquiryIds(String[] inquiryIds)
    {
        return sysInquiryMapper.deleteSysInquiryByInquiryIds(inquiryIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param inquiryId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysInquiryByInquiryId(String inquiryId)
    {
        return sysInquiryMapper.deleteSysInquiryByInquiryId(inquiryId);
    }
}
