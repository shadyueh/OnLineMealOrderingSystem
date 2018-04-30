/*
Navicat MySQL Data Transfer

Source Server         : mysql on 192.168.50.104
Source Server Version : 50622
Source Host           : 192.168.50.104:3306
Source Database       : OnLineOrderingSystem

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-04-30 12:13:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_dish
-- ----------------------------
DROP TABLE IF EXISTS `order_dish`;
CREATE TABLE `order_dish` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `dish_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`dish_id`),
  KEY `dish_id` (`dish_id`),
  CONSTRAINT `order_dish_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_dish_ibfk_2` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
