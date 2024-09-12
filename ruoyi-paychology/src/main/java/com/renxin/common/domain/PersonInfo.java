package com.renxin.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 个人信息
 *
 * @author renxin
 * @date 2024-09-11
 */
@Data
public class PersonInfo extends BaseEntity
{
    
    private Long id;

    /** 用户类型 1来访者 2咨询师 */
    private Integer userType;
    
    private String name;
    //头像
    private String avatar;


}
