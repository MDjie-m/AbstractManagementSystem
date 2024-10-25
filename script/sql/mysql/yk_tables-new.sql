

-- 课程相关表设计

CREATE TABLE courses (  
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '课程ID',  
    course_name VARCHAR(255) NOT NULL COMMENT '课程名称',  
    course_description TEXT DEFAULT NULL COMMENT '课程简介',  
   course_cover VARCHAR(255) DEFAULT NULL COMMENT '课程封面', 
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', 
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';


CREATE TABLE courses_menu (  
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '目录ID',  
    memu_name VARCHAR(255) NOT NULL COMMENT '目录名称',  
    course_id bigint(20) NOT NULL COMMENT '课程id',
	sort INT DEFAULT NULL COMMENT '排序',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程目录';


CREATE TABLE courses_file( 
    file_id bigint(20) NOT NULL PRIMARY KEY COMMENT '文件ID',  
	file_name VARCHAR(255) NOT NULL COMMENT '文件名称',  
    file_url VARCHAR(255) NOT NULL COMMENT '文件地址',  
    file_size VARCHAR(100) DEFAULT NULL COMMENT '文件大小',  
    file_type VARCHAR(2100) DEFAULT NULL COMMENT '文件类型',  
	use_type int DEFAULT NULL COMMENT '应用分类  0课程体系 1课程效果 2材料包展示 3课堂总价 4教案 5视频', 
	type_name VARCHAR(100) DEFAULT NULL COMMENT '应用分类名',
	sort INT DEFAULT NULL COMMENT '排序',
	course_id bigint(20) DEFAULT NULL COMMENT '课程ID',  
	menu_id bigint(20) DEFAULT NULL COMMENT '目录ID', 
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程相关文件';


--学校学生相关表设计

CREATE TABLE schools (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '学校ID',
    school_name VARCHAR(255) NOT NULL COMMENT '学校名称',
    address VARCHAR(255) DEFAULT NULL COMMENT '学校地址',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学校表';

CREATE TABLE grade_segment (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '年段ID',
    school_id bigint(20) NOT NULL COMMENT '学校ID',
    segment_name VARCHAR(100) DEFAULT NULL COMMENT '年段（如小学、初中、高中）',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='年段表';


CREATE TABLE grades (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '年级ID',
    grade_name VARCHAR(100) NOT NULL COMMENT '年级名称（如一年级、二年级等）',
    grade_segment_id bigint(20) NOT NULL COMMENT '年段ID',
	school_id bigint(20) COMMENT '学校ID',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='年级表';

CREATE TABLE classes (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '班级ID',
    grade_id bigint(20) NOT NULL COMMENT '年级ID',
    class_name VARCHAR(100) NOT NULL COMMENT '班级名称',
	head_teacher_id INT DEFAULT NULL COMMENT '班主任',
	grade_segment_id bigint(20) DEFAULT NULL COMMENT '年段id',
	school_id bigint(20) DEFAULT NULL COMMENT '学校ID',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

CREATE TABLE teachers (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '教师ID',
    school_id bigint(20) NOT NULL COMMENT '学校ID',
    teacher_name VARCHAR(100) NOT NULL COMMENT '教师姓名',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

CREATE TABLE students (
    id bigint(20) NOT NULL PRIMARY KEY COMMENT '学生ID',
    class_id bigint(20) NOT NULL COMMENT '班级ID',
    student_name VARCHAR(100) NOT NULL COMMENT '学生姓名',
    student_no VARCHAR(100) NOT NULL COMMENT '学号',
    enrolled_time TIMESTAMP COMMENT '入学时间',
	sex VARCHAR(100) DEFAULT NULL COMMENT '性别',
    school_id bigint(20) NOT NULL COMMENT '学校ID',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',  
    update_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
    del_flag tinyint(1) DEFAULT '0' COMMENT '0标识未删除，1表示已删除',
    create_by varchar(64) DEFAULT NULL COMMENT '创建者',
    update_by varchar(64) DEFAULT NULL COMMENT '更新者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';




