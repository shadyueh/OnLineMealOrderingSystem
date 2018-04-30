/*
Navicat MySQL Data Transfer

Source Server         : mysql on 192.168.50.104
Source Server Version : 50622
Source Host           : 192.168.50.104:3306
Source Database       : OnLineOrderingSystem

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-04-30 12:13:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` varchar(100) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
