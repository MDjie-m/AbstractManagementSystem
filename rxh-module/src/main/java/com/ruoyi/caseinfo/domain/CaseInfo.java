package com.ruoyi.caseinfo.domain;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 案件信息对象 rxh_case_info
 *
 * @author ysg
 * @date 2024-08-12
 */
@Data
public class CaseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案件ID */
    private Long caseId;

    /** 原告 */
    @Excel(name = "原告")
    private String plaintiff;

    /** 被告 */
    @Excel(name = "被告")
    private String defendant;

    /** 被告手机号 */
    @Excel(name = "被告手机号")
    private String defendantPhone;

    /** 法院 */
    @Excel(name = "法院")
    private String court;

    /** 案号 */
    @Excel(name = "案号")
    private String caseNo;

    /** 合作公司 */
    @Excel(name = "合作公司")
    private String cooperativeCompany;

    /** 承办律师A */
    @Excel(name = "承办律师A")
    private String undertakeLawyerA;

    /** 承办律师B */
    @Excel(name = "承办律师B")
    private String undertakeLawyerB;

    /** 律师助理A */
    @Excel(name = "律师助理A")
    private String paralegalA;

    /** 律师助理B */
    @Excel(name = "律师助理B")
    private String paralegalB;

    /** 调解员 */
    @Excel(name = "调解员")
    private String mediator;

    /** 一审文书 */
    @Excel(name = "一审文书")
    private String firstInstanceFileids;

    /** 二审文书 */
    @Excel(name = "二审文书")
    private String secondInstanceFileids;

    /** 首次执行文书 */
    @Excel(name = "首次执行文书")
    private String firstExecutionFileids;

    /** 恢复执行文书 */
    @Excel(name = "恢复执行文书")
    private String secondExecutionFileids;

    /** 认领状态（0未认领 1已认领） */
    @Excel(name = "认领状态", readConverterExp = "0=未认领,1=已认领")
    private String claimStatus;

    /** 是否退回（0未退回 1已退回） */
    @Excel(name = "是否退回", readConverterExp = "0=未退回,1=已退回")
    private String isReturn;

    /** 欠款总额 */
    @Excel(name = "欠款总额")
    private Long arrears;

    /** 已还款 */
    @Excel(name = "已还款")
    private Long arrearsYes;

    /** 未还款 */
    @Excel(name = "未还款")
    private Long arrearsNo;

    /** 案件状态（0未结 1待结 2已结） */
    @Excel(name = "案件状态", readConverterExp = "0=未结,1=待结,2=已结")
    private String caseStatus;

    /** 数据权限查看人 */
    @Excel(name = "数据权限查看人")
    private String dataRoleUserids;

}
