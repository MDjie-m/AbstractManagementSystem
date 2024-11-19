-- SIM卡管理菜单生成
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
	('2000', 'SIM卡管理', '0', '1', 'sim', NULL, NULL, '', '1', '0', 'M', '0', '0', '', 'job', 'ry', '2024-10-30 13:56:28', 'admin', '2024-11-05 11:17:21', '');

-- 停机分析菜单
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
	('2004', '停机分析', '2000', '0', 'stop', 'sim/stop/index', NULL, '', '1', '0', 'C', '0', '0', 'sim:stop:list', 'dict', 'admin', '2024-11-05 11:21:13', 'admin', '2024-11-05 11:25:07', '');


-- 套餐到期预警
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
	('2009', '套餐到期预警', '2000', '2', 'pack_exp', 'sim/package/index', NULL, '', '1', '0', 'C', '0', '0', 'sim:pack_exp:list', 'date', 'admin', '2024-11-06 14:39:17', 'admin', '2024-11-06 15:01:59', '套餐到期预警菜单');

-- 停机分析表
CREATE TABLE `sim_shutdown_list` (
  `prov_id` int(11) DEFAULT NULL COMMENT '省份id',
  `prov_name` varchar(100) DEFAULT NULL COMMENT '省份名称',
  `area_id` int(11) DEFAULT NULL COMMENT '地市id',
  `area_name` varchar(100) DEFAULT NULL COMMENT '地市名称',
  `cust_id` varchar(20) DEFAULT NULL COMMENT '客户id',
  `cust_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `prod_inst_id` varchar(20) DEFAULT NULL COMMENT '产品实例id',
  `acc_nbr` varchar(20) DEFAULT NULL COMMENT '用户号码',
  `main_offer_name` varchar(100) DEFAULT NULL COMMENT '主套餐名称',
  `net_style` varchar(20) DEFAULT NULL COMMENT '制式',
  `stop_reason` varchar(100) DEFAULT NULL COMMENT '停机原因',
  `yyyymmdd` varchar(20) DEFAULT NULL COMMENT '数据日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停机清单表';

-- 套餐到期预警表
CREATE TABLE `pack_exp_list` (
  `prov_id` bigint(20) DEFAULT NULL COMMENT '省份id',
  `prov_name` varchar(100) DEFAULT NULL COMMENT '省份名称',
  `area_id` int(11) DEFAULT NULL COMMENT '地市id',
  `area_name` varchar(100) DEFAULT NULL COMMENT '地市名称',
  `cust_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `cust_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `prod_inst_id` bigint(20) DEFAULT NULL COMMENT '产品实例id',
  `acc_nbr` bigint(20) DEFAULT NULL COMMENT '用户号码',
  `main_offer_name` varchar(100) DEFAULT NULL COMMENT '主套餐名称',
  `net_style` varchar(10) DEFAULT NULL COMMENT '制式',
  `main_exp_date` varchar(20) DEFAULT NULL COMMENT '到期时间',
  `yyyymmdd` varchar(20) DEFAULT NULL COMMENT '日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐到期预警表';