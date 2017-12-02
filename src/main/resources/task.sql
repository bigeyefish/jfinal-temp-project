/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : home_manager

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-12-01 14:56:59
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `startTime` datetime NOT NULL COMMENT '开始时间',
  `expire_type` int(2) NOT NULL COMMENT '过期类型 1 - 本日有效，2 - 本周有效，3 - 7天有效， 4 - 本月有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '吃水果', '每天一个水果', '2017-11-04 22:23:07', '1', '5', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-11-28 00:00:00', '1.00', '1', '1', '2017-11-27 16:53:39', '1');
INSERT INTO `task` VALUES ('2', '背单词', '每天40个单词', '2017-11-27 16:54:40', '1', '5', '2', '3', null, null, '1', '0 0 0 * * ?', '2017-11-28 00:00:00', '40.00', '1', '1', '2017-11-27 16:56:16', '1');
INSERT INTO `task` VALUES ('3', '跑步', '每周三次', '2017-11-27 17:00:37', '1', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,WED,SAT', '2017-11-29 00:00:00', '3000.00', '2', '1', '2017-11-27 17:01:45', '1');
INSERT INTO `task` VALUES ('4', '羽毛球', '每周两次', '2017-11-27 17:03:22', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * TUE,THU', '2017-11-28 00:00:00', null, null, '2', '2017-11-27 17:05:03', '1');
INSERT INTO `task` VALUES ('5', '拖地', '每周两次', '2017-11-27 17:06:50', '2', '3', '2', '3', null, null, '1', '0 0 0 ? * WED,SUN', '2017-11-29 00:00:00', null, null, '2', '2017-11-27 17:07:48', '1');
INSERT INTO `task` VALUES ('6', '周会', '每周日', '2017-11-27 17:08:19', '3', '4', '2', '2', null, null, '1', '0 0 0 ? * L', '2017-12-02 00:00:00', null, null, '2', '2017-11-27 17:11:51', '1');
INSERT INTO `task` VALUES ('7', '读书', '每天至少30分钟', '2017-11-27 17:13:43', '1', '6', '2', '4', null, null, '1', '0 0 0 * * ?', '2017-11-28 00:00:00', '30.00', '3', '1', '2017-11-27 17:14:19', '1');
INSERT INTO `task` VALUES ('8', '做早餐', '每周两次', '2017-11-27 17:16:21', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-11-28 00:00:00', null, null, '2', '2017-11-27 17:17:17', '1');
INSERT INTO `task` VALUES ('9', '做正餐', '每周两次', '2017-11-27 17:19:37', '2', '6', '2', '2', null, null, '1', '0 0 0 ? * MON,TUE', '2017-11-28 00:00:00', null, null, '2', '2017-11-27 17:20:23', '1');
INSERT INTO `task` VALUES ('10', '大扫除', '每个月最后一个星期六', '2017-11-27 17:21:19', '3', '6', '2', '2', null, null, '1', '0 0 0 ? * 6L', '2017-12-29 00:00:00', null, null, '2', '2017-11-27 17:27:02', '1');
INSERT INTO `task` VALUES ('11', '刷牙', '每天要刷牙', '2017-11-29 18:07:59', '1', '0', '2', '1', null, null, '1', '0 0 0 * * ?', '2017-11-30 00:00:00', '0.00', '1', '1', '2017-11-29 00:00:00', '1');
