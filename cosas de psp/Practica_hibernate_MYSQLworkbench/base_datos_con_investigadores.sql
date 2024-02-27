-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: investigacion
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `educacion`
--

DROP TABLE IF EXISTS `educacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educacion` (
  `ED_ID` int NOT NULL AUTO_INCREMENT,
  `UNI_ID` int DEFAULT NULL,
  `ED_GRADO` varchar(255) DEFAULT NULL,
  `ED_CAMPO` varchar(255) DEFAULT NULL,
  `ED_DESCRIPCION` text,
  `INV_ID` int DEFAULT NULL,
  PRIMARY KEY (`ED_ID`),
  KEY `educacion_ibfk_1` (`INV_ID`),
  CONSTRAINT `educacion_ibfk_1` FOREIGN KEY (`INV_ID`) REFERENCES `investigador` (`INV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educacion`
--

LOCK TABLES `educacion` WRITE;
/*!40000 ALTER TABLE `educacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `educacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia_laboral`
--

DROP TABLE IF EXISTS `experiencia_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiencia_laboral` (
  `EL_ID` int NOT NULL AUTO_INCREMENT,
  `EL_INSTITUTO` varchar(255) DEFAULT NULL,
  `EL_PERIODO` varchar(255) DEFAULT NULL,
  `EL_DESCRIPCION` text,
  `INV_ID` int DEFAULT NULL,
  PRIMARY KEY (`EL_ID`),
  KEY `experiencia_laboral_ibfk_1` (`INV_ID`),
  CONSTRAINT `experiencia_laboral_ibfk_1` FOREIGN KEY (`INV_ID`) REFERENCES `investigador` (`INV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia_laboral`
--

LOCK TABLES `experiencia_laboral` WRITE;
/*!40000 ALTER TABLE `experiencia_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiencia_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investigador`
--

DROP TABLE IF EXISTS `investigador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investigador` (
  `INV_ID` int NOT NULL AUTO_INCREMENT,
  `INV_NOMBRE` varchar(255) DEFAULT NULL,
  `INV_EXTENSION` int DEFAULT NULL,
  `INV_EMAIL` varchar(255) DEFAULT NULL,
  `INV_SNI` int DEFAULT NULL,
  `INV_PRODEP` int DEFAULT NULL,
  `INV_CATEGORIA` int DEFAULT NULL,
  `INV_CUBICULO` int DEFAULT NULL,
  `INV_DESCRIPCION` text,
  PRIMARY KEY (`INV_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investigador`
--

LOCK TABLES `investigador` WRITE;
/*!40000 ALTER TABLE `investigador` DISABLE KEYS */;
INSERT INTO `investigador` VALUES (1,'2',2,'2',2,2,1,2,'2');
/*!40000 ALTER TABLE `investigador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tesis_redactadas`
--

DROP TABLE IF EXISTS `tesis_redactadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tesis_redactadas` (
  `TESIS_ID` int NOT NULL AUTO_INCREMENT,
  `TESIS_NOMBRE` varchar(255) DEFAULT NULL,
  `TESIS_DESCRIPCION` text,
  `INV_ID` int DEFAULT NULL,
  PRIMARY KEY (`TESIS_ID`),
  KEY `tesis_redactadas_ibfk_1` (`INV_ID`),
  CONSTRAINT `tesis_redactadas_ibfk_1` FOREIGN KEY (`INV_ID`) REFERENCES `investigador` (`INV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tesis_redactadas`
--

LOCK TABLES `tesis_redactadas` WRITE;
/*!40000 ALTER TABLE `tesis_redactadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tesis_redactadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajos_grado_dirigidos`
--

DROP TABLE IF EXISTS `trabajos_grado_dirigidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajos_grado_dirigidos` (
  `TG_ID` int NOT NULL AUTO_INCREMENT,
  `TG_NOMBRE` varchar(255) DEFAULT NULL,
  `TG_ALUMNO` varchar(255) DEFAULT NULL,
  `TG_NIVEL` varchar(255) DEFAULT NULL,
  `TG_TIPO` varchar(255) DEFAULT NULL,
  `INV_ID` int DEFAULT NULL,
  PRIMARY KEY (`TG_ID`),
  KEY `trabajos_grado_dirigidos_ibfk_1` (`INV_ID`),
  CONSTRAINT `trabajos_grado_dirigidos_ibfk_1` FOREIGN KEY (`INV_ID`) REFERENCES `investigador` (`INV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajos_grado_dirigidos`
--

LOCK TABLES `trabajos_grado_dirigidos` WRITE;
/*!40000 ALTER TABLE `trabajos_grado_dirigidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `trabajos_grado_dirigidos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 17:59:56
