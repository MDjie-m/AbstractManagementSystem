-- done
ALTER TABLE psy_consult_work ADD COLUMN consult_type CHAR(4) NOT NULL DEFAULT '1' COMMENT '1 咨询 2倾听 3督导'  AFTER consult_id;
update psy_consult_work set consult_type = '1' where consult_type is null;

ALTER TABLE psy_consult_order ADD COLUMN consult_type CHAR(4) NOT NULL DEFAULT '1' COMMENT '1 咨询 2倾听 3督导' AFTER nick_name;
ALTER TABLE psy_consult ADD COLUMN team_type CHAR(4) NOT NULL DEFAULT '' COMMENT '团督类型: 1 个督 2团督 '  AFTER user_id;

ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `id` `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '团督主键' FIRST;


ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `supervision_id` `supervision_id` BIGINT(19) NULL DEFAULT NULL COMMENT '督导团员ID、个体督导ID、个人体验ID' AFTER `id`,
    CHANGE COLUMN `supervision_type` `supervision_type` CHAR(4) NULL DEFAULT NULL COMMENT '1:团督  2.个体督导  3个人体验' COLLATE 'utf8mb4_0900_ai_ci' AFTER `supervision_id`;

ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `supervision_id` `supervision_id` BIGINT(19) NULL DEFAULT NULL COMMENT '督导师ID、体验师ID' AFTER `id`;

ALTER TABLE `psy_consultant_supervision_member`
    ADD COLUMN `team_supervision_id` INT NULL COMMENT '团队id' AFTER `update_time`;

ALTER TABLE `psy_consultant_supervision_member`
    COMMENT='督导成员表(服务对象)';

ALTER TABLE `psy_consultant_team_supervision`
    ADD COLUMN `period_no` INT NULL DEFAULT NULL COMMENT '当前期数' AFTER `title`,
    ADD COLUMN `week_day` INT NULL COMMENT '星期几开课' AFTER `remark`,
    ADD COLUMN `lecture_start_time` VARCHAR(50) NULL DEFAULT NULL COMMENT '开课日开始时间' AFTER `week_day`,
    ADD COLUMN `lecture_end_time` VARCHAR(50) NULL DEFAULT NULL COMMENT '开课日结束时间' AFTER `lecture_start_time`,
    ADD COLUMN `first_lecture_date` VARCHAR(50) NULL DEFAULT NULL COMMENT '初次开课日期' AFTER `lecture_end_time`,
    ADD COLUMN `max_num_people` VARCHAR(50) NULL DEFAULT NULL COMMENT '最大团队人数' AFTER `first_lecture_date`,
    ADD COLUMN `price` VARCHAR(50) NULL DEFAULT NULL COMMENT '入会价格' AFTER `max_num_people`;


ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `price` `price` DECIMAL(20,2) NULL DEFAULT NULL COMMENT '入会价格' COLLATE 'utf8mb4_0900_ai_ci' AFTER `max_num_people`;

ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `consultant_id` `consultant_id` VARCHAR(255) NULL DEFAULT NULL COMMENT '督导师id' COLLATE 'utf8mb4_0900_ai_ci' AFTER `cycle_number`,
    ADD COLUMN `team_type` INT NULL DEFAULT NULL COMMENT '督导类型  1.团体督导  2.个体督导  3.个人体验' AFTER `consultant_id`;

ALTER TABLE `psy_consult`
    ADD COLUMN `level` INT NULL DEFAULT NULL COMMENT '级别  1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.督导师' AFTER `sex`;


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `create_by`, `create_time`) 
VALUES (1001, '咨询师流派', 'consult_genre', 'admin', '2024-01-25 02:06:11');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1001, 1, '精神分析心理动力学', '精神分析心理动力学', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1002, 2, '认知学派', '认知学派', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1003, 3, '精神分析流派', '精神分析流派', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1004, 4, '萨提亚家庭治疗', '萨提亚家庭治疗', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1005, 5, '儿童青少年游戏治疗', '儿童青少年游戏治疗', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1006, 6, '后现代心理学', '后现代心理学', 'consult_genre', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1002, '咨询师擅长领域', 'consult_way', '0', 'admin', '2024-01-25 02:06:11', '', NULL, NULL);

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1101, 1, '失恋分手', '失恋分手', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1102, 2, '孕产心理', '孕产心理', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1103, 3, '离异/单亲', '离异/单亲', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1104, 4, '亲子冲突', '亲子冲突', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1105, 5, '创伤修复', '创伤修复', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1106, 6, '原生家庭关系', '原生家庭关系', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1107, 7, '育儿', '育儿', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1108, 8, '成长', '成长', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1109, 9, '无意义感', '无意义感', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1110, 10, '男性成长', '男性成长', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);


ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `id` `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '团体成员主键' FIRST,
    ADD PRIMARY KEY (`id`);


ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `team_supervision_id` `team_supervision_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '团队id' AFTER `supervision_id`;


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1003, '团队督导类型', 'supervision_type', '0', 'admin', '2024-01-25 02:06:11', '', NULL, NULL);
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1004, '团队督导状态', 'supervision_status', '0', 'admin', '2024-01-25 02:06:11', '', NULL, NULL);

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (901, 1, '团体督导', '1', 'supervision_type', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (902, 2, '个体督导', '2', 'supervision_type', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (903, 3, '个人体验', '3', 'supervision_type', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (911, 1, '招募中', '0', 'supervision_status', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (912, 2, '已开始', '1', 'supervision_status', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (913, 3, '已结束', '2', 'supervision_status', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (914, 4, '暂停', '3', 'supervision_status', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1005, '星期', 'week_day', '0', 'admin', '2024-01-25 02:06:11', '', NULL, NULL);


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1006, '咨询师级别', 'consult_level', '0', 'admin', '2024-01-25 02:06:11', '', NULL, NULL);

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (931, 1, '学员咨询师', '1', 'consult_level', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (932, 2, '初级咨询师', '2', 'consult_level', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (933, 3, '中级咨询师', '3', 'consult_level', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (934, 4, '高级咨询师', '4', 'consult_level', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (935, 5, '督导师', '5', 'consult_level', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);


ALTER TABLE `psy_consultant_order`
    CHANGE COLUMN `pay_consultant_id` `pay_consultant_id` INT(10) NOT NULL COMMENT '支付咨询师id' AFTER `pay_type`,
    CHANGE COLUMN `pay_consultant_name` `pay_consultant_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '支付咨询师名称' COLLATE 'utf8mb4_0900_ai_ci' AFTER `pay_consultant_id`;


