
CREATE DATABASE `propriedade_automovel0`;


USE `propriedade_automovel0`;


CREATE TABLE `automovel` (
  `matricula` varchar(8) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `tipo` enum('LIGEIRO','PESADO') NOT NULL,
  `proprietario` int NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `proprietario` (`proprietario`),
  CONSTRAINT `automovel_ibfk_1` FOREIGN KEY (`proprietario`) REFERENCES `proprietario` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `proprietario` (
  `numero` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE VIEW `v_propriedade` AS select `automovel`.`matricula` AS `matricula`,`automovel`.`marca` AS `marca`,`automovel`.`cor` AS `cor`,`automovel`.`tipo` AS `tipo`,`proprietario`.`numero` AS `numero`,`proprietario`.`nome` AS `nome`,`proprietario`.`telefone` AS `telefone` from (`automovel` join `proprietario` on((`automovel`.`proprietario` = `proprietario`.`numero`))) order by `automovel`.`marca`;


INSERT INTO `automovel` VALUES ('LD1111AA','AUDI','AZUL','LIGEIRO',32),('LD2222BB','BMW','BRANCO','LIGEIRO',31),('LD3333CC','CITROEN','CASTANHO','LIGEIRO',30);
INSERT INTO `proprietario` VALUES (30,'ABEL','911111111'),(31,'BELA','922222222'),(32,'CARLOS','933333333');
