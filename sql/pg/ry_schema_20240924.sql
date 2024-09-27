-- 在postgres上增加find_in_set方法，以便同时兼容mysql&postgres
CREATE OR REPLACE FUNCTION find_in_set(value text, col_name text)
RETURNS boolean AS $$
BEGIN
  RETURN value = ANY(string_to_array(col_name, ','));
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION find_in_set(value bigint, col_name text)
RETURNS boolean AS $$
BEGIN
  RETURN concat('',value) = ANY(string_to_array(col_name, ','));
END;
$$ LANGUAGE plpgsql;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dept' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_dept CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dept_dept_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_dept_dept_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_dept_dept_id_seq START WITH 200  INCREMENT BY 1;

CREATE TABLE sys_dept (
  dept_id           BIGINT          NOT NULL DEFAULT NEXTVAL('sys_dept_dept_id_seq') PRIMARY KEY, -- 部门id
  parent_id         BIGINT          DEFAULT 0, -- 父部门id
  ancestors         VARCHAR(50)     DEFAULT '', -- 祖级列表
  dept_name         VARCHAR(30)     DEFAULT '', -- 部门名称
  order_num         INT             DEFAULT 0, -- 显示顺序
  leader            VARCHAR(20), -- 负责人
  phone             VARCHAR(11), -- 联系电话
  email             VARCHAR(50), -- 邮箱
  status            CHAR(1)         DEFAULT '0', -- 部门状态（0正常 1停用）
  del_flag          CHAR(1)         DEFAULT '0', -- 删除标志（0代表存在 2代表删除）
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP -- 更新时间
);

COMMENT ON COLUMN sys_dept.dept_id IS '部门id';
COMMENT ON COLUMN sys_dept.parent_id IS '父部门id';
COMMENT ON COLUMN sys_dept.ancestors IS '祖级列表';
COMMENT ON COLUMN sys_dept.dept_name IS '部门名称';
COMMENT ON COLUMN sys_dept.order_num IS '显示顺序';
COMMENT ON COLUMN sys_dept.leader IS '负责人';
COMMENT ON COLUMN sys_dept.phone IS '联系电话';
COMMENT ON COLUMN sys_dept.email IS '邮箱';
COMMENT ON COLUMN sys_dept.status IS '部门状态（0正常 1停用）';
COMMENT ON COLUMN sys_dept.del_flag IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN sys_dept.create_by IS '创建者';
COMMENT ON COLUMN sys_dept.create_time IS '创建时间';
COMMENT ON COLUMN sys_dept.update_by IS '更新者';
COMMENT ON COLUMN sys_dept.update_time IS '更新时间';

COMMENT ON TABLE sys_dept IS '部门表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_user' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_user CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_user_user_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_user_user_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_user_user_id_seq;

