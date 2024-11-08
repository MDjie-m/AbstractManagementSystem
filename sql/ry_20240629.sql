-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment '部门id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  dept_name         varchar(30)     default ''                 comment '部门名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  phone             varchar(11)     default null               comment '联系电话',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 comment = '部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(100,  0,   '0',          '若依科技',   0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(101,  100, '0,100',      '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(102,  100, '0,100',      '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(103,  101, '0,100,101',  '研发部门',   1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(104,  101, '0,100,101',  '市场部门',   2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(105,  101, '0,100,101',  '测试部门',   3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(106,  101, '0,100,101',  '财务部门',   4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(107,  101, '0,100,101',  '运维部门',   5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(108,  102, '0,100,102',  '市场部门',   1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(109,  102, '0,100,102',  '财务部门',   2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);

insert into sys_dept values(8110000, 0, '0' , '北京市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110100, 8110000, '0,8110000' , '北京市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110101, 8110100, '0,8110100,8110000' , '东城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110102, 8110100, '0,8110100,8110000' , '西城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110105, 8110100, '0,8110100,8110000' , '朝阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110106, 8110100, '0,8110100,8110000' , '丰台区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110107, 8110100, '0,8110100,8110000' , '石景山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110108, 8110100, '0,8110100,8110000' , '海淀区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110109, 8110100, '0,8110100,8110000' , '门头沟区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110111, 8110100, '0,8110100,8110000' , '房山区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110112, 8110100, '0,8110100,8110000' , '通州区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110113, 8110100, '0,8110100,8110000' , '顺义区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110114, 8110100, '0,8110100,8110000' , '昌平区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110115, 8110100, '0,8110100,8110000' , '大兴区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110116, 8110100, '0,8110100,8110000' , '怀柔区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110117, 8110100, '0,8110100,8110000' , '平谷区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110200, 8110000, '0,8110000' , '县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110228, 8110200, '0,8110200,8110000' , '密云县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8110229, 8110200, '0,8110200,8110000' , '延庆县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120000, 0, '0' , '天津市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120100, 8120000, '0,8120000' , '天津市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120101, 8120100, '0,8120100,8120000' , '和平区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120102, 8120100, '0,8120100,8120000' , '河东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120103, 8120100, '0,8120100,8120000' , '河西区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120104, 8120100, '0,8120100,8120000' , '南开区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120105, 8120100, '0,8120100,8120000' , '河北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120106, 8120100, '0,8120100,8120000' , '红桥区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120110, 8120100, '0,8120100,8120000' , '东丽区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120111, 8120100, '0,8120100,8120000' , '西青区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120112, 8120100, '0,8120100,8120000' , '津南区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120113, 8120100, '0,8120100,8120000' , '北辰区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120114, 8120100, '0,8120100,8120000' , '武清区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120115, 8120100, '0,8120100,8120000' , '宝坻区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120116, 8120100, '0,8120100,8120000' , '滨海新区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120200, 8120000, '0,8120000' , '县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120221, 8120200, '0,8120200,8120000' , '宁河县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120223, 8120200, '0,8120200,8120000' , '静海县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8120225, 8120200, '0,8120200,8120000' , '蓟县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130000, 0, '0' , '河北省', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130100, 8130000, '0,8130000' , '石家庄市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130101, 8130100, '0,8130100,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130102, 8130100, '0,8130100,8130000' , '长安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130103, 8130100, '0,8130100,8130000' , '桥东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130104, 8130100, '0,8130100,8130000' , '桥西区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130105, 8130100, '0,8130100,8130000' , '新华区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130107, 8130100, '0,8130100,8130000' , '井陉矿区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130108, 8130100, '0,8130100,8130000' , '裕华区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130121, 8130100, '0,8130100,8130000' , '井陉县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130123, 8130100, '0,8130100,8130000' , '正定县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130124, 8130100, '0,8130100,8130000' , '栾城县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130125, 8130100, '0,8130100,8130000' , '行唐县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130126, 8130100, '0,8130100,8130000' , '灵寿县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130127, 8130100, '0,8130100,8130000' , '高邑县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130128, 8130100, '0,8130100,8130000' , '深泽县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130129, 8130100, '0,8130100,8130000' , '赞皇县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130130, 8130100, '0,8130100,8130000' , '无极县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130131, 8130100, '0,8130100,8130000' , '平山县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130132, 8130100, '0,8130100,8130000' , '元氏县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130133, 8130100, '0,8130100,8130000' , '赵县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130142, 8130100, '0,8130100,8130000' , '开发区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130181, 8130100, '0,8130100,8130000' , '辛集市', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130182, 8130100, '0,8130100,8130000' , '藁城市', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130183, 8130100, '0,8130100,8130000' , '晋州市', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130184, 8130100, '0,8130100,8130000' , '新乐市', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130185, 8130100, '0,8130100,8130000' , '鹿泉市', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130186, 8130100, '0,8130100,8130000' , '桥东区', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130187, 8130100, '0,8130100,8130000' , '西环区', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130200, 8130000, '0,8130000' , '唐山市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130201, 8130200, '0,8130200,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130202, 8130200, '0,8130200,8130000' , '路南区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130203, 8130200, '0,8130200,8130000' , '路北区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130204, 8130200, '0,8130200,8130000' , '古冶区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130205, 8130200, '0,8130200,8130000' , '开平区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130207, 8130200, '0,8130200,8130000' , '丰南区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130208, 8130200, '0,8130200,8130000' , '丰润区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130209, 8130200, '0,8130200,8130000' , '曹妃甸区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130223, 8130200, '0,8130200,8130000' , '滦县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130224, 8130200, '0,8130200,8130000' , '滦南县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130225, 8130200, '0,8130200,8130000' , '乐亭县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130227, 8130200, '0,8130200,8130000' , '迁西县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130229, 8130200, '0,8130200,8130000' , '玉田县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130240, 8130200, '0,8130200,8130000' , '开发区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130281, 8130200, '0,8130200,8130000' , '遵化市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130283, 8130200, '0,8130200,8130000' , '迁安市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130300, 8130000, '0,8130000' , '秦皇岛市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130301, 8130300, '0,8130300,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130302, 8130300, '0,8130300,8130000' , '海港区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130303, 8130300, '0,8130300,8130000' , '山海关区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130304, 8130300, '0,8130300,8130000' , '北戴河区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130321, 8130300, '0,8130300,8130000' , '青龙满族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130322, 8130300, '0,8130300,8130000' , '昌黎县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130323, 8130300, '0,8130300,8130000' , '抚宁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130324, 8130300, '0,8130300,8130000' , '卢龙县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130331, 8130300, '0,8130300,8130000' , '开发区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130333, 8130300, '0,8130300,8130000' , '南港区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130334, 8130300, '0,8130300,8130000' , '杜庄', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130335, 8130300, '0,8130300,8130000' , '北港区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130336, 8130300, '0,8130300,8130000' , '中港区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130398, 8130300, '0,8130300,8130000' , '北戴河新区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130400, 8130000, '0,8130000' , '邯郸市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130401, 8130400, '0,8130400,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130402, 8130400, '0,8130400,8130000' , '邯山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130403, 8130400, '0,8130400,8130000' , '丛台区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130404, 8130400, '0,8130400,8130000' , '复兴区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130406, 8130400, '0,8130400,8130000' , '峰峰矿区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130421, 8130400, '0,8130400,8130000' , '开发区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130423, 8130400, '0,8130400,8130000' , '临漳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130424, 8130400, '0,8130400,8130000' , '成安县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130425, 8130400, '0,8130400,8130000' , '大名县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130426, 8130400, '0,8130400,8130000' , '涉县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130427, 8130400, '0,8130400,8130000' , '磁县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130428, 8130400, '0,8130400,8130000' , '肥乡县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130429, 8130400, '0,8130400,8130000' , '永年县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130430, 8130400, '0,8130400,8130000' , '邱县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130431, 8130400, '0,8130400,8130000' , '鸡泽县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130432, 8130400, '0,8130400,8130000' , '广平县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130433, 8130400, '0,8130400,8130000' , '馆陶县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130434, 8130400, '0,8130400,8130000' , '魏县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130435, 8130400, '0,8130400,8130000' , '曲周县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130441, 8130400, '0,8130400,8130000' , '南区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130442, 8130400, '0,8130400,8130000' , '西区', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130443, 8130400, '0,8130400,8130000' , '北区', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130481, 8130400, '0,8130400,8130000' , '武安市', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130500, 8130000, '0,8130000' , '邢台市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130501, 8130500, '0,8130500,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130502, 8130500, '0,8130500,8130000' , '桥东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130503, 8130500, '0,8130500,8130000' , '桥西区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130521, 8130500, '0,8130500,8130000' , '邢台县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130522, 8130500, '0,8130500,8130000' , '临城县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130523, 8130500, '0,8130500,8130000' , '内丘县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130524, 8130500, '0,8130500,8130000' , '柏乡县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130525, 8130500, '0,8130500,8130000' , '隆尧县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130526, 8130500, '0,8130500,8130000' , '任县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130527, 8130500, '0,8130500,8130000' , '南和县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130528, 8130500, '0,8130500,8130000' , '宁晋县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130529, 8130500, '0,8130500,8130000' , '巨鹿县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130530, 8130500, '0,8130500,8130000' , '新河县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130531, 8130500, '0,8130500,8130000' , '广宗县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130532, 8130500, '0,8130500,8130000' , '平乡县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130533, 8130500, '0,8130500,8130000' , '威县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130534, 8130500, '0,8130500,8130000' , '清河县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130535, 8130500, '0,8130500,8130000' , '临西县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130581, 8130500, '0,8130500,8130000' , '南宫市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130582, 8130500, '0,8130500,8130000' , '沙河市', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130600, 8130000, '0,8130000' , '保定市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130601, 8130600, '0,8130600,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130602, 8130600, '0,8130600,8130000' , '新市区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130603, 8130600, '0,8130600,8130000' , '北市区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130604, 8130600, '0,8130600,8130000' , '南市区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130621, 8130600, '0,8130600,8130000' , '满城县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130622, 8130600, '0,8130600,8130000' , '清苑县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130623, 8130600, '0,8130600,8130000' , '涞水县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130624, 8130600, '0,8130600,8130000' , '阜平县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130625, 8130600, '0,8130600,8130000' , '徐水县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130626, 8130600, '0,8130600,8130000' , '定兴县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130627, 8130600, '0,8130600,8130000' , '唐县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130628, 8130600, '0,8130600,8130000' , '高阳县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130629, 8130600, '0,8130600,8130000' , '容城县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130630, 8130600, '0,8130600,8130000' , '涞源县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130631, 8130600, '0,8130600,8130000' , '望都县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130632, 8130600, '0,8130600,8130000' , '安新县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130633, 8130600, '0,8130600,8130000' , '易县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130634, 8130600, '0,8130600,8130000' , '曲阳县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130635, 8130600, '0,8130600,8130000' , '蠡县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130636, 8130600, '0,8130600,8130000' , '顺平县', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130637, 8130600, '0,8130600,8130000' , '博野县', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130638, 8130600, '0,8130600,8130000' , '雄县', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130642, 8130600, '0,8130600,8130000' , '白沟', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130644, 8130600, '0,8130600,8130000' , '市区西区', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130645, 8130600, '0,8130600,8130000' , '市区西南区', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130646, 8130600, '0,8130600,8130000' , '市区东区', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130681, 8130600, '0,8130600,8130000' , '涿州市', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130682, 8130600, '0,8130600,8130000' , '定州市', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130683, 8130600, '0,8130600,8130000' , '安国市', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130684, 8130600, '0,8130600,8130000' , '高碑店市', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130700, 8130000, '0,8130000' , '张家口市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130701, 8130700, '0,8130700,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130702, 8130700, '0,8130700,8130000' , '桥东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130703, 8130700, '0,8130700,8130000' , '桥西区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130705, 8130700, '0,8130700,8130000' , '宣化区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130706, 8130700, '0,8130700,8130000' , '下花园区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130721, 8130700, '0,8130700,8130000' , '宣化县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130722, 8130700, '0,8130700,8130000' , '张北县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130723, 8130700, '0,8130700,8130000' , '康保县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130724, 8130700, '0,8130700,8130000' , '沽源县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130725, 8130700, '0,8130700,8130000' , '尚义县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130726, 8130700, '0,8130700,8130000' , '蔚县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130727, 8130700, '0,8130700,8130000' , '阳原县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130728, 8130700, '0,8130700,8130000' , '怀安县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130729, 8130700, '0,8130700,8130000' , '万全县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130730, 8130700, '0,8130700,8130000' , '怀来县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130731, 8130700, '0,8130700,8130000' , '涿鹿县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130732, 8130700, '0,8130700,8130000' , '赤城县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130733, 8130700, '0,8130700,8130000' , '崇礼县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130734, 8130700, '0,8130700,8130000' , '经开区', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130800, 8130000, '0,8130000' , '承德市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130801, 8130800, '0,8130800,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130802, 8130800, '0,8130800,8130000' , '双桥区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130803, 8130800, '0,8130800,8130000' , '双滦区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130804, 8130800, '0,8130800,8130000' , '鹰手营子矿区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130821, 8130800, '0,8130800,8130000' , '承德县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130822, 8130800, '0,8130800,8130000' , '兴隆县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130823, 8130800, '0,8130800,8130000' , '平泉市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130824, 8130800, '0,8130800,8130000' , '滦平县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130825, 8130800, '0,8130800,8130000' , '隆化县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130826, 8130800, '0,8130800,8130000' , '丰宁满族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130827, 8130800, '0,8130800,8130000' , '宽城满族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130828, 8130800, '0,8130800,8130000' , '围场满族蒙古族自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130831, 8130800, '0,8130800,8130000' , '高新区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130832, 8130800, '0,8130800,8130000' , '双桥区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130900, 8130000, '0,8130000' , '沧州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130901, 8130900, '0,8130900,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130902, 8130900, '0,8130900,8130000' , '新华区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130903, 8130900, '0,8130900,8130000' , '沧州高新区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130921, 8130900, '0,8130900,8130000' , '沧县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130922, 8130900, '0,8130900,8130000' , '青县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130923, 8130900, '0,8130900,8130000' , '东光县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130924, 8130900, '0,8130900,8130000' , '海兴县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130925, 8130900, '0,8130900,8130000' , '盐山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130926, 8130900, '0,8130900,8130000' , '肃宁县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130927, 8130900, '0,8130900,8130000' , '南皮县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130928, 8130900, '0,8130900,8130000' , '吴桥县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130929, 8130900, '0,8130900,8130000' , '献县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130930, 8130900, '0,8130900,8130000' , '孟村回族自治县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130981, 8130900, '0,8130900,8130000' , '泊头市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130982, 8130900, '0,8130900,8130000' , '任丘市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130983, 8130900, '0,8130900,8130000' , '黄骅市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130984, 8130900, '0,8130900,8130000' , '河间市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130951, 8130900, '0,8130900,8130000' , '电信运西区', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130952, 8130900, '0,8130900,8130000' , '电信渤海新区', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130956, 8130900, '0,8130900,8130000' , '电信运东区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130961, 8130900, '0,8130900,8130000' , '河西', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130962, 8130900, '0,8130900,8130000' , '河东', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131000, 8130000, '0,8130000' , '廊坊市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131001, 8131000, '0,8131000,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131002, 8131000, '0,8131000,8130000' , '安次区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131003, 8131000, '0,8131000,8130000' , '广阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131022, 8131000, '0,8131000,8130000' , '固安县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131023, 8131000, '0,8131000,8130000' , '永清县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131024, 8131000, '0,8131000,8130000' , '香河县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131025, 8131000, '0,8131000,8130000' , '大城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131026, 8131000, '0,8131000,8130000' , '文安县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131028, 8131000, '0,8131000,8130000' , '大厂回族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131032, 8131000, '0,8131000,8130000' , '左各庄', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131033, 8131000, '0,8131000,8130000' , '燕郊', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131034, 8131000, '0,8131000,8130000' , '胜芳', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131035, 8131000, '0,8131000,8130000' , '开发区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131081, 8131000, '0,8131000,8130000' , '霸州市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131082, 8131000, '0,8131000,8130000' , '三河市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131100, 8130000, '0,8130000' , '衡水市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131101, 8131100, '0,8131100,8130000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131102, 8131100, '0,8131100,8130000' , '桃城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131121, 8131100, '0,8131100,8130000' , '枣强县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131122, 8131100, '0,8131100,8130000' , '武邑县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131123, 8131100, '0,8131100,8130000' , '武强县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131124, 8131100, '0,8131100,8130000' , '饶阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131125, 8131100, '0,8131100,8130000' , '安平县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131126, 8131100, '0,8131100,8130000' , '故城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131127, 8131100, '0,8131100,8130000' , '景县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131128, 8131100, '0,8131100,8130000' , '阜城县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131131, 8131100, '0,8131100,8130000' , '桃城西区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131137, 8131100, '0,8131100,8130000' , '桃城东区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131181, 8131100, '0,8131100,8130000' , '冀州市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131182, 8131100, '0,8131100,8130000' , '深州市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8131200, 8130000, '0,8130000' , '雄安新区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140000, 0, '0' , '山西省', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140100, 8140000, '0,8140000' , '太原市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140101, 8140100, '0,8140100,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140105, 8140100, '0,8140100,8140000' , '小店区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140106, 8140100, '0,8140100,8140000' , '迎泽区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140107, 8140100, '0,8140100,8140000' , '杏花岭区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140108, 8140100, '0,8140100,8140000' , '尖草坪区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140109, 8140100, '0,8140100,8140000' , '万柏林区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140110, 8140100, '0,8140100,8140000' , '晋源区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140121, 8140100, '0,8140100,8140000' , '清徐县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140122, 8140100, '0,8140100,8140000' , '阳曲县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140123, 8140100, '0,8140100,8140000' , '娄烦县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140181, 8140100, '0,8140100,8140000' , '古交市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140200, 8140000, '0,8140000' , '大同市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140201, 8140200, '0,8140200,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140202, 8140200, '0,8140200,8140000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140203, 8140200, '0,8140200,8140000' , '矿区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140211, 8140200, '0,8140200,8140000' , '南郊区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140212, 8140200, '0,8140200,8140000' , '新荣区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140221, 8140200, '0,8140200,8140000' , '阳高县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140222, 8140200, '0,8140200,8140000' , '天镇县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140223, 8140200, '0,8140200,8140000' , '广灵县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140224, 8140200, '0,8140200,8140000' , '灵丘县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140225, 8140200, '0,8140200,8140000' , '浑源县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140226, 8140200, '0,8140200,8140000' , '左云县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140227, 8140200, '0,8140200,8140000' , '大同县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140300, 8140000, '0,8140000' , '阳泉市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140301, 8140300, '0,8140300,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140302, 8140300, '0,8140300,8140000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140303, 8140300, '0,8140300,8140000' , '矿区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140311, 8140300, '0,8140300,8140000' , '郊区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140321, 8140300, '0,8140300,8140000' , '平定县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140322, 8140300, '0,8140300,8140000' , '盂县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140400, 8140000, '0,8140000' , '长治市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140401, 8140400, '0,8140400,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140402, 8140400, '0,8140400,8140000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140411, 8140400, '0,8140400,8140000' , '郊区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140421, 8140400, '0,8140400,8140000' , '长治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140423, 8140400, '0,8140400,8140000' , '襄垣县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140424, 8140400, '0,8140400,8140000' , '屯留县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140425, 8140400, '0,8140400,8140000' , '平顺县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140426, 8140400, '0,8140400,8140000' , '黎城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140427, 8140400, '0,8140400,8140000' , '壶关县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140428, 8140400, '0,8140400,8140000' , '长子县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140429, 8140400, '0,8140400,8140000' , '武乡县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140430, 8140400, '0,8140400,8140000' , '沁县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140431, 8140400, '0,8140400,8140000' , '沁源县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140481, 8140400, '0,8140400,8140000' , '潞城市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140500, 8140000, '0,8140000' , '晋城市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140501, 8140500, '0,8140500,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140502, 8140500, '0,8140500,8140000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140521, 8140500, '0,8140500,8140000' , '沁水县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140522, 8140500, '0,8140500,8140000' , '阳城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140524, 8140500, '0,8140500,8140000' , '陵川县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140525, 8140500, '0,8140500,8140000' , '泽州县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140581, 8140500, '0,8140500,8140000' , '高平市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140600, 8140000, '0,8140000' , '朔州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140601, 8140600, '0,8140600,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140602, 8140600, '0,8140600,8140000' , '朔城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140603, 8140600, '0,8140600,8140000' , '平鲁区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140621, 8140600, '0,8140600,8140000' , '山阴县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140622, 8140600, '0,8140600,8140000' , '应县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140623, 8140600, '0,8140600,8140000' , '右玉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140624, 8140600, '0,8140600,8140000' , '怀仁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140700, 8140000, '0,8140000' , '晋中市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140701, 8140700, '0,8140700,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140702, 8140700, '0,8140700,8140000' , '榆次区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140721, 8140700, '0,8140700,8140000' , '榆社县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140722, 8140700, '0,8140700,8140000' , '左权县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140723, 8140700, '0,8140700,8140000' , '和顺县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140724, 8140700, '0,8140700,8140000' , '昔阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140725, 8140700, '0,8140700,8140000' , '寿阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140726, 8140700, '0,8140700,8140000' , '太谷县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140727, 8140700, '0,8140700,8140000' , '祁县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140728, 8140700, '0,8140700,8140000' , '平遥县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140729, 8140700, '0,8140700,8140000' , '灵石县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140781, 8140700, '0,8140700,8140000' , '介休市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140800, 8140000, '0,8140000' , '运城市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140801, 8140800, '0,8140800,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140802, 8140800, '0,8140800,8140000' , '盐湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140821, 8140800, '0,8140800,8140000' , '临猗县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140822, 8140800, '0,8140800,8140000' , '万荣县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140823, 8140800, '0,8140800,8140000' , '闻喜县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140824, 8140800, '0,8140800,8140000' , '稷山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140825, 8140800, '0,8140800,8140000' , '新绛县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140826, 8140800, '0,8140800,8140000' , '绛县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140827, 8140800, '0,8140800,8140000' , '垣曲县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140828, 8140800, '0,8140800,8140000' , '夏县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140829, 8140800, '0,8140800,8140000' , '平陆县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140830, 8140800, '0,8140800,8140000' , '芮城县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140881, 8140800, '0,8140800,8140000' , '永济市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140882, 8140800, '0,8140800,8140000' , '河津市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140900, 8140000, '0,8140000' , '忻州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140901, 8140900, '0,8140900,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140902, 8140900, '0,8140900,8140000' , '忻府区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140921, 8140900, '0,8140900,8140000' , '定襄县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140922, 8140900, '0,8140900,8140000' , '五台县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140923, 8140900, '0,8140900,8140000' , '代县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140924, 8140900, '0,8140900,8140000' , '繁峙县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140925, 8140900, '0,8140900,8140000' , '宁武县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140926, 8140900, '0,8140900,8140000' , '静乐县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140927, 8140900, '0,8140900,8140000' , '神池县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140928, 8140900, '0,8140900,8140000' , '五寨县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140929, 8140900, '0,8140900,8140000' , '岢岚县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140930, 8140900, '0,8140900,8140000' , '河曲县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140931, 8140900, '0,8140900,8140000' , '保德县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140932, 8140900, '0,8140900,8140000' , '偏关县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8140981, 8140900, '0,8140900,8140000' , '原平市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141000, 8140000, '0,8140000' , '临汾市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141001, 8141000, '0,8141000,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141002, 8141000, '0,8141000,8140000' , '尧都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141021, 8141000, '0,8141000,8140000' , '曲沃县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141022, 8141000, '0,8141000,8140000' , '翼城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141023, 8141000, '0,8141000,8140000' , '襄汾县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141024, 8141000, '0,8141000,8140000' , '洪洞县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141025, 8141000, '0,8141000,8140000' , '古县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141026, 8141000, '0,8141000,8140000' , '安泽县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141027, 8141000, '0,8141000,8140000' , '浮山县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141028, 8141000, '0,8141000,8140000' , '吉县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141029, 8141000, '0,8141000,8140000' , '乡宁县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141030, 8141000, '0,8141000,8140000' , '大宁县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141031, 8141000, '0,8141000,8140000' , '隰县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141032, 8141000, '0,8141000,8140000' , '永和县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141033, 8141000, '0,8141000,8140000' , '蒲县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141034, 8141000, '0,8141000,8140000' , '汾西县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141081, 8141000, '0,8141000,8140000' , '侯马市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141082, 8141000, '0,8141000,8140000' , '霍州市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141100, 8140000, '0,8140000' , '吕梁市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141101, 8141100, '0,8141100,8140000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141102, 8141100, '0,8141100,8140000' , '离石区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141121, 8141100, '0,8141100,8140000' , '文水县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141122, 8141100, '0,8141100,8140000' , '交城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141123, 8141100, '0,8141100,8140000' , '兴县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141124, 8141100, '0,8141100,8140000' , '临县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141125, 8141100, '0,8141100,8140000' , '柳林县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141126, 8141100, '0,8141100,8140000' , '石楼县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141127, 8141100, '0,8141100,8140000' , '岚县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141128, 8141100, '0,8141100,8140000' , '方山县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141129, 8141100, '0,8141100,8140000' , '中阳县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141130, 8141100, '0,8141100,8140000' , '交口县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141181, 8141100, '0,8141100,8140000' , '孝义市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8141182, 8141100, '0,8141100,8140000' , '汾阳市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150000, 0, '0' , '内蒙古自治区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150100, 8150000, '0,8150000' , '呼和浩特市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150101, 8150100, '0,8150100,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150102, 8150100, '0,8150100,8150000' , '新城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150103, 8150100, '0,8150100,8150000' , '回民区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150104, 8150100, '0,8150100,8150000' , '玉泉区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150105, 8150100, '0,8150100,8150000' , '赛罕区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150121, 8150100, '0,8150100,8150000' , '土默特左旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150122, 8150100, '0,8150100,8150000' , '托克托县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150123, 8150100, '0,8150100,8150000' , '和林格尔县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150124, 8150100, '0,8150100,8150000' , '清水河县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150125, 8150100, '0,8150100,8150000' , '武川县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150200, 8150000, '0,8150000' , '包头市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150201, 8150200, '0,8150200,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150202, 8150200, '0,8150200,8150000' , '东河区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150203, 8150200, '0,8150200,8150000' , '昆都仑区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150204, 8150200, '0,8150200,8150000' , '青山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150205, 8150200, '0,8150200,8150000' , '石拐区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150206, 8150200, '0,8150200,8150000' , '白云鄂博矿区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150207, 8150200, '0,8150200,8150000' , '九原区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150221, 8150200, '0,8150200,8150000' , '土默特右旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150222, 8150200, '0,8150200,8150000' , '固阳县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150223, 8150200, '0,8150200,8150000' , '达尔罕茂明安联合旗', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150300, 8150000, '0,8150000' , '乌海市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150301, 8150300, '0,8150300,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150302, 8150300, '0,8150300,8150000' , '海勃湾区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150303, 8150300, '0,8150300,8150000' , '海南区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150304, 8150300, '0,8150300,8150000' , '乌达区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150400, 8150000, '0,8150000' , '赤峰市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150401, 8150400, '0,8150400,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150402, 8150400, '0,8150400,8150000' , '红山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150403, 8150400, '0,8150400,8150000' , '元宝山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150404, 8150400, '0,8150400,8150000' , '松山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150421, 8150400, '0,8150400,8150000' , '阿鲁科尔沁旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150422, 8150400, '0,8150400,8150000' , '巴林左旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150423, 8150400, '0,8150400,8150000' , '巴林右旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150424, 8150400, '0,8150400,8150000' , '林西县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150425, 8150400, '0,8150400,8150000' , '克什克腾旗', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150426, 8150400, '0,8150400,8150000' , '翁牛特旗', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150428, 8150400, '0,8150400,8150000' , '喀喇沁旗', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150429, 8150400, '0,8150400,8150000' , '宁城县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150430, 8150400, '0,8150400,8150000' , '敖汉旗', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150500, 8150000, '0,8150000' , '通辽市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150501, 8150500, '0,8150500,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150502, 8150500, '0,8150500,8150000' , '科尔沁区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150521, 8150500, '0,8150500,8150000' , '科尔沁左翼中旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150522, 8150500, '0,8150500,8150000' , '科尔沁左翼后旗', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150523, 8150500, '0,8150500,8150000' , '开鲁县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150524, 8150500, '0,8150500,8150000' , '库伦旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150525, 8150500, '0,8150500,8150000' , '奈曼旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150526, 8150500, '0,8150500,8150000' , '扎鲁特旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150581, 8150500, '0,8150500,8150000' , '霍林郭勒市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150600, 8150000, '0,8150000' , '鄂尔多斯市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150601, 8150600, '0,8150600,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150602, 8150600, '0,8150600,8150000' , '东胜区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150621, 8150600, '0,8150600,8150000' , '达拉特旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150622, 8150600, '0,8150600,8150000' , '准格尔旗', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150623, 8150600, '0,8150600,8150000' , '鄂托克前旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150624, 8150600, '0,8150600,8150000' , '鄂托克旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150625, 8150600, '0,8150600,8150000' , '杭锦旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150626, 8150600, '0,8150600,8150000' , '乌审旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150627, 8150600, '0,8150600,8150000' , '伊金霍洛旗', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150628, 8150600, '0,8150600,8150000' , '康巴什新区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150700, 8150000, '0,8150000' , '呼伦贝尔市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150701, 8150700, '0,8150700,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150702, 8150700, '0,8150700,8150000' , '海拉尔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150721, 8150700, '0,8150700,8150000' , '阿荣旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150722, 8150700, '0,8150700,8150000' , '莫力达瓦达斡尔族自治旗', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150723, 8150700, '0,8150700,8150000' , '鄂伦春自治旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150724, 8150700, '0,8150700,8150000' , '鄂温克族自治旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150725, 8150700, '0,8150700,8150000' , '陈巴尔虎旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150726, 8150700, '0,8150700,8150000' , '新巴尔虎左旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150727, 8150700, '0,8150700,8150000' , '新巴尔虎右旗', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150781, 8150700, '0,8150700,8150000' , '满洲里市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150782, 8150700, '0,8150700,8150000' , '牙克石市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150783, 8150700, '0,8150700,8150000' , '扎兰屯市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150784, 8150700, '0,8150700,8150000' , '额尔古纳市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150785, 8150700, '0,8150700,8150000' , '根河市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150800, 8150000, '0,8150000' , '巴彦淖尔市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150801, 8150800, '0,8150800,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150802, 8150800, '0,8150800,8150000' , '临河区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150821, 8150800, '0,8150800,8150000' , '五原县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150822, 8150800, '0,8150800,8150000' , '磴口县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150823, 8150800, '0,8150800,8150000' , '乌拉特前旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150824, 8150800, '0,8150800,8150000' , '乌拉特中旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150825, 8150800, '0,8150800,8150000' , '乌拉特后旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150826, 8150800, '0,8150800,8150000' , '杭锦后旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150900, 8150000, '0,8150000' , '乌兰察布市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150901, 8150900, '0,8150900,8150000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150902, 8150900, '0,8150900,8150000' , '集宁区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150921, 8150900, '0,8150900,8150000' , '卓资县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150922, 8150900, '0,8150900,8150000' , '化德县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150923, 8150900, '0,8150900,8150000' , '商都县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150924, 8150900, '0,8150900,8150000' , '兴和县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150925, 8150900, '0,8150900,8150000' , '凉城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150926, 8150900, '0,8150900,8150000' , '察哈尔右翼前旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150927, 8150900, '0,8150900,8150000' , '察哈尔右翼中旗', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150928, 8150900, '0,8150900,8150000' , '察哈尔右翼后旗', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150929, 8150900, '0,8150900,8150000' , '四子王旗', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8150981, 8150900, '0,8150900,8150000' , '丰镇市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152200, 8150000, '0,8150000' , '兴安盟', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152201, 8152200, '0,8152200,8150000' , '乌兰浩特市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152202, 8152200, '0,8152200,8150000' , '阿尔山市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152221, 8152200, '0,8152200,8150000' , '科尔沁右翼前旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152222, 8152200, '0,8152200,8150000' , '科尔沁右翼中旗', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152223, 8152200, '0,8152200,8150000' , '扎赉特旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152224, 8152200, '0,8152200,8150000' , '突泉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152500, 8150000, '0,8150000' , '锡林郭勒盟', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152501, 8152500, '0,8152500,8150000' , '二连浩特市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152502, 8152500, '0,8152500,8150000' , '锡林浩特市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152522, 8152500, '0,8152500,8150000' , '阿巴嘎旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152523, 8152500, '0,8152500,8150000' , '苏尼特左旗', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152524, 8152500, '0,8152500,8150000' , '苏尼特右旗', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152525, 8152500, '0,8152500,8150000' , '东乌珠穆沁旗', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152526, 8152500, '0,8152500,8150000' , '西乌珠穆沁旗', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152527, 8152500, '0,8152500,8150000' , '太仆寺旗', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152528, 8152500, '0,8152500,8150000' , '镶黄旗', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152529, 8152500, '0,8152500,8150000' , '正镶白旗', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152530, 8152500, '0,8152500,8150000' , '正蓝旗', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152531, 8152500, '0,8152500,8150000' , '多伦县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152900, 8150000, '0,8150000' , '阿拉善盟', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152921, 8152900, '0,8152900,8150000' , '阿拉善左旗', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152922, 8152900, '0,8152900,8150000' , '阿拉善右旗', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8152923, 8152900, '0,8152900,8150000' , '额济纳旗', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210000, 0, '0' , '辽宁省', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210100, 8210000, '0,8210000' , '沈阳市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210101, 8210100, '0,8210100,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210102, 8210100, '0,8210100,8210000' , '和平区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210103, 8210100, '0,8210100,8210000' , '沈河区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210104, 8210100, '0,8210100,8210000' , '大东区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210105, 8210100, '0,8210100,8210000' , '皇姑区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210106, 8210100, '0,8210100,8210000' , '铁西区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210111, 8210100, '0,8210100,8210000' , '苏家屯区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210112, 8210100, '0,8210100,8210000' , '东陵区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210113, 8210100, '0,8210100,8210000' , '沈北新区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210114, 8210100, '0,8210100,8210000' , '于洪区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210115, 8210100, '0,8210100,8210000' , '浑南新区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210122, 8210100, '0,8210100,8210000' , '辽中县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210123, 8210100, '0,8210100,8210000' , '康平县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210124, 8210100, '0,8210100,8210000' , '法库县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210181, 8210100, '0,8210100,8210000' , '新民市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210200, 8210000, '0,8210000' , '大连市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210201, 8210200, '0,8210200,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210202, 8210200, '0,8210200,8210000' , '中山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210203, 8210200, '0,8210200,8210000' , '西岗区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210204, 8210200, '0,8210200,8210000' , '沙河口区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210211, 8210200, '0,8210200,8210000' , '甘井子区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210212, 8210200, '0,8210200,8210000' , '旅顺口区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210213, 8210200, '0,8210200,8210000' , '金州区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210214, 8210200, '0,8210200,8210000' , '开发区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210215, 8210200, '0,8210200,8210000' , '高新园', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210224, 8210200, '0,8210200,8210000' , '长海县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210281, 8210200, '0,8210200,8210000' , '瓦房店市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210282, 8210200, '0,8210200,8210000' , '普兰店市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210283, 8210200, '0,8210200,8210000' , '庄河市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210299, 8210200, '0,8210200,8210000' , '保税区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210298, 8210200, '0,8210200,8210000' , '高新园区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210300, 8210000, '0,8210000' , '鞍山市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210301, 8210300, '0,8210300,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210302, 8210300, '0,8210300,8210000' , '铁东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210303, 8210300, '0,8210300,8210000' , '铁西区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210304, 8210300, '0,8210300,8210000' , '立山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210311, 8210300, '0,8210300,8210000' , '千山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210321, 8210300, '0,8210300,8210000' , '台安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210323, 8210300, '0,8210300,8210000' , '岫岩满族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210381, 8210300, '0,8210300,8210000' , '海城市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210312, 8210300, '0,8210300,8210000' , '达道湾新城区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210400, 8210000, '0,8210000' , '抚顺市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210401, 8210400, '0,8210400,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210402, 8210400, '0,8210400,8210000' , '新抚区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210403, 8210400, '0,8210400,8210000' , '东洲区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210404, 8210400, '0,8210400,8210000' , '望花区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210411, 8210400, '0,8210400,8210000' , '顺城区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210421, 8210400, '0,8210400,8210000' , '抚顺县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210422, 8210400, '0,8210400,8210000' , '新宾满族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210423, 8210400, '0,8210400,8210000' , '清原满族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210500, 8210000, '0,8210000' , '本溪市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210501, 8210500, '0,8210500,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210502, 8210500, '0,8210500,8210000' , '平山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210503, 8210500, '0,8210500,8210000' , '溪湖区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210504, 8210500, '0,8210500,8210000' , '明山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210505, 8210500, '0,8210500,8210000' , '南芬区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210521, 8210500, '0,8210500,8210000' , '本溪满族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210522, 8210500, '0,8210500,8210000' , '桓仁满族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210511, 8210500, '0,8210500,8210000' , '开发区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210600, 8210000, '0,8210000' , '丹东市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210601, 8210600, '0,8210600,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210602, 8210600, '0,8210600,8210000' , '元宝区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210603, 8210600, '0,8210600,8210000' , '振兴区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210604, 8210600, '0,8210600,8210000' , '振安区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210624, 8210600, '0,8210600,8210000' , '宽甸满族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210681, 8210600, '0,8210600,8210000' , '东港市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210682, 8210600, '0,8210600,8210000' , '凤城市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210700, 8210000, '0,8210000' , '锦州市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210701, 8210700, '0,8210700,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210702, 8210700, '0,8210700,8210000' , '古塔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210703, 8210700, '0,8210700,8210000' , '凌河区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210711, 8210700, '0,8210700,8210000' , '太和区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210726, 8210700, '0,8210700,8210000' , '黑山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210727, 8210700, '0,8210700,8210000' , '义县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210781, 8210700, '0,8210700,8210000' , '凌海市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210782, 8210700, '0,8210700,8210000' , '北镇市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210712, 8210700, '0,8210700,8210000' , '锦州_市开发区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210800, 8210000, '0,8210000' , '营口市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210801, 8210800, '0,8210800,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210802, 8210800, '0,8210800,8210000' , '站前区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210803, 8210800, '0,8210800,8210000' , '西市区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210804, 8210800, '0,8210800,8210000' , '鲅鱼圈区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210811, 8210800, '0,8210800,8210000' , '老边区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210881, 8210800, '0,8210800,8210000' , '盖州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210882, 8210800, '0,8210800,8210000' , '大石桥市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210900, 8210000, '0,8210000' , '阜新市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210901, 8210900, '0,8210900,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210902, 8210900, '0,8210900,8210000' , '海州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210903, 8210900, '0,8210900,8210000' , '新邱区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210904, 8210900, '0,8210900,8210000' , '太平区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210905, 8210900, '0,8210900,8210000' , '清河门区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210911, 8210900, '0,8210900,8210000' , '细河区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210921, 8210900, '0,8210900,8210000' , '阜新蒙古族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8210922, 8210900, '0,8210900,8210000' , '彰武县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211000, 8210000, '0,8210000' , '辽阳市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211001, 8211000, '0,8211000,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211002, 8211000, '0,8211000,8210000' , '白塔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211003, 8211000, '0,8211000,8210000' , '文圣区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211004, 8211000, '0,8211000,8210000' , '宏伟区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211005, 8211000, '0,8211000,8210000' , '弓长岭区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211011, 8211000, '0,8211000,8210000' , '太子河区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211021, 8211000, '0,8211000,8210000' , '辽阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211081, 8211000, '0,8211000,8210000' , '灯塔市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211100, 8210000, '0,8210000' , '盘锦市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211101, 8211100, '0,8211100,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211102, 8211100, '0,8211100,8210000' , '双台子区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211103, 8211100, '0,8211100,8210000' , '兴隆台区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211121, 8211100, '0,8211100,8210000' , '大洼县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211122, 8211100, '0,8211100,8210000' , '盘山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211111, 8211100, '0,8211100,8210000' , '辽河油田', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211200, 8210000, '0,8210000' , '铁岭市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211201, 8211200, '0,8211200,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211202, 8211200, '0,8211200,8210000' , '银州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211204, 8211200, '0,8211200,8210000' , '清河区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211221, 8211200, '0,8211200,8210000' , '铁岭县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211223, 8211200, '0,8211200,8210000' , '西丰县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211224, 8211200, '0,8211200,8210000' , '昌图县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211281, 8211200, '0,8211200,8210000' , '调兵山市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211282, 8211200, '0,8211200,8210000' , '开原市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211300, 8210000, '0,8210000' , '朝阳市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211301, 8211300, '0,8211300,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211302, 8211300, '0,8211300,8210000' , '双塔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211303, 8211300, '0,8211300,8210000' , '龙城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211321, 8211300, '0,8211300,8210000' , '朝阳县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211322, 8211300, '0,8211300,8210000' , '建平县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211324, 8211300, '0,8211300,8210000' , '喀喇沁左翼蒙古族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211381, 8211300, '0,8211300,8210000' , '北票市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211382, 8211300, '0,8211300,8210000' , '凌源市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211400, 8210000, '0,8210000' , '葫芦岛市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211401, 8211400, '0,8211400,8210000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211402, 8211400, '0,8211400,8210000' , '连山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211403, 8211400, '0,8211400,8210000' , '龙港区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211404, 8211400, '0,8211400,8210000' , '南票区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211421, 8211400, '0,8211400,8210000' , '绥中县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211422, 8211400, '0,8211400,8210000' , '建昌县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8211481, 8211400, '0,8211400,8210000' , '兴城市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220000, 0, '0' , '吉林省', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220100, 8220000, '0,8220000' , '长春市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220101, 8220100, '0,8220100,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220102, 8220100, '0,8220100,8220000' , '南关区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220103, 8220100, '0,8220100,8220000' , '宽城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220104, 8220100, '0,8220100,8220000' , '朝阳区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220105, 8220100, '0,8220100,8220000' , '二道区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220106, 8220100, '0,8220100,8220000' , '绿园区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220112, 8220100, '0,8220100,8220000' , '双阳区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220122, 8220100, '0,8220100,8220000' , '农安县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220181, 8220100, '0,8220100,8220000' , '九台市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220182, 8220100, '0,8220100,8220000' , '榆树市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220183, 8220100, '0,8220100,8220000' , '德惠市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220196, 8220100, '0,8220100,8220000' , '高新区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220197, 8220100, '0,8220100,8220000' , '汽车区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220198, 8220100, '0,8220100,8220000' , '净月区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220199, 8220100, '0,8220100,8220000' , '经开区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220200, 8220000, '0,8220000' , '吉林市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220201, 8220200, '0,8220200,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220202, 8220200, '0,8220200,8220000' , '昌邑区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220203, 8220200, '0,8220200,8220000' , '龙潭区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220204, 8220200, '0,8220200,8220000' , '船营区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220211, 8220200, '0,8220200,8220000' , '丰满区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220221, 8220200, '0,8220200,8220000' , '永吉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220281, 8220200, '0,8220200,8220000' , '蛟河市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220282, 8220200, '0,8220200,8220000' , '桦甸市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220283, 8220200, '0,8220200,8220000' , '舒兰市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220284, 8220200, '0,8220200,8220000' , '磐石市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220300, 8220000, '0,8220000' , '四平市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220301, 8220300, '0,8220300,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220302, 8220300, '0,8220300,8220000' , '铁西区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220303, 8220300, '0,8220300,8220000' , '铁东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220322, 8220300, '0,8220300,8220000' , '梨树县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220323, 8220300, '0,8220300,8220000' , '伊通满族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220381, 8220300, '0,8220300,8220000' , '公主岭市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220382, 8220300, '0,8220300,8220000' , '双辽市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220400, 8220000, '0,8220000' , '辽源市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220401, 8220400, '0,8220400,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220402, 8220400, '0,8220400,8220000' , '龙山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220403, 8220400, '0,8220400,8220000' , '西安区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220421, 8220400, '0,8220400,8220000' , '东丰县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220422, 8220400, '0,8220400,8220000' , '东辽县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220499, 8220400, '0,8220400,8220000' , '辽源经济技术开发区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220500, 8220000, '0,8220000' , '通化市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220501, 8220500, '0,8220500,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220502, 8220500, '0,8220500,8220000' , '东昌区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220503, 8220500, '0,8220500,8220000' , '二道江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220521, 8220500, '0,8220500,8220000' , '通化县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220523, 8220500, '0,8220500,8220000' , '辉南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220524, 8220500, '0,8220500,8220000' , '柳河县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220581, 8220500, '0,8220500,8220000' , '梅河口市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220582, 8220500, '0,8220500,8220000' , '集安市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220600, 8220000, '0,8220000' , '白山市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220601, 8220600, '0,8220600,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220602, 8220600, '0,8220600,8220000' , '八道江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220605, 8220600, '0,8220600,8220000' , '江源区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220621, 8220600, '0,8220600,8220000' , '抚松县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220622, 8220600, '0,8220600,8220000' , '靖宇县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220623, 8220600, '0,8220600,8220000' , '长白朝鲜族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220681, 8220600, '0,8220600,8220000' , '临江市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220700, 8220000, '0,8220000' , '松原市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220701, 8220700, '0,8220700,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220702, 8220700, '0,8220700,8220000' , '宁江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220721, 8220700, '0,8220700,8220000' , '前郭尔罗斯蒙古族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220722, 8220700, '0,8220700,8220000' , '长岭县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220723, 8220700, '0,8220700,8220000' , '乾安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220724, 8220700, '0,8220700,8220000' , '扶余县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220800, 8220000, '0,8220000' , '白城市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220801, 8220800, '0,8220800,8220000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220802, 8220800, '0,8220800,8220000' , '洮北区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220821, 8220800, '0,8220800,8220000' , '镇赉县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220822, 8220800, '0,8220800,8220000' , '通榆县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220881, 8220800, '0,8220800,8220000' , '洮南市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8220882, 8220800, '0,8220800,8220000' , '大安市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222400, 8220000, '0,8220000' , '延边朝鲜族自治州', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222401, 8222400, '0,8222400,8220000' , '延吉市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222402, 8222400, '0,8222400,8220000' , '图们市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222403, 8222400, '0,8222400,8220000' , '敦化市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222404, 8222400, '0,8222400,8220000' , '珲春市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222405, 8222400, '0,8222400,8220000' , '龙井市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222406, 8222400, '0,8222400,8220000' , '和龙市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222424, 8222400, '0,8222400,8220000' , '汪清县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8222426, 8222400, '0,8222400,8220000' , '安图县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230000, 0, '0' , '黑龙江省', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230100, 8230000, '0,8230000' , '哈尔滨市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230101, 8230100, '0,8230100,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230102, 8230100, '0,8230100,8230000' , '道里区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230103, 8230100, '0,8230100,8230000' , '南岗区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230104, 8230100, '0,8230100,8230000' , '道外区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230108, 8230100, '0,8230100,8230000' , '平房区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230109, 8230100, '0,8230100,8230000' , '松北区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230110, 8230100, '0,8230100,8230000' , '香坊区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230111, 8230100, '0,8230100,8230000' , '呼兰区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230112, 8230100, '0,8230100,8230000' , '阿城区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230123, 8230100, '0,8230100,8230000' , '依兰县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230124, 8230100, '0,8230100,8230000' , '方正县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230125, 8230100, '0,8230100,8230000' , '宾县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230126, 8230100, '0,8230100,8230000' , '巴彦县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230127, 8230100, '0,8230100,8230000' , '木兰县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230128, 8230100, '0,8230100,8230000' , '通河县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230129, 8230100, '0,8230100,8230000' , '延寿县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230182, 8230100, '0,8230100,8230000' , '双城市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230183, 8230100, '0,8230100,8230000' , '尚志市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230184, 8230100, '0,8230100,8230000' , '五常市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230199, 8230100, '0,8230100,8230000' , '群力区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230200, 8230000, '0,8230000' , '齐齐哈尔市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230201, 8230200, '0,8230200,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230202, 8230200, '0,8230200,8230000' , '龙沙区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230203, 8230200, '0,8230200,8230000' , '建华区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230204, 8230200, '0,8230200,8230000' , '铁锋区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230205, 8230200, '0,8230200,8230000' , '昂昂溪区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230206, 8230200, '0,8230200,8230000' , '富拉尔基区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230207, 8230200, '0,8230200,8230000' , '碾子山区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230208, 8230200, '0,8230200,8230000' , '梅里斯达斡尔族区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230221, 8230200, '0,8230200,8230000' , '龙江县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230223, 8230200, '0,8230200,8230000' , '依安县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230224, 8230200, '0,8230200,8230000' , '泰来县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230225, 8230200, '0,8230200,8230000' , '甘南县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230227, 8230200, '0,8230200,8230000' , '富裕县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230229, 8230200, '0,8230200,8230000' , '克山县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230230, 8230200, '0,8230200,8230000' , '克东县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230231, 8230200, '0,8230200,8230000' , '拜泉县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230281, 8230200, '0,8230200,8230000' , '讷河市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230300, 8230000, '0,8230000' , '鸡西市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230301, 8230300, '0,8230300,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230302, 8230300, '0,8230300,8230000' , '鸡冠区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230303, 8230300, '0,8230300,8230000' , '恒山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230304, 8230300, '0,8230300,8230000' , '滴道区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230305, 8230300, '0,8230300,8230000' , '梨树区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230306, 8230300, '0,8230300,8230000' , '城子河区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230307, 8230300, '0,8230300,8230000' , '麻山区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230321, 8230300, '0,8230300,8230000' , '鸡东县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230381, 8230300, '0,8230300,8230000' , '虎林市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230382, 8230300, '0,8230300,8230000' , '密山市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230400, 8230000, '0,8230000' , '鹤岗市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230401, 8230400, '0,8230400,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230402, 8230400, '0,8230400,8230000' , '向阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230403, 8230400, '0,8230400,8230000' , '工农区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230404, 8230400, '0,8230400,8230000' , '南山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230405, 8230400, '0,8230400,8230000' , '兴安区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230406, 8230400, '0,8230400,8230000' , '东山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230407, 8230400, '0,8230400,8230000' , '兴山区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230421, 8230400, '0,8230400,8230000' , '萝北县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230422, 8230400, '0,8230400,8230000' , '绥滨县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230499, 8230400, '0,8230400,8230000' , '宝泉岭县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230500, 8230000, '0,8230000' , '双鸭山市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230501, 8230500, '0,8230500,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230502, 8230500, '0,8230500,8230000' , '尖山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230503, 8230500, '0,8230500,8230000' , '岭东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230505, 8230500, '0,8230500,8230000' , '四方台区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230506, 8230500, '0,8230500,8230000' , '宝山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230521, 8230500, '0,8230500,8230000' , '集贤县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230522, 8230500, '0,8230500,8230000' , '友谊县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230523, 8230500, '0,8230500,8230000' , '宝清县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230524, 8230500, '0,8230500,8230000' , '饶河县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230599, 8230500, '0,8230500,8230000' , '红兴隆县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230600, 8230000, '0,8230000' , '大庆市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230601, 8230600, '0,8230600,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230602, 8230600, '0,8230600,8230000' , '萨尔图区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230603, 8230600, '0,8230600,8230000' , '龙凤区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230604, 8230600, '0,8230600,8230000' , '让胡路区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230605, 8230600, '0,8230600,8230000' , '红岗区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230606, 8230600, '0,8230600,8230000' , '大同区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230621, 8230600, '0,8230600,8230000' , '肇州县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230622, 8230600, '0,8230600,8230000' , '肇源县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230623, 8230600, '0,8230600,8230000' , '林甸县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230624, 8230600, '0,8230600,8230000' , '杜尔伯特蒙古族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230700, 8230000, '0,8230000' , '伊春市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230701, 8230700, '0,8230700,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230702, 8230700, '0,8230700,8230000' , '伊春区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230703, 8230700, '0,8230700,8230000' , '南岔区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230704, 8230700, '0,8230700,8230000' , '友好区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230705, 8230700, '0,8230700,8230000' , '西林区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230706, 8230700, '0,8230700,8230000' , '翠峦区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230707, 8230700, '0,8230700,8230000' , '新青区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230708, 8230700, '0,8230700,8230000' , '美溪区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230709, 8230700, '0,8230700,8230000' , '金山屯区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230710, 8230700, '0,8230700,8230000' , '五营区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230711, 8230700, '0,8230700,8230000' , '乌马河区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230712, 8230700, '0,8230700,8230000' , '汤旺河区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230713, 8230700, '0,8230700,8230000' , '带岭区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230714, 8230700, '0,8230700,8230000' , '乌伊岭区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230715, 8230700, '0,8230700,8230000' , '红星区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230716, 8230700, '0,8230700,8230000' , '上甘岭区', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230722, 8230700, '0,8230700,8230000' , '嘉荫县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230781, 8230700, '0,8230700,8230000' , '铁力市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230800, 8230000, '0,8230000' , '佳木斯市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230801, 8230800, '0,8230800,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230803, 8230800, '0,8230800,8230000' , '向阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230804, 8230800, '0,8230800,8230000' , '前进区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230805, 8230800, '0,8230800,8230000' , '东风区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230811, 8230800, '0,8230800,8230000' , '郊区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230822, 8230800, '0,8230800,8230000' , '桦南县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230826, 8230800, '0,8230800,8230000' , '桦川县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230828, 8230800, '0,8230800,8230000' , '汤原县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230833, 8230800, '0,8230800,8230000' , '抚远县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230881, 8230800, '0,8230800,8230000' , '同江市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230882, 8230800, '0,8230800,8230000' , '富锦市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230900, 8230000, '0,8230000' , '七台河市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230901, 8230900, '0,8230900,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230902, 8230900, '0,8230900,8230000' , '新兴区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230903, 8230900, '0,8230900,8230000' , '桃山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230904, 8230900, '0,8230900,8230000' , '茄子河区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230921, 8230900, '0,8230900,8230000' , '勃利县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8230999, 8230900, '0,8230900,8230000' , '金沙新区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231000, 8230000, '0,8230000' , '牡丹江市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231001, 8231000, '0,8231000,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231002, 8231000, '0,8231000,8230000' , '东安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231003, 8231000, '0,8231000,8230000' , '阳明区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231004, 8231000, '0,8231000,8230000' , '爱民区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231005, 8231000, '0,8231000,8230000' , '西安区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231024, 8231000, '0,8231000,8230000' , '东宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231025, 8231000, '0,8231000,8230000' , '林口县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231081, 8231000, '0,8231000,8230000' , '绥芬河市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231083, 8231000, '0,8231000,8230000' , '海林市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231084, 8231000, '0,8231000,8230000' , '宁安市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231085, 8231000, '0,8231000,8230000' , '穆棱市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231100, 8230000, '0,8230000' , '黑河市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231101, 8231100, '0,8231100,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231102, 8231100, '0,8231100,8230000' , '爱辉区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231121, 8231100, '0,8231100,8230000' , '嫩江县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231123, 8231100, '0,8231100,8230000' , '逊克县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231124, 8231100, '0,8231100,8230000' , '孙吴县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231181, 8231100, '0,8231100,8230000' , '北安市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231182, 8231100, '0,8231100,8230000' , '五大连池市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231200, 8230000, '0,8230000' , '绥化市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231201, 8231200, '0,8231200,8230000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231202, 8231200, '0,8231200,8230000' , '北林区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231221, 8231200, '0,8231200,8230000' , '望奎县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231222, 8231200, '0,8231200,8230000' , '兰西县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231223, 8231200, '0,8231200,8230000' , '青冈县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231224, 8231200, '0,8231200,8230000' , '庆安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231225, 8231200, '0,8231200,8230000' , '明水县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231226, 8231200, '0,8231200,8230000' , '绥棱县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231281, 8231200, '0,8231200,8230000' , '安达市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231282, 8231200, '0,8231200,8230000' , '肇东市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8231283, 8231200, '0,8231200,8230000' , '海伦市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232700, 8230000, '0,8230000' , '大兴安岭地区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232701, 8232700, '0,8232700,8230000' , '加格达奇区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232702, 8232700, '0,8232700,8230000' , '松岭区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232703, 8232700, '0,8232700,8230000' , '新林区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232704, 8232700, '0,8232700,8230000' , '呼中区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232721, 8232700, '0,8232700,8230000' , '呼玛县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232722, 8232700, '0,8232700,8230000' , '塔河县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8232723, 8232700, '0,8232700,8230000' , '漠河县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310000, 0, '0' , '上海市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310100, 8310000, '0,8310000' , '上海市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310101, 8310100, '0,8310100,8310000' , '黄浦区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310104, 8310100, '0,8310100,8310000' , '徐汇区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310105, 8310100, '0,8310100,8310000' , '长宁区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310106, 8310100, '0,8310100,8310000' , '静安区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310107, 8310100, '0,8310100,8310000' , '普陀区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310108, 8310100, '0,8310100,8310000' , '闸北区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310109, 8310100, '0,8310100,8310000' , '虹口区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310110, 8310100, '0,8310100,8310000' , '杨浦区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310112, 8310100, '0,8310100,8310000' , '闵行区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310113, 8310100, '0,8310100,8310000' , '宝山区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310114, 8310100, '0,8310100,8310000' , '嘉定区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310115, 8310100, '0,8310100,8310000' , '浦东新区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310116, 8310100, '0,8310100,8310000' , '金山区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310117, 8310100, '0,8310100,8310000' , '松江区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310118, 8310100, '0,8310100,8310000' , '青浦区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310120, 8310100, '0,8310100,8310000' , '奉贤区', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310230, 8310100, '0,8310100,8310000' , '崇明县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310200, 8310000, '0,8310000' , '县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320000, 0, '0' , '江苏省', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320100, 8320000, '0,8320000' , '南京市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320101, 8320100, '0,8320100,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320102, 8320100, '0,8320100,8320000' , '玄武区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320103, 8320100, '0,8320100,8320000' , '白下区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320104, 8320100, '0,8320100,8320000' , '秦淮区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320105, 8320100, '0,8320100,8320000' , '建邺区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320106, 8320100, '0,8320100,8320000' , '鼓楼区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320107, 8320100, '0,8320100,8320000' , '下关区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320111, 8320100, '0,8320100,8320000' , '浦口区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320113, 8320100, '0,8320100,8320000' , '栖霞区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320114, 8320100, '0,8320100,8320000' , '雨花台区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320115, 8320100, '0,8320100,8320000' , '江宁区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320116, 8320100, '0,8320100,8320000' , '六合区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320124, 8320100, '0,8320100,8320000' , '溧水县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320125, 8320100, '0,8320100,8320000' , '高淳县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320200, 8320000, '0,8320000' , '无锡市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320201, 8320200, '0,8320200,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320202, 8320200, '0,8320200,8320000' , '崇安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320203, 8320200, '0,8320200,8320000' , '南长区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320204, 8320200, '0,8320200,8320000' , '北塘区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320205, 8320200, '0,8320200,8320000' , '锡山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320206, 8320200, '0,8320200,8320000' , '惠山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320211, 8320200, '0,8320200,8320000' , '滨湖区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320281, 8320200, '0,8320200,8320000' , '江阴市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320282, 8320200, '0,8320200,8320000' , '宜兴市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320214, 8320200, '0,8320200,8320000' , '新吴区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320685, 8320200, '0,8320200,8320000' , '新吴区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320300, 8320000, '0,8320000' , '徐州市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320301, 8320300, '0,8320300,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320302, 8320300, '0,8320300,8320000' , '鼓楼区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320303, 8320300, '0,8320300,8320000' , '云龙区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320305, 8320300, '0,8320300,8320000' , '贾汪区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320311, 8320300, '0,8320300,8320000' , '泉山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320312, 8320300, '0,8320300,8320000' , '铜山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320321, 8320300, '0,8320300,8320000' , '丰县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320322, 8320300, '0,8320300,8320000' , '沛县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320324, 8320300, '0,8320300,8320000' , '睢宁县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320381, 8320300, '0,8320300,8320000' , '新沂市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320382, 8320300, '0,8320300,8320000' , '邳州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320383, 8320300, '0,8320300,8320000' , '经济开发区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320384, 8320300, '0,8320300,8320000' , '新城区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320400, 8320000, '0,8320000' , '常州市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320401, 8320400, '0,8320400,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320402, 8320400, '0,8320400,8320000' , '天宁区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320404, 8320400, '0,8320400,8320000' , '钟楼区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320405, 8320400, '0,8320400,8320000' , '经开区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320411, 8320400, '0,8320400,8320000' , '新北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320412, 8320400, '0,8320400,8320000' , '武进区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320481, 8320400, '0,8320400,8320000' , '溧阳市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320482, 8320400, '0,8320400,8320000' , '金坛市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320500, 8320000, '0,8320000' , '苏州市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320501, 8320500, '0,8320500,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320505, 8320500, '0,8320500,8320000' , '虎丘区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320506, 8320500, '0,8320500,8320000' , '吴中区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320507, 8320500, '0,8320500,8320000' , '相城区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320508, 8320500, '0,8320500,8320000' , '姑苏区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320581, 8320500, '0,8320500,8320000' , '常熟市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320582, 8320500, '0,8320500,8320000' , '张家港市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320583, 8320500, '0,8320500,8320000' , '昆山市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320584, 8320500, '0,8320500,8320000' , '吴江市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320585, 8320500, '0,8320500,8320000' , '太仓市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320599, 8320500, '0,8320500,8320000' , '工业园区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320600, 8320000, '0,8320000' , '南通市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320601, 8320600, '0,8320600,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320602, 8320600, '0,8320600,8320000' , '崇川区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320603, 8320600, '0,8320600,8320000' , '开发区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320611, 8320600, '0,8320600,8320000' , '港闸区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320612, 8320600, '0,8320600,8320000' , '通州市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320621, 8320600, '0,8320600,8320000' , '海安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320623, 8320600, '0,8320600,8320000' , '如东县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320681, 8320600, '0,8320600,8320000' , '启东市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320682, 8320600, '0,8320600,8320000' , '如皋市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320684, 8320600, '0,8320600,8320000' , '海门市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320699, 8320600, '0,8320600,8320000' , '新城区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320700, 8320000, '0,8320000' , '连云港市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320701, 8320700, '0,8320700,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320703, 8320700, '0,8320700,8320000' , '连云区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320705, 8320700, '0,8320700,8320000' , '新浦区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320706, 8320700, '0,8320700,8320000' , '海州区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320721, 8320700, '0,8320700,8320000' , '赣榆县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320722, 8320700, '0,8320700,8320000' , '东海县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320723, 8320700, '0,8320700,8320000' , '灌云县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320724, 8320700, '0,8320700,8320000' , '灌南县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320799, 8320700, '0,8320700,8320000' , '高新区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320800, 8320000, '0,8320000' , '淮安市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320801, 8320800, '0,8320800,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320802, 8320800, '0,8320800,8320000' , '清河区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320803, 8320800, '0,8320800,8320000' , '淮安区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320804, 8320800, '0,8320800,8320000' , '淮阴区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320811, 8320800, '0,8320800,8320000' , '清浦区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320826, 8320800, '0,8320800,8320000' , '涟水县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320829, 8320800, '0,8320800,8320000' , '洪泽县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320830, 8320800, '0,8320800,8320000' , '盱眙县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320831, 8320800, '0,8320800,8320000' , '金湖县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320832, 8320800, '0,8320800,8320000' , '开发区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320900, 8320000, '0,8320000' , '盐城市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320901, 8320900, '0,8320900,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320902, 8320900, '0,8320900,8320000' , '亭湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320903, 8320900, '0,8320900,8320000' , '盐都区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320921, 8320900, '0,8320900,8320000' , '响水县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320922, 8320900, '0,8320900,8320000' , '滨海县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320923, 8320900, '0,8320900,8320000' , '阜宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320924, 8320900, '0,8320900,8320000' , '射阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320925, 8320900, '0,8320900,8320000' , '建湖县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320981, 8320900, '0,8320900,8320000' , '东台市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320982, 8320900, '0,8320900,8320000' , '大丰市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320999, 8320900, '0,8320900,8320000' , '城区区局', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320998, 8320900, '0,8320900,8320000' , '城南区局', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320997, 8320900, '0,8320900,8320000' , '开发区区局', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321000, 8320000, '0,8320000' , '扬州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321001, 8321000, '0,8321000,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321002, 8321000, '0,8321000,8320000' , '广陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321003, 8321000, '0,8321000,8320000' , '邗江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321011, 8321000, '0,8321000,8320000' , '维扬区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321023, 8321000, '0,8321000,8320000' , '宝应县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321081, 8321000, '0,8321000,8320000' , '仪征市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321084, 8321000, '0,8321000,8320000' , '高邮市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321088, 8321000, '0,8321000,8320000' , '江都市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321100, 8320000, '0,8320000' , '镇江市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321101, 8321100, '0,8321100,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321102, 8321100, '0,8321100,8320000' , '京口区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321111, 8321100, '0,8321100,8320000' , '润州区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321112, 8321100, '0,8321100,8320000' , '丹徒区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321181, 8321100, '0,8321100,8320000' , '丹阳市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321182, 8321100, '0,8321100,8320000' , '扬中市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321183, 8321100, '0,8321100,8320000' , '句容市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321199, 8321100, '0,8321100,8320000' , '新区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321200, 8320000, '0,8320000' , '泰州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321201, 8321200, '0,8321200,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321202, 8321200, '0,8321200,8320000' , '海陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321203, 8321200, '0,8321200,8320000' , '高港区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321281, 8321200, '0,8321200,8320000' , '兴化市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321282, 8321200, '0,8321200,8320000' , '靖江市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321283, 8321200, '0,8321200,8320000' , '泰兴市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321284, 8321200, '0,8321200,8320000' , '姜堰市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321300, 8320000, '0,8320000' , '宿迁市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321301, 8321300, '0,8321300,8320000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321302, 8321300, '0,8321300,8320000' , '宿城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321311, 8321300, '0,8321300,8320000' , '宿豫区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321322, 8321300, '0,8321300,8320000' , '沭阳县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321323, 8321300, '0,8321300,8320000' , '泗阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8321324, 8321300, '0,8321300,8320000' , '泗洪县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330000, 0, '0' , '浙江省', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330100, 8330000, '0,8330000' , '杭州市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330101, 8330100, '0,8330100,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330102, 8330100, '0,8330100,8330000' , '上城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330103, 8330100, '0,8330100,8330000' , '下城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330104, 8330100, '0,8330100,8330000' , '江干区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330105, 8330100, '0,8330100,8330000' , '拱墅区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330106, 8330100, '0,8330100,8330000' , '西湖区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330107, 8330100, '0,8330100,8330000' , '下沙区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330108, 8330100, '0,8330100,8330000' , '滨江区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330109, 8330100, '0,8330100,8330000' , '萧山区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330110, 8330100, '0,8330100,8330000' , '余杭区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330122, 8330100, '0,8330100,8330000' , '桐庐县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330127, 8330100, '0,8330100,8330000' , '淳安县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330182, 8330100, '0,8330100,8330000' , '建德市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330183, 8330100, '0,8330100,8330000' , '富阳市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330185, 8330100, '0,8330100,8330000' , '临安市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330200, 8330000, '0,8330000' , '宁波市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330201, 8330200, '0,8330200,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330203, 8330200, '0,8330200,8330000' , '海曙区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330204, 8330200, '0,8330200,8330000' , '江东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330205, 8330200, '0,8330200,8330000' , '江北区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330206, 8330200, '0,8330200,8330000' , '北仑区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330207, 8330200, '0,8330200,8330000' , '高新区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330211, 8330200, '0,8330200,8330000' , '镇海区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330212, 8330200, '0,8330200,8330000' , '鄞州区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330225, 8330200, '0,8330200,8330000' , '象山县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330226, 8330200, '0,8330200,8330000' , '宁海县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330281, 8330200, '0,8330200,8330000' , '余姚市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330282, 8330200, '0,8330200,8330000' , '慈溪市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330283, 8330200, '0,8330200,8330000' , '奉化区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330300, 8330000, '0,8330000' , '温州市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330301, 8330300, '0,8330300,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330302, 8330300, '0,8330300,8330000' , '鹿城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330303, 8330300, '0,8330300,8330000' , '龙湾区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330304, 8330300, '0,8330300,8330000' , '瓯海区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330322, 8330300, '0,8330300,8330000' , '洞头县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330324, 8330300, '0,8330300,8330000' , '永嘉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330326, 8330300, '0,8330300,8330000' , '平阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330327, 8330300, '0,8330300,8330000' , '苍南县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330328, 8330300, '0,8330300,8330000' , '文成县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330329, 8330300, '0,8330300,8330000' , '泰顺县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330381, 8330300, '0,8330300,8330000' , '瑞安市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330382, 8330300, '0,8330300,8330000' , '乐清市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330400, 8330000, '0,8330000' , '嘉兴市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330401, 8330400, '0,8330400,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330402, 8330400, '0,8330400,8330000' , '南湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330411, 8330400, '0,8330400,8330000' , '秀洲区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330421, 8330400, '0,8330400,8330000' , '嘉善县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330424, 8330400, '0,8330400,8330000' , '海盐县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330481, 8330400, '0,8330400,8330000' , '海宁市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330482, 8330400, '0,8330400,8330000' , '平湖市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330483, 8330400, '0,8330400,8330000' , '桐乡市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330500, 8330000, '0,8330000' , '湖州市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330501, 8330500, '0,8330500,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330502, 8330500, '0,8330500,8330000' , '吴兴区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330503, 8330500, '0,8330500,8330000' , '南浔区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330521, 8330500, '0,8330500,8330000' , '德清县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330522, 8330500, '0,8330500,8330000' , '长兴县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330523, 8330500, '0,8330500,8330000' , '安吉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330600, 8330000, '0,8330000' , '绍兴市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330601, 8330600, '0,8330600,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330602, 8330600, '0,8330600,8330000' , '越城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330621, 8330600, '0,8330600,8330000' , ' 柯桥', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330624, 8330600, '0,8330600,8330000' , '新昌', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330681, 8330600, '0,8330600,8330000' , '诸暨', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330682, 8330600, '0,8330600,8330000' , '上虞', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330683, 8330600, '0,8330600,8330000' , '嵊州', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330700, 8330000, '0,8330000' , '金华市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330701, 8330700, '0,8330700,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330702, 8330700, '0,8330700,8330000' , '婺城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330703, 8330700, '0,8330700,8330000' , '金东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330723, 8330700, '0,8330700,8330000' , '武义县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330726, 8330700, '0,8330700,8330000' , '浦江县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330727, 8330700, '0,8330700,8330000' , '磐安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330781, 8330700, '0,8330700,8330000' , '兰溪市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330782, 8330700, '0,8330700,8330000' , '义乌市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330783, 8330700, '0,8330700,8330000' , '东阳市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330784, 8330700, '0,8330700,8330000' , '永康市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330800, 8330000, '0,8330000' , '衢州市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330801, 8330800, '0,8330800,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330802, 8330800, '0,8330800,8330000' , '柯城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330803, 8330800, '0,8330800,8330000' , '衢江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330822, 8330800, '0,8330800,8330000' , '常山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330824, 8330800, '0,8330800,8330000' , '开化县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330825, 8330800, '0,8330800,8330000' , '龙游县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330881, 8330800, '0,8330800,8330000' , '江山市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330900, 8330000, '0,8330000' , '舟山市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330901, 8330900, '0,8330900,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330902, 8330900, '0,8330900,8330000' , '定海区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330903, 8330900, '0,8330900,8330000' , '普陀区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330921, 8330900, '0,8330900,8330000' , '岱山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8330922, 8330900, '0,8330900,8330000' , '嵊泗县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331000, 8330000, '0,8330000' , '台州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331001, 8331000, '0,8331000,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331002, 8331000, '0,8331000,8330000' , '椒江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331003, 8331000, '0,8331000,8330000' , '黄岩区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331004, 8331000, '0,8331000,8330000' , '路桥区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331021, 8331000, '0,8331000,8330000' , '玉环县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331022, 8331000, '0,8331000,8330000' , '三门县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331023, 8331000, '0,8331000,8330000' , '天台县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331024, 8331000, '0,8331000,8330000' , '仙居县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331081, 8331000, '0,8331000,8330000' , '温岭市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331082, 8331000, '0,8331000,8330000' , '临海市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331100, 8330000, '0,8330000' , '丽水市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331101, 8331100, '0,8331100,8330000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331102, 8331100, '0,8331100,8330000' , '莲都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331121, 8331100, '0,8331100,8330000' , '青田县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331122, 8331100, '0,8331100,8330000' , '缙云县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331123, 8331100, '0,8331100,8330000' , '遂昌县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331124, 8331100, '0,8331100,8330000' , '松阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331125, 8331100, '0,8331100,8330000' , '云和县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331126, 8331100, '0,8331100,8330000' , '庆元县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331127, 8331100, '0,8331100,8330000' , '景宁畲族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331181, 8331100, '0,8331100,8330000' , '龙泉市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8331199, 8331100, '0,8331100,8330000' , '南城区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340000, 0, '0' , '安徽省', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340100, 8340000, '0,8340000' , '合肥市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340101, 8340100, '0,8340100,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340102, 8340100, '0,8340100,8340000' , '瑶海区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340103, 8340100, '0,8340100,8340000' , '庐阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340104, 8340100, '0,8340100,8340000' , '蜀山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340105, 8340100, '0,8340100,8340000' , '居巢区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340111, 8340100, '0,8340100,8340000' , '包河区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340121, 8340100, '0,8340100,8340000' , '长丰县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340122, 8340100, '0,8340100,8340000' , '肥东县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340123, 8340100, '0,8340100,8340000' , '肥西县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340124, 8340100, '0,8340100,8340000' , '庐江县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340181, 8340100, '0,8340100,8340000' , '巢湖市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340200, 8340000, '0,8340000' , '芜湖市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340201, 8340200, '0,8340200,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340202, 8340200, '0,8340200,8340000' , '镜湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340203, 8340200, '0,8340200,8340000' , '弋江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340207, 8340200, '0,8340200,8340000' , '鸠江区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340208, 8340200, '0,8340200,8340000' , '三山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340221, 8340200, '0,8340200,8340000' , '芜湖县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340222, 8340200, '0,8340200,8340000' , '繁昌县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340223, 8340200, '0,8340200,8340000' , '南陵县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340224, 8340200, '0,8340200,8340000' , '无为县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340300, 8340000, '0,8340000' , '蚌埠市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340301, 8340300, '0,8340300,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340302, 8340300, '0,8340300,8340000' , '龙子湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340303, 8340300, '0,8340300,8340000' , '蚌山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340304, 8340300, '0,8340300,8340000' , '禹会区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340311, 8340300, '0,8340300,8340000' , '淮上区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340321, 8340300, '0,8340300,8340000' , '怀远县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340322, 8340300, '0,8340300,8340000' , '五河县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340323, 8340300, '0,8340300,8340000' , '固镇县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340400, 8340000, '0,8340000' , '淮南市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340401, 8340400, '0,8340400,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340402, 8340400, '0,8340400,8340000' , '大通区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340403, 8340400, '0,8340400,8340000' , '田家庵区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340404, 8340400, '0,8340400,8340000' , '谢家集区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340405, 8340400, '0,8340400,8340000' , '八公山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340406, 8340400, '0,8340400,8340000' , '潘集区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340421, 8340400, '0,8340400,8340000' , '凤台县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340499, 8340400, '0,8340400,8340000' , '寿县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340500, 8340000, '0,8340000' , '马鞍山市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340501, 8340500, '0,8340500,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340502, 8340500, '0,8340500,8340000' , '金家庄区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340503, 8340500, '0,8340500,8340000' , '花山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340504, 8340500, '0,8340500,8340000' , '雨山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340521, 8340500, '0,8340500,8340000' , '当涂县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340522, 8340500, '0,8340500,8340000' , '含山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340523, 8340500, '0,8340500,8340000' , '和县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340600, 8340000, '0,8340000' , '淮北市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340601, 8340600, '0,8340600,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340602, 8340600, '0,8340600,8340000' , '杜集区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340603, 8340600, '0,8340600,8340000' , '相山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340604, 8340600, '0,8340600,8340000' , '烈山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340621, 8340600, '0,8340600,8340000' , '濉溪县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340700, 8340000, '0,8340000' , '铜陵市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340701, 8340700, '0,8340700,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340702, 8340700, '0,8340700,8340000' , '铜官山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340703, 8340700, '0,8340700,8340000' , '狮子山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340711, 8340700, '0,8340700,8340000' , '郊区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340721, 8340700, '0,8340700,8340000' , '铜陵县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340799, 8340700, '0,8340700,8340000' , '枞阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340800, 8340000, '0,8340000' , '安庆市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340801, 8340800, '0,8340800,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340802, 8340800, '0,8340800,8340000' , '迎江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340803, 8340800, '0,8340800,8340000' , '大观区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340811, 8340800, '0,8340800,8340000' , '宜秀区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340822, 8340800, '0,8340800,8340000' , '怀宁县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340823, 8340800, '0,8340800,8340000' , '枞阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340824, 8340800, '0,8340800,8340000' , '潜山县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340825, 8340800, '0,8340800,8340000' , '太湖县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340826, 8340800, '0,8340800,8340000' , '宿松县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340827, 8340800, '0,8340800,8340000' , '望江县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340828, 8340800, '0,8340800,8340000' , '岳西县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8340881, 8340800, '0,8340800,8340000' , '桐城市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341000, 8340000, '0,8340000' , '黄山市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341001, 8341000, '0,8341000,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341002, 8341000, '0,8341000,8340000' , '屯溪区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341003, 8341000, '0,8341000,8340000' , '黄山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341004, 8341000, '0,8341000,8340000' , '徽州区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341021, 8341000, '0,8341000,8340000' , '歙县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341022, 8341000, '0,8341000,8340000' , '休宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341023, 8341000, '0,8341000,8340000' , '黟县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341024, 8341000, '0,8341000,8340000' , '祁门县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341100, 8340000, '0,8340000' , '滁州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341101, 8341100, '0,8341100,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341102, 8341100, '0,8341100,8340000' , '琅琊区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341103, 8341100, '0,8341100,8340000' , '南谯区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341122, 8341100, '0,8341100,8340000' , '来安县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341124, 8341100, '0,8341100,8340000' , '全椒县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341125, 8341100, '0,8341100,8340000' , '定远县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341126, 8341100, '0,8341100,8340000' , '凤阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341181, 8341100, '0,8341100,8340000' , '天长市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341182, 8341100, '0,8341100,8340000' , '明光市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341200, 8340000, '0,8340000' , '阜阳市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341201, 8341200, '0,8341200,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341202, 8341200, '0,8341200,8340000' , '颍州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341203, 8341200, '0,8341200,8340000' , '颍东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341204, 8341200, '0,8341200,8340000' , '颍泉区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341221, 8341200, '0,8341200,8340000' , '临泉县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341222, 8341200, '0,8341200,8340000' , '太和县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341225, 8341200, '0,8341200,8340000' , '阜南县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341226, 8341200, '0,8341200,8340000' , '颍上县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341282, 8341200, '0,8341200,8340000' , '界首市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341300, 8340000, '0,8340000' , '宿州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341301, 8341300, '0,8341300,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341302, 8341300, '0,8341300,8340000' , '埇桥区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341321, 8341300, '0,8341300,8340000' , '砀山县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341322, 8341300, '0,8341300,8340000' , '萧县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341323, 8341300, '0,8341300,8340000' , '灵璧县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341324, 8341300, '0,8341300,8340000' , '泗县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341500, 8340000, '0,8340000' , '六安市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341501, 8341500, '0,8341500,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341502, 8341500, '0,8341500,8340000' , '金安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341503, 8341500, '0,8341500,8340000' , '裕安区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341521, 8341500, '0,8341500,8340000' , '寿县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341522, 8341500, '0,8341500,8340000' , '霍邱县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341523, 8341500, '0,8341500,8340000' , '舒城县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341524, 8341500, '0,8341500,8340000' , '金寨县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341525, 8341500, '0,8341500,8340000' , '霍山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341600, 8340000, '0,8340000' , '亳州市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341601, 8341600, '0,8341600,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341602, 8341600, '0,8341600,8340000' , '谯城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341621, 8341600, '0,8341600,8340000' , '涡阳县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341622, 8341600, '0,8341600,8340000' , '蒙城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341623, 8341600, '0,8341600,8340000' , '利辛县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341700, 8340000, '0,8340000' , '池州市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341701, 8341700, '0,8341700,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341702, 8341700, '0,8341700,8340000' , '贵池区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341721, 8341700, '0,8341700,8340000' , '东至县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341722, 8341700, '0,8341700,8340000' , '石台县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341723, 8341700, '0,8341700,8340000' , '青阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341800, 8340000, '0,8340000' , '宣城市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341801, 8341800, '0,8341800,8340000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341802, 8341800, '0,8341800,8340000' , '宣州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341821, 8341800, '0,8341800,8340000' , '郎溪县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341822, 8341800, '0,8341800,8340000' , '广德县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341823, 8341800, '0,8341800,8340000' , '泾县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341824, 8341800, '0,8341800,8340000' , '绩溪县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341825, 8341800, '0,8341800,8340000' , '旌德县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341881, 8341800, '0,8341800,8340000' , '宁国市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350000, 0, '0' , '福建省', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350100, 8350000, '0,8350000' , '福州市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350101, 8350100, '0,8350100,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350102, 8350100, '0,8350100,8350000' , '鼓楼区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350103, 8350100, '0,8350100,8350000' , '台江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350104, 8350100, '0,8350100,8350000' , '仓山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350105, 8350100, '0,8350100,8350000' , '马尾区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350111, 8350100, '0,8350100,8350000' , '晋安区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350121, 8350100, '0,8350100,8350000' , '闽侯县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350122, 8350100, '0,8350100,8350000' , '连江县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350123, 8350100, '0,8350100,8350000' , '罗源县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350124, 8350100, '0,8350100,8350000' , '闽清县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350125, 8350100, '0,8350100,8350000' , '永泰县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350128, 8350100, '0,8350100,8350000' , '平潭县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350181, 8350100, '0,8350100,8350000' , '福清市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350182, 8350100, '0,8350100,8350000' , '长乐市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350183, 8350100, '0,8350100,8350000' , '鼓楼西', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350185, 8350100, '0,8350100,8350000' , '仓山东', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350200, 8350000, '0,8350000' , '厦门市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350201, 8350200, '0,8350200,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350203, 8350200, '0,8350200,8350000' , '思明区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350205, 8350200, '0,8350200,8350000' , '海沧区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350206, 8350200, '0,8350200,8350000' , '湖里区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350208, 8350200, '0,8350200,8350000' , '滨北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350209, 8350200, '0,8350200,8350000' , '环岛区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350210, 8350200, '0,8350200,8350000' , '自贸区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350211, 8350200, '0,8350200,8350000' , '集美区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350212, 8350200, '0,8350200,8350000' , '同安区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350213, 8350200, '0,8350200,8350000' , '翔安区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350300, 8350000, '0,8350000' , '莆田市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350301, 8350300, '0,8350300,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350302, 8350300, '0,8350300,8350000' , '城厢区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350303, 8350300, '0,8350300,8350000' , '涵江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350304, 8350300, '0,8350300,8350000' , '荔城区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350305, 8350300, '0,8350300,8350000' , '秀屿区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350322, 8350300, '0,8350300,8350000' , '仙游县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350400, 8350000, '0,8350000' , '三明市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350401, 8350400, '0,8350400,8350000' , '梅列区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350402, 8350400, '0,8350400,8350000' , '梅列区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350403, 8350400, '0,8350400,8350000' , '三元区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350421, 8350400, '0,8350400,8350000' , '明溪县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350423, 8350400, '0,8350400,8350000' , '清流县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350424, 8350400, '0,8350400,8350000' , '宁化县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350425, 8350400, '0,8350400,8350000' , '大田县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350426, 8350400, '0,8350400,8350000' , '尤溪县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350427, 8350400, '0,8350400,8350000' , '沙县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350428, 8350400, '0,8350400,8350000' , '将乐县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350429, 8350400, '0,8350400,8350000' , '泰宁县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350430, 8350400, '0,8350400,8350000' , '建宁县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350481, 8350400, '0,8350400,8350000' , '永安市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350500, 8350000, '0,8350000' , '泉州市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350501, 8350500, '0,8350500,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350502, 8350500, '0,8350500,8350000' , '鲤城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350503, 8350500, '0,8350500,8350000' , '丰泽区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350504, 8350500, '0,8350500,8350000' , '洛江区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350505, 8350500, '0,8350500,8350000' , '泉港区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350521, 8350500, '0,8350500,8350000' , '惠安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350524, 8350500, '0,8350500,8350000' , '安溪县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350525, 8350500, '0,8350500,8350000' , '永春县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350526, 8350500, '0,8350500,8350000' , '德化县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350527, 8350500, '0,8350500,8350000' , '金门县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350581, 8350500, '0,8350500,8350000' , '石狮市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350582, 8350500, '0,8350500,8350000' , '晋江市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350583, 8350500, '0,8350500,8350000' , '南安市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350599, 8350500, '0,8350500,8350000' , '台商区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350600, 8350000, '0,8350000' , '漳州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350601, 8350600, '0,8350600,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350602, 8350600, '0,8350600,8350000' , '芗城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350603, 8350600, '0,8350600,8350000' , '龙文区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350622, 8350600, '0,8350600,8350000' , '云霄县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350623, 8350600, '0,8350600,8350000' , '漳浦县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350624, 8350600, '0,8350600,8350000' , '诏安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350625, 8350600, '0,8350600,8350000' , '长泰县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350626, 8350600, '0,8350600,8350000' , '东山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350627, 8350600, '0,8350600,8350000' , '南靖县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350628, 8350600, '0,8350600,8350000' , '平和县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350629, 8350600, '0,8350600,8350000' , '华安县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350681, 8350600, '0,8350600,8350000' , '龙海市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350699, 8350600, '0,8350600,8350000' , '台商投资区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350700, 8350000, '0,8350000' , '南平市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350701, 8350700, '0,8350700,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350702, 8350700, '0,8350700,8350000' , '延平区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350721, 8350700, '0,8350700,8350000' , '顺昌县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350722, 8350700, '0,8350700,8350000' , '浦城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350723, 8350700, '0,8350700,8350000' , '光泽县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350724, 8350700, '0,8350700,8350000' , '松溪县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350725, 8350700, '0,8350700,8350000' , '政和县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350781, 8350700, '0,8350700,8350000' , '邵武市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350782, 8350700, '0,8350700,8350000' , '武夷山市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350783, 8350700, '0,8350700,8350000' , '建瓯市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350784, 8350700, '0,8350700,8350000' , '建阳市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350800, 8350000, '0,8350000' , '龙岩市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350801, 8350800, '0,8350800,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350802, 8350800, '0,8350800,8350000' , '新罗区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350803, 8350800, '0,8350800,8350000' , '高新开发区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350821, 8350800, '0,8350800,8350000' , '长汀县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350822, 8350800, '0,8350800,8350000' , '永定县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350823, 8350800, '0,8350800,8350000' , '上杭县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350824, 8350800, '0,8350800,8350000' , '武平县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350825, 8350800, '0,8350800,8350000' , '连城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350881, 8350800, '0,8350800,8350000' , '漳平市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350900, 8350000, '0,8350000' , '宁德市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350901, 8350900, '0,8350900,8350000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350902, 8350900, '0,8350900,8350000' , '蕉城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350903, 8350900, '0,8350900,8350000' , '东侨区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350921, 8350900, '0,8350900,8350000' , '霞浦县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350922, 8350900, '0,8350900,8350000' , '古田县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350923, 8350900, '0,8350900,8350000' , '屏南县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350924, 8350900, '0,8350900,8350000' , '寿宁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350925, 8350900, '0,8350900,8350000' , '周宁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350926, 8350900, '0,8350900,8350000' , '柘荣县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350981, 8350900, '0,8350900,8350000' , '福安市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8350982, 8350900, '0,8350900,8350000' , '福鼎市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360000, 0, '0' , '江西省', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360100, 8360000, '0,8360000' , '南昌市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360101, 8360100, '0,8360100,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360102, 8360100, '0,8360100,8360000' , '东湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360103, 8360100, '0,8360100,8360000' , '西湖区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360104, 8360100, '0,8360100,8360000' , '青云谱区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360105, 8360100, '0,8360100,8360000' , '湾里区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360111, 8360100, '0,8360100,8360000' , '青山湖区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360121, 8360100, '0,8360100,8360000' , '南昌县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360122, 8360100, '0,8360100,8360000' , '新建县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360123, 8360100, '0,8360100,8360000' , '安义县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360124, 8360100, '0,8360100,8360000' , '进贤县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360200, 8360000, '0,8360000' , '景德镇市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360201, 8360200, '0,8360200,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360202, 8360200, '0,8360200,8360000' , '昌江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360203, 8360200, '0,8360200,8360000' , '珠山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360222, 8360200, '0,8360200,8360000' , '浮梁县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360281, 8360200, '0,8360200,8360000' , '乐平市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360300, 8360000, '0,8360000' , '萍乡市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360301, 8360300, '0,8360300,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360302, 8360300, '0,8360300,8360000' , '安源区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360313, 8360300, '0,8360300,8360000' , '湘东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360321, 8360300, '0,8360300,8360000' , '莲花县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360322, 8360300, '0,8360300,8360000' , '上栗县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360323, 8360300, '0,8360300,8360000' , '芦溪县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360400, 8360000, '0,8360000' , '九江市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360401, 8360400, '0,8360400,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360402, 8360400, '0,8360400,8360000' , '庐山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360403, 8360400, '0,8360400,8360000' , '浔阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360421, 8360400, '0,8360400,8360000' , '九江县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360423, 8360400, '0,8360400,8360000' , '武宁县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360424, 8360400, '0,8360400,8360000' , '修水县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360425, 8360400, '0,8360400,8360000' , '永修县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360426, 8360400, '0,8360400,8360000' , '德安县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360427, 8360400, '0,8360400,8360000' , '星子县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360428, 8360400, '0,8360400,8360000' , '都昌县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360429, 8360400, '0,8360400,8360000' , '湖口县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360430, 8360400, '0,8360400,8360000' , '彭泽县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360481, 8360400, '0,8360400,8360000' , '瑞昌市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360482, 8360400, '0,8360400,8360000' , '共青城市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360500, 8360000, '0,8360000' , '新余市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360501, 8360500, '0,8360500,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360502, 8360500, '0,8360500,8360000' , '渝水区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360521, 8360500, '0,8360500,8360000' , '分宜县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360600, 8360000, '0,8360000' , '鹰潭市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360601, 8360600, '0,8360600,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360602, 8360600, '0,8360600,8360000' , '月湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360622, 8360600, '0,8360600,8360000' , '余江县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360681, 8360600, '0,8360600,8360000' , '贵溪市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360700, 8360000, '0,8360000' , '赣州市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360701, 8360700, '0,8360700,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360702, 8360700, '0,8360700,8360000' , '章贡区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360721, 8360700, '0,8360700,8360000' , '赣县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360722, 8360700, '0,8360700,8360000' , '信丰县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360723, 8360700, '0,8360700,8360000' , '大余县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360724, 8360700, '0,8360700,8360000' , '上犹县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360725, 8360700, '0,8360700,8360000' , '崇义县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360726, 8360700, '0,8360700,8360000' , '安远县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360727, 8360700, '0,8360700,8360000' , '龙南县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360728, 8360700, '0,8360700,8360000' , '定南县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360729, 8360700, '0,8360700,8360000' , '全南县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360730, 8360700, '0,8360700,8360000' , '宁都县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360731, 8360700, '0,8360700,8360000' , '于都县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360732, 8360700, '0,8360700,8360000' , '兴国县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360733, 8360700, '0,8360700,8360000' , '会昌县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360734, 8360700, '0,8360700,8360000' , '寻乌县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360735, 8360700, '0,8360700,8360000' , '石城县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360781, 8360700, '0,8360700,8360000' , '瑞金市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360782, 8360700, '0,8360700,8360000' , '南康市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360800, 8360000, '0,8360000' , '吉安市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360801, 8360800, '0,8360800,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360802, 8360800, '0,8360800,8360000' , '吉州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360803, 8360800, '0,8360800,8360000' , '青原区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360821, 8360800, '0,8360800,8360000' , '吉安县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360822, 8360800, '0,8360800,8360000' , '吉水县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360823, 8360800, '0,8360800,8360000' , '峡江县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360824, 8360800, '0,8360800,8360000' , '新干县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360825, 8360800, '0,8360800,8360000' , '永丰县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360826, 8360800, '0,8360800,8360000' , '泰和县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360827, 8360800, '0,8360800,8360000' , '遂川县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360828, 8360800, '0,8360800,8360000' , '万安县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360829, 8360800, '0,8360800,8360000' , '安福县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360830, 8360800, '0,8360800,8360000' , '永新县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360881, 8360800, '0,8360800,8360000' , '井冈山市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360900, 8360000, '0,8360000' , '宜春市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360901, 8360900, '0,8360900,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360902, 8360900, '0,8360900,8360000' , '袁州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360921, 8360900, '0,8360900,8360000' , '奉新县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360922, 8360900, '0,8360900,8360000' , '万载县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360923, 8360900, '0,8360900,8360000' , '上高县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360924, 8360900, '0,8360900,8360000' , '宜丰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360925, 8360900, '0,8360900,8360000' , '靖安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360926, 8360900, '0,8360900,8360000' , '铜鼓县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360981, 8360900, '0,8360900,8360000' , '丰城市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360982, 8360900, '0,8360900,8360000' , '樟树市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8360983, 8360900, '0,8360900,8360000' , '高安市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361000, 8360000, '0,8360000' , '抚州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361001, 8361000, '0,8361000,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361002, 8361000, '0,8361000,8360000' , '临川区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361021, 8361000, '0,8361000,8360000' , '南城县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361022, 8361000, '0,8361000,8360000' , '黎川县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361023, 8361000, '0,8361000,8360000' , '南丰县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361024, 8361000, '0,8361000,8360000' , '崇仁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361025, 8361000, '0,8361000,8360000' , '乐安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361026, 8361000, '0,8361000,8360000' , '宜黄县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361027, 8361000, '0,8361000,8360000' , '金溪县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361028, 8361000, '0,8361000,8360000' , '资溪县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361029, 8361000, '0,8361000,8360000' , '东乡县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361030, 8361000, '0,8361000,8360000' , '广昌县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361100, 8360000, '0,8360000' , '上饶市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361101, 8361100, '0,8361100,8360000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361102, 8361100, '0,8361100,8360000' , '信州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361121, 8361100, '0,8361100,8360000' , '上饶县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361122, 8361100, '0,8361100,8360000' , '广丰县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361123, 8361100, '0,8361100,8360000' , '玉山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361124, 8361100, '0,8361100,8360000' , '铅山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361125, 8361100, '0,8361100,8360000' , '横峰县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361126, 8361100, '0,8361100,8360000' , '弋阳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361127, 8361100, '0,8361100,8360000' , '余干县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361128, 8361100, '0,8361100,8360000' , '鄱阳县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361129, 8361100, '0,8361100,8360000' , '万年县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361130, 8361100, '0,8361100,8360000' , '婺源县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8361181, 8361100, '0,8361100,8360000' , '德兴市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370000, 0, '0' , '山东省', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370100, 8370000, '0,8370000' , '济南市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370101, 8370100, '0,8370100,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370102, 8370100, '0,8370100,8370000' , '历下区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370103, 8370100, '0,8370100,8370000' , '市中区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370104, 8370100, '0,8370100,8370000' , '槐荫区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370105, 8370100, '0,8370100,8370000' , '天桥区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370112, 8370100, '0,8370100,8370000' , '历城区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370113, 8370100, '0,8370100,8370000' , '长清区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370124, 8370100, '0,8370100,8370000' , '平阴县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370125, 8370100, '0,8370100,8370000' , '济阳县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370126, 8370100, '0,8370100,8370000' , '商河县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370181, 8370100, '0,8370100,8370000' , '章丘市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370182, 8370100, '0,8370100,8370000' , '高新区分公司', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370183, 8370100, '0,8370100,8370000' , '济南政企部', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370200, 8370000, '0,8370000' , '青岛市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370201, 8370200, '0,8370200,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370202, 8370200, '0,8370200,8370000' , '市南区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370203, 8370200, '0,8370200,8370000' , '市北区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370205, 8370200, '0,8370200,8370000' , '四方区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370211, 8370200, '0,8370200,8370000' , '黄岛区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370212, 8370200, '0,8370200,8370000' , '崂山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370213, 8370200, '0,8370200,8370000' , '李沧区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370214, 8370200, '0,8370200,8370000' , '城阳区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370281, 8370200, '0,8370200,8370000' , '胶州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370282, 8370200, '0,8370200,8370000' , '即墨市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370283, 8370200, '0,8370200,8370000' , '平度市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370284, 8370200, '0,8370200,8370000' , '胶南市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370285, 8370200, '0,8370200,8370000' , '莱西市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370286, 8370200, '0,8370200,8370000' , '高新区分公司', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370287, 8370200, '0,8370200,8370000' , '开发区分公司', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370300, 8370000, '0,8370000' , '淄博市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370301, 8370300, '0,8370300,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370302, 8370300, '0,8370300,8370000' , '淄川区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370303, 8370300, '0,8370300,8370000' , '张店区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370304, 8370300, '0,8370300,8370000' , '博山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370305, 8370300, '0,8370300,8370000' , '临淄区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370306, 8370300, '0,8370300,8370000' , '周村区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370321, 8370300, '0,8370300,8370000' , '桓台县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370322, 8370300, '0,8370300,8370000' , '高青县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370323, 8370300, '0,8370300,8370000' , '沂源县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370324, 8370300, '0,8370300,8370000' , '郊区分公司', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370325, 8370300, '0,8370300,8370000' , '开发区分公司', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370399, 8370300, '0,8370300,8370000' , '淄博_本部', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370400, 8370000, '0,8370000' , '枣庄市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370401, 8370400, '0,8370400,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370402, 8370400, '0,8370400,8370000' , '市中区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370403, 8370400, '0,8370400,8370000' , '薛城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370404, 8370400, '0,8370400,8370000' , '峄城区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370405, 8370400, '0,8370400,8370000' , '台儿庄区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370406, 8370400, '0,8370400,8370000' , '山亭区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370481, 8370400, '0,8370400,8370000' , '滕州市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370482, 8370400, '0,8370400,8370000' , '高新区分公司', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370499, 8370400, '0,8370400,8370000' , '枣庄_本部', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370500, 8370000, '0,8370000' , '东营市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370501, 8370500, '0,8370500,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370502, 8370500, '0,8370500,8370000' , '东营区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370503, 8370500, '0,8370500,8370000' , '河口区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370521, 8370500, '0,8370500,8370000' , '垦利县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370522, 8370500, '0,8370500,8370000' , '利津县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370523, 8370500, '0,8370500,8370000' , '广饶县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370524, 8370500, '0,8370500,8370000' , '经济开发区分公司', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370599, 8370500, '0,8370500,8370000' , '东营_本部', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370600, 8370000, '0,8370000' , '烟台市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370601, 8370600, '0,8370600,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370602, 8370600, '0,8370600,8370000' , '芝罘区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370611, 8370600, '0,8370600,8370000' , '福山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370612, 8370600, '0,8370600,8370000' , '牟平区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370613, 8370600, '0,8370600,8370000' , '莱山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370634, 8370600, '0,8370600,8370000' , '长岛县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370681, 8370600, '0,8370600,8370000' , '龙口市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370682, 8370600, '0,8370600,8370000' , '莱阳市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370683, 8370600, '0,8370600,8370000' , '莱州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370684, 8370600, '0,8370600,8370000' , '蓬莱市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370685, 8370600, '0,8370600,8370000' , '招远市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370686, 8370600, '0,8370600,8370000' , '栖霞市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370687, 8370600, '0,8370600,8370000' , '海阳市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370688, 8370600, '0,8370600,8370000' , '开发区分公司', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370689, 8370600, '0,8370600,8370000' , '高新区分公司', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370699, 8370600, '0,8370600,8370000' , '烟台_本部', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370700, 8370000, '0,8370000' , '潍坊市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370701, 8370700, '0,8370700,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370702, 8370700, '0,8370700,8370000' , '潍城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370703, 8370700, '0,8370700,8370000' , '寒亭区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370704, 8370700, '0,8370700,8370000' , '坊子区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370705, 8370700, '0,8370700,8370000' , '奎文区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370724, 8370700, '0,8370700,8370000' , '临朐县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370725, 8370700, '0,8370700,8370000' , '昌乐县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370781, 8370700, '0,8370700,8370000' , '青州市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370782, 8370700, '0,8370700,8370000' , '诸城市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370783, 8370700, '0,8370700,8370000' , '寿光市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370784, 8370700, '0,8370700,8370000' , '安丘市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370785, 8370700, '0,8370700,8370000' , '高密市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370786, 8370700, '0,8370700,8370000' , '昌邑市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370787, 8370700, '0,8370700,8370000' , '高新技术开发区分公司', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370788, 8370700, '0,8370700,8370000' , '滨海开发区分公司', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370789, 8370700, '0,8370700,8370000' , '峡山分公司', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370799, 8370700, '0,8370700,8370000' , '潍坊_本部', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370800, 8370000, '0,8370000' , '济宁市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370801, 8370800, '0,8370800,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370802, 8370800, '0,8370800,8370000' , '市中区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370811, 8370800, '0,8370800,8370000' , '任城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370826, 8370800, '0,8370800,8370000' , '微山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370827, 8370800, '0,8370800,8370000' , '鱼台县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370828, 8370800, '0,8370800,8370000' , '金乡县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370829, 8370800, '0,8370800,8370000' , '嘉祥县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370830, 8370800, '0,8370800,8370000' , '汶上县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370831, 8370800, '0,8370800,8370000' , '泗水县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370832, 8370800, '0,8370800,8370000' , '梁山县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370881, 8370800, '0,8370800,8370000' , '曲阜市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370882, 8370800, '0,8370800,8370000' , '兖州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370883, 8370800, '0,8370800,8370000' , '邹城市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370884, 8370800, '0,8370800,8370000' , '高新区分公司', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370885, 8370800, '0,8370800,8370000' , '北湖区分公司', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370900, 8370000, '0,8370000' , '泰安市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370901, 8370900, '0,8370900,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370902, 8370900, '0,8370900,8370000' , '泰山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370911, 8370900, '0,8370900,8370000' , '岱岳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370921, 8370900, '0,8370900,8370000' , '宁阳县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370923, 8370900, '0,8370900,8370000' , '东平县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370982, 8370900, '0,8370900,8370000' , '新泰市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370983, 8370900, '0,8370900,8370000' , '肥城市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370984, 8370900, '0,8370900,8370000' , '泰安高新区分公司', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8370999, 8370900, '0,8370900,8370000' , '泰安_本部', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371000, 8370000, '0,8370000' , '威海市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371001, 8371000, '0,8371000,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371002, 8371000, '0,8371000,8370000' , '环翠区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371081, 8371000, '0,8371000,8370000' , '文登市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371082, 8371000, '0,8371000,8370000' , '荣成市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371083, 8371000, '0,8371000,8370000' , '乳山市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371084, 8371000, '0,8371000,8370000' , '经区分公司', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371085, 8371000, '0,8371000,8370000' , '石岛分公司', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371086, 8371000, '0,8371000,8370000' , '高区分公司', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371099, 8371000, '0,8371000,8370000' , '威海_本部', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371100, 8370000, '0,8370000' , '日照市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371101, 8371100, '0,8371100,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371102, 8371100, '0,8371100,8370000' , '东港区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371103, 8371100, '0,8371100,8370000' , '岚山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371121, 8371100, '0,8371100,8370000' , '五莲县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371122, 8371100, '0,8371100,8370000' , '莒县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371198, 8371100, '0,8371100,8370000' , '日照市本部', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371200, 8370000, '0,8370000' , '莱芜市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371201, 8371200, '0,8371200,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371202, 8371200, '0,8371200,8370000' , '莱城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371203, 8371200, '0,8371200,8370000' , '钢城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371204, 8371200, '0,8371200,8370000' , '高新区分公司', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371205, 8371200, '0,8371200,8370000' , '经济开发区分公司', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371299, 8371200, '0,8371200,8370000' , '莱芜_本部', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371300, 8370000, '0,8370000' , '临沂市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371301, 8371300, '0,8371300,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371302, 8371300, '0,8371300,8370000' , '兰山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371311, 8371300, '0,8371300,8370000' , '罗庄区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371312, 8371300, '0,8371300,8370000' , '河东区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371321, 8371300, '0,8371300,8370000' , '沂南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371322, 8371300, '0,8371300,8370000' , '郯城县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371323, 8371300, '0,8371300,8370000' , '沂水县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371324, 8371300, '0,8371300,8370000' , '苍山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371325, 8371300, '0,8371300,8370000' , '费县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371326, 8371300, '0,8371300,8370000' , '平邑县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371327, 8371300, '0,8371300,8370000' , '莒南县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371328, 8371300, '0,8371300,8370000' , '蒙阴县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371329, 8371300, '0,8371300,8370000' , '临沭县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371330, 8371300, '0,8371300,8370000' , '临港区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371398, 8371300, '0,8371300,8370000' , '高新区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371399, 8371300, '0,8371300,8370000' , '经济开发区', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371397, 8371300, '0,8371300,8370000' , '临沂_本部', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371400, 8370000, '0,8370000' , '德州市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371401, 8371400, '0,8371400,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371402, 8371400, '0,8371400,8370000' , '德城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371421, 8371400, '0,8371400,8370000' , '陵县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371422, 8371400, '0,8371400,8370000' , '宁津县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371423, 8371400, '0,8371400,8370000' , '庆云县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371424, 8371400, '0,8371400,8370000' , '临邑县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371425, 8371400, '0,8371400,8370000' , '齐河县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371426, 8371400, '0,8371400,8370000' , '平原县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371427, 8371400, '0,8371400,8370000' , '夏津县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371428, 8371400, '0,8371400,8370000' , '武城县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371481, 8371400, '0,8371400,8370000' , '乐陵市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371482, 8371400, '0,8371400,8370000' , '禹城市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371483, 8371400, '0,8371400,8370000' , '开发区分公司', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371484, 8371400, '0,8371400,8370000' , '运河区分公司', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371500, 8370000, '0,8370000' , '聊城市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371501, 8371500, '0,8371500,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371502, 8371500, '0,8371500,8370000' , '东昌府区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371521, 8371500, '0,8371500,8370000' , '阳谷县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371522, 8371500, '0,8371500,8370000' , '莘县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371523, 8371500, '0,8371500,8370000' , '茌平县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371524, 8371500, '0,8371500,8370000' , '东阿县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371525, 8371500, '0,8371500,8370000' , '冠县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371526, 8371500, '0,8371500,8370000' , '高唐县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371581, 8371500, '0,8371500,8370000' , '临清市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371582, 8371500, '0,8371500,8370000' , '开发区分公司', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371583, 8371500, '0,8371500,8370000' , '聊城旅游度假区分公司', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371584, 8371500, '0,8371500,8370000' , '聊城高新区分公司', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371599, 8371500, '0,8371500,8370000' , '聊城_本部', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371600, 8370000, '0,8370000' , '滨州市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371601, 8371600, '0,8371600,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371602, 8371600, '0,8371600,8370000' , '滨城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371621, 8371600, '0,8371600,8370000' , '惠民县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371622, 8371600, '0,8371600,8370000' , '阳信县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371623, 8371600, '0,8371600,8370000' , '无棣县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371624, 8371600, '0,8371600,8370000' , '沾化县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371625, 8371600, '0,8371600,8370000' , '博兴县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371626, 8371600, '0,8371600,8370000' , '邹平县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371627, 8371600, '0,8371600,8370000' , '滨北分公司', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371628, 8371600, '0,8371600,8370000' , '高新区分公司', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371699, 8371600, '0,8371600,8370000' , '开发区分公司', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371698, 8371600, '0,8371600,8370000' , '滨州_本部', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371700, 8370000, '0,8370000' , '菏泽市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371701, 8371700, '0,8371700,8370000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371702, 8371700, '0,8371700,8370000' , '牡丹区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371721, 8371700, '0,8371700,8370000' , '曹县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371722, 8371700, '0,8371700,8370000' , '单县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371723, 8371700, '0,8371700,8370000' , '成武县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371724, 8371700, '0,8371700,8370000' , '巨野县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371725, 8371700, '0,8371700,8370000' , '郓城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371726, 8371700, '0,8371700,8370000' , '鄄城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371727, 8371700, '0,8371700,8370000' , '定陶县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371728, 8371700, '0,8371700,8370000' , '东明县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371729, 8371700, '0,8371700,8370000' , '开发区分公司', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371730, 8371700, '0,8371700,8370000' , '高新区分公司', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371731, 8371700, '0,8371700,8370000' , '菏泽新区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8371799, 8371700, '0,8371700,8370000' , '菏泽_本部', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8379000, 8370000, '0,8370000' , '云计算青岛基地', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8379001, 8379000, '0,8379000,8370000' , '云计算青岛基地', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8379002, 8379000, '0,8379000,8370000' , '云计算', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8379003, 8379000, '0,8379000,8370000' , '大数据', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410000, 0, '0' , '河南省', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410100, 8410000, '0,8410000' , '郑州市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410101, 8410100, '0,8410100,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410102, 8410100, '0,8410100,8410000' , '中原区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410103, 8410100, '0,8410100,8410000' , '二七区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410104, 8410100, '0,8410100,8410000' , '管城回族区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410105, 8410100, '0,8410100,8410000' , '金水区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410106, 8410100, '0,8410100,8410000' , '上街区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410108, 8410100, '0,8410100,8410000' , '惠济区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410122, 8410100, '0,8410100,8410000' , '中牟县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410181, 8410100, '0,8410100,8410000' , '巩义市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410182, 8410100, '0,8410100,8410000' , '荥阳市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410183, 8410100, '0,8410100,8410000' , '新密市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410184, 8410100, '0,8410100,8410000' , '新郑市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410185, 8410100, '0,8410100,8410000' , '登封市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410200, 8410000, '0,8410000' , '开封市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410201, 8410200, '0,8410200,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410202, 8410200, '0,8410200,8410000' , '龙亭区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410203, 8410200, '0,8410200,8410000' , '顺河回族区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410204, 8410200, '0,8410200,8410000' , '鼓楼区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410205, 8410200, '0,8410200,8410000' , '禹王台区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410211, 8410200, '0,8410200,8410000' , '金明区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410221, 8410200, '0,8410200,8410000' , '杞县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410222, 8410200, '0,8410200,8410000' , '通许县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410223, 8410200, '0,8410200,8410000' , '尉氏县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410224, 8410200, '0,8410200,8410000' , '开封县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410225, 8410200, '0,8410200,8410000' , '兰考县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410299, 8410200, '0,8410200,8410000' , '自贸区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410300, 8410000, '0,8410000' , '洛阳市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410301, 8410300, '0,8410300,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410302, 8410300, '0,8410300,8410000' , '老城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410303, 8410300, '0,8410300,8410000' , '西工区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410304, 8410300, '0,8410300,8410000' , '瀍河回族区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410305, 8410300, '0,8410300,8410000' , '涧西区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410306, 8410300, '0,8410300,8410000' , '吉利区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410311, 8410300, '0,8410300,8410000' , '洛龙区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410322, 8410300, '0,8410300,8410000' , '孟津县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410323, 8410300, '0,8410300,8410000' , '新安县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410324, 8410300, '0,8410300,8410000' , '栾川县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410325, 8410300, '0,8410300,8410000' , '嵩县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410326, 8410300, '0,8410300,8410000' , '汝阳县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410327, 8410300, '0,8410300,8410000' , '宜阳县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410328, 8410300, '0,8410300,8410000' , '洛宁县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410329, 8410300, '0,8410300,8410000' , '伊川县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410381, 8410300, '0,8410300,8410000' , '偃师市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410400, 8410000, '0,8410000' , '平顶山市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410401, 8410400, '0,8410400,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410402, 8410400, '0,8410400,8410000' , '新华区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410403, 8410400, '0,8410400,8410000' , '卫东区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410404, 8410400, '0,8410400,8410000' , '石龙区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410411, 8410400, '0,8410400,8410000' , '湛河区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410421, 8410400, '0,8410400,8410000' , '宝丰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410422, 8410400, '0,8410400,8410000' , '叶县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410423, 8410400, '0,8410400,8410000' , '鲁山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410425, 8410400, '0,8410400,8410000' , '郏县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410481, 8410400, '0,8410400,8410000' , '舞钢市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410482, 8410400, '0,8410400,8410000' , '汝州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410500, 8410000, '0,8410000' , '安阳市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410501, 8410500, '0,8410500,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410502, 8410500, '0,8410500,8410000' , '文峰区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410503, 8410500, '0,8410500,8410000' , '北关区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410505, 8410500, '0,8410500,8410000' , '殷都区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410506, 8410500, '0,8410500,8410000' , '龙安区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410522, 8410500, '0,8410500,8410000' , '安阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410523, 8410500, '0,8410500,8410000' , '汤阴县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410526, 8410500, '0,8410500,8410000' , '滑县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410527, 8410500, '0,8410500,8410000' , '内黄县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410581, 8410500, '0,8410500,8410000' , '林州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410600, 8410000, '0,8410000' , '鹤壁市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410601, 8410600, '0,8410600,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410602, 8410600, '0,8410600,8410000' , '鹤山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410603, 8410600, '0,8410600,8410000' , '山城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410611, 8410600, '0,8410600,8410000' , '淇滨区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410621, 8410600, '0,8410600,8410000' , '浚县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410622, 8410600, '0,8410600,8410000' , '淇县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410700, 8410000, '0,8410000' , '新乡市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410701, 8410700, '0,8410700,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410702, 8410700, '0,8410700,8410000' , '红旗区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410703, 8410700, '0,8410700,8410000' , '卫滨区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410704, 8410700, '0,8410700,8410000' , '凤泉区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410711, 8410700, '0,8410700,8410000' , '牧野区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410721, 8410700, '0,8410700,8410000' , '新乡县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410724, 8410700, '0,8410700,8410000' , '获嘉县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410725, 8410700, '0,8410700,8410000' , '原阳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410726, 8410700, '0,8410700,8410000' , '延津县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410727, 8410700, '0,8410700,8410000' , '封丘县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410728, 8410700, '0,8410700,8410000' , '长垣县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410781, 8410700, '0,8410700,8410000' , '卫辉市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410782, 8410700, '0,8410700,8410000' , '辉县市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410800, 8410000, '0,8410000' , '焦作市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410801, 8410800, '0,8410800,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410802, 8410800, '0,8410800,8410000' , '解放区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410803, 8410800, '0,8410800,8410000' , '中站区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410804, 8410800, '0,8410800,8410000' , '马村区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410811, 8410800, '0,8410800,8410000' , '山阳区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410821, 8410800, '0,8410800,8410000' , '修武县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410822, 8410800, '0,8410800,8410000' , '博爱县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410823, 8410800, '0,8410800,8410000' , '武陟县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410825, 8410800, '0,8410800,8410000' , '温县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410882, 8410800, '0,8410800,8410000' , '沁阳市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410883, 8410800, '0,8410800,8410000' , '孟州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410900, 8410000, '0,8410000' , '濮阳市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410901, 8410900, '0,8410900,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410902, 8410900, '0,8410900,8410000' , '华龙区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410922, 8410900, '0,8410900,8410000' , '清丰县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410923, 8410900, '0,8410900,8410000' , '南乐县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410926, 8410900, '0,8410900,8410000' , '范县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410927, 8410900, '0,8410900,8410000' , '台前县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8410928, 8410900, '0,8410900,8410000' , '濮阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411000, 8410000, '0,8410000' , '许昌市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411001, 8411000, '0,8411000,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411002, 8411000, '0,8411000,8410000' , '魏都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411023, 8411000, '0,8411000,8410000' , '许昌县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411024, 8411000, '0,8411000,8410000' , '鄢陵县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411025, 8411000, '0,8411000,8410000' , '襄城县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411081, 8411000, '0,8411000,8410000' , '禹州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411082, 8411000, '0,8411000,8410000' , '长葛市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411100, 8410000, '0,8410000' , '漯河市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411101, 8411100, '0,8411100,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411102, 8411100, '0,8411100,8410000' , '源汇区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411103, 8411100, '0,8411100,8410000' , '郾城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411104, 8411100, '0,8411100,8410000' , '召陵区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411121, 8411100, '0,8411100,8410000' , '舞阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411122, 8411100, '0,8411100,8410000' , '临颍县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411200, 8410000, '0,8410000' , '三门峡市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411201, 8411200, '0,8411200,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411202, 8411200, '0,8411200,8410000' , '湖滨区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411221, 8411200, '0,8411200,8410000' , '渑池县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411222, 8411200, '0,8411200,8410000' , '陕县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411224, 8411200, '0,8411200,8410000' , '卢氏县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411281, 8411200, '0,8411200,8410000' , '义马市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411282, 8411200, '0,8411200,8410000' , '灵宝市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411300, 8410000, '0,8410000' , '南阳市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411301, 8411300, '0,8411300,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411302, 8411300, '0,8411300,8410000' , '宛城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411303, 8411300, '0,8411300,8410000' , '卧龙区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411321, 8411300, '0,8411300,8410000' , '南召县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411322, 8411300, '0,8411300,8410000' , '方城县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411323, 8411300, '0,8411300,8410000' , '西峡县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411324, 8411300, '0,8411300,8410000' , '镇平县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411325, 8411300, '0,8411300,8410000' , '内乡县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411326, 8411300, '0,8411300,8410000' , '淅川县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411327, 8411300, '0,8411300,8410000' , '社旗县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411328, 8411300, '0,8411300,8410000' , '唐河县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411329, 8411300, '0,8411300,8410000' , '新野县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411330, 8411300, '0,8411300,8410000' , '桐柏县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411381, 8411300, '0,8411300,8410000' , '邓州市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411400, 8410000, '0,8410000' , '商丘市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411401, 8411400, '0,8411400,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411402, 8411400, '0,8411400,8410000' , '梁园区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411403, 8411400, '0,8411400,8410000' , '睢阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411421, 8411400, '0,8411400,8410000' , '民权县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411422, 8411400, '0,8411400,8410000' , '睢县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411423, 8411400, '0,8411400,8410000' , '宁陵县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411424, 8411400, '0,8411400,8410000' , '柘城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411425, 8411400, '0,8411400,8410000' , '虞城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411426, 8411400, '0,8411400,8410000' , '夏邑县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411481, 8411400, '0,8411400,8410000' , '永城市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411500, 8410000, '0,8410000' , '信阳市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411501, 8411500, '0,8411500,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411502, 8411500, '0,8411500,8410000' , '浉河区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411503, 8411500, '0,8411500,8410000' , '平桥区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411521, 8411500, '0,8411500,8410000' , '罗山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411522, 8411500, '0,8411500,8410000' , '光山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411523, 8411500, '0,8411500,8410000' , '新县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411524, 8411500, '0,8411500,8410000' , '商城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411525, 8411500, '0,8411500,8410000' , '固始县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411526, 8411500, '0,8411500,8410000' , '潢川县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411527, 8411500, '0,8411500,8410000' , '淮滨县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411528, 8411500, '0,8411500,8410000' , '息县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411600, 8410000, '0,8410000' , '周口市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411601, 8411600, '0,8411600,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411602, 8411600, '0,8411600,8410000' , '川汇区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411621, 8411600, '0,8411600,8410000' , '扶沟县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411622, 8411600, '0,8411600,8410000' , '西华县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411623, 8411600, '0,8411600,8410000' , '商水县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411624, 8411600, '0,8411600,8410000' , '沈丘县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411625, 8411600, '0,8411600,8410000' , '郸城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411626, 8411600, '0,8411600,8410000' , '淮阳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411627, 8411600, '0,8411600,8410000' , '太康县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411628, 8411600, '0,8411600,8410000' , '鹿邑县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411681, 8411600, '0,8411600,8410000' , '项城市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411700, 8410000, '0,8410000' , '驻马店市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411701, 8411700, '0,8411700,8410000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411702, 8411700, '0,8411700,8410000' , '驿城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411721, 8411700, '0,8411700,8410000' , '西平县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411722, 8411700, '0,8411700,8410000' , '上蔡县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411723, 8411700, '0,8411700,8410000' , '平舆县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411724, 8411700, '0,8411700,8410000' , '正阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411725, 8411700, '0,8411700,8410000' , '确山县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411726, 8411700, '0,8411700,8410000' , '泌阳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411727, 8411700, '0,8411700,8410000' , '汝南县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411728, 8411700, '0,8411700,8410000' , '遂平县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8411729, 8411700, '0,8411700,8410000' , '新蔡县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8419000, 8410000, '0,8410000' , '省直辖县级行政区划', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8419001, 8410000, '0,8410000' , '济源市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420000, 0, '0' , '湖北省', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420100, 8420000, '0,8420000' , '武汉市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420101, 8420100, '0,8420100,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420102, 8420100, '0,8420100,8420000' , '江岸区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420103, 8420100, '0,8420100,8420000' , '江汉区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420104, 8420100, '0,8420100,8420000' , '硚口区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420105, 8420100, '0,8420100,8420000' , '汉阳区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420106, 8420100, '0,8420100,8420000' , '武昌区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420107, 8420100, '0,8420100,8420000' , '青山区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420111, 8420100, '0,8420100,8420000' , '洪山区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420112, 8420100, '0,8420100,8420000' , '东西湖区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420113, 8420100, '0,8420100,8420000' , '汉南区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420114, 8420100, '0,8420100,8420000' , '蔡甸区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420115, 8420100, '0,8420100,8420000' , '江夏区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420116, 8420100, '0,8420100,8420000' , '黄陂区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420117, 8420100, '0,8420100,8420000' , '新洲区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420199, 8420100, '0,8420100,8420000' , '水果湖区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420200, 8420000, '0,8420000' , '黄石市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420201, 8420200, '0,8420200,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420202, 8420200, '0,8420200,8420000' , '黄石港区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420203, 8420200, '0,8420200,8420000' , '西塞山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420204, 8420200, '0,8420200,8420000' , '下陆区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420205, 8420200, '0,8420200,8420000' , '铁山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420222, 8420200, '0,8420200,8420000' , '阳新县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420281, 8420200, '0,8420200,8420000' , '大冶市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420300, 8420000, '0,8420000' , '十堰市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420301, 8420300, '0,8420300,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420302, 8420300, '0,8420300,8420000' , '茅箭区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420303, 8420300, '0,8420300,8420000' , '张湾区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420321, 8420300, '0,8420300,8420000' , '郧县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420322, 8420300, '0,8420300,8420000' , '郧西县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420323, 8420300, '0,8420300,8420000' , '竹山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420324, 8420300, '0,8420300,8420000' , '竹溪县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420325, 8420300, '0,8420300,8420000' , '房县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420381, 8420300, '0,8420300,8420000' , '丹江口市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420500, 8420000, '0,8420000' , '宜昌市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420501, 8420500, '0,8420500,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420502, 8420500, '0,8420500,8420000' , '西陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420503, 8420500, '0,8420500,8420000' , '伍家岗区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420504, 8420500, '0,8420500,8420000' , '点军区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420505, 8420500, '0,8420500,8420000' , '猇亭区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420506, 8420500, '0,8420500,8420000' , '夷陵区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420525, 8420500, '0,8420500,8420000' , '远安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420526, 8420500, '0,8420500,8420000' , '兴山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420527, 8420500, '0,8420500,8420000' , '秭归县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420528, 8420500, '0,8420500,8420000' , '长阳土家族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420529, 8420500, '0,8420500,8420000' , '五峰土家族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420581, 8420500, '0,8420500,8420000' , '宜都市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420582, 8420500, '0,8420500,8420000' , '当阳市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420583, 8420500, '0,8420500,8420000' , '枝江市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420600, 8420000, '0,8420000' , '襄阳市

', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420601, 8420600, '0,8420600,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420602, 8420600, '0,8420600,8420000' , '襄城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420606, 8420600, '0,8420600,8420000' , '樊城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420607, 8420600, '0,8420600,8420000' , '襄州区

', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420624, 8420600, '0,8420600,8420000' , '南漳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420625, 8420600, '0,8420600,8420000' , '谷城县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420626, 8420600, '0,8420600,8420000' , '保康县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420682, 8420600, '0,8420600,8420000' , '老河口市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420683, 8420600, '0,8420600,8420000' , '枣阳市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420684, 8420600, '0,8420600,8420000' , '宜城市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420700, 8420000, '0,8420000' , '鄂州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420701, 8420700, '0,8420700,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420702, 8420700, '0,8420700,8420000' , '梁子湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420703, 8420700, '0,8420700,8420000' , '华容区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420704, 8420700, '0,8420700,8420000' , '鄂城区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420799, 8420700, '0,8420700,8420000' , '经济开发区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420800, 8420000, '0,8420000' , '荆门市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420801, 8420800, '0,8420800,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420802, 8420800, '0,8420800,8420000' , '东宝区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420804, 8420800, '0,8420800,8420000' , '掇刀区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420821, 8420800, '0,8420800,8420000' , '京山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420822, 8420800, '0,8420800,8420000' , '沙洋县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420881, 8420800, '0,8420800,8420000' , '钟祥市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420900, 8420000, '0,8420000' , '孝感市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420901, 8420900, '0,8420900,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420902, 8420900, '0,8420900,8420000' , '孝南区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420921, 8420900, '0,8420900,8420000' , '孝昌县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420922, 8420900, '0,8420900,8420000' , '大悟县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420923, 8420900, '0,8420900,8420000' , '云梦县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420981, 8420900, '0,8420900,8420000' , '应城市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420982, 8420900, '0,8420900,8420000' , '安陆市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8420984, 8420900, '0,8420900,8420000' , '汉川市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421000, 8420000, '0,8420000' , '荆州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421001, 8421000, '0,8421000,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421002, 8421000, '0,8421000,8420000' , '沙市区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421003, 8421000, '0,8421000,8420000' , '荆州区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421022, 8421000, '0,8421000,8420000' , '公安县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421023, 8421000, '0,8421000,8420000' , '监利县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421024, 8421000, '0,8421000,8420000' , '江陵县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421081, 8421000, '0,8421000,8420000' , '石首市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421083, 8421000, '0,8421000,8420000' , '洪湖市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421087, 8421000, '0,8421000,8420000' , '松滋市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421100, 8420000, '0,8420000' , '黄冈市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421101, 8421100, '0,8421100,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421102, 8421100, '0,8421100,8420000' , '黄州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421121, 8421100, '0,8421100,8420000' , '团风县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421122, 8421100, '0,8421100,8420000' , '红安县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421123, 8421100, '0,8421100,8420000' , '罗田县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421124, 8421100, '0,8421100,8420000' , '英山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421125, 8421100, '0,8421100,8420000' , '浠水县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421126, 8421100, '0,8421100,8420000' , '蕲春县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421127, 8421100, '0,8421100,8420000' , '黄梅县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421181, 8421100, '0,8421100,8420000' , '麻城市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421182, 8421100, '0,8421100,8420000' , '武穴市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421200, 8420000, '0,8420000' , '咸宁市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421201, 8421200, '0,8421200,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421202, 8421200, '0,8421200,8420000' , '咸安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421221, 8421200, '0,8421200,8420000' , '嘉鱼县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421222, 8421200, '0,8421200,8420000' , '通城县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421223, 8421200, '0,8421200,8420000' , '崇阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421224, 8421200, '0,8421200,8420000' , '通山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421281, 8421200, '0,8421200,8420000' , '赤壁市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421300, 8420000, '0,8420000' , '随州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421301, 8421300, '0,8421300,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421303, 8421300, '0,8421300,8420000' , '曾都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421321, 8421300, '0,8421300,8420000' , '随县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8421381, 8421300, '0,8421300,8420000' , '广水市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422800, 8420000, '0,8420000' , '恩施土家族苗族自治州', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422801, 8422800, '0,8422800,8420000' , '恩施市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422802, 8422800, '0,8422800,8420000' , '利川市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422822, 8422800, '0,8422800,8420000' , '建始县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422823, 8422800, '0,8422800,8420000' , '巴东县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422825, 8422800, '0,8422800,8420000' , '宣恩县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422826, 8422800, '0,8422800,8420000' , '咸丰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422827, 8422800, '0,8422800,8420000' , '来凤县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422828, 8422800, '0,8422800,8420000' , '鹤峰县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8422899, 8422800, '0,8422800,8420000' , '农村辖区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429000, 8420000, '0,8420000' , '省直辖县级行政区划', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429004, 8420000, '0,8420000' , '仙桃市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429096, 8429004, '0,8429004,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429005, 8420000, '0,8420000' , '潜江市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429097, 8429005, '0,8429005,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429006, 8420000, '0,8420000' , '天门市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429098, 8429006, '0,8429006,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429021, 8420000, '0,8420000' , '神农架林区', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8429099, 8429021, '0,8429021,8420000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430000, 0, '0' , '湖南省', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430100, 8430000, '0,8430000' , '长沙市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430101, 8430100, '0,8430100,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430102, 8430100, '0,8430100,8430000' , '芙蓉区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430103, 8430100, '0,8430100,8430000' , '天心区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430104, 8430100, '0,8430100,8430000' , '岳麓区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430105, 8430100, '0,8430100,8430000' , '开福区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430111, 8430100, '0,8430100,8430000' , '雨花区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430121, 8430100, '0,8430100,8430000' , '长沙县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430112, 8430100, '0,8430100,8430000' , '望城区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430122, 8430100, '0,8430100,8430000' , '望城县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430124, 8430100, '0,8430100,8430000' , '宁乡县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430181, 8430100, '0,8430100,8430000' , '浏阳市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430200, 8430000, '0,8430000' , '株洲市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430201, 8430200, '0,8430200,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430202, 8430200, '0,8430200,8430000' , '荷塘区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430203, 8430200, '0,8430200,8430000' , '芦淞区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430204, 8430200, '0,8430200,8430000' , '石峰区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430211, 8430200, '0,8430200,8430000' , '天元区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430221, 8430200, '0,8430200,8430000' , '株洲县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430223, 8430200, '0,8430200,8430000' , '攸县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430224, 8430200, '0,8430200,8430000' , '茶陵县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430225, 8430200, '0,8430200,8430000' , '炎陵县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430281, 8430200, '0,8430200,8430000' , '醴陵市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430300, 8430000, '0,8430000' , '湘潭市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430301, 8430300, '0,8430300,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430302, 8430300, '0,8430300,8430000' , '雨湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430304, 8430300, '0,8430300,8430000' , '岳塘区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430321, 8430300, '0,8430300,8430000' , '湘潭县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430381, 8430300, '0,8430300,8430000' , '湘乡市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430382, 8430300, '0,8430300,8430000' , '韶山市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430400, 8430000, '0,8430000' , '衡阳市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430401, 8430400, '0,8430400,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430405, 8430400, '0,8430400,8430000' , '珠晖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430406, 8430400, '0,8430400,8430000' , '雁峰区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430407, 8430400, '0,8430400,8430000' , '石鼓区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430408, 8430400, '0,8430400,8430000' , '蒸湘区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430412, 8430400, '0,8430400,8430000' , '南岳区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430421, 8430400, '0,8430400,8430000' , '衡阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430422, 8430400, '0,8430400,8430000' , '衡南县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430423, 8430400, '0,8430400,8430000' , '衡山县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430424, 8430400, '0,8430400,8430000' , '衡东县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430426, 8430400, '0,8430400,8430000' , '祁东县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430481, 8430400, '0,8430400,8430000' , '耒阳市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430482, 8430400, '0,8430400,8430000' , '常宁市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430500, 8430000, '0,8430000' , '邵阳市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430501, 8430500, '0,8430500,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430502, 8430500, '0,8430500,8430000' , '双清区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430503, 8430500, '0,8430500,8430000' , '大祥区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430511, 8430500, '0,8430500,8430000' , '北塔区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430521, 8430500, '0,8430500,8430000' , '邵东县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430522, 8430500, '0,8430500,8430000' , '新邵县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430523, 8430500, '0,8430500,8430000' , '邵阳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430524, 8430500, '0,8430500,8430000' , '隆回县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430525, 8430500, '0,8430500,8430000' , '洞口县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430527, 8430500, '0,8430500,8430000' , '绥宁县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430528, 8430500, '0,8430500,8430000' , '新宁县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430529, 8430500, '0,8430500,8430000' , '城步苗族自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430581, 8430500, '0,8430500,8430000' , '武冈市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430600, 8430000, '0,8430000' , '岳阳市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430601, 8430600, '0,8430600,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430602, 8430600, '0,8430600,8430000' , '岳阳楼区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430603, 8430600, '0,8430600,8430000' , '云溪区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430611, 8430600, '0,8430600,8430000' , '君山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430621, 8430600, '0,8430600,8430000' , '岳阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430623, 8430600, '0,8430600,8430000' , '华容县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430624, 8430600, '0,8430600,8430000' , '湘阴县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430626, 8430600, '0,8430600,8430000' , '平江县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430681, 8430600, '0,8430600,8430000' , '汨罗市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430682, 8430600, '0,8430600,8430000' , '临湘市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430700, 8430000, '0,8430000' , '常德市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430701, 8430700, '0,8430700,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430702, 8430700, '0,8430700,8430000' , '武陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430703, 8430700, '0,8430700,8430000' , '鼎城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430721, 8430700, '0,8430700,8430000' , '安乡县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430722, 8430700, '0,8430700,8430000' , '汉寿县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430723, 8430700, '0,8430700,8430000' , '澧县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430724, 8430700, '0,8430700,8430000' , '临澧县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430725, 8430700, '0,8430700,8430000' , '桃源县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430726, 8430700, '0,8430700,8430000' , '石门县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430781, 8430700, '0,8430700,8430000' , '津市市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430800, 8430000, '0,8430000' , '张家界市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430801, 8430800, '0,8430800,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430802, 8430800, '0,8430800,8430000' , '永定区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430811, 8430800, '0,8430800,8430000' , '武陵源区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430821, 8430800, '0,8430800,8430000' , '慈利县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430822, 8430800, '0,8430800,8430000' , '桑植县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430900, 8430000, '0,8430000' , '益阳市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430901, 8430900, '0,8430900,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430902, 8430900, '0,8430900,8430000' , '资阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430903, 8430900, '0,8430900,8430000' , '赫山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430921, 8430900, '0,8430900,8430000' , '南县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430922, 8430900, '0,8430900,8430000' , '桃江县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430923, 8430900, '0,8430900,8430000' , '安化县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430981, 8430900, '0,8430900,8430000' , '沅江市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8430999, 8430900, '0,8430900,8430000' , '益阳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431000, 8430000, '0,8430000' , '郴州市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431001, 8431000, '0,8431000,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431002, 8431000, '0,8431000,8430000' , '北湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431003, 8431000, '0,8431000,8430000' , '苏仙区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431021, 8431000, '0,8431000,8430000' , '桂阳县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431022, 8431000, '0,8431000,8430000' , '宜章县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431023, 8431000, '0,8431000,8430000' , '永兴县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431024, 8431000, '0,8431000,8430000' , '嘉禾县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431025, 8431000, '0,8431000,8430000' , '临武县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431026, 8431000, '0,8431000,8430000' , '汝城县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431027, 8431000, '0,8431000,8430000' , '桂东县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431028, 8431000, '0,8431000,8430000' , '安仁县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431081, 8431000, '0,8431000,8430000' , '资兴市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431099, 8431000, '0,8431000,8430000' , '郊区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431100, 8430000, '0,8430000' , '永州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431101, 8431100, '0,8431100,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431102, 8431100, '0,8431100,8430000' , '零陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431103, 8431100, '0,8431100,8430000' , '冷水滩区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431121, 8431100, '0,8431100,8430000' , '祁阳县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431122, 8431100, '0,8431100,8430000' , '东安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431123, 8431100, '0,8431100,8430000' , '双牌县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431124, 8431100, '0,8431100,8430000' , '道县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431125, 8431100, '0,8431100,8430000' , '江永县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431126, 8431100, '0,8431100,8430000' , '宁远县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431127, 8431100, '0,8431100,8430000' , '蓝山县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431128, 8431100, '0,8431100,8430000' , '新田县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431129, 8431100, '0,8431100,8430000' , '江华瑶族自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431200, 8430000, '0,8430000' , '怀化市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431201, 8431200, '0,8431200,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431202, 8431200, '0,8431200,8430000' , '鹤城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431221, 8431200, '0,8431200,8430000' , '中方县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431222, 8431200, '0,8431200,8430000' , '沅陵县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431223, 8431200, '0,8431200,8430000' , '辰溪县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431224, 8431200, '0,8431200,8430000' , '溆浦县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431225, 8431200, '0,8431200,8430000' , '会同县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431226, 8431200, '0,8431200,8430000' , '麻阳苗族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431227, 8431200, '0,8431200,8430000' , '新晃侗族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431228, 8431200, '0,8431200,8430000' , '芷江侗族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431229, 8431200, '0,8431200,8430000' , '靖州苗族侗族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431230, 8431200, '0,8431200,8430000' , '通道侗族自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431281, 8431200, '0,8431200,8430000' , '洪江市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431299, 8431200, '0,8431200,8430000' , '洪江区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431300, 8430000, '0,8430000' , '娄底市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431301, 8431300, '0,8431300,8430000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431302, 8431300, '0,8431300,8430000' , '娄星区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431321, 8431300, '0,8431300,8430000' , '双峰县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431322, 8431300, '0,8431300,8430000' , '新化县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431381, 8431300, '0,8431300,8430000' , '冷水江市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8431382, 8431300, '0,8431300,8430000' , '涟源市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433100, 8430000, '0,8430000' , '湘西土家族苗族自治州', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433101, 8433100, '0,8433100,8430000' , '吉首市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433122, 8433100, '0,8433100,8430000' , '泸溪县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433123, 8433100, '0,8433100,8430000' , '凤凰县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433124, 8433100, '0,8433100,8430000' , '花垣县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433125, 8433100, '0,8433100,8430000' , '保靖县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433126, 8433100, '0,8433100,8430000' , '古丈县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433127, 8433100, '0,8433100,8430000' , '永顺县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8433130, 8433100, '0,8433100,8430000' , '龙山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440000, 0, '0' , '广东省', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440100, 8440000, '0,8440000' , '广州市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440101, 8440100, '0,8440100,8440000' , '东山区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440103, 8440100, '0,8440100,8440000' , '荔湾区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440104, 8440100, '0,8440100,8440000' , '越秀区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440105, 8440100, '0,8440100,8440000' , '海珠区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440106, 8440100, '0,8440100,8440000' , '天河区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440111, 8440100, '0,8440100,8440000' , '白云区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440112, 8440100, '0,8440100,8440000' , '黄埔区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440113, 8440100, '0,8440100,8440000' , '番禺区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440114, 8440100, '0,8440100,8440000' , '花都区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440115, 8440100, '0,8440100,8440000' , '南沙区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440116, 8440100, '0,8440100,8440000' , '萝岗区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440183, 8440100, '0,8440100,8440000' , '增城区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440184, 8440100, '0,8440100,8440000' , '从化区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440200, 8440000, '0,8440000' , '韶关市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440201, 8440200, '0,8440200,8440000' , '城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440203, 8440200, '0,8440200,8440000' , '武江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440204, 8440200, '0,8440200,8440000' , '浈江区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440205, 8440200, '0,8440200,8440000' , '曲江', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440222, 8440200, '0,8440200,8440000' , '始兴', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440224, 8440200, '0,8440200,8440000' , '仁化', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440229, 8440200, '0,8440200,8440000' , '翁源', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440232, 8440200, '0,8440200,8440000' , '乳源', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440233, 8440200, '0,8440200,8440000' , '新丰', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440281, 8440200, '0,8440200,8440000' , '乐昌', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440282, 8440200, '0,8440200,8440000' , '南雄', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440300, 8440000, '0,8440000' , '深圳市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440301, 8440300, '0,8440300,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440303, 8440300, '0,8440300,8440000' , '罗湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440304, 8440300, '0,8440300,8440000' , '福田区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440305, 8440300, '0,8440300,8440000' , '南山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440306, 8440300, '0,8440300,8440000' , '宝安区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440307, 8440300, '0,8440300,8440000' , '龙岗区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440308, 8440300, '0,8440300,8440000' , '盐田区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440309, 8440300, '0,8440300,8440000' , '光明区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440310, 8440300, '0,8440300,8440000' , '龙华区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440311, 8440300, '0,8440300,8440000' , '坪山大鹏区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440399, 8440300, '0,8440300,8440000' , '蛇口区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440398, 8440300, '0,8440300,8440000' , '深汕合作区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440400, 8440000, '0,8440000' , '珠海市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440401, 8440400, '0,8440400,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440402, 8440400, '0,8440400,8440000' , '香洲区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440403, 8440400, '0,8440400,8440000' , '斗门区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440404, 8440400, '0,8440400,8440000' , '金湾区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440405, 8440400, '0,8440400,8440000' , '拱北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440406, 8440400, '0,8440400,8440000' , '前山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440407, 8440400, '0,8440400,8440000' , '横琴新区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440408, 8440400, '0,8440400,8440000' , '高新区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440409, 8440400, '0,8440400,8440000' , '高栏港区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440500, 8440000, '0,8440000' , '汕头市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440501, 8440500, '0,8440500,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440507, 8440500, '0,8440500,8440000' , '龙湖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440511, 8440500, '0,8440500,8440000' , '金平区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440512, 8440500, '0,8440500,8440000' , '濠江区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440513, 8440500, '0,8440500,8440000' , '潮阳区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440514, 8440500, '0,8440500,8440000' , '潮南区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440515, 8440500, '0,8440500,8440000' , '澄海区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440523, 8440500, '0,8440500,8440000' , '南澳县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440600, 8440000, '0,8440000' , '佛山市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440601, 8440600, '0,8440600,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440604, 8440600, '0,8440600,8440000' , '禅城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440605, 8440600, '0,8440600,8440000' , '南海区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440606, 8440600, '0,8440600,8440000' , '顺德区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440607, 8440600, '0,8440600,8440000' , '三水区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440608, 8440600, '0,8440600,8440000' , '高明区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440700, 8440000, '0,8440000' , '江门市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440701, 8440700, '0,8440700,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440703, 8440700, '0,8440700,8440000' , '蓬江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440704, 8440700, '0,8440700,8440000' , '江海区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440705, 8440700, '0,8440700,8440000' , '新会区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440781, 8440700, '0,8440700,8440000' , '台山市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440783, 8440700, '0,8440700,8440000' , '开平市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440784, 8440700, '0,8440700,8440000' , '鹤山市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440785, 8440700, '0,8440700,8440000' , '恩平市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440800, 8440000, '0,8440000' , '湛江市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440801, 8440800, '0,8440800,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440802, 8440800, '0,8440800,8440000' , '赤坎区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440803, 8440800, '0,8440800,8440000' , '霞山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440804, 8440800, '0,8440800,8440000' , '坡头区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440811, 8440800, '0,8440800,8440000' , '麻章区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440823, 8440800, '0,8440800,8440000' , '遂溪县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440825, 8440800, '0,8440800,8440000' , '徐闻县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440881, 8440800, '0,8440800,8440000' , '廉江市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440882, 8440800, '0,8440800,8440000' , '雷州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440883, 8440800, '0,8440800,8440000' , '吴川市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440900, 8440000, '0,8440000' , '茂名市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440901, 8440900, '0,8440900,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440902, 8440900, '0,8440900,8440000' , '茂南区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440903, 8440900, '0,8440900,8440000' , '茂港区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440923, 8440900, '0,8440900,8440000' , '电白县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440981, 8440900, '0,8440900,8440000' , '高州市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440982, 8440900, '0,8440900,8440000' , '化州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440983, 8440900, '0,8440900,8440000' , '信宜市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441200, 8440000, '0,8440000' , '肇庆市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441201, 8441200, '0,8441200,8440000' , '端州区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441202, 8441200, '0,8441200,8440000' , '市辖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441203, 8441200, '0,8441200,8440000' , '鼎湖区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441223, 8441200, '0,8441200,8440000' , '广宁县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441224, 8441200, '0,8441200,8440000' , '怀集县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441225, 8441200, '0,8441200,8440000' , '封开县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441226, 8441200, '0,8441200,8440000' , '德庆县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441283, 8441200, '0,8441200,8440000' , '高要市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441284, 8441200, '0,8441200,8440000' , '四会市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441285, 8441200, '0,8441200,8440000' , '高新区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441300, 8440000, '0,8440000' , '惠州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441301, 8441300, '0,8441300,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441302, 8441300, '0,8441300,8440000' , '惠城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441303, 8441300, '0,8441300,8440000' , '惠阳区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441322, 8441300, '0,8441300,8440000' , '博罗县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441323, 8441300, '0,8441300,8440000' , '惠东县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441324, 8441300, '0,8441300,8440000' , '龙门县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441305, 8441300, '0,8441300,8440000' , '仲恺高新区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441304, 8441300, '0,8441300,8440000' , '大亚湾', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441400, 8440000, '0,8440000' , '梅州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441401, 8441400, '0,8441400,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441402, 8441400, '0,8441400,8440000' , '梅江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441421, 8441400, '0,8441400,8440000' , '梅县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441422, 8441400, '0,8441400,8440000' , '大埔县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441423, 8441400, '0,8441400,8440000' , '丰顺县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441424, 8441400, '0,8441400,8440000' , '五华县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441426, 8441400, '0,8441400,8440000' , '平远县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441427, 8441400, '0,8441400,8440000' , '蕉岭县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441481, 8441400, '0,8441400,8440000' , '兴宁市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441500, 8440000, '0,8440000' , '汕尾市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441501, 8441500, '0,8441500,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441502, 8441500, '0,8441500,8440000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441521, 8441500, '0,8441500,8440000' , '海丰县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441523, 8441500, '0,8441500,8440000' , '陆河县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441581, 8441500, '0,8441500,8440000' , '陆丰市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441600, 8440000, '0,8440000' , '河源市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441601, 8441600, '0,8441600,8440000' , '城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441602, 8441600, '0,8441600,8440000' , '源城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441621, 8441600, '0,8441600,8440000' , '紫金县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441622, 8441600, '0,8441600,8440000' , '龙川县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441623, 8441600, '0,8441600,8440000' , '连平县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441624, 8441600, '0,8441600,8440000' , '和平县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441625, 8441600, '0,8441600,8440000' , '东源县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441700, 8440000, '0,8440000' , '阳江市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441701, 8441700, '0,8441700,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441702, 8441700, '0,8441700,8440000' , '江城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441721, 8441700, '0,8441700,8440000' , '阳西县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441723, 8441700, '0,8441700,8440000' , '阳东县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441781, 8441700, '0,8441700,8440000' , '阳春市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441782, 8441700, '0,8441700,8440000' , '高新区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441800, 8440000, '0,8440000' , '清远市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441801, 8441800, '0,8441800,8440000' , '城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441802, 8441800, '0,8441800,8440000' , '清城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441821, 8441800, '0,8441800,8440000' , '佛冈县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441823, 8441800, '0,8441800,8440000' , '阳山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441825, 8441800, '0,8441800,8440000' , '连山壮族瑶族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441826, 8441800, '0,8441800,8440000' , '连南瑶族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441827, 8441800, '0,8441800,8440000' , '清新县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441881, 8441800, '0,8441800,8440000' , '英德市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441882, 8441800, '0,8441800,8440000' , '连州市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441900, 8440000, '0,8440000' , '东莞市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441901, 8441900, '0,8441900,8440000' , '厚街镇', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441902, 8441900, '0,8441900,8440000' , '市辖区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441903, 8441900, '0,8441900,8440000' , '常平镇', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441904, 8441900, '0,8441900,8440000' , '大朗镇', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441905, 8441900, '0,8441900,8440000' , '塘厦镇', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441906, 8441900, '0,8441900,8440000' , '长安镇', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441907, 8441900, '0,8441900,8440000' , '清溪镇', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441908, 8441900, '0,8441900,8440000' , '虎门镇', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441909, 8441900, '0,8441900,8440000' , '寮步镇', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441910, 8441900, '0,8441900,8440000' , '中堂镇', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441911, 8441900, '0,8441900,8440000' , '石龙镇', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441912, 8441900, '0,8441900,8440000' , '东城区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441913, 8441900, '0,8441900,8440000' , '南城区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441914, 8441900, '0,8441900,8440000' , '莞城区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441915, 8441900, '0,8441900,8440000' , '万江区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441916, 8441900, '0,8441900,8440000' , '石碣镇', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441917, 8441900, '0,8441900,8440000' , '凤岗镇', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441918, 8441900, '0,8441900,8440000' , '大岭山镇', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441919, 8441900, '0,8441900,8440000' , '黄江镇', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441920, 8441900, '0,8441900,8440000' , '茶山镇', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441921, 8441900, '0,8441900,8440000' , '樟木头镇', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441922, 8441900, '0,8441900,8440000' , '横沥镇', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441923, 8441900, '0,8441900,8440000' , '桥头镇', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441924, 8441900, '0,8441900,8440000' , '石排镇', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441925, 8441900, '0,8441900,8440000' , '沙田镇', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441926, 8441900, '0,8441900,8440000' , '企石镇', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441927, 8441900, '0,8441900,8440000' , '高埗镇', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441928, 8441900, '0,8441900,8440000' , '道滘镇', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441929, 8441900, '0,8441900,8440000' , '东坑镇', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441930, 8441900, '0,8441900,8440000' , '麻涌镇', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441931, 8441900, '0,8441900,8440000' , '谢岗镇', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441932, 8441900, '0,8441900,8440000' , '松山湖镇', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441933, 8441900, '0,8441900,8440000' , '望牛墩镇', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441934, 8441900, '0,8441900,8440000' , '洪梅镇', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442000, 8440000, '0,8440000' , '中山市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442001, 8442000, '0,8442000,8440000' , '城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442002, 8442000, '0,8442000,8440000' , '小榄区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442003, 8442000, '0,8442000,8440000' , '西部', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442004, 8442000, '0,8442000,8440000' , '北部', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442005, 8442000, '0,8442000,8440000' , '东部', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442006, 8442000, '0,8442000,8440000' , '沙溪区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442007, 8442000, '0,8442000,8440000' , '南部', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442008, 8442000, '0,8442000,8440000' , '坦洲区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445100, 8440000, '0,8440000' , '潮州市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445101, 8445100, '0,8445100,8440000' , '城区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445102, 8445100, '0,8445100,8440000' , '湘桥区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445121, 8445100, '0,8445100,8440000' , '潮安县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445122, 8445100, '0,8445100,8440000' , '饶平县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445200, 8440000, '0,8440000' , '揭阳市', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445201, 8445200, '0,8445200,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445202, 8445200, '0,8445200,8440000' , '城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445221, 8445200, '0,8445200,8440000' , '揭东县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445222, 8445200, '0,8445200,8440000' , '揭西县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445224, 8445200, '0,8445200,8440000' , '惠来县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445281, 8445200, '0,8445200,8440000' , '普宁市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445300, 8440000, '0,8440000' , '云浮市', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445301, 8445300, '0,8445300,8440000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445302, 8445300, '0,8445300,8440000' , '云城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445321, 8445300, '0,8445300,8440000' , '新兴县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445322, 8445300, '0,8445300,8440000' , '郁南县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445323, 8445300, '0,8445300,8440000' , '云安区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8445381, 8445300, '0,8445300,8440000' , '罗定市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450000, 0, '0' , '广西壮族自治区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450100, 8450000, '0,8450000' , '南宁市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450101, 8450100, '0,8450100,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450102, 8450100, '0,8450100,8450000' , '兴宁区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450103, 8450100, '0,8450100,8450000' , '青秀区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450105, 8450100, '0,8450100,8450000' , '江南区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450107, 8450100, '0,8450100,8450000' , '西乡塘区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450108, 8450100, '0,8450100,8450000' , '良庆区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450109, 8450100, '0,8450100,8450000' , '邕宁区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450122, 8450100, '0,8450100,8450000' , '武鸣县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450123, 8450100, '0,8450100,8450000' , '隆安县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450124, 8450100, '0,8450100,8450000' , '马山县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450125, 8450100, '0,8450100,8450000' , '上林县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450126, 8450100, '0,8450100,8450000' , '宾阳县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450127, 8450100, '0,8450100,8450000' , '横县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450200, 8450000, '0,8450000' , '柳州市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450201, 8450200, '0,8450200,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450202, 8450200, '0,8450200,8450000' , '城中区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450203, 8450200, '0,8450200,8450000' , '鱼峰区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450204, 8450200, '0,8450200,8450000' , '柳南区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450205, 8450200, '0,8450200,8450000' , '柳北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450221, 8450200, '0,8450200,8450000' , '柳江县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450222, 8450200, '0,8450200,8450000' , '柳城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450223, 8450200, '0,8450200,8450000' , '鹿寨县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450224, 8450200, '0,8450200,8450000' , '融安县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450225, 8450200, '0,8450200,8450000' , '融水苗族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450226, 8450200, '0,8450200,8450000' , '三江侗族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450300, 8450000, '0,8450000' , '桂林市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450301, 8450300, '0,8450300,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450302, 8450300, '0,8450300,8450000' , '秀峰区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450303, 8450300, '0,8450300,8450000' , '叠彩区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450304, 8450300, '0,8450300,8450000' , '象山区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450305, 8450300, '0,8450300,8450000' , '七星区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450311, 8450300, '0,8450300,8450000' , '雁山区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450321, 8450300, '0,8450300,8450000' , '阳朔县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450322, 8450300, '0,8450300,8450000' , '临桂县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450323, 8450300, '0,8450300,8450000' , '灵川县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450324, 8450300, '0,8450300,8450000' , '全州县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450325, 8450300, '0,8450300,8450000' , '兴安县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450326, 8450300, '0,8450300,8450000' , '永福县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450327, 8450300, '0,8450300,8450000' , '灌阳县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450328, 8450300, '0,8450300,8450000' , '龙胜各族自治县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450329, 8450300, '0,8450300,8450000' , '资源县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450330, 8450300, '0,8450300,8450000' , '平乐县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450331, 8450300, '0,8450300,8450000' , '荔蒲县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450332, 8450300, '0,8450300,8450000' , '恭城瑶族自治县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450400, 8450000, '0,8450000' , '梧州市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450401, 8450400, '0,8450400,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450403, 8450400, '0,8450400,8450000' , '万秀区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450404, 8450400, '0,8450400,8450000' , '蝶山区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450405, 8450400, '0,8450400,8450000' , '长洲区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450421, 8450400, '0,8450400,8450000' , '苍梧县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450422, 8450400, '0,8450400,8450000' , '藤县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450423, 8450400, '0,8450400,8450000' , '蒙山县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450481, 8450400, '0,8450400,8450000' , '岑溪市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450500, 8450000, '0,8450000' , '北海市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450501, 8450500, '0,8450500,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450502, 8450500, '0,8450500,8450000' , '海城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450503, 8450500, '0,8450500,8450000' , '银海区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450512, 8450500, '0,8450500,8450000' , '铁山港区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450521, 8450500, '0,8450500,8450000' , '合浦县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450600, 8450000, '0,8450000' , '防城港市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450601, 8450600, '0,8450600,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450602, 8450600, '0,8450600,8450000' , '港口区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450603, 8450600, '0,8450600,8450000' , '防城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450621, 8450600, '0,8450600,8450000' , '上思县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450681, 8450600, '0,8450600,8450000' , '东兴市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450700, 8450000, '0,8450000' , '钦州市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450701, 8450700, '0,8450700,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450702, 8450700, '0,8450700,8450000' , '钦南区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450703, 8450700, '0,8450700,8450000' , '钦北区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450721, 8450700, '0,8450700,8450000' , '灵山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450722, 8450700, '0,8450700,8450000' , '浦北县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450799, 8450700, '0,8450700,8450000' , '钦州港', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450800, 8450000, '0,8450000' , '贵港市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450801, 8450800, '0,8450800,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450802, 8450800, '0,8450800,8450000' , '港北区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450803, 8450800, '0,8450800,8450000' , '港南区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450804, 8450800, '0,8450800,8450000' , '覃塘区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450821, 8450800, '0,8450800,8450000' , '平南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450881, 8450800, '0,8450800,8450000' , '桂平市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450900, 8450000, '0,8450000' , '玉林市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450901, 8450900, '0,8450900,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450902, 8450900, '0,8450900,8450000' , '玉州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450921, 8450900, '0,8450900,8450000' , '容县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450922, 8450900, '0,8450900,8450000' , '陆川县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450923, 8450900, '0,8450900,8450000' , '博白县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450924, 8450900, '0,8450900,8450000' , '兴业县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450981, 8450900, '0,8450900,8450000' , '北流市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8450999, 8450900, '0,8450900,8450000' , '福绵管理区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451000, 8450000, '0,8450000' , '百色市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451001, 8451000, '0,8451000,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451002, 8451000, '0,8451000,8450000' , '右江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451021, 8451000, '0,8451000,8450000' , '田阳县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451022, 8451000, '0,8451000,8450000' , '田东县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451023, 8451000, '0,8451000,8450000' , '平果县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451024, 8451000, '0,8451000,8450000' , '德保县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451025, 8451000, '0,8451000,8450000' , '靖西县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451026, 8451000, '0,8451000,8450000' , '那坡县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451027, 8451000, '0,8451000,8450000' , '凌云县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451028, 8451000, '0,8451000,8450000' , '乐业县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451029, 8451000, '0,8451000,8450000' , '田林县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451030, 8451000, '0,8451000,8450000' , '西林县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451031, 8451000, '0,8451000,8450000' , '隆林各族自治县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451100, 8450000, '0,8450000' , '贺州市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451101, 8451100, '0,8451100,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451102, 8451100, '0,8451100,8450000' , '八步区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451119, 8451100, '0,8451100,8450000' , '平桂管理区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451121, 8451100, '0,8451100,8450000' , '昭平县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451122, 8451100, '0,8451100,8450000' , '钟山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451123, 8451100, '0,8451100,8450000' , '富川瑶族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451199, 8451100, '0,8451100,8450000' , '城区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451200, 8450000, '0,8450000' , '河池市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451201, 8451200, '0,8451200,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451202, 8451200, '0,8451200,8450000' , '金城江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451221, 8451200, '0,8451200,8450000' , '南丹县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451222, 8451200, '0,8451200,8450000' , '天峨县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451223, 8451200, '0,8451200,8450000' , '凤山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451224, 8451200, '0,8451200,8450000' , '东兰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451225, 8451200, '0,8451200,8450000' , '罗城仫佬族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451226, 8451200, '0,8451200,8450000' , '环江毛南族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451227, 8451200, '0,8451200,8450000' , '巴马瑶族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451228, 8451200, '0,8451200,8450000' , '都安瑶族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451229, 8451200, '0,8451200,8450000' , '大化瑶族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451281, 8451200, '0,8451200,8450000' , '宜州市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451300, 8450000, '0,8450000' , '来宾市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451301, 8451300, '0,8451300,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451302, 8451300, '0,8451300,8450000' , '兴宾区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451321, 8451300, '0,8451300,8450000' , '忻城县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451322, 8451300, '0,8451300,8450000' , '象州县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451323, 8451300, '0,8451300,8450000' , '武宣县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451324, 8451300, '0,8451300,8450000' , '金秀瑶族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451381, 8451300, '0,8451300,8450000' , '合山市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451400, 8450000, '0,8450000' , '崇左市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451401, 8451400, '0,8451400,8450000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451402, 8451400, '0,8451400,8450000' , '江州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451421, 8451400, '0,8451400,8450000' , '扶绥县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451422, 8451400, '0,8451400,8450000' , '宁明县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451423, 8451400, '0,8451400,8450000' , '龙州县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451424, 8451400, '0,8451400,8450000' , '大新县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451425, 8451400, '0,8451400,8450000' , '天等县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8451481, 8451400, '0,8451400,8450000' , '凭祥市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460000, 0, '0' , '海南省', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460100, 8460000, '0,8460000' , '海南本地网', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460101, 8460100, '0,8460100,8460000' , '海口市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460200, 8460100, '0,8460100,8460000' , '三亚市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460300, 8460100, '0,8460100,8460000' , '三沙市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469001, 8460100, '0,8460100,8460000' , '五指山市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469002, 8460100, '0,8460100,8460000' , '琼海市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469003, 8460100, '0,8460100,8460000' , '儋州市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469005, 8460100, '0,8460100,8460000' , '文昌市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469006, 8460100, '0,8460100,8460000' , '万宁市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469007, 8460100, '0,8460100,8460000' , '东方市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469021, 8460100, '0,8460100,8460000' , '定安县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469022, 8460100, '0,8460100,8460000' , '屯昌县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469023, 8460100, '0,8460100,8460000' , '澄迈县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469024, 8460100, '0,8460100,8460000' , '临高县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469025, 8460100, '0,8460100,8460000' , '白沙黎族自治县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469026, 8460100, '0,8460100,8460000' , '昌江黎族自治县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469027, 8460100, '0,8460100,8460000' , '乐东黎族自治县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469028, 8460100, '0,8460100,8460000' , '陵水黎族自治县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469029, 8460100, '0,8460100,8460000' , '保亭黎族苗族自治县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469030, 8460100, '0,8460100,8460000' , '琼中黎族苗族自治县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500000, 0, '0' , '重庆市', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500100, 8500000, '0,8500000' , '重庆市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500101, 8500100, '0,8500100,8500000' , '万州区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500102, 8500100, '0,8500100,8500000' , '涪陵区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500103, 8500100, '0,8500100,8500000' , '渝中区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500104, 8500100, '0,8500100,8500000' , '大渡口区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500105, 8500100, '0,8500100,8500000' , '江北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500106, 8500100, '0,8500100,8500000' , '沙坪坝区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500107, 8500100, '0,8500100,8500000' , '九龙坡区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500108, 8500100, '0,8500100,8500000' , '南岸区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500109, 8500100, '0,8500100,8500000' , '北碚区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500110, 8500100, '0,8500100,8500000' , '万盛区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500111, 8500100, '0,8500100,8500000' , '双桥区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500112, 8500100, '0,8500100,8500000' , '渝北区', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500113, 8500100, '0,8500100,8500000' , '巴南区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500114, 8500100, '0,8500100,8500000' , '黔江区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500115, 8500100, '0,8500100,8500000' , '长寿区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500116, 8500100, '0,8500100,8500000' , '江津区', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500117, 8500100, '0,8500100,8500000' , '合川区', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500118, 8500100, '0,8500100,8500000' , '永川区', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500119, 8500100, '0,8500100,8500000' , '南川区', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500199, 8500100, '0,8500100,8500000' , '北部新区', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500200, 8500000, '0,8500000' , '县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500222, 8500200, '0,8500200,8500000' , '綦江县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500223, 8500200, '0,8500200,8500000' , '潼南县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500224, 8500200, '0,8500200,8500000' , '铜梁县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500225, 8500200, '0,8500200,8500000' , '大足县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500226, 8500200, '0,8500200,8500000' , '荣昌县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500227, 8500200, '0,8500200,8500000' , '璧山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500228, 8500200, '0,8500200,8500000' , '梁平县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500229, 8500200, '0,8500200,8500000' , '城口县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500230, 8500200, '0,8500200,8500000' , '丰都县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500231, 8500200, '0,8500200,8500000' , '垫江县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500232, 8500200, '0,8500200,8500000' , '武隆县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500233, 8500200, '0,8500200,8500000' , '忠县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500234, 8500200, '0,8500200,8500000' , '开县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500235, 8500200, '0,8500200,8500000' , '云阳县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500236, 8500200, '0,8500200,8500000' , '奉节县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500237, 8500200, '0,8500200,8500000' , '巫山县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500238, 8500200, '0,8500200,8500000' , '巫溪县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500240, 8500200, '0,8500200,8500000' , '石柱土家族自治县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500241, 8500200, '0,8500200,8500000' , '秀山土家族苗族自治县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500242, 8500200, '0,8500200,8500000' , '酉阳土家族苗族自治县', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8500243, 8500200, '0,8500200,8500000' , '彭水苗族土家族自治县', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510000, 0, '0' , '四川省', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510100, 8510000, '0,8510000' , '成都市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510101, 8510100, '0,8510100,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510104, 8510100, '0,8510100,8510000' , '锦江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510105, 8510100, '0,8510100,8510000' , '青羊区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510106, 8510100, '0,8510100,8510000' , '金牛区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510107, 8510100, '0,8510100,8510000' , '武侯区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510108, 8510100, '0,8510100,8510000' , '成华区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510112, 8510100, '0,8510100,8510000' , '龙泉驿区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510113, 8510100, '0,8510100,8510000' , '青白江区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510114, 8510100, '0,8510100,8510000' , '新都区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510115, 8510100, '0,8510100,8510000' , '温江区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510121, 8510100, '0,8510100,8510000' , '金堂县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510122, 8510100, '0,8510100,8510000' , '双流县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510124, 8510100, '0,8510100,8510000' , '郫县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510129, 8510100, '0,8510100,8510000' , '大邑县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510131, 8510100, '0,8510100,8510000' , '蒲江县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510132, 8510100, '0,8510100,8510000' , '新津县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510181, 8510100, '0,8510100,8510000' , '都江堰市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510182, 8510100, '0,8510100,8510000' , '彭州市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510183, 8510100, '0,8510100,8510000' , '邛崃市', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510184, 8510100, '0,8510100,8510000' , '崇州市', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510199, 8510100, '0,8510100,8510000' , '简阳市', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510300, 8510000, '0,8510000' , '自贡市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510301, 8510300, '0,8510300,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510302, 8510300, '0,8510300,8510000' , '自流井区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510303, 8510300, '0,8510300,8510000' , '贡井区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510304, 8510300, '0,8510300,8510000' , '大安区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510311, 8510300, '0,8510300,8510000' , '沿滩区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510321, 8510300, '0,8510300,8510000' , '荣县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510322, 8510300, '0,8510300,8510000' , '富顺县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510400, 8510000, '0,8510000' , '攀枝花市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510401, 8510400, '0,8510400,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510402, 8510400, '0,8510400,8510000' , '东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510403, 8510400, '0,8510400,8510000' , '西区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510411, 8510400, '0,8510400,8510000' , '仁和区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510421, 8510400, '0,8510400,8510000' , '米易县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510422, 8510400, '0,8510400,8510000' , '盐边县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510500, 8510000, '0,8510000' , '泸州市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510501, 8510500, '0,8510500,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510502, 8510500, '0,8510500,8510000' , '江阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510503, 8510500, '0,8510500,8510000' , '纳溪区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510504, 8510500, '0,8510500,8510000' , '龙马潭区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510521, 8510500, '0,8510500,8510000' , '泸县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510522, 8510500, '0,8510500,8510000' , '合江县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510524, 8510500, '0,8510500,8510000' , '叙永县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510525, 8510500, '0,8510500,8510000' , '古蔺县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510600, 8510000, '0,8510000' , '德阳市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510601, 8510600, '0,8510600,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510603, 8510600, '0,8510600,8510000' , '旌阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510623, 8510600, '0,8510600,8510000' , '中江县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510626, 8510600, '0,8510600,8510000' , '罗江县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510681, 8510600, '0,8510600,8510000' , '广汉市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510682, 8510600, '0,8510600,8510000' , '什邡市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510683, 8510600, '0,8510600,8510000' , '绵竹市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510700, 8510000, '0,8510000' , '绵阳市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510701, 8510700, '0,8510700,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510703, 8510700, '0,8510700,8510000' , '涪城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510704, 8510700, '0,8510700,8510000' , '游仙区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510722, 8510700, '0,8510700,8510000' , '三台县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510723, 8510700, '0,8510700,8510000' , '盐亭县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510724, 8510700, '0,8510700,8510000' , '安县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510725, 8510700, '0,8510700,8510000' , '梓潼县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510726, 8510700, '0,8510700,8510000' , '北川羌族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510727, 8510700, '0,8510700,8510000' , '平武县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510781, 8510700, '0,8510700,8510000' , '江油市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510800, 8510000, '0,8510000' , '广元市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510801, 8510800, '0,8510800,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510802, 8510800, '0,8510800,8510000' , '利州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510811, 8510800, '0,8510800,8510000' , '元坝区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510812, 8510800, '0,8510800,8510000' , '朝天区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510821, 8510800, '0,8510800,8510000' , '旺苍县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510822, 8510800, '0,8510800,8510000' , '青川县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510823, 8510800, '0,8510800,8510000' , '剑阁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510824, 8510800, '0,8510800,8510000' , '苍溪县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510825, 8510800, '0,8510800,8510000' , '宝轮区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510900, 8510000, '0,8510000' , '遂宁市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510901, 8510900, '0,8510900,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510903, 8510900, '0,8510900,8510000' , '船山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510904, 8510900, '0,8510900,8510000' , '安居区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510921, 8510900, '0,8510900,8510000' , '蓬溪县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510922, 8510900, '0,8510900,8510000' , '射洪县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8510923, 8510900, '0,8510900,8510000' , '大英县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511000, 8510000, '0,8510000' , '内江市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511001, 8511000, '0,8511000,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511002, 8511000, '0,8511000,8510000' , '市中区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511011, 8511000, '0,8511000,8510000' , '东兴区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511024, 8511000, '0,8511000,8510000' , '威远县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511025, 8511000, '0,8511000,8510000' , '资中县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511028, 8511000, '0,8511000,8510000' , '隆昌县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511100, 8510000, '0,8510000' , '乐山市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511101, 8511100, '0,8511100,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511102, 8511100, '0,8511100,8510000' , '市中区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511111, 8511100, '0,8511100,8510000' , '沙湾区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511112, 8511100, '0,8511100,8510000' , '五通桥区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511113, 8511100, '0,8511100,8510000' , '金口河区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511123, 8511100, '0,8511100,8510000' , '犍为县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511124, 8511100, '0,8511100,8510000' , '井研县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511126, 8511100, '0,8511100,8510000' , '夹江县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511129, 8511100, '0,8511100,8510000' , '沐川县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511132, 8511100, '0,8511100,8510000' , '峨边彝族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511133, 8511100, '0,8511100,8510000' , '马边彝族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511181, 8511100, '0,8511100,8510000' , '峨眉山市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511300, 8510000, '0,8510000' , '南充市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511301, 8511300, '0,8511300,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511302, 8511300, '0,8511300,8510000' , '顺庆区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511303, 8511300, '0,8511300,8510000' , '高坪区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511304, 8511300, '0,8511300,8510000' , '嘉陵区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511321, 8511300, '0,8511300,8510000' , '南部县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511322, 8511300, '0,8511300,8510000' , '营山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511323, 8511300, '0,8511300,8510000' , '蓬安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511324, 8511300, '0,8511300,8510000' , '仪陇县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511325, 8511300, '0,8511300,8510000' , '西充县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511381, 8511300, '0,8511300,8510000' , '阆中市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511400, 8510000, '0,8510000' , '眉山市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511401, 8511400, '0,8511400,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511402, 8511400, '0,8511400,8510000' , '东坡区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511421, 8511400, '0,8511400,8510000' , '仁寿县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511422, 8511400, '0,8511400,8510000' , '彭山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511423, 8511400, '0,8511400,8510000' , '洪雅县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511424, 8511400, '0,8511400,8510000' , '丹棱县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511425, 8511400, '0,8511400,8510000' , '青神县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511426, 8511400, '0,8511400,8510000' , '眉区乡', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511500, 8510000, '0,8510000' , '宜宾市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511501, 8511500, '0,8511500,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511502, 8511500, '0,8511500,8510000' , '翠屏区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511521, 8511500, '0,8511500,8510000' , '宜宾县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511522, 8511500, '0,8511500,8510000' , '南溪县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511523, 8511500, '0,8511500,8510000' , '江安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511524, 8511500, '0,8511500,8510000' , '长宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511525, 8511500, '0,8511500,8510000' , '高县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511526, 8511500, '0,8511500,8510000' , '珙县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511527, 8511500, '0,8511500,8510000' , '筠连县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511528, 8511500, '0,8511500,8510000' , '兴文县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511529, 8511500, '0,8511500,8510000' , '屏山县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511600, 8510000, '0,8510000' , '广安市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511601, 8511600, '0,8511600,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511602, 8511600, '0,8511600,8510000' , '广安区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511621, 8511600, '0,8511600,8510000' , '岳池县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511622, 8511600, '0,8511600,8510000' , '武胜县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511623, 8511600, '0,8511600,8510000' , '邻水县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511681, 8511600, '0,8511600,8510000' , '华蓥市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511700, 8510000, '0,8510000' , '达州市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511701, 8511700, '0,8511700,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511702, 8511700, '0,8511700,8510000' , '通川区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511721, 8511700, '0,8511700,8510000' , '达县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511722, 8511700, '0,8511700,8510000' , '宣汉县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511723, 8511700, '0,8511700,8510000' , '开江县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511724, 8511700, '0,8511700,8510000' , '大竹县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511725, 8511700, '0,8511700,8510000' , '渠县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511781, 8511700, '0,8511700,8510000' , '万源市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511800, 8510000, '0,8510000' , '雅安市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511801, 8511800, '0,8511800,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511802, 8511800, '0,8511800,8510000' , '雨城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511821, 8511800, '0,8511800,8510000' , '名山县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511822, 8511800, '0,8511800,8510000' , '荥经县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511823, 8511800, '0,8511800,8510000' , '汉源县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511824, 8511800, '0,8511800,8510000' , '石棉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511825, 8511800, '0,8511800,8510000' , '天全县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511826, 8511800, '0,8511800,8510000' , '芦山县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511827, 8511800, '0,8511800,8510000' , '宝兴县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511900, 8510000, '0,8510000' , '巴中市', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511901, 8511900, '0,8511900,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511902, 8511900, '0,8511900,8510000' , '巴州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511921, 8511900, '0,8511900,8510000' , '通江县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511922, 8511900, '0,8511900,8510000' , '南江县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8511923, 8511900, '0,8511900,8510000' , '平昌县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512000, 8510000, '0,8510000' , '资阳市', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512001, 8512000, '0,8512000,8510000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512002, 8512000, '0,8512000,8510000' , '雁江区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512021, 8512000, '0,8512000,8510000' , '安岳县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512022, 8512000, '0,8512000,8510000' , '乐至县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8512081, 8512000, '0,8512000,8510000' , '简阳市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513200, 8510000, '0,8510000' , '阿坝藏族羌族自治州', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513221, 8513200, '0,8513200,8510000' , '汶川县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513222, 8513200, '0,8513200,8510000' , '理县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513223, 8513200, '0,8513200,8510000' , '茂县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513224, 8513200, '0,8513200,8510000' , '松潘县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513225, 8513200, '0,8513200,8510000' , '九寨沟县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513226, 8513200, '0,8513200,8510000' , '金川县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513227, 8513200, '0,8513200,8510000' , '小金县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513228, 8513200, '0,8513200,8510000' , '黑水县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513229, 8513200, '0,8513200,8510000' , '马尔康县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513230, 8513200, '0,8513200,8510000' , '壤塘县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513231, 8513200, '0,8513200,8510000' , '阿坝县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513232, 8513200, '0,8513200,8510000' , '若尔盖县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513233, 8513200, '0,8513200,8510000' , '红原县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513300, 8510000, '0,8510000' , '甘孜藏族自治州', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513321, 8513300, '0,8513300,8510000' , '康定县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513322, 8513300, '0,8513300,8510000' , '泸定县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513323, 8513300, '0,8513300,8510000' , '丹巴县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513324, 8513300, '0,8513300,8510000' , '九龙县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513325, 8513300, '0,8513300,8510000' , '雅江县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513326, 8513300, '0,8513300,8510000' , '道孚县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513327, 8513300, '0,8513300,8510000' , '炉霍县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513328, 8513300, '0,8513300,8510000' , '甘孜县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513329, 8513300, '0,8513300,8510000' , '新龙县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513330, 8513300, '0,8513300,8510000' , '德格县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513331, 8513300, '0,8513300,8510000' , '白玉县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513332, 8513300, '0,8513300,8510000' , '石渠县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513333, 8513300, '0,8513300,8510000' , '色达县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513334, 8513300, '0,8513300,8510000' , '理塘县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513335, 8513300, '0,8513300,8510000' , '巴塘县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513336, 8513300, '0,8513300,8510000' , '乡城县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513337, 8513300, '0,8513300,8510000' , '稻城县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513338, 8513300, '0,8513300,8510000' , '得荣县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513400, 8510000, '0,8510000' , '凉山彝族自治州', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513401, 8513400, '0,8513400,8510000' , '西昌市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513422, 8513400, '0,8513400,8510000' , '木里藏族自治县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513423, 8513400, '0,8513400,8510000' , '盐源县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513424, 8513400, '0,8513400,8510000' , '德昌县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513425, 8513400, '0,8513400,8510000' , '会理县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513426, 8513400, '0,8513400,8510000' , '会东县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513427, 8513400, '0,8513400,8510000' , '宁南县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513428, 8513400, '0,8513400,8510000' , '普格县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513429, 8513400, '0,8513400,8510000' , '布拖县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513430, 8513400, '0,8513400,8510000' , '金阳县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513431, 8513400, '0,8513400,8510000' , '昭觉县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513432, 8513400, '0,8513400,8510000' , '喜德县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513433, 8513400, '0,8513400,8510000' , '冕宁县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513434, 8513400, '0,8513400,8510000' , '越西县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513435, 8513400, '0,8513400,8510000' , '甘洛县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513436, 8513400, '0,8513400,8510000' , '美姑县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8513437, 8513400, '0,8513400,8510000' , '雷波县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520000, 0, '0' , '贵州省', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520100, 8520000, '0,8520000' , '贵阳市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520101, 8520100, '0,8520100,8520000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520102, 8520100, '0,8520100,8520000' , '南明区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520103, 8520100, '0,8520100,8520000' , '云岩区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520111, 8520100, '0,8520100,8520000' , '花溪区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520112, 8520100, '0,8520100,8520000' , '乌当区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520113, 8520100, '0,8520100,8520000' , '白云区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520114, 8520100, '0,8520100,8520000' , '小河区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520115, 8520100, '0,8520100,8520000' , '观山湖区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520121, 8520100, '0,8520100,8520000' , '开阳县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520122, 8520100, '0,8520100,8520000' , '息烽县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520123, 8520100, '0,8520100,8520000' , '修文县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520181, 8520100, '0,8520100,8520000' , '清镇市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520199, 8520100, '0,8520100,8520000' , '贵安新区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520200, 8520000, '0,8520000' , '六盘水市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520201, 8520200, '0,8520200,8520000' , '钟山区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520203, 8520200, '0,8520200,8520000' , '六枝特区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520221, 8520200, '0,8520200,8520000' , '水城县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520222, 8520200, '0,8520200,8520000' , '盘县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520299, 8520200, '0,8520200,8520000' , '六盘水水城矿务区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520300, 8520000, '0,8520000' , '遵义市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520301, 8520300, '0,8520300,8520000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520302, 8520300, '0,8520300,8520000' , '红花岗区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520303, 8520300, '0,8520300,8520000' , '汇川区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520321, 8520300, '0,8520300,8520000' , '播州区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520322, 8520300, '0,8520300,8520000' , '桐梓县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520323, 8520300, '0,8520300,8520000' , '绥阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520324, 8520300, '0,8520300,8520000' , '正安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520325, 8520300, '0,8520300,8520000' , '道真仡佬族苗族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520326, 8520300, '0,8520300,8520000' , '务川仡佬族苗族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520327, 8520300, '0,8520300,8520000' , '凤冈县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520328, 8520300, '0,8520300,8520000' , '湄潭县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520329, 8520300, '0,8520300,8520000' , '余庆县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520330, 8520300, '0,8520300,8520000' , '习水县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520381, 8520300, '0,8520300,8520000' , '赤水市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520382, 8520300, '0,8520300,8520000' , '仁怀市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520383, 8520300, '0,8520300,8520000' , '新蒲新区', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520400, 8520000, '0,8520000' , '安顺市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520401, 8520400, '0,8520400,8520000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520402, 8520400, '0,8520400,8520000' , '西秀区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520421, 8520400, '0,8520400,8520000' , '平坝县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520422, 8520400, '0,8520400,8520000' , '普定县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520423, 8520400, '0,8520400,8520000' , '镇宁布依族苗族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520424, 8520400, '0,8520400,8520000' , '关岭布依族苗族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520425, 8520400, '0,8520400,8520000' , '紫云苗族布依族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520499, 8520400, '0,8520400,8520000' , '经济技术开发区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8520498, 8520400, '0,8520400,8520000' , '贵安新区2', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522200, 8520000, '0,8520000' , '铜仁地区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522201, 8522200, '0,8522200,8520000' , '铜仁市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522222, 8522200, '0,8522200,8520000' , '江口县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522223, 8522200, '0,8522200,8520000' , '玉屏侗族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522224, 8522200, '0,8522200,8520000' , '石阡县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522225, 8522200, '0,8522200,8520000' , '思南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522226, 8522200, '0,8522200,8520000' , '印江土家族苗族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522227, 8522200, '0,8522200,8520000' , '德江县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522228, 8522200, '0,8522200,8520000' , '沿河土家族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522229, 8522200, '0,8522200,8520000' , '松桃苗族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522230, 8522200, '0,8522200,8520000' , '万山特区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522300, 8520000, '0,8520000' , '黔西南布依族苗族自治州', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522301, 8522300, '0,8522300,8520000' , '兴义市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522322, 8522300, '0,8522300,8520000' , '兴仁县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522323, 8522300, '0,8522300,8520000' , '普安县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522324, 8522300, '0,8522300,8520000' , '晴隆县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522325, 8522300, '0,8522300,8520000' , '贞丰县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522326, 8522300, '0,8522300,8520000' , '望谟县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522327, 8522300, '0,8522300,8520000' , '册亨县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522328, 8522300, '0,8522300,8520000' , '安龙县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522398, 8522300, '0,8522300,8520000' , '黔西南郊区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522399, 8522300, '0,8522300,8520000' , '顶效开发区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522400, 8520000, '0,8520000' , '毕节地区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522401, 8522400, '0,8522400,8520000' , '毕节市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522422, 8522400, '0,8522400,8520000' , '大方县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522423, 8522400, '0,8522400,8520000' , '黔西县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522424, 8522400, '0,8522400,8520000' , '金沙县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522425, 8522400, '0,8522400,8520000' , '织金县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522426, 8522400, '0,8522400,8520000' , '纳雍县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522427, 8522400, '0,8522400,8520000' , '威宁彝族回族苗族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522428, 8522400, '0,8522400,8520000' , '赫章县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522600, 8520000, '0,8520000' , '黔东南苗族侗族自治州', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522601, 8522600, '0,8522600,8520000' , '凯里市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522622, 8522600, '0,8522600,8520000' , '黄平县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522623, 8522600, '0,8522600,8520000' , '施秉县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522624, 8522600, '0,8522600,8520000' , '三穗县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522625, 8522600, '0,8522600,8520000' , '镇远县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522626, 8522600, '0,8522600,8520000' , '岑巩县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522627, 8522600, '0,8522600,8520000' , '天柱县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522628, 8522600, '0,8522600,8520000' , '锦屏县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522629, 8522600, '0,8522600,8520000' , '剑河县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522630, 8522600, '0,8522600,8520000' , '台江县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522631, 8522600, '0,8522600,8520000' , '黎平县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522632, 8522600, '0,8522600,8520000' , '榕江县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522633, 8522600, '0,8522600,8520000' , '从江县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522634, 8522600, '0,8522600,8520000' , '雷山县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522635, 8522600, '0,8522600,8520000' , '麻江县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522636, 8522600, '0,8522600,8520000' , '丹寨县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522700, 8520000, '0,8520000' , '黔南布依族苗族自治州', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522701, 8522700, '0,8522700,8520000' , '都匀市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522702, 8522700, '0,8522700,8520000' , '福泉市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522722, 8522700, '0,8522700,8520000' , '荔波县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522723, 8522700, '0,8522700,8520000' , '贵定县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522725, 8522700, '0,8522700,8520000' , '瓮安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522726, 8522700, '0,8522700,8520000' , '独山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522727, 8522700, '0,8522700,8520000' , '平塘县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522728, 8522700, '0,8522700,8520000' , '罗甸县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522729, 8522700, '0,8522700,8520000' , '长顺县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522730, 8522700, '0,8522700,8520000' , '龙里县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522731, 8522700, '0,8522700,8520000' , '惠水县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8522732, 8522700, '0,8522700,8520000' , '三都水族自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8529000, 8520000, '0,8520000' , '贵安新区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8529001, 8529000, '0,8529000,8520000' , '湖潮生态新城', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8529002, 8529000, '0,8529000,8520000' , '马场科技新城', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8529003, 8529000, '0,8529000,8520000' , '花溪大学城', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530000, 0, '0' , '云南省', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530100, 8530000, '0,8530000' , '昆明市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530101, 8530100, '0,8530100,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530102, 8530100, '0,8530100,8530000' , '五华区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530103, 8530100, '0,8530100,8530000' , '盘龙区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530111, 8530100, '0,8530100,8530000' , '官渡区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530112, 8530100, '0,8530100,8530000' , '西山区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530113, 8530100, '0,8530100,8530000' , '东川区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530121, 8530100, '0,8530100,8530000' , '呈贡县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530122, 8530100, '0,8530100,8530000' , '晋宁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530124, 8530100, '0,8530100,8530000' , '富民县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530125, 8530100, '0,8530100,8530000' , '宜良县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530126, 8530100, '0,8530100,8530000' , '石林彝族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530127, 8530100, '0,8530100,8530000' , '嵩明县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530128, 8530100, '0,8530100,8530000' , '禄劝彝族苗族自治县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530129, 8530100, '0,8530100,8530000' , '寻甸回族彝族自治县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530181, 8530100, '0,8530100,8530000' , '安宁市', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530300, 8530000, '0,8530000' , '曲靖市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530301, 8530300, '0,8530300,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530302, 8530300, '0,8530300,8530000' , '麒麟区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530321, 8530300, '0,8530300,8530000' , '马龙县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530322, 8530300, '0,8530300,8530000' , '陆良县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530323, 8530300, '0,8530300,8530000' , '师宗县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530324, 8530300, '0,8530300,8530000' , '罗平县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530325, 8530300, '0,8530300,8530000' , '富源县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530326, 8530300, '0,8530300,8530000' , '会泽县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530328, 8530300, '0,8530300,8530000' , '沾益县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530381, 8530300, '0,8530300,8530000' , '宣威市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530400, 8530000, '0,8530000' , '玉溪市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530401, 8530400, '0,8530400,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530402, 8530400, '0,8530400,8530000' , '红塔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530421, 8530400, '0,8530400,8530000' , '江川县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530422, 8530400, '0,8530400,8530000' , '澄江县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530423, 8530400, '0,8530400,8530000' , '通海县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530424, 8530400, '0,8530400,8530000' , '华宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530425, 8530400, '0,8530400,8530000' , '易门县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530426, 8530400, '0,8530400,8530000' , '峨山彝族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530427, 8530400, '0,8530400,8530000' , '新平彝族傣族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530428, 8530400, '0,8530400,8530000' , '元江哈尼族彝族傣族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530500, 8530000, '0,8530000' , '保山市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530501, 8530500, '0,8530500,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530502, 8530500, '0,8530500,8530000' , '隆阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530521, 8530500, '0,8530500,8530000' , '施甸县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530522, 8530500, '0,8530500,8530000' , '腾冲县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530523, 8530500, '0,8530500,8530000' , '龙陵县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530524, 8530500, '0,8530500,8530000' , '昌宁县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530600, 8530000, '0,8530000' , '昭通市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530601, 8530600, '0,8530600,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530602, 8530600, '0,8530600,8530000' , '昭阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530621, 8530600, '0,8530600,8530000' , '鲁甸县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530622, 8530600, '0,8530600,8530000' , '巧家县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530623, 8530600, '0,8530600,8530000' , '盐津县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530624, 8530600, '0,8530600,8530000' , '大关县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530625, 8530600, '0,8530600,8530000' , '永善县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530626, 8530600, '0,8530600,8530000' , '绥江县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530627, 8530600, '0,8530600,8530000' , '镇雄县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530628, 8530600, '0,8530600,8530000' , '彝良县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530629, 8530600, '0,8530600,8530000' , '威信县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530630, 8530600, '0,8530600,8530000' , '水富县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530700, 8530000, '0,8530000' , '丽江市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530701, 8530700, '0,8530700,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530702, 8530700, '0,8530700,8530000' , '古城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530721, 8530700, '0,8530700,8530000' , '玉龙纳西族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530722, 8530700, '0,8530700,8530000' , '永胜县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530723, 8530700, '0,8530700,8530000' , '华坪县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530724, 8530700, '0,8530700,8530000' , '宁蒗彝族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530800, 8530000, '0,8530000' , '普洱市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530801, 8530800, '0,8530800,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530802, 8530800, '0,8530800,8530000' , '思茅区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530821, 8530800, '0,8530800,8530000' , '宁洱哈尼族彝族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530822, 8530800, '0,8530800,8530000' , '墨江哈尼族自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530823, 8530800, '0,8530800,8530000' , '景东彝族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530824, 8530800, '0,8530800,8530000' , '景谷傣族彝族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530825, 8530800, '0,8530800,8530000' , '镇沅彝族哈尼族拉祜族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530826, 8530800, '0,8530800,8530000' , '江城哈尼族彝族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530827, 8530800, '0,8530800,8530000' , '孟连傣族拉祜族佤族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530828, 8530800, '0,8530800,8530000' , '澜沧拉祜族自治县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530829, 8530800, '0,8530800,8530000' , '西盟佤族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530900, 8530000, '0,8530000' , '临沧市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530901, 8530900, '0,8530900,8530000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530902, 8530900, '0,8530900,8530000' , '临翔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530921, 8530900, '0,8530900,8530000' , '凤庆县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530922, 8530900, '0,8530900,8530000' , '云县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530923, 8530900, '0,8530900,8530000' , '永德县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530924, 8530900, '0,8530900,8530000' , '镇康县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530925, 8530900, '0,8530900,8530000' , '双江拉祜族佤族布朗族傣族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530926, 8530900, '0,8530900,8530000' , '耿马傣族佤族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8530927, 8530900, '0,8530900,8530000' , '沧源佤族自治县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532300, 8530000, '0,8530000' , '楚雄彝族自治州', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532301, 8532300, '0,8532300,8530000' , '楚雄市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532322, 8532300, '0,8532300,8530000' , '双柏县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532323, 8532300, '0,8532300,8530000' , '牟定县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532324, 8532300, '0,8532300,8530000' , '南华县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532325, 8532300, '0,8532300,8530000' , '姚安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532326, 8532300, '0,8532300,8530000' , '大姚县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532327, 8532300, '0,8532300,8530000' , '永仁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532328, 8532300, '0,8532300,8530000' , '元谋县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532329, 8532300, '0,8532300,8530000' , '武定县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532331, 8532300, '0,8532300,8530000' , '禄丰县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532500, 8530000, '0,8530000' , '红河哈尼族彝族自治州', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532501, 8532500, '0,8532500,8530000' , '个旧市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532502, 8532500, '0,8532500,8530000' , '开远市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532503, 8532500, '0,8532500,8530000' , '蒙自市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532523, 8532500, '0,8532500,8530000' , '屏边苗族自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532524, 8532500, '0,8532500,8530000' , '建水县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532525, 8532500, '0,8532500,8530000' , '石屏县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532526, 8532500, '0,8532500,8530000' , '弥勒县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532527, 8532500, '0,8532500,8530000' , '泸西县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532528, 8532500, '0,8532500,8530000' , '元阳县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532529, 8532500, '0,8532500,8530000' , '红河县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532530, 8532500, '0,8532500,8530000' , '金平苗族瑶族傣族自治县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532531, 8532500, '0,8532500,8530000' , '绿春县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532532, 8532500, '0,8532500,8530000' , '河口瑶族自治县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532600, 8530000, '0,8530000' , '文山壮族苗族自治州', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532621, 8532600, '0,8532600,8530000' , '文山县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532622, 8532600, '0,8532600,8530000' , '砚山县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532623, 8532600, '0,8532600,8530000' , '西畴县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532624, 8532600, '0,8532600,8530000' , '麻栗坡县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532625, 8532600, '0,8532600,8530000' , '马关县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532626, 8532600, '0,8532600,8530000' , '丘北县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532627, 8532600, '0,8532600,8530000' , '广南县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532628, 8532600, '0,8532600,8530000' , '富宁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532800, 8530000, '0,8530000' , '西双版纳傣族自治州', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532801, 8532800, '0,8532800,8530000' , '景洪市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532822, 8532800, '0,8532800,8530000' , '勐海县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532823, 8532800, '0,8532800,8530000' , '勐腊县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532900, 8530000, '0,8530000' , '大理白族自治州', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532901, 8532900, '0,8532900,8530000' , '大理市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532922, 8532900, '0,8532900,8530000' , '漾濞彝族自治县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532923, 8532900, '0,8532900,8530000' , '祥云县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532924, 8532900, '0,8532900,8530000' , '宾川县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532925, 8532900, '0,8532900,8530000' , '弥渡县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532926, 8532900, '0,8532900,8530000' , '南涧彝族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532927, 8532900, '0,8532900,8530000' , '巍山彝族回族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532928, 8532900, '0,8532900,8530000' , '永平县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532929, 8532900, '0,8532900,8530000' , '云龙县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532930, 8532900, '0,8532900,8530000' , '洱源县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532931, 8532900, '0,8532900,8530000' , '剑川县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532932, 8532900, '0,8532900,8530000' , '鹤庆县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8532999, 8532900, '0,8532900,8530000' , '古城区', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533100, 8530000, '0,8530000' , '德宏傣族景颇族自治州', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533102, 8533100, '0,8533100,8530000' , '瑞丽市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533103, 8533100, '0,8533100,8530000' , '芒市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533122, 8533100, '0,8533100,8530000' , '梁河县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533123, 8533100, '0,8533100,8530000' , '盈江县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533124, 8533100, '0,8533100,8530000' , '陇川县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533300, 8530000, '0,8530000' , '怒江傈僳族自治州', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533321, 8533300, '0,8533300,8530000' , '泸水县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533323, 8533300, '0,8533300,8530000' , '福贡县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533324, 8533300, '0,8533300,8530000' , '贡山独龙族怒族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533325, 8533300, '0,8533300,8530000' , '兰坪白族普米族自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533400, 8530000, '0,8530000' , '迪庆藏族自治州', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533421, 8533400, '0,8533400,8530000' , '香格里拉县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533422, 8533400, '0,8533400,8530000' , '德钦县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8533423, 8533400, '0,8533400,8530000' , '维西傈僳族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540000, 0, '0' , '西藏自治区', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540100, 8540000, '0,8540000' , '拉萨市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540101, 8540100, '0,8540100,8540000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540102, 8540100, '0,8540100,8540000' , '城关区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540103, 8540100, '0,8540100,8540000' , '当雄羊八井支局', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540104, 8540100, '0,8540100,8540000' , '贡嘎机场支局', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540121, 8540100, '0,8540100,8540000' , '林周县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540122, 8540100, '0,8540100,8540000' , '当雄县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540123, 8540100, '0,8540100,8540000' , '尼木县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540124, 8540100, '0,8540100,8540000' , '曲水县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540125, 8540100, '0,8540100,8540000' , '堆龙德庆县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540126, 8540100, '0,8540100,8540000' , '达孜县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8540127, 8540100, '0,8540100,8540000' , '墨竹工卡县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542100, 8540000, '0,8540000' , '昌都地区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542101, 8542100, '0,8542100,8540000' , '俄洛桥支局', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542102, 8542100, '0,8542100,8540000' , '察雅吉塘支局', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542121, 8542100, '0,8542100,8540000' , '昌都县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542122, 8542100, '0,8542100,8540000' , '江达县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542123, 8542100, '0,8542100,8540000' , '贡觉县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542124, 8542100, '0,8542100,8540000' , '类乌齐县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542125, 8542100, '0,8542100,8540000' , '丁青县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542126, 8542100, '0,8542100,8540000' , '察雅县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542127, 8542100, '0,8542100,8540000' , '八宿县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542128, 8542100, '0,8542100,8540000' , '左贡县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542129, 8542100, '0,8542100,8540000' , '芒康县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542132, 8542100, '0,8542100,8540000' , '洛隆县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542133, 8542100, '0,8542100,8540000' , '边坝县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542200, 8540000, '0,8540000' , '山南地区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542201, 8542200, '0,8542200,8540000' , '泽当镇', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542221, 8542200, '0,8542200,8540000' , '乃东县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542222, 8542200, '0,8542200,8540000' , '扎囊县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542223, 8542200, '0,8542200,8540000' , '贡嘎县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542224, 8542200, '0,8542200,8540000' , '桑日县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542225, 8542200, '0,8542200,8540000' , '琼结县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542226, 8542200, '0,8542200,8540000' , '曲松县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542227, 8542200, '0,8542200,8540000' , '措美县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542228, 8542200, '0,8542200,8540000' , '洛扎县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542229, 8542200, '0,8542200,8540000' , '加查县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542231, 8542200, '0,8542200,8540000' , '隆子县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542232, 8542200, '0,8542200,8540000' , '错那县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542233, 8542200, '0,8542200,8540000' , '浪卡子县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542300, 8540000, '0,8540000' , '日喀则地区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542301, 8542300, '0,8542300,8540000' , '日喀则市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542302, 8542300, '0,8542300,8540000' , '樟木口岸', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542322, 8542300, '0,8542300,8540000' , '南木林县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542323, 8542300, '0,8542300,8540000' , '江孜县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542324, 8542300, '0,8542300,8540000' , '定日县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542325, 8542300, '0,8542300,8540000' , '萨迦县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542326, 8542300, '0,8542300,8540000' , '拉孜县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542327, 8542300, '0,8542300,8540000' , '昂仁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542328, 8542300, '0,8542300,8540000' , '谢通门县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542329, 8542300, '0,8542300,8540000' , '白朗县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542330, 8542300, '0,8542300,8540000' , '仁布县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542331, 8542300, '0,8542300,8540000' , '康马县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542332, 8542300, '0,8542300,8540000' , '定结县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542333, 8542300, '0,8542300,8540000' , '仲巴县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542334, 8542300, '0,8542300,8540000' , '亚东县', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542335, 8542300, '0,8542300,8540000' , '吉隆县', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542336, 8542300, '0,8542300,8540000' , '聂拉木县', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542337, 8542300, '0,8542300,8540000' , '萨嘎县', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542338, 8542300, '0,8542300,8540000' , '岗巴县', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542399, 8542300, '0,8542300,8540000' , '吉隆口岸', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542400, 8540000, '0,8540000' , '那曲地区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542401, 8542400, '0,8542400,8540000' , '双湖', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542421, 8542400, '0,8542400,8540000' , '那曲县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542422, 8542400, '0,8542400,8540000' , '嘉黎县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542423, 8542400, '0,8542400,8540000' , '比如县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542424, 8542400, '0,8542400,8540000' , '聂荣县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542425, 8542400, '0,8542400,8540000' , '安多县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542426, 8542400, '0,8542400,8540000' , '申扎县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542427, 8542400, '0,8542400,8540000' , '索县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542428, 8542400, '0,8542400,8540000' , '班戈县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542429, 8542400, '0,8542400,8540000' , '巴青县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542430, 8542400, '0,8542400,8540000' , '尼玛县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542500, 8540000, '0,8540000' , '阿里地区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542501, 8542500, '0,8542500,8540000' , '狮泉河', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542521, 8542500, '0,8542500,8540000' , '普兰县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542522, 8542500, '0,8542500,8540000' , '札达县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542523, 8542500, '0,8542500,8540000' , '阿里市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542524, 8542500, '0,8542500,8540000' , '日土县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542525, 8542500, '0,8542500,8540000' , '革吉县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542526, 8542500, '0,8542500,8540000' , '改则县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542527, 8542500, '0,8542500,8540000' , '措勤县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542600, 8540000, '0,8540000' , '林芝地区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542601, 8542600, '0,8542600,8540000' , '八一镇', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542602, 8542600, '0,8542600,8540000' , '米林帮宗支局', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542621, 8542600, '0,8542600,8540000' , '林芝县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542622, 8542600, '0,8542600,8540000' , '工布江达县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542623, 8542600, '0,8542600,8540000' , '米林县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542624, 8542600, '0,8542600,8540000' , '墨脱县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542625, 8542600, '0,8542600,8540000' , '波密县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542626, 8542600, '0,8542600,8540000' , '察隅县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8542627, 8542600, '0,8542600,8540000' , '朗县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610000, 0, '0' , '陕西省', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610100, 8610000, '0,8610000' , '西安市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610101, 8610100, '0,8610100,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610102, 8610100, '0,8610100,8610000' , '新城区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610103, 8610100, '0,8610100,8610000' , '碑林区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610104, 8610100, '0,8610100,8610000' , '莲湖区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610111, 8610100, '0,8610100,8610000' , '灞桥区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610112, 8610100, '0,8610100,8610000' , '未央区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610113, 8610100, '0,8610100,8610000' , '雁塔区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610114, 8610100, '0,8610100,8610000' , '阎良区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610115, 8610100, '0,8610100,8610000' , '临潼区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610116, 8610100, '0,8610100,8610000' , '长安区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610122, 8610100, '0,8610100,8610000' , '蓝田县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610124, 8610100, '0,8610100,8610000' , '周至县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610125, 8610100, '0,8610100,8610000' , '户县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610126, 8610100, '0,8610100,8610000' , '高陵县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610403, 8610100, '0,8610100,8610000' , '杨陵区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610199, 8610100, '0,8610100,8610000' , '航天分局', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610200, 8610000, '0,8610000' , '铜川市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610201, 8610200, '0,8610200,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610202, 8610200, '0,8610200,8610000' , '王益区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610203, 8610200, '0,8610200,8610000' , '印台区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610204, 8610200, '0,8610200,8610000' , '耀州区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610222, 8610200, '0,8610200,8610000' , '宜君县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610300, 8610000, '0,8610000' , '宝鸡市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610301, 8610300, '0,8610300,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610302, 8610300, '0,8610300,8610000' , '渭滨区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610303, 8610300, '0,8610300,8610000' , '金台区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610304, 8610300, '0,8610300,8610000' , '陈仓区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610322, 8610300, '0,8610300,8610000' , '凤翔县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610323, 8610300, '0,8610300,8610000' , '岐山县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610324, 8610300, '0,8610300,8610000' , '扶风县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610326, 8610300, '0,8610300,8610000' , '眉县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610327, 8610300, '0,8610300,8610000' , '陇县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610328, 8610300, '0,8610300,8610000' , '千阳县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610329, 8610300, '0,8610300,8610000' , '麟游县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610330, 8610300, '0,8610300,8610000' , '凤县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610331, 8610300, '0,8610300,8610000' , '太白县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610399, 8610300, '0,8610300,8610000' , '蔡家坡', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610400, 8610000, '0,8610000' , '咸阳市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610401, 8610400, '0,8610400,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610402, 8610400, '0,8610400,8610000' , '秦都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610404, 8610400, '0,8610400,8610000' , '渭城区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610422, 8610400, '0,8610400,8610000' , '三原县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610423, 8610400, '0,8610400,8610000' , '泾阳县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610424, 8610400, '0,8610400,8610000' , '乾县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610425, 8610400, '0,8610400,8610000' , '礼泉县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610426, 8610400, '0,8610400,8610000' , '永寿县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610427, 8610400, '0,8610400,8610000' , '彬县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610428, 8610400, '0,8610400,8610000' , '长武县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610429, 8610400, '0,8610400,8610000' , '旬邑县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610430, 8610400, '0,8610400,8610000' , '淳化县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610431, 8610400, '0,8610400,8610000' , '武功县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610481, 8610400, '0,8610400,8610000' , '兴平市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610499, 8610400, '0,8610400,8610000' , '西安沣西新城', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610500, 8610000, '0,8610000' , '渭南市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610501, 8610500, '0,8610500,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610502, 8610500, '0,8610500,8610000' , '临渭区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610521, 8610500, '0,8610500,8610000' , '华县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610522, 8610500, '0,8610500,8610000' , '潼关县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610523, 8610500, '0,8610500,8610000' , '大荔县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610524, 8610500, '0,8610500,8610000' , '合阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610525, 8610500, '0,8610500,8610000' , '澄城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610526, 8610500, '0,8610500,8610000' , '蒲城县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610527, 8610500, '0,8610500,8610000' , '白水县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610528, 8610500, '0,8610500,8610000' , '富平县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610581, 8610500, '0,8610500,8610000' , '韩城市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610582, 8610500, '0,8610500,8610000' , '华阴市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610600, 8610000, '0,8610000' , '延安市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610601, 8610600, '0,8610600,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610602, 8610600, '0,8610600,8610000' , '宝塔区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610621, 8610600, '0,8610600,8610000' , '延长县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610622, 8610600, '0,8610600,8610000' , '延川县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610623, 8610600, '0,8610600,8610000' , '子长县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610624, 8610600, '0,8610600,8610000' , '安塞县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610625, 8610600, '0,8610600,8610000' , '志丹县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610626, 8610600, '0,8610600,8610000' , '吴起县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610627, 8610600, '0,8610600,8610000' , '甘泉县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610628, 8610600, '0,8610600,8610000' , '富县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610629, 8610600, '0,8610600,8610000' , '洛川县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610630, 8610600, '0,8610600,8610000' , '宜川县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610631, 8610600, '0,8610600,8610000' , '黄龙县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610632, 8610600, '0,8610600,8610000' , '黄陵县', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610700, 8610000, '0,8610000' , '汉中市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610701, 8610700, '0,8610700,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610702, 8610700, '0,8610700,8610000' , '汉台区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610721, 8610700, '0,8610700,8610000' , '南郑县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610722, 8610700, '0,8610700,8610000' , '城固县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610723, 8610700, '0,8610700,8610000' , '洋县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610724, 8610700, '0,8610700,8610000' , '西乡县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610725, 8610700, '0,8610700,8610000' , '勉县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610726, 8610700, '0,8610700,8610000' , '宁强县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610727, 8610700, '0,8610700,8610000' , '略阳县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610728, 8610700, '0,8610700,8610000' , '镇巴县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610729, 8610700, '0,8610700,8610000' , '留坝县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610730, 8610700, '0,8610700,8610000' , '佛坪县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610797, 8610700, '0,8610700,8610000' , '河东店', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610798, 8610700, '0,8610700,8610000' , '大河坎', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610799, 8610700, '0,8610700,8610000' , '铺镇', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610800, 8610000, '0,8610000' , '榆林市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610801, 8610800, '0,8610800,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610802, 8610800, '0,8610800,8610000' , '榆阳区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610821, 8610800, '0,8610800,8610000' , '神木县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610822, 8610800, '0,8610800,8610000' , '府谷县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610823, 8610800, '0,8610800,8610000' , '横山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610824, 8610800, '0,8610800,8610000' , '靖边县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610825, 8610800, '0,8610800,8610000' , '定边县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610826, 8610800, '0,8610800,8610000' , '绥德县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610827, 8610800, '0,8610800,8610000' , '米脂县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610828, 8610800, '0,8610800,8610000' , '佳县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610829, 8610800, '0,8610800,8610000' , '吴堡县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610830, 8610800, '0,8610800,8610000' , '清涧县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610831, 8610800, '0,8610800,8610000' , '子洲县', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610832, 8610800, '0,8610800,8610000' , '店塔管理区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610898, 8610800, '0,8610800,8610000' , '锦界', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610899, 8610800, '0,8610800,8610000' , '大柳塔', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610900, 8610000, '0,8610000' , '安康市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610901, 8610900, '0,8610900,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610902, 8610900, '0,8610900,8610000' , '汉滨区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610921, 8610900, '0,8610900,8610000' , '汉阴县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610922, 8610900, '0,8610900,8610000' , '石泉县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610923, 8610900, '0,8610900,8610000' , '宁陕县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610924, 8610900, '0,8610900,8610000' , '紫阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610925, 8610900, '0,8610900,8610000' , '岚皋县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610926, 8610900, '0,8610900,8610000' , '平利县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610927, 8610900, '0,8610900,8610000' , '镇坪县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610928, 8610900, '0,8610900,8610000' , '旬阳县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610929, 8610900, '0,8610900,8610000' , '白河县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8610930, 8610900, '0,8610900,8610000' , '恒口', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611000, 8610000, '0,8610000' , '商洛市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611001, 8611000, '0,8611000,8610000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611002, 8611000, '0,8611000,8610000' , '商州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611021, 8611000, '0,8611000,8610000' , '洛南县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611022, 8611000, '0,8611000,8610000' , '丹凤县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611023, 8611000, '0,8611000,8610000' , '商南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611024, 8611000, '0,8611000,8610000' , '山阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611025, 8611000, '0,8611000,8610000' , '镇安县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8611026, 8611000, '0,8611000,8610000' , '柞水县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8619000, 8610000, '0,8610000' , '云计算基地', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8619001, 8619000, '0,8619000,8610000' , '云计算基地营业区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620000, 0, '0' , '甘肃省', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620100, 8620000, '0,8620000' , '兰州市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620101, 8620100, '0,8620100,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620102, 8620100, '0,8620100,8620000' , '城关区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620103, 8620100, '0,8620100,8620000' , '七里河区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620104, 8620100, '0,8620100,8620000' , '西固区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620105, 8620100, '0,8620100,8620000' , '安宁区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620111, 8620100, '0,8620100,8620000' , '红古区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620121, 8620100, '0,8620100,8620000' , '永登县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620122, 8620100, '0,8620100,8620000' , '皋兰县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620123, 8620100, '0,8620100,8620000' , '榆中县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620198, 8620100, '0,8620100,8620000' , '和平区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620199, 8620100, '0,8620100,8620000' , '兰州新区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620200, 8620000, '0,8620000' , '嘉峪关市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620201, 8620200, '0,8620200,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620300, 8620000, '0,8620000' , '金昌市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620301, 8620300, '0,8620300,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620302, 8620300, '0,8620300,8620000' , '金川区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620321, 8620300, '0,8620300,8620000' , '永昌县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620400, 8620000, '0,8620000' , '白银市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620401, 8620400, '0,8620400,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620402, 8620400, '0,8620400,8620000' , '白银区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620403, 8620400, '0,8620400,8620000' , '平川区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620421, 8620400, '0,8620400,8620000' , '靖远县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620422, 8620400, '0,8620400,8620000' , '会宁县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620423, 8620400, '0,8620400,8620000' , '景泰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620500, 8620000, '0,8620000' , '天水市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620501, 8620500, '0,8620500,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620502, 8620500, '0,8620500,8620000' , '秦州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620503, 8620500, '0,8620500,8620000' , '麦积区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620521, 8620500, '0,8620500,8620000' , '清水县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620522, 8620500, '0,8620500,8620000' , '秦安县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620523, 8620500, '0,8620500,8620000' , '甘谷县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620524, 8620500, '0,8620500,8620000' , '武山县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620525, 8620500, '0,8620500,8620000' , '张家川回族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620600, 8620000, '0,8620000' , '武威市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620601, 8620600, '0,8620600,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620602, 8620600, '0,8620600,8620000' , '凉州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620621, 8620600, '0,8620600,8620000' , '民勤县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620622, 8620600, '0,8620600,8620000' , '古浪县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620623, 8620600, '0,8620600,8620000' , '天祝藏族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620700, 8620000, '0,8620000' , '张掖市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620701, 8620700, '0,8620700,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620702, 8620700, '0,8620700,8620000' , '甘州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620721, 8620700, '0,8620700,8620000' , '肃南裕固族自治县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620722, 8620700, '0,8620700,8620000' , '民乐县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620723, 8620700, '0,8620700,8620000' , '临泽县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620724, 8620700, '0,8620700,8620000' , '高台县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620725, 8620700, '0,8620700,8620000' , '山丹县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620800, 8620000, '0,8620000' , '平凉市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620801, 8620800, '0,8620800,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620802, 8620800, '0,8620800,8620000' , '崆峒区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620821, 8620800, '0,8620800,8620000' , '泾川县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620822, 8620800, '0,8620800,8620000' , '灵台县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620823, 8620800, '0,8620800,8620000' , '崇信县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620824, 8620800, '0,8620800,8620000' , '华亭县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620825, 8620800, '0,8620800,8620000' , '庄浪县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620826, 8620800, '0,8620800,8620000' , '静宁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620900, 8620000, '0,8620000' , '酒泉市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620901, 8620900, '0,8620900,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620902, 8620900, '0,8620900,8620000' , '肃州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620921, 8620900, '0,8620900,8620000' , '金塔县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620922, 8620900, '0,8620900,8620000' , '瓜州县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620923, 8620900, '0,8620900,8620000' , '肃北蒙古族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620924, 8620900, '0,8620900,8620000' , '阿克塞哈萨克族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620981, 8620900, '0,8620900,8620000' , '玉门市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8620982, 8620900, '0,8620900,8620000' , '敦煌市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621000, 8620000, '0,8620000' , '庆阳市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621001, 8621000, '0,8621000,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621002, 8621000, '0,8621000,8620000' , '西峰区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621021, 8621000, '0,8621000,8620000' , '庆城县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621022, 8621000, '0,8621000,8620000' , '环县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621023, 8621000, '0,8621000,8620000' , '华池县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621024, 8621000, '0,8621000,8620000' , '合水县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621025, 8621000, '0,8621000,8620000' , '正宁县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621026, 8621000, '0,8621000,8620000' , '宁县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621027, 8621000, '0,8621000,8620000' , '镇原县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621100, 8620000, '0,8620000' , '定西市', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621101, 8621100, '0,8621100,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621102, 8621100, '0,8621100,8620000' , '安定区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621121, 8621100, '0,8621100,8620000' , '通渭县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621122, 8621100, '0,8621100,8620000' , '陇西县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621123, 8621100, '0,8621100,8620000' , '渭源县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621124, 8621100, '0,8621100,8620000' , '临洮县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621125, 8621100, '0,8621100,8620000' , '漳县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621126, 8621100, '0,8621100,8620000' , '岷县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621200, 8620000, '0,8620000' , '陇南市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621201, 8621200, '0,8621200,8620000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621202, 8621200, '0,8621200,8620000' , '武都区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621221, 8621200, '0,8621200,8620000' , '成县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621222, 8621200, '0,8621200,8620000' , '文县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621223, 8621200, '0,8621200,8620000' , '宕昌县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621224, 8621200, '0,8621200,8620000' , '康县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621225, 8621200, '0,8621200,8620000' , '西和县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621226, 8621200, '0,8621200,8620000' , '礼县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621227, 8621200, '0,8621200,8620000' , '徽县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8621228, 8621200, '0,8621200,8620000' , '两当县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622900, 8620000, '0,8620000' , '临夏回族自治州', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622901, 8622900, '0,8622900,8620000' , '临夏市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622921, 8622900, '0,8622900,8620000' , '临夏县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622922, 8622900, '0,8622900,8620000' , '康乐县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622923, 8622900, '0,8622900,8620000' , '永靖县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622924, 8622900, '0,8622900,8620000' , '广河县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622925, 8622900, '0,8622900,8620000' , '和政县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622926, 8622900, '0,8622900,8620000' , '东乡族自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8622927, 8622900, '0,8622900,8620000' , '积石山保安族东乡族撒拉族自治县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623000, 8620000, '0,8620000' , '甘南藏族自治州', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623001, 8623000, '0,8623000,8620000' , '合作市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623021, 8623000, '0,8623000,8620000' , '临潭县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623022, 8623000, '0,8623000,8620000' , '卓尼县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623023, 8623000, '0,8623000,8620000' , '舟曲县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623024, 8623000, '0,8623000,8620000' , '迭部县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623025, 8623000, '0,8623000,8620000' , '玛曲县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623026, 8623000, '0,8623000,8620000' , '碌曲县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8623027, 8623000, '0,8623000,8620000' , '夏河县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630000, 0, '0' , '青海省', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630100, 8630000, '0,8630000' , '西宁市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630101, 8630100, '0,8630100,8630000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630102, 8630100, '0,8630100,8630000' , '城东区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630103, 8630100, '0,8630100,8630000' , '城中区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630104, 8630100, '0,8630100,8630000' , '城西区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630105, 8630100, '0,8630100,8630000' , '城北区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630121, 8630100, '0,8630100,8630000' , '大通回族土族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630122, 8630100, '0,8630100,8630000' , '湟中县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8630123, 8630100, '0,8630100,8630000' , '湟源县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632100, 8630000, '0,8630000' , '海东地区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632121, 8632100, '0,8632100,8630000' , '平安县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632122, 8632100, '0,8632100,8630000' , '民和回族土族自治县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632123, 8632100, '0,8632100,8630000' , '乐都县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632126, 8632100, '0,8632100,8630000' , '互助土族自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632127, 8632100, '0,8632100,8630000' , '化隆回族自治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632128, 8632100, '0,8632100,8630000' , '循化撒拉族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632200, 8630000, '0,8630000' , '海北藏族自治州', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632221, 8632200, '0,8632200,8630000' , '门源回族自治县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632222, 8632200, '0,8632200,8630000' , '祁连县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632223, 8632200, '0,8632200,8630000' , '海晏县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632224, 8632200, '0,8632200,8630000' , '刚察县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632225, 8632200, '0,8632200,8630000' , '西海镇', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632300, 8630000, '0,8630000' , '黄南藏族自治州', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632321, 8632300, '0,8632300,8630000' , '同仁县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632322, 8632300, '0,8632300,8630000' , '尖扎县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632323, 8632300, '0,8632300,8630000' , '泽库县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632324, 8632300, '0,8632300,8630000' , '河南蒙古族自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632325, 8632300, '0,8632300,8630000' , '李家峡', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632500, 8630000, '0,8630000' , '海南藏族自治州', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632521, 8632500, '0,8632500,8630000' , '共和县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632522, 8632500, '0,8632500,8630000' , '同德县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632523, 8632500, '0,8632500,8630000' , '贵德县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632524, 8632500, '0,8632500,8630000' , '兴海县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632525, 8632500, '0,8632500,8630000' , '贵南县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632600, 8630000, '0,8630000' , '果洛藏族自治州', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632621, 8632600, '0,8632600,8630000' , '玛沁县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632622, 8632600, '0,8632600,8630000' , '班玛县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632623, 8632600, '0,8632600,8630000' , '甘德县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632624, 8632600, '0,8632600,8630000' , '达日县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632625, 8632600, '0,8632600,8630000' , '久治县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632626, 8632600, '0,8632600,8630000' , '玛多县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632700, 8630000, '0,8630000' , '玉树藏族自治州', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632721, 8632700, '0,8632700,8630000' , '玉树县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632722, 8632700, '0,8632700,8630000' , '杂多县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632723, 8632700, '0,8632700,8630000' , '称多县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632724, 8632700, '0,8632700,8630000' , '治多县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632725, 8632700, '0,8632700,8630000' , '囊谦县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632726, 8632700, '0,8632700,8630000' , '曲麻莱县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632800, 8630000, '0,8630000' , '海西蒙古族藏族自治州', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632802, 8632800, '0,8632800,8630000' , '德令哈市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632821, 8632800, '0,8632800,8630000' , '乌兰县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632822, 8632800, '0,8632800,8630000' , '都兰县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632823, 8632800, '0,8632800,8630000' , '天峻县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632824, 8632800, '0,8632800,8630000' , '大柴旦', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632825, 8632800, '0,8632800,8630000' , '茫崖', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632826, 8632800, '0,8632800,8630000' , '冷湖', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632900, 8630000, '0,8630000' , '格尔木市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8632801, 8632900, '0,8632900,8630000' , '格尔木市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640000, 0, '0' , '宁夏回族自治区', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640100, 8640000, '0,8640000' , '银川市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640101, 8640100, '0,8640100,8640000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640104, 8640100, '0,8640100,8640000' , '兴庆区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640105, 8640100, '0,8640100,8640000' , '西夏区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640106, 8640100, '0,8640100,8640000' , '金凤区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640121, 8640100, '0,8640100,8640000' , '永宁县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640122, 8640100, '0,8640100,8640000' , '贺兰县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640181, 8640100, '0,8640100,8640000' , '灵武市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640197, 8640100, '0,8640100,8640000' , '宁东    ', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640198, 8640100, '0,8640100,8640000' , '神华宁东', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640199, 8640100, '0,8640100,8640000' , '神华银川', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640200, 8640000, '0,8640000' , '石嘴山市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640201, 8640200, '0,8640200,8640000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640202, 8640200, '0,8640200,8640000' , '大武口区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640205, 8640200, '0,8640200,8640000' , '惠农区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640221, 8640200, '0,8640200,8640000' , '平罗县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640298, 8640200, '0,8640200,8640000' , '神华大武口', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640299, 8640200, '0,8640200,8640000' , '神华石嘴山', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640300, 8640000, '0,8640000' , '吴忠市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640301, 8640300, '0,8640300,8640000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640302, 8640300, '0,8640300,8640000' , '利通区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640303, 8640300, '0,8640300,8640000' , '红寺堡区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640323, 8640300, '0,8640300,8640000' , '盐池县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640324, 8640300, '0,8640300,8640000' , '同心县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640381, 8640300, '0,8640300,8640000' , '青铜峡市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640400, 8640000, '0,8640000' , '固原市', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640401, 8640400, '0,8640400,8640000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640402, 8640400, '0,8640400,8640000' , '原州区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640422, 8640400, '0,8640400,8640000' , '西吉县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640423, 8640400, '0,8640400,8640000' , '隆德县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640424, 8640400, '0,8640400,8640000' , '泾源县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640425, 8640400, '0,8640400,8640000' , '彭阳县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640499, 8640400, '0,8640400,8640000' , '三营', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640500, 8640000, '0,8640000' , '中卫市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640501, 8640500, '0,8640500,8640000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640502, 8640500, '0,8640500,8640000' , '沙坡头区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640521, 8640500, '0,8640500,8640000' , '中宁县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640522, 8640500, '0,8640500,8640000' , '海原县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8640599, 8640500, '0,8640500,8640000' , '海兴开发区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650000, 0, '0' , '新疆维吾尔自治区', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650100, 8650000, '0,8650000' , '乌鲁木齐市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650101, 8650100, '0,8650100,8650000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650102, 8650100, '0,8650100,8650000' , '天山区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650103, 8650100, '0,8650100,8650000' , '沙依巴克区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650104, 8650100, '0,8650100,8650000' , '新市区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650105, 8650100, '0,8650100,8650000' , '水磨沟区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650106, 8650100, '0,8650100,8650000' , '头屯河区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650107, 8650100, '0,8650100,8650000' , '达坂城区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650108, 8650100, '0,8650100,8650000' , '东山区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650109, 8650100, '0,8650100,8650000' , '米东区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650121, 8650100, '0,8650100,8650000' , '乌鲁木齐县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650200, 8650000, '0,8650000' , '克拉玛依市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650201, 8650200, '0,8650200,8650000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650202, 8650200, '0,8650200,8650000' , '独山子区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650203, 8650200, '0,8650200,8650000' , '克拉玛依区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650204, 8650200, '0,8650200,8650000' , '白碱滩区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8650205, 8650200, '0,8650200,8650000' , '乌尔禾区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654299, 8650200, '0,8650200,8650000' , '五五新镇', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652100, 8650000, '0,8650000' , '吐鲁番地区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652101, 8652100, '0,8652100,8650000' , '吐鲁番市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652122, 8652100, '0,8652100,8650000' , '鄯善县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652123, 8652100, '0,8652100,8650000' , '托克逊县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652200, 8650000, '0,8650000' , '哈密地区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652201, 8652200, '0,8652200,8650000' , '哈密市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652222, 8652200, '0,8652200,8650000' , '巴里坤哈萨克自治县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652223, 8652200, '0,8652200,8650000' , '伊吾县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652300, 8650000, '0,8650000' , '昌吉回族自治州', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652301, 8652300, '0,8652300,8650000' , '昌吉市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652302, 8652300, '0,8652300,8650000' , '阜康市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652323, 8652300, '0,8652300,8650000' , '呼图壁县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652324, 8652300, '0,8652300,8650000' , '玛纳斯县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652325, 8652300, '0,8652300,8650000' , '奇台县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652327, 8652300, '0,8652300,8650000' , '吉木萨尔县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652328, 8652300, '0,8652300,8650000' , '木垒哈萨克自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659004, 8652300, '0,8652300,8650000' , '五家渠市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652700, 8650000, '0,8650000' , '博尔塔拉蒙古自治州', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652701, 8652700, '0,8652700,8650000' , '博乐市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652702, 8652700, '0,8652700,8650000' , '阿拉山口市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659007, 8652700, '0,8652700,8650000' , '双河市', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652722, 8652700, '0,8652700,8650000' , '精河县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652723, 8652700, '0,8652700,8650000' , '温泉县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652800, 8650000, '0,8650000' , '巴音郭楞蒙古自治州', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652801, 8652800, '0,8652800,8650000' , '库尔勒市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652822, 8652800, '0,8652800,8650000' , '轮台县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652823, 8652800, '0,8652800,8650000' , '尉犁县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652824, 8652800, '0,8652800,8650000' , '若羌县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652825, 8652800, '0,8652800,8650000' , '且末县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652826, 8652800, '0,8652800,8650000' , '焉耆回族自治县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652827, 8652800, '0,8652800,8650000' , '和静县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652828, 8652800, '0,8652800,8650000' , '和硕县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652829, 8652800, '0,8652800,8650000' , '博湖县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659006, 8652800, '0,8652800,8650000' , '铁门关市', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652900, 8650000, '0,8650000' , '阿克苏地区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652901, 8652900, '0,8652900,8650000' , '阿克苏市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652922, 8652900, '0,8652900,8650000' , '温宿县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652923, 8652900, '0,8652900,8650000' , '库车县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652924, 8652900, '0,8652900,8650000' , '沙雅县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652925, 8652900, '0,8652900,8650000' , '新和县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652926, 8652900, '0,8652900,8650000' , '拜城县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652927, 8652900, '0,8652900,8650000' , '乌什县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652928, 8652900, '0,8652900,8650000' , '阿瓦提县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652929, 8652900, '0,8652900,8650000' , '柯坪县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652998, 8652900, '0,8652900,8650000' , '金银川', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8652999, 8652900, '0,8652900,8650000' , '塔门', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659002, 8652900, '0,8652900,8650000' , '阿拉尔市', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653000, 8650000, '0,8650000' , '克孜勒苏柯尔克孜自治州', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653001, 8653000, '0,8653000,8650000' , '阿图什市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653022, 8653000, '0,8653000,8650000' , '阿克陶县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653023, 8653000, '0,8653000,8650000' , '阿合奇县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653024, 8653000, '0,8653000,8650000' , '乌恰县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653100, 8650000, '0,8650000' , '喀什地区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653101, 8653100, '0,8653100,8650000' , '喀什市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653121, 8653100, '0,8653100,8650000' , '疏附县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653122, 8653100, '0,8653100,8650000' , '疏勒县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653123, 8653100, '0,8653100,8650000' , '英吉沙县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653124, 8653100, '0,8653100,8650000' , '泽普县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653125, 8653100, '0,8653100,8650000' , '莎车县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653126, 8653100, '0,8653100,8650000' , '叶城县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653127, 8653100, '0,8653100,8650000' , '麦盖提县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653128, 8653100, '0,8653100,8650000' , '岳普湖县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653129, 8653100, '0,8653100,8650000' , '伽师县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653130, 8653100, '0,8653100,8650000' , '巴楚县', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653131, 8653100, '0,8653100,8650000' , '塔什库尔干塔吉克自治县', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653199, 8653100, '0,8653100,8650000' , '奎依巴格', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659003, 8653100, '0,8653100,8650000' , '图木舒克市', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653200, 8650000, '0,8650000' , '和田地区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653201, 8653200, '0,8653200,8650000' , '和田市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653221, 8653200, '0,8653200,8650000' , '和田县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653222, 8653200, '0,8653200,8650000' , '墨玉县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653223, 8653200, '0,8653200,8650000' , '皮山县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653224, 8653200, '0,8653200,8650000' , '洛浦县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653225, 8653200, '0,8653200,8650000' , '策勒县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653226, 8653200, '0,8653200,8650000' , '于田县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8653227, 8653200, '0,8653200,8650000' , '民丰县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659009, 8653200, '0,8653200,8650000' , '昆玉市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654000, 8650000, '0,8650000' , '伊犁哈萨克自治州', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654002, 8654000, '0,8654000,8650000' , '伊宁市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654004, 8654000, '0,8654000,8650000' , '霍尔果斯市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654021, 8654000, '0,8654000,8650000' , '伊宁县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654022, 8654000, '0,8654000,8650000' , '察布查尔锡伯自治县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654023, 8654000, '0,8654000,8650000' , '霍城县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654024, 8654000, '0,8654000,8650000' , '巩留县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654025, 8654000, '0,8654000,8650000' , '新源县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654026, 8654000, '0,8654000,8650000' , '昭苏县', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654027, 8654000, '0,8654000,8650000' , '特克斯县', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654028, 8654000, '0,8654000,8650000' , '尼勒克县', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654003, 8650000, '0,8650000' , '奎屯市', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654190, 8654003, '0,8654003,8650000' , '奎屯市市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654191, 8654003, '0,8654003,8650000' , '车排子', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654200, 8650000, '0,8650000' , '塔城地区', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654201, 8654200, '0,8654200,8650000' , '塔城市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654202, 8654200, '0,8654200,8650000' , '乌苏市', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654221, 8654200, '0,8654200,8650000' , '额敏县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654223, 8654200, '0,8654200,8650000' , '沙湾县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654224, 8654200, '0,8654200,8650000' , '托里县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654225, 8654200, '0,8654200,8650000' , '裕民县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654226, 8654200, '0,8654200,8650000' , '和布克赛尔蒙古自治县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654300, 8650000, '0,8650000' , '阿勒泰地区', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654301, 8654300, '0,8654300,8650000' , '阿勒泰市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654321, 8654300, '0,8654300,8650000' , '布尔津县', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654322, 8654300, '0,8654300,8650000' , '富蕴县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654323, 8654300, '0,8654300,8650000' , '福海县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654324, 8654300, '0,8654300,8650000' , '哈巴河县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654325, 8654300, '0,8654300,8650000' , '青河县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8654326, 8654300, '0,8654300,8650000' , '吉木乃县', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659005, 8654300, '0,8654300,8650000' , '北屯市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659001, 8650000, '0,8650000' , '石河子市', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659190, 8659001, '0,8659001,8650000' , '石河子市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659191, 8659001, '0,8659001,8650000' , '下野地', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659192, 8659001, '0,8659001,8650000' , '莫索湾', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659193, 8659001, '0,8659001,8650000' , '北泉', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659001, 8659001, '0,8659001,8650000' , '石河子市', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659002, 8659001, '0,8659001,8650000' , '阿拉尔市', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659003, 8659001, '0,8659001,8650000' , '图木舒克市', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659004, 8659001, '0,8659001,8650000' , '五家渠市', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8659005, 8659001, '0,8659001,8650000' , '北屯市', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8609902, 0, '0' , '中国电信集团物联网公司', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8609903, 0, '0' , '卫星通信', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8609904, 0, '0' , '卫星公司', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990000, 0, '0' , '虚拟省', 36, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990100, 8990000, '0,8990000' , '虚拟本地网A', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990101, 8990100, '0,8990100,8990000' , '虚拟县A01', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990102, 8990100, '0,8990100,8990000' , '虚拟县A02', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990103, 8990100, '0,8990100,8990000' , '虚拟县A03', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990200, 8990000, '0,8990000' , '虚拟本地网B', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990201, 8990200, '0,8990200,8990000' , '虚拟县B01', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990202, 8990200, '0,8990200,8990000' , '虚拟县B02', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990203, 8990200, '0,8990200,8990000' , '虚拟县B03', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990300, 8990000, '0,8990000' , '虚拟本地网C', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990301, 8990300, '0,8990300,8990000' , '虚拟县C01', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990302, 8990300, '0,8990300,8990000' , '虚拟县C02', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8990303, 8990300, '0,8990300,8990000' , '虚拟县C03', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9000000, 0, '0' , '非中国', 37, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110000, 0, '0' , '亚洲', 38, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110100, 9110000, '0,9110000' , '阿富汗', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110200, 9110000, '0,9110000' , '巴林', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110300, 9110000, '0,9110000' , '孟加拉国', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110400, 9110000, '0,9110000' , '不丹', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110500, 9110000, '0,9110000' , '文莱', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110600, 9110000, '0,9110000' , '缅甸', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110700, 9110000, '0,9110000' , '柬埔寨', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110800, 9110000, '0,9110000' , '塞浦路斯', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9110900, 9110000, '0,9110000' , '朝鲜', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111000, 9110000, '0,9110000' , '香港', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111100, 9110000, '0,9110000' , '印度', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111200, 9110000, '0,9110000' , '印度尼西亚', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111300, 9110000, '0,9110000' , '伊朗', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111400, 9110000, '0,9110000' , '伊拉克', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111500, 9110000, '0,9110000' , '以色列', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111600, 9110000, '0,9110000' , '日本', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111700, 9110000, '0,9110000' , '约旦', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111800, 9110000, '0,9110000' , '科威特', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9111900, 9110000, '0,9110000' , '老挝', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112000, 9110000, '0,9110000' , '黎巴嫩', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112100, 9110000, '0,9110000' , '澳门', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112200, 9110000, '0,9110000' , '马来西亚', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112300, 9110000, '0,9110000' , '马尔代夫', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112400, 9110000, '0,9110000' , '蒙古', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112500, 9110000, '0,9110000' , '尼泊尔', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112600, 9110000, '0,9110000' , '阿曼', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112700, 9110000, '0,9110000' , '巴基斯坦', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112800, 9110000, '0,9110000' , '巴勒斯坦', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9112900, 9110000, '0,9110000' , '菲律宾', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113000, 9110000, '0,9110000' , '卡塔尔', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113100, 9110000, '0,9110000' , '沙特阿拉伯', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113200, 9110000, '0,9110000' , '新加坡', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113300, 9110000, '0,9110000' , '韩国', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113400, 9110000, '0,9110000' , '斯里兰卡', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113500, 9110000, '0,9110000' , '叙利亚', 36, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113600, 9110000, '0,9110000' , '泰国', 37, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113700, 9110000, '0,9110000' , '土耳其', 38, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113800, 9110000, '0,9110000' , '阿拉伯联合酋长国', 39, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9113900, 9110000, '0,9110000' , '也门共和国', 40, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9114100, 9110000, '0,9110000' , '越南', 41, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9114300, 9110000, '0,9110000' , '台湾省', 42, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9119900, 9110000, '0,9110000' , '亚洲其他', 43, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120000, 0, '0' , '非洲', 39, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120100, 9120000, '0,9120000' , '阿尔及利亚', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120200, 9120000, '0,9120000' , '安哥拉', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120300, 9120000, '0,9120000' , '贝宁', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120400, 9120000, '0,9120000' , '博茨瓦那', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120500, 9120000, '0,9120000' , '布隆迪', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120600, 9120000, '0,9120000' , '喀麦隆', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120700, 9120000, '0,9120000' , '加那利群岛', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120800, 9120000, '0,9120000' , '佛得角', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9120900, 9120000, '0,9120000' , '中非', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121000, 9120000, '0,9120000' , '塞卜泰（休达）', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121100, 9120000, '0,9120000' , '乍得', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121200, 9120000, '0,9120000' , '科摩罗', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121300, 9120000, '0,9120000' , '刚果', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121400, 9120000, '0,9120000' , '吉布提', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121500, 9120000, '0,9120000' , '埃及', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121600, 9120000, '0,9120000' , '赤道几内亚', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121700, 9120000, '0,9120000' , '埃塞俄比亚', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9121900, 9120000, '0,9120000' , '冈比亚', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122000, 9120000, '0,9120000' , '加纳', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122100, 9120000, '0,9120000' , '几内亚', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122200, 9120000, '0,9120000' , '几内亚（比绍）', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122300, 9120000, '0,9120000' , '科特迪瓦', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122400, 9120000, '0,9120000' , '肯尼亚', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122500, 9120000, '0,9120000' , '利比里亚', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122600, 9120000, '0,9120000' , '利比亚', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122700, 9120000, '0,9120000' , '马达加斯加', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122800, 9120000, '0,9120000' , '马拉维', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9122900, 9120000, '0,9120000' , '马里', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123000, 9120000, '0,9120000' , '毛里塔尼亚', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123100, 9120000, '0,9120000' , '毛里求斯', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123200, 9120000, '0,9120000' , '摩洛哥', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123300, 9120000, '0,9120000' , '莫桑比克', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123400, 9120000, '0,9120000' , '纳米比亚', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123500, 9120000, '0,9120000' , '尼日尔', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123600, 9120000, '0,9120000' , '尼日利亚', 36, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123700, 9120000, '0,9120000' , '留尼汪', 37, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123800, 9120000, '0,9120000' , '卢旺达', 38, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9123900, 9120000, '0,9120000' , '圣多美和普林西比', 39, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124000, 9120000, '0,9120000' , '塞内加尔', 40, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124100, 9120000, '0,9120000' , '塞舌尔', 41, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124200, 9120000, '0,9120000' , '塞拉利昂', 42, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124300, 9120000, '0,9120000' , '马里', 43, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124400, 9120000, '0,9120000' , '南非', 44, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124500, 9120000, '0,9120000' , '西撒哈拉', 45, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124600, 9120000, '0,9120000' , '苏丹', 46, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124700, 9120000, '0,9120000' , '坦桑尼亚', 47, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124800, 9120000, '0,9120000' , '多哥', 48, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9124900, 9120000, '0,9120000' , '突尼斯', 49, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125000, 9120000, '0,9120000' , '乌干达', 50, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125100, 9120000, '0,9120000' , '布基纳法', 51, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125200, 9120000, '0,9120000' , '扎伊尔', 52, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125300, 9120000, '0,9120000' , '赞比亚', 53, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125400, 9120000, '0,9120000' , '津巴布韦', 54, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125500, 9120000, '0,9120000' , '莱托', 55, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125600, 9120000, '0,9120000' , '梅利利亚', 56, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125700, 9120000, '0,9120000' , '斯威士兰', 57, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125800, 9120000, '0,9120000' , '厄立特里亚', 58, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9125900, 9120000, '0,9120000' , '刚果（金）', 59, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9126000, 9120000, '0,9120000' , '加蓬', 60, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9126100, 9120000, '0,9120000' , '刚果（布）', 61, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9129900, 9120000, '0,9120000' , '非洲其他', 62, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130000, 0, '0' , '欧洲', 40, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130100, 9130000, '0,9130000' , '比利时', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130200, 9130000, '0,9130000' , '丹麦', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130300, 9130000, '0,9130000' , '英国', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130400, 9130000, '0,9130000' , '德意志联邦共和国', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130500, 9130000, '0,9130000' , '法国', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130600, 9130000, '0,9130000' , '爱尔兰', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130700, 9130000, '0,9130000' , '意大利', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130800, 9130000, '0,9130000' , '卢森堡', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9130900, 9130000, '0,9130000' , '荷兰', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131000, 9130000, '0,9130000' , '希腊', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131100, 9130000, '0,9130000' , '葡萄牙', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131200, 9130000, '0,9130000' , '西班牙', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131300, 9130000, '0,9130000' , '阿尔巴尼亚', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131400, 9130000, '0,9130000' , '安道尔', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131500, 9130000, '0,9130000' , '奥地利', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131600, 9130000, '0,9130000' , '保加利亚', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9131800, 9130000, '0,9130000' , '芬兰', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132000, 9130000, '0,9130000' , '直布罗陀', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132100, 9130000, '0,9130000' , '匈牙利', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132200, 9130000, '0,9130000' , '冰岛', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132300, 9130000, '0,9130000' , '列支敦士登', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132400, 9130000, '0,9130000' , '马耳他', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132500, 9130000, '0,9130000' , '摩纳哥', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132600, 9130000, '0,9130000' , '挪威', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132700, 9130000, '0,9130000' , '波兰', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132800, 9130000, '0,9130000' , '罗马尼亚', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9132900, 9130000, '0,9130000' , '圣马力诺', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133000, 9130000, '0,9130000' , '瑞典', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133100, 9130000, '0,9130000' , '瑞士', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133400, 9130000, '0,9130000' , '爱沙尼亚', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133500, 9130000, '0,9130000' , '拉脱维亚', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133600, 9130000, '0,9130000' , '立陶宛', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133700, 9130000, '0,9130000' , '格鲁吉亚', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133800, 9130000, '0,9130000' , '亚美尼亚', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9133900, 9130000, '0,9130000' , '阿塞拜疆', 36, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134000, 9130000, '0,9130000' , '白俄罗斯', 37, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134100, 9130000, '0,9130000' , '哈萨克斯坦', 38, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134200, 9130000, '0,9130000' , '吉尔吉斯', 39, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134300, 9130000, '0,9130000' , '摩尔多瓦', 40, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134400, 9130000, '0,9130000' , '俄罗斯', 41, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134500, 9130000, '0,9130000' , '塔吉克斯坦', 42, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134600, 9130000, '0,9130000' , '土库曼斯坦', 43, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134700, 9130000, '0,9130000' , '乌克兰', 44, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134800, 9130000, '0,9130000' , '乌兹别克斯坦', 45, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9134900, 9130000, '0,9130000' , '南斯拉夫联盟共和国', 46, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135000, 9130000, '0,9130000' , '斯洛文尼亚共和国', 47, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135100, 9130000, '0,9130000' , '克罗地亚共和国', 48, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135200, 9130000, '0,9130000' , '捷克共和国', 49, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135300, 9130000, '0,9130000' , '斯洛伐克共和国', 50, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135400, 9130000, '0,9130000' , '前南斯拉夫马其顿共和国', 51, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135500, 9130000, '0,9130000' , '波斯尼亚-黑塞哥维那共和国', 52, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9135600, 9130000, '0,9130000' , '塞尔维亚', 53, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9139900, 9130000, '0,9130000' , '欧洲其他', 54, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140000, 0, '0' , '南美洲', 41, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140100, 9140000, '0,9140000' , '安提瓜和巴布达', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140200, 9140000, '0,9140000' , '阿根廷', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140300, 9140000, '0,9140000' , '阿鲁巴岛', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140400, 9140000, '0,9140000' , '巴哈马', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140500, 9140000, '0,9140000' , '巴巴多斯', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140600, 9140000, '0,9140000' , '伯利兹', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140800, 9140000, '0,9140000' , '玻利维亚', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9140900, 9140000, '0,9140000' , '博内尔', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141000, 9140000, '0,9140000' , '巴西', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141100, 9140000, '0,9140000' , '开曼群岛', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141200, 9140000, '0,9140000' , '智利', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141300, 9140000, '0,9140000' , '哥伦比亚', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141400, 9140000, '0,9140000' , '多米尼克', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141500, 9140000, '0,9140000' , '哥斯达黎加', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141600, 9140000, '0,9140000' , '古巴', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141700, 9140000, '0,9140000' , '库腊岛', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141800, 9140000, '0,9140000' , '多米尼加共和国', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9141900, 9140000, '0,9140000' , '厄瓜多尔', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142000, 9140000, '0,9140000' , '法属圭亚那', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142100, 9140000, '0,9140000' , '格林纳达', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142200, 9140000, '0,9140000' , '瓜德罗普', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142300, 9140000, '0,9140000' , '危地马拉', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142400, 9140000, '0,9140000' , '圭亚那', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142500, 9140000, '0,9140000' , '海地', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142600, 9140000, '0,9140000' , '洪都拉斯', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142700, 9140000, '0,9140000' , '牙买加', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142800, 9140000, '0,9140000' , '马提尼克', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9142900, 9140000, '0,9140000' , '墨西哥', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143000, 9140000, '0,9140000' , '蒙特塞拉特', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143100, 9140000, '0,9140000' , '尼加拉瓜', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143200, 9140000, '0,9140000' , '巴拿马', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143300, 9140000, '0,9140000' , '巴拉圭', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143400, 9140000, '0,9140000' , '秘鲁', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143500, 9140000, '0,9140000' , '波多黎各', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143600, 9140000, '0,9140000' , '萨巴', 36, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143700, 9140000, '0,9140000' , '圣卢西亚', 37, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143800, 9140000, '0,9140000' , '圣马丁岛', 38, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9143900, 9140000, '0,9140000' , '圣文森特和格林纳丁斯', 39, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144000, 9140000, '0,9140000' , '萨尔瓦多', 40, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144100, 9140000, '0,9140000' , '苏里南', 41, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144200, 9140000, '0,9140000' , '特立尼达和多巴哥', 42, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144300, 9140000, '0,9140000' , '特克斯和凯科斯群岛', 43, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144400, 9140000, '0,9140000' , '乌拉圭', 44, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144500, 9140000, '0,9140000' , '委内瑞拉', 45, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144600, 9140000, '0,9140000' , '英属维尔京群岛', 46, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9144700, 9140000, '0,9140000' , '圣其茨--尼维斯', 47, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9149900, 9140000, '0,9140000' , '拉丁美洲其他', 48, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150000, 0, '0' , '北美洲', 42, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150100, 9150000, '0,9150000' , '加拿大', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150200, 9150000, '0,9150000' , '美国', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150300, 9150000, '0,9150000' , '美国', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150400, 9150000, '0,9150000' , '百慕大', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9150500, 9150000, '0,9150000' , '格陵兰', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9159800, 9150000, '0,9150000' , '美国（借用91598的编码头来表达美国其他城市）', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9159900, 9150000, '0,9150000' , '北美洲其他', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160000, 0, '0' , '大洋州', 43, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160100, 9160000, '0,9160000' , '澳大利亚', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160200, 9160000, '0,9160000' , '库克群岛', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160300, 9160000, '0,9160000' , '斐济', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160400, 9160000, '0,9160000' , '盖比群岛', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160500, 9160000, '0,9160000' , '马克萨斯群岛', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160600, 9160000, '0,9160000' , '瑙鲁', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160700, 9160000, '0,9160000' , '新喀里多尼亚', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160800, 9160000, '0,9160000' , '瓦努阿图', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9160900, 9160000, '0,9160000' , '新西兰', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161000, 9160000, '0,9160000' , '诺福克岛', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161100, 9160000, '0,9160000' , '巴布亚新几内亚', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161200, 9160000, '0,9160000' , '社会群岛', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161300, 9160000, '0,9160000' , '所罗门群岛', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161400, 9160000, '0,9160000' , '汤加', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161500, 9160000, '0,9160000' , '土阿莫土群岛', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161600, 9160000, '0,9160000' , '土布艾群岛', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161700, 9160000, '0,9160000' , '萨摩亚', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161800, 9160000, '0,9160000' , '基里巴斯', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9161900, 9160000, '0,9160000' , '图瓦卢', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9162000, 9160000, '0,9160000' , '密克罗尼西亚联邦', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9162100, 9160000, '0,9160000' , '马绍尔群岛共和国', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9162200, 9160000, '0,9160000' , '贝劳共和国', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9169900, 9160000, '0,9160000' , '大洋洲其他', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9170000, 0, '0' , '其他', 44, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9170100, 9170000, '0,9170000' , '国（地）别不详的', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9170200, 9170100, '0,9170100' , '联合国和其他国际组织', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(9180000, 0, '0' , '南极洲', 45, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8130230, 9170200, '0,9170200,9170100' , '唐海县', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8310103, 9170200, '0,9170200,9170100' , '卢湾区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320502, 9170200, '0,9170200,9170100' , '沧浪区', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320503, 9170200, '0,9170200,9170100' , '平江区', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8320504, 9170200, '0,9170200,9170100' , '金阊区', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440399, 9170200, '0,9170200,9170100' , '光明区', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440497, 9170200, '0,9170200,9170100' , '拱北区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440498, 9170200, '0,9170200,9170100' , '前山区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8440499, 9170200, '0,9170200,9170100' , '横琴新区', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341400, 9180000, '0,9180000' , '巢湖市', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341401, 8341400, '0,8341400,9180000' , '市辖区', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341402, 8341400, '0,8341400,9180000' , '居巢区', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341421, 8341400, '0,8341400,9180000' , '庐江县', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341422, 8341400, '0,8341400,9180000' , '无为县', 5, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341423, 8341400, '0,8341400,9180000' , '含山县', 6, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8341424, 8341400, '0,8341400,9180000' , '和县', 7, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441299, 8341400, '0,8341400,9180000' , '高新区', 8, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441399, 8341400, '0,8341400,9180000' , '仲恺高新区', 9, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441398, 8341400, '0,8341400,9180000' , '大亚湾', 10, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441799, 8341400, '0,8341400,9180000' , '高新区', 11, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441901, 8341400, '0,8341400,9180000' , '市辖区', 12, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441990, 8341400, '0,8341400,9180000' , '长安镇', 13, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441991, 8341400, '0,8341400,9180000' , '虎门镇', 14, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441992, 8341400, '0,8341400,9180000' , '石龙镇', 15, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441993, 8341400, '0,8341400,9180000' , '常平镇', 16, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441994, 8341400, '0,8341400,9180000' , '清溪镇', 17, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441995, 8341400, '0,8341400,9180000' , '寮步镇', 18, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441996, 8341400, '0,8341400,9180000' , '塘厦镇', 19, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441997, 8341400, '0,8341400,9180000' , '厚街镇', 20, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441998, 8341400, '0,8341400,9180000' , '大朗镇', 21, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8441999, 8341400, '0,8341400,9180000' , '中堂镇', 22, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442001, 8341400, '0,8341400,9180000' , '市辖区', 23, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442093, 8341400, '0,8341400,9180000' , '小榄区', 24, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442094, 8341400, '0,8341400,9180000' , '火炬开发区', 25, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442095, 8341400, '0,8341400,9180000' , '东凤区', 26, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442096, 8341400, '0,8341400,9180000' , '古镇区', 27, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442097, 8341400, '0,8341400,9180000' , '沙溪区', 28, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442098, 8341400, '0,8341400,9180000' , '三乡区', 29, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8442099, 8341400, '0,8341400,9180000' , '坦洲区', 30, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460105, 8341400, '0,8341400,9180000' , '秀英区', 31, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460106, 8341400, '0,8341400,9180000' , '龙华区', 32, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460107, 8341400, '0,8341400,9180000' , '琼山区', 33, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460108, 8341400, '0,8341400,9180000' , '美兰区', 34, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8460201, 8341400, '0,8341400,9180000' , '市辖区', 35, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469000, 9180000, '0,9180000' , '省直辖县级行政区划', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469031, 8469000, '0,8469000,9180000' , '西沙群岛', 2, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469032, 8469000, '0,8469000,9180000' , '南沙群岛', 3, '', '', '', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(8469033, 8469000, '0,8469000,9180000' , '中沙群岛的岛礁及其海域', 4, '', '', '', '0', '0', 'admin', sysdate(), '', null);

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  user_name         varchar(30)     not null                   comment '用户账号',
  nick_name         varchar(30)     not null                   comment '用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  email             varchar(50)     default ''                 comment '用户邮箱',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像地址',
  password          varchar(100)    default ''                 comment '密码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(128)    default ''                 comment '最后登录IP',
  login_date        datetime                                   comment '最后登录时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1,  103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '管理员');
insert into sys_user values(2,  105, 'ry',    '若依', '00', 'ry@qq.com',  '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '测试员');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment '岗位ID',
  post_code     varchar(64)     not null                   comment '岗位编码',
  post_name     varchar(50)     not null                   comment '岗位名称',
  post_sort     int(4)          not null                   comment '显示顺序',
  status        char(1)         not null                   comment '状态（0正常 1停用）',
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   datetime                                   comment '创建时间',
  update_by     varchar(64)     default ''			       comment '更新者',
  update_time   datetime                                   comment '更新时间',
  remark        varchar(500)    default null               comment '备注',
  primary key (post_id)
) engine=innodb comment = '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'ceo',  '董事长',    1, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(2, 'se',   '项目经理',  2, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(3, 'hr',   '人力资源',  3, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(4, 'user', '普通员工',  4, '0', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id              bigint(20)      not null auto_increment    comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  role_key             varchar(100)    not null                   comment '角色权限字符串',
  role_sort            int(4)          not null                   comment '显示顺序',
  data_scope           char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  menu_check_strictly  tinyint(1)      default 1                  comment '菜单树选择项是否关联显示',
  dept_check_strictly  tinyint(1)      default 1                  comment '部门树选择项是否关联显示',
  status               char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '系统管理员',  'admin',  1, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, '系统管理员');
insert into sys_role values('2', '省市管理员', 'province_admin', 2, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '省市管理员');
insert into sys_role values('3', '普通角色',    'common', 3, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  query             varchar(255)    default null               comment '路由参数',
  route_name        varchar(50)     default ''                 comment '路由名称',
  is_frame          int(1)          default 1                  comment '是否为外链（0是 1否）',
  is_cache          int(1)          default 0                  comment '是否缓存（0缓存 1不缓存）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', '系统管理', '0', '1', 'system',           null, '', '', 1, 0, 'M', '0', '0', '', 'system',   'admin', sysdate(), '', null, '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', 'monitor',          null, '', '', 1, 0, 'M', '0', '0', '', 'monitor',  'admin', sysdate(), '', null, '系统监控目录');
insert into sys_menu values('3', '系统工具', '0', '3', 'tool',             null, '', '', 1, 0, 'M', '0', '0', '', 'tool',     'admin', sysdate(), '', null, '系统工具目录');
insert into sys_menu values('4', '若依官网', '0', '4', 'http://ruoyi.vip', null, '', '', 0, 0, 'M', '0', '0', '', 'guide',    'admin', sysdate(), '', null, '若依官网地址');
-- 二级菜单
insert into sys_menu values('100',  '用户管理', '1',   '1', 'user',       'system/user/index',        '', '', 1, 0, 'C', '0', '0', 'system:user:list',        'user',          'admin', sysdate(), '', null, '用户管理菜单');
insert into sys_menu values('101',  '角色管理', '1',   '2', 'role',       'system/role/index',        '', '', 1, 0, 'C', '0', '0', 'system:role:list',        'peoples',       'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu values('102',  '菜单管理', '1',   '3', 'menu',       'system/menu/index',        '', '', 1, 0, 'C', '0', '0', 'system:menu:list',        'tree-table',    'admin', sysdate(), '', null, '菜单管理菜单');
insert into sys_menu values('103',  '部门管理', '1',   '4', 'dept',       'system/dept/index',        '', '', 1, 0, 'C', '0', '0', 'system:dept:list',        'tree',          'admin', sysdate(), '', null, '部门管理菜单');
insert into sys_menu values('104',  '岗位管理', '1',   '5', 'post',       'system/post/index',        '', '', 1, 0, 'C', '0', '0', 'system:post:list',        'post',          'admin', sysdate(), '', null, '岗位管理菜单');
insert into sys_menu values('105',  '字典管理', '1',   '6', 'dict',       'system/dict/index',        '', '', 1, 0, 'C', '0', '0', 'system:dict:list',        'dict',          'admin', sysdate(), '', null, '字典管理菜单');
insert into sys_menu values('106',  '参数设置', '1',   '7', 'config',     'system/config/index',      '', '', 1, 0, 'C', '0', '0', 'system:config:list',      'edit',          'admin', sysdate(), '', null, '参数设置菜单');
insert into sys_menu values('107',  '通知公告', '1',   '8', 'notice',     'system/notice/index',      '', '', 1, 0, 'C', '0', '0', 'system:notice:list',      'message',       'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu values('108',  '日志管理', '1',   '9', 'log',        '',                         '', '', 1, 0, 'M', '0', '0', '',                        'log',           'admin', sysdate(), '', null, '日志管理菜单');
insert into sys_menu values('109',  '在线用户', '2',   '1', 'online',     'monitor/online/index',     '', '', 1, 0, 'C', '0', '0', 'monitor:online:list',     'online',        'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu values('110',  '定时任务', '2',   '2', 'job',        'monitor/job/index',        '', '', 1, 0, 'C', '0', '0', 'monitor:job:list',        'job',           'admin', sysdate(), '', null, '定时任务菜单');
insert into sys_menu values('111',  '数据监控', '2',   '3', 'druid',      'monitor/druid/index',      '', '', 1, 0, 'C', '0', '0', 'monitor:druid:list',      'druid',         'admin', sysdate(), '', null, '数据监控菜单');
insert into sys_menu values('112',  '服务监控', '2',   '4', 'server',     'monitor/server/index',     '', '', 1, 0, 'C', '0', '0', 'monitor:server:list',     'server',        'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu values('113',  '缓存监控', '2',   '5', 'cache',      'monitor/cache/index',      '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list',      'redis',         'admin', sysdate(), '', null, '缓存监控菜单');
insert into sys_menu values('114',  '缓存列表', '2',   '6', 'cacheList',  'monitor/cache/list',       '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list',      'redis-list',    'admin', sysdate(), '', null, '缓存列表菜单');
insert into sys_menu values('115',  '表单构建', '3',   '1', 'build',      'tool/build/index',         '', '', 1, 0, 'C', '0', '0', 'tool:build:list',         'build',         'admin', sysdate(), '', null, '表单构建菜单');
insert into sys_menu values('116',  '代码生成', '3',   '2', 'gen',        'tool/gen/index',           '', '', 1, 0, 'C', '0', '0', 'tool:gen:list',           'code',          'admin', sysdate(), '', null, '代码生成菜单');
insert into sys_menu values('117',  '系统接口', '3',   '3', 'swagger',    'tool/swagger/index',       '', '', 1, 0, 'C', '0', '0', 'tool:swagger:list',       'swagger',       'admin', sysdate(), '', null, '系统接口菜单');
-- 三级菜单
insert into sys_menu values('500',  '操作日志', '108', '1', 'operlog',    'monitor/operlog/index',    '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list',    'form',          'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu values('501',  '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor',    'admin', sysdate(), '', null, '登录日志菜单');
-- 用户管理按钮
insert into sys_menu values('1000', '用户查询', '100', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1001', '用户新增', '100', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1002', '用户修改', '100', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1003', '用户删除', '100', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1004', '用户导出', '100', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1005', '用户导入', '100', '6',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1006', '重置密码', '100', '7',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd',       '#', 'admin', sysdate(), '', null, '');
-- 角色管理按钮
insert into sys_menu values('1007', '角色查询', '101', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1008', '角色新增', '101', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1009', '角色修改', '101', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1010', '角色删除', '101', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1011', '角色导出', '101', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export',         '#', 'admin', sysdate(), '', null, '');
-- 菜单管理按钮
insert into sys_menu values('1012', '菜单查询', '102', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1013', '菜单新增', '102', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1014', '菜单修改', '102', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1015', '菜单删除', '102', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove',         '#', 'admin', sysdate(), '', null, '');
-- 部门管理按钮
insert into sys_menu values('1016', '部门查询', '103', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1017', '部门新增', '103', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1018', '部门修改', '103', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1019', '部门删除', '103', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove',         '#', 'admin', sysdate(), '', null, '');
-- 岗位管理按钮
insert into sys_menu values('1020', '岗位查询', '104', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1021', '岗位新增', '104', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1022', '岗位修改', '104', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1023', '岗位删除', '104', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1024', '岗位导出', '104', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export',         '#', 'admin', sysdate(), '', null, '');
-- 字典管理按钮
insert into sys_menu values('1025', '字典查询', '105', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1026', '字典新增', '105', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1027', '字典修改', '105', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1028', '字典删除', '105', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1029', '字典导出', '105', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export',         '#', 'admin', sysdate(), '', null, '');
-- 参数设置按钮
insert into sys_menu values('1030', '参数查询', '106', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1031', '参数新增', '106', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1032', '参数修改', '106', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1033', '参数删除', '106', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1034', '参数导出', '106', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export',       '#', 'admin', sysdate(), '', null, '');
-- 通知公告按钮
insert into sys_menu values('1035', '公告查询', '107', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1036', '公告新增', '107', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1037', '公告修改', '107', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1038', '公告删除', '107', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove',       '#', 'admin', sysdate(), '', null, '');
-- 操作日志按钮
insert into sys_menu values('1039', '操作查询', '500', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query',      '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1040', '操作删除', '500', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove',     '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1041', '日志导出', '500', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export',     '#', 'admin', sysdate(), '', null, '');
-- 登录日志按钮
insert into sys_menu values('1042', '登录查询', '501', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1043', '登录删除', '501', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1044', '日志导出', '501', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1045', '账户解锁', '501', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock',  '#', 'admin', sysdate(), '', null, '');
-- 在线用户按钮
insert into sys_menu values('1046', '在线查询', '109', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1047', '批量强退', '109', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1048', '单条强退', '109', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', sysdate(), '', null, '');
-- 定时任务按钮
insert into sys_menu values('1049', '任务查询', '110', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1050', '任务新增', '110', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1051', '任务修改', '110', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1052', '任务删除', '110', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1053', '状态修改', '110', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1054', '任务导出', '110', '6', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export',         '#', 'admin', sysdate(), '', null, '');
-- 代码生成按钮
insert into sys_menu values('1055', '生成查询', '116', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query',             '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1056', '生成修改', '116', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit',              '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1057', '生成删除', '116', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1058', '导入代码', '116', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1059', '预览代码', '116', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1060', '生成代码', '116', '6', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code',              '#', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '4');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '116');
insert into sys_role_menu values ('2', '117');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(20) not null comment '角色ID',
  dept_id   bigint(20) not null comment '部门ID',
  primary key(role_id, dept_id)
) engine=innodb comment = '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null comment '用户ID',
  post_id   bigint(20) not null comment '岗位ID',
  primary key (user_id, post_id)
) engine=innodb comment = '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(200)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_ip           varchar(128)    default ''                 comment '主机地址',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  cost_time         bigint(20)      default 0                  comment '消耗时间',
  primary key (oper_id),
  key idx_sys_oper_log_bt (business_type),
  key idx_sys_oper_log_s  (status),
  key idx_sys_oper_log_ot (oper_time)
) engine=innodb auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment '字典主键',
  dict_name        varchar(100)    default ''                 comment '字典名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', sysdate(), '', null, '登录状态列表');


-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  css_class        varchar(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', sysdate(), '', null, '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', sysdate(), '', null, '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', sysdate(), '', null, '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', sysdate(), '', null, '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '关闭状态');
insert into sys_dict_data values(18, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '其他操作');
insert into sys_dict_data values(19, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '新增操作');
insert into sys_dict_data values(20, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '修改操作');
insert into sys_dict_data values(21, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '删除操作');
insert into sys_dict_data values(22, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '授权操作');
insert into sys_dict_data values(23, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导出操作');
insert into sys_dict_data values(24, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导入操作');
insert into sys_dict_data values(25, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '强退操作');
insert into sys_dict_data values(26, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '生成操作');
insert into sys_dict_data values(27, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '清空操作');
insert into sys_dict_data values(28, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(29, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';

insert into sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',            'skin-blue',     'Y', 'admin', sysdate(), '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',         '123456',        'Y', 'admin', sysdate(), '', null, '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',           'theme-dark',    'Y', 'admin', sysdate(), '', null, '深色主题theme-dark，浅色主题theme-light' );
insert into sys_config values(4, '账号自助-验证码开关',           'sys.account.captchaEnabled',    'true',          'Y', 'admin', sysdate(), '', null, '是否开启验证码功能（true开启，false关闭）');
insert into sys_config values(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser',      'false',         'Y', 'admin', sysdate(), '', null, '是否开启注册用户功能（true开启，false关闭）');
insert into sys_config values(6, '用户登录-黑名单列表',           'sys.login.blackIPList',         '',              'Y', 'admin', sysdate(), '', null, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  user_name      varchar(50)    default ''                comment '用户账号',
  ipaddr         varchar(128)   default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id),
  key idx_sys_logininfor_s  (status),
  key idx_sys_logininfor_lt (login_time)
) engine=innodb auto_increment=100 comment = '系统访问记录';


-- ----------------------------
-- 15、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  not null                   comment '调用目标字符串',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  misfire_policy      varchar(20)   default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  status              char(1)       default '0'                comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         datetime                                 comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         datetime                                 comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = '定时任务调度表';

insert into sys_job values(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')',  '0/15 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 16、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)    not null                   comment '任务名称',
  job_group           varchar(64)    not null                   comment '任务组名',
  invoke_target       varchar(500)   not null                   comment '调用目标字符串',
  job_message         varchar(500)                              comment '日志信息',
  status              char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  create_time         datetime                                  comment '创建时间',
  primary key (job_log_id)
) engine=innodb comment = '定时任务调度日志表';


-- ----------------------------
-- 17、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_type       char(1)         not null                   comment '公告类型（1通知 2公告）',
  notice_content    longblob        default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) engine=innodb auto_increment=10 comment = '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate(), '', null, '管理员');
insert into sys_notice values('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容',   '0', 'admin', sysdate(), '', null, '管理员');


-- ----------------------------
-- 18、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  sub_table_name    varchar(64)     default null               comment '关联子表的表名',
  sub_table_fk_name varchar(64)     default null               comment '子表关联的外键名',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  tpl_web_type      varchar(30)     default ''                 comment '前端模板类型（element-ui模版 element-plus模版）',
  package_name      varchar(100)                               comment '生成包路径',
  module_name       varchar(30)                                comment '生成模块名',
  business_name     varchar(30)                                comment '生成业务名',
  function_name     varchar(50)                                comment '生成功能名',
  function_author   varchar(50)                                comment '生成功能作者',
  gen_type          char(1)         default '0'                comment '生成代码方式（0zip压缩包 1自定义路径）',
  gen_path          varchar(200)    default '/'                comment '生成路径（不填默认项目路径）',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';


-- ----------------------------
-- 19、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          bigint(20)                                 comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';


-- 创建套餐到期预警菜单
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐到期预警', '2000', '1', 'pack_exp', 'sim/package/index', 1, 0, 'C', '0', '0', 'sim:pack_exp:list', '#', 'admin', sysdate(), '', null, '套餐到期预警菜单');

-- 创建套餐到期预警（港华）菜单
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐到期预警(港华)', '2000', '1', 'pack_exp_gh', 'sim/package_gh/index', 1, 0, 'C', '0', '0', 'sim:pack_exp_gh:list', '#', 'admin', sysdate(), '', null, '套餐到期预警菜单');


-- 创建停机分析菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('停机分析', '2000', '0', 'stop', 'sim/stop/index', 1, 0, 'C', '0', '0', 'sim:stop:list', 'dict', 'admin', sysdate(), '', null, '');

-- 创建停机清单表
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

-- 创建套餐到期预警表
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

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('NB异常清单', '2000', '1', 'nb', 'sim/nb/index', 1, 0, 'C', '0', '0', 'sim:nb:list', 'chart', 'admin', sysdate(), '', null, 'NB异常清单菜单');

-- 建表语句
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


-- 菜单SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('欠费停机预警', '2000', '1', 'arrear', 'sim/arrear/index', 1, 0, 'C', '0', '0', 'sim:arrear:list', 'data', 'admin', sysdate(), '', null, '欠费停机预警菜单');

-- 建表语句
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


--菜单sql
INSERT INTO ruoyi.sys_menu
(menu_id, menu_name, parent_id, order_num, `path`, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES(2005, '断网分析', 2000, 1, 'disconnect', 'sim/disconnect/index', NULL, '', 1, 0, 'C', '0', '0', 'sim:simNetStopList:list', 'dict', 'admin', '2024-11-05 11:24:14', 'admin', '2024-11-06 14:15:27', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('套餐长期不使用预警', '2000', '1', 'nonuse', 'sim/nonuse/index', 1, 0, 'C', '0', '0', 'sim:nonuse:list', '#', 'admin', sysdate(), '', null, '套餐长期不使用预警菜单');

