INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES	('2000', 'SIM卡管理', '0', '0', 'sim', NULL, NULL, '', '1', '0', 'M', '0', '0', '', 'job', 'admin', sysdate(), 'admin', sysdate(), '');

-- 创建停机分析菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('停机分析', '2000', '1', 'stop', 'sim/stop/index', 1, 0, 'C', '0', '0', 'sim:stop:list', 'dict', 'admin', sysdate(), '', sysdate(), '');
-- 菜单sql
INSERT INTO sys_menu(menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES( '断网分析', '2000', '2', 'disconnect', 'sim/disconnect/index', 1, 0, 'C', '0', '0', 'sim:simNetStopList:list', 'dict', 'admin', sysdate(), '', sysdate(), '');
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('NB异常清单', '2000', '3', 'nb', 'sim/nb/index', 1, 0, 'C', '0', '0', 'sim:nb:list', 'chart', 'admin', sysdate(), '', sysdate(), 'NB异常清单菜单');
-- 菜单SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('欠费停机预警', '2000', '4', 'arrear', 'sim/arrear/index', 1, 0, 'C', '0', '0', 'sim:arrear:list', 'chart', 'admin', sysdate(), '', sysdate(), '欠费停机预警菜单');
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐到期预警', '2000', '5', 'pack_exp', 'sim/package/index', 1, 0, 'C', '0', '0', 'sim:pack_exp:list', 'dict', 'admin', sysdate(), '', sysdate(), '套餐到期预警菜单');
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐到期预警(港华)', '2000', '6', 'pack_exp_gh', 'sim/package_gh/index', 1, 0, 'C', '0', '0', 'sim:pack_exp_gh:list', 'chart', 'admin', sysdate(), '', sysdate(), '套餐到期预警菜单');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐长期不使用预警', '2000', '7', 'nonuse', 'sim/nonuse/index', 1, 0, 'C', '0', '0', 'sim:nonuse:list', 'dict', 'admin', sysdate(), '', sysdate(), '套餐长期不使用预警菜单');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES	('2009', '终端管理', '0', '0', 'terminal', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'server', 'admin', sysdate(), '', sysdate(), '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('终端地图', '2009', '1', 'terminalMap', 'terminal/terminalMap/index',  1, 0, 'C', '0', '0', 'terminal:terminalMap:list', 'dict', 'admin', sysdate(), '', sysdate(), '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('故障诊断', '2009', '2', 'diagnosis', 'terminal/diagnosis/index', 1, 0, 'C', '0', '0', 'terminal:diagnosis:list', 'bug', 'admin', sysdate(), '', sysdate(), '');



-- 建表语句
drop table if exists sim_nb_list;
CREATE TABLE sim_nb_list (
    prov_id bigint NOT NULL COMMENT '省份ID',
    prov_name varchar(100) NOT NULL COMMENT '省份名称',
    area_id int NOT NULL COMMENT '地市ID',
    area_name varchar(100) NOT NULL COMMENT '地市名称',
    cust_id bigint NOT NULL COMMENT '客户ID',
    cust_name varchar(100) NOT NULL COMMENT '客户名称',
    prod_inst_id bigint NOT NULL COMMENT '产品实例ID',
    acc_nbr bigint NOT NULL COMMENT '接入号码',
    main_offer_name varchar(100) NOT NULL COMMENT '主套餐名称',
    net_style varchar(4) NOT NULL COMMENT '网络制式',
    abnormal_reason varchar(100) NOT NULL COMMENT '异常原因',
    yyyymmdd varchar(20) NOT NULL COMMENT '数据日期'
) COMMENT='NB异常清单表';



-- 建表语句
drop table if exists arrear_list;
CREATE TABLE arrear_list (
    prov_id bigint NOT NULL COMMENT '省份ID',
    prov_name varchar(100) NOT NULL COMMENT '省份名称',
    area_id int NOT NULL COMMENT '地市id',
    area_name varchar(100) NOT NULL COMMENT '地市名称',
    cust_id bigint NOT NULL COMMENT '客户ID',
    cust_name varchar(100) NOT NULL COMMENT '客户名称',
    prod_inst_id bigint NOT NULL COMMENT '产品实例id',
    acc_nbr bigint NOT NULL COMMENT '用户号码',
    main_offer_name varchar(100) NOT NULL COMMENT '主套餐名称',
    net_style varchar(4) NOT NULL COMMENT '网络制式',
    stop_time varchar(100) NOT NULL COMMENT '拟停机时间',
    yyyymmdd varchar(20) NOT NULL COMMENT '数据日期'
) COMMENT = '欠费停机预警表';


-- SIM卡管理菜单生成


-- 停机分析表
drop table if exists sim_shutdown_list;
CREATE TABLE sim_shutdown_list (
  prov_id int(11) DEFAULT NULL COMMENT '省份id',
  prov_name varchar(100) DEFAULT NULL COMMENT '省份名称',
  area_id int(11) DEFAULT NULL COMMENT '地市id',
  area_name varchar(100) DEFAULT NULL COMMENT '地市名称',
  cust_id varchar(20) DEFAULT NULL COMMENT '客户id',
  cust_name varchar(100) DEFAULT NULL COMMENT '客户名称',
  prod_inst_id varchar(20) DEFAULT NULL COMMENT '产品实例id',
  acc_nbr varchar(20) DEFAULT NULL COMMENT '用户号码',
  main_offer_name varchar(100) DEFAULT NULL COMMENT '主套餐名称',
  net_style varchar(20) DEFAULT NULL COMMENT '制式',
  stop_reason varchar(100) DEFAULT NULL COMMENT '停机原因',
  yyyymmdd varchar(20) DEFAULT NULL COMMENT '数据日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停机清单表';

-- 套餐到期预警表
drop table if exists pack_exp_list;
CREATE TABLE pack_exp_list (
  prov_id bigint(20) DEFAULT NULL COMMENT '省份id',
  prov_name varchar(100) DEFAULT NULL COMMENT '省份名称',
  area_id int(11) DEFAULT NULL COMMENT '地市id',
  area_name varchar(100) DEFAULT NULL COMMENT '地市名称',
  cust_id bigint(20) DEFAULT NULL COMMENT '客户id',
  cust_name varchar(100) DEFAULT NULL COMMENT '客户名称',
  prod_inst_id bigint(20) DEFAULT NULL COMMENT '产品实例id',
  acc_nbr bigint(20) DEFAULT NULL COMMENT '用户号码',
  main_offer_name varchar(100) DEFAULT NULL COMMENT '主套餐名称',
  net_style varchar(10) DEFAULT NULL COMMENT '制式',
  main_exp_date varchar(20) DEFAULT NULL COMMENT '到期时间',
  yyyymmdd varchar(20) DEFAULT NULL COMMENT '日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐到期预警表';








