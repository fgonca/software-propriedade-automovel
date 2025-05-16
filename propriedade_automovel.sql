CREATE DATABASE propriedade_automovel;

USE propriedade_automovel;
CREATE TABLE `proprietario` (
  `numero` int PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `automovel` (
  `matricula` varchar(8) PRIMARY KEY,
  `marca` varchar(45) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `tipo` enum('ligeiro','pesado') NOT NULL,
  `proprietario` int NOT NULL,
  FOREIGN KEY (`proprietario`) REFERENCES `proprietario` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
