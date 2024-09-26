package com.ruoyi.billiard.domain.vo.miniappdomain;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-21:15
 * @className: HomeReportVoConsume
 */
@Data
public class HomeReportVoConsume implements Serializable {

    /** 总消费统计 */
    private HomeReportVoConsumeDetail total;

    /** 消费类型列表统计 */
    private List<HomeReportVoConsumeDetail> typeList = Lists.newArrayList();

}
