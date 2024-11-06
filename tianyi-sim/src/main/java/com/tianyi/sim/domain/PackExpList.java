package com.tianyi.sim.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tianyi.common.annotation.Excel;
import com.tianyi.common.core.domain.BaseEntity;

/**
 * 套餐到期预警对象 pack_exp_list
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public class PackExpList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 省份id */
    @Excel(name = "省份id")
    private Long provId;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String provName;

    /** 地市id */
    @Excel(name = "地市id")
    private Long areaId;

    /** 地市名称 */
    @Excel(name = "地市名称")
    private String areaName;

    /** 客户id */
    @Excel(name = "客户id")
    private Long custId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String custName;

    /** 产品实例id */
    @Excel(name = "产品实例id")
    private Long prodInstId;

    /** 用户号码 */
    @Excel(name = "用户号码")
    private Long accNbr;

    /** 主套餐名称 */
    @Excel(name = "主套餐名称")
    private String mainOfferName;

    /** 制式 */
    @Excel(name = "制式")
    private String netStyle;

    /** 到期时间 */
    @Excel(name = "到期时间")
    private String mainExpDate;

    /** 日期 */
    @Excel(name = "日期")
    private String yyyymmdd;

    public void setProvId(Long provId) 
    {
        this.provId = provId;
    }

    public Long getProvId() 
    {
        return provId;
    }
    public void setProvName(String provName) 
    {
        this.provName = provName;
    }

    public String getProvName() 
    {
        return provName;
    }
    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setCustId(Long custId) 
    {
        this.custId = custId;
    }

    public Long getCustId() 
    {
        return custId;
    }
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setProdInstId(Long prodInstId) 
    {
        this.prodInstId = prodInstId;
    }

    public Long getProdInstId() 
    {
        return prodInstId;
    }
    public void setAccNbr(Long accNbr) 
    {
        this.accNbr = accNbr;
    }

    public Long getAccNbr() 
    {
        return accNbr;
    }
    public void setMainOfferName(String mainOfferName) 
    {
        this.mainOfferName = mainOfferName;
    }

    public String getMainOfferName() 
    {
        return mainOfferName;
    }
    public void setNetStyle(String netStyle) 
    {
        this.netStyle = netStyle;
    }

    public String getNetStyle() 
    {
        return netStyle;
    }
    public void setMainExpDate(String mainExpDate) 
    {
        this.mainExpDate = mainExpDate;
    }

    public String getMainExpDate() 
    {
        return mainExpDate;
    }
    public void setYyyymmdd(String yyyymmdd) 
    {
        this.yyyymmdd = yyyymmdd;
    }

    public String getYyyymmdd() 
    {
        return yyyymmdd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("provId", getProvId())
            .append("provName", getProvName())
            .append("areaId", getAreaId())
            .append("areaName", getAreaName())
            .append("custId", getCustId())
            .append("custName", getCustName())
            .append("prodInstId", getProdInstId())
            .append("accNbr", getAccNbr())
            .append("mainOfferName", getMainOfferName())
            .append("netStyle", getNetStyle())
            .append("mainExpDate", getMainExpDate())
            .append("yyyymmdd", getYyyymmdd())
            .toString();
    }
}
