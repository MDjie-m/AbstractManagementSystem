drop table if exists t_store;
create table t_store
(
    store_id      bigint auto_increment                 not null comment '门店id'
        primary key,
    store_name    nvarchar(60)                          not null comment '门店名称',
    store_address nvarchar(200)                         not null comment '门店地址',
    store_img     nvarchar(200)                         not null comment '门店图片',
    status        int                                   not null comment '门店状态（0正常 1闭店）',

    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id  bigint                                null comment '创建者Id',
    update_by_id  bigint                                null comment '更新者Id',
    remark        varchar(500)                          null comment '备注'
)
    comment '门店';
drop table if exists t_store_user;
create table t_store_user
(
    store_user_id bigint                                not null comment '员工id'
        primary key,
    real_name     nvarchar(60)                          not null comment '姓名',
    mobile        nvarchar(30)                          not null comment '手机号',
    user_img      nvarchar(200)                         not null comment '头像',
    sex           char(1)                               null comment '性别（0=男，1=女，2=未知）',
    status        int                                   not null comment '门店状态（0正常 1停用）',
    del_flag      char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    store_id      BIGINT                                not null comment '门店',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    login_user_id bigint                                not null comment '登录账户id',
    create_by_id  bigint                                null comment '创建者Id',
    update_by_id  bigint                                null comment '更新者Id',
    remark        nvarchar(500)                         null comment '备注'
)
    comment '门店员工';
drop table if exists t_store_tutor;
create table t_store_tutor
(
    store_tutor_id   bigint                                not null comment '员工id'
        primary key,
    real_name        nvarchar(60)                          not null comment '姓名',
    mobile           nvarchar(30)                          not null comment '手机号',
    user_img         nvarchar(200)                         not null comment '头像',
    sex              char(1)                               null comment '性别（0=男，1=女，2=未知）',
    current_order_id bigint                                null comment '当前关联的订单id',
    level            int                                   not null comment '等级(1=助教，2=教练，3=总教)',
    status           int                                   not null comment '教练（0=在岗 1=离职）',
    work_status      int         default 0                 not null comment '工作状态（0=空闲，1=计费中，3=已停止）',
    del_flag         char        default '0'               null comment '删除标志（0代表存在 2代表删除）',
    create_by        varchar(64) default ''                null comment '创建者',
    create_time      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by        varchar(64) default ''                null comment '更新者',
    update_time      timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    store_id         BIGINT                                not null comment '门店',
    login_user_id    bigint                                not null comment '登录账户id',
    create_by_id     bigint                                null comment '创建者Id',
    update_by_id     bigint                                null comment '更新者Id',
    aptitude         nvarchar(500)                         null comment '资质',
    remark           nvarchar(500)                         null comment '备注'
)
    comment '门店助教';

drop table if exists t_member;
create table t_member
(
    member_id         bigint                                   not null comment 'ID'
        primary key,
    real_name         nvarchar(60)                             not null comment '姓名',
    mobile            nvarchar(30)                             not null comment '手机号',
    current_amount    DECIMAL(20, 2) default 0                 not null default 0 comment '当前金额',
    total_amount      DECIMAL(20, 2) default 0                 not null default 0 comment '历史总金额',
    give_amount       decimal(20, 2) default 0                 not null comment '充值赠送金额',
    total_give_amount decimal(20, 2) default 0                 null comment '历史充值赠送总金额',
    pay_password      varchar(200)                             null comment '支付密码',
    sex               char(1)                                  null comment '性别（0=男，1=女，2=未知）',
    store_id          BIGINT                                   not null comment '门店',
    level_id          BIGINT                                   not null comment '会员等级',
    status            int                                      not null comment '门店状态（0正常 1停用）',
    del_flag          char           default '0'               null comment '删
除标志（0代表存在 2代表删除）',
    create_by         varchar(64)    default ''                null comment '创建者',
    create_time       timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by         varchar(64)    default ''                null comment '更新者',
    update_time       timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id      bigint                                   null comment '创建者Id',
    update_by_id      bigint                                   null comment '更新者Id',
    remark            nvarchar(500)                            null comment '备注'
)
    comment '门店会员';
