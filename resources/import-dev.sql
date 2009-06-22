SET FOREIGN_KEY_CHECKS=0;
SET NAMES utf8;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

CREATE DATABASE IF NOT EXISTS `appstore` DEFAULT CHARACTER SET latin1;

USE `appstore`;

DROP TABLE IF EXISTS `appstore_app`;
CREATE TABLE `appstore_app` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `appId` varchar(255) NOT NULL, `typeId` bigint(255) NOT NULL, `name` varchar(255) DEFAULT NULL, `description` text, `cost` float DEFAULT NULL, `currencyId` bigint(255) NOT NULL, `size` bigint(255), `ext` varchar(7) NOT NULL, `source` longblob NOT NULL, PRIMARY KEY (`id`), KEY `typeId` (`typeId`), KEY `FK_app_currency_relationship_121` (`currencyId`), CONSTRAINT `FK_app_type_relationship` FOREIGN KEY (`typeId`) REFERENCES `appstore_app_type` (`id`), CONSTRAINT `FK_app_currency_relationship` FOREIGN KEY (`currencyId`) REFERENCES `appstore_currency_code` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_app`(id,appId,typeId,name,description,cost,currencyId,source,size,ext) values (1,'testApp',1,'Test Application','This is a test app',10.99,1,NULL,0,'.zip'),(2,'testApp2',1,'Test Application2','This is a test2 app',10.95,1,NULL,0,'.zip');

DROP TABLE IF EXISTS `appstore_app_category_map`;
CREATE TABLE `appstore_app_category_map` ( `appId` bigint(255) NOT NULL, `categoryId` bigint(255) NOT NULL, PRIMARY KEY (`appId`,`categoryId`), KEY `FK_app_category_map_categoryMapper` (`categoryId`), CONSTRAINT `FK_app_category_map_categoryMapper` FOREIGN KEY (`categoryId`) REFERENCES `appstore_category` (`id`), CONSTRAINT `FK_app_category_map_appMapper` FOREIGN KEY (`appId`) REFERENCES `appstore_app` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_app_category_map`(appId,categoryId) values (1,1);

