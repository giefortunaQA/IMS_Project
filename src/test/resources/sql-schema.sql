drop table orders_items;
drop table orders;
drop table items;
drop table customers;

CREATE TABLE IF NOT EXISTS `customers` (
	`cid` INT(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(40) DEFAULT NULL,
	`surname` VARCHAR(40) DEFAULT NULL,
	`house_number` INT(10) NOT NULL,
	`postcode` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`cid`)
	);
	
CREATE TABLE IF NOT EXISTS `items` (
	`iid` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL,
	`price` DEC(5,2) NOT NULL,
	PRIMARY KEY (`iid`)
	);

CREATE TABLE IF NOT EXISTS `orders` (
	`oid` INT(10) NOT NULL AUTO_INCREMENT,
	`fk_cid` INT(10) NOT NULL,
	`order_value` DOUBLE,
	PRIMARY KEY (`oid`),
	FOREIGN KEY (`fk_cid`) references customers(`cid`) ON UPDATE CASCADE ON DELETE CASCADE
	);
	
CREATE TABLE IF NOT EXISTS `orders_items` (
	`fk_oid` INT(10) NOT NULL,
	`fk_iid` INT(10) NOT NULL,
	FOREIGN KEY (`fk_oid`) references orders(`oid`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`fk_iid`) references items(`iid`) ON UPDATE CASCADE ON DELETE CASCADE
	);