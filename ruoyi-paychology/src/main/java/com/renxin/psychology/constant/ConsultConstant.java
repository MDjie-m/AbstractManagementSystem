package com.renxin.psychology.constant;

public class ConsultConstant {
    public static final String MODULE_CONSULT= "consult";

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 4-已关闭*/
    public static final String CONSULT_ORDER_STATUE_CREATED = "0";
    public static final String CONSULT_ORDER_STATUE_PENDING = "1";
    public static final String CONSULT_ORDER_STATUE_FINISHED = "2";
    public static final String CONSULT_ORDER_STATUE_CANCELED = "3";
    public static final String CONSULT_ORDER_STATUE_CLOSED = "4";

    // 1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败
    public static final String PAY_STATUE_PENDING = "1";
    public static final String PAY_STATUE_PAID = "2";
    public static final String PAY_STATUE_REFUNDING = "3";
    public static final String PAY_STATUE_PART_REFUNDING= "4";
    public static final String PAY_STATUE_REFUN= "5";
    public static final String PAY_STATUE_REFUN_FAIL= "6";

    public static final String ONSULT_ORDER_ITEM_CREATED = "0";
    public static final String ONSULT_ORDER_ITEM_FINISHED = "1";

    // 咨询形式 语音咨询、视频咨询、当面咨询
    public static final Integer CONSULT_MODE_SOUND= 1;
    public static final Integer CONSULT_MODE_VOICE= 2;
    public static final Integer CONSULT_MODE_FACE= 3;

    // 服务类型 单次咨询 套餐咨询
    public static final Integer CONSULT_TYPE_ONCE = 1;
    public static final Integer CONSULT_TYPE_PACKAGE = 2;

    // 限购 0-不限制 1-限制
    public static final Integer CONSULT_NO_LIMIT = 0;
    public static final Integer CONSULT_LIMIT = 1;

    // 入驻申请类型
    public static final Integer PARTNER_TYPE_HZ = 1;
    public static final String PARTNER_TYPE_HZ_STR = "合作型";
    public static final Integer PARTNER_TYPE_TT = 2;
    public static final String PARTNER_TYPE_TT_STR = "签约型（团体督导）";
    public static final Integer PARTNER_TYPE_GT = 3;
    public static final String PARTNER_TYPE_GT_STR = "签约型（个体督导）";
    public static final Integer PARTNER_TYPE_QY = 4;
    public static final String PARTNER_TYPE_QY_STR = "签约型";

    // 入驻审批状态
    public static final String PARTNER_STATUS_0 = "0";
    public static final String PARTNER_STATUS_0_STR = "草稿";
    public static final String PARTNER_STATUS_1 = "1";
    public static final String PARTNER_STATUS_1_STR = "入驻审核中";
    public static final String PARTNER_STATUS_2 = "2";
    public static final String PARTNER_STATUS_2_STR = "审核通过";
    public static final String PARTNER_STATUS_3 = "3";
    public static final String PARTNER_STATUS_3_STR = "修改审核中";
    public static final String PARTNER_STATUS_4 = "4";
    public static final String PARTNER_STATUS_4_STR = "审核驳回";

    public static final String PARTNER_STATUS_5 = "5";
    public static final String PARTNER_STATUS_5_STR = "未实人认证";


    // 合同状态 1-待签署,2-已撤销,3-逾期未签署,4-待生效,5-生效中,6-已失效,7-终止
    public static final String CONTRACT_STATUS_1 = "1";
    public static final String CONTRACT_STATUS_2 = "2";
    public static final String CONTRACT_STATUS_3 = "3";
    public static final String CONTRACT_STATUS_4 = "4";
    public static final String CONTRACT_STATUS_5 = "5";
    public static final String CONTRACT_STATUS_6 = "6";
    public static final String CONTRACT_STATUS_7 = "7";

    /** 咨询类型 1 咨询 2倾听 3督导 */
    public static final String CONSULT_TYPE_1 = "1";
    public static final String CONSULT_TYPE_2 = "2";
    public static final String CONSULT_TYPE_3 = "3";

    /**  团队督导状态  0:招募中 1:已开始 2:已结束 3:暂停  */
    public static final Integer TEAM_STATUS_RECRUITING_0 = 0;
    public static final Integer TEAM_STATUS_START_1 = 1;
    public static final Integer TEAM_STATUS_END_2 = 2;
    public static final Integer TEAM_STATUS_PAUSE_3 = 3;

}
