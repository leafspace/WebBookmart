/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50624
Source Host           : 192.168.1.128:3306
Source Database       : cloudsfavorites

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-12 22:53:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `level` int(10) unsigned zerofill NOT NULL,
  `experience` int(10) unsigned zerofill NOT NULL,
  `userName` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `isManager` bit(1) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000000010', '0000000550', 'cindy', '123456', '\0');
INSERT INTO `user` VALUES ('0000000060', '0000100000', 'leafspace', '123456', '');