drop table if exists t_member_level;
create table t_member_level
(
    member_level_id bigint                                not null comment 'ID'
        primary key,
    level_name      nvarchar(60)                          not null comment '会员等级',
    discount        decimal(4, 2)                         not null comment '折扣力度 95折就填写95',
    store_id        decimal(20, 2)                        not null comment '门店',
    create_by       varchar(64) default ''                null comment '创建者',
    create_time     timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by       varchar(64) default ''                null comment '更新者',
    update_time     timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id    bigint                                null comment '创建者Id',
    update_by_id    bigint                                null comment '更新者Id',
    remark          nvarchar(500)                         null comment '备注'
)
    comment '门店会员等级';
drop table if exists t_level_discount_permission;
create table t_level_discount_permission
(
    level_discount_permission_id bigint                                not null comment 'ID'
        primary key,
    member_level_id              bigint                                not null comment '等级id',
    value                        int                                   not null comment '打折类型：{1=球桌费用， 3=商品购买,4=陪练费用，5=教学费用} ',
    discount                     decimal(4, 2)                         not null comment '折扣力度',
    create_by                    varchar(64) default ''                null comment '创建者',
    create_time                  timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by                    varchar(64) default ''                null comment '更新者',
    update_time                  timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id                 bigint                                null comment '创建者Id',
    update_by_id                 bigint                                null comment '更新者Id',
    remark                       nvarchar(500)                         null comment '备注'
)
    comment '等级折扣范围';

drop table if exists t_store_desk;
create table t_store_desk
(
    desk_id          bigint                                not null comment '球桌编码'
        primary key,
    desk_name        nvarchar(60)                          not null comment '球桌名',
    desk_num         int                                   not null comment '编号',
    desk_type        bigint                                   not null comment '球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌',
    place_type       bigint                                   not null comment '位置：0=大厅，1=包厢',
    store_id         bigint                                not null comment '门店',
    light_device_id  bigint                                null comment '灯光id',
    camera_device_id bigint                                null comment '摄像头设备id',
    status           int                                   not null comment '状态：0=空闲，1=计时中， ,3=已停止',
    enable           tinyint     default 1                 not null comment '是否启用（1=启用，0=禁用，禁用后收银端不会显示）',
    current_order_id bigint                                null comment '当前关联的订单id',
    create_by        varchar(64) default ''                null comment '创建者',
    create_time      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by        varchar(64) default ''                null comment '更新者',
    update_time      timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id     bigint                                null comment '创建者Id',
    update_by_id     bigint                                null comment '更新者Id',
    remark           nvarchar(500)                         null comment '备注'
)
    comment '球桌';

drop table if exists t_order;
create table t_order
(
    order_id              bigint                                   not null comment '订单id' primary key,
    order_no              varchar(60)                              not null comment '订单编码',

    pre_pay_amount        decimal(20, 2) default 0.00              not null default 0.00 not null comment '预付费',
    refund_amount         DECIMAL(20, 2) default 0.00              not null comment '退款金额',
    supplementary_amount  decimal(20, 2) default 0.00              not null comment '补充缴费金额',
    order_type            int                                      not null comment '类型：1=球桌费用，2=会员充值，3=商品购买,4=陪练费用，5=教学费用',
    total_amount_due      decimal(20, 2)                           null comment '应付总金额 ',
    total_discount_amount decimal(20, 2)                           null comment '折扣金额',
    total_amount          decimal(20, 2)                           null comment '实际支付金额',
    discount_value        decimal(4, 2)                            null comment '当前折扣',
    total_wipe_zero       decimal(4, 2)                            null comment '抹零金额',
    pay_time              datetime                                 null comment '支付时间',
    store_id              bigint                                   not null comment '门店',
    pay_type              int                                      null comment '支付方式：0=扫码，1=现金，2=会员',
    status                int                                      not null comment '订单状态：0=计费中,1=待结算,2=已结算，3=作废',
    member_id             bigint                                   null comment '支付会员id',
    remark                nvarchar(500)                            null comment '备注',
    create_by             varchar(64)    default ''                null comment '创建者',
    create_time           timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64)    default ''                null comment '更新者',
    create_by_id          bigint                                   null comment '创建者Id',
    update_by_id          bigint                                   null comment '更新者Id',
    update_time           timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单';

