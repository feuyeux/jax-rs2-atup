DROP TABLE IF EXISTS `atup_user`;

CREATE TABLE `atup_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jaxrs2_atup`.`atup_user`(`user_id`,`user_name`,`user_pwd`,`user_role`,`user_status`)
VALUES (1,'atupAdmin','650b81ee61374ef758f6e6b8ab68426e',1,0),
(2,'atupJobKiller','650b81ee61374ef758f6e6b8ab68426e',2,0),
(3,'atupDeviceKeeper','650b81ee61374ef758f6e6b8ab68426e',3,0),
(4,'xer','faa709c5035aea00f9efb278f2ad5df0',4,0);