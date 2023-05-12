
DROP SCHEMA IF EXISTS `clinica_medica` ;

CREATE SCHEMA IF NOT EXISTS `clinica_medica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clinica_medica` ;

DROP TABLE IF EXISTS `clinica_medica`.`consultas` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`consultas` (
  `IdConsultas` INT NOT NULL AUTO_INCREMENT,
  `IdDelPacienteConsultas` INT NOT NULL,
  `IdDelMedicoConsultas` INT NOT NULL,
  `IdDeEspecialidadDelMedico` INT NOT NULL,
  `PorcentajeAPP` DECIMAL(3,2) NOT NULL,
  `FechaCreacion` DATE NOT NULL,
  `FechaAgendada` DATETIME NOT NULL,
  `PrecioConsulta` DECIMAL(12,2) NOT NULL,
  `InformeFinal` VARCHAR(800) NULL DEFAULT NULL,
  `EstadoConsulta` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`IdConsultas`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`especialidades` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`especialidades` (
  `IdEspecialidades` INT NOT NULL AUTO_INCREMENT,
  `NombreEspecialidad` VARCHAR(225) NOT NULL,
  `DescripcionEspecialidad` VARCHAR(800) NOT NULL,
  `EstadoEspecialidad` TINYINT NOT NULL,
  PRIMARY KEY (`IdEspecialidades`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`especialidadesmedicos` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`especialidadesmedicos` (
  `IdEM` INT NOT NULL AUTO_INCREMENT,
  `IdDelMedicoEM` INT NOT NULL,
  `IdEspecialidadEM` INT NOT NULL,
  `PrecioEspecialidad` DECIMAL(9,2) NOT NULL,
  `EstadoEM` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdEM`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`exameneslaboratorios` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`exameneslaboratorios` (
  `IdEL` INT NOT NULL AUTO_INCREMENT,
  `IdDelLabEL` INT NOT NULL,
  `IdExamenEL` INT NOT NULL,
  `PrecioExamen` DECIMAL(9,2) NOT NULL,
  `EstadoEL` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`IdEL`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`examenessolicitadosenlaboratorio` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`examenessolicitadosenlaboratorio` (
  `IdESL` INT NOT NULL AUTO_INCREMENT,
  `IdSolicitudLaboratorioESL` INT NOT NULL,
  `IdExamenESL` INT NOT NULL,
  `ArchivoExamenLaboratorioESL` MEDIUMBLOB NULL DEFAULT NULL,
  `NombreDeArchivoESL` VARCHAR(105) NULL DEFAULT NULL,
  PRIMARY KEY (`IdESL`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`examenessolicitadosenlasconsultas` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`examenessolicitadosenlasconsultas` (
  `IdEC` INT NOT NULL AUTO_INCREMENT,
  `IdConsultaEC` INT NOT NULL,
  `IdExamenEC` INT NOT NULL,
  `ArchivoExamen` MEDIUMBLOB NULL DEFAULT NULL,
  `NombreArchivo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`IdEC`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`gananciageneradalabs` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`gananciageneradalabs` (
  `IdGGL` INT NOT NULL AUTO_INCREMENT,
  `IdDeSolicitud` INT NOT NULL,
  `CantidadGeneradaLabAPP` DECIMAL(12,2) NOT NULL,
  `CantidadPagadaAPP` DECIMAL(12,2) NOT NULL,
  `IdDelExamen` INT NOT NULL,
  `FechaDeMovLab` DATE NOT NULL,
  PRIMARY KEY (`IdGGL`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`gananciageneradamedicos` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`gananciageneradamedicos` (
  `IdGGM` INT NOT NULL AUTO_INCREMENT,
  `IdConsultaGGM` INT NOT NULL,
  `CantidadGeneradaAppGGM` DECIMAL(12,2) NOT NULL,
  `CantidadGeneradaMedicoGGM` DECIMAL(12,2) NOT NULL,
  `FechaDeMov` DATE NOT NULL,
  PRIMARY KEY (`IdGGM`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`horariomedicos` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`horariomedicos` (
  `IdHM` INT NOT NULL AUTO_INCREMENT,
  `IdDelMedicoHM` INT NOT NULL,
  `HoraDeEntrada` TIME NOT NULL,
  `HoraDeSalida` TIME NOT NULL,
  PRIMARY KEY (`IdHM`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`porcentajedecobroappconsultas` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`porcentajedecobroappconsultas` (
  `IdPorcentajeAPPConsultas` INT NOT NULL AUTO_INCREMENT,
  `IdDeEspecialidadPorcentaje` INT NOT NULL,
  `Porcentaje` DECIMAL(3,2) NOT NULL,
  `FechaDeInicio` DATE NOT NULL,
  `FechaFinal` DATE NULL DEFAULT NULL,
  `Activa` TINYINT NOT NULL,
  PRIMARY KEY (`IdPorcentajeAPPConsultas`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`porcentajedecobroappexamenes` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`porcentajedecobroappexamenes` (
  `IdPorcentajeDeCobroAPPExamenes` INT NOT NULL AUTO_INCREMENT,
  `IdDelExamenPCE` INT NOT NULL,
  `PorcentajePCE` DECIMAL(3,2) NOT NULL,
  `FechaDeInicioPCE` DATE NOT NULL,
  `FechaFinalPCE` DATE NULL DEFAULT NULL,
  `ActivaPCE` TINYINT NOT NULL,
  PRIMARY KEY (`IdPorcentajeDeCobroAPPExamenes`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`recargaspacientes` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`recargaspacientes` (
  `IdRP` INT NOT NULL AUTO_INCREMENT,
  `Monto` DECIMAL(12,2) NOT NULL,
  `HoraFecha` DATETIME NOT NULL,
  `IdPaciente` INT NOT NULL,
  PRIMARY KEY (`IdRP`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`solicitudlaboratorio` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`solicitudlaboratorio` (
  `IdSolicitudLaboratorio` INT NOT NULL AUTO_INCREMENT,
  `IdDelPacienteSL` INT NOT NULL,
  `IdDelLaboratorioSL` INT NOT NULL,
  `PorcentajeAppSL` DECIMAL(3,2) NULL DEFAULT NULL,
  `FechaSolicitadoSL` DATE NOT NULL,
  `FechaFinalizadoSL` DATE NULL DEFAULT NULL,
  `EstadoSL` VARCHAR(225) NULL DEFAULT NULL,
  PRIMARY KEY (`IdSolicitudLaboratorio`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`tipodeexamenes` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`tipodeexamenes` (
  `IdTipoDeExamenes` INT NOT NULL AUTO_INCREMENT,
  `NombreExamen` VARCHAR(225) NOT NULL,
  `DescripcionExamen` VARCHAR(800) NOT NULL,
  `EstadoExamen` TINYINT NOT NULL,
  PRIMARY KEY (`IdTipoDeExamenes`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clinica_medica`.`usuariosmedic` ;

CREATE TABLE IF NOT EXISTS `clinica_medica`.`usuariosmedic` (
  `IdUsuario` INT NOT NULL AUTO_INCREMENT,
  `NombreUsuario` VARCHAR(225) NOT NULL,
  `Username` VARCHAR(225) NOT NULL,
  `Password` VARCHAR(225) NOT NULL,
  `Direccion` VARCHAR(225) NULL DEFAULT NULL,
  `CUI` VARCHAR(20) NULL DEFAULT NULL,
  `Telefono` VARCHAR(20) NULL DEFAULT NULL,
  `Email` VARCHAR(225) NOT NULL,
  `FechaNacimiento` DATE NOT NULL,
  `Tipo` VARCHAR(225) NOT NULL,
  `Saldo` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`IdUsuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

