package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    //创建表必备字段#####################

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //#####################

    /** 搜索值 */
    @JsonIgnore
    @TableField(exist = false)
    private String searchValue;

    /** 备注 */
    @TableField(exist = false)
    private String remark;

    /** 请求参数 */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    //分页插件的原理，请求中带了pageSize参数就会自动分页
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序：属性/字段,asc/desc  例：user_name,desc 、userName,desc")
    private String sortStr = "id,desc";


    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setSortStr(String sortStr) {
        if (StringUtils.isEmpty(sortStr)) {
            this.sortStr = "id,desc";
        } else {
            this.sortStr = sortStr;
        }
    }
}
