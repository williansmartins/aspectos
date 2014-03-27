/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : exercicioaop

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2014-03-27 01:21:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `compras`
-- ----------------------------
DROP TABLE IF EXISTS `compras`;
CREATE TABLE `compras` (
  `numero` int(10) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `reserva_id` int(11) NOT NULL,
  `itens_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of compras
-- ----------------------------
INSERT INTO `compras` VALUES ('1', '2014-03-26', '1', '1', '1');
INSERT INTO `compras` VALUES ('2', '2013-02-20', '2', '2', '2');
INSERT INTO `compras` VALUES ('3', '2014-03-26', '3', '3', '3');
INSERT INTO `compras` VALUES ('4', '2013-02-20', '4', '4', '4');
INSERT INTO `compras` VALUES ('5', '2014-03-26', '5', '5', '5');
INSERT INTO `compras` VALUES ('6', '2013-02-20', '6', '6', '6');
INSERT INTO `compras` VALUES ('7', '2013-02-20', '7', '7', '7');