drop table if exists t_order_pay;
create table t_order_pay
(
    order_pre_pay_id bigint                                   not null comment '预支付id' primary key,
    order_id         bigint                                   not null comment '订单 ',
    store_id         bigint                                   not null comment '门店',
    amount           decimal(20, 2) default 0.00              not null default 0.00 not null comment '预付费',
    pay_type         int                                      null comment '支付方式：0=扫码，1=现金',
    pre_pay          tinyint                                  not null comment '是否是预付',
    paid             tinyint                                  not null comment '是否支付',
    pay_time         datetime                                 null comment '付款时间',
    remark           nvarchar(500)                            null comment '备注',
    create_by        varchar(64)    default ''                null comment '创建者',
    create_time      timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by        varchar(64)    default ''                null comment '更新者',
    create_by_id     bigint                                   null comment '创建者Id',
    update_by_id     bigint                                   null comment '更新者Id',
    update_time      timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单预付费';

drop table if exists t_order_refund;
create table t_order_refund
(
    order_refund_id bigint                                   not null comment '预支付id' primary key,
    order_id        bigint                                   not null comment '订单 ',
    store_id        bigint                                   not null comment '门店',
    amount          decimal(20, 2) default 0.00              not null default 0.00 not null comment '金额',
    return_pay_type int                                      null comment '支付方式：0=扫码，1=现金',
    paid            tinyint                                  null comment '是否支付',
    return_pay_time datetime                                 not null comment '退款时间',
    remark          nvarchar(500)                            null comment '备注',
    create_by       varchar(64)    default ''                null comment '创建者',
    create_time     timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by       varchar(64)    default ''                null comment '更新者',
    create_by_id    bigint                                   null comment '创建者Id',
    update_by_id    bigint                                   null comment '更新者Id',
    update_time     timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单退款';

drop table if exists t_order_relation;
create table t_order_relation
(
    order_relation_id bigint                                not null comment '订单id' primary key,
    main_order_id     bigint                                not null comment '主单id',
    sub_order_id      bigint                                not null comment '从单id',
    remark            nvarchar(500)                         null comment '备注',
    create_by         varchar(64) default ''                null comment '创建者',
    create_time       timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by         varchar(64) default ''                null comment '更新者',
    create_by_id      bigint                                null comment '创建者Id',
    update_by_id      bigint                                null comment '更新者Id',
    update_time       timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '订单关系表';


drop table if exists t_order_goods;
create table t_order_goods
(
    order_detail_id       bigint                                not null comment 'id' primary key,
    order_id              bigint                                not null comment '订单id',
    desk_id               bigint                                null comment '球桌id',
    goods_id              bigint                                not null comment '商品id',
    goods_name            nvarchar(64)                          not null comment '商品名称',
    price                 decimal(10, 2)                        not null comment '单价',
    num                   int                                   not null comment '数量',
    total_amount_due      decimal(20, 2)                        null comment '应付总金额 ',
    total_discount_amount decimal(20, 2)                        null comment '折扣金额',
    total_amount          decimal(20, 2)                        null comment '实际支付金额',
    total_give_amount     decimal(20, 2)                        null comment '实际赠送支付金额',
    discount_value        decimal(4, 2)                         null comment '当前折扣',

    total_wipe_zero       decimal(4, 2)                         null comment '抹零金额',
    remark                nvarchar(500)                         null comment '备注',
    create_by             varchar(64) default ''                null comment '创建者',
    create_time           timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64) default ''                null comment '更新者',
    create_by_id          bigint                                null comment '创建者Id',
    update_by_id          bigint                                null comment '更新者Id',
    update_time           timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '购买商品';
drop table if exists t_order_member_deduct;
create table t_order_member_deduct
(
    order_member_deduct_id bigint                                not null comment 'id' primary key,
    order_id               bigint                                not null comment '订单id',
    member_id              bigint                                not null comment '会员id',
    total_amount           decimal(20, 2)                        null comment '实际支付金额',

    create_by              varchar(64) default ''                null comment '创建者',
    create_time            timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by              varchar(64) default ''                null comment '更新者',
    update_time            timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id           bigint                                null comment '创建者Id',
    update_by_id           bigint                                null comment '更新者Id'
) comment '会员付款记录';
drop table if exists t_order_recharge;
create table t_order_recharge
(
    order_recharge_id     bigint                                   not null comment 'id' primary key,
    order_id              bigint                                   not null comment '订单id',
    member_id             bigint                                   not null comment '会员id',
    recharge_amount       decimal(10, 2)                           not null comment '充值金额',
    give_amount           decimal(10, 2) default 0                 not null comment '充值赠送金额',
    total_amount          decimal(10, 2)                           not null comment '支付金额',
    total_discount_amount decimal(20, 2)                           null comment '打折金额',
    discount_value        decimal(20, 2)                           not null comment '当时折扣',
    remark                nvarchar(500)                            null comment '备注',
    create_by             varchar(64)    default ''                null comment '创建者',
    create_time           timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64)    default ''                null comment '更新者',
    update_time           timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id          bigint                                   null comment '创建者Id',
    update_by_id          bigint                                   null comment '更新者Id'
) comment '会员充值';
drop table if exists t_order_desk_time;

create table t_order_desk_time
(
    order_desk_time_id    bigint                                not null comment '球桌编码'
        primary key,
    order_id              bigint                                not null comment '订单编号',
    desk_id               bigint                                not null comment '球桌编码',
    from_desk_id          bigint                                null comment '转桌之前的Id',
    start_time            datetime                              not null comment '开始时间',
    end_time              datetime                              null comment '结束时间',
    total_time            int                                   null comment '总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算）',
    price                 decimal(10, 2)                        not null comment '价格/分钟',
    total_amount_due      decimal(20, 2)                        null comment '应付总金额 ',
    total_discount_amount decimal(20, 2)                        null comment '折扣金额',
    total_amount          decimal(20, 2)                        null comment '实际支付金额',
    total_give_amount     decimal(20, 2)                        null comment '实际赠送支付金额',
    discount_value        decimal(4, 2)                         null comment '当前折扣',
    total_wipe_zero       decimal(4, 2)                         null comment '抹零金额',
    create_by             varchar(64) default ''                null comment '创建者',
    create_time           timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64) default ''                null comment '更新者',

    update_time           timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id          bigint                                null comment '创建者Id',
    update_by_id          bigint                                null comment '更新者Id',
    status                int                                   not null default 1 comment '状态:1=计费中，2=暂停，3=已结束',
    remark                nvarchar(500)                         null comment '备注'
)
    comment '订单台费计时';
drop table if exists t_order_tutor_time;
create table t_order_tutor_time
(
    order_tutor_time_id   bigint                                not null comment '球桌编码'
        primary key,
    order_id              bigint                                not null comment '订单编号',
    desk_id               bigint                                not null comment '球桌编码',
    tutor_id              bigint                                not null comment '教练id',
    start_time            datetime                              not null comment '开始时间',
    end_time              datetime                              null comment '结束时间',
    type                  int                                   not null comment '类型：4=陪练,5=教学',
    total_time            int                                   null comment '总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算）',
    price                 decimal(10, 2)                        not null comment '价格/分钟',
    total_amount_due      decimal(20, 2)                        null comment '应付总金额 ',
    total_discount_amount decimal(20, 2)                        null comment '折扣金额',
    total_give_amount     decimal(20, 2)                        null comment '实际赠送支付金额',
    total_amount          decimal(20, 2)                        null comment '实际支付金额',
    discount_value        decimal(4, 2)                         null comment '当前折扣',
    total_wipe_zero       decimal(4, 2)                         null comment '抹零金额',
    create_by             varchar(64) default ''                null comment '创建者',
    create_time           timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64) default ''                null comment '更新者',
    update_time           timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id          bigint                                null comment '创建者Id',
    update_by_id          bigint                                null comment '更新者Id',
    status                int                                   not null default 1 comment '状态:1=计费中，2=暂停，3=已结束',
    remark                nvarchar(500)                         null comment '备注'
)
    comment '订单教练计时';



