package com.ruoyi.hotel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 房间类型对象 htl_room_type
 * 
 * @author sucheng
 * @date 2020-12-24
 */
public class HtlRoomType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间类型ID */
    private Long roomTypeId;

    /** 酒店ID */
    @Excel(name = "酒店ID")
    private Long hotelId;

    /** 房间类型 */
    @Excel(name = "房间类型")
    private String roomType;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    public void setRoomTypeId(Long roomTypeId) 
    {
        this.roomTypeId = roomTypeId;
    }

    public Long getRoomTypeId() 
    {
        return roomTypeId;
    }
    public void setHotelId(Long hotelId) 
    {
        this.hotelId = hotelId;
    }

    public Long getHotelId() 
    {
        return hotelId;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomTypeId", getRoomTypeId())
            .append("hotelId", getHotelId())
            .append("roomType", getRoomType())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
