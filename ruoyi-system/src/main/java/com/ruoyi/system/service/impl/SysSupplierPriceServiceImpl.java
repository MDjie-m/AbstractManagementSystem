package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.domain.vo.AsticVo;
import com.ruoyi.system.domain.vo.PriceDetailVo;
import com.ruoyi.system.mapper.SysInquiryMapper;
import com.ruoyi.system.mapper.SysProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSupplierPriceMapper;
import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.service.ISysSupplierPriceService;

/**
 * 供应商报价Service业务层处理
 * 
 * @author wzh
 * @date 2024-07-21
 */
@Service
public class SysSupplierPriceServiceImpl implements ISysSupplierPriceService 
{
    @Autowired
    private SysSupplierPriceMapper sysSupplierPriceMapper;
    @Autowired
    private SysProductMapper sysProductMapper;
    @Autowired
    private SysInquiryMapper sysInquiryMapper;


    /**
     * 查询供应商报价
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 供应商报价
     */
    @Override
    public SysSupplierPrice selectSysSupplierPriceBySupplierPriceId(String supplierPriceId)
    {
        return sysSupplierPriceMapper.selectSysSupplierPriceBySupplierPriceId(supplierPriceId);
    }

    /**
     * 查询供应商报价列表
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 供应商报价
     */
    @Override
    public List<SysSupplierPrice> selectSysSupplierPriceList(SysSupplierPrice sysSupplierPrice)
    {
        return sysSupplierPriceMapper.selectSysSupplierPriceList(sysSupplierPrice);
    }

