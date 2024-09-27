DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_job_details' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_job_details CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_job_details_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_job_details_seq CASCADE;
  END IF;
END $$;

CREATE SEQUENCE qrtz_job_details_seq;

CREATE TABLE qrtz_job_details (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    job_name             VARCHAR(200)    NOT NULL,   -- 任务名称
    job_group            VARCHAR(200)    NOT NULL,   -- 任务组名
    description          VARCHAR(250),   -- 相关介绍
    job_class_name       VARCHAR(250)    NOT NULL,   -- 执行任务类名称
    is_durable           CHAR(1)         NOT NULL,   -- 是否持久化
    is_nonconcurrent     CHAR(1)         NOT NULL,   -- 是否并发
    is_update_data       CHAR(1)         NOT NULL,   -- 是否更新数据
    requests_recovery    CHAR(1)         NOT NULL,   -- 是否接受恢复执行
    job_data             BYTEA,          -- 存放持久化job对象
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

COMMENT ON TABLE qrtz_job_details IS '任务详细信息表';
COMMENT ON COLUMN qrtz_job_details.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_job_details.job_name IS '任务名称';
COMMENT ON COLUMN qrtz_job_details.job_group IS '任务组名';
COMMENT ON COLUMN qrtz_job_details.description IS '相关介绍';
COMMENT ON COLUMN qrtz_job_details.job_class_name IS '执行任务类名称';
COMMENT ON COLUMN qrtz_job_details.is_durable IS '是否持久化';
COMMENT ON COLUMN qrtz_job_details.is_nonconcurrent IS '是否并发';
COMMENT ON COLUMN qrtz_job_details.is_update_data IS '是否更新数据';
COMMENT ON COLUMN qrtz_job_details.requests_recovery IS '是否接受恢复执行';
COMMENT ON COLUMN qrtz_job_details.job_data IS '存放持久化job对象';
COMMENT ON COLUMN qrtz_job_details.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_job_details.update_time IS '更新时间';

ALTER TABLE qrtz_job_details ADD PRIMARY KEY (sched_name, job_name, job_group);

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_triggers_seq;

CREATE TABLE qrtz_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_name         VARCHAR(200)    NOT NULL,   -- 触发器的名字
    trigger_group        VARCHAR(200)    NOT NULL,   -- 触发器所属组的名字
    job_name             VARCHAR(200)    NOT NULL,   -- qrtz_job_details表job_name的外键
    job_group            VARCHAR(200)    NOT NULL,   -- qrtz_job_details表job_group的外键
    description          VARCHAR(250),   -- 相关介绍
    next_fire_time       BIGINT,         -- 上一次触发时间（毫秒）
    prev_fire_time       BIGINT,         -- 下一次触发时间（默认为-1表示不触发）
    priority             INTEGER,        -- 优先级
    trigger_state        VARCHAR(16)     NOT NULL,   -- 触发器状态
    trigger_type         VARCHAR(8)      NOT NULL,   -- 触发器的类型
    start_time           BIGINT          NOT NULL,   -- 开始时间
    end_time             BIGINT,         -- 结束时间
    calendar_name        VARCHAR(200),   -- 日程表名称
    misfire_instr        SMALLINT,       -- 补偿执行的策略
    job_data             BYTEA,          -- 存放持久化job对象
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_triggers ADD PRIMARY KEY (sched_name, trigger_name, trigger_group);
ALTER TABLE qrtz_triggers ADD FOREIGN KEY (sched_name, job_name, job_group) REFERENCES qrtz_job_details(sched_name, job_name, job_group);

COMMENT ON TABLE qrtz_triggers IS '触发器详细信息表';
COMMENT ON COLUMN qrtz_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_triggers.trigger_name IS '触发器的名字';
COMMENT ON COLUMN qrtz_triggers.trigger_group IS '触发器所属组的名字';
COMMENT ON COLUMN qrtz_triggers.job_name IS 'qrtz_job_details表job_name的外键';
COMMENT ON COLUMN qrtz_triggers.job_group IS 'qrtz_job_details表job_group的外键';
COMMENT ON COLUMN qrtz_triggers.description IS '相关介绍';
COMMENT ON COLUMN qrtz_triggers.next_fire_time IS '上一次触发时间（毫秒）';
COMMENT ON COLUMN qrtz_triggers.prev_fire_time IS '下一次触发时间（默认为-1表示不触发）';
COMMENT ON COLUMN qrtz_triggers.priority IS '优先级';
COMMENT ON COLUMN qrtz_triggers.trigger_state IS '触发器状态';
COMMENT ON COLUMN qrtz_triggers.trigger_type IS '触发器的类型';
COMMENT ON COLUMN qrtz_triggers.start_time IS '开始时间';
COMMENT ON COLUMN qrtz_triggers.end_time IS '结束时间';
COMMENT ON COLUMN qrtz_triggers.calendar_name IS '日程表名称';
COMMENT ON COLUMN qrtz_triggers.misfire_instr IS '补偿执行的策略';
COMMENT ON COLUMN qrtz_triggers.job_data IS '存放持久化job对象';
COMMENT ON COLUMN qrtz_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_triggers.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_simple_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_simple_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_simple_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_simple_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_simple_triggers_seq;

CREATE TABLE qrtz_simple_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_name         VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_name的外键
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    repeat_count         BIGINT          NOT NULL,   -- 重复的次数统计
    repeat_interval      BIGINT          NOT NULL,   -- 重复的间隔时间
    times_triggered      BIGINT          NOT NULL,   -- 已经触发的次数
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_simple_triggers ADD PRIMARY KEY (sched_name, trigger_name, trigger_group);
ALTER TABLE qrtz_simple_triggers ADD FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers(sched_name, trigger_name, trigger_group);

COMMENT ON TABLE qrtz_simple_triggers IS '简单触发器的信息表';
COMMENT ON COLUMN qrtz_simple_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_simple_triggers.trigger_name IS 'qrtz_triggers表trigger_name的外键';
COMMENT ON COLUMN qrtz_simple_triggers.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_simple_triggers.repeat_count IS '重复的次数统计';
COMMENT ON COLUMN qrtz_simple_triggers.repeat_interval IS '重复的间隔时间';
COMMENT ON COLUMN qrtz_simple_triggers.times_triggered IS '已经触发的次数';
COMMENT ON COLUMN qrtz_simple_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_simple_triggers.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_cron_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_cron_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_cron_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_cron_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_cron_triggers_seq;

CREATE TABLE qrtz_cron_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_name         VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_name的外键
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    cron_expression      VARCHAR(200)    NOT NULL,   -- cron表达式
    time_zone_id         VARCHAR(80),    -- 时区
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_cron_triggers ADD PRIMARY KEY (sched_name, trigger_name, trigger_group);
ALTER TABLE qrtz_cron_triggers ADD FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers(sched_name, trigger_name, trigger_group);

COMMENT ON TABLE qrtz_cron_triggers IS 'Cron类型的触发器表';
COMMENT ON COLUMN qrtz_cron_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_cron_triggers.trigger_name IS 'qrtz_triggers表trigger_name的外键';
COMMENT ON COLUMN qrtz_cron_triggers.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_cron_triggers.cron_expression IS 'cron表达式';
COMMENT ON COLUMN qrtz_cron_triggers.time_zone_id IS '时区';
COMMENT ON COLUMN qrtz_cron_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_cron_triggers.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_blob_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_blob_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_blob_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_blob_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_blob_triggers_seq;

CREATE TABLE qrtz_blob_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_name         VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_name的外键
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    blob_data            BYTEA,          -- 存放持久化Trigger对象
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_blob_triggers ADD PRIMARY KEY (sched_name, trigger_name, trigger_group);
ALTER TABLE qrtz_blob_triggers ADD FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers(sched_name, trigger_name, trigger_group);

COMMENT ON TABLE qrtz_blob_triggers IS 'Blob类型的触发器表';
COMMENT ON COLUMN qrtz_blob_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_blob_triggers.trigger_name IS 'qrtz_triggers表trigger_name的外键';
COMMENT ON COLUMN qrtz_blob_triggers.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_blob_triggers.blob_data IS '存放持久化Trigger对象';
COMMENT ON COLUMN qrtz_blob_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_blob_triggers.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_calendars' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_calendars CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_calendars_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_calendars_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_calendars_seq;

CREATE TABLE qrtz_calendars (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    calendar_name        VARCHAR(200)    NOT NULL,   -- 日历名称
    calendar             BYTEA           NOT NULL,   -- 存放持久化calendar对象
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_calendars ADD PRIMARY KEY (sched_name, calendar_name);

COMMENT ON TABLE qrtz_calendars IS '日历信息表';
COMMENT ON COLUMN qrtz_calendars.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_calendars.calendar_name IS '日历名称';
COMMENT ON COLUMN qrtz_calendars.calendar IS '存放持久化calendar对象';
COMMENT ON COLUMN qrtz_calendars.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_calendars.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_paused_trigger_grps' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_paused_trigger_grps CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_paused_trigger_grps_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_paused_trigger_grps_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_paused_trigger_grps_seq;

CREATE TABLE qrtz_paused_trigger_grps (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_paused_trigger_grps ADD PRIMARY KEY (sched_name, trigger_group);

COMMENT ON TABLE qrtz_paused_trigger_grps IS '暂停的触发器表';
COMMENT ON COLUMN qrtz_paused_trigger_grps.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_paused_trigger_grps.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_paused_trigger_grps.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_paused_trigger_grps.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_fired_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_fired_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_fired_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_fired_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_fired_triggers_seq;

CREATE TABLE qrtz_fired_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    entry_id             VARCHAR(95)     NOT NULL,   -- 调度器实例id
    trigger_name         VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_name的外键
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    instance_name        VARCHAR(200)    NOT NULL,   -- 调度器实例名
    fired_time           BIGINT          NOT NULL,   -- 触发的时间
    sched_time           BIGINT          NOT NULL,   -- 定时器制定的时间
    priority             INTEGER         NOT NULL,   -- 优先级
    state                VARCHAR(16)     NOT NULL,   -- 状态
    job_name             VARCHAR(200),   -- 任务名称
    job_group            VARCHAR(200),   -- 任务组名
    is_nonconcurrent     CHAR(1),        -- 是否并发
    requests_recovery    CHAR(1),        -- 是否接受恢复执行
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_fired_triggers ADD PRIMARY KEY (sched_name, entry_id);

COMMENT ON TABLE qrtz_fired_triggers IS '已触发的触发器表';
COMMENT ON COLUMN qrtz_fired_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_fired_triggers.entry_id IS '调度器实例id';
COMMENT ON COLUMN qrtz_fired_triggers.trigger_name IS 'qrtz_triggers表trigger_name的外键';
COMMENT ON COLUMN qrtz_fired_triggers.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_fired_triggers.instance_name IS '调度器实例名';
COMMENT ON COLUMN qrtz_fired_triggers.fired_time IS '触发的时间';
COMMENT ON COLUMN qrtz_fired_triggers.sched_time IS '定时器制定的时间';
COMMENT ON COLUMN qrtz_fired_triggers.priority IS '优先级';
COMMENT ON COLUMN qrtz_fired_triggers.state IS '状态';
COMMENT ON COLUMN qrtz_fired_triggers.job_name IS '任务名称';
COMMENT ON COLUMN qrtz_fired_triggers.job_group IS '任务组名';
COMMENT ON COLUMN qrtz_fired_triggers.is_nonconcurrent IS '是否并发';
COMMENT ON COLUMN qrtz_fired_triggers.requests_recovery IS '是否接受恢复执行';
COMMENT ON COLUMN qrtz_fired_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_fired_triggers.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_scheduler_state' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_scheduler_state CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_scheduler_state_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_scheduler_state_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_scheduler_state_seq;

CREATE TABLE qrtz_scheduler_state (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    instance_name        VARCHAR(200)    NOT NULL,   -- 实例名称
    last_checkin_time    BIGINT          NOT NULL,   -- 上次检查时间
    checkin_interval     BIGINT          NOT NULL,   -- 检查间隔时间
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_scheduler_state ADD PRIMARY KEY (sched_name, instance_name);

COMMENT ON TABLE qrtz_scheduler_state IS '调度器状态表';
COMMENT ON COLUMN qrtz_scheduler_state.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_scheduler_state.instance_name IS '实例名称';
COMMENT ON COLUMN qrtz_scheduler_state.last_checkin_time IS '上次检查时间';
COMMENT ON COLUMN qrtz_scheduler_state.checkin_interval IS '检查间隔时间';
COMMENT ON COLUMN qrtz_scheduler_state.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_scheduler_state.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_locks' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_locks CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_locks_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_locks_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_locks_seq;

CREATE TABLE qrtz_locks (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    lock_name            VARCHAR(40)     NOT NULL,   -- 悲观锁名称
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_locks ADD PRIMARY KEY (sched_name, lock_name);

COMMENT ON TABLE qrtz_locks IS '存储的悲观锁信息表';
COMMENT ON COLUMN qrtz_locks.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_locks.lock_name IS '悲观锁名称';
COMMENT ON COLUMN qrtz_locks.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_locks.update_time IS '更新时间';

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_simprop_triggers' AND n.nspname = 'public') THEN
    DROP TABLE IF EXISTS qrtz_simprop_triggers CASCADE;
  END IF;
END $$;

DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_catalog.pg_class c
             JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
             WHERE c.relname = 'qrtz_simprop_triggers_seq' AND n.nspname = 'public') THEN
    DROP SEQUENCE IF EXISTS qrtz_simprop_triggers_seq;
  END IF;
END $$;

CREATE SEQUENCE qrtz_simprop_triggers_seq;

CREATE TABLE qrtz_simprop_triggers (
    sched_name           VARCHAR(120)    NOT NULL,   -- 调度名称
    trigger_name         VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_name的外键
    trigger_group        VARCHAR(200)    NOT NULL,   -- qrtz_triggers表trigger_group的外键
    str_prop_1           VARCHAR(512),   -- String类型的trigger的第一个参数
    str_prop_2           VARCHAR(512),   -- String类型的trigger的第二个参数
    str_prop_3           VARCHAR(512),   -- String类型的trigger的第三个参数
    int_prop_1           INTEGER,        -- int类型的trigger的第一个参数
    int_prop_2           INTEGER,        -- int类型的trigger的第二个参数
    long_prop_1          BIGINT,         -- long类型的trigger的第一个参数
    long_prop_2          BIGINT,         -- long类型的trigger的第二个参数
    dec_prop_1           NUMERIC(13,4),  -- decimal类型的trigger的第一个参数
    dec_prop_2           NUMERIC(13,4),  -- decimal类型的trigger的第二个参数
    bool_prop_1          CHAR(1),        -- Boolean类型的trigger的第一个参数
    bool_prop_2          CHAR(1),        -- Boolean类型的trigger的第二个参数
    create_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

ALTER TABLE qrtz_simprop_triggers ADD PRIMARY KEY (sched_name, trigger_name, trigger_group);
ALTER TABLE qrtz_simprop_triggers ADD FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers(sched_name, trigger_name, trigger_group);

COMMENT ON TABLE qrtz_simprop_triggers IS '同步机制的行锁表';
COMMENT ON COLUMN qrtz_simprop_triggers.sched_name IS '调度名称';
COMMENT ON COLUMN qrtz_simprop_triggers.trigger_name IS 'qrtz_triggers表trigger_name的外键';
COMMENT ON COLUMN qrtz_simprop_triggers.trigger_group IS 'qrtz_triggers表trigger_group的外键';
COMMENT ON COLUMN qrtz_simprop_triggers.str_prop_1 IS 'String类型的trigger的第一个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.str_prop_2 IS 'String类型的trigger的第二个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.str_prop_3 IS 'String类型的trigger的第三个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.int_prop_1 IS 'int类型的trigger的第一个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.int_prop_2 IS 'int类型的trigger的第二个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.long_prop_1 IS 'long类型的trigger的第一个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.long_prop_2 IS 'long类型的trigger的第二个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.dec_prop_1 IS 'decimal类型的trigger的第一个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.dec_prop_2 IS 'decimal类型的trigger的第二个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.bool_prop_1 IS 'Boolean类型的trigger的第一个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.bool_prop_2 IS 'Boolean类型的trigger的第二个参数';
COMMENT ON COLUMN qrtz_simprop_triggers.create_time IS '创建时间';
COMMENT ON COLUMN qrtz_simprop_triggers.update_time IS '更新时间';