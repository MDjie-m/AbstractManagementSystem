package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 汇率对象 sys_currency
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
public class SysCurrency extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 汇率表主键id */
    private Long currencyId;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date update;

    /** 当日汇率 */
    @Excel(name = "当日汇率")
    private BigDecimal currency;

    public void setCurrencyId(Long currencyId) 
    {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() 
    {
        return currencyId;
    }
    public void setUpdate(Date update) 
    {
        this.update = update;
    }

    public Date getUpdate() 
    {
        return update;
    }
    public void setCurrency(BigDecimal currency) 
    {
        this.currency = currency;
    }

    public BigDecimal getCurrency() 
    {
        return currency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("currencyId", getCurrencyId())
            .append("update", getUpdate())
            .append("currency", getCurrency())
            .toString();
    }
}
