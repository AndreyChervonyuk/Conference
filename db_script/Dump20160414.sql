-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: conference
-- ------------------------------------------------------
-- Server version	5.5.48-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  `reply_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9aq5p2jgf17y6b38x5ayd90oc` (`author_id`),
  KEY `FK_5qo83tosgx0p9ayujl1y87502` (`reply_id`),
  CONSTRAINT `FK_5qo83tosgx0p9ayujl1y87502` FOREIGN KEY (`reply_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK_9aq5p2jgf17y6b38x5ayd90oc` FOREIGN KEY (`author_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (13,'2016-04-14','Comment 1','andrewchervonyuk@gmail.com',NULL),(14,'2016-04-14','Comment 2','andrewchervonyuk@gmail.com',NULL),(15,'2016-04-14','reply 1','1@1',13),(16,'2016-04-14','reply 2','1@1',14);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `load_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `document_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_esb799ihho7ek44mndbsvmyit` (`user_id`),
  CONSTRAINT `FK_esb799ihho7ek44mndbsvmyit` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (21,'2016-04-14 09:27:19','Kursova_PRIS.docx','\\user\\andrewchervonyuk@gmail.com\\Kursova_PRIS.docx','andrewchervonyuk@gmail.com',NULL),(26,'2016-04-14 10:12:32','text.txt','\\user\\andrewchervonyuk@gmail.com\\text.txt','andrewchervonyuk@gmail.com',NULL);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `information` varchar(2550) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `poster_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gcwj9ni1erqlwoj1f0ua4ha2s` (`creator_id`),
  CONSTRAINT `FK_gcwj9ni1erqlwoj1f0ua4ha2s` FOREIGN KEY (`creator_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (18,'address','2016-03-17 01:06:00','\r\n	AndroidDevCon2016 — это онлайн конференция для Android разработчиков. Целью нашей конференции является собрать профессионалов в сфере разработки под Android для обмена опытом и обсуждения актуальных вопросов. ','2016-01-24','<p>Спикеры и темы докладов:</p>\r\n<p>Сергей Комлач — Senior Android Developer с опытом разработки более восьми лет.</p>\r\nТема уточняется.\r\nДмитрий Данылык — опытный Android Developer, автор технических статей и open source библиотек, спикер на многочисленных конференция','AndroidDevCon2016 — free online conference for Android Developers','2016-01-24',0,'andrewchervonyuk@gmail.com','\\user\\andrewchervonyuk@gmail.com\\3.jpg');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_document`
--

DROP TABLE IF EXISTS `event_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_document` (
  `event_id` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL,
  PRIMARY KEY (`document_id`,`event_id`),
  KEY `FK_replxw2ksc8q3cgbhuan8pge3` (`event_id`),
  CONSTRAINT `FK_6cdbib02f1jlv3kkgqq3cspoh` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FK_replxw2ksc8q3cgbhuan8pge3` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_document`
--

LOCK TABLES `event_document` WRITE;
/*!40000 ALTER TABLE `event_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_permission`
--

DROP TABLE IF EXISTS `event_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p285dpgcs9y81onpwl37wnl75` (`event_id`),
  KEY `FK_d4k8qxfg8nb6ro4f1xtdly8wf` (`permission_id`),
  KEY `FK_muqj102vjvswpmkud4hyx5gcx` (`user_group_id`),
  CONSTRAINT `FK_d4k8qxfg8nb6ro4f1xtdly8wf` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FK_muqj102vjvswpmkud4hyx5gcx` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`),
  CONSTRAINT `FK_p285dpgcs9y81onpwl37wnl75` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_permission`
--

LOCK TABLES `event_permission` WRITE;
/*!40000 ALTER TABLE `event_permission` DISABLE KEYS */;
INSERT INTO `event_permission` VALUES (2,18,2,1),(3,18,3,3),(5,18,5,1),(9,18,9,1),(10,18,10,1),(11,18,11,1),(12,18,12,3),(13,18,13,1),(14,18,14,1),(15,18,15,1);
/*!40000 ALTER TABLE `event_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `load_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ro4jg03wemsdnjdxuhlubdcy4` (`event_id`),
  KEY `FK_2sk286iqotk0tmjjmurw9pmof` (`user_email`),
  CONSTRAINT `FK_2sk286iqotk0tmjjmurw9pmof` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`),
  CONSTRAINT `FK_ro4jg03wemsdnjdxuhlubdcy4` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (10,'\\user\\andrewchervonyuk@gmail.com\\1.jpg',NULL,NULL,'andrewchervonyuk@gmail.com','2016-04-14 09:21:01','1.jpg'),(11,'\\user\\andrewchervonyuk@gmail.com\\3.jpg',NULL,NULL,'andrewchervonyuk@gmail.com','2016-04-14 09:21:01','3.jpg'),(12,'\\user\\andrewchervonyuk@gmail.com\\2.jpg',NULL,NULL,'andrewchervonyuk@gmail.com','2016-04-14 09:23:29','2.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h3p4pldyfhuhxdrxkaknsy4st` (`event_id`),
  KEY `FK_a9bw6sk85ykh4bacjpu0ju5f6` (`user_id`),
  KEY `FK_2vdh437h5wdmauf32c03hck37` (`user_group_id`),
  CONSTRAINT `FK_2vdh437h5wdmauf32c03hck37` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`),
  CONSTRAINT `FK_a9bw6sk85ykh4bacjpu0ju5f6` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`),
  CONSTRAINT `FK_h3p4pldyfhuhxdrxkaknsy4st` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,18,'andrewchervonyuk@gmail.com',3),(21,18,'andrew_19@ukr.net',1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h7jb8oc5l01h4dtt73yerbo4t` (`author_id`),
  KEY `FK_q252wdsuinb5otylpc758hp8p` (`event_id`),
  CONSTRAINT `FK_h7jb8oc5l01h4dtt73yerbo4t` FOREIGN KEY (`author_id`) REFERENCES `user` (`email`),
  CONSTRAINT `FK_q252wdsuinb5otylpc758hp8p` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (19,'2016-04-14','Notification 1 ','Some text','andrewchervonyuk@gmail.com',18);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_document`
--

DROP TABLE IF EXISTS `notification_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `document_id` int(11) NOT NULL,
  `notification_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_plx3ds7q3j1djhi6ueskxs061` (`document_id`),
  KEY `FK_iersb52ibvs2hbq7l1ta4mx1u` (`notification_id`),
  CONSTRAINT `FK_iersb52ibvs2hbq7l1ta4mx1u` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FK_plx3ds7q3j1djhi6ueskxs061` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_document`
--

LOCK TABLES `notification_document` WRITE;
/*!40000 ALTER TABLE `notification_document` DISABLE KEYS */;
INSERT INTO `notification_document` VALUES (25,NULL,21,19);
/*!40000 ALTER TABLE `notification_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications_comment`
--

DROP TABLE IF EXISTS `notifications_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tctwphe76r8nrva11ndb4hde2` (`comment_id`),
  KEY `FK_7b36a74apyf8btiqolwk2wlqc` (`notification_id`),
  CONSTRAINT `FK_7b36a74apyf8btiqolwk2wlqc` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FK_tctwphe76r8nrva11ndb4hde2` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications_comment`
--

LOCK TABLES `notifications_comment` WRITE;
/*!40000 ALTER TABLE `notifications_comment` DISABLE KEYS */;
INSERT INTO `notifications_comment` VALUES (7,13,19),(8,14,19);
/*!40000 ALTER TABLE `notifications_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9myy1bvburr5vxgout2br7q8f` (`category_id`),
  CONSTRAINT `FK_9myy1bvburr5vxgout2br7q8f` FOREIGN KEY (`category_id`) REFERENCES `permission_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (2,'Create notification','create_notification',4),(3,'Edit members','edit_members',2),(5,'Comment notification','create_notification_comments',4),(9,'View group documents','view_documents',1),(10,'View list of members','view_members',2),(11,'View notification comments','view_notification_comments',4),(12,'Edit permission','edit_permission',3),(13,'View event permission','view_permission',3),(14,'Send email','send_email',4),(15,'View notification','view_notifications',4);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_category`
--

DROP TABLE IF EXISTS `permission_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_category`
--

LOCK TABLES `permission_category` WRITE;
/*!40000 ALTER TABLE `permission_category` DISABLE KEYS */;
INSERT INTO `permission_category` VALUES (1,'Documents'),(2,'Members'),(3,'Permissions'),(4,'Notifications');
/*!40000 ALTER TABLE `permission_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `birth_day` date DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1@1','1899-11-30','','test','1','user'),('andrewchervonyuk@gmail.com','1899-11-30','','Andrey','1','Chervonyuk'),('andrew_19@ukr.net','1899-11-30','','user','1','2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,'MEMBERS'),(2,'NOT_MEMBERS'),(3,'GROUP_ADMINS'),(4,'WAIT');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s21d8k5lywjjc7inw14brj6ro` (`role`),
  KEY `FK_swg6cfg9nesh7ytuhbr4vhe7a` (`email`),
  CONSTRAINT `FK_s21d8k5lywjjc7inw14brj6ro` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_swg6cfg9nesh7ytuhbr4vhe7a` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,'andrewchervonyuk@gmail.com'),(4,1,'andrew_19@ukr.net'),(5,1,'1@1');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-14 17:01:34
