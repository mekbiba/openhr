-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.26-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema freehrms
--

CREATE DATABASE IF NOT EXISTS freehrms;
USE freehrms;

--
-- Temporary table structure for view `emp_benefit_view`
--
DROP TABLE IF EXISTS `emp_benefit_view`;
DROP VIEW IF EXISTS `emp_benefit_view`;
CREATE TABLE `emp_benefit_view` (
  `EMP_ID` varchar(45),
  `SALARY` double,
  `BENEFIT_AMNT` double,
  `BENEFIT_TYPE` varchar(45)
);

--
-- Temporary table structure for view `emp_payroll_view`
--
DROP TABLE IF EXISTS `emp_payroll_view`;
DROP VIEW IF EXISTS `emp_payroll_view`;
CREATE TABLE `emp_payroll_view` (
  `EMPLOYEEID` varchar(45),
  `FULL_NAME` varchar(137),
  `GROSS_SALARY` double,
  `BENEFIT_TYPE` varchar(8),
  `BENEFIT_AMNT` double
);

--
-- Definition of table `benefit`
--

DROP TABLE IF EXISTS `benefit`;
CREATE TABLE `benefit` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `typeId` int(10) unsigned NOT NULL,
  `amount` double NOT NULL,
  `employeeId` int(10) unsigned NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_benefit_type` (`typeId`),
  KEY `FK_benefit_employee` (`employeeId`),
  CONSTRAINT `FK_benefit_employee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_benefit_type` FOREIGN KEY (`typeId`) REFERENCES `benefitype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `benefit`
--

/*!40000 ALTER TABLE `benefit` DISABLE KEYS */;
INSERT INTO `benefit` (`id`,`typeId`,`amount`,`employeeId`,`version`) VALUES 
 (1,5,1500,3,1),
 (2,5,1000,2,1),
 (3,4,700,3,1),
 (4,4,1500,24,1);
/*!40000 ALTER TABLE `benefit` ENABLE KEYS */;


--
-- Definition of table `benefitype`
--

DROP TABLE IF EXISTS `benefitype`;
CREATE TABLE `benefitype` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `cap` double NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `benefitype`
--

/*!40000 ALTER TABLE `benefitype` DISABLE KEYS */;
INSERT INTO `benefitype` (`id`,`name`,`cap`,`version`) VALUES 
 (3,'Transport Allowance',599,1),
 (4,'House Allowance',1500,1),
 (5,'Cash Indeminty Allowance',200,1),
 (6,'fuel',1500,1);
/*!40000 ALTER TABLE `benefitype` ENABLE KEYS */;


--
-- Definition of table `deduction`
--

DROP TABLE IF EXISTS `deduction`;
CREATE TABLE `deduction` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `employeeId` int(10) unsigned NOT NULL,
  `deductionType` int(10) unsigned NOT NULL,
  `dueDate` varchar(45) NOT NULL,
  `done` tinyint(1) NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_dedecuction_type` (`deductionType`),
  KEY `FK_dedecuction_emp` (`employeeId`),
  CONSTRAINT `FK_dedecuction_emp` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_dedecuction_type` FOREIGN KEY (`deductionType`) REFERENCES `deductiontype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `deduction`
--

/*!40000 ALTER TABLE `deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `deduction` ENABLE KEYS */;


--
-- Definition of table `deductiontype`
--

DROP TABLE IF EXISTS `deductiontype`;
CREATE TABLE `deductiontype` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `deductiontype`
--

