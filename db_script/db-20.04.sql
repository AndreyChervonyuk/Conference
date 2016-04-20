-- --------------------------------------------------------
-- Сервер:                       127.0.0.1
-- Версія сервера:               5.6.24-log - MySQL Community Server (GPL)
-- ОС сервера:                   Win64
-- HeidiSQL Версія:              9.2.0.4950
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for conference
CREATE DATABASE IF NOT EXISTS `conference` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `conference`;


-- Dumping structure for таблиця conference.comment
DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.comment: ~0 rows (приблизно)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `date`, `text`, `author_id`, `reply_id`) VALUES
	(1, '2016-04-20', 'comment 1', 'andrewchervonyuk@gmail.com', NULL),
	(2, '2016-04-20', 'comment 2', 'andrewchervonyuk@gmail.com', NULL),
	(3, NULL, 'reply 1', 'andrew_19@ukr.net', 1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


-- Dumping structure for таблиця conference.default_event_permission
DROP TABLE IF EXISTS `default_event_permission`;
CREATE TABLE IF NOT EXISTS `default_event_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mit8okkf3xk100klk1q4gw4lj` (`permission_id`),
  KEY `FK_3bttqyisj37b3ivtgcs6gawod` (`user_group_id`),
  CONSTRAINT `FK_3bttqyisj37b3ivtgcs6gawod` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`),
  CONSTRAINT `FK_mit8okkf3xk100klk1q4gw4lj` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.default_event_permission: ~8 rows (приблизно)
/*!40000 ALTER TABLE `default_event_permission` DISABLE KEYS */;
INSERT INTO `default_event_permission` (`id`, `permission_id`, `user_group_id`) VALUES
	(1, 2, 1),
	(2, 3, 3),
	(3, 5, 1),
	(4, 9, 1),
	(5, 10, 1),
	(6, 11, 1),
	(7, 12, 3),
	(8, 13, 1),
	(9, 14, 3),
	(10, 15, 1);
/*!40000 ALTER TABLE `default_event_permission` ENABLE KEYS */;


-- Dumping structure for таблиця conference.document
DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `load_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `document_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_esb799ihho7ek44mndbsvmyit` (`user_id`),
  CONSTRAINT `FK_esb799ihho7ek44mndbsvmyit` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.document: ~0 rows (приблизно)
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;


-- Dumping structure for таблиця conference.email
DROP TABLE IF EXISTS `email`;
CREATE TABLE IF NOT EXISTS `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sy65b8d4elhn4vbny4keduh9b` (`author_id`),
  CONSTRAINT `FK_sy65b8d4elhn4vbny4keduh9b` FOREIGN KEY (`author_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.email: ~0 rows (приблизно)
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
/*!40000 ALTER TABLE `email` ENABLE KEYS */;


-- Dumping structure for таблиця conference.event
DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.event: ~1 rows (приблизно)
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`id`, `address`, `create_date`, `description`, `finish_date`, `information`, `name`, `start_date`, `status`, `creator_id`, `poster_path`) VALUES
	(18, 'address', '2016-03-17 01:06:00', '\r\n	AndroidDevCon2016 — это онлайн конференция для Android разработчиков. Целью нашей конференции является собрать профессионалов в сфере разработки под Android для обмена опытом и обсуждения актуальных вопросов. ', '2016-01-24', '<p>Спикеры и темы докладов:</p>\r\n<p>Сергей Комлач — Senior Android Developer с опытом разработки более восьми лет.</p>\r\nТема уточняется.\r\nДмитрий Данылык — опытный Android Developer, автор технических статей и open source библиотек, спикер на многочисленных конференция', 'AndroidDevCon2016 — free online conference for Android Developers', '2016-01-24', 0, 'andrewchervonyuk@gmail.com', '\\user\\andrewchervonyuk@gmail.com\\3.jpg');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;


-- Dumping structure for таблиця conference.event_document
DROP TABLE IF EXISTS `event_document`;
CREATE TABLE IF NOT EXISTS `event_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cdbib02f1jlv3kkgqq3cspoh` (`document_id`),
  KEY `FK_replxw2ksc8q3cgbhuan8pge3` (`event_id`),
  CONSTRAINT `FK_6cdbib02f1jlv3kkgqq3cspoh` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FK_replxw2ksc8q3cgbhuan8pge3` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.event_document: ~0 rows (приблизно)
/*!40000 ALTER TABLE `event_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_document` ENABLE KEYS */;


-- Dumping structure for таблиця conference.event_permission
DROP TABLE IF EXISTS `event_permission`;
CREATE TABLE IF NOT EXISTS `event_permission` (
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.event_permission: ~10 rows (приблизно)
/*!40000 ALTER TABLE `event_permission` DISABLE KEYS */;
INSERT INTO `event_permission` (`id`, `event_id`, `permission_id`, `user_group_id`) VALUES
	(2, 18, 2, 1),
	(3, 18, 3, 3),
	(5, 18, 5, 1),
	(9, 18, 9, 1),
	(10, 18, 10, 1),
	(11, 18, 11, 1),
	(12, 18, 12, 3),
	(13, 18, 13, 1),
	(14, 18, 14, 1),
	(15, 18, 15, 1);
/*!40000 ALTER TABLE `event_permission` ENABLE KEYS */;


-- Dumping structure for таблиця conference.images
DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `load_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pqx83ec9hoknq2vfo4rtfspvq` (`user_email`),
  CONSTRAINT `FK_pqx83ec9hoknq2vfo4rtfspvq` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.images: ~0 rows (приблизно)
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;


-- Dumping structure for таблиця conference.member
DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.member: ~1 rows (приблизно)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `event_id`, `user_id`, `user_group_id`) VALUES
	(23, 18, 'andrewchervonyuk@gmail.com', 3);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- Dumping structure for таблиця conference.notification
DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.notification: ~1 rows (приблизно)
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`id`, `date`, `header`, `text`, `author_id`, `event_id`) VALUES
	(13, '2016-04-20', 'notification 1', 'Some text', 'andrewchervonyuk@gmail.com', 18);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;


-- Dumping structure for таблиця conference.notifications_comment
DROP TABLE IF EXISTS `notifications_comment`;
CREATE TABLE IF NOT EXISTS `notifications_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tctwphe76r8nrva11ndb4hde2` (`comment_id`),
  KEY `FK_7b36a74apyf8btiqolwk2wlqc` (`notification_id`),
  CONSTRAINT `FK_7b36a74apyf8btiqolwk2wlqc` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FK_tctwphe76r8nrva11ndb4hde2` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.notifications_comment: ~1 rows (приблизно)
/*!40000 ALTER TABLE `notifications_comment` DISABLE KEYS */;
INSERT INTO `notifications_comment` (`id`, `comment_id`, `notification_id`) VALUES
	(1, 1, 13),
	(2, 2, 13);
/*!40000 ALTER TABLE `notifications_comment` ENABLE KEYS */;


-- Dumping structure for таблиця conference.notification_document
DROP TABLE IF EXISTS `notification_document`;
CREATE TABLE IF NOT EXISTS `notification_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `notification_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_plx3ds7q3j1djhi6ueskxs061` (`document_id`),
  KEY `FK_iersb52ibvs2hbq7l1ta4mx1u` (`notification_id`),
  CONSTRAINT `FK_iersb52ibvs2hbq7l1ta4mx1u` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  CONSTRAINT `FK_plx3ds7q3j1djhi6ueskxs061` FOREIGN KEY (`document_id`) REFERENCES `event_document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.notification_document: ~0 rows (приблизно)
/*!40000 ALTER TABLE `notification_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_document` ENABLE KEYS */;


-- Dumping structure for таблиця conference.permission
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9myy1bvburr5vxgout2br7q8f` (`category_id`),
  CONSTRAINT `FK_9myy1bvburr5vxgout2br7q8f` FOREIGN KEY (`category_id`) REFERENCES `permission_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.permission: ~10 rows (приблизно)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `description`, `name`, `category_id`) VALUES
	(2, 'Create notification', 'create_notification', 4),
	(3, 'Edit members', 'edit_members', 2),
	(5, 'Comment notification', 'create_notification_comments', 4),
	(9, 'View group documents', 'view_documents', 1),
	(10, 'View list of members', 'view_members', 2),
	(11, 'View notification comments', 'view_notification_comments', 4),
	(12, 'Edit permission', 'edit_permission', 3),
	(13, 'View event permission', 'view_permission', 3),
	(14, 'Send email', 'send_email', 4),
	(15, 'View notification', 'view_notifications', 4);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- Dumping structure for таблиця conference.permission_category
DROP TABLE IF EXISTS `permission_category`;
CREATE TABLE IF NOT EXISTS `permission_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.permission_category: ~4 rows (приблизно)
/*!40000 ALTER TABLE `permission_category` DISABLE KEYS */;
INSERT INTO `permission_category` (`id`, `name`) VALUES
	(1, 'Documents'),
	(2, 'Members'),
	(3, 'Permissions'),
	(4, 'Notifications');
/*!40000 ALTER TABLE `permission_category` ENABLE KEYS */;


-- Dumping structure for таблиця conference.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.role: ~2 rows (приблизно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for таблиця conference.send_to
DROP TABLE IF EXISTS `send_to`;
CREATE TABLE IF NOT EXISTS `send_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rrnp54x58bbmpk63ekox1koky` (`email_id`),
  KEY `FK_a7tfhbbkgssj15d5b2vgx94yt` (`member_id`),
  CONSTRAINT `FK_a7tfhbbkgssj15d5b2vgx94yt` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_rrnp54x58bbmpk63ekox1koky` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.send_to: ~0 rows (приблизно)
/*!40000 ALTER TABLE `send_to` DISABLE KEYS */;
/*!40000 ALTER TABLE `send_to` ENABLE KEYS */;


-- Dumping structure for таблиця conference.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(255) NOT NULL,
  `birth_day` date DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table conference.user: ~3 rows (приблизно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`email`, `birth_day`, `enabled`, `name`, `password`, `surname`) VALUES
	('andrewchervonyuk@gmail.com', '1899-11-30', b'1', 'Andrey', '$2a$10$vX58V1xgMWbvtEs2oNrG.OOkIc7cwBaS/0v/ETaKT8kyQ/fqVCCTW', 'Chervonyuk'),
	('andrew_19@ukr.net', '1899-11-30', b'1', 'test', '$2a$10$gTwPIgVVlSkLl9hXBxrpBOojd4OQ5hMQWn0o8Cw/u.jiblXbG5N7S', 'user 2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for таблиця conference.user_group
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE IF NOT EXISTS `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.user_group: ~4 rows (приблизно)
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` (`id`, `name`) VALUES
	(1, 'MEMBERS'),
	(2, 'NOT_MEMBERS'),
	(3, 'GROUP_ADMINS'),
	(4, 'NOT_CONFIRMED');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


-- Dumping structure for таблиця conference.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s21d8k5lywjjc7inw14brj6ro` (`role`),
  KEY `FK_swg6cfg9nesh7ytuhbr4vhe7a` (`email`),
  CONSTRAINT `FK_s21d8k5lywjjc7inw14brj6ro` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_swg6cfg9nesh7ytuhbr4vhe7a` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table conference.user_role: ~3 rows (приблизно)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `role`, `email`) VALUES
	(8, 1, 'andrewchervonyuk@gmail.com'),
	(9, 1, 'andrew_19@ukr.net');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
