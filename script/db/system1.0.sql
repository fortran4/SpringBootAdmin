/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.20 : Database - springbootadmin
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springbootadmin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springbootadmin`;

/*Table structure for table `sys_dict` */

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

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`label`,`value`,`dict_type`,`description`,`sort`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (10,'有效','1','isValid','是否有效',1,NULL,'2016-06-07 15:34:52',NULL,NULL,'','0');

/*Table structure for table `sys_log` */

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
  PRIMARY KEY (`log_id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_create_date` (`create_date`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`log_id`,`type`,`create_by`,`create_date`,`remote_addr`,`user_agent`,`request_uri`,`method`,`params`,`exception`,`response`) values (1,'1',1,'2016-05-23 13:06:49','test_remote_addr','user_agent','test_request_uri','post','test',NULL,NULL),(2,'1',1,'2016-05-23 17:02:18','testset','testset','setttest','get','test',NULL,NULL),(3,'1',NULL,'2016-06-02 10:24:14','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36','http://localhost/login','login','[admin, admin]',NULL,'SysUser{id=1, loginName=\'admin\', loginPwd=\'admin\', createDate=2016-05-19 16:12:38.0, createBy=1, status=0}'),(4,'1',NULL,'2016-06-02 10:27:34','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36','http://localhost/sysLog/findAll','findByCreateDateBetween','[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]',NULL,'Page 0 of 0 containing UNKNOWN instances'),(5,'1',NULL,'2016-06-02 10:27:34','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36','http://localhost/sysLog/findAll','findByCreateDateBetween','[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]',NULL,'Page 0 of 0 containing UNKNOWN instances'),(6,'1',NULL,'2016-06-02 10:27:37','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36','http://localhost/sysLog/findAll','findByCreateDateBetween','[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]',NULL,'Page 0 of 0 containing UNKNOWN instances'),(7,'1',NULL,'2016-06-02 10:27:37','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36','http://localhost/sysLog/findAll','findByCreateDateBetween','[Page request [number: 0, size 10, sort: id: DESC], SysLog{id=null, type=\'null\', remoteAddr=\'null\', userAgent=\'null\', requestUri=\'null\', method=\'null\', createDate=null, params=\'null\'}]',NULL,'Page 0 of 0 containing UNKNOWN instances');

/*Table structure for table `sys_menu` */

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
  `status` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`menu_id`),
  KEY `sys_menu_del_flag` (`status`),
  KEY `sys_menu_parent_id` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`level`,`menu_name`,`href`,`target`,`icon`,`sort`,`is_show`,`permission`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`status`) values (1,0,NULL,'顶级菜单',NULL,NULL,NULL,1,'1',NULL,NULL,'2016-06-06 13:15:55',NULL,NULL,NULL,0),(3,1,NULL,'系统管理',NULL,NULL,NULL,2,'1',NULL,NULL,'2016-06-06 13:16:29',NULL,NULL,NULL,0),(4,3,NULL,'日志管理','/sysLog/findAll',NULL,NULL,3,'1',NULL,NULL,'2016-06-06 13:17:12',NULL,NULL,NULL,0),(5,3,NULL,'菜单管理','sysMenu/find','mainFrame',NULL,2,'1','',NULL,'2016-06-07 13:32:18',NULL,NULL,'',0),(6,3,NULL,'数据字典管理','sysDict/find','mainFrame',NULL,2,'1','',NULL,'2016-06-07 13:35:36',NULL,NULL,'',0),(7,3,NULL,'用户管理','/user/findAll','mainFrame',NULL,0,NULL,'',NULL,'2016-08-05 12:48:01',NULL,NULL,NULL,0);

/*Table structure for table `sys_role` */

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

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`status`) values (1,'系统管理员',1,'2016-08-02 19:35:58',NULL,NULL,NULL,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,7);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户信息表ID',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最近一次登录IP',
  `last_login_date` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` smallint(6) DEFAULT NULL COMMENT '0正常：-1：失效',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_id`,`login_name`,`login_pwd`,`last_login_ip`,`last_login_date`,`create_by`,`create_date`,`status`) values (1,1,'admin123','admin123',NULL,NULL,1,'2016-05-19 16:12:38',0);

/*Table structure for table `sys_user_info` */

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

/*Data for the table `sys_user_info` */

insert  into `sys_user_info`(`user_id`,`real_name`,`phone`,`email`,`create_by`,`create_date`,`status`) values (1,'admin','18610014566','zhanglin@testin.cn',1,'2016-08-02 19:37:21',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
