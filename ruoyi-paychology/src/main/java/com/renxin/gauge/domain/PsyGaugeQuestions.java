package com.renxin.gauge.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 心理测评问题对象 psy_gauge_questions
 * 
 * @author renxin
 * @date 2022-09-06
 */
@Data
public class PsyGaugeQuestions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 测评表单ID */
    @Excel(name = "测评表单ID")
    private Long gaugeId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /**
     * 选择类型
     * 0-单选
     * 1-多选
     */
    private Integer selectType;

    @Excel(name = "选择类型")
    private String selectTypeName;

    /** 序号 */
    @Excel(name = "序号")
    private Integer no;

    private Long userId;

    private String img;

    /**
     * 订单id
     */
    private Long orderId;

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
        this.selectTypeName = this.selectType == 0 ? "单选" : "多选";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gaugeId", getGaugeId())
            .append("title", getTitle())
            .append("no", getNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("img", getImg())
            .toString();
    }
}
