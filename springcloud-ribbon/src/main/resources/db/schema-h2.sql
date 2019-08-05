DROP TABLE IF EXISTS user_info;

CREATE TABLE user_info
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	username VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	password varchar(50) default null comment '密码',
	user_sex varchar(2) default null comment '姓别',
	nick_name varchar(50) default null comment '别名',
	birthday date NULL DEFAULT NULL COMMENT '生日',
	jobs VARCHAR(50) NULL DEFAULT NULL COMMENT '职位',
	PRIMARY KEY (id)
);