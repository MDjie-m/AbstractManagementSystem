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

ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `logo_pic_url` `logo_pic_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '团督head图片' COLLATE 'utf8mb4_0900_ai_ci' AFTER `period_no`,
    CHANGE COLUMN `detail_pic_url` `detail_pic_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '小组特色/团督详情  (逗号分隔多个url)' COLLATE 'utf8mb4_0900_ai_ci' AFTER `logo_pic_url`;

ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `logo_pic_url` `head_pic_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '团督head图片' COLLATE 'utf8mb4_0900_ai_ci' AFTER `period_no`,
    CHANGE COLUMN `detail_pic_url` `special_pic_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '小组特色' COLLATE 'utf8mb4_0900_ai_ci' AFTER `head_pic_url`,
    ADD COLUMN `register_notice_pic_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '报名须知(图片url)' AFTER `special_pic_url`;

ALTER TABLE `psy_consult`
    CHANGE COLUMN `user_id` `user_id` BIGINT(19) NULL COMMENT '登录名' AFTER `id`,
    CHANGE COLUMN `team_type` `team_type` CHAR(4) NULL DEFAULT '' COMMENT '团督类型: 1 个督 2团督 ' COLLATE 'utf8mb4_0900_ai_ci' AFTER `user_id`,
    CHANGE COLUMN `user_name` `user_name` VARCHAR(255) NULL COMMENT '登录名' COLLATE 'utf8mb4_general_ci' AFTER `team_type`,
    CHANGE COLUMN `nick_name` `nick_name` VARCHAR(100) NULL COMMENT '姓名' COLLATE 'utf8mb4_0900_ai_ci' AFTER `experience`;


ALTER TABLE `psy_consult_partner`
    CHANGE COLUMN `user_id` `user_id` INT(10) NULL COMMENT '登录名' AFTER `id`;

ALTER TABLE `psy_consult_partner`
    CHANGE COLUMN `sex` `sex` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT '用户性别（1男 2女 ）' AFTER `remark`;

ALTER TABLE `psy_consult_partner_item`
    CHANGE COLUMN `p_id` `p_id` BIGINT(19) NOT NULL COMMENT '申请单id' AFTER `id`,
    CHANGE COLUMN `type` `type` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '申请类型1-7  1学校名称,2资质类型,3证书名称,4培训名称,5咨询类型,6督导类型,7其他经历' AFTER `p_id`;

ALTER TABLE `psy_consult_partner_item`
    CHANGE COLUMN `param1` `param1` VARCHAR(10240) NULL DEFAULT '' COMMENT '1学校名称,2资质类型,3证书名称,4培训名称,5咨询类型,6督导类型,7其他经历' COLLATE 'utf8mb4_general_ci' AFTER `num`;


ALTER TABLE `psy_consult_server_config`
    ADD COLUMN `level` INT NULL COMMENT '咨询师级别 1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.督导师' AFTER `end`,
    ADD COLUMN `service_object` INT NULL COMMENT '服务对象  1来访者  2咨询师' AFTER `level`;


ALTER TABLE `psy_consult`
    DROP COLUMN `user_id`;

ALTER TABLE `psy_consult_partner`
    DROP COLUMN `user_id`;

ALTER TABLE `psy_consultant_schedule`
    ADD COLUMN `schedule_type` INT NOT NULL DEFAULT 0 COMMENT '任务类型  1.团督开课   2.咨询服务' AFTER `team_id`;

ALTER TABLE `psy_consult_server_config`
    CHANGE COLUMN `service_object` `service_object` INT(10) NULL DEFAULT NULL COMMENT '服务对象   1来访者  2咨询师(督导)   3咨询师(体验)' AFTER `level`;


ALTER TABLE `psy_consult_server_config`
    CHANGE COLUMN `mode` `mode` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT '咨询形式   1语音咨询    2视频咨询   3面对面咨询' AFTER `id`;


ALTER TABLE `psy_consult`
    CHANGE COLUMN `server_to` `server_to` VARCHAR(255) NULL DEFAULT NULL COMMENT '服务对象   1来访者  2咨询师(督导)   3咨询师(体验)' COLLATE 'utf8mb4_0900_ai_ci' AFTER `level`,
    CHANGE COLUMN `work_num` `work_num` INT(10) NOT NULL DEFAULT '0' COMMENT '咨询人数' AFTER `detail`;

ALTER TABLE `psy_consult_server_config`
    CHANGE COLUMN `service_object` `service_object` VARCHAR(50) NULL DEFAULT NULL COMMENT '服务对象   1来访者  2咨询师(督导)   3咨询师(体验)' AFTER `level`;


ALTER TABLE `psy_consult`
    CHANGE COLUMN `server_to` `service_object` VARCHAR(255) NULL DEFAULT NULL COMMENT '服务对象   1来访者  2咨询师(督导)   3咨询师(体验)' COLLATE 'utf8mb4_0900_ai_ci' AFTER `level`;


/*原服务清空*/
UPDATE psy_consult_server_config SET del_flag = 1 WHERE LEVEL IS null;
DELETE FROM  psy_consult_serve;
update psy_consult set SERVE = 0 ;


ALTER TABLE `psy_consult_serve`
    ADD COLUMN `relation_id` BIGINT(19) NOT NULL AUTO_INCREMENT FIRST,
    ADD INDEX `索引 2` (`relation_id`);


ALTER TABLE `psy_consultant_schedule`
    CHANGE COLUMN `order_id` `order_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '订单号' AFTER `id`;

ALTER TABLE `psy_consultant_schedule`
    CHANGE COLUMN `time_num` `time_num` INT(10) NULL DEFAULT '0' COMMENT '同一任务的第几次执行' AFTER `schedule_type`;
