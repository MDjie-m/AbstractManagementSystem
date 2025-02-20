package com.ruoyi.common.core.page;

import com.ruoyi.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页数据
 *
 * @author ruoyi
 */
public class PageDomain
{
    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String []orderByColumn;

    /** 排序的方向desc或者asc */
    private String [] isAsc = new String [] {"asc"};

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public String getOrderBy()
    {
        if (orderByColumn==null||orderByColumn.length == 0)
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(StringUtils.concatenateArrays(orderByColumn,isAsc));
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String [] getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String [] orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String [] getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String []isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            for (String t : isAsc) {
                t = t.replaceAll("ascending", "asc")
                        .replaceAll("descending", "desc");
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable()
    {
        if (StringUtils.isNull(reasonable))
        {
            return Boolean.TRUE;
        }
        return reasonable;
    }

    public void setReasonable(Boolean reasonable)
    {
        this.reasonable = reasonable;
    }
}
