package com.renxin.psychology.service.impl;

import java.util.Date;
import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.domain.PsyConsultantPackageEquity;
import com.renxin.psychology.mapper.PsyConsultantPackageEquityMapper;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import com.renxin.psychology.service.IPsyConsultantPackageEquityService;
import com.renxin.psychology.service.IPsyConsultantPackageService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 咨询师套餐权益Service业务层处理
 * 
 * @author renxin
 * @date 2024-07-10
 */
@Service
public class PsyConsultantPackageEquityServiceImpl implements IPsyConsultantPackageEquityService 
{
    @Autowired
    private PsyConsultantPackageEquityMapper packageEquityMapper;
    
    @Autowired
    private IPsyConsultantOrderService consultantOrderService;
    
    @Autowired
    private IPsyConsultantPackageService packageService;

    /**
     * 查询咨询师套餐权益
     * 
     * @param consultantId 咨询师套餐权益主键
     * @return 咨询师套餐权益
     */
    @Override
    public PsyConsultantPackageEquity selectPsyConsultantPackageEquityByConsultantId(Long consultantId)
    {
        return packageEquityMapper.selectPsyConsultantPackageEquityByConsultantId(consultantId);
    }

    /**
     * 查询咨询师套餐权益列表
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 咨询师套餐权益
     */
    @Override
    public List<PsyConsultantPackageEquity> selectPsyConsultantPackageEquityList(PsyConsultantPackageEquity psyConsultantPackageEquity)
    {
        return packageEquityMapper.selectPsyConsultantPackageEquityList(psyConsultantPackageEquity);
    }

    /**
     * 新增咨询师套餐权益
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 结果
     */
    @Override
    public int insertPsyConsultantPackageEquity(PsyConsultantPackageEquity psyConsultantPackageEquity)
    {
        psyConsultantPackageEquity.setCreateTime(DateUtils.getNowDate());
        return packageEquityMapper.insertPsyConsultantPackageEquity(psyConsultantPackageEquity);
    }

    /**
     * 修改咨询师套餐权益
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 结果
     */
    @Override
    public int updatePsyConsultantPackageEquity(PsyConsultantPackageEquity psyConsultantPackageEquity)
    {
        psyConsultantPackageEquity.setUpdateTime(DateUtils.getNowDate());
        return packageEquityMapper.updatePsyConsultantPackageEquity(psyConsultantPackageEquity);
    }

    /**
     * 批量删除咨询师套餐权益
     * 
     * @param consultantIds 需要删除的咨询师套餐权益主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantPackageEquityByConsultantIds(Long[] consultantIds)
    {
        return packageEquityMapper.deletePsyConsultantPackageEquityByConsultantIds(consultantIds);
    }

    /**
     * 删除咨询师套餐权益信息
     * 
     * @param consultantId 咨询师套餐权益主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantPackageEquityByConsultantId(Long consultantId)
    {
        return packageEquityMapper.deletePsyConsultantPackageEquityByConsultantId(consultantId);
    }

    /**
     * 处理套餐订单 （购买套餐-获得券)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleConsultantPackageOrder(String orderNo){
        //订单详情
        PsyConsultantOrder order = consultantOrderService.selectPsyConsultantOrderByOrderNo(orderNo);
        //套餐详情
        PsyConsultantPackage consultantPackage = packageService.selectPsyConsultantPackageByPackageId(Long.valueOf(order.getServerId()));
        
       /* PsyConsultantPackageEquity req = new PsyConsultantPackageEquity();
            req.setConsultantId(Long.valueOf(order.getPayConsultantId()));
        List<PsyConsultantPackageEquity> equityList = packageEquityMapper.selectPsyConsultantPackageEquityList(req);
        
        //若该咨询师无相关权益记录, 则新增
        if (ObjectUtils.isEmpty(equityList)){
            req.setTeamSupNum(consultantPackage.getTeamSupNum());
            req.setPersonSupNum(consultantPackage.getPersonSupNum());
            req.setPersonExpNum(consultantPackage.getPersonExpNum());
            req.setCourseNum(consultantPackage.getCourseNum());
            req.setCreateBy(order.getPayConsultantId());
            req.setUpdateBy(order.getPayConsultantId());
            req.setCreateTime(new Date());
            req.setUpdateTime(new Date());
            packageEquityMapper.insertPsyConsultantPackageEquity(req);
        } else {
            //若已存在该咨询师的权益记录, 则在基础上添加次数
            PsyConsultantPackageEquity equity = equityList.get(0);
            equity.setTeamSupNum(equity.getTeamSupNum() + consultantPackage.getTeamSupNum());
            equity.setPersonSupNum(equity.getPersonSupNum() + consultantPackage.getPersonSupNum());
            equity.setPersonExpNum(equity.getPersonExpNum() + consultantPackage.getPersonExpNum());
            equity.setCourseNum(equity.getCourseNum() + consultantPackage.getCourseNum());
            equity.setUpdateTime(new Date());
            packageEquityMapper.updatePsyConsultantPackageEquity(equity);
        }*/

    }
    
}