/*!40000 ALTER TABLE `deductiontype` DISABLE KEYS */;
/*!40000 ALTER TABLE `deductiontype` ENABLE KEYS */;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `employeeId` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `middlename` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `sex` varchar(6) NOT NULL,
  `birthdate` datetime NOT NULL,
  `hiredate` datetime NOT NULL,
  `positionId` int(10) unsigned NOT NULL,
  `photo_path` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_employee_position` (`positionId`),
  CONSTRAINT `FK_employee_position` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`,`employeeId`,`firstname`,`middlename`,`lastname`,`sex`,`birthdate`,`hiredate`,`positionId`,`photo_path`,`status`,`version`) VALUES 
 (2,'MODETH-0002','MULUNEH','AWOKE','ADGEH1','Male','1980-06-19 00:00:00','2011-06-14 00:00:00',4,'/data/photo/185145_481927465161666_2082997324_n.jpg','ACTIVE',1),
 (3,'MODETH-0001','Desta','Abebe','Muluken','Male','1987-01-05 00:00:00','2012-07-02 00:00:00',3,'/data/photo/418446_185246901607959_1591185693_n.jpg','IN ACTIVE',1),
 (4,'MODETH-0004','AMAN','DEKSISO','DEKSISO','Male','1980-09-06 00:00:00','2012-07-04 00:00:00',4,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (5,'ECOMEX-0005','New','Employee','Name','Male','2000-02-01 00:00:00','2012-07-09 00:00:00',2,'/data/photo/Photo-0002.jpg','ACTIVE',1),
 (6,'ETHMTN-0006','Abebe','Abebe','Abebe','Male','1994-06-14 00:00:00','2012-07-09 00:00:00',5,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (7,'EEPCO-0007','James','Smith','Mailman','Male','1989-05-16 00:00:00','2012-07-16 00:00:00',5,'/data/photo/placeholder-pic.png','IN ACTIVE',1),
 (8,'HOF-0008','Haile','Gebre','Silasie','Male','1980-06-10 00:00:00','2012-07-16 00:00:00',1,'/data/photo/placeholder-pic.png','ACTIVE',1);
INSERT INTO `employee` (`id`,`employeeId`,`firstname`,`middlename`,`lastname`,`sex`,`birthdate`,`hiredate`,`positionId`,`photo_path`,`status`,`version`) VALUES 
 (12,'MODETH-0012','Anderson','Neyo','Mick','Female','1980-08-01 00:00:00','2012-08-01 00:00:00',1,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (13,'MODETH-0013','Abera','Girma','Lemma','Female','2012-08-02 00:00:00','2012-08-02 00:00:00',1,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (14,'MODETH-0014','James','Gasolin','Chant','Male','1940-08-05 23:44:45','2012-08-06 00:00:00',2,'employees.bmp','ACTIVE',1),
 (15,'MODETH-0015','asdfasd','fasdf','asdfadsf','Male','2012-08-09 00:00:00','2012-08-09 00:00:00',1,'/data/photo/Koala.jpg','ACTIVE',1),
 (16,'MODETH-0016','asdf','dsdd','ddd','Male','2012-08-09 00:00:00','2012-08-09 00:00:00',2,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (17,'MODETH-0017','ssxdfere','adfa','zxcavdfadsf','Male','2012-08-09 00:00:00','2012-08-09 00:00:00',1,'/data/photo/Tulips.jpg','ACTIVE',1),
 (18,'MODETH-0018','Asdfs','asdf','asdfwerwer','Male','1989-02-07 00:00:00','2012-08-10 00:00:00',1,'/data/photo/placeholder-pic.png','IN ACTIVE',1),
 (19,'MODETH-0019','Mesay1','Solomon','Solomon','Male','1980-01-01 00:00:00','2012-08-10 00:00:00',2,'/data/photo/placeholder-pic.png','ACTIVE',1);
INSERT INTO `employee` (`id`,`employeeId`,`firstname`,`middlename`,`lastname`,`sex`,`birthdate`,`hiredate`,`positionId`,`photo_path`,`status`,`version`) VALUES 
 (20,'MODETH-0020','Biruk','Abebe','Degu','Male','1975-02-02 00:00:00','2012-08-10 00:00:00',2,'/data/photo/placeholder-pic.png','ACTIVE',1),
 (21,'MODETH-0021','xxsd','wer','qwewqe','Male','2012-08-11 00:00:00','2012-08-11 00:00:00',2,'employees.bmp','ACTIVE',1),
 (22,'MODETH-0022','James','Smith','Joshwa','Male','2012-08-20 00:00:00','2012-08-20 00:00:00',1,'movie-poster.jpg','ACTIVE',1),
 (23,'MODETH-0023','Selam','Haile','Mule','Male','1960-01-28 00:00:00','2012-09-13 00:00:00',5,'Penguins.jpg','ACTIVE',1),
 (24,'MODETH-0024','Mikias','Mikias','Mikias','Male','1969-10-14 00:00:00','2012-09-25 00:00:00',6,'/data/photo/placeholder-pic.png','IN ACTIVE',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `employeepaygroup`
--

DROP TABLE IF EXISTS `employeepaygroup`;
CREATE TABLE `employeepaygroup` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `employeeId` int(10) unsigned NOT NULL,
  `payGroupId` int(10) unsigned NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_employeePayGroup_emp` (`employeeId`),
  KEY `FK_employeePayGroup_group` (`payGroupId`),
  CONSTRAINT `FK_employeePayGroup_emp` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_employeePayGroup_group` FOREIGN KEY (`payGroupId`) REFERENCES `paygroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employeepaygroup`
--

/*!40000 ALTER TABLE `employeepaygroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeepaygroup` ENABLE KEYS */;


--
-- Definition of table `leaveapproval`
--

DROP TABLE IF EXISTS `leaveapproval`;
CREATE TABLE `leaveapproval` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `approverId` int(10) unsigned NOT NULL,
  `requestId` int(10) unsigned NOT NULL,
  `approvedbydate` datetime NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_leaveapproval_employee` (`approverId`),
  KEY `FK_leaveapproval_request` (`requestId`),
  CONSTRAINT `FK_leaveapproval_employee` FOREIGN KEY (`approverId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_leaveapproval_request` FOREIGN KEY (`requestId`) REFERENCES `leaverequest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leaveapproval`
--

/*!40000 ALTER TABLE `leaveapproval` DISABLE KEYS */;
/*!40000 ALTER TABLE `leaveapproval` ENABLE KEYS */;


--
-- Definition of table `leaverequest`
--

DROP TABLE IF EXISTS `leaverequest`;
CREATE TABLE `leaverequest` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `employeeId` int(10) unsigned NOT NULL,
  `leaveTypeId` int(10) unsigned NOT NULL,
  `leaveDate` datetime NOT NULL,
  `returnDate` datetime NOT NULL,
  `status` int(10) unsigned NOT NULL default '0',
  `noOfDays` int(10) unsigned NOT NULL,
  `description` text NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_leaverequest_employee` (`employeeId`),
  KEY `FK_leaverequest_leaveType` (`leaveTypeId`),
  CONSTRAINT `FK_leaverequest_employee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_leaverequest_leaveType` FOREIGN KEY (`leaveTypeId`) REFERENCES `leavetype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leaverequest`
--

/*!40000 ALTER TABLE `leaverequest` DISABLE KEYS */;
INSERT INTO `leaverequest` (`id`,`employeeId`,`leaveTypeId`,`leaveDate`,`returnDate`,`status`,`noOfDays`,`description`,`version`) VALUES 
 (1,3,3,'2012-08-22 16:22:59','2012-08-22 16:22:59',0,4,'hhhh',1);
/*!40000 ALTER TABLE `leaverequest` ENABLE KEYS */;


--
-- Definition of table `leavetype`
--

DROP TABLE IF EXISTS `leavetype`;
CREATE TABLE `leavetype` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `day_cap` double NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leavetype`
--

/*!40000 ALTER TABLE `leavetype` DISABLE KEYS */;
INSERT INTO `leavetype` (`id`,`name`,`day_cap`,`version`) VALUES 
 (1,'Sick Leave',9,1),
 (2,'Maternity Leave',90,1),
 (3,'Leave of Absence',6,1),
 (4,'Maternity Father\'s Leave',10,1);
/*!40000 ALTER TABLE `leavetype` ENABLE KEYS */;


--
-- Definition of table `paygroup`
--

DROP TABLE IF EXISTS `paygroup`;
CREATE TABLE `paygroup` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `payRate` double NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `paygroup`
--

/*!40000 ALTER TABLE `paygroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `paygroup` ENABLE KEYS */;


--
-- Definition of table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
CREATE TABLE `payroll` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `runOnDate` datetime NOT NULL,
  `runBy` int(10) unsigned NOT NULL,
  `file` blob NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_payroll_run_by_user` (`runBy`),
  CONSTRAINT `FK_payroll_run_by_user` FOREIGN KEY (`runBy`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payroll`
--

/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `salary` double NOT NULL,
  `raisePerYear` double NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`,`name`,`salary`,`raisePerYear`,`version`) VALUES 
 (1,'SOFTWARE ENGINEER',12000,1500,1),
 (2,'System Administrator',5000,1200,1),
 (3,'Senior Accountant',5000,1000,1),
 (4,'Managerial Constultant',14000,2000,1),
 (5,'Senior Accountant',5000,1000,1),
 (6,'aa',3455,1234,1);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


--
-- Definition of table `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `template` varchar(45) NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `report`
--

/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;


--
-- Definition of table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`,`name`,`version`) VALUES 
 (1,'Administration',1),
 (2,'Development',1),
 (3,'Finance',1),
 (4,'Leave Management',1),
 (5,'Leave Management1',1),
 (6,'Sales',1),
 (7,'PR',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `tbl_leave`
--

DROP TABLE IF EXISTS `tbl_leave`;
CREATE TABLE `tbl_leave` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `leaveType` int(10) unsigned NOT NULL,
  `unuseddays` int(10) unsigned NOT NULL,
  `employeeId` int(10) unsigned NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_leave_leavetype` USING BTREE (`leaveType`),
  KEY `FK_tbl_leave_employee` (`employeeId`),
  CONSTRAINT `FK_leave_leavetype` FOREIGN KEY (`leaveType`) REFERENCES `leavetype` (`id`),
  CONSTRAINT `FK_tbl_leave_employee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_leave`
--

/*!40000 ALTER TABLE `tbl_leave` DISABLE KEYS */;
INSERT INTO `tbl_leave` (`id`,`leaveType`,`unuseddays`,`employeeId`,`year`,`version`) VALUES 
 (5,4,10,3,2012,1);
/*!40000 ALTER TABLE `tbl_leave` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `employeeId` int(10) unsigned NOT NULL,
  `roleId` int(10) unsigned NOT NULL,
  `version` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_users_employeeId` (`employeeId`),
  KEY `FK_users_roles` (`roleId`),
  CONSTRAINT `FK_users_employeeId` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`roleId`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`username`,`password`,`employeeId`,`roleId`,`version`) VALUES 
 (1,'Mekbib','password',2,2,1),
 (2,'Administrator','123456',3,1,1),
 (3,'Mule','mule',18,7,1),
 (4,'Abebe','password',6,7,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of view `emp_benefit_view`
--

DROP TABLE IF EXISTS `emp_benefit_view`;
DROP VIEW IF EXISTS `emp_benefit_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `freehrms`.`emp_benefit_view` AS select `freehrms`.`employee`.`employeeId` AS `EMP_ID`,`freehrms`.`position`.`salary` AS `SALARY`,`freehrms`.`benefit`.`amount` AS `BENEFIT_AMNT`,`freehrms`.`benefitype`.`name` AS `BENEFIT_TYPE` from (((`freehrms`.`employee` join `freehrms`.`position`) join `freehrms`.`benefit`) join `freehrms`.`benefitype`) where ((`freehrms`.`employee`.`positionId` = `freehrms`.`position`.`id`) and (`freehrms`.`employee`.`id` = `freehrms`.`benefit`.`employeeId`) and (`freehrms`.`benefit`.`typeId` = `freehrms`.`benefitype`.`id`));

--
-- Definition of view `emp_payroll_view`
--

DROP TABLE IF EXISTS `emp_payroll_view`;
DROP VIEW IF EXISTS `emp_payroll_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `freehrms`.`emp_payroll_view` AS select `freehrms`.`employee`.`employeeId` AS `EMPLOYEEID`,concat(`freehrms`.`employee`.`firstname`,_utf8' ',`freehrms`.`employee`.`middlename`,_utf8' ',`freehrms`.`employee`.`lastname`) AS `FULL_NAME`,`freehrms`.`position`.`salary` AS `GROSS_SALARY`,_utf8'Benefits' AS `BENEFIT_TYPE`,sum(`emp_benefit_view`.`BENEFIT_AMNT`) AS `BENEFIT_AMNT` from (`freehrms`.`position` join (`freehrms`.`employee` left join `freehrms`.`emp_benefit_view` on((`freehrms`.`employee`.`employeeId` = `emp_benefit_view`.`EMP_ID`)))) where (`freehrms`.`position`.`id` = `freehrms`.`employee`.`positionId`) group by `freehrms`.`employee`.`employeeId`;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
