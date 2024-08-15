package com.renxin.course.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.course.domain.CourCourse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("课程订单")
public class CourseOrderVO {
    /** ID */
    private Long id;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /** 订单状态（0-创建 1-完成 2-关闭） */
    @ApiModelProperty("订单状态")
    private Integer status;

    /** 应付金额 */
    @ApiModelProperty("金额")
    private BigDecimal coursePrice;

    /** 下单时间 */
    @ApiModelProperty("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 课程编号 */
    @ApiModelProperty("课程编号")
    private String courseId;


    /** 支付金额 */
    @ApiModelProperty("课程总时长（秒）")
    private Integer totalDuration;

    @ApiModelProperty("课程章节数量")
    private Integer sectionNum;

    @ApiModelProperty("课程作者")
    private String author;

    @ApiModelProperty("课程标题")
    private String  courseTitle;

    @ApiModelProperty("课程列表展示图片")
    private String  courseIconUrl;

    @ApiModelProperty("课程章节完成数量")
    private String  finishNum;


}
