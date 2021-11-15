CREATE TABLE IF NOT EXISTS `pessoas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `nome_social` varchar(30) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)