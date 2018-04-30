/*
Navicat MySQL Data Transfer

Source Server         : mysql on 192.168.50.104
Source Server Version : 50622
Source Host           : 192.168.50.104:3306
Source Database       : OnLineOrderingSystem

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-04-30 12:13:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `id` varchar(100) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
