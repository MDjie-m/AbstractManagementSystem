package com.ruoyi.module.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试对象 module_my_test
 * 
 * @author ruoyi
 * @date 2024-08-12
 */
public class ModuleMyTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测试ID */
    private Integer testId;

    public void setTestId(Integer testId) 
    {
        this.testId = testId;
    }

    public Integer getTestId() 
    {
        return testId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("testId", getTestId())
            .append("remark", getRemark())
            .toString();
    }
}
