CREATE TABLE `atup_device` (
  `device_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_host` varchar(255) DEFAULT NULL,
  `device_name` varchar(255) DEFAULT NULL,
  `device_type` int(11) DEFAULT NULL,
  `device_status` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `device_id` (`device_id`),
  UNIQUE KEY `device_host` (`device_host`),
  UNIQUE KEY `device_name` (`device_name`),
  KEY `FK_atup_device_userId` (`userId`),
  CONSTRAINT `FK_atup_device_userId` FOREIGN KEY (`userId`) REFERENCES `atup_user` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;