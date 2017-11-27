/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : home_manager

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-11-27 18:08:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '类型 1 - 共同，2 - 竞争',
  `score` int(11) DEFAULT NULL COMMENT '分值',
  `created_by` int(11) DEFAULT NULL COMMENT '创建者',
  `priority` int(1) DEFAULT NULL COMMENT '优先级',
  `end_time` datetime DEFAULT NULL COMMENT '结束日期',
  `alarm_type` int(2) DEFAULT NULL COMMENT '提醒类型',
  `status` int(11) DEFAULT '1',
  `cron_expression` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `next_fire_time` datetime DEFAULT NULL,
  `amount` double(11,2) DEFAULT NULL COMMENT '数量',
  `unit` int(2) DEFAULT NULL COMMENT '单位',
  `measure_type` int(2) DEFAULT '1' COMMENT '度量标准 1-数字 2-开关',
  `startTime` datetime DEFAULT NULL,
  `delay_type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '吃水果', '每天一个水果', '2017-11-04 22:23:07', '1', '5', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-11-27 00:01:00', '1.00', '1', '1', '2017-11-27 16:53:39', '1');
INSERT INTO `task` VALUES ('2', '背单词', '每天40个单词', '2017-11-27 16:54:40', '1', '5', '2', '3', null, null, '1', '0 0 0 * * ?', '2017-11-27 16:55:50', '40.00', '1', '1', '2017-11-27 16:56:16', '1');
INSERT INTO `task` VALUES ('3', '跑步', '每周三次', '2017-11-27 17:00:37', '1', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,WED,SAT', '2017-11-27 18:05:17', '3000.00', '2', '1', '2017-11-27 17:01:45', '1');
INSERT INTO `task` VALUES ('4', '羽毛球', '每周两次', '2017-11-27 17:03:22', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * TUE,THU', '2017-11-27 18:05:20', null, null, '2', '2017-11-27 17:05:03', '1');
INSERT INTO `task` VALUES ('5', '拖地', '每周两次', '2017-11-27 17:06:50', '2', '3', '2', '3', null, null, '1', '0 0 0 ? * WED,SUN', '2017-11-27 18:05:24', null, null, '2', '2017-11-27 17:07:48', '1');
INSERT INTO `task` VALUES ('6', '周会', '每周日', '2017-11-27 17:08:19', '3', '4', '2', '2', null, null, '1', '0 0 0 ? * L', '2017-11-27 17:12:11', null, null, '2', '2017-11-27 17:11:51', '1');
INSERT INTO `task` VALUES ('7', '读书', '每天至少30分钟', '2017-11-27 17:13:43', '1', '6', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-11-27 18:05:27', '30.00', '3', '1', '2017-11-27 17:14:19', '1');
INSERT INTO `task` VALUES ('8', '做早餐', '每周两次', '2017-11-27 17:16:21', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-11-27 18:05:29', null, null, '2', '2017-11-27 17:17:17', '1');
INSERT INTO `task` VALUES ('9', '做正餐', '每周两次', '2017-11-27 17:19:37', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-11-27 18:05:34', null, null, '2', '2017-11-27 17:20:23', '1');
INSERT INTO `task` VALUES ('10', '大扫除', '每个月最后一个星期六', '2017-11-27 17:21:19', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * 6L', '2017-11-27 18:05:37', null, null, '2', '2017-11-27 17:27:02', '1');

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
