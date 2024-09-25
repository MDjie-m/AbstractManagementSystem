package com.ruoyi.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 球桌抓拍等对象 t_desk_image
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@TableName("t_desk_image")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskImage extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("desk_multimedia_id")
    private Long deskMultimediaId;

    /** 抓拍时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抓拍时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("capture_time")
    private Date captureTime;

    /** 摄像头id */
    @Excel(name = "摄像头id")

    @TableField("camera_id")
    private Long cameraId;

    /** 球桌id */
    @Excel(name = "球桌id")

    @TableField("desk_id")
    private Long deskId;

    /** 订单id(不一定有订单) */
    @Excel(name = "订单id(不一定有订单)")

    @TableField("order_id")
    private Long orderId;

    /** 图片地址 */
    @Excel(name = "图片地址")

    @TableField("file_path")
    private String filePath;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;



    @TableField("store_id")
    private Long storeId;







}
