package com.ruoyi.companySys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 风场管理对象 wind_farm
 * 
 * @author GG
 * @date 2024-07-13
 */
public class WindFarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long wId;

    /** 风场编号 */
    @Excel(name = "风场编号")
    private String windFarmCode;

    /** 风场名称 */
    @Excel(name = "风场名称")
    private String windFarmName;

    /** 所属公司 */
    @Excel(name = "所属公司")
    private Long companyCode;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 备用1 */
    private Long wReserve1;

    /** 备用2 */
    private String wReserve2;

    /** 备用3 */
    private String wReserve3;

    /** 备用4 */
    private Date wReserve4;

    public void setwId(Long wId) 
    {
        this.wId = wId;
    }

    public Long getwId() 
    {
        return wId;
    }
    public void setWindFarmCode(String windFarmCode) 
    {
        this.windFarmCode = windFarmCode;
    }

    public String getWindFarmCode() 
    {
        return windFarmCode;
    }
    public void setWindFarmName(String windFarmName) 
    {
        this.windFarmName = windFarmName;
    }

    public String getWindFarmName() 
    {
        return windFarmName;
    }
    public void setCompanyCode(Long companyCode) 
    {
        this.companyCode = companyCode;
    }

    public Long getCompanyCode() 
    {
        return companyCode;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setwReserve1(Long wReserve1) 
    {
        this.wReserve1 = wReserve1;
    }

    public Long getwReserve1() 
    {
        return wReserve1;
    }
    public void setwReserve2(String wReserve2) 
    {
        this.wReserve2 = wReserve2;
    }

    public String getwReserve2() 
    {
        return wReserve2;
    }
    public void setwReserve3(String wReserve3) 
    {
        this.wReserve3 = wReserve3;
    }

    public String getwReserve3() 
    {
        return wReserve3;
    }
    public void setwReserve4(Date wReserve4) 
    {
        this.wReserve4 = wReserve4;
    }

    public Date getwReserve4() 
    {
        return wReserve4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wId", getwId())
            .append("windFarmCode", getWindFarmCode())
            .append("windFarmName", getWindFarmName())
            .append("companyCode", getCompanyCode())
            .append("address", getAddress())
            .append("wReserve1", getwReserve1())
            .append("wReserve2", getwReserve2())
            .append("wReserve3", getwReserve3())
            .append("wReserve4", getwReserve4())
            .toString();
    }
}
