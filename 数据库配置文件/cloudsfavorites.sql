/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : cloudsfavorites

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-01 20:57:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bookmart
-- ----------------------------
DROP TABLE IF EXISTS `bookmart`;
CREATE TABLE `bookmart` (
  `userName` varchar(16) NOT NULL,
  `classification` varchar(20) NOT NULL,
  `websiteName` varchar(20) NOT NULL,
  `website` varchar(30) NOT NULL,
  `isRemove` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookmart
-- ----------------------------
INSERT INTO `bookmart` VALUES ('cindy', '搜索引擎', '百度搜索', 'https://www.baidu.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '搜索引擎', '谷歌搜索', 'https://www.google.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '种子搜索', 'BTSEARCH', 'http://www.btsearchs.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '种子搜索', 'BTBook', 'http://www.btbook.net/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发论坛', 'CSDN', 'http://www.csdn.net/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发论坛', '博客园', 'http://www.cnblogs.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资源', 'MSDN', 'http://www.itellyou.cn/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资源', '开源中国', 'http://www.oschina.net/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资源', '苹果在线', 'http://www.chinamac.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资源', 'html5资源教程', 'http://www.html5tricks.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资讯', '伯乐在线', 'www.jobbole.com', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资讯', 'live直播', 'https://www.livecoding.tv/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资讯', 'IT之家', 'http://www.ithome.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资讯', '极客公园', 'http://www.geekpark.net/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '开发资讯', '远景在线', 'http://www.pcbeta.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '电影视频', '电影天堂', 'http://www.dy2018.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '电影视频', 'LOL电影天堂', 'http://www.loldytt.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '电影视频', '韩剧网', 'http://www.hanjucc.com/hanju/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '电影视频', 'Youtube', 'https://www.youtube.com/', '\0');
INSERT INTO `bookmart` VALUES ('cindy', '电影视频', 'tokyo-hot', 'http://www.tokyo-hot.com/index', '\0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `level` int(10) unsigned zerofill NOT NULL,
  `experience` double unsigned zerofill NOT NULL,
  `userName` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `isManager` bit(1) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000000005', '0000000000000000000180', 'cindy', '123456', '\0');
INSERT INTO `user` VALUES ('0000000060', '0000000000000000100000', 'leafspace', '123456', '');