    /**
     * 新增供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    @Override
    public int insertSysSupplierPrice(SysSupplierPrice sysSupplierPrice)
    {
        String productId = sysSupplierPrice.getProductId();
        SysInquiry sysInquiry = sysInquiryMapper.selectAllByProductId(productId);
        Integer inquiryStatus = sysProductMapper.getSysProductByProductId(productId).getInquiryStatus();
        if ( inquiryStatus != 1 ||(inquiryStatus == 1 && sysInquiry.getFeedbackStatus() == 2)){
            //询价状态 未询价 供应商主动报价
            sysSupplierPrice.setSupplierPriceId(UUID.randomUUID().toString());
            sysProductMapper.updateSysProductStatus(productId);//更新产品表中quote_status字段为已报价
        }else {
            //询价状态 已询价 供应商接受询价后再报价
            sysSupplierPrice.setSupplierPriceId(UUID.randomUUID().toString());
            //询价后报价 报价表新增数据同时更新询价表
            sysInquiry.setInquiryDate(sysSupplierPrice.getTime());
            sysInquiry.setPriceRmb(sysSupplierPrice.getPriceRmb());
            sysInquiry.setRMBQuoteUnit(sysSupplierPrice.getRMBQuoteUnit());
            sysInquiry.setPriceUsd(sysSupplierPrice.getPriceUsd());
            sysInquiry.setUnitprice(sysSupplierPrice.getUnitprice());
            sysInquiry.setUnitpriceUnit(sysSupplierPrice.getUnitpriceUnit());
            sysInquiry.setFeedbackStatus(1);
            sysInquiryMapper.updateSysInquiry(sysInquiry);
            sysProductMapper.updateSysProductStatus(productId);
    }
        return sysSupplierPriceMapper.insertSysSupplierPrice(sysSupplierPrice);
    }

    /**
     * 修改供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    @Override
    public int updateSysSupplierPrice(SysSupplierPrice sysSupplierPrice)
    {
        return sysSupplierPriceMapper.updateSysSupplierPrice(sysSupplierPrice);
    }

    /**
     * 批量删除供应商报价
     * 
     * @param supplierPriceIds 需要删除的供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierPriceBySupplierPriceIds(String[] supplierPriceIds)
    {
        return sysSupplierPriceMapper.deleteSysSupplierPriceBySupplierPriceIds(supplierPriceIds);
    }

    /**
     * 删除供应商报价信息
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierPriceBySupplierPriceId(String supplierPriceId)
    {
        return sysSupplierPriceMapper.deleteSysSupplierPriceBySupplierPriceId(supplierPriceId);
    }

    @Override
    public List<AsticVo> productPriceStatistics(
            List<String> supplierIds, String productId, String startDate, String endDate) {
        List<AsticVo> list = new ArrayList();
        // 调用Mapper方法获取所有匹配的报价
        List<SysSupplierPrice> allQuotes = sysSupplierPriceMapper.productPriceStatistics(supplierIds, productId, startDate, endDate);
        // 使用Stream API或传统循环来构建Map
        Map<String, List<SysSupplierPrice>> resultMap = new LinkedHashMap<>(); // 使用LinkedHashMap保持插入顺序
        for (SysSupplierPrice quote : allQuotes) {
            resultMap.computeIfAbsent(quote.getSupplierNameCn(), k -> new ArrayList<>()).add(quote);
        }

        Set<String> strings = resultMap.keySet();
        for (String string : strings) {
            AsticVo vo = new AsticVo();
            vo.setName(string);
            List<SysSupplierPrice> sysSupplierPrices = resultMap.get(string);
            List<PriceDetailVo> priceVoList = new ArrayList<>();
            for (SysSupplierPrice sysSupplierPrice : sysSupplierPrices) {
                PriceDetailVo priceVo = new PriceDetailVo();
                priceVo.setPrice(sysSupplierPrice.getPriceRmb());
                priceVo.setTime(sysSupplierPrice.getTime());
                priceVoList.add(priceVo);
            }
            vo.setQuotes(priceVoList);
            list.add(vo);
        }
        return list;
        }


    /**
     * 查询已报价产品列表
     *
     * @param supplierId 供应商id   productName 产品名称
     * @return 结果
     */
    @Override
    public List<QuoteVo> quoteableProducts(String supplierId, String productId) {
        List<QuoteVo> vvolist = new ArrayList<>();
        List<SysProductVO> pvoList = sysProductMapper.selectProductNamesByParam(supplierId, productId);
        for (SysProductVO sysProductVO : pvoList) {
            QuoteVo quoteVo = new QuoteVo();
            quoteVo.setProductVO(sysProductVO);
            Integer quoteRows = sysSupplierPriceMapper.getQuoteRows(sysProductVO.getProductId());
            Integer cnSupplierRows = sysSupplierPriceMapper.getCnSupplierRows(sysProductVO.getProductId());
            Integer supplierRows = sysSupplierPriceMapper.getSupplierRows(sysProductVO.getProductId());
            quoteVo.setQuoteRows(quoteRows);
            quoteVo.setCnSupplierRows(cnSupplierRows);
            quoteVo.setSupplierRows(supplierRows);
            quoteVo.setTotalRows(cnSupplierRows + supplierRows);
            vvolist.add(quoteVo);
        }
        return vvolist;
    }

    /**
     * 根据产品名称返回对此产品报价的供应商列表
     * @param productId 产品id
     *  @return 结果
     */
    @Override
    public List<SupplierProductVo> quoteSupplier(String productId,Integer classification,Date startDate, Date endDate) {
        List<SupplierProductVo> resultList = new ArrayList<>();
        List<SupplierProductVo> supplierProductVoList = sysSupplierPriceMapper.getSuppliersByProductName(productId,classification,startDate,endDate);
        // supplierProductVoList 包含所有已报价商品供应商相关数据
        //1.按照供应商名称进行分组
        Map<String, List<SupplierProductVo>> groupedBySupplier = supplierProductVoList.stream()
                .collect(Collectors.groupingBy(
                        SupplierProductVo::getSupplierName
                ));
        //2.取每一组时间最新的数据
        Set<String> supplierNameSet = groupedBySupplier.keySet();
        for (String name : supplierNameSet) {
            List<SupplierProductVo> voList = groupedBySupplier.get(name);
            List<SupplierProductVo> collect = voList.stream()
                    .sorted(Comparator.comparing(SupplierProductVo::getPriceDate).reversed())
                    .collect(Collectors.toList());
            resultList.add(collect.get(0));
        }
        return resultList;
    }
}
