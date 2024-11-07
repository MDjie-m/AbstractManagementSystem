package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 督导成员对象 psy_consultant_supervision_member
 * 
 * @author renxin
 * @date 2024-06-26
 */
@TableName("psy_consultant_supervision_member")
@Data
public class PsyConsultantSupervisionMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团体成员主键 */
    private Long id;

    /** 督导师id */
    @Excel(name = "督导师id")
    private Long supervisionId;

    /** 团队id */
    @Excel(name = "团队id")
    private Long teamSupervisionId;

    /** 团体类型  1.团体督导  2.1V2督导  3.读书会  4.活动小组 */
    private Integer teamType;
    private List<Integer> teamTypeList;
    
    /** 成员ID(咨询师ID） */
    @Excel(name = "成员ID(咨询师ID）")
    private Long memberId;

    /** 成员类型  1:正式成员   2:观摩成员 */
    @Excel(name = "成员类型  1:正式成员   2:观摩成员")
    private Integer memberType;
   
    
    //成员用户类型   1来访者   2咨询师
    private Integer memberUserType;
    
    /** 订单ID */
    @Excel(name = "订单ID")
    private String orderNo;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
    
    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    private String delFlag;

    /** 成员姓名 */
    private String memberName;
    /** 成员级别 */
    private Integer memberLevel;
    /** 成员头像 */
    private String memberAvatar;
    /** 成员邮箱 */
    private String memberEmail;
    /** 成员手机号 */
    private String memberPhonenumber;
    /** 成员性别 */
    private String memberSex;

  
}
