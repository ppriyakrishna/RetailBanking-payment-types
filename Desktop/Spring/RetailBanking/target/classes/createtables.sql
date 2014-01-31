delimiter $$

CREATE DATABASE `cs548_retailbanking` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

CREATE TABLE `account` (
  `account_Id` int(11) NOT NULL AUTO_INCREMENT,
  `account_Name` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `branch_Id` int(11) DEFAULT NULL,
  `branch_Name` varchar(45) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`account_Id`),
  UNIQUE KEY `account_Id_UNIQUE` (`account_Id`),
  UNIQUE KEY `account_Name_UNIQUE` (`account_Name`),
  KEY `account_branch_Id_idx` (`branch_Id`),
  KEY `account_user_Id_idx` (`user_Id`),
  CONSTRAINT `account_branch_Id` FOREIGN KEY (`branch_Id`) REFERENCES `branch` (`branch_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `account_user_Id` FOREIGN KEY (`user_Id`) REFERENCES `user` (`user_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10226 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `bankdetails` (
  `bank_Id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_Name` varchar(45) DEFAULT NULL,
  `country_Name` varchar(45) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bank_Id`),
  UNIQUE KEY `bank_Id_UNIQUE` (`bank_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `branch` (
  `branch_Id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_Name` varchar(45) DEFAULT NULL,
  `state_Name` varchar(45) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  `bank_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`branch_Id`),
  UNIQUE KEY `branch_Id_UNIQUE` (`branch_Id`),
  KEY `branch_bank_Id_idx` (`bank_Id`),
  CONSTRAINT `branch_bank_id` FOREIGN KEY (`bank_Id`) REFERENCES `bankdetails` (`bank_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `paymenttype` (
  `type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `type_Code` varchar(45) DEFAULT NULL,
  `type_Name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `user` (
  `user_Id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_Id`),
  UNIQUE KEY `user_Id_UNIQUE` (`user_Id`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8$$






