package com.ruoyi.companySys.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司管理对象 company
 * 
 * @author GG
 * @date 2024-07-13
 */
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long cId;

    /** 公司编号 */
    @Excel(name = "公司编号")
    private String companyCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddress;

    /** 公司邮箱 */
    @Excel(name = "公司邮箱")
    private String companyEmail;

    /** 备用1 */
    private Long cReserve1;

    /** 备用2 */
    private String cReserve2;

    /** 备用3 */
    private String cReserve3;

    /** 备用4 */
    private Date cReserve4;

    /** 风场管理信息 */
    private List<WindFarm> windFarmList;

    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setCompanyAddress(String companyAddress) 
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() 
    {
        return companyAddress;
    }
    public void setCompanyEmail(String companyEmail) 
    {
        this.companyEmail = companyEmail;
    }

    public String getCompanyEmail() 
    {
        return companyEmail;
    }
    public void setcReserve1(Long cReserve1) 
    {
        this.cReserve1 = cReserve1;
    }

    public Long getcReserve1() 
    {
        return cReserve1;
    }
    public void setcReserve2(String cReserve2) 
    {
        this.cReserve2 = cReserve2;
    }

    public String getcReserve2() 
    {
        return cReserve2;
    }
    public void setcReserve3(String cReserve3) 
    {
        this.cReserve3 = cReserve3;
    }

    public String getcReserve3() 
    {
        return cReserve3;
    }
    public void setcReserve4(Date cReserve4) 
    {
        this.cReserve4 = cReserve4;
    }

    public Date getcReserve4() 
    {
        return cReserve4;
    }

    public List<WindFarm> getWindFarmList()
    {
        return windFarmList;
    }

    public void setWindFarmList(List<WindFarm> windFarmList)
    {
        this.windFarmList = windFarmList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cId", getcId())
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .append("companyAddress", getCompanyAddress())
            .append("companyEmail", getCompanyEmail())
            .append("cReserve1", getcReserve1())
            .append("cReserve2", getcReserve2())
            .append("cReserve3", getcReserve3())
            .append("cReserve4", getcReserve4())
            .append("windFarmList", getWindFarmList())
            .toString();
    }
}
