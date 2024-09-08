create table  t_store
(
    store_id      bigint                                not null comment '门店id'
        primary key,
    store_name    nvarchar(60)         not null comment '门店名称',
    store_address nvarchar(200)        not null comment '门店地址',
    store_img     nvarchar(200)                          not null comment '门店图片',
    status        int                                   not null comment '门店状态（0正常 1闭店）',
    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',

    remark        varchar(500)                          null comment '备注'
)
    comment '门店';

create table  t_store_user
(
    store_user_id      bigint                                not null comment '员工id'
        primary key,
    real_name    nvarchar(60)         not null comment '姓名',
    mobile  nvarchar(30)        not null comment '手机号',
    user_img     nvarchar(200)                          not null comment '头像',
    sex char(1)   null comment '性别（0=男，1=女，2=未知）',
    status        int                                   not null comment '门店状态（0正常 1停用）',
    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    store_id BIGINT not null  comment '门店',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    login_user_id bigint  not null comment '登录账户id',
    remark        nvarchar(500)                          null comment '备注'
)
    comment '门店员工';

create table  t_store_tutor
(
    store_tutor_id      bigint                                not null comment '员工id'
        primary key,
    real_name    nvarchar(60)         not null comment '姓名',
    mobile  nvarchar(30)        not null comment '手机号',
    user_img     nvarchar(200)                          not null comment '头像',
    sex char(1)   null comment '性别（0=男，1=女，2=未知）',
    level int not null  comment '助教等级',
    status        int                                   not null comment '门店状态（0正常 1停用）',
    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    store_id BIGINT not null  comment '门店',
    login_user_id bigint  not null comment '登录账户id',
    remark        nvarchar(500)                          null comment '备注'
)
    comment '门店助教';


create table  t_member
(
    member_id      bigint                                not null comment 'ID'
        primary key,
    real_name    nvarchar(60)         not null comment '姓名',
    mobile  nvarchar(30)        not null comment '手机号',
    sex char(1)   null comment '性别（0=男，1=女，2=未知）',
    level int not null  comment '会员等级',
    status        int                                   not null comment '门店状态（0正常 1停用）',
    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    store_id BIGINT not null  comment '门店',
    remark        nvarchar(500)                          null comment '备注'
)
    comment '门店会员';


