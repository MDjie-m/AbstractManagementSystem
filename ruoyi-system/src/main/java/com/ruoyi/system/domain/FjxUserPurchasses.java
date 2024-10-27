package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户购买商品对象 FJX_user_purchasses
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class FjxUserPurchasses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private String id;

    /** 购买记录的唯一标识 */
    @Excel(name = "购买记录的唯一标识")
    private Long purchaseId;

    /** 用户的唯一标识 */
    @Excel(name = "用户的唯一标识")
    private Long userId;

    /** 商品的唯一标识 */
    @Excel(name = "商品的唯一标识")
    private Long productId;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Long purchaseQuantity;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal purchassesPrice;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String purchassesState;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dispatchTime;

    /** 收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptTime;

    /** 订单完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productNreceiptame;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date opderTime;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date turnoverTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setPurchaseQuantity(Long purchaseQuantity) 
    {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Long getPurchaseQuantity() 
    {
        return purchaseQuantity;
    }
    public void setPurchassesPrice(BigDecimal purchassesPrice) 
    {
        this.purchassesPrice = purchassesPrice;
    }

    public BigDecimal getPurchassesPrice() 
    {
        return purchassesPrice;
    }
    public void setPurchassesState(String purchassesState) 
    {
        this.purchassesState = purchassesState;
    }

    public String getPurchassesState() 
    {
        return purchassesState;
    }
    public void setDispatchTime(Date dispatchTime) 
    {
        this.dispatchTime = dispatchTime;
    }

    public Date getDispatchTime() 
    {
        return dispatchTime;
    }
    public void setReceiptTime(Date receiptTime) 
    {
        this.receiptTime = receiptTime;
    }

    public Date getReceiptTime() 
    {
        return receiptTime;
    }
    public void setProductNreceiptame(Date productNreceiptame) 
    {
        this.productNreceiptame = productNreceiptame;
    }

    public Date getProductNreceiptame() 
    {
        return productNreceiptame;
    }
    public void setOpderTime(Date opderTime) 
    {
        this.opderTime = opderTime;
    }

    public Date getOpderTime() 
    {
        return opderTime;
    }
    public void setTurnoverTime(Date turnoverTime) 
    {
        this.turnoverTime = turnoverTime;
    }

    public Date getTurnoverTime() 
    {
        return turnoverTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseId", getPurchaseId())
            .append("userId", getUserId())
            .append("productId", getProductId())
            .append("purchaseQuantity", getPurchaseQuantity())
            .append("purchassesPrice", getPurchassesPrice())
            .append("purchassesState", getPurchassesState())
            .append("dispatchTime", getDispatchTime())
            .append("receiptTime", getReceiptTime())
            .append("productNreceiptame", getProductNreceiptame())
            .append("opderTime", getOpderTime())
            .append("turnoverTime", getTurnoverTime())
            .toString();
    }
}