CREATE TABLE sys_user (
  user_id           BIGINT          NOT NULL DEFAULT NEXTVAL('sys_user_user_id_seq') PRIMARY KEY, -- 用户ID
  dept_id           BIGINT, -- 部门ID
  user_name         VARCHAR(30)     NOT NULL, -- 用户账号
  nick_name         VARCHAR(30)     NOT NULL, -- 用户昵称
  user_type         VARCHAR(2)      DEFAULT '00', -- 用户类型（00系统用户）
  email             VARCHAR(50)     DEFAULT '', -- 用户邮箱
  phonenumber       VARCHAR(11)     DEFAULT '', -- 手机号码
  sex               CHAR(1)         DEFAULT '0', -- 用户性别（0男 1女 2未知）
  avatar            VARCHAR(100)    DEFAULT '', -- 头像地址
  password          VARCHAR(100)    DEFAULT '', -- 密码
  status            CHAR(1)         DEFAULT '0', -- 帐号状态（0正常 1停用）
  del_flag          CHAR(1)         DEFAULT '0', -- 删除标志（0代表存在 2代表删除）
  login_ip          VARCHAR(128)    DEFAULT '', -- 最后登录IP
  login_date        TIMESTAMP WITH TIME ZONE, -- 最后登录时间
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark            VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_user.user_id IS '用户ID';
COMMENT ON COLUMN sys_user.dept_id IS '部门ID';
COMMENT ON COLUMN sys_user.user_name IS '用户账号';
COMMENT ON COLUMN sys_user.nick_name IS '用户昵称';
COMMENT ON COLUMN sys_user.user_type IS '用户类型（00系统用户）';
COMMENT ON COLUMN sys_user.email IS '用户邮箱';
COMMENT ON COLUMN sys_user.phonenumber IS '手机号码';
COMMENT ON COLUMN sys_user.sex IS '用户性别（0男 1女 2未知）';
COMMENT ON COLUMN sys_user.avatar IS '头像地址';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.status IS '帐号状态（0正常 1停用）';
COMMENT ON COLUMN sys_user.del_flag IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN sys_user.login_ip IS '最后登录IP';
COMMENT ON COLUMN sys_user.login_date IS '最后登录时间';
COMMENT ON COLUMN sys_user.create_by IS '创建者';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';
COMMENT ON COLUMN sys_user.update_by IS '更新者';
COMMENT ON COLUMN sys_user.update_time IS '更新时间';
COMMENT ON COLUMN sys_user.remark IS '备注';

COMMENT ON TABLE sys_user IS '用户信息表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_post' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_post CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_post_post_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_post_post_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_post_post_id_seq;

CREATE TABLE sys_post (
  post_id       BIGINT          NOT NULL DEFAULT NEXTVAL('sys_post_post_id_seq') PRIMARY KEY, -- 岗位ID
  post_code     VARCHAR(64)     NOT NULL, -- 岗位编码
  post_name     VARCHAR(50)     NOT NULL, -- 岗位名称
  post_sort     INT             NOT NULL, -- 显示顺序
  status        CHAR(1)         NOT NULL, -- 状态（0正常 1停用）
  create_by     VARCHAR(64)     DEFAULT '', -- 创建者
  create_time   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by     VARCHAR(64)     DEFAULT '', -- 更新者
  update_time   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark        VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_post.post_id IS '岗位ID';
COMMENT ON COLUMN sys_post.post_code IS '岗位编码';
COMMENT ON COLUMN sys_post.post_name IS '岗位名称';
COMMENT ON COLUMN sys_post.post_sort IS '显示顺序';
COMMENT ON COLUMN sys_post.status IS '状态（0正常 1停用）';
COMMENT ON COLUMN sys_post.create_by IS '创建者';
COMMENT ON COLUMN sys_post.create_time IS '创建时间';
COMMENT ON COLUMN sys_post.update_by IS '更新者';
COMMENT ON COLUMN sys_post.update_time IS '更新时间';
COMMENT ON COLUMN sys_post.remark IS '备注';

COMMENT ON TABLE sys_post IS '岗位信息表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_role' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_role CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_role_role_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_role_role_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_role_role_id_seq;

CREATE TABLE sys_role (
  role_id              BIGINT          NOT NULL DEFAULT NEXTVAL('sys_role_role_id_seq') PRIMARY KEY, -- 角色ID
  role_name            VARCHAR(30)     NOT NULL, -- 角色名称
  role_key             VARCHAR(100)    NOT NULL, -- 角色权限字符串
  role_sort            INT             NOT NULL, -- 显示顺序
  data_scope           CHAR(1)         DEFAULT '1', -- 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
  menu_check_strictly  SMALLINT        DEFAULT 1, -- 菜单树选择项是否关联显示
  dept_check_strictly  SMALLINT        DEFAULT 1, -- 部门树选择项是否关联显示
  status               CHAR(1)         NOT NULL, -- 角色状态（0正常 1停用）
  del_flag             CHAR(1)         DEFAULT '0', -- 删除标志（0代表存在 2代表删除）
  create_by            VARCHAR(64)     DEFAULT '', -- 创建者
  create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by            VARCHAR(64)     DEFAULT '', -- 更新者
  update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark               VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_role.role_id IS '角色ID';
COMMENT ON COLUMN sys_role.role_name IS '角色名称';
COMMENT ON COLUMN sys_role.role_key IS '角色权限字符串';
COMMENT ON COLUMN sys_role.role_sort IS '显示顺序';
COMMENT ON COLUMN sys_role.data_scope IS '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）';
COMMENT ON COLUMN sys_role.menu_check_strictly IS '菜单树选择项是否关联显示';
COMMENT ON COLUMN sys_role.dept_check_strictly IS '部门树选择项是否关联显示';
COMMENT ON COLUMN sys_role.status IS '角色状态（0正常 1停用）';
COMMENT ON COLUMN sys_role.del_flag IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN sys_role.create_by IS '创建者';
COMMENT ON COLUMN sys_role.create_time IS '创建时间';
COMMENT ON COLUMN sys_role.update_by IS '更新者';
COMMENT ON COLUMN sys_role.update_time IS '更新时间';
COMMENT ON COLUMN sys_role.remark IS '备注';

COMMENT ON TABLE sys_role IS '角色信息表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_menu' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_menu CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_menu_menu_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_menu_menu_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_menu_menu_id_seq  START WITH 2000  INCREMENT BY 1;

CREATE TABLE sys_menu (
  menu_id           BIGINT          NOT NULL DEFAULT NEXTVAL('sys_menu_menu_id_seq') PRIMARY KEY, -- 菜单ID
  menu_name         VARCHAR(50)     NOT NULL, -- 菜单名称
  parent_id         BIGINT          DEFAULT 0, -- 父菜单ID
  order_num         INT             DEFAULT 0, -- 显示顺序
  path              VARCHAR(200)    DEFAULT '', -- 路由地址
  component         VARCHAR(255), -- 组件路径
  query             VARCHAR(255), -- 路由参数
  route_name        VARCHAR(50)     DEFAULT '', -- 路由名称
  is_frame          INT             DEFAULT 1, -- 是否为外链（0是 1否）
  is_cache          INT             DEFAULT 0, -- 是否缓存（0缓存 1不缓存）
  menu_type         CHAR(1)         DEFAULT '', -- 菜单类型（M目录 C菜单 F按钮）
  visible           CHAR(1)         DEFAULT '0', -- 菜单状态（0显示 1隐藏）
  status            CHAR(1)         DEFAULT '0', -- 菜单状态（0正常 1停用）
  perms             VARCHAR(100), -- 权限标识
  icon              VARCHAR(100)    DEFAULT '#', -- 菜单图标
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark            VARCHAR(500)    DEFAULT '' -- 备注
);

COMMENT ON COLUMN sys_menu.menu_id IS '菜单ID';
COMMENT ON COLUMN sys_menu.menu_name IS '菜单名称';
COMMENT ON COLUMN sys_menu.parent_id IS '父菜单ID';
COMMENT ON COLUMN sys_menu.order_num IS '显示顺序';
COMMENT ON COLUMN sys_menu.path IS '路由地址';
COMMENT ON COLUMN sys_menu.component IS '组件路径';
COMMENT ON COLUMN sys_menu.query IS '路由参数';
COMMENT ON COLUMN sys_menu.route_name IS '路由名称';
COMMENT ON COLUMN sys_menu.is_frame IS '是否为外链（0是 1否）';
COMMENT ON COLUMN sys_menu.is_cache IS '是否缓存（0缓存 1不缓存）';
COMMENT ON COLUMN sys_menu.menu_type IS '菜单类型（M目录 C菜单 F按钮）';
COMMENT ON COLUMN sys_menu.visible IS '菜单状态（0显示 1隐藏）';
COMMENT ON COLUMN sys_menu.status IS '菜单状态（0正常 1停用）';
COMMENT ON COLUMN sys_menu.perms IS '权限标识';
COMMENT ON COLUMN sys_menu.icon IS '菜单图标';
COMMENT ON COLUMN sys_menu.create_by IS '创建者';
COMMENT ON COLUMN sys_menu.create_time IS '创建时间';
COMMENT ON COLUMN sys_menu.update_by IS '更新者';
COMMENT ON COLUMN sys_menu.update_time IS '更新时间';
COMMENT ON COLUMN sys_menu.remark IS '备注';

COMMENT ON TABLE sys_menu IS '菜单权限表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_user_role' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_user_role CASCADE;
  END IF;
END $$;

CREATE TABLE sys_user_role (
  user_id   BIGINT NOT NULL, -- 用户ID
  role_id   BIGINT NOT NULL, -- 角色ID,
  PRIMARY KEY (user_id, role_id)
);

COMMENT ON COLUMN sys_user_role.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';

COMMENT ON TABLE sys_user_role IS '用户和角色关联表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_role_menu' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_role_menu CASCADE;
  END IF;
END $$;

CREATE TABLE sys_role_menu (
  role_id   BIGINT NOT NULL, -- 角色ID
  menu_id   BIGINT NOT NULL, -- 菜单ID,
  PRIMARY KEY (role_id, menu_id)
);

COMMENT ON COLUMN sys_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_menu.menu_id IS '菜单ID';

COMMENT ON TABLE sys_role_menu IS '角色和菜单关联表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_role_dept' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_role_dept CASCADE;
  END IF;
END $$;

CREATE TABLE sys_role_dept (
  role_id   BIGINT NOT NULL, -- 角色ID
  dept_id   BIGINT NOT NULL, -- 部门ID
  PRIMARY KEY (role_id, dept_id)
);

COMMENT ON COLUMN sys_role_dept.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_dept.dept_id IS '部门ID';

COMMENT ON TABLE sys_role_dept IS '角色和部门关联表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_user_post' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_user_post CASCADE;
  END IF;
END $$;

CREATE TABLE sys_user_post (
  user_id   BIGINT NOT NULL, -- 用户ID
  post_id   BIGINT NOT NULL, -- 岗位ID,
  PRIMARY KEY (user_id, post_id)
);

COMMENT ON COLUMN sys_user_post.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_post.post_id IS '岗位ID';

COMMENT ON TABLE sys_user_post IS '用户与岗位关联表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_oper_log' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_oper_log CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_oper_log_oper_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_oper_log_oper_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_oper_log_oper_id_seq;

CREATE TABLE sys_oper_log (
  oper_id           BIGINT          NOT NULL DEFAULT NEXTVAL('sys_oper_log_oper_id_seq') PRIMARY KEY, -- 日志主键
  title             VARCHAR(50)     DEFAULT '', -- 模块标题
  business_type     INT             DEFAULT 0, -- 业务类型（0其它 1新增 2修改 3删除）
  method            VARCHAR(200)    DEFAULT '', -- 方法名称
  request_method    VARCHAR(10)     DEFAULT '', -- 请求方式
  operator_type     INT             DEFAULT 0, -- 操作类别（0其它 1后台用户 2手机端用户）
  oper_name         VARCHAR(50)     DEFAULT '', -- 操作人员
  dept_name         VARCHAR(50)     DEFAULT '', -- 部门名称
  oper_url          VARCHAR(255)    DEFAULT '', -- 请求URL
  oper_ip           VARCHAR(128)    DEFAULT '', -- 主机地址
  oper_location     VARCHAR(255)    DEFAULT '', -- 操作地点
  oper_param        VARCHAR(2000)   DEFAULT '', -- 请求参数
  json_result       VARCHAR(2000)   DEFAULT '', -- 返回参数
  status            INT             DEFAULT 0, -- 操作状态（0正常 1异常）
  error_msg         VARCHAR(2000)   DEFAULT '', -- 错误消息
  oper_time         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 操作时间
  cost_time         BIGINT          DEFAULT 0 -- 消耗时间
);

-- 创建索引
CREATE INDEX idx_sys_oper_log_bt ON sys_oper_log(business_type);
CREATE INDEX idx_sys_oper_log_s  ON sys_oper_log(status);
CREATE INDEX idx_sys_oper_log_ot ON sys_oper_log(oper_time);

COMMENT ON COLUMN sys_oper_log.oper_id IS '日志主键';
COMMENT ON COLUMN sys_oper_log.title IS '模块标题';
COMMENT ON COLUMN sys_oper_log.business_type IS '业务类型（0其它 1新增 2修改 3删除）';
COMMENT ON COLUMN sys_oper_log.method IS '方法名称';
COMMENT ON COLUMN sys_oper_log.request_method IS '请求方式';
COMMENT ON COLUMN sys_oper_log.operator_type IS '操作类别（0其它 1后台用户 2手机端用户）';
COMMENT ON COLUMN sys_oper_log.oper_name IS '操作人员';
COMMENT ON COLUMN sys_oper_log.dept_name IS '部门名称';
COMMENT ON COLUMN sys_oper_log.oper_url IS '请求URL';
COMMENT ON COLUMN sys_oper_log.oper_ip IS '主机地址';
COMMENT ON COLUMN sys_oper_log.oper_location IS '操作地点';
COMMENT ON COLUMN sys_oper_log.oper_param IS '请求参数';
COMMENT ON COLUMN sys_oper_log.json_result IS '返回参数';
COMMENT ON COLUMN sys_oper_log.status IS '操作状态（0正常 1异常）';
COMMENT ON COLUMN sys_oper_log.error_msg IS '错误消息';
COMMENT ON COLUMN sys_oper_log.oper_time IS '操作时间';
COMMENT ON COLUMN sys_oper_log.cost_time IS '消耗时间';

COMMENT ON TABLE sys_oper_log IS '操作日志记录';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dict_type' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_dict_type CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dict_type_dict_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_dict_type_dict_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_dict_type_dict_id_seq;

CREATE TABLE sys_dict_type (
  dict_id          BIGINT          NOT NULL DEFAULT NEXTVAL('sys_dict_type_dict_id_seq') PRIMARY KEY, -- 字典主键
  dict_name        VARCHAR(100)    DEFAULT '', -- 字典名称
  dict_type        VARCHAR(100)    DEFAULT '', -- 字典类型
  status           CHAR(1)         DEFAULT '0', -- 状态（0正常 1停用）
  create_by        VARCHAR(64)     DEFAULT '', -- 创建者
  create_time      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by        VARCHAR(64)     DEFAULT '', -- 更新者
  update_time      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark           VARCHAR(500)    DEFAULT NULL -- 备注
);

ALTER TABLE sys_dict_type ADD CONSTRAINT uk_sys_dict_type UNIQUE (dict_type);

COMMENT ON COLUMN sys_dict_type.dict_id IS '字典主键';
COMMENT ON COLUMN sys_dict_type.dict_name IS '字典名称';
COMMENT ON COLUMN sys_dict_type.dict_type IS '字典类型';
COMMENT ON COLUMN sys_dict_type.status IS '状态（0正常 1停用）';
COMMENT ON COLUMN sys_dict_type.create_by IS '创建者';
COMMENT ON COLUMN sys_dict_type.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_type.update_by IS '更新者';
COMMENT ON COLUMN sys_dict_type.update_time IS '更新时间';
COMMENT ON COLUMN sys_dict_type.remark IS '备注';

COMMENT ON TABLE sys_dict_type IS '字典类型表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dict_data' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_dict_data CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_dict_data_dict_code_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_dict_data_dict_code_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_dict_data_dict_code_seq;

CREATE TABLE sys_dict_data (
  dict_code        BIGINT          NOT NULL DEFAULT NEXTVAL('sys_dict_data_dict_code_seq') PRIMARY KEY, -- 字典编码
  dict_sort        INT             DEFAULT 0, -- 字典排序
  dict_label       VARCHAR(100)    DEFAULT '', -- 字典标签
  dict_value       VARCHAR(100)    DEFAULT '', -- 字典键值
  dict_type        VARCHAR(100)    DEFAULT '', -- 字典类型
  css_class        VARCHAR(100), -- 样式属性（其他样式扩展）
  list_class       VARCHAR(100), -- 表格回显样式
  is_default       CHAR(1)         DEFAULT 'N', -- 是否默认（Y是 N否）
  status           CHAR(1)         DEFAULT '0', -- 状态（0正常 1停用）
  create_by        VARCHAR(64)     DEFAULT '', -- 创建者
  create_time      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by        VARCHAR(64)     DEFAULT '', -- 更新者
  update_time      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark           VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_dict_data.dict_code IS '字典编码';
COMMENT ON COLUMN sys_dict_data.dict_sort IS '字典排序';
COMMENT ON COLUMN sys_dict_data.dict_label IS '字典标签';
COMMENT ON COLUMN sys_dict_data.dict_value IS '字典键值';
COMMENT ON COLUMN sys_dict_data.dict_type IS '字典类型';
COMMENT ON COLUMN sys_dict_data.css_class IS '样式属性（其他样式扩展）';
COMMENT ON COLUMN sys_dict_data.list_class IS '表格回显样式';
COMMENT ON COLUMN sys_dict_data.is_default IS '是否默认（Y是 N否）';
COMMENT ON COLUMN sys_dict_data.status IS '状态（0正常 1停用）';
COMMENT ON COLUMN sys_dict_data.create_by IS '创建者';
COMMENT ON COLUMN sys_dict_data.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_data.update_by IS '更新者';
COMMENT ON COLUMN sys_dict_data.update_time IS '更新时间';
COMMENT ON COLUMN sys_dict_data.remark IS '备注';

COMMENT ON TABLE sys_dict_data IS '字典数据表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_config' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_config CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_config_config_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_config_config_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_config_config_id_seq;

CREATE TABLE sys_config (
  config_id         INTEGER         NOT NULL DEFAULT NEXTVAL('sys_config_config_id_seq') PRIMARY KEY, -- 参数主键
  config_name       VARCHAR(100)    DEFAULT '', -- 参数名称
  config_key        VARCHAR(100)    DEFAULT '', -- 参数键名
  config_value      VARCHAR(500)    DEFAULT '', -- 参数键值
  config_type       CHAR(1)         DEFAULT 'N', -- 系统内置（Y是 N否）
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark            VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_config.config_id IS '参数主键';
COMMENT ON COLUMN sys_config.config_name IS '参数名称';
COMMENT ON COLUMN sys_config.config_key IS '参数键名';
COMMENT ON COLUMN sys_config.config_value IS '参数键值';
COMMENT ON COLUMN sys_config.config_type IS '系统内置（Y是 N否）';
COMMENT ON COLUMN sys_config.create_by IS '创建者';
COMMENT ON COLUMN sys_config.create_time IS '创建时间';
COMMENT ON COLUMN sys_config.update_by IS '更新者';
COMMENT ON COLUMN sys_config.update_time IS '更新时间';
COMMENT ON COLUMN sys_config.remark IS '备注';

COMMENT ON TABLE sys_config IS '参数配置表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_logininfor' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_logininfor CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_logininfor_info_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_logininfor_info_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_logininfor_info_id_seq;

CREATE TABLE sys_logininfor (
  info_id        BIGINT          NOT NULL DEFAULT NEXTVAL('sys_logininfor_info_id_seq') PRIMARY KEY, -- 访问ID
  user_name      VARCHAR(50)     DEFAULT '', -- 用户账号
  ipaddr         VARCHAR(128)    DEFAULT '', -- 登录IP地址
  login_location VARCHAR(255)    DEFAULT '', -- 登录地点
  browser        VARCHAR(50)     DEFAULT '', -- 浏览器类型
  os             VARCHAR(50)     DEFAULT '', -- 操作系统
  status         CHAR(1)         DEFAULT '0', -- 登录状态（0成功 1失败）
  msg            VARCHAR(255)    DEFAULT '', -- 提示消息
  login_time     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP -- 访问时间
);

-- 创建索引
CREATE INDEX idx_sys_logininfor_s  ON sys_logininfor(status);
CREATE INDEX idx_sys_logininfor_lt ON sys_logininfor(login_time);

COMMENT ON COLUMN sys_logininfor.info_id IS '访问ID';
COMMENT ON COLUMN sys_logininfor.user_name IS '用户账号';
COMMENT ON COLUMN sys_logininfor.ipaddr IS '登录IP地址';
COMMENT ON COLUMN sys_logininfor.login_location IS '登录地点';
COMMENT ON COLUMN sys_logininfor.browser IS '浏览器类型';
COMMENT ON COLUMN sys_logininfor.os IS '操作系统';
COMMENT ON COLUMN sys_logininfor.status IS '登录状态（0成功 1失败）';
COMMENT ON COLUMN sys_logininfor.msg IS '提示消息';
COMMENT ON COLUMN sys_logininfor.login_time IS '访问时间';

COMMENT ON TABLE sys_logininfor IS '系统访问记录';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_job' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_job CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_job_job_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_job_job_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_job_job_id_seq;

CREATE TABLE sys_job (
  job_id              BIGINT          NOT NULL DEFAULT NEXTVAL('sys_job_job_id_seq'), -- 任务ID
  job_name            VARCHAR(64)     DEFAULT '', -- 任务名称
  job_group           VARCHAR(64)     DEFAULT 'DEFAULT', -- 任务组名
  invoke_target       VARCHAR(500)    NOT NULL, -- 调用目标字符串
  cron_expression     VARCHAR(255)    DEFAULT '', -- cron执行表达式
  misfire_policy      VARCHAR(20)     DEFAULT '3', -- 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
  concurrent          CHAR(1)         DEFAULT '1', -- 是否并发执行（0允许 1禁止）
  status              CHAR(1)         DEFAULT '0', -- 状态（0正常 1暂停）
  create_by           VARCHAR(64)     DEFAULT '', -- 创建者
  create_time         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by           VARCHAR(64)     DEFAULT '', -- 更新者
  update_time         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark              VARCHAR(500)    DEFAULT '', -- 备注信息
  PRIMARY KEY (job_id, job_name, job_group)
);

COMMENT ON COLUMN sys_job.job_id IS '任务ID';
COMMENT ON COLUMN sys_job.job_name IS '任务名称';
COMMENT ON COLUMN sys_job.job_group IS '任务组名';
COMMENT ON COLUMN sys_job.invoke_target IS '调用目标字符串';
COMMENT ON COLUMN sys_job.cron_expression IS 'cron执行表达式';
COMMENT ON COLUMN sys_job.misfire_policy IS '计划执行错误策略（1立即执行 2执行一次 3放弃执行）';
COMMENT ON COLUMN sys_job.concurrent IS '是否并发执行（0允许 1禁止）';
COMMENT ON COLUMN sys_job.status IS '状态（0正常 1暂停）';
COMMENT ON COLUMN sys_job.create_by IS '创建者';
COMMENT ON COLUMN sys_job.create_time IS '创建时间';
COMMENT ON COLUMN sys_job.update_by IS '更新者';
COMMENT ON COLUMN sys_job.update_time IS '更新时间';
COMMENT ON COLUMN sys_job.remark IS '备注信息';

COMMENT ON TABLE sys_job IS '定时任务调度表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_job_log' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_job_log CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_job_log_job_log_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_job_log_job_log_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_job_log_job_log_id_seq;

CREATE TABLE sys_job_log (
  job_log_id          BIGINT          NOT NULL DEFAULT NEXTVAL('sys_job_log_job_log_id_seq') PRIMARY KEY, -- 任务日志ID
  job_name            VARCHAR(64)     NOT NULL, -- 任务名称
  job_group           VARCHAR(64)     NOT NULL, -- 任务组名
  invoke_target       VARCHAR(500)    NOT NULL, -- 调用目标字符串
  job_message         VARCHAR(500), -- 日志信息
  status              CHAR(1)         DEFAULT '0', -- 执行状态（0正常 1失败）
  exception_info      VARCHAR(2000)   DEFAULT '', -- 异常信息
  create_time         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP -- 创建时间
);

COMMENT ON COLUMN sys_job_log.job_log_id IS '任务日志ID';
COMMENT ON COLUMN sys_job_log.job_name IS '任务名称';
COMMENT ON COLUMN sys_job_log.job_group IS '任务组名';
COMMENT ON COLUMN sys_job_log.invoke_target IS '调用目标字符串';
COMMENT ON COLUMN sys_job_log.job_message IS '日志信息';
COMMENT ON COLUMN sys_job_log.status IS '执行状态（0正常 1失败）';
COMMENT ON COLUMN sys_job_log.exception_info IS '异常信息';
COMMENT ON COLUMN sys_job_log.create_time IS '创建时间';

COMMENT ON TABLE sys_job_log IS '定时任务调度日志表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_notice' AND n.nspname = 'public') THEN
    DROP TABLE public.sys_notice CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'sys_notice_notice_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.sys_notice_notice_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE sys_notice_notice_id_seq;

CREATE TABLE sys_notice (
  notice_id         INTEGER         NOT NULL DEFAULT NEXTVAL('sys_notice_notice_id_seq') PRIMARY KEY, -- 公告ID
  notice_title      VARCHAR(50)     NOT NULL, -- 公告标题
  notice_type       CHAR(1)         NOT NULL, -- 公告类型（1通知 2公告）
  notice_content    BYTEA, -- 公告内容
  status            CHAR(1)         DEFAULT '0', -- 公告状态（0正常 1关闭）
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark            VARCHAR(255)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN sys_notice.notice_id IS '公告ID';
COMMENT ON COLUMN sys_notice.notice_title IS '公告标题';
COMMENT ON COLUMN sys_notice.notice_type IS '公告类型（1通知 2公告）';
COMMENT ON COLUMN sys_notice.notice_content IS '公告内容';
COMMENT ON COLUMN sys_notice.status IS '公告状态（0正常 1关闭）';
COMMENT ON COLUMN sys_notice.create_by IS '创建者';
COMMENT ON COLUMN sys_notice.create_time IS '创建时间';
COMMENT ON COLUMN sys_notice.update_by IS '更新者';
COMMENT ON COLUMN sys_notice.update_time IS '更新时间';
COMMENT ON COLUMN sys_notice.remark IS '备注';

COMMENT ON TABLE sys_notice IS '通知公告表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'gen_table' AND n.nspname = 'public') THEN
    DROP TABLE public.gen_table CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'gen_table_table_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.gen_table_table_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE gen_table_table_id_seq;

CREATE TABLE gen_table (
  table_id          BIGINT          NOT NULL DEFAULT NEXTVAL('gen_table_table_id_seq') PRIMARY KEY, -- 编号
  table_name        VARCHAR(200)    DEFAULT '', -- 表名称
  table_comment     VARCHAR(500)    DEFAULT '', -- 表描述
  sub_table_name    VARCHAR(64), -- 关联子表的表名
  sub_table_fk_name VARCHAR(64), -- 子表关联的外键名
  class_name        VARCHAR(100)    DEFAULT '', -- 实体类名称
  tpl_category      VARCHAR(200)    DEFAULT 'crud', -- 使用的模板（crud单表操作 tree树表操作）
  tpl_web_type      VARCHAR(30), -- 前端模板类型（element-ui模版 element-plus模版）
  package_name      VARCHAR(100), -- 生成包路径
  module_name       VARCHAR(30), -- 生成模块名
  business_name     VARCHAR(30), -- 生成业务名
  function_name     VARCHAR(50), -- 生成功能名
  function_author   VARCHAR(50), -- 生成功能作者
  gen_type          CHAR(1)         DEFAULT '0', -- 生成代码方式（0zip压缩包 1自定义路径）
  gen_path          VARCHAR(200)    DEFAULT '/', -- 生成路径（不填默认项目路径）
  options           VARCHAR(1000), -- 其它生成选项
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
  remark            VARCHAR(500)    DEFAULT NULL -- 备注
);

COMMENT ON COLUMN gen_table.table_id IS '编号';
COMMENT ON COLUMN gen_table.table_name IS '表名称';
COMMENT ON COLUMN gen_table.table_comment IS '表描述';
COMMENT ON COLUMN gen_table.sub_table_name IS '关联子表的表名';
COMMENT ON COLUMN gen_table.sub_table_fk_name IS '子表关联的外键名';
COMMENT ON COLUMN gen_table.class_name IS '实体类名称';
COMMENT ON COLUMN gen_table.tpl_category IS '使用的模板（crud单表操作 tree树表操作）';
COMMENT ON COLUMN gen_table.tpl_web_type IS '前端模板类型（element-ui模版 element-plus模版）';
COMMENT ON COLUMN gen_table.package_name IS '生成包路径';
COMMENT ON COLUMN gen_table.module_name IS '生成模块名';
COMMENT ON COLUMN gen_table.business_name IS '生成业务名';
COMMENT ON COLUMN gen_table.function_name IS '生成功能名';
COMMENT ON COLUMN gen_table.function_author IS '生成功能作者';
COMMENT ON COLUMN gen_table.gen_type IS '生成代码方式（0zip压缩包 1自定义路径）';
COMMENT ON COLUMN gen_table.gen_path IS '生成路径（不填默认项目路径）';
COMMENT ON COLUMN gen_table.options IS '其它生成选项';
COMMENT ON COLUMN gen_table.create_by IS '创建者';
COMMENT ON COLUMN gen_table.create_time IS '创建时间';
COMMENT ON COLUMN gen_table.update_by IS '更新者';
COMMENT ON COLUMN gen_table.update_time IS '更新时间';
COMMENT ON COLUMN gen_table.remark IS '备注';

COMMENT ON TABLE gen_table IS '代码生成业务表';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'gen_table_column' AND n.nspname = 'public') THEN
    DROP TABLE public.gen_table_column CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relname = 'gen_table_column_column_id_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE public.gen_table_column_column_id_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE gen_table_column_column_id_seq;

CREATE TABLE gen_table_column (
  column_id         BIGINT          NOT NULL DEFAULT NEXTVAL('gen_table_column_column_id_seq') PRIMARY KEY, -- 编号
  table_id          BIGINT, -- 归属表编号
  column_name       VARCHAR(200), -- 列名称
  column_comment    VARCHAR(500), -- 列描述
  column_type       VARCHAR(100), -- 列类型
  java_type         VARCHAR(500), -- JAVA类型
  java_field        VARCHAR(200), -- JAVA字段名
  is_pk             CHAR(1), -- 是否主键（1是）
  is_increment      CHAR(1), -- 是否自增（1是）
  is_required       CHAR(1), -- 是否必填（1是）
  is_insert         CHAR(1), -- 是否为插入字段（1是）
  is_edit           CHAR(1), -- 是否编辑字段（1是）
  is_list           CHAR(1), -- 是否列表字段（1是）
  is_query          CHAR(1), -- 是否查询字段（1是）
  query_type        VARCHAR(200)    DEFAULT 'EQ', -- 查询方式（等于、不等于、大于、小于、范围）
  html_type         VARCHAR(200), -- 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
  dict_type         VARCHAR(200)    DEFAULT '', -- 字典类型
  sort              INT, -- 排序
  create_by         VARCHAR(64)     DEFAULT '', -- 创建者
  create_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by         VARCHAR(64)     DEFAULT '', -- 更新者
  update_time       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP -- 更新时间
);

COMMENT ON COLUMN gen_table_column.column_id IS '编号';
COMMENT ON COLUMN gen_table_column.table_id IS '归属表编号';
COMMENT ON COLUMN gen_table_column.column_name IS '列名称';
COMMENT ON COLUMN gen_table_column.column_comment IS '列描述';
COMMENT ON COLUMN gen_table_column.column_type IS '列类型';
COMMENT ON COLUMN gen_table_column.java_type IS 'JAVA类型';
COMMENT ON COLUMN gen_table_column.java_field IS 'JAVA字段名';
COMMENT ON COLUMN gen_table_column.is_pk IS '是否主键（1是）';
COMMENT ON COLUMN gen_table_column.is_increment IS '是否自增（1是）';
COMMENT ON COLUMN gen_table_column.is_required IS '是否必填（1是）';
COMMENT ON COLUMN gen_table_column.is_insert IS '是否为插入字段（1是）';
COMMENT ON COLUMN gen_table_column.is_edit IS '是否编辑字段（1是）';
COMMENT ON COLUMN gen_table_column.is_list IS '是否列表字段（1是）';
COMMENT ON COLUMN gen_table_column.is_query IS '是否查询字段（1是）';
COMMENT ON COLUMN gen_table_column.query_type IS '查询方式（等于、不等于、大于、小于、范围）';
COMMENT ON COLUMN gen_table_column.html_type IS '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）';
COMMENT ON COLUMN gen_table_column.dict_type IS '字典类型';
COMMENT ON COLUMN gen_table_column.sort IS '排序';
COMMENT ON COLUMN gen_table_column.create_by IS '创建者';
COMMENT ON COLUMN gen_table_column.create_time IS '创建时间';
COMMENT ON COLUMN gen_table_column.update_by IS '更新者';
COMMENT ON COLUMN gen_table_column.update_time IS '更新时间';

COMMENT ON TABLE gen_table_column IS '代码生成业务表字段';
