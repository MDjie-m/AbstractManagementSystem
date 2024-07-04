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

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1007, 1, '失恋分手', '失恋分手', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1008, 2, '孕产心理', '孕产心理', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1009, 3, '离异/单亲', '离异/单亲', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1010, 4, '亲子冲突', '亲子冲突', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1011, 5, '创伤修复', '创伤修复', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1012, 6, '原生家庭关系', '原生家庭关系', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1013, 7, '育儿', '育儿', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1014, 8, '成长', '成长', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1015, 9, '无意义感', '无意义感', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1016, 10, '男性成长', '男性成长', 'consult_way', NULL, 'default', 'N', '0', 'admin', '2024-03-06 18:02:17', '', NULL, NULL);


ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `id` `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '团体成员主键' FIRST,
    ADD PRIMARY KEY (`id`);


ALTER TABLE `psy_consultant_supervision_member`
    CHANGE COLUMN `team_supervision_id` `team_supervision_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '团队id' AFTER `supervision_id`;
