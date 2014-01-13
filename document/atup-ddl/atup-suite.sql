CREATE TABLE `test_suite` (
  `suite_id` int(11) NOT NULL AUTO_INCREMENT,
  `suite_name` varchar(255) DEFAULT NULL,
  `suite_status` int(11) DEFAULT NULL,
  `suite_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`suite_id`),
  UNIQUE KEY `suite_id` (`suite_id`),
  UNIQUE KEY `suite_name` (`suite_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jaxrs2_atup`.`test_suite`(`suite_id`,`suite_name`,`suite_status`,`suite_type`)
VALUES (1,'Performance Test',1,2),
(2,'GUI Test',1,1),
(3,'Unit Test',1,1),
(4,'Integration Test',1,1),
(5,'System Test',1,1);