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
    login_user_id bigint  not null comment '登录账户id',
    remark        nvarchar(500)                          null comment '备注'
)
    comment '门店助教';