DROP TABLE IF EXISTS `appstore_app_platform_map`;
CREATE TABLE `appstore_app_platform_map` ( `appId` bigint(255) NOT NULL, `platformId` bigint(255) NOT NULL, PRIMARY KEY (`appId`,`platformId`), KEY `FK_app_platform_map_platformMapper_M2M` (`platformId`), CONSTRAINT `FK_app_platform_map_platformMapper` FOREIGN KEY (`platformId`) REFERENCES `appstore_platform` (`id`), CONSTRAINT `FK_app_platform_map_appMapper` FOREIGN KEY (`appId`) REFERENCES `appstore_app` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_app_platform_map`(appId,platformId) values (1,1);

DROP TABLE IF EXISTS `appstore_app_type`;
CREATE TABLE `appstore_app_type` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `platformId` bigint(255) NOT NULL, `name` varchar(255) NOT NULL, `description` text, PRIMARY KEY (`id`), KEY `FK_app_type_platform_relationship_121` (`platformId`), CONSTRAINT `FK_app_type_platform_relationship` FOREIGN KEY (`platformId`) REFERENCES `appstore_platform` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_app_type`(id,platformId,name,description) values (1,1,'HTML',NULL);

DROP TABLE IF EXISTS `appstore_category`;
CREATE TABLE `appstore_category` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `description` text, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_category`(id,name,description) values (1,'Test','This is a test category. Apps that belong to this category are strictly for testing purposes!'),(2,'IaaS','Infrastructure as a Service. Apps which belong to this category are designed for Infrastructure as a Service administration and/or offerings.'),(3,'PaaS','Platform as a Service. Apps which belong to this category are designed for Platform as a Service administration and/or offerings.'),(4,'SaaS','Software as a Service. Apps which belong to this category are designed for Software as a Service administration and/or offerings.');

DROP TABLE IF EXISTS `appstore_category_platform_map`;
CREATE TABLE `appstore_category_platform_map` ( `categoryId` bigint(255) NOT NULL, `platformId` bigint(255) NOT NULL, PRIMARY KEY (`categoryId`,`platformId`), KEY `FK_category_platform_map_platformMapper_M2M` (`platformId`), CONSTRAINT `FK_category_platform_map_platformMapper` FOREIGN KEY (`platformId`) REFERENCES `appstore_platform` (`id`), CONSTRAINT `FK_category_platform_map_categoryMapper` FOREIGN KEY (`categoryId`) REFERENCES `appstore_category` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_category_platform_map`(categoryId,platformId) values (1,1),(2,1),(3,1),(4,1);

DROP TABLE IF EXISTS `appstore_currency_code`;
CREATE TABLE `appstore_currency_code` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `code` varchar(3) NOT NULL, `symbol` varchar(7) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_currency_code`(id,code,symbol) values (1,'USD','&#36;'),(2,'EUR','&#8364;');

DROP TABLE IF EXISTS `appstore_platform`;
CREATE TABLE `appstore_platform` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `description` text NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_platform`(id,name,description) values (1,'jhosting','jHosting: The Enterprise Open Source Hosting Platform');

DROP TABLE IF EXISTS `appstore_user`;
CREATE TABLE `appstore_user` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `username` varchar(100) NOT NULL, `password` varchar(128) NOT NULL, `lastLogin` timestamp DEFAULT '0000-00-00 00:00:00', `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, `apiKey` varchar(64) DEFAULT NULL, `apiEnabled` tinyint(1) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_user`(id,username,password,lastLogin,created,apiKey,apiEnabled) values (1,'admin','ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff','2009-06-26 04:38:43','2009-06-10 04:38:29','123qwe',1),(2,'devuser','ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff','2009-06-26 04:38:43','2009-06-10 04:38:29','123qwe',1),(3,'stduser','ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff','2009-06-26 04:38:43','2009-06-10 04:38:29',NULL,0);

DROP TABLE IF EXISTS `appstore_user_app_map`;
CREATE TABLE `appstore_user_app_map` ( `appId` bigint(255) NOT NULL, `userId` bigint(255) NOT NULL, PRIMARY KEY (`appId`,`userId`), KEY `FK_user_app_map_userMapper_M2M` (`userId`), CONSTRAINT `FK_user_app_map_userMapper` FOREIGN KEY (`userId`) REFERENCES `appstore_user` (`id`), CONSTRAINT `FK_user_app_map_appMapper` FOREIGN KEY (`appId`) REFERENCES `appstore_app` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_user_app_map`(appId,userId) values (1,1);
insert  into `appstore_user_app_map`(appId,userId) values (2,2);

DROP TABLE IF EXISTS `appstore_user_platform_map`;
CREATE TABLE `appstore_user_platform_map` ( `platformId` bigint(255) NOT NULL, `userId` bigint(255) NOT NULL, PRIMARY KEY (`platformId`,`userId`), KEY `FK_user_platform_map_userMapper_M2M` (`userId`), CONSTRAINT `FK_user_platform_map_userMapper` FOREIGN KEY (`userId`) REFERENCES `appstore_user` (`id`), CONSTRAINT `FK_user_platform_map_platformMapper` FOREIGN KEY (`platformId`) REFERENCES `appstore_platform` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_user_platform_map`(platformId,userId) values (1,1);

DROP TABLE IF EXISTS `appstore_user_role`;
CREATE TABLE `appstore_user_role` ( `id` bigint(255) NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `description` text, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_user_role`(id,name,description) values (1,'admin','This role is an administrative account. Users who belong to this role have unrestricted access to the appstore.'),(2,'developer','This role is a developer account. Users who belong to this role are allowed to download and upload apps.'),(3,'user','This role is a standard user account. Users who belong to this role are allowed to download apps.');

DROP TABLE IF EXISTS `appstore_user_role_map`;
CREATE TABLE `appstore_user_role_map` ( `userId` bigint(255) NOT NULL, `roleId` bigint(255) NOT NULL, PRIMARY KEY (`userId`,`roleId`), KEY `FK_appstore_user_role_map_roleMapper` (`roleId`), CONSTRAINT `FK_appstore_user_role_map_roleMapper` FOREIGN KEY (`roleId`) REFERENCES `appstore_user_role` (`id`), CONSTRAINT `FK_appstore_user_role_map_userMapper` FOREIGN KEY (`userId`) REFERENCES `appstore_user` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
insert  into `appstore_user_role_map`(userId,roleId) values (1,1);
insert  into `appstore_user_role_map`(userId,roleId) values (2,2);
insert  into `appstore_user_role_map`(userId,roleId) values (3,3);

SET FOREIGN_KEY_CHECKS=1;