-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: bpngestiontest
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alta`
--

DROP TABLE IF EXISTS `alta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alta` (
  `idAlta` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAlta`),
  KEY `FK_alta_empleado` (`idEmpleado`),
  KEY `FK_alta_empresa` (`idEmpresa`),
  CONSTRAINT `FK_alta_empleado` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`),
  CONSTRAINT `FK_alta_empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alta`
--

LOCK TABLES `alta` WRITE;
/*!40000 ALTER TABLE `alta` DISABLE KEYS */;
/*!40000 ALTER TABLE `alta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archivo_alta`
--

DROP TABLE IF EXISTS `archivo_alta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archivo_alta` (
  `idArchivo_Alta` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` int(11) DEFAULT NULL,
  `cantidadRegistros` int(11) DEFAULT NULL,
  `ruta_Archivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArchivo_Alta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archivo_alta`
--

LOCK TABLES `archivo_alta` WRITE;
/*!40000 ALTER TABLE `archivo_alta` DISABLE KEYS */;
/*!40000 ALTER TABLE `archivo_alta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archivo_debito`
--

DROP TABLE IF EXISTS `archivo_debito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archivo_debito` (
  `idArchivo_Debito` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` date DEFAULT NULL,
  `cantidadRegistros` int(11) DEFAULT NULL,
  `importeTotal` double DEFAULT NULL,
  `ruta_Archivo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idArchivo_Debito`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archivo_debito`
--

LOCK TABLES `archivo_debito` WRITE;
/*!40000 ALTER TABLE `archivo_debito` DISABLE KEYS */;
/*!40000 ALTER TABLE `archivo_debito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debito`
--

DROP TABLE IF EXISTS `debito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debito` (
  `idDebito` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('ABIERTO','CERRADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `importe1` double DEFAULT NULL,
  `importe2` double DEFAULT NULL,
  `importe3` double DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDebito`),
  KEY `FK_debito_empresa` (`idEmpresa`),
  KEY `FK_debito_usuario` (`idUsuario`),
  CONSTRAINT `FK_debito_empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`),
  CONSTRAINT `FK_debito_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debito`
--

LOCK TABLES `debito` WRITE;
/*!40000 ALTER TABLE `debito` DISABLE KEYS */;
/*!40000 ALTER TABLE `debito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domicilio`
--

DROP TABLE IF EXISTS `domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domicilio` (
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(50) DEFAULT NULL,
  `altura` int(11) DEFAULT NULL,
  `piso` varchar(50) DEFAULT NULL,
  `dpto` varchar(50) DEFAULT NULL,
  `barrio` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `codigoPostal` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDomicilio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domicilio`
--

LOCK TABLES `domicilio` WRITE;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apellido` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cuil` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cbu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `importe` double(22,0) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idEmpleado`),
  KEY `FK_empleado_usuario` (`idUsuario`),
  CONSTRAINT `FK_empleado_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,2,'Luis','Soto','20322717564','6666666666666666666666',600,1),(2,2,'Adolfo','Barrionuevo','32271785','2222222222222222222222',100,1),(3,2,'Lalo','Landa','272658746','3333333333333333333333',1000,1),(7,2,'Alfredo','Bustos','253677546','9999999999999999999999',300,1),(9,2,'Pablo','Perez','2032271454','9999999999999999999999',200,0),(10,2,'Carlos','Futre','2029956050','8888888888888888888888',0,1);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cuit` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cuenta` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idEmpresa`),
  KEY `fk_Empresa_Usuario_idx` (`idUsuario`) USING BTREE,
  CONSTRAINT `FK_Usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Rincon S.A.','30322717569','64411',2,1),(2,'Transat','6041241566','65554',2,1),(3,'Amanecer','271203597','95546',3,1),(5,'Cars','309455681','955454',2,1),(7,'El Sol S.A','30322717564','6441741',2,0),(8,'Olmos','20322717564','666565',2,1),(9,'AAA','201212142421','666565',2,1),(10,'oooooo','1114','8888888',2,0),(11,'dsdsd','2222','3333',2,0),(12,'ddd','444','3333333338',2,0);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fechaAcred` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cantidadPagos` int(11) NOT NULL,
  `totalPagado` double(22,0) NOT NULL,
  `prestacion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subPrestacion` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `referencia` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idEmpresa` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `archivo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idPago`),
  KEY `fk_Pago_Usuario1_idx` (`idUsuario`) USING BTREE,
  KEY `FK_pago_empresa` (`idEmpresa`),
  CONSTRAINT `FK_pago_empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`),
  CONSTRAINT `FK_pago_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (50,'29-08-2020','27-08-2020',5,1700,'SUELDOS','sub00','Mayo',1,2,'29082020_Rincon S.A.__V4'),(51,'29-08-2020','05-09-2020',1,90000,'PROVEEDOR','sub0033','Noviembre',2,2,'29082020_Transat__V4'),(52,'29-09-2020','01-10-2020',5,2000,'SUELDOS','sub_efip','efip1',1,2,'29092020_Rincon S.A.__V4'),(53,'29-09-2020','01-10-2020',2,92000,'PROVEEDOR','sub_efip','efip1',1,2,'29092020_Rincon S.A.__V4');
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordresettoken`
--

DROP TABLE IF EXISTS `passwordresettoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passwordresettoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user` int(11) NOT NULL,
  `expiryDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hibernate_sequence_usuario` (`user`) USING BTREE,
  CONSTRAINT `FK_hibernate_sequence_usuario` FOREIGN KEY (`user`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordresettoken`
--

LOCK TABLES `passwordresettoken` WRITE;
/*!40000 ALTER TABLE `passwordresettoken` DISABLE KEYS */;
INSERT INTO `passwordresettoken` VALUES (1,'96cdd033-2bc5-4bf5-948f-a8ceca881398',16,'2020-08-05 00:00:00'),(2,'a0dfbc33-3744-4a57-8c4c-7f30f6a7374f',16,'2020-08-05 00:00:00'),(3,'9601e41c-80e5-4194-bb4a-2d08e0e316f6',16,'2020-08-05 00:00:00'),(4,'c2deb318-65cf-4d43-b01b-fc6a45ef295b',16,'2020-08-04 00:00:00'),(5,'b1641d8d-8218-462c-a6fa-f397d08175b4',16,'2020-08-05 00:00:00'),(6,'d5ee7536-5f29-4841-968f-76a908b5f9fe',16,'2020-08-05 00:00:00'),(7,'407343b0-2ac5-4c12-9bb9-a4968f83ee4f',16,'2020-08-05 00:00:00'),(8,'781015fc-48d6-4894-9a08-bd36ebd61a70',16,'2020-08-05 00:00:00'),(10,'261af65e-f821-4b5c-ae7a-1860919ada5d',16,'2020-08-05 00:00:00'),(11,'3a6467f3-d0a6-446a-9164-aa14985f1447',16,'2020-08-05 00:00:00'),(12,'387eddfb-531a-412e-b013-02787fc5daf9',16,'2020-08-05 00:00:00'),(13,'acbb5297-107e-4d00-a43a-47327ddfd997',16,'2020-08-05 00:00:00'),(14,'b301d966-63e8-46ae-a16e-c168ca23df20',16,'2020-08-05 00:00:00'),(15,'663e1e24-779e-4ffb-8519-ee72103e66ca',16,'2020-08-05 09:35:54'),(17,'0cb6b912-8d54-45b3-86f2-221debf8a42f',16,'2020-08-05 10:58:22'),(18,'0c95b74c-5583-4b79-86ca-db9785d35b69',16,'2020-08-05 11:24:56'),(19,'e361a0c2-b731-4a0a-a01e-bc69a53b9b96',16,'2020-08-05 11:55:45'),(20,'290837be-f430-481b-978e-4fc006942d21',16,'2020-08-08 19:21:02'),(21,'9b24142e-873b-4cdb-943e-89dbb9e410b2',16,'2020-08-08 23:06:07'),(23,'da7fab99-d7a4-4701-abe7-9ae185661069',16,'2020-08-08 23:31:50'),(24,'48b4ed77-5be9-45ff-b336-b10963c84e91',16,'2020-08-09 02:49:25'),(25,'090a46b8-f113-4333-bf1c-bcdf0afd1779',16,'2020-08-18 17:36:39'),(26,'9666d2c7-f017-40a5-bb57-43f960ab645d',16,'2020-08-18 17:39:26'),(33,'41594cef-a86c-4fb0-a94d-ce4e9d61c595',16,'2020-08-30 15:35:46'),(34,'a3dde17c-17a7-4c06-b074-d75c5aa1308c',23,'2020-09-29 10:55:07');
/*!40000 ALTER TABLE `passwordresettoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `idPerfiles` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idPerfiles`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'SUPERVISOR'),(2,'ADMINISTRADOR'),(3,'USUARIO');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cuit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cbu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `importe` double(22,0) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idProveedor`),
  KEY `FK_proveedor_usuario` (`idUsuario`),
  CONSTRAINT `FK_proveedor_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (2,2,'Luis SA','202032785845','3333333333333333333333',90000,0),(3,2,'Gonzalez SRL','20322717564','2222222222222222222222',90000,1),(5,2,'El Tatos RER','20428745643','4454545454544',5000000,0),(6,2,'Iana','30428754958','9999999999999999999999',2000,1);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fechaRegistro` date NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Pepe','pepe@pepe.com','test','$2a$10$FdCHV1rPMFayR47wExOT6.oEdk6HGr3AGTPr65EKG4PpOsOzu1RIS','2020-03-23',1),(3,'test2020','test2@test2.com','test2','$2a$10$aZRtwoYaQG0xJ.uz2nsJVutlRRp2hjOsjy3Z9HEgjItzDImLlar2u','2020-06-15',1),(5,'Super','super@super.com','super','$2a$10$Vc/d2vYHYMO/6RZjs6UMjuAAb33Kpr6H3YCuU2iubMf6YALtKuDRe','2020-06-15',1),(16,'Lalo','nestorchoele@gmail.com','kaka','$2a$10$Dyw5YpDwLx0W5OQSnkytDucB.rawQKZFgdtN34YMvU4PQ6XsfLaUy','2020-06-30',1),(17,'test23','test23@test23.com','test23','$2a$10$PEBJywjOWTfBn8zswn4Hi.xy7WFTgIK7ZZUZJCqAlBKiJPyXf24nW','2020-07-30',1),(18,'Nestor','admin@admin.com','admin','$2a$10$xWiCzNGrMqflQbkhX3SHjeF66MKVvDji8dHrppDYsxy60FE.RX0gi','2020-08-04',1),(20,'Pablo','pablo@pablo.com','pablo','$2a$10$mAilnTucngxjrSp4YK/WAeVzF0z09rSMaaiHoamsRDKvCvNH8voNC','2020-08-08',0),(21,'Miguel','miguel@miguel.com','miguel','$2a$10$VKb7h62OjDSZ/xjWbsvV4OKJWgLNUkcAirOLOwMIyFj0wny/.XLka','2020-08-08',0),(22,'Carmelo','carme@carme.com','testvacio','$2a$10$K.O64o0qe6ifnMAvY2Y2VO6YhjnS84/d3N11ClWEvaHwfwapgVnb.','2020-08-29',0),(23,'test3','nestornqn@icloud.com','test3','$2a$10$954kJdZkonMkDRoGo1AmeucVCcmmJEirs9fj/iRmdeo.UN4YCqKPG','2020-09-29',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarioperfil`
--

DROP TABLE IF EXISTS `usuarioperfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarioperfil` (
  `idUsuario` int(11) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  UNIQUE KEY `Usuario_Perfil_UNIQUE` (`idUsuario`,`idPerfil`),
  KEY `fk_perfiles_idx` (`idPerfil`),
  KEY `fk_usuarios_idx` (`idUsuario`),
  CONSTRAINT `FK_perfiles` FOREIGN KEY (`idPerfil`) REFERENCES `perfiles` (`idPerfiles`),
  CONSTRAINT `FK_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarioperfil`
--

LOCK TABLES `usuarioperfil` WRITE;
/*!40000 ALTER TABLE `usuarioperfil` DISABLE KEYS */;
INSERT INTO `usuarioperfil` VALUES (5,1),(18,2),(2,3),(16,3),(17,3),(20,3),(21,3),(22,3),(23,3);
/*!40000 ALTER TABLE `usuarioperfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bpngestiontest'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-28 23:38:54
