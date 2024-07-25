--  done
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for psy_consultant_account
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_account`;
CREATE TABLE `psy_consultant_account`  (
  `consultant_id` bigint NOT NULL COMMENT '账户ID',
  `account_number` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '数字账号ID',
  `withdrawal_password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '提现密码',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '1' COMMENT '1 正常 0 未激活',
  `account_amount` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '账户余额',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`consultant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_account_record
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_account_record`;
CREATE TABLE `psy_consultant_account_record`  (
  `record_id` bigint NOT NULL COMMENT '记录ID',
  `consultant_id` bigint NOT NULL COMMENT '账户ID',
  `status` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '1' COMMENT '1 成功 0 失败',
  `pay_type` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '0 分成 1 提现',
  `order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单ID ：提现订单ID',
  `account_amount` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '支付前账户余额',
  `pay_amount` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '支付金额',
  `pay_message` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '支付消息结果',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '账户明细流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_address
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_address`;
CREATE TABLE `psy_consultant_address`  (
  `address_id` bigint NOT NULL COMMENT '地址ID',
  `consultant_id` bigint NOT NULL COMMENT '咨询师ID',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '1 默认 0 未默认',
  `contact_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '联系人名',
  `contact_telephone` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系电话',
  `contact_address` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系地址',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '咨询师地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_debitcard
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_debitcard`;
CREATE TABLE `psy_consultant_debitcard`  (
  `card_number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '借记卡ID',
  `consultant_id` bigint NOT NULL COMMENT '咨询师ID',
  `card_banck` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '借记卡所属银行',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '1' COMMENT '1 正常 0 未激活',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`card_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '客户银行卡' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_referral_order
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_referral_order`;
CREATE TABLE `psy_consultant_referral_order`  (
  `referral_id` bigint NOT NULL COMMENT '转介ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `consultant_id` bigint NOT NULL COMMENT '转入咨询师ID',
  `from_consult_id` bigint NOT NULL COMMENT '来着咨询师ID',
  `referral_remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '备注',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '1' COMMENT '1 成功  0 处理中 4失败',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`referral_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '转介订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_withdrawal_order
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_withdrawal_order`;
CREATE TABLE `psy_consultant_withdrawal_order`  (
  `withdrawal_no` bigint NOT NULL COMMENT '提现ID',
  `consultant_id` bigint NOT NULL COMMENT '咨询师ID',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '1 成功  0 处理中 4失败',
  `withdrawal_amount` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '提现金额',
  `del_flag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`withdrawal_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '提现订单' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for psy_consultant_equity
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_equity`;
CREATE TABLE `psy_consultant_equity`  (
  `id` bigint NOT NULL COMMENT '权益主键',
  `consultant_id` bigint NULL DEFAULT NULL COMMENT '咨询师ID',
  `package_id` bigint NULL DEFAULT NULL COMMENT '套餐ID',
  `team_supervision` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '团督周期 第几期',
  `supervision_number` int NULL DEFAULT NULL COMMENT '套餐次数',
  `person_supervision` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个督套餐',
  `supervision_number2` int NULL DEFAULT NULL COMMENT '个督套餐次数',
  `person_experience` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '体验套餐',
  `supervision_number3` int NULL DEFAULT NULL COMMENT '体验套餐次数',
  `course_supervision` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程套餐',
  `supervision_number4` int NULL DEFAULT NULL COMMENT '课程套餐次数',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0:有效 1:失效',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '咨询师权益表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for psy_consultant_equity_verification
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_equity_verification`;
CREATE TABLE `psy_consultant_equity_verification`  (
  `id` bigint NOT NULL COMMENT '权益主键',
  `consultant_id` bigint NULL DEFAULT NULL COMMENT '咨询师ID',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `cycle_type` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1团督 2 个督 3体验  4课程',
  `cycle_number` int NULL DEFAULT NULL COMMENT '核销次数',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0:有效 1:失效',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '咨询师权益核销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for psy_consultant_order
-- ----------------------------
DROP TABLE IF EXISTS `psy_consultant_order`;
CREATE TABLE `psy_consultant_order` (
                                        `id` BIGINT(19) NOT NULL AUTO_INCREMENT,
                                        `order_no` VARCHAR(80) NOT NULL COMMENT '流水编号' COLLATE 'utf8mb4_0900_ai_ci',
                                        `server_id` VARCHAR(64) NOT NULL COMMENT '服务ID :团督ID、 体验ID、个督ID、课程ID,  套餐ID' COLLATE 'utf8mb4_0900_ai_ci',
                                        `server_type` INT(10) NOT NULL COMMENT '服务类型  1:团督   2:个督  3:体验  4:课程   5:咨询师成长套餐',
                                        `server_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '服务名称' COLLATE 'utf8mb4_0900_ai_ci',
                                        `status` CHAR(1) NULL DEFAULT '0' COMMENT '订单状态0-待付款 1-进行中 2-已完成 3-已取消' COLLATE 'utf8mb4_0900_ai_ci',
                                        `pay_type` CHAR(1) NULL DEFAULT '0' COMMENT '支付方式 1.现款支付   2.权益支付' COLLATE 'utf8mb4_0900_ai_ci',
                                        `pay_consultant_id` BIGINT(19) NOT NULL DEFAULT '0' COMMENT '支付咨询师id',
                                        `pay_consultant_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '支付咨询师名称' COLLATE 'utf8mb4_0900_ai_ci',
                                        `pay_amount` DECIMAL(10,2) NULL DEFAULT NULL COMMENT '实际支付费用',
                                        `pay_datetime` DATETIME NULL DEFAULT NULL COMMENT '付款时间',
                                        `pay_status` CHAR(1) NULL DEFAULT '1' COMMENT '支付状态 1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败' COLLATE 'utf8mb4_0900_ai_ci',
                                        `remark` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8mb4_0900_ai_ci',
                                        `del_flag` CHAR(1) NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）' COLLATE 'utf8mb4_0900_ai_ci',
                                        `create_by` VARCHAR(64) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8mb4_0900_ai_ci',
                                        `create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_by` VARCHAR(64) NULL DEFAULT NULL COMMENT '更新者' COLLATE 'utf8mb4_0900_ai_ci',
                                        `update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
                                        PRIMARY KEY (`id`) USING BTREE
)
    COMMENT='咨询师订单'
    COLLATE='utf8mb4_0900_ai_ci'
    ENGINE=InnoDB
    ROW_FORMAT=DYNAMIC
;




DROP TABLE IF EXISTS `psy_consultant_package`;
CREATE TABLE `psy_consultant_package`  (
  `package_id` bigint NOT NULL COMMENT '套餐主键',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `product_pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '套餐产品图片地址',
  `detail_pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详情图片地址',
  `cycle_type` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1团督 2 个督 3体验  4课程',
  `cycle_number` int NULL DEFAULT NULL COMMENT '套餐次数',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0:有效 1:失效',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '咨询师成长套餐表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `psy_consultant_supervision_member`;
CREATE TABLE `psy_consultant_supervision_member`  (
  `id` bigint NOT NULL COMMENT '团体成员主键',
  `supervision_id` bigint NULL DEFAULT NULL COMMENT '督导团员ID、个体督导ID、个体体验ID',
  `supervision_type` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1:团督  2.个体督导  3个体体验',
  `order_no` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `member_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成员ID(咨询师ID）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '督导成员表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `psy_consultant_supervision_member_record`;
CREATE TABLE `psy_consultant_supervision_member_record`  (
  `id` bigint NOT NULL COMMENT '团体成员主键',
  `task_id` bigint NULL DEFAULT NULL COMMENT '督导任务ID',
  `supervision_type` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1:团督  2.个体督导  3个体体验',
  `supervision_id` bigint NULL DEFAULT NULL COMMENT '督导团员ID、个体督导ID、个体体验ID',
  `member_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成员ID 、督导ID',
  `evaluate_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价标签',
  `evaluate_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '评价分数',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '督导笔记、成员评价 标签评价' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `psy_consultant_supervision_task`;
CREATE TABLE `psy_consultant_supervision_task`  (
  `task_id` bigint NOT NULL COMMENT '任务ID',
  `supervision_id` bigint NULL DEFAULT NULL COMMENT '团督ID 个督ID',
  `supervision_type` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1:团督  2.个体督导  3个人体验',
  `consultant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '咨询师ID',
  `task_datetime` datetime NULL DEFAULT NULL COMMENT '任务执行时间',
  `cycle` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '周期',
  `cycle_number` int NULL DEFAULT NULL COMMENT '周期次数',
  `theme` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主题',
  `supervision_record` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '督导记录',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '团队督导(组织)任务' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `psy_consultant_supervision_task_case`;
CREATE TABLE `psy_consultant_supervision_task_case`  (
  `task_id` bigint NOT NULL COMMENT '任务ID',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '督导对象ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '督导任务个案' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `psy_consultant_team_supervision`;
CREATE TABLE `psy_consultant_team_supervision`  (
  `id` bigint NOT NULL COMMENT '团督主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '团督标题',
  `logo_pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '团督logo',
  `detail_pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '团督明细地址',
  `cycle` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '周期',
  `cycle_number` int NULL DEFAULT NULL COMMENT '周期次数',
  `consultant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '带领者咨询师',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0:招募中 1:已开始 2:已结束 3:暂停',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '团督备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '团队督导(组织)' ROW_FORMAT = Dynamic;


CREATE TABLE `psy_consultant_schedule` (
                                           `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                           `order_id` BIGINT(19) NULL DEFAULT NULL COMMENT '订单号',
                                           `team_id` BIGINT(19) NULL DEFAULT NULL COMMENT '团督号',
                                           `work_id` BIGINT(19) NULL DEFAULT NULL COMMENT '排班',
                                           `schedule_type` INT(10) NOT NULL DEFAULT '0' COMMENT '任务类型  1.咨询服务   2.团督开课',
                                           `time_num` INT(10) NOT NULL DEFAULT '0' COMMENT '同一任务的第几次执行',
                                           `time_start` VARCHAR(10) NULL DEFAULT NULL COMMENT '开始时间' COLLATE 'utf8mb4_0900_ai_ci',
                                           `time_end` VARCHAR(10) NULL DEFAULT NULL COMMENT '结束时间' COLLATE 'utf8mb4_0900_ai_ci',
                                           `day` VARCHAR(20) NULL DEFAULT NULL COMMENT '天' COLLATE 'utf8mb4_0900_ai_ci',
                                           `week` VARCHAR(10) NULL DEFAULT NULL COMMENT '周' COLLATE 'utf8mb4_0900_ai_ci',
                                           `status` CHAR(1) NULL DEFAULT '0' COMMENT '状态（0待办  1已完成）' COLLATE 'utf8mb4_0900_ai_ci',
                                           `create_by` VARCHAR(64) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8mb4_0900_ai_ci',
                                           `create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
                                           `update_by` VARCHAR(64) NULL DEFAULT NULL COMMENT '更新者' COLLATE 'utf8mb4_0900_ai_ci',
                                           `update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
                                           `time` TINYINT(3) NULL DEFAULT NULL COMMENT '开始时间(整点)',
                                           `real_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '实际咨询开始时间' COLLATE 'utf8mb4_0900_ai_ci',
                                           `consult_id` BIGINT(19) NULL DEFAULT NULL COMMENT '督导师',
                                           PRIMARY KEY (`id`) USING BTREE
)
    COMMENT='咨询师排班任务表'
    COLLATE='utf8mb4_0900_ai_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=21
;
