CREATE TABLE `test_case` (
  `case_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_name` varchar(255) DEFAULT NULL,
  `case_body` varchar(255) DEFAULT NULL,
  `case_status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `suiteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`case_id`),
  UNIQUE KEY `case_id` (`case_id`),
  UNIQUE KEY `case_name` (`case_name`),
  KEY `FK_test_case_suiteId` (`suiteId`),
  CONSTRAINT `FK_test_case_suiteId` FOREIGN KEY (`suiteId`) REFERENCES `test_suite` (`suite_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;