DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `ID` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Senha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Acesso` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`ID`, `Nome`, `Email`, `Senha`, `Acesso`) VALUES
(1, 'admin', 'admin@admin.com', 'admin', 2),
(2, 'user', 'user@user.com', 'user', 1);
COMMIT;

DROP TABLE IF EXISTS `contas`;
CREATE TABLE IF NOT EXISTS `contas` (
  `ID` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `NumConta` varchar(100) DEFAULT NULL,
  `Titular` varchar(100) DEFAULT NULL,
  `Saldo` decimal(10,0) DEFAULT NULL,
  `TipoConta` int DEFAULT NULL,
  `UsuarioID` int UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `UsuarioID` (`UsuarioID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `contas` (`ID`, `NumConta`, `Titular`, `Saldo`, `TipoConta`, `UsuarioID`) VALUES
(1, '000001', 'Fabricio Lima', '500', 1, 2);
COMMIT;