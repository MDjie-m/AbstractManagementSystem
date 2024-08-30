package com.renxin.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.course.domain.CourSection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  用户与其他对象的关联信息
 */
@Data
@NoArgsConstructor
public class RelateInfo implements Serializable
{
    //是否已购   0未购  1已购
    private Integer isBuy;

    //章节清单
    private List<CourSection> sectionList;

    /** 测评订单ID */
    private Long orderId;
    private String orderNo;
    /** 测评已回答的问题数量 */
    private Integer finishedNum;
    /** 测评是否已购买 */
    private Integer isCompleted;
    private Integer size;
    /** 测评次数 */
    private Integer num;

}