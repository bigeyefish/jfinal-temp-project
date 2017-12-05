/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : home_manager

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-12-05 18:08:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `family`
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `id` int(11) NOT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of family
-- ----------------------------
INSERT INTO `family` VALUES ('1', '土豆家庭');

-- ----------------------------
-- Table structure for `job`
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号 - 用来防止同一天生成相同的job',
  `task_id` int(11) NOT NULL COMMENT 'task主键',
  `user_id` int(11) NOT NULL COMMENT '执行用户id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '状态 1 - 进行中， 2 - 已完成， 3 - 已过期， 4 - 待抢，5 - 已下线',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `updated_by` int(11) DEFAULT NULL,
  `plan_amount` double(11,2) DEFAULT NULL COMMENT '计划完成数量（数字量类型需要）',
  `finish_amount` double(11,2) DEFAULT NULL COMMENT '完成数量（数字量类型需要）',
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `job_task_id` (`task_id`),
  KEY `job_user_id` (`user_id`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('6', '1_2017-11-05', '1', '1', '2017-11-05 22:44:05', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('7', '1_2017-11-18', '1', '2', '2017-11-18 22:25:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('8', '1_2017-11-18', '1', '3', '2017-11-18 22:25:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('9', '1_2017-11-18', '1', '4', '2017-11-18 22:25:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('10', '1_2017-11-19', '1', '2', '2017-11-19 08:58:11', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('11', '1_2017-11-19', '1', '3', '2017-11-19 08:58:11', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('12', '1_2017-11-19', '1', '4', '2017-11-19 08:58:11', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('13', '1_2017-11-20', '1', '2', '2017-11-20 21:38:08', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('14', '1_2017-11-20', '1', '3', '2017-11-20 21:38:08', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('15', '1_2017-11-20', '1', '4', '2017-11-20 21:38:08', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('16', '1_2017-11-21', '1', '2', '2017-11-21 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('17', '1_2017-11-21', '1', '3', '2017-11-21 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('18', '1_2017-11-21', '1', '4', '2017-11-21 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('19', '1_2017-11-27', '1', '2', '2017-11-27 00:00:12', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('20', '1_2017-11-27', '1', '3', '2017-11-27 00:00:12', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('21', '1_2017-11-27', '1', '4', '2017-11-27 00:00:12', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('22', '4_2017-11-28', '4', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('23', '2_2017-11-28', '2', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('24', '7_2017-11-28', '7', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('25', '1_2017-11-28', '1', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('26', '9_2017-11-28', '9', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('27', '8_2017-11-28', '8', '2', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('28', '9_2017-11-28', '9', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('29', '2_2017-11-28', '2', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('30', '4_2017-11-28', '4', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('31', '1_2017-11-28', '1', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('32', '7_2017-11-28', '7', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('33', '8_2017-11-28', '8', '3', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('34', '1_2017-11-28', '1', '4', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('35', '7_2017-11-28', '7', '4', '2017-11-28 10:03:47', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('36', '3_2017-11-29', '3', '2', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('37', '5_2017-11-29', '5', '2', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('38', '2_2017-11-29', '2', '2', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('39', '7_2017-11-29', '7', '2', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('40', '1_2017-11-29', '1', '2', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('41', '5_2017-11-29', '5', '3', '2017-11-29 00:00:00', '2', null, null, null, null, null);
INSERT INTO `job` VALUES ('42', '3_2017-11-29', '3', '3', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('43', '2_2017-11-29', '2', '3', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('44', '1_2017-11-29', '1', '3', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('45', '7_2017-11-29', '7', '3', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('46', '7_2017-11-29', '7', '4', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('47', '3_2017-11-29', '3', '4', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('48', '1_2017-11-29', '1', '4', '2017-11-29 00:00:00', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('49', '9_2017-11-29', '9', '2', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('50', '8_2017-11-29', '8', '2', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('51', '4_2017-11-29', '4', '2', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('52', '9_2017-11-29', '9', '3', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('53', '8_2017-11-29', '8', '3', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('54', '4_2017-11-29', '4', '3', '2017-11-29 17:24:40', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('55', '11_2017-11-29', '11', '2', '2017-11-29 18:10:54', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('56', '11_2017-11-29', '11', '3', '2017-11-29 18:10:54', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('57', '11_2017-11-29', '11', '4', '2017-11-29 18:10:54', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('147', '9_2017-12-05', '9', '2', '2017-12-05 16:06:24', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('148', '8_2017-12-05', '8', '2', '2017-12-05 16:06:24', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('149', '3_2017-12-05', '3', '2', '2017-12-05 16:06:24', '1', null, null, '3000.00', null, null);
INSERT INTO `job` VALUES ('150', '1_2017-12-05', '1', '2', '2017-12-05 16:06:24', '1', null, null, '1.00', null, null);
INSERT INTO `job` VALUES ('151', '4_2017-12-05', '4', '2', '2017-12-05 16:06:24', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('152', '6_2017-12-05', '6', '2', '2017-12-05 16:06:24', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('153', '9_2017-12-05', '9', '3', '2017-12-05 16:06:24', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('154', '6_2017-12-05', '6', '3', '2017-12-05 16:06:24', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('155', '2_2017-12-05', '2', '2', '2017-12-05 16:06:24', '1', null, null, '40.00', null, null);
INSERT INTO `job` VALUES ('156', '7_2017-12-05', '7', '2', '2017-12-05 16:06:24', '1', null, null, '30.00', null, null);
INSERT INTO `job` VALUES ('157', '6_2017-12-05', '6', '4', '2017-12-05 16:06:24', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('158', '4_2017-12-05', '4', '3', '2017-12-05 16:06:24', '1', null, null, null, null, null);
INSERT INTO `job` VALUES ('159', '7_2017-12-05', '7', '3', '2017-12-05 16:06:24', '1', null, null, '30.00', null, null);
INSERT INTO `job` VALUES ('160', '8_2017-12-05', '8', '3', '2017-12-05 16:06:24', '4', null, null, null, null, null);
INSERT INTO `job` VALUES ('161', '2_2017-12-05', '2', '3', '2017-12-05 16:06:24', '1', null, null, '40.00', null, null);
INSERT INTO `job` VALUES ('162', '7_2017-12-05', '7', '4', '2017-12-05 16:06:24', '1', null, null, '30.00', null, null);
INSERT INTO `job` VALUES ('163', '1_2017-12-05', '1', '3', '2017-12-05 16:06:24', '1', null, null, '1.00', null, null);
INSERT INTO `job` VALUES ('164', '3_2017-12-05', '3', '3', '2017-12-05 16:06:24', '1', null, null, '3000.00', null, null);
INSERT INTO `job` VALUES ('165', '3_2017-12-05', '3', '4', '2017-12-05 16:06:24', '1', null, null, '3000.00', null, null);
INSERT INTO `job` VALUES ('166', '1_2017-12-05', '1', '4', '2017-12-05 16:06:24', '1', null, null, '1.00', null, null);

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限名称',
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '直播管理', '0', '1', '2017-07-19 14:18:28', '2017-08-30 10:58:35');
INSERT INTO `permission` VALUES ('2', 'app首页管理', '0', '0', '2017-07-19 14:18:28', '2017-07-19 14:18:29');
INSERT INTO `permission` VALUES ('3', '用户管理', '0', '1', '2017-07-19 14:18:28', '2017-08-01 20:20:33');
INSERT INTO `permission` VALUES ('5', '数据统计2', '0', '5', '2017-07-19 14:18:28', '2017-07-20 19:50:08');
INSERT INTO `permission` VALUES ('14', '权限管理', '1', '1', '2017-08-01 20:07:07', '2017-08-01 20:28:58');

-- ----------------------------
-- Table structure for `permission_resource`
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource`;
CREATE TABLE `permission_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_resource_oper_id` (`permission_id`,`resource_id`) USING BTREE,
  KEY `permission_id` (`permission_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `permission_resource_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1038 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限对应资源及操作表';

-- ----------------------------
-- Records of permission_resource
-- ----------------------------
INSERT INTO `permission_resource` VALUES ('337', '2', '4', '2', '2', '2017-07-28 09:19:05', '2017-07-28 09:19:05');
INSERT INTO `permission_resource` VALUES ('339', '2', '6', '2', '2', '2017-07-28 09:19:05', '2017-07-28 09:19:05');
INSERT INTO `permission_resource` VALUES ('341', '2', '18', '2', '2', '2017-07-28 09:19:05', '2017-07-28 09:19:05');
INSERT INTO `permission_resource` VALUES ('645', '1', '6', '1', '1', '2017-08-30 10:58:35', '2017-08-30 10:58:35');
INSERT INTO `permission_resource` VALUES ('647', '1', '13', '1', '1', '2017-08-30 10:58:35', '2017-08-30 10:58:35');
INSERT INTO `permission_resource` VALUES ('649', '1', '15', '1', '1', '2017-08-30 10:58:35', '2017-08-30 10:58:35');
INSERT INTO `permission_resource` VALUES ('650', '1', '16', '1', '1', '2017-08-30 10:58:35', '2017-08-30 10:58:35');
INSERT INTO `permission_resource` VALUES ('651', '1', '17', '1', '1', '2017-08-30 10:58:35', '2017-08-30 10:58:35');
INSERT INTO `permission_resource` VALUES ('888', '3', '18', '5', '5', '2017-09-06 19:45:44', '2017-09-06 19:45:44');
INSERT INTO `permission_resource` VALUES ('889', '3', '75', '5', '5', '2017-09-06 19:45:44', '2017-09-06 19:45:44');
INSERT INTO `permission_resource` VALUES ('980', '14', '4', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('981', '14', '6', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('983', '14', '13', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('985', '14', '15', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('986', '14', '16', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('987', '14', '17', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('988', '14', '18', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('992', '14', '47', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('993', '14', '50', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('994', '14', '52', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('995', '14', '73', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('996', '14', '74', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('997', '14', '75', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('998', '14', '76', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1000', '14', '81', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1001', '14', '82', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1002', '14', '84', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1003', '14', '85', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1004', '14', '86', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1005', '14', '87', '26', '26', '2017-09-18 14:08:46', '2017-09-18 14:08:46');
INSERT INTO `permission_resource` VALUES ('1037', '5', '6', '1', '1', '2017-09-26 14:43:55', '2017-09-26 14:43:55');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '资源编号',
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '资源名称',
  `url` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '访问路径',
  `icon` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标',
  `component` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组件',
  `seq` tinyint(2) DEFAULT '1' COMMENT '菜单或资源显示顺序',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点（菜单）',
  `type` tinyint(2) DEFAULT NULL COMMENT '资源类型（1-菜单 2-数据 3-按钮 4-文件）',
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  `isactive` tinyint(1) DEFAULT '1' COMMENT '是否有效（0-无效 1-有效）',
  `visible` tinyint(1) DEFAULT '1' COMMENT '1可见，2不可见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('4', '0_', '组合管理', '/portfolio', 'zonghe', 'layout/Layout', '2', '0', '1', '0', '1', '2017-07-19 15:36:02', '2017-11-18 12:09:12', '1', '1');
INSERT INTO `resource` VALUES ('6', 'btn_zhibi_check', '直播审核按钮', '/tg_search', '', null, '1', '0', '2', '0', '5', '2017-07-19 15:38:29', '2017-11-20 21:52:49', '1', '1');
INSERT INTO `resource` VALUES ('13', '0_', '权限管理', '/permission', 'quanxian', 'layout/Layout', '7', '0', '1', '0', '3', '2017-07-24 21:24:26', '2017-11-18 12:10:17', '1', '1');
INSERT INTO `resource` VALUES ('15', '0_13_', '角色管理', 'role', null, 'permission/RoleView', '3', '13', '1', '0', '5', '2017-07-24 21:25:31', '2017-11-18 12:10:31', '1', '1');
INSERT INTO `resource` VALUES ('16', '0_13_', '权限管理', 'permissionmgr', null, 'permission/PermissionView', '3', '13', '1', '0', '2', '2017-07-24 21:25:31', '2017-11-18 12:10:53', '1', '1');
INSERT INTO `resource` VALUES ('17', '0_13_', '菜单管理', 'menu', null, 'permission/MenuView', '4', '13', '1', '0', '1', '2017-07-24 21:25:31', '2017-11-18 12:10:56', '1', '1');
INSERT INTO `resource` VALUES ('18', 'btn_add_role', '增加角色按钮', '/role/add', null, null, '2', '0', '2', '2', '2', '2017-07-26 15:40:00', '2017-08-01 16:08:35', '1', '1');
INSERT INTO `resource` VALUES ('47', 'data_rolelist', '角色列表', '/api/role/list', null, null, '3', '0', '3', '2', '1', '2017-07-28 09:34:36', '2017-07-31 14:48:26', '1', '1');
INSERT INTO `resource` VALUES ('49', 'getResourceByType', 'getByType', '/api/resource/getListByType/:2/:1/:10', null, null, '1', '0', '4', '1', '1', '2017-07-28 17:59:38', '2017-08-02 11:03:45', '1', '1');
INSERT INTO `resource` VALUES ('50', '0_13_', '按钮管理', 'buttons', null, 'permission/ButtonView', '5', '13', '1', '1', '1', '2017-07-31 10:41:23', '2017-11-18 12:11:01', '1', '1');
INSERT INTO `resource` VALUES ('52', '0_13_', '数据管理', 'data', null, 'permission/DataView', '6', '13', '1', '1', '5', '2017-07-31 10:55:00', '2017-11-18 12:11:09', '1', '1');
INSERT INTO `resource` VALUES ('57', 'rolelist', 'role list data', '/api/role/list/:page/:size', null, null, '1', '0', '4', '1', '1', '2017-07-31 13:59:07', '2017-08-01 17:07:56', '1', '1');
INSERT INTO `resource` VALUES ('68', 'userlist', '用户列表数据', '/api/user/list/:page/:size', null, null, '5', '0', '4', '1', '1', '2017-08-01 10:45:44', '2017-08-02 14:36:43', '1', '1');
INSERT INTO `resource` VALUES ('71', 'login', '登录', '/api/user/login', null, null, '1', '0', '4', '1', '1', '2017-08-01 10:48:53', '2017-08-01 15:52:36', '1', '1');
INSERT INTO `resource` VALUES ('73', 'btn_role_save', '角色保存按钮', null, null, null, '1', '0', '2', '2', '2', '2017-08-01 15:59:21', '2017-08-01 15:59:21', '1', '1');
INSERT INTO `resource` VALUES ('74', 'btn_role_edit', '角色编辑按钮', null, null, null, '1', '0', '2', '2', '2', '2017-08-01 16:07:41', '2017-08-01 16:07:41', '1', '1');
INSERT INTO `resource` VALUES ('75', 'btn_user_add', '用户添加按钮', null, null, null, '1', '0', '2', '2', '2', '2017-08-01 16:07:59', '2017-08-01 16:07:59', '1', '1');
INSERT INTO `resource` VALUES ('76', 'btn_update_pass', '修改密码', null, null, null, '1', '0', '2', '2', '2', '2017-08-01 16:49:21', '2017-08-01 16:49:21', '1', '1');
INSERT INTO `resource` VALUES ('77', 'data_login', '登录请求', '/api/user/login', null, null, '1', '0', '4', '2', '2', '2017-08-01 17:06:31', '2017-08-01 17:06:31', '1', '1');
INSERT INTO `resource` VALUES ('81', 'data_resource_list', '分类获取资源列表', '/api/resource/getListByType/:type/:page/:size', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:05:15', '2017-08-01 20:31:08', '1', '1');
INSERT INTO `resource` VALUES ('82', 'data_resource_save', '保存资源', '/api/resource/save', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:09:44', '2017-08-01 20:09:44', '1', '1');
INSERT INTO `resource` VALUES ('84', 'data_permission_list', '权限列表接口', '/api/permission/list/:page/:size', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:27:51', '2017-09-27 17:29:07', '1', '1');
INSERT INTO `resource` VALUES ('85', 'data_resource_all', '获取总资源列表接口', '/api/resource/typeList', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:31:48', '2017-08-01 20:38:18', '1', '1');
INSERT INTO `resource` VALUES ('86', 'data_role_save', '角色保存接口', '/api/role/save', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:33:18', '2017-08-01 20:33:18', '1', '1');
INSERT INTO `resource` VALUES ('87', 'data_permission_save', '权限保存接口', '/api/permission/save', null, null, '1', '0', '3', '1', '1', '2017-08-01 20:38:09', '2017-08-01 20:38:09', '1', '1');
INSERT INTO `resource` VALUES ('88', 'data_user_remove', '删除用户', '/api/user/remove/:userId', null, null, '1', '0', '4', '1', '1', '2017-08-02 15:38:16', '2017-08-02 15:38:16', '1', '1');
INSERT INTO `resource` VALUES ('89', 'data_user_saveRole', '保存用户角色', '/api/user/saveUserRole/:userId', null, null, '1', '0', '4', '1', '1', '2017-08-03 09:45:28', '2017-08-03 09:45:28', '1', '1');
INSERT INTO `resource` VALUES ('90', 'save_or_update_user', '保存和跟新用户', '/api/user/save', null, null, '1', '0', '4', '1', '1', '2017-08-03 09:45:28', '2017-08-03 14:13:10', '1', '1');
INSERT INTO `resource` VALUES ('93', 'change_pwd', '修改密码', 'changePwd', null, null, '1', '0', '4', '5', '5', '2017-08-31 15:12:10', '2017-08-31 15:12:10', '1', '1');
INSERT INTO `resource` VALUES ('153', '0_4_', '组合列表', 'list', null, 'Blank', '1', '4', '1', '1', '1', '2017-09-23 15:12:39', '2017-11-18 12:12:11', '1', '1');
INSERT INTO `resource` VALUES ('154', '0_4_153', '', '', null, 'portfolio/portfolioList', '1', '153', '1', '1', '1', '2017-09-23 15:24:50', '2017-11-18 12:12:24', '1', '0');
INSERT INTO `resource` VALUES ('156', '0_4_153', '组合详情', ':id', null, 'portfolio/portfolioDetail', '2', '153', '1', '1', '1', '2017-10-10 16:25:27', '2017-11-18 12:12:25', '1', '0');
INSERT INTO `resource` VALUES ('161', '0_', '任务管理', '/task', 'zonghe', 'layout/Layout', '1', '0', '1', '1', '1', '2017-11-20 21:52:26', '2017-11-20 21:53:17', '1', '1');
INSERT INTO `resource` VALUES ('162', '0_161_', '任务列表', 'list', null, 'Blank', '1', '161', '1', '1', '1', '2017-11-20 21:54:16', '2017-11-20 21:54:16', '1', '1');
INSERT INTO `resource` VALUES ('163', '0_161_', ' ', '', null, 'task/list', '1', '162', '1', '1', '1', '2017-11-20 21:56:16', '2017-11-20 22:26:55', '1', '0');
INSERT INTO `resource` VALUES ('164', '0_161_', '我的job', 'job_list', null, 'task/jobList', '1', '161', '1', '1', '1', '2017-12-05 15:33:19', '2017-12-05 15:33:21', '1', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '0', '5', '2017-07-18 09:40:10', '2017-08-03 13:54:28');
INSERT INTO `role` VALUES ('3', '投顾', '0', '0', '2017-07-18 09:41:34', '2017-07-18 09:41:35');
INSERT INTO `role` VALUES ('4', '投顾助理', '0', '5', '2017-07-18 09:41:54', '2017-08-03 13:54:36');
INSERT INTO `role` VALUES ('6', 'test2', '2', '2', '2017-07-28 15:14:43', '2017-07-28 15:14:43');
INSERT INTO `role` VALUES ('7', 'test3', '2', '2', '2017-07-28 15:14:52', '2017-07-28 15:14:52');
INSERT INTO `role` VALUES ('8', 'test4', '2', '2', '2017-07-28 15:14:58', '2017-07-28 15:14:58');
INSERT INTO `role` VALUES ('9', 'test5', '2', '2', '2017-07-28 15:15:05', '2017-07-28 15:15:05');
INSERT INTO `role` VALUES ('10', 'test7', '2', '2', '2017-07-28 15:15:11', '2017-07-31 14:52:17');
INSERT INTO `role` VALUES ('12', 'test8', '2', '2', '2017-07-28 15:15:27', '2017-07-28 15:15:27');
INSERT INTO `role` VALUES ('13', 'test8', '2', '2', '2017-07-28 15:15:35', '2017-07-28 15:15:35');
INSERT INTO `role` VALUES ('14', 'sdfsfsf', '2', '2', '2017-07-31 10:23:14', '2017-07-31 10:23:14');
INSERT INTO `role` VALUES ('15', '管理员', '2', '2', '2017-07-31 14:48:45', '2017-07-31 14:48:45');
INSERT INTO `role` VALUES ('16', '2222', '5', '5', '2017-08-03 10:48:35', '2017-08-03 18:10:46');
INSERT INTO `role` VALUES ('17', '&lt;h1>', '2', '2', '2017-08-03 18:00:51', '2017-08-03 18:00:51');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_permission_id` (`role_id`,`permission_id`) USING BTREE,
  KEY `permission_id` (`permission_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色权限中间表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('85', '3', '2', '5', '5', '2017-07-25 20:03:51', '2017-07-27 10:30:22');
INSERT INTO `role_permission` VALUES ('88', '3', '5', '5', '5', '2017-07-25 20:03:51', '2017-07-27 10:30:45');
INSERT INTO `role_permission` VALUES ('94', '15', '1', '2', '2', '2017-07-31 14:48:45', '2017-07-31 14:48:45');
INSERT INTO `role_permission` VALUES ('95', '15', '2', '2', '2', '2017-07-31 14:48:45', '2017-07-31 14:48:45');
INSERT INTO `role_permission` VALUES ('96', '15', '3', '2', '2', '2017-07-31 14:48:45', '2017-07-31 14:48:45');
INSERT INTO `role_permission` VALUES ('183', '4', '2', '5', '5', '2017-08-03 13:54:36', '2017-08-03 13:54:36');
INSERT INTO `role_permission` VALUES ('184', '4', '3', '5', '5', '2017-08-03 13:54:36', '2017-08-03 13:54:36');
INSERT INTO `role_permission` VALUES ('185', '4', '5', '5', '5', '2017-08-03 13:54:36', '2017-08-03 13:54:36');
INSERT INTO `role_permission` VALUES ('186', '1', '1', '5', '5', '2017-09-06 19:45:36', '2017-09-06 19:45:36');
INSERT INTO `role_permission` VALUES ('187', '1', '2', '5', '5', '2017-09-06 19:45:36', '2017-09-06 19:45:36');
INSERT INTO `role_permission` VALUES ('188', '1', '3', '5', '5', '2017-09-06 19:45:36', '2017-09-06 19:45:36');
INSERT INTO `role_permission` VALUES ('189', '1', '5', '5', '5', '2017-09-06 19:45:36', '2017-09-06 19:45:36');
INSERT INTO `role_permission` VALUES ('190', '1', '14', '5', '5', '2017-09-06 19:45:36', '2017-09-06 19:45:36');
INSERT INTO `role_permission` VALUES ('191', '6', '1', '1', '1', '2017-09-27 17:29:33', '2017-09-27 17:29:33');

-- ----------------------------
-- Table structure for `score_flow`
-- ----------------------------
DROP TABLE IF EXISTS `score_flow`;
CREATE TABLE `score_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `score` double(11,2) NOT NULL COMMENT '交易分数',
  `created_time` datetime NOT NULL COMMENT '时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `job_id` int(11) NOT NULL COMMENT 'job 主键',
  `before_score` double(11,2) NOT NULL COMMENT '交易前分值',
  `after_score` double(11,2) NOT NULL COMMENT '交易后分值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分值流水';

-- ----------------------------
-- Records of score_flow
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `type` int(2) NOT NULL COMMENT '类型 1 - 个人，2 - 竞争，3 - 共同，4 - 轮流',
  `score` int(11) DEFAULT NULL COMMENT '分值',
  `created_by` int(11) DEFAULT NULL COMMENT '创建者',
  `priority` int(1) NOT NULL COMMENT '优先级 1 - 紧急重要，2 - 紧急不重要， 3 - 重要不激进， 4 - 不重要不紧急',
  `end_time` datetime DEFAULT NULL COMMENT '结束日期',
  `alarm_type` int(2) DEFAULT NULL COMMENT '提醒类型',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效 1 - 有效，0 - 无效（下架）',
  `cron_expression` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '触发频率表达式',
  `next_fire_time` datetime DEFAULT NULL COMMENT '下次触发时间',
  `amount` double(11,2) DEFAULT NULL COMMENT '数量',
  `unit` int(2) DEFAULT NULL COMMENT '单位',
  `measure_type` int(2) NOT NULL DEFAULT '1' COMMENT '度量标准 1 - 数字，2 - 开关',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `expire_type` int(2) NOT NULL COMMENT '过期类型 1 - 本日有效，2 - 本周有效，3 - 7天有效， 4 - 本月有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '吃水果', '每天吃一个水果', '2017-11-04 22:23:07', '1', '5', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-12-06 00:00:00', '1.00', '1', '1', '2017-11-27 00:00:00', '1');
INSERT INTO `task` VALUES ('2', '背单词', '每天40个单词', '2017-11-27 16:54:40', '1', '5', '2', '3', null, null, '1', '0 0 0 * * ?', '2017-12-06 00:00:00', '40.00', '1', '1', '2017-11-27 16:56:16', '1');
INSERT INTO `task` VALUES ('3', '跑步', '每周三次', '2017-11-27 17:00:37', '1', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,WED,SAT', '2017-11-27 00:00:00', '3000.00', '2', '1', '2017-11-27 00:00:00', '4');
INSERT INTO `task` VALUES ('4', '羽毛球', '每周两次', '2017-11-27 17:03:22', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * TUE,THU', '2017-11-28 00:00:00', null, null, '2', '2017-11-27 00:00:00', '2');
INSERT INTO `task` VALUES ('5', '拖地', '每周两次', '2017-11-27 17:06:50', '4', '3', '2', '3', null, null, '1', '0 0 0 ? * WED,SUN', '2017-12-06 00:00:00', null, null, '2', '2017-11-27 00:00:00', '1');
INSERT INTO `task` VALUES ('6', '周会', '每周日', '2017-11-27 17:08:19', '3', '4', '2', '2', null, null, '1', '0 0 0 ? * L', '2017-12-02 00:00:00', null, null, '2', '2017-11-27 00:00:00', '3');
INSERT INTO `task` VALUES ('7', '读书', '每天至少30分钟', '2017-11-27 17:13:43', '1', '6', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-12-06 00:00:00', '30.00', '3', '1', '2017-11-27 17:14:19', '1');
INSERT INTO `task` VALUES ('8', '做早餐', '每周两次', '2017-11-27 17:16:21', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-12-11 00:00:00', null, null, '2', '2017-11-27 17:17:17', '1');
INSERT INTO `task` VALUES ('9', '做正餐', '每周两次', '2017-11-27 17:19:37', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-12-11 00:00:00', null, null, '2', '2017-11-27 17:20:23', '1');
INSERT INTO `task` VALUES ('10', '大扫除', '每个月最后一个星期六', '2017-11-27 17:21:19', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * 6L', '2017-12-29 00:00:00', null, null, '2', '2017-11-27 00:00:00', '4');
INSERT INTO `task` VALUES ('11', '刷牙', '每天要刷牙', '2017-11-29 18:07:59', '1', '0', '2', '1', null, null, '0', '0 0 0 * * ?', '2017-11-29 00:00:00', '0.00', '1', '1', '2017-11-29 00:00:00', '1');

-- ----------------------------
-- Table structure for `task_user`
-- ----------------------------
DROP TABLE IF EXISTS `task_user`;
CREATE TABLE `task_user` (
  `task_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`task_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of task_user
-- ----------------------------
INSERT INTO `task_user` VALUES ('1', '2');
INSERT INTO `task_user` VALUES ('1', '3');
INSERT INTO `task_user` VALUES ('1', '4');
INSERT INTO `task_user` VALUES ('2', '2');
INSERT INTO `task_user` VALUES ('2', '3');
INSERT INTO `task_user` VALUES ('3', '2');
INSERT INTO `task_user` VALUES ('3', '3');
INSERT INTO `task_user` VALUES ('3', '4');
INSERT INTO `task_user` VALUES ('4', '2');
INSERT INTO `task_user` VALUES ('4', '3');
INSERT INTO `task_user` VALUES ('5', '2');
INSERT INTO `task_user` VALUES ('5', '3');
INSERT INTO `task_user` VALUES ('6', '2');
INSERT INTO `task_user` VALUES ('6', '3');
INSERT INTO `task_user` VALUES ('6', '4');
INSERT INTO `task_user` VALUES ('7', '2');
INSERT INTO `task_user` VALUES ('7', '3');
INSERT INTO `task_user` VALUES ('7', '4');
INSERT INTO `task_user` VALUES ('8', '2');
INSERT INTO `task_user` VALUES ('8', '3');
INSERT INTO `task_user` VALUES ('9', '2');
INSERT INTO `task_user` VALUES ('9', '3');
INSERT INTO `task_user` VALUES ('10', '2');
INSERT INTO `task_user` VALUES ('10', '3');
INSERT INTO `task_user` VALUES ('10', '4');
INSERT INTO `task_user` VALUES ('11', '2');
INSERT INTO `task_user` VALUES ('11', '3');
INSERT INTO `task_user` VALUES ('11', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `family_id` int(11) DEFAULT NULL COMMENT '家庭编号',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `mobile` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `isactive` tinyint(1) DEFAULT '1' COMMENT '是否激活状态 0 - 未激活 1 - 激活',
  `issuper` tinyint(1) DEFAULT '0' COMMENT '是否超级管理员 0 - 不是， 1 - 是',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by` int(11) DEFAULT NULL COMMENT '修改者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `token` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录凭证',
  `score` double(11,0) NOT NULL DEFAULT '0' COMMENT '账户总分数',
  PRIMARY KEY (`id`),
  KEY `user_family_id` (`family_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '915d46552c671efa1eb7753a090eb10', null, '2017-11-14 00:01:37', null, '1', 'http://7xr387.com1.z0.glb.clouddn.com/little_rabbit.gif', null, null, '1', '1', null, null, null, null, '', '0');
INSERT INTO `user` VALUES ('2', 'wangshiyu', '土豆爸爸', '959ba35982e3f5aacfd1ac32a71213de', '30', '2017-11-28 15:11:18', '1', null, 'http://7xr387.com1.z0.glb.clouddn.com/little_rabbit.gif', null, null, '1', '1', null, null, null, null, '3tMLYrVL5nisdn1RChk6SmF3+JwbQqXwkC3VYzpfq4s=', '0');
INSERT INTO `user` VALUES ('3', 'qinting', '土豆妈妈', '959ba35982e3f5aacfd1ac32a71213de', '30', null, '1', null, 'http://7xr387.com1.z0.glb.clouddn.com/little_rabbit.gif', null, null, '1', '1', null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('4', 'wanghaiyu', '土豆', '959ba35982e3f5aacfd1ac32a71213de', '4', null, '1', null, 'http://7xr387.com1.z0.glb.clouddn.com/little_rabbit.gif', null, null, '1', '1', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `creator_id` int(11) DEFAULT NULL,
  `updator_id` int(11) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_id` (`user_id`,`role_id`) USING BTREE,
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户角色中间表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('13', '4', '1', '0', '0', '2017-07-18 16:23:45', '2017-07-18 16:23:45');
INSERT INTO `user_role` VALUES ('14', '4', '4', '0', '0', '2017-07-18 16:23:45', '2017-07-18 16:23:45');
INSERT INTO `user_role` VALUES ('34', '3', '1', '0', '0', '2017-07-25 18:25:35', '2017-07-25 18:25:35');
INSERT INTO `user_role` VALUES ('38', '2', '1', '0', '0', '2017-07-25 18:35:04', '2017-07-25 18:35:04');
INSERT INTO `user_role` VALUES ('39', '3', '3', '0', '0', '2017-07-25 18:35:04', '2017-07-27 10:29:45');
INSERT INTO `user_role` VALUES ('40', '8', '4', '0', '0', '2017-07-25 18:35:04', '2017-07-27 10:30:01');
INSERT INTO `user_role` VALUES ('43', '6', '1', '1', '1', '2017-08-03 09:45:47', '2017-08-03 09:45:47');
INSERT INTO `user_role` VALUES ('45', '22', '1', '5', '5', '2017-08-31 14:53:23', '2017-08-31 14:53:23');
INSERT INTO `user_role` VALUES ('46', '26', '1', '1', '1', '2017-09-05 18:34:48', '2017-09-05 18:34:48');
INSERT INTO `user_role` VALUES ('47', '26', '3', '1', '1', '2017-09-05 18:34:48', '2017-09-05 18:34:48');
