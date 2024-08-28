package com.renxin.psychology.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  
 */
@Data
public class QueryListByTypeReq extends BaseValObj implements Serializable
{
    
    //清单类型
    private String listType;
    
}

