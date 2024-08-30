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

}