alter table sys_menu
    add menu_category int default 0 not null comment '菜单分类(0=后台,1=收银,2=app)';


create index t_order_desk_time_desk_id_index
    on t_order_desk_time (desk_id);

create index t_order_desk_time_order_id_index
    on t_order_desk_time (order_id);

