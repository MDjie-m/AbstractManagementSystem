RENAME TABLE `ruoyi-nbcio`.`courses` TO `ruoyi-nbcio`.`yk_courses`;

RENAME TABLE `ruoyi-nbcio`.`courses_file` TO `ruoyi-nbcio`.`yk_courses_file`;

RENAME TABLE `ruoyi-nbcio`.`courses_menu` TO `ruoyi-nbcio`.`yk_courses_menu`;

ALTER TABLE `ruoyi-nbcio`.`yk_courses`
    CHANGE COLUMN `create_date` `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `course_cover`,
    CHANGE COLUMN `update_date` `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `create_time`;

ALTER TABLE `ruoyi-nbcio`.`yk_courses_file`
    CHANGE COLUMN `create_date` `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `menu_id`,
    CHANGE COLUMN `update_date` `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `create_time`;

ALTER TABLE `ruoyi-nbcio`.`yk_courses_menu`
    CHANGE COLUMN `create_date` `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `sort`,
    CHANGE COLUMN `update_date` `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `create_time`;

INSERT INTO `ruoyi-nbcio`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query_param`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1850011722318680066, '课程中心', 0, 2, 'course', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'education', 'admin', '2024-10-26 11:09:01', 'admin', '2024-10-27 10:26:58', '');
INSERT INTO `ruoyi-nbcio`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query_param`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1850013495884320770, '课程管理', 1850011722318680066, 2, 'index', 'youke/courses/index', NULL, 1, 0, 'C', '0', '0', 'workflow:courses:list', 'form', 'admin', '2024-10-26 11:16:04', 'admin', '2024-10-27 10:27:24', '');
INSERT INTO `ruoyi-nbcio`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query_param`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1850361963702239234, '课程列表', 1850011722318680066, 1, 'list', 'youke/courses/list', NULL, 1, 0, 'C', '0', '0', 'workflow:courses:list', 'documentation', 'admin', '2024-10-27 10:20:45', 'admin', '2024-10-27 10:27:06', '');
