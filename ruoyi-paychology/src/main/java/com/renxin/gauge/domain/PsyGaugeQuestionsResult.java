package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 心理测评问题结果对象 psy_gauge_questions_result
 * 
 * @author renxin
 * @date 2022-09-10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PsyGaugeQuestionsResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "用户ID")
    private Long userId;

    @Excel(name = "量表")
    @NotNull(message = "量表id不能为空")
    private Long gaugeId;

    @Excel(name = "问题")
    @NotNull(message = "问题id不能为空")
    private Integer questionsId;

    @Excel(name = "问题选项集合")
    @NotNull(message = "问题选项id集合不能为空")
    private List<Integer> questionsOptionsIdList;

    private Long questionsOptionsId;

    private Integer questionsLat;

    @Excel(name = "纬度")
    private String lat;

    /** 得分 */
    @Excel(name = "得分")
    private Integer score;

    /**
     * 订单id
     */
    private Long orderId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("gaugeId", getGaugeId())
            .append("questionsId", getQuestionsId())
            .append("questionsOptionsId", getQuestionsOptionsId())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .append("lat", getLat())
            .append("questionsLat", getQuestionsLat())
            .toString();
    }
}
