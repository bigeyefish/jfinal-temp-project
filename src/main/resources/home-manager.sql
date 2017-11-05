/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : home-manager

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2017-11-05 22:54:45
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
  `code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `job_task_id` (`task_id`),
  KEY `job_user_id` (`user_id`),
  CONSTRAINT `job_task_id` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('6', '1_2017-11-05', '1', '1', '2017-11-05 22:44:05', '1', '吃水果', '2');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1 - 个人，2 - 家庭共同，3 - 家庭竞争',
  `score` int(11) DEFAULT NULL COMMENT '分值',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `priority` int(1) DEFAULT NULL COMMENT '优先级',
  `end_time` datetime DEFAULT NULL COMMENT '结束日期',
  `alarm_type` int(2) DEFAULT NULL COMMENT '提醒类型',
  `status` int(11) DEFAULT '1',
  `cron_expression` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `next_fire_time` datetime DEFAULT NULL,
  `executor` int(11) DEFAULT NULL COMMENT '任务执行单位（个人或者家庭id）根据type',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '吃水果', '每天一个水果', '2017-11-04 22:23:07', '2', '5', null, '1', null, null, '1', '0 * * * * ?', '2017-11-05 22:45:00', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `nick_name` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `family_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_family_id` (`family_id`),
  CONSTRAINT `user_family_id` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', null, '2017-11-04 16:27:57', null);
INSERT INTO `user` VALUES ('2', 'wangshiyu', '土豆爸爸', 'wangshiyu', '30', null, '1');
INSERT INTO `user` VALUES ('3', 'qinting', '土豆妈妈', 'qinting', '30', null, '1');
INSERT INTO `user` VALUES ('4', 'wanghaiyu', '土豆', 'wanghaiyu', '4', null, '1');
