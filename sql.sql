-- common_login_log：字典名
DROP TABLE IF EXISTS common_login_log;
CREATE TABLE common_login_log (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	request_ip varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作IP',
	user_id bigint NULL DEFAULT NULL COMMENT '登录人ID',
	user_name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录人姓名',
	account varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录人账号',
	description varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录描述',
	login_date date NULL DEFAULT NULL COMMENT '登录时间',
	ua varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器请求头',
	browser varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器名称',
	browser_version varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器版本',
	operating_system varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作系统',
	location varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录地点',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典名' ROW_FORMAT = Dynamic;

-- common_opt_log：操作日志表
DROP TABLE IF EXISTS common_opt_log;
CREATE TABLE common_opt_log (
	id bigint NOT NULL COMMENT '主键',
	request_ip varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作IP',
	type varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日志类型 OPT:操作类型 EX:异常类型',
	user_name varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
	description varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作描述',
	class_path varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类路径',
	action_method varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求方法',
	request_uri varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求地址',
	http_method varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求类型 GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求',
	params longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求参数',
	result longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '返回值',
	ex_desc longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异常详情信息',
	ex_detail longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异常描述',
	start_time timestamp NULL DEFAULT NULL COMMENT '开始时间',
	finish_time timestamp NULL DEFAULT NULL COMMENT '完成时间',
	consuming_time bigint NULL DEFAULT NULL COMMENT '消耗时间',
	ua varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器请求头',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- auth_menu：菜单表
DROP TABLE IF EXISTS auth_menu;
CREATE TABLE auth_menu (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单名称',
	describe_ varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '功能描述',
	is_public bit NULL DEFAULT NULL COMMENT '是否是公开菜单',
	path varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应路由path',
	component varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应路由组件component',
	is_enable bit NULL DEFAULT NULL COMMENT '是否启用',
	sort_value int NULL DEFAULT NULL COMMENT '排序',
	icon varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单图标',
	group_ varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单分组',
	parent_id bigint NULL DEFAULT NULL COMMENT '父级菜单id',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人id',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- auth_resource：资源表
DROP TABLE IF EXISTS auth_resource;
CREATE TABLE auth_resource (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	code varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源编码',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接口名称',
	menu_id bigint NULL DEFAULT NULL COMMENT '菜单ID',
	method varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'HTTP请求方式',
	url varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接口请求url',
	describe_ varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接口描述',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人id',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人id',
	update_time datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- auth_role：角色表
DROP TABLE IF EXISTS auth_role;
CREATE TABLE auth_role (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
	code varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色编码',
	describe_ varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
	status bit NULL DEFAULT NULL COMMENT '是否启用状态',
	readonly bit NULL DEFAULT NULL COMMENT '是否内置角色',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人id',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人id',
	update_time datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- auth_user：用户表
DROP TABLE IF EXISTS auth_user;
CREATE TABLE auth_user (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	account varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
	org_id bigint NULL DEFAULT NULL COMMENT '组织ID',
	station_id bigint NULL DEFAULT NULL COMMENT '岗位ID',
	email varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
	mobile varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
	sex varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
	status bit NULL DEFAULT NULL COMMENT '启用状态',
	avatar varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
	work_describe varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作描述',
	password_error_last_time datetime NULL DEFAULT NULL COMMENT '最后一次输错密码时间',
	password_error_num int NULL DEFAULT NULL COMMENT '密码错误次数',
	password_expire_time datetime NULL DEFAULT NULL COMMENT '密码过期时间',
	password varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
	last_login_time datetime NULL DEFAULT NULL COMMENT '最后登录时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人id',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人id',
	update_time datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- core_station：岗位表
DROP TABLE IF EXISTS core_station;
CREATE TABLE core_station (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '岗位名称',
	org_id bigint NULL DEFAULT NULL COMMENT '组织ID',
	status bit NULL DEFAULT NULL COMMENT '是否启用状态',
	describe_ varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID',
	update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '岗位表' ROW_FORMAT = Dynamic;

-- core_org：组织表
DROP TABLE IF EXISTS core_org;
CREATE TABLE core_org (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组织名称',
	abbreviation varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简称',
	parent_id bigint NULL DEFAULT NULL COMMENT '父ID',
	tree_path varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '树结构',
	sort_value int NULL DEFAULT NULL COMMENT '排序',
	status bit NULL DEFAULT NULL COMMENT '状态',
	describe_ varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID',
	update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
	update_user bigint NULL DEFAULT NULL COMMENT '更新人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '组织表' ROW_FORMAT = Dynamic;

-- auth_user_role：用户角色关系
DROP TABLE IF EXISTS auth_user_role;
CREATE TABLE auth_user_role (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	role_id bigint NULL DEFAULT NULL COMMENT '角色ID',
	user_id bigint NULL DEFAULT NULL COMMENT '用户ID',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- auth_role_org：角色组织关系表
DROP TABLE IF EXISTS auth_role_org;
CREATE TABLE auth_role_org (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	role_id bigint NULL DEFAULT NULL COMMENT '角色ID',
	org_id bigint NULL DEFAULT NULL COMMENT '组织ID',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色组织关系表' ROW_FORMAT = Dynamic;

-- auth_role_authority：角色权限关系表
DROP TABLE IF EXISTS auth_role_authority;
CREATE TABLE auth_role_authority (
	id bigint PRIMARY KEY NOT NULL COMMENT '主键',
	authority_id bigint NULL DEFAULT NULL COMMENT '权限ID',
	authority_type varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限类型 MENU:菜单 RESOURCE:资源',
	role_id bigint NULL DEFAULT NULL COMMENT '角色ID',
	create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
	create_user bigint NULL DEFAULT NULL COMMENT '创建人ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

