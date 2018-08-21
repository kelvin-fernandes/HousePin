--
-- Table structure for table `tipo_imovel`
--

DROP TABLE IF EXISTS `tipo_imovel`;

CREATE TABLE `tipo_imovel` (
  `tipo_imovel_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tipo_imovel_id`)
);

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;

CREATE TABLE `endereco` (
  `cep` varchar(8) NOT NULL,
  `logradouro` varchar(255) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cep`)
);

--
-- Table structure for table `anunciante`
--

DROP TABLE IF EXISTS `anunciante`;

CREATE TABLE `anunciante` (
  `anunciante_cpf` varchar(11) NOT NULL,
  `creci` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `razao_social` varchar(20) DEFAULT NULL,
  `endereco_numero` int NOT NULL,
  `endereco_id` int NOT NULL,
  PRIMARY KEY (`anunciante_cpf`),
  UNIQUE (`email`, `creci`),
  CONSTRAINT `FK_Anunciante_Endereco` FOREIGN KEY (`endereco_id`) REFERENCES `endereco`(`endereco_id`)
);

--
-- Table structure for table `anuncio`
--

DROP TABLE IF EXISTS `anuncio`;

CREATE TABLE `anuncio` (
  `anuncio_id` int(11) NOT NULL AUTO_INCREMENT,
  `situacao` varchar(10) DEFAULT NULL,
  `data_insercao` datetime DEFAULT CURRENT_TIMESTAMP(),
  `data_expiracao` smalldatetime DEFAULT NULL,
  `valor` decimal(10, 2) NOT NULL,
  `condominio` decimal(10, 2) NOT NULL,
  `iptu` decimal(10, 2) NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `condicao` varchar(20) NOT NULL,
  `finalidade` varchar(20) NOT NULL,
  `area_total` decimal(10, 2) NOT NULL,
  `area_util` decimal(10, 2) NOT NULL,
  `endereco_numero` int NOT NULL,
  `endereco_id` int NOT NULL,
  `anunciante_cpf` int NOT NULL,
  `tipo_imovel_id` int NOT NULL,
  PRIMARY KEY (`anuncio_id`),
  CONSTRAINT `FK_Anuncio_Endereco` FOREIGN KEY (`endereco_id`) REFERENCES `endereco`(`endereco_id`),
  CONSTRAINT `FK_Anuncio_Anunciante` FOREIGN KEY (`anunciante_cpf`) REFERENCES `anunciante`(`anunciante_cpf`),
  CONSTRAINT `FK_Anuncio_Tipo_Imovel` FOREIGN KEY (`tipo_imovel_id`) REFERENCES `tipo_imovel`(`tipo_imovel_id`)
);

--
-- Table structure for table `caracteristica`
--

DROP TABLE IF EXISTS `caracteristica`;

CREATE TABLE `caracteristica` (
  `caracteristica_id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`caracteristica_id`)
);

--
-- Table structure for table `anuncio_caracteristica`
--

DROP TABLE IF EXISTS `anuncio_caracteristica`;

CREATE TABLE `anuncio_caracteristica` (
  `anuncio_id` int(11) NOT NULL,
  `caracteristica_id` int(11) NOT NULL,
  PRIMARY KEY (`anuncio_id`, `caracteristica_id`),
  CONSTRAINT `FK_Anuncio_Caracteristica` FOREIGN KEY (`anuncio_id`) REFERENCES `anuncio`(`anuncio_id`),
  CONSTRAINT `FK_Caracteristica_Anuncio` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica`(`caracteristica_id`)
);

--
-- Table structure for table `imagem`
--

DROP TABLE IF EXISTS `imagem`;

CREATE TABLE `imagem` (
  `imagem_id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) DEFAULT NULL,
  `anuncio_id` int(11) NOT NULL,
  PRIMARY KEY (`imagem_id`),
  CONSTRAINT `FK_Imagem_Anuncio` FOREIGN KEY (`anuncio_id`) REFERENCES `anuncio`(`anuncio_id`)
);