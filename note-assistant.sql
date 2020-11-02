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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `audio` */

insert  into `audio`(`a_id`,`a_url`,`sort`,`a_text`,`n_id`) values 
(13,'http://ybkuvs.bv/cvfnhmhgpg',55692328,'nostrud Lorem ipsum irure',27);

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `information` */

insert  into `information`(`i_id`,`phone`,`profile`,`country`,`province`,`city`,`photo_url`,`u_id`) values 
(13,'string','string','string','string','string','string',17),
(15,'admin',NULL,NULL,NULL,NULL,NULL,19),
(17,'root',NULL,NULL,NULL,NULL,NULL,21),
(19,'13411953715',NULL,NULL,NULL,NULL,NULL,23);

/*Table structure for table `note` */

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `n_id` int NOT NULL AUTO_INCREMENT COMMENT '笔记id',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '笔记名称',
  `favorite` tinyint(1) DEFAULT '0' COMMENT '是否收藏',
  `createtime` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '摘要',
  `u_id` int NOT NULL COMMENT '所属用户id',
  PRIMARY KEY (`n_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `note` */

insert  into `note`(`n_id`,`title`,`favorite`,`createtime`,`summary`,`u_id`) values 
(17,'test4',0,'2020-10-21 20:24:59.000000',NULL,17),
(18,'test5',1,'2020-10-17 20:25:13.000000',NULL,17),
(19,'test6',1,'2020-10-30 20:29:22.000000',NULL,17),
(20,'test7',0,'2020-10-30 20:31:04.000000',NULL,17),
(21,'test8',1,'2020-10-10 20:31:06.000000',NULL,17),
(22,'test9',0,'2020-10-14 20:31:08.000000',NULL,17),
(23,'test10',1,'2020-10-10 20:31:11.000000',NULL,17),
(24,'test11',0,'2020-10-06 20:31:19.000000',NULL,17),
(25,'test12',1,'2020-10-17 20:31:22.000000',NULL,17),
(26,'string01',0,'2020-10-30 20:51:04.915000',NULL,17),
(27,'string01',0,'2020-10-30 20:51:38.335000',NULL,17),
(28,'string01',0,'2020-10-30 20:52:03.875000',NULL,17),
(29,'string01',0,'2020-10-30 20:56:42.890000',NULL,17),
(30,'string01',0,'2020-10-30 21:06:30.981000',NULL,17),
(31,'string01',0,'2020-10-30 21:08:22.599000',NULL,17),
(32,'string01',1,'2020-10-30 21:11:39.239000',NULL,17),
(33,'string01',1,'2020-10-30 21:12:56.587000',NULL,17),
(34,'string01',1,'2020-10-30 21:13:02.279000',NULL,17);

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `picture` */

insert  into `picture`(`p_id`,`p_url`,`sort`,`p_text`,`n_id`) values 
(28,'http://iasl.in/ebrz',-41085601,'cillum occaecat tempor',27);

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `text` */

insert  into `text`(`t_id`,`sort`,`text`,`n_id`) values 
(19,80379381,'dolor labore et veniam ut',27);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`u_id`,`username`,`password`) values 
(17,'string','$2a$10$I5/NAH1Wzuciq.BGph4su.xJ2.mgHj1bfyTj07qiA4PbWlg4tc9/u'),
(19,'admin','$2a$10$iCkwozYCfet/zNSLi7G35ujQE8Lldaj7dYUWELETNUNrG4.3yYhbC'),
(21,'root','$2a$10$QEG96e/4BkIS1KvWf1mUMOudAmpgXVNbGcdrD6CXoHFgmc995onFO'),
(23,'13411953715','$2a$10$.xv3ayH6kyXvtvKYshYd9OkmFYP/Uw8AKU5vVsDB9oPx15/ou1I4.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
