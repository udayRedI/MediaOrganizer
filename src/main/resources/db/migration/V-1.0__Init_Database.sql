-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: MediaOrganizer
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `LoginRoles`
--

DROP TABLE IF EXISTS `LoginRoles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoginRoles` (
  `userName` varchar(100) NOT NULL,
  `authority` varchar(100) NOT NULL,
  `UID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginRoles`
--

LOCK TABLES `LoginRoles` WRITE;
/*!40000 ALTER TABLE `LoginRoles` DISABLE KEYS */;
INSERT INTO `LoginRoles` VALUES ('uday','ROLE_USER',1);
/*!40000 ALTER TABLE `LoginRoles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoginTable`
--

DROP TABLE IF EXISTS `LoginTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoginTable` (
  `UID` int(11) NOT NULL,
  `userName` varchar(1000) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `authority` varchar(100) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginTable`
--

LOCK TABLES `LoginTable` WRITE;
/*!40000 ALTER TABLE `LoginTable` DISABLE KEYS */;
INSERT INTO `LoginTable` VALUES (1,'uday','uday','ROLE_USER');
/*!40000 ALTER TABLE `LoginTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie`
--

DROP TABLE IF EXISTS `Movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Movie_Name` varchar(1000) NOT NULL,
  `Year` varchar(10) NOT NULL DEFAULT '0',
  `Movie_Rating` float NOT NULL DEFAULT '0',
  `ImdbVotes` varchar(100) DEFAULT NULL,
  `Released` varchar(100) DEFAULT '',
  `Runtime` varchar(100) DEFAULT NULL,
  `Genres` varchar(100) DEFAULT NULL,
  `Director` varchar(1000) DEFAULT NULL,
  `Writers` varchar(1000) DEFAULT NULL,
  `Actors` varchar(1000) DEFAULT NULL,
  `Plot` varchar(1000) DEFAULT NULL,
  `Poster` varchar(1000) DEFAULT NULL,
  `ImdbID` varchar(100) DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `MediaOrganizerFilePath` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie`
--

LOCK TABLES `Movie` WRITE;
/*!40000 ALTER TABLE `Movie` DISABLE KEYS */;
INSERT INTO `Movie` VALUES (71,'Thor','2011',7,NULL,'','1 h 55 min','Action, Adventure, Fantasy','Kenneth Branagh','Ashley Miller, Zack Stentz','Chris Hemsworth, Anthony Hopkins, Natalie Portman, Tom Hiddleston','The powerful but arrogant god Thor is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.',NULL,NULL,NULL,'/home/uday/MediaOrganizer/Movies/thor'),(72,'Iron Man','2005',8,NULL,'','2 h 6 min','Action, Adventure, Sci-Fi','Jon Favreau','Mark Fergus, Hawk Ostby','Robert Downey Jr., Gwyneth Paltrow, Terrence Howard, Jeff Bridges','When wealthy industrialist Tony Stark is forced to build an armored suit after a life-threatening incident, he ultimately decides to use its technology to fight against evil.',NULL,NULL,NULL,'/home/uday/MediaOrganizer/Movies/iron man');
/*!40000 ALTER TABLE `Movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie_Preferences`
--

DROP TABLE IF EXISTS `Movie_Preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movie_Preferences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `File_Type` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie_Preferences`
--

LOCK TABLES `Movie_Preferences` WRITE;
/*!40000 ALTER TABLE `Movie_Preferences` DISABLE KEYS */;
INSERT INTO `Movie_Preferences` VALUES (1,'video'),(2,'image'),(3,'.srt');
/*!40000 ALTER TABLE `Movie_Preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `USER_ROLE_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,100,'ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (100,'uday','uday',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-26  7:40:53
