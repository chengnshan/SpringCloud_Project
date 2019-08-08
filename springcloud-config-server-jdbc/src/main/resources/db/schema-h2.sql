
DROP TABLE IF EXISTS `config_properties`;

CREATE TABLE config_properties (
  `id` BIGINT(20) NOT NULL ,
  `key` VARCHAR(50) DEFAULT NULL,
  `value` VARCHAR(500) DEFAULT NULL,
  `application` varchar(50) DEFAULT NULL,
  `profile` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `remark` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

/*
CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `application` varchar(50) DEFAULT NULL,
  `profile` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4*/;