create table  t_store_desk
(
    desk_id      bigint                                not null comment '球桌编码'
        primary key,
    desk_name    nvarchar(60)         not null comment '球桌名',
    desk_num int not null comment '编号',
    desk_type int not null comment '球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌',
    place_type int not null comment '位置：0=大厅，1=包厢',
    store_id bigint not null  comment '门店',
    light_device_id      bigint                                  null comment '灯光id',
    camera_device_id     bigint                                null comment '摄像头设备id',
    price decimal(10,2) not null  comment '价格/分钟',
    status        int                                   not null comment '状态：0=空闲，1=计时中，2=暂停,3=已停止',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark        nvarchar(500)                          null comment '备注'
)
comment '球桌';
drop table  if exists t_order;
create table  t_order
(
    order_id      bigint                                not null comment '订单id' primary key,
    order_no varchar(60) not null  comment '订单编码',
    order_type int not null  comment '类型：1=球桌费用，2=会员充值，3=商品购买,4=陪练费用，5=教学费用',
    total_amount_due decimal(10,2)  not null comment '应付总金额 ',
    total_discount_amount decimal(10,2) null  comment '折扣金额',
    total_amount decimal(10,2) null  comment '实际支付金额',
    pay_type int null  comment '支付方式：0=扫码，1=现金，2=会员',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单';

drop table  if exists t_order_goods;
create table  t_order_goods
(
    order_detail_id      bigint                                not null comment 'id' primary key,
    order_id bigint not null  comment '订单id',
    desk_id bigint null comment '球桌id',
    goods_id bigint not null  comment '商品id',
    goods_name bigint not null  comment '商品名称',
    price decimal(10,2) not null  comment  '单价',
    num  int  not null  comment  '数量',
    total_amount decimal(10,2)  not null comment '实际付款总金额 ',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '购买商品';
drop table  if exists t_order_member_deduct;
create table  t_order_member_deduct
(
     order_member_deduct_id      bigint                                not null comment 'id' primary key,
     order_id bigint not null  comment '订单id',
     member_id bigint not null  comment '会员id',
     total_amount decimal(10,2)  not null comment '实际付款总金额',
     create_by     varchar(64) default ''                null comment '创建者',
     create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
     update_by     varchar(64) default ''                null comment '更新者',
     update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '会员付款记录';
drop table  if exists t_order_recharge;
create table  t_order_recharge
(
    order_recharge_id      bigint                                not null comment 'id' primary key,
    order_id bigint not null  comment '订单id',
    member_id bigint not null  comment '会员id',
    recharge_amount decimal(10,2) not null comment  '充值金额',
    total_amount decimal(10,2)  not null comment '支付金额',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '会员充值';

create table  t_order_desk_time
(
    order_desk_time_id      bigint                                not null comment '球桌编码'
        primary key,
    order_id             bigint                          not null comment '订单编号' ,
    desk_id      bigint                                not null comment '球桌编码',
    from_desk_id bigint null comment '转桌之前的Id',
    start_time        datetime                                  not null comment '开始时间',
    end_time        datetime                                   not null comment '结束时间',
    total_time int  not null comment '总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算）',
    price decimal(10,2)  not null comment '价格/分钟',
    total_amount decimal(10,2)  not null comment '球桌总费用',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '订单计时';
drop table  if exists t_order_tutor_time;
create table  t_order_tutor_time
(
    order_tutor_time_id      bigint                                not null comment '球桌编码'
        primary key,
    order_id             bigint                          not null comment '订单编号' ,
    desk_id      bigint                                not null comment '球桌编码',
    start_time        datetime                                  not null comment '开始时间',
    end_time        datetime                                   not null comment '结束时间',
    type int not null  comment '类型：4=陪练,5=教学',
    total_time int  not null comment '总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算）',
    price decimal(10,2)  not null comment '价格/分钟',
    total_amount decimal(10,2)  not null comment '助教总费用',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '订单教练计时';




create table  t_stock
(
    stock_id      bigint                                not null comment '商品编码'
        primary key,
    goods_id             bigint                          not null comment '门店Id' ,
    goods_name      nvarchar(64)                                not null comment '商品名称',
    total      bigint                                null comment '商品条码',
    last_check_point_id      bigint                                null comment '最近一次盘点日志ID',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '库存';

create table  t_stock_log
(
    stock_log_id      bigint                                not null comment '商品编码'
        primary key,
    stock_id             bigint                          not null comment '库存id' ,
    change_count      bigint                                not null comment '变化数量(有+-符号)',
    change_type      int                                 null comment '变化类型：0=入库，1=出库，2=盘点',
    before_count  bigint not null comment '变化前数量',
    current_count  bigint not null comment '变化前后当前数量',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '库存日志';
DROP TABLE IF EXISTS t_device;
create table  t_device
(
    device_id      bigint                                not null comment '商品编码'
        primary key,
    store_id             bigint                          not null comment '门店' ,
    device_name        varchar(64)     charset utf8mb4                             not null comment '设备名称',
    device_serial_num        varchar(64)     charset utf8mb4                                    null comment '设备编码',
    device_type        int                                 not null comment '设备类型：0=摄像头,1=灯光',
    extend_data   json  null comment 'json扩展配置',
    status      int                                 null comment '变化类型：0=未知，1=在线，2=掉线',

    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
    comment '设备信息';
create unique index t_store_desk__uindex_num
    on t_store_desk (store_id, desk_num);
DROP TABLE IF EXISTS t_goods_category;
create table  t_goods_category
(
    goods_category_id      bigint                                not null comment 'ID'
        primary key,
    goods_category_name       varchar(64)     charset utf8mb4                             not null comment '商品分类名称',
    store_id bigint not null  comment '门店',
    sort       int                          not null comment '排序',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark        nvarchar(500)                          null comment '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
    comment '商品分类';
drop table  if exists t_goods;
create table  t_goods
(
    goods_id      bigint                                not null comment '商品编码'
        primary key,
    store_id             bigint                          not null comment '门店Id' ,
    goods_name      nvarchar(64)                                not null comment '商品名称',
    goods_img nvarchar(500) not null comment '商品图片',
    sort       int                          not null comment '排序',
    barcode      nvarchar(64)                                  null comment '商品条码',
    category_id       bigint not null                                  null comment '商品分类',
    sell tinyint not null  comment  '是否上架销售',
    price decimal(10,2)  not null comment '价格',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '商品';

create table  t_desk_device_relation
(
    desk_device_relation_id      bigint                 auto_increment               not null comment 'ID'
        primary key,
    desk_id bigint not null  comment '桌子id',
    device_id bigint not null  comment '设备id',

    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark        nvarchar(500)                          null comment '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
    comment '桌子设备关联关系';