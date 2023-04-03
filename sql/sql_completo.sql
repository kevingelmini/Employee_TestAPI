drop database if exists db_project_management;
CREATE DATABASE  IF NOT EXISTS `db_project_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_project_management`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: db_project_management
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `DEPARTMENT_ID` bigint NOT NULL,
  `DEPARTMENT_NAME` varchar(25) NOT NULL,
  `HEAD_ID` bigint NOT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`),
  KEY `DEPT_HEAD_ID_FK` (`HEAD_ID`),
  CONSTRAINT `DEPT_HEAD_ID_FK` FOREIGN KEY (`HEAD_ID`) REFERENCES `employees` (`EMPLOYEE_ID`)  on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1000,'MANAGEMENT',587191),(1001,'HUMAN RESOURCES',492783),(1002,'FINANCIAL',489302),(1003,'COMPUTER SCIENCE',485432),(1004,'ELECTRICAL ENGINEERING',461427),(1005,'BUSINESS&ADMINISTRATION',358432);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_tasks`
--

DROP TABLE IF EXISTS `emp_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_tasks` (
  `EMPLOYEE_ID` bigint NOT NULL,
  `TASK_ID` bigint NOT NULL,
  KEY `EMP_TASKS_EMP_ID_FK` (`EMPLOYEE_ID`),
  KEY `EMP_TASKS_TASK_ID_FK` (`TASK_ID`),
  CONSTRAINT `EMP_TASKS_EMP_ID_FK` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employees` (`EMPLOYEE_ID`)  on update cascade on delete cascade,
  CONSTRAINT `EMP_TASKS_TASK_ID_FK` FOREIGN KEY (`TASK_ID`) REFERENCES `tasks` (`TASK_ID`)  on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_tasks`
--

LOCK TABLES `emp_tasks` WRITE;
/*!40000 ALTER TABLE `emp_tasks` DISABLE KEYS */;
INSERT INTO `emp_tasks` VALUES (587191,3155),(392051,3150),(375531,3151),(442461,3152),(328471,3153),(268721,3154),(485432,3326),(408694,3321),(272765,3322),(339902,3323),(295423,3324),(422231,3325),(461427,3281),(365698,3275),(300346,3276),(421009,3277),(401883,3278),(382692,3279),(318633,3280),(358432,3146),(261508,3142),(336096,3143),(331954,3144),(344166,3145),(492783,3514),(394425,3510),(325479,3511),(306677,3512),(383576,3513),(489302,3701),(446296,3702),(338338,3703),(287674,3704),(312993,3150),(367322,3151),(294245,3152),(440283,3153),(409805,3154),(371283,3321),(394094,3322),(425931,3323),(420853,3324),(284595,3325),(298639,3275),(307036,3276),(256504,3277),(383622,3278),(389074,3279),(339186,3280),(276444,3142),(328152,3143),(392925,3144),(329194,3145),(267352,3514),(328471,3510),(268721,3511),(408694,3703),(272765,3702),(339902,3703),(295423,3704),(394425,3150),(325479,3151),(306677,3152),(383576,3153),(329194,3154),(392051,3321),(375531,3322),(442461,3323),(328471,3324),(268721,3325),(422231,3275),(408694,3276),(272765,3277),(339902,3278),(295423,3279),(375531,3280);
/*!40000 ALTER TABLE `emp_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `EMPLOYEE_ID` bigint NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(25) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `SALARY` decimal(18,4) NOT NULL,
  `TOTAL_WORK_PERCENTAGE` double NOT NULL,
  `MEMBERSHIP_COUNT` bigint DEFAULT NULL,
  `DEPARTMENT_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  UNIQUE KEY `EMP_EMAIL_UK` (`EMAIL`),
  KEY `EMP_DEPARTMENT_ID_FK` (`DEPARTMENT_ID`),
  CONSTRAINT `EMP_DEPARTMENT_ID_FK` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `departments` (`DEPARTMENT_ID`)  on update cascade on delete cascade,
  CONSTRAINT `EMP_SALARY_MIN` CHECK ((`SALARY` > 0)),
  CONSTRAINT `MEMBER_MAX` CHECK (((`MEMBERSHIP_COUNT` <= 5) and (`MEMBERSHIP_COUNT` >= 0))),
  CONSTRAINT `WORK_PER_MAX` CHECK (((`TOTAL_WORK_PERCENTAGE` <= 1) and (`TOTAL_WORK_PERCENTAGE` >= 0)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (256504,'Hilel','Byers','hilel.byers@ozu.edu.tr',10317.0000,0.1,1,1004),(261508,'Ina','Boyer','Ina.Boyer@ozu.edu.tr',7368.0000,0.15,1,1002),(267352,'Garrison','Craft','garrison.craft@ozu.edu.tr',6615.0000,0.25,1,1000),(268721,'Malachi','Dickerson','Malachi.Dickerson@ozu.edu.tr',10424.0000,0.65,3,1000),(272765,'Nyssa','Prince','Nyssa.Prince@ozu.edu.tr',9412.0000,0.3,3,1000),(276444,'Meredith','Greer','meredith.greer@ozu.edu.tr',7161.0000,0.15,1,1003),(284595,'Megan','Keith','Megan.Keith@ozu.edu.tr',9461.0000,0.2,1,1003),(287674,'Iola','Orr','Iola.Orr@ozu.edu.tr',5651.0000,0.2,1,1000),(294245,'Kameko','Charles','Kameko.Charles@ozu.edu.tr',5430.0000,0.15,1,1004),(295423,'Imelda','Abbott','Imelda.Abbott@ozu.edu.tr',10103.0000,0.55,3,1001),(298639,'Leo','Kelley','Leo.Kelley@ozu.edu.tr',9475.0000,0.1,1,1002),(300346,'Holmes','Brown','Holmes.Brown@ozu.edu.tr',6876.0000,0.15,1,1000),(306677,'Dane','Barker','Dane.Barker@ozu.edu.tr',12445.0000,0.3,2,1000),(307036,'Denton','Beard','Denton.Beard@ozu.edu.tr',6911.0000,0.15,1,1000),(312993,'Ariel','Whitley','Ariel.Whitley@ozu.edu.tr',10161.0000,0.15,1,1004),(318633,'Ahmed','Mckee','Ahmed.Mckee@ozu.edu.tr',7875.0000,0.2,1,1003),(325479,'Sasha','Bean','Sasha.Bean@ozu.edu.tr',3517.0000,0.5,2,1001),(328152,'Olga','Medina','olga.medina@ozu.edu.tr',13023.0000,0.3,1,1001),(328471,'Harper','Ramsey','Harper.Ramsey@ozu.edu.tr',8136.0000,0.4,3,1003),(329194,'Miranda','Robertson','miranda.robertson@ozu.edu.tr',3882.0000,0.3,2,1000),(331954,'Maile','Vincent','Maile.Vincent@ozu.edu.tr',13496.0000,0.15,1,1004),(336096,'Charde','Mcclure','Charde.Mcclure@ozu.edu.tr',14975.0000,0.3,1,1001),(338338,'Ryder','Parker','Ryder.Parker@ozu.edu.tr',10032.0000,0.3,1,1004),(339186,'Allegra','Rush','allegra.rush@ozu.edu.tr',13032.0000,0.2,1,1004),(339902,'Yvonne','Walls','Yvonne.Walls@ozu.edu.tr',4776.0000,0.55,3,1001),(344166,'Scarlet','Bryant','Scarlet.Bryant@ozu.edu.tr',13126.0000,0.1,1,1004),(358432,'Yusuf','Aslan','Yusuf.Aslan@ozu.edu.tr',23750.0000,0.3,1,1005),(365698,'Wayne','Holman','Wayne.Holman@ozu.edu.tr',11086.0000,0.1,1,1001),(367322,'Ann','David','Ann.David@ozu.edu.tr',13506.0000,0.25,1,1002),(371283,'Samantha','Fuentes','Samantha.Fuentes@ozu.edu.tr',5402.0000,0.3,1,1001),(375531,'Holly','Douglas','Holly.Douglas@ozu.edu.tr',7348.0000,0.55,3,1003),(382692,'Kiona','Bass','Kiona.Bass@ozu.edu.tr',9172.0000,0.2,1,1004),(383576,'Deacon','Randolph','Deacon.Randolph@ozu.edu.tr',11841.0000,0.4,2,1001),(383622,'Carol','Crawford','carol.crawford@ozu.edu.tr',10413.0000,0.1,1,1001),(389074,'Zahir','George','zahir.george@ozu.edu.tr',9909.0000,0.2,1,1002),(392051,'Candice','Medina','Candice.Medina@ozu.edu.tr',7375.0000,0.45,2,1003),(392925,'Brock','Nguyen','brock.nguyen@ozu.edu.tr',3631.0000,0.15,1,1004),(394094,'Xantha','Allison','Xantha.Allison@ozu.edu.tr',6384.0000,0.1,1,1002),(394425,'Bertha','Knox','Bertha.Knox@ozu.edu.tr',14705.0000,0.25,2,1002),(401883,'Amelia','Townsend','Amelia.Townsend@ozu.edu.tr',8355.0000,0.1,1,1000),(408694,'Rama','Haynes','Rama.Haynes@ozu.edu.tr',8956.0000,0.85,3,1001),(409805,'Jarrod','Tate','Jarrod.Tate@ozu.edu.tr',13569.0000,0.2,1,1004),(420853,'Rhiannon','Bauer','Rhiannon.Bauer@ozu.edu.tr',3592.0000,0.15,1,1003),(421009,'Cally','Burch','Cally.Burch@ozu.edu.tr',12615.0000,0.1,1,1000),(422231,'Brett','Watkins','Brett.Watkins@ozu.edu.tr',5777.0000,0.3,2,1002),(425931,'Charity','Brewer','Charity.Brewer@ozu.edu.tr',7403.0000,0.15,1,1004),(440283,'Carl','Myers','Carl.Myers@ozu.edu.tr',8540.0000,0.15,1,1004),(442461,'Alyssa','Riddle','Alyssa.Riddle@ozu.edu.tr',8448.0000,0.3,2,1000),(446296,'Abbot','Harmon','Abbot.Harmon@ozu.edu.tr',4459.0000,0.1,1,1001),(461427,'Alexander','Petrov','Alexander.Petrov@ozu.edu.tr',21750.0000,0.15,1,1004),(485432,'Rahul','Priya','Rahul.Priya@ozu.edu.tr',20750.0000,0.1,1,1003),(489302,'John','Brown','John.Brown@ozu.edu.tr',24000.0000,0.4,1,1002),(492783,'Hasan','Yilmaz','Hasan.Yilmaz@ozu.edu.tr',23000.0000,0.25,1,1001),(587191,'Ahmet','Can','Ahmet.Can@ozu.edu.tr',28000.0000,0.2,1,1000);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_teams`
--

DROP TABLE IF EXISTS `project_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_teams` (
  `PROJECT_ID` bigint NOT NULL,
  `EMPLOYEE_ID` bigint NOT NULL,
  `WORK_PERCENTAGE` double NOT NULL,
  KEY `TEAMS_PROJECT_ID_FK` (`PROJECT_ID`),
  KEY `TEAMS_EMPLOYEE_ID_FK` (`EMPLOYEE_ID`),
  CONSTRAINT `TEAMS_EMPLOYEE_ID_FK` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employees` (`EMPLOYEE_ID`)  on update cascade on delete cascade,
  CONSTRAINT `TEAMS_PROJECT_ID_FK` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`)  on update cascade on delete cascade,
  CONSTRAINT `TEAMS_WORK_PER_CONST` CHECK (((`WORK_PERCENTAGE` < 1) and (`WORK_PERCENTAGE` > 0)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_teams`
--

LOCK TABLES `project_teams` WRITE;
/*!40000 ALTER TABLE `project_teams` DISABLE KEYS */;
INSERT INTO `project_teams` VALUES (2001,587191,0.1),(2001,392051,0.15),(2001,375531,0.25),(2001,442461,0.15),(2001,328471,0.15),(2001,268721,0.2),(2002,485432,0.1),(2002,408694,0.3),(2002,272765,0.1),(2002,339902,0.15),(2002,295423,0.15),(2002,422231,0.2),(2003,461427,0.15),(2003,365698,0.1),(2003,300346,0.15),(2003,421009,0.1),(2003,401883,0.1),(2003,382692,0.2),(2003,318633,0.2),(2004,358432,0.3),(2004,261508,0.15),(2004,336096,0.3),(2004,331954,0.15),(2004,344166,0.1),(2005,492783,0.25),(2005,394425,0.1),(2005,325479,0.25),(2005,306677,0.15),(2005,383576,0.25),(2006,489302,0.4),(2006,446296,0.1),(2006,338338,0.3),(2006,287674,0.2),(2001,312993,0.15),(2001,367322,0.25),(2001,294245,0.15),(2001,440283,0.15),(2001,409805,0.2),(2002,371283,0.3),(2002,394094,0.1),(2002,425931,0.15),(2002,420853,0.15),(2002,284595,0.2),(2003,298639,0.1),(2003,307036,0.15),(2003,256504,0.1),(2003,383622,0.1),(2003,389074,0.2),(2003,339186,0.2),(2004,276444,0.15),(2004,328152,0.3),(2004,392925,0.15),(2004,329194,0.1),(2005,267352,0.25),(2005,328471,0.1),(2005,268721,0.25),(2006,408694,0.4),(2006,272765,0.1),(2006,339902,0.3),(2006,295423,0.2),(2001,394425,0.15),(2001,325479,0.25),(2001,306677,0.15),(2001,383576,0.15),(2001,329194,0.2),(2002,392051,0.3),(2002,375531,0.1),(2002,442461,0.15),(2002,328471,0.15),(2002,268721,0.2),(2003,422231,0.1),(2003,408694,0.15),(2003,272765,0.1),(2003,339902,0.1),(2003,295423,0.2),(2003,375531,0.2);
/*!40000 ALTER TABLE `project_teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `PROJECT_ID` bigint NOT NULL,
  `PROJECT_NAME` varchar(25) NOT NULL,
  `LEADER_ID` bigint DEFAULT NULL,
  `PROJECT_STATUS` varchar(1) NOT NULL,
  `PROJECT_START_DATE` date DEFAULT NULL,
  `PROJECT_END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`),
  KEY `PROJ_LEADER_ID_FK` (`LEADER_ID`),
  CONSTRAINT `PROJ_LEADER_ID_FK` FOREIGN KEY (`LEADER_ID`) REFERENCES `employees` (`EMPLOYEE_ID`)  on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (2001,'FINANCIAL REPORTING',587191,'S','2016-06-07','2016-12-30'),(2002,'DATABASE CREATION',485432,'P','2016-12-15','2017-03-20'),(2003,'CPU DESIGN',461427,'S','2016-08-15','2017-08-19'),(2004,'MANAGEMENT REPORTING',358432,'P','2017-01-15','2017-02-15'),(2005,'SYSTEM SOFTWARE DESIGN',492783,'S','2016-11-01','2017-02-01'),(2006,'NEW SYSTEM FOR GRADING',489302,'P','2017-02-01','2017-05-01');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `TASK_ID` bigint NOT NULL,
  `TASK_NAME` varchar(25) NOT NULL,
  `TASK_STATUS` varchar(1) NOT NULL,
  `PROJECT_ID` bigint NOT NULL default 0,
  `COORDINATOR_ID` bigint NOT NULL default 0,
  `TASK_START_DATE` date DEFAULT NULL,
  `TASK_END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `TASK_PROJECT_ID_FK` (`PROJECT_ID`),
  KEY `TASK_EMPLOYEE_ID_FK` (`COORDINATOR_ID`),
  CONSTRAINT `TASK_EMPLOYEE_ID_FK` FOREIGN KEY (`COORDINATOR_ID`) REFERENCES `employees` (`EMPLOYEE_ID`) on update cascade on delete cascade,
  CONSTRAINT `TASK_PROJECT_ID_FK` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`) on update cascade on delete cascade,
  CONSTRAINT `TASK_STATUS_CONSCHECK` CHECK ((`TASK_STATUS` in (_utf8mb4'C',_utf8mb4'P',_utf8mb4'S',_utf8mb4'F')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (3142,'GETTING INFO','P',2004,261508,'2017-01-15','2017-02-15'),(3143,'VISITING ALL DEPARTMENTS','P',2004,336096,'2017-01-15','2017-02-15'),(3144,'COLLECTING DATA','P',2004,331954,'2017-01-15','2017-02-15'),(3145,'CREATING REPORT','P',2004,344166,'2017-01-15','2017-02-15'),(3146,'MANAGEMENT','P',2004,358432,'2017-01-15','2017-02-15'),(3150,'CALCULATING','S',2001,392051,'2016-06-08','2016-11-20'),(3151,'DATA ENTERING','S',2001,375531,'2016-06-08','2016-11-01'),(3152,'ENTERING DATA','S',2001,442461,'2016-06-08','2016-10-15'),(3153,'GETTING INFORMATION','C',2001,328471,'2016-06-08','2016-10-05'),(3154,'CREATING NEEDS','C',2001,268721,'2016-06-08','2016-10-05'),(3155,'MANAGEMENT','S',2001,587191,'2016-06-07','2016-12-30'),(3275,'REPORTING NEEDS','S',2003,365698,'2016-08-16','2017-08-19'),(3276,'BUDGET REPORTING','S',2003,300346,'2016-09-15','2016-12-15'),(3277,'INTERIOR DESIGNING','S',2003,421009,'2016-09-15','2016-10-15'),(3278,'PHYSICAL BUILDING','P',2003,401883,'2017-05-01','2017-08-19'),(3279,'TESTING','P',2003,382692,'2017-08-01','2017-08-19'),(3280,'PRODUCING','P',2003,318633,'2017-08-01','2017-08-19'),(3281,'MANAGEMENT','S',2003,461427,'2016-08-15','2017-08-19'),(3321,'REPORTING NEEDS','S',2002,408694,'2016-12-16','2017-01-10'),(3322,'INSERT CREATING','S',2002,272765,'2016-12-18','2017-02-01'),(3323,'BUILDING STRUCTURE','S',2002,339902,'2016-06-08','2016-10-15'),(3324,'MANAGING INFORMATION','P',2002,295423,'2016-12-25','2017-03-20'),(3325,'ENTERING DATA','F',2002,422231,'2016-12-16','2016-12-25'),(3326,'MANAGEMENT','S',2002,485432,'2016-12-15','2017-03-20'),(3510,'SYSTEM NEEDS','S',2005,394425,'2016-11-01','2017-02-01'),(3511,'DETAILED DOCUMENTING','P',2005,325479,'2017-01-01','2017-02-01'),(3512,'JAVA DEVELOPMENT','S',2005,306677,'2016-12-01','2017-01-01'),(3513,'PHP DEVELOPMENT','S',2005,383576,'2016-12-01','2017-01-01'),(3514,'MANAGEMENT','S',2005,492783,'2016-11-01','2017-02-01'),(3701,'MANAGEMENT','P',2006,489302,'2017-02-01','2017-05-01'),(3702,'SYSTEM CONTROL','P',2006,446296,'2017-02-01','2017-05-01'),(3703,'GRADING OPTIONS','P',2006,338338,'2017-02-01','2017-05-01'),(3704,'CONTROL MANAGEMENT','P',2006,287674,'2017-02-01','2017-05-01');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_project_management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 12:14:01
