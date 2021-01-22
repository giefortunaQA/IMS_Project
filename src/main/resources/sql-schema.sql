drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
	`cid` INT(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(40) DEFAULT NULL,
	`surname` VARCHAR(40) DEFAULT NULL,
	`house_number` INT(10) NOT NULL,
	`postcode` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`cid`)
	);