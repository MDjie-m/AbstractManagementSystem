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


create table  t_store_desk
(
    desk_id      bigint                                not null comment '球桌编码'
        primary key,
    desk_name    nvarchar(60)         not null comment '球桌名',
    desk_num int not null comment '编号',
    desk_type int not null comment '球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌',
    place_type int not null comment '位置：0=大厅，1=包厢',
    store_id BIGINT not null  comment '门店',
    price decimal(10,2) not null  comment '价格/分钟',
    status        int                                   not null comment '状态：0=空闲，1=计时中，2=暂停,3=已停止',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark        nvarchar(500)                          null comment '备注'
)
comment '球桌';
create table  t_order
(
    order_id      bigint                                not null comment '订单id' primary key,
    order_no varchar(60) not null  comment '订单编码',
    order_type int not null  comment '类型：1=球桌费用，2=会员充值，3=商品购买,4=陪练费用，5=教学费用',
    total_amount decimal(10,2) null  comment '总费用',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单';
create table  t_order_goods
(
    order_detail_id      bigint                                not null comment 'id' primary key,
    order_id bigint not null  comment '订单id',
    desk_id bigint null comment '球桌id',
    goods_id bigint not null  comment '商品id',
    goods_name bigint not null  comment '商品名称',
    price decimal(10,2) not null  comment  '单价',
    num  int  not null  comment  '数量',
    total_amount_due decimal(10,2)  not null comment '应付总金额(total_discount_amount+total_amount)',
    total_discount_amount decimal(10,2)  not null default 0.00 comment '折扣金额 ',
    total_amount decimal(10,2)  not null comment '实际付款总金额（total_cash_amount+total_member_amount）',
    total_cash_amount decimal(10,2)  not null comment '实际现金付款总金额',
    total_member_amount decimal(10,2)  not null comment '实际会员付款总金额',
    remark        nvarchar(500)                          null comment '备注',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '购买商品';
create table  t_order_member_deduct
(
     order_member_deduct_id      bigint                                not null comment 'id' primary key,
     order_id bigint not null  comment '订单id',
     member_id bigint not null  comment '会员id',
     total_amount_due bigint not null  comment '应付金额',
     discount_amount decimal(10,2)  not null default 0.00 comment '折扣金额',
     total_amount decimal(10,2)  not null comment '实际付款总金额',
     create_by     varchar(64) default ''                null comment '创建者',
     create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
     update_by     varchar(64) default ''                null comment '更新者',
     update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '会员付款记录';
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
    comment '订单计时';

