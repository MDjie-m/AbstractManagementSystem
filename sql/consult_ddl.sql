CREATE TABLE `psy_consult` (
                               `id` bigint NOT NULL COMMENT '主键',
                               `cat_id` bigint NOT NULL COMMENT '分类',
                               `user_id` bigint NOT NULL COMMENT '登录名',
                               `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录名',
                               `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
                               `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
                               `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
                               `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别',
                               `tabs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
                               `way` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '咨询方向',
                               `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '介绍文案',
                               `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '图文详情',
                               `work_num` int unsigned NOT NULL DEFAULT '0' COMMENT '咨询人数',
                               `work_time` int unsigned NOT NULL DEFAULT '0' COMMENT '服务时长',
                               `work_hours` int unsigned NOT NULL DEFAULT '0' COMMENT '从业时间',
                               `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                               `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                               `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `way_str` varchar(255) DEFAULT NULL COMMENT '咨询方向',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='心理咨询师表';

CREATE TABLE `psy_consult_banner_config` (
                                             `id` bigint NOT NULL COMMENT '主键',
                                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
                                             `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片地址',
                                             `link_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '跳转url',
                                             `type` tinyint NOT NULL COMMENT 'banner分类(0-首页一级banner页)',
                                             `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                             `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                             `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                             `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='咨询banner配置表';

CREATE TABLE `psy_consult_class` (
                                     `id` bigint NOT NULL COMMENT '类型ID',
                                     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
                                     `sort` int DEFAULT NULL COMMENT '排序',
                                     `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标路径',
                                     `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                     `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
                                     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='咨询类型表';

CREATE TABLE `psy_consult_order` (
                                     `id` bigint NOT NULL COMMENT '主键',
                                     `order_no` varchar(80) DEFAULT NULL COMMENT '订单号',
                                     `consult_id` bigint NOT NULL COMMENT '咨询师',
                                     `consult_name` varchar(100) NOT NULL COMMENT '咨询师',
                                     `serve_id` bigint NOT NULL COMMENT '服务',
                                     `serve_name` varchar(100) NOT NULL COMMENT '服务',
                                     `user_id` bigint NOT NULL COMMENT '客户id',
                                     `work_id` bigint NOT NULL COMMENT '排班',
                                     `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '可选班次0全天 1上午 2下午 3晚上',
                                     `time_start` datetime DEFAULT NULL COMMENT '咨询时段开始',
                                     `time_end` datetime DEFAULT NULL COMMENT '咨询时段结束',
                                     `time` tinyint unsigned DEFAULT NULL COMMENT '咨询时长(分钟)',
                                     `num` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '可预约数量',
                                     `buy_num` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '已预约数量',
                                     `amount` decimal(10,2) DEFAULT NULL COMMENT '应付费用',
                                     `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                     `status` char(1) DEFAULT '0' COMMENT '订单状态0-待付款 1-待预约 2-待咨询 3-已完成 4-已取消',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                                     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     `day` varchar(20) DEFAULT NULL COMMENT '天',
                                     `week` varchar(10) DEFAULT NULL COMMENT '周',
                                     `user_name` varchar(100) DEFAULT NULL COMMENT '客户',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='咨询订单';

CREATE TABLE `psy_consult_pay` (
                                   `id` bigint NOT NULL COMMENT '主键',
                                   `order_id` bigint NOT NULL COMMENT '订单号',
                                   `amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
                                   `pay_type` int DEFAULT NULL COMMENT '支付方式  默认1-微信  2 支付宝 3  银行卡  4 其他',
                                   `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                   `status` char(1) DEFAULT '0' COMMENT '支付状态(1-待支付,2-已支付，3-取消支付)',
                                   `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                                   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='心理咨询订单支付信息表';

CREATE TABLE `psy_consult_serve` (
                                     `id` bigint NOT NULL COMMENT '主键',
                                     `consult_id` bigint NOT NULL COMMENT '咨询师',
                                     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务名称',
                                     `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '服务介绍',
                                     `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                                     `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                     `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                                     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='咨询服务';

CREATE TABLE `psy_consult_work` (
                                    `id` bigint NOT NULL COMMENT '主键',
                                    `consult_id` bigint NOT NULL COMMENT '咨询师',
                                    `consult_name` varchar(100) NOT NULL COMMENT '咨询师',
                                    `serve_id` bigint NOT NULL COMMENT '服务',
                                    `serve_name` varchar(100) NOT NULL COMMENT '服务名称',
                                    `type` varchar(10) NOT NULL DEFAULT '0' COMMENT '可选班次0全天 1上午 2下午 3晚上',
                                    `time_start` datetime DEFAULT NULL COMMENT '咨询时段',
                                    `time_end` datetime DEFAULT NULL COMMENT '咨询时段',
                                    `time` tinyint unsigned DEFAULT NULL COMMENT '咨询时长(分钟)',
                                    `num` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '可预约数量',
                                    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                                    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
                                    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    `buy_num` tinyint unsigned DEFAULT '0' COMMENT '已预约数量',
                                    `day` varchar(20) DEFAULT NULL COMMENT '天',
                                    `week` varchar(10) DEFAULT NULL COMMENT '周',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='咨询服务';