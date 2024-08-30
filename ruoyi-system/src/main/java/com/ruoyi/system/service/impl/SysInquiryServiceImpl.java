package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.domain.dto.SysInquiryDTO;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.InquiryVo;
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

    /**
     * 查询询价次数
     * @param productId
     * @return
     */
    @Override
    public Integer selectInquiryTimes(String productId) {
        return sysInquiryMapper.selectInquiryTimes(productId);
    }

    /**
     * 批量拒绝报价
     * @param inquiryIds 询价id列表
     * @return int
     */
    @Override
    public int refuseQuotation(List<Long> inquiryIds) {
        return sysInquiryMapper.refuseQuotation(inquiryIds);
    }

    @Override
    public String batchInquiry(List<SysProDuctDTO> sysProDuctDTOList) {
        int i=0;
        List<String> productIds = new ArrayList<>();
        for (SysProDuctDTO sysProDuctDTO : sysProDuctDTOList) {
            //先判断这个产品的询价状态是否为已询价，如果是那就不会再添加一条询价,==0说明未询价，
            if(sysProDuctDTO.getInquiryStatus()==0){//==0说明未询价，那就把状态变为已询价
                productIds.add(sysProDuctDTO.getProductId());//记录正在询价的产品的id
                SysInquiry sysInquiry = new SysInquiry();
                sysInquiry.setBuyerId(SecurityUtils.getLoginUser().getUserId());//赋值采购员id，也可能是管理员
//                sysInquiry.setBuyerId(Long.valueOf(2));//后端测试用
                sysInquiry.setProductId(sysProDuctDTO.getProductId());//赋值产品Id，
                sysInquiry.setInquiryDate(new Date());
                //还得判断这个产品今天是否已报价
                if(sysProDuctDTO.getQuoteStatus()==1){//==0说明今天没报价，==1说明今天已报价
                    sysInquiry.setFeedbackStatus(1);//赋值反馈状态为已报价
                    //说明已报价，那就先去查一次今天的报价,然后赋值给询价对象里面的报价字段
                    SysSupplierPrice sysSupplierPrice = sysInquiryMapper.findLatestQuote(sysProDuctDTO.getProductId());
                    if(!Objects.isNull(sysSupplierPrice)) {//避免业务出错没查到数据的情况
                        //赋值单价和人民币报价
                        sysInquiry.setPriceRmb(sysSupplierPrice.getPriceRmb());
                        sysInquiry.setPriceUsd(sysSupplierPrice.getPriceUsd());
                        sysInquiry.setRMBQuoteUnit(sysSupplierPrice.getRMBQuoteUnit());
                        sysInquiry.setUnitprice(sysSupplierPrice.getUnitprice());
                        sysInquiry.setUnitpriceUnit(sysSupplierPrice.getUnitpriceUnit());
                    }
                }else {
                    //说明未报价，那就赋值询价反馈状态为待报价
                    sysInquiry.setFeedbackStatus(0);
                }
                //然后把这条询价数据添加进询价表中
                sysInquiryMapper.insertSysInquiry(sysInquiry);
                i = i + 1;
            }
        }
        sysInquiryMapper.updateInquiryStatus(productIds);//批量变更询价状态
        return "成功添加"+i+"条询价记录。";
    }

    /**
     * 查询询价结果列表
     * @param sysInquiryDTO 询价dto
     * @return list
     */
    @Override
    public List<InquiryVo> selectInquiryResult(SysInquiryDTO sysInquiryDTO) {
        return null;
    }
}
