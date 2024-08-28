package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.domain.SysSupplierPrice;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author tyc
 * @date 2024-08-26
 */
public interface SysInquiryMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param inquiryId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysInquiryByInquiryId(String inquiryId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param inquiryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysInquiryByInquiryIds(String[] inquiryIds);

    Integer selectInquiryTimes(@Param("productId") String productId);

    int refuseQuotation(@Param("inquiryIds") List<Long> inquiryIds);

    SysSupplierPrice findLatestQuote(@Param("productId") String productId);

    int updateInquiryStatus(@Param("productIds") List<String> productIds);
}
