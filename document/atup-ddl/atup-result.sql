CREATE DATABASE IF NOT EXISTS `jaxrs2_atup`;
USE  `jaxrs2_atup`;

DROP TABLE IF EXISTS `test_result`;
CREATE TABLE `test_result` (
  `result_id` int(11) NOT NULL AUTO_INCREMENT,
  `result_body` varchar(255) DEFAULT NULL,
  `result_status` int(11) DEFAULT NULL,
  `caseId` int(11) DEFAULT NULL,
  `deviceId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`result_id`),
  UNIQUE KEY `result_id` (`result_id`),
  KEY `FK_test_result_caseId` (`caseId`),
  KEY `FK_test_result_userId` (`userId`),
  KEY `FK_test_result_deviceId` (`deviceId`),
  CONSTRAINT `FK_test_result_caseId` FOREIGN KEY (`caseId`) REFERENCES `test_case` (`case_id`),
  CONSTRAINT `FK_test_result_deviceId` FOREIGN KEY (`deviceId`) REFERENCES `atup_device` (`device_id`),
  CONSTRAINT `FK_test_result_userId` FOREIGN KEY (`userId`) REFERENCES `atup_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;