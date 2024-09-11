alter table sys_menu
    add menu_category int default 0 not null comment '菜单分类(0=后台,1=收银,2=app)';