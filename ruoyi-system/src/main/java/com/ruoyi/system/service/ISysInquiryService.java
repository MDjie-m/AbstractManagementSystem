package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.domain.dto.SysProDuctDTO;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author tyc
 * @date 2024-08-26
 */
public interface ISysInquiryService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param inquiryId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysInquiry selectSysInquiryByInquiryId(String inquiryId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysInquiry> selectSysInquiryList(SysInquiry sysInquiry);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 结果
     */
    public int insertSysInquiry(SysInquiry sysInquiry);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysInquiry 【请填写功能名称】
     * @return 结果
     */
    public int updateSysInquiry(SysInquiry sysInquiry);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param inquiryIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSysInquiryByInquiryIds(String[] inquiryIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param inquiryId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysInquiryByInquiryId(String inquiryId);

    Integer selectInquiryTimes(String productId);

    /**
     * 批量拒绝报价
     * @param inquiryIds 询价id列表
     * @return int
     */
    int refuseQuotation(List<Long> inquiryIds);

    /**
     * 批量询价
     * @param sysProDuctDTOList 产品列表
     * @return string
     */
    String batchInquiry(List<SysProDuctDTO> sysProDuctDTOList);
}