drop table if exists t_stock;
create table t_stock
(
    stock_id            bigint                                not null comment '商品编码'
        primary key,
    goods_id            bigint                                not null comment '门店Id',
    goods_name          nvarchar(64)                          not null comment '商品名称',
    store_id            bigint                                not null comment '门店',
    total               bigint                                not null default 0 comment '库存',
    total_in            bigint      default 0                 not null comment '入库总数',
    total_out           bigint      default 0                 not null comment '出库总数',
    last_check_point_id bigint                                null comment '最近一次盘点日志ID',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
)
    comment '库存';
create unique index t_stock_goods_id_uindex
    on t_stock (goods_id)
    comment '商品唯一约束';


drop table if exists t_stock_log;
create table t_stock_log
(
    stock_log_id  bigint auto_increment                 not null comment '商品编码'
        primary key,
    stock_id      bigint                                not null comment '库存id',
    change_count  bigint                                not null comment '变化数量(有+-符号)',
    change_type   int                                   null comment '变化类型：0=入库，1=出库，2=盘点',
    order_id      bigint                                null comment '订单id',
    before_count  bigint                                not null comment '变化前数量',
    current_count bigint                                not null comment '变化前后当前数量',
    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id  bigint                                null comment '创建者Id',
    update_by_id  bigint                                null comment '更新者Id',
    remark        nvarchar(500)                         null comment '备注'
)
    comment '库存日志';

