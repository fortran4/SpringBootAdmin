/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 127.0.0.1
 Source Database       : admin

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : utf-8

 Date: 08/09/2016 13:25:24 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `dict_type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序（升序）',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_del_flag` (`del_flag`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_value` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('10', '有效', '1', 'isValid', '是否有效', '1', null, '2016-06-07 15:34:52', null, null, '', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型1：访问日志 2：异常日志',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(1024) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `response` text COMMENT '响应信息',
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_create_date` (`create_date`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
--  Records of `sys_log`
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES ('1', '1', '1', '2016-05-23 13:06:49', 'test_remote_addr', 'user_agent', 'test_request_uri', 'post', 'test', null, null, null), ('2', '1', '1', '2016-05-23 17:02:18', 'testset', 'testset', 'setttest', 'get', 'test', null, null, null), ('3', '1', null, '2016-06-02 10:24:14', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36', 'http://localhost/login', 'login', '[admin, admin]', null, 'SysUser{id=1, loginName=\'admin\', loginPwd=\'admin\', createDate=2016-05-19 16:12:38.0, createBy=1, status=0}', null), ('4', '1', null, '2016-06-02 10:27:34', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36', 'http://localhost/sysLog/findAll', 'findByCreateDateBetween', '[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]', null, 'Page 0 of 0 containing UNKNOWN instances', null), ('5', '1', null, '2016-06-02 10:27:34', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36', 'http://localhost/sysLog/findAll', 'findByCreateDateBetween', '[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]', null, 'Page 0 of 0 containing UNKNOWN instances', null), ('6', '1', null, '2016-06-02 10:27:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36', 'http://localhost/sysLog/findAll', 'findByCreateDateBetween', '[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]', null, 'Page 0 of 0 containing UNKNOWN instances', null), ('7', '1', null, '2016-06-02 10:27:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36', 'http://localhost/sysLog/findAll', 'findByCreateDateBetween', '[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]', null, 'Page 0 of 0 containing UNKNOWN instances', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序（升序）',
  `is_show` char(1) DEFAULT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`menu_id`),
  KEY `sys_menu_del_flag` (`status`),
  KEY `sys_menu_parent_id` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '0', null, '顶级菜单', null, null, null, '1', '1', null, null, '2016-06-06 13:15:55', null, null, null, '0'), ('3', '1', null, '系统管理', null, null, null, '2', '1', null, null, '2016-06-06 13:16:29', null, null, null, '0'), ('4', '3', null, '日志管理', '/sysLog/findAll', null, null, '3', '1', null, null, '2016-06-06 13:17:12', null, null, null, '0'), ('5', '3', null, '菜单管理', 'sysMenu/find', 'mainFrame', null, '2', '1', '', null, '2016-06-07 13:32:18', null, null, '', '0'), ('6', '3', null, '数据字典管理', 'sysDict/find', 'mainFrame', null, '2', '1', '', null, '2016-06-07 13:35:36', null, null, '', '0'), ('7', '3', null, '用户管理', '/user/findAll', 'mainFrame', null, '0', null, '', null, '2016-08-05 12:48:01', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `status` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`role_id`),
  KEY `sys_role_del_flag` (`status`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '2016-08-02 19:35:58', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('1', '7');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户信息表ID',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `gender` char(1) DEFAULT NULL COMMENT '最近一次登录时间',
  `birthday` varchar(45) DEFAULT NULL COMMENT '生日',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `head_img` varchar(45) DEFAULT NULL COMMENT '头像',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` char(6) DEFAULT NULL COMMENT '0正常：1：失效',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED COMMENT='系统用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin123', 'admin123', null, null, null, null, null, null, '1', '2016-05-19 16:12:38', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT ' 邮箱',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` smallint(6) DEFAULT NULL COMMENT '0: 正常 ；-1失效',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED COMMENT='用户基本信息表';

-- ----------------------------
--  Records of `sys_user_info`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_info` VALUES ('1', 'admin', '18610014566', 'zhanglin@testin.cn', '1', '2016-08-02 19:37:21', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
