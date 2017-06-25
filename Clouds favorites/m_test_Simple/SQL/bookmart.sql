/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50624
Source Host           : 192.168.1.128:3306
Source Database       : temp

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-12 15:00:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bookmart
-- ----------------------------
DROP TABLE IF EXISTS `bookmart`;
CREATE TABLE `bookmart` (
  `username` varchar(255) NOT NULL,
  `classification` varchar(255) DEFAULT NULL,
  `bookmartName` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookmart
-- ----------------------------
INSERT INTO `bookmart` VALUES ('cindy', '电影', '1', '1');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '2', '2');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '3', '3');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '4', '4');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '5', '5');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '6', '6');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '7', '7');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '8', '8');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '9', '9');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '10', '10');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '11', '11');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '12', '12');
INSERT INTO `bookmart` VALUES ('cindy', '电影', '13', '13');
