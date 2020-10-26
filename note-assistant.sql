/*
SQLyog Enterprise v12.14 (64 bit)
MySQL - 8.0.20 : Database - note_assistant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`note_assistant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `note_assistant`;

/*Table structure for table `audio` */

DROP TABLE IF EXISTS `audio`;

CREATE TABLE `audio` (
  `a_id` int NOT NULL AUTO_INCREMENT COMMENT '音频id',
  `a_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '音频地址',
  `sort` int DEFAULT NULL COMMENT '音频排序',
  `a_text` text COMMENT '音频对应的文本',
  `n_id` int DEFAULT NULL COMMENT '所属笔记id',
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `sort` (`sort`,`n_id`),
  KEY `n_id` (`n_id`),
  CONSTRAINT `audio_ibfk_1` FOREIGN KEY (`n_id`) REFERENCES `note` (`n_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `audio` */

insert  into `audio`(`a_id`,`a_url`,`sort`,`a_text`,`n_id`) values 
(7,'string',0,'string',14);

/*Table structure for table `information` */

DROP TABLE IF EXISTS `information`;

CREATE TABLE `information` (
  `i_id` int NOT NULL AUTO_INCREMENT COMMENT '信息id',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `profile` text COMMENT '个人简介',
  `country` varchar(15) DEFAULT NULL COMMENT '国家',
  `province` varchar(15) DEFAULT NULL COMMENT '省份',
  `city` varchar(15) DEFAULT NULL COMMENT '城市',
  `photo_url` varchar(32) DEFAULT NULL COMMENT '头像url',
  `u_id` int DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`i_id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `information_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `information` */

insert  into `information`(`i_id`,`phone`,`profile`,`country`,`province`,`city`,`photo_url`,`u_id`) values 
(13,'string','string','string','string','string','string',17),
(15,'admin',NULL,NULL,NULL,NULL,NULL,19);

/*Table structure for table `note` */

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `n_id` int NOT NULL AUTO_INCREMENT COMMENT '笔记id',
  `n_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '笔记名称',
  `createtime` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `u_id` int DEFAULT NULL COMMENT '所属用户id',
  PRIMARY KEY (`n_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `note` */

insert  into `note`(`n_id`,`n_name`,`createtime`,`u_id`) values 
(14,'string','1975-01-31 09:03:25.977000',17),
(15,'test2','2020-10-25 18:34:23.953000',17);

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `p_id` int NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `p_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片的url',
  `sort` int DEFAULT NULL COMMENT '图片的排序',
  `p_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '图片对应的文本',
  `n_id` int DEFAULT NULL COMMENT '图片所属笔记id',
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `sort` (`sort`,`n_id`),
  KEY `n_id` (`n_id`),
  CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`n_id`) REFERENCES `note` (`n_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `picture` */

/*Table structure for table `text` */

DROP TABLE IF EXISTS `text`;

CREATE TABLE `text` (
  `t_id` int NOT NULL AUTO_INCREMENT COMMENT '文本id',
  `sort` int DEFAULT NULL COMMENT '文本的排序',
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文本内容',
  `n_id` int DEFAULT NULL COMMENT '文本所属笔记id',
  PRIMARY KEY (`t_id`),
  UNIQUE KEY `sort` (`sort`,`n_id`),
  KEY `n_id` (`n_id`),
  CONSTRAINT `text_ibfk_1` FOREIGN KEY (`n_id`) REFERENCES `note` (`n_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `text` */

insert  into `text`(`t_id`,`sort`,`text`,`n_id`) values 
(9,0,'string',14),
(11,2,'string',14);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`u_id`,`username`,`password`) values 
(17,'string','$2a$10$vyB4w7kRb6kiB2l5bO9yTO3Wm2WfCmM0RSw8nno8uq3JvRKDDNeiW'),
(19,'admin','$2a$10$iCkwozYCfet/zNSLi7G35ujQE8Lldaj7dYUWELETNUNrG4.3yYhbC');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