ALTER TABLE `psy_consultant_order`
    ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT FIRST,
    ADD PRIMARY KEY (`id`);

ALTER TABLE `psy_consult`
    ADD COLUMN `server_to` VARCHAR(255) NULL DEFAULT NULL COMMENT '服务对象   user:访客   consultSup:咨询师(督导)   consultExp:咨询师(体验)' AFTER `level`;

ALTER TABLE `psy_order_pay`
    CHANGE COLUMN `order_id` `order_id` INT(10) NULL COMMENT '订单号' AFTER `id`,
    ADD COLUMN `consultant_order_id` INT NULL COMMENT '咨询师订单号' AFTER `order_id`;

ALTER TABLE `psy_consultant_package`
    ADD COLUMN `team_sup_num` INT NULL COMMENT '团队督导次数' AFTER `detail_pic_url`,
    ADD COLUMN `person_sup_num` INT NULL COMMENT '个人督导次数' AFTER `team_sup_num`,
    ADD COLUMN `person_exp_num` INT NULL COMMENT '个人体验次数' AFTER `person_sup_num`,
    ADD COLUMN `course_num` INT NULL COMMENT '课程次数' AFTER `person_exp_num`,
    DROP COLUMN `cycle_type`,
    DROP COLUMN `cycle_number`;

ALTER TABLE `psy_consultant_order`
    ADD COLUMN `work_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '排班id (server_type =2 or 3时需要)' AFTER `pay_status`,
    ADD COLUMN `time` VARCHAR(50) NULL DEFAULT NULL COMMENT '预约时间 (server_type =2 or 3时需要)' AFTER `work_id`;

ALTER TABLE `cour_user_course_section`
    ADD COLUMN `consultant_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '咨询师ID' AFTER `user_id`;

ALTER TABLE `psy_consultant_package`
    ADD COLUMN `price` DECIMAL(20,6) NULL DEFAULT NULL COMMENT '套餐价格' AFTER `course_num`;

ALTER TABLE `psy_consultant_package`
    CHANGE COLUMN `package_id` `package_id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '套餐主键' FIRST;


ALTER TABLE `cour_user_course_section`
    CHANGE COLUMN `consultant_id` `user_type` INT NOT NULL DEFAULT (3) COMMENT '用户类型  1管理端  2咨询者  3来访者' COLLATE 'utf8mb4_general_ci' AFTER `user_id`;
ALTER TABLE `cour_user_course_section`
    CHANGE COLUMN `user_id` `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户ID' AFTER `id`;
UPDATE cour_user_course_section SET user_type = 3;

ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `order_no` `order_no` VARCHAR(50) NULL DEFAULT NULL COMMENT '订单ID' AFTER `supervision_type`;