DROP TABLE IF EXISTS t_device;
create table t_device
(
    device_id         bigint                                not null comment '商品编码'
        primary key,
    store_id          bigint                                not null comment '门店',
    device_name       varchar(64) charset utf8mb4           not null comment '设备名称',
    device_serial_num varchar(64) charset utf8mb4           null comment '设备编码',
    device_type       int                                   not null comment '设备类型：0=摄像头,1=灯光',
    extend_data       json                                  null comment 'json扩展配置',
    status            int                                   null comment '变化类型：0=未知，1=在线，2=掉线',
    custom_status     int                                   null comment '各种设备自定义状态：灯光（1=开，0=关）',
    last_report_time  datetime                              null comment '最后上报时间',
    create_by         varchar(64) default ''                null comment '创建者',
    create_time       timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by         varchar(64) default ''                null comment '更新者',
    update_time       timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id      bigint                                null comment '创建者Id',
    update_by_id      bigint                                null comment '更新者Id',
    remark            nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '设备信息';
create unique index t_store_desk__uindex_num
    on t_store_desk (store_id, desk_num);

DROP TABLE IF EXISTS t_goods_category;
create table t_goods_category
(
    goods_category_id   bigint                                not null comment 'ID'
        primary key,
    goods_category_name varchar(64) charset utf8mb4           not null comment '商品分类名称',
    store_id            bigint                                not null comment '门店',
    sort                int                                   not null comment '排序',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '商品分类';
drop table if exists t_goods;
create table t_goods
(
    goods_id         bigint                                not null comment '商品编码'
        primary key,
    store_id         bigint                                not null comment '门店Id',
    goods_name       nvarchar(64)                          not null comment '商品名称',
    goods_img        nvarchar(500)                         not null comment '商品图片',
    discount_disable tinyint     default 0                 not null comment '禁止折扣',
    sort             int                                   not null comment '排序',
    barcode          nvarchar(64)                          null comment '商品条码',
    category_id      bigint                                not null null comment '商品分类',
    sell             tinyint                               not null comment '是否上架销售',
    cost             decimal(10, 2)                        not null comment '成本价',
    price            decimal(10, 2)                        not null comment '价格',
    create_by        varchar(64) default ''                null comment '创建者',
    create_time      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by        varchar(64) default ''                null comment '更新者',
    update_time      timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id     bigint                                null comment '创建者Id',
    update_by_id     bigint                                null comment '更新者Id',
    remark           nvarchar(500)                         null comment '备注'
)
    comment '商品';
drop table if exists t_desk_device_relation;
create table t_desk_device_relation
(
    desk_device_relation_id bigint auto_increment                 not null comment 'ID'
        primary key,
    desk_id                 bigint                                not null comment '桌子id',
    device_id               bigint                                not null comment '设备id',

    create_by               varchar(64) default ''                null comment '创建者',
    create_time             timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by               varchar(64) default ''                null comment '更新者',
    update_time             timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id            bigint                                null comment '创建者Id',
    update_by_id            bigint                                null comment '更新者Id',
    remark                  nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '桌子设备关联关系';
-- ------
drop table if exists t_order_desk_score;
create table t_order_desk_score
(
    order_desk_score_id bigint                                not null comment 'ID'
        primary key,
    order_id            bigint                                not null comment '订单id',
    desk_id             bigint                                not null comment '桌子id',
    score_a             int         default 0                 not null comment '比分A',
    score_b             int         default 0                 not null comment '比分B',
    start_time          datetime    default CURRENT_TIMESTAMP not null comment '开始时间',
    end_time            datetime                              null comment '结束时间',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '台球桌比分';
drop table if exists t_tutor_work_plan;
create table t_tutor_work_plan
(
    tutor_work_plan_id bigint                                not null comment 'ID'
        primary key,
    tutor_id           bigint                                not null comment '教练id',
    store_id           bigint                                not null comment '门店',
    day                date                                  not null comment '日期',
    create_by          varchar(64) default ''                null comment '创建者',
    create_time        timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by          varchar(64) default ''                null comment '更新者',
    update_time        timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id       bigint                                null comment '创建者Id',
    update_by_id       bigint                                null comment '更新者Id',
    count              int                                   not null comment '排课数量',


    remark             nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '教练排班计划';

drop table if exists t_tutor_work_plan_detail;
create table t_tutor_work_plan_detail
(
    tutor_work_plan_detail_id bigint                                not null comment 'ID'
        primary key,
    tutor_work_plan_id        bigint                                not null comment '计划id',
    tutor_id                  bigint                                not null comment '教练id',
    store_id                  bigint                                not null comment '门店',
    plan_type                 int                                   not null comment '计划类型：4=陪练,5=教学',
    start_time                datetime                              not null comment '开始时间',
    end_time                  datetime                              not null comment '结束时间',
    create_by                 varchar(64) default ''                null comment '创建者',
    create_time               timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by                 varchar(64) default ''                null comment '更新者',
    update_time               timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id              bigint                                null comment '创建者Id',
    update_by_id              bigint                                null comment '更新者Id',
    remark                    nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '教练排班计划详细';

drop table if exists t_tutor_booking;
create table t_tutor_booking
(
    tutor_booking_id    bigint                                not null comment 'ID'
        primary key,
    store_id            bigint                                not null comment '门店',
    start_time          datetime                              not null comment '开始时间',
    end_time            datetime                              not null comment '结束时间',
    tutor_id            bigint                                not null comment '助教id',
    booking_user_name   nvarchar(64)                          not null comment '预约人姓名',
    booking_user_mobile nvarchar(64)                          not null comment '预约人手机号',
    status              int         default 0                 not null comment '0=有效，1=预约生效,2=过期',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '教练预约';

drop table if exists t_desk_booking;
create table t_desk_booking
(
    desk_booking_id     bigint                                not null comment 'ID'
        primary key,
    start_time          datetime                              not null comment '开始时间',
    end_time            datetime                              not null comment '结束时间',
    store_id            bigint                                not null comment '门店',
    desk_id             bigint                                not null comment '球桌id',
    booking_user_name   nvarchar(64)                          not null comment '预约人姓名',
    booking_user_mobile nvarchar(64)                          not null comment '预约人手机号',
    status              int         default 0                 not null comment '0=有效，1=预约生效,2=过期',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '球桌预约';


drop table if exists t_desk_image;
create table t_desk_image
(
    desk_multimedia_id bigint                                not null comment 'ID'
        primary key,
    store_id           bigint                                not null comment '门店',
    capture_time       datetime                              not null comment '抓拍时间',
    camera_id          bigint                                not null comment '摄像头id',
    desk_id            bigint                                null comment '球桌id',
    order_id           bigint                                not null comment '订单id(不一定有订单)',
    file_path          nvarchar(200)                         not null comment '图片地址',
    create_by          varchar(64) default ''                null comment '创建者',
    create_time        timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by          varchar(64) default ''                null comment '更新者',
    update_time        timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id       bigint                                null comment '创建者Id',
    update_by_id       bigint                                null comment '更新者Id',
    remark             nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '球桌抓拍等';



drop table if exists t_desk_price;
create table t_desk_price
(
    desk_price_id bigint                                not null comment 'ID'
        primary key,
    store_id      bigint                                not null not null comment '抓拍时间',
    desk_type     bigint                                   not null comment '球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌',
    price         decimal(10, 2)                        null comment '价格',

    create_by     varchar(64) default ''                null comment '创建者',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by     varchar(64) default ''                null comment '更新者',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id  bigint                                null comment '创建者Id',
    update_by_id  bigint                                null comment '更新者Id',
    remark        nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '球桌价格';



drop table if exists t_tutor_price;
create table t_tutor_price
(
    tutor_price_id bigint                                not null comment 'ID'
        primary key,
    store_id       bigint                                not null not null comment '抓拍时间',
    level          int                                   not null comment '等级(1=助教，2=教练，3=总教)',
    price          decimal(10, 2)                        null comment '价格',

    create_by      varchar(64) default ''                null comment '创建者',
    create_time    timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by      varchar(64) default ''                null comment '更新者',
    update_time    timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id   bigint                                null comment '创建者Id',
    update_by_id   bigint                                null comment '更新者Id',
    remark         nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '教练价格';

drop table if exists t_light_timer;
create table t_light_timer
(
    light_timer_id bigint auto_increment                 not null comment 'ID'
        primary key,
    store_id       bigint                                not null comment '门店',
    start_time     datetime                              not null comment '开始时间',
    end_time       datetime                              not null comment '结束时间',
    desk_id        bigint                                not null comment '桌子id',
    order_id       bigint                                null comment '订单id',
    light_type     int                                   not null comment '定光定时类型：0=零时灯,1=计费灯',
    enable         tinyint                               not null default 1 comment '是否有效',
    create_by      varchar(64) default ''                null comment '创建者',
    create_time    timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by      varchar(64) default ''                null comment '更新者',
    update_time    timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id   bigint                                null comment '创建者Id',
    update_by_id   bigint                                null comment '更新者Id',
    remark         nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '灯光计时器';
create index t_light_timer_desk_id_index
    on t_light_timer (desk_id);
create index t_light_timer_order_id_index
    on t_light_timer (order_id);
create index t_light_timer_store_id_end_time_index
    on t_light_timer (store_id, end_time);



drop table if exists t_store_schedule;
create table t_store_schedule
(
    store_schedule_id     bigint                                not null comment 'ID'
        primary key,
    store_id              bigint                                not null comment '门店',
    start_time            time                                  not null comment '开始时间',
    end_time              time                                  not null comment '结束时间',
    start_time_offset_day int                                   not null comment '-1,0,+1',
    end_time_offset_day   int                                   null comment '-1,0,+1',
    day                   date                                  not null comment '哪一天',
    default_schedule      tinyint     default 0                 not null comment '是否是默认系统班次',
    create_by             varchar(64) default ''                null comment '创建者',
    create_time           timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by             varchar(64) default ''                null comment '更新者',
    update_time           timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id          bigint                                null comment '创建者Id',
    update_by_id          bigint                                null comment '更新者Id',
    remark                nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '门店班次';

create unique index t_store_schedule_store_id_index
    on t_store_schedule (store_id, day);


drop table if exists t_tutor_punch_in;
create table t_tutor_punch_in
(
    tutor_punch_in_id bigint                                not null comment 'ID'
        primary key,
    store_id          bigint                                not null comment '门店',
    start_time        datetime                              null comment '开始时间',
    end_time          datetime                              null comment '结束时间',
    schedule_day      date                                  not null comment '班次',
    tutor_id          bigint                                not null comment '助教',
    create_by         varchar(64) default ''                null comment '创建者',
    create_time       timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by         varchar(64) default ''                null comment '更新者',
    update_time       timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id      bigint                                null comment '创建者Id',
    update_by_id      bigint                                null comment '更新者Id',
    remark            nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '教练打卡';

create unique index t_tutor_punch_in_store_id_index
    on t_tutor_punch_in (store_id, tutor_id, schedule_day);

drop table if exists t_store_swap_record;
create table t_store_swap_record
(
    swap_record_id           bigint                                   not null comment 'id'
        primary key,
    total                    decimal(20, 2)                           not null comment '总营收',
    cash_total               decimal(20, 2)                           not null comment '现金',
    desk_total               decimal(20, 2)                           not null comment '台桌费用',
    tutor_total              decimal(20, 2)                           not null comment '教练费用',
    goods_total              decimal(20, 2)                           not null comment '教练费用',

    total_wipe_zero          decimal(20, 2)                           not null comment '抹零金额',
    suspend_order_count      bigint                                   not null comment '挂单单数',
    suspend_order_amount     decimal(20, 2)                           not null comment '挂单金额',

    not_settled_order_count  bigint         default 0                 not null comment '待结算订单数量',
    not_settled_order_amount decimal(20, 2) default 0.00              not null comment '待结算订单金额',
    schedule_day             date                                     not null comment '班次',
    store_id                 bigint                                   not null comment '门店',
    create_by                varchar(64)    default ''                null comment '创建者',
    create_time              timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by                varchar(64)    default ''                null comment '更新者',
    update_time              timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id             bigint                                   null comment '创建者Id',
    update_by_id             bigint                                   null comment '更新者Id',
    remark                   nvarchar(500)                            null comment '备注'
)
    comment '交班记录';

create index t_store_swap_record_store_id_index
    on t_store_swap_record (store_id, schedule_day);



drop table if exists t_desk_light;
create table t_desk_light
(
    light_id     bigint                                not null comment 'id'
        primary key,
    open         tinyint                               not null comment '总营收',
    desk_num     int                                   not null comment '台桌编号',
    store_id     bigint                                not null comment '门店',
    create_by    varchar(64) default ''                null comment '创建者',
    create_time  timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by    varchar(64) default ''                null comment '更新者',
    update_time  timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id bigint                                null comment '创建者Id',
    update_by_id bigint                                null comment '更新者Id',
    remark       nvarchar(500)                         null comment '备注'
)
    comment '灯光';
create unique index t_desk_light_store_id_desk_num_uindex
    on t_desk_light (store_id, desk_num);


DROP TABLE IF EXISTS t_desk_place;
create table t_desk_place
(
    desk_place_id   bigint                                not null comment 'ID'
        primary key,
    name varchar(64) charset utf8mb4           not null comment '区域名称',
    store_id            bigint                                not null comment '门店',
    sort                int                                   not null comment '排序',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '台桌区域';


DROP TABLE IF EXISTS t_desk_type;
create table t_desk_type
(
    desk_type_id   bigint                                not null comment 'ID'
        primary key,
    name varchar(64) charset utf8mb4           not null comment '类型名称',
    store_id            bigint                                not null comment '门店',
    sort                int                                   not null comment '排序',
    create_by           varchar(64) default ''                null comment '创建者',
    create_time         timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           varchar(64) default ''                null comment '更新者',
    update_time         timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by_id        bigint                                null comment '创建者Id',
    update_by_id        bigint                                null comment '更新者Id',
    remark              nvarchar(500)                         null comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '台桌类型';