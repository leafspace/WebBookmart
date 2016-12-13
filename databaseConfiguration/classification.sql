/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50624
Source Host           : 192.168.1.128:3306
Source Database       : cloudsfavorites

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-12 22:53:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
  `username` varchar(16) NOT NULL,
  `classification` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES ('cindy', '搜索引擎');
INSERT INTO `classification` VALUES ('cindy', '种子搜索');
INSERT INTO `classification` VALUES ('cindy', '开发论坛');
INSERT INTO `classification` VALUES ('cindy', '开发资源');
INSERT INTO `classification` VALUES ('cindy', '开发资讯');
INSERT INTO `classification` VALUES ('cindy', '电影视频');
