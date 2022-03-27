DROP DATABASE IF EXISTS quizif;

CREATE DATABASE quizif
DEFAULT CHARSET UTF8;

USE quizif;

CREATE TABLE TABARE(
  CODIGO INT AUTO_INCREMENT NOT NULL,
  NOME VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(CODIGO)
);

CREATE TABLE TABPER(
  CODIGO INT AUTO_INCREMENT NOT NULL,
  CODAREA INT NOT NULL,
  DONO INT NOT NULL,
  PERGUNTA VARCHAR(1500) DEFAULT '',
  DIFICULDADE INT DEFAULT 0,
  ALTERNATIVAS VARCHAR(2004) DEFAULT '',
  CORRETA TINYINT(1) DEFAULT 0,
  SITUACAO CHAR(1) DEFAULT 'A',
  PRIMARY KEY(CODIGO),
  FOREIGN KEY(CODAREA) REFERENCES TABARE(CODIGO)
);

CREATE TABLE TABPRO(
  CODIGO INT AUTO_INCREMENT NOT NULL,
  DONO INT NOT NULL,
  NOME VARCHAR(75) DEFAULT '',
  AREAGERAL INT NOT NULL,
  DIFICULDADE INT DEFAULT 2,
  SITUACAO CHAR(1) DEFAULT 'A',
  PRIMARY KEY(CODIGO),
  FOREIGN KEY(AREAGERAL) REFERENCES TABARE(CODIGO)
);

CREATE TABLE TABUSU(
  CODIGO INT AUTO_INCREMENT NOT NULL,
  NOME VARCHAR(50) DEFAULT '',
  APELIDO VARCHAR(25) DEFAULT '',
  EMAIL VARCHAR(100) UNIQUE NOT NULL,
  SENHA VARCHAR(64) NOT NULL,
  SAL VARCHAR(32) NOT NULL,
  TIPO CHAR(1) DEFAULT 'C',
  PRIMARY KEY(CODIGO)
);

CREATE TABLE TABUSUPRO(
  PROVA INT NOT NULL,
  USUARIO INT NOT NULL,
  NUMPERGUNTAS INT DEFAULT 0,
  NUMACERTOS INT DEFAULT 0,
  FOREIGN KEY(PROVA) REFERENCES TABPRO(CODIGO)
    ON DELETE RESTRICT,
  FOREIGN KEY(USUARIO) REFERENCES TABUSU(CODIGO)
    ON DELETE CASCADE
);

CREATE TABLE TABPROPER(
  PROVA INT NOT NULL,
  PERGUNTA INT NOT NULL,
  FOREIGN KEY(PROVA) REFERENCES TABPRO(CODIGO)
    ON DELETE CASCADE,
  FOREIGN KEY(PERGUNTA) REFERENCES TABPER(CODIGO)
    ON DELETE RESTRICT
);

INSERT INTO TABARE (NOME) VALUES ("Matemática"), ("Língua Portugesa"), 
                                 ("Biologia"), ("História"), ("Química"), 
                                 ("Sociologia"), ("Física"), ("Filosofia"), 
                                 ("Educação Física"), ("Geografia"), 
                                 ("Banco de dados"), ("Linguagem de programação"), ("Conhecimento geral");
                               
INSERT INTO TABUSU (NOME, APELIDO, EMAIL, SENHA, SAL, TIPO) VALUES ("Mateus Roberto Algayer", "Roberto", "matealgayer@gmail.com", "ca6a59c04a0279d0795368e6fbda20240acae97a64d9130f7bfff9f20d580136", "02246a97e4af1aaa43de87904fce51c5", "A"),
                                                                   ("João Jorge Stahl Gomes", "Jorge", "joaogomes17i02@gmail.com", "ca6a59c04a0279d0795368e6fbda20240acae97a64d9130f7bfff9f20d580136", "02246a97e4af1aaa43de87904fce51c5", "J"),
                                                                   ("João Felipe Staub", "Staub", "joaostaub.va187@academico.ifsul.edu.br", "ca6a59c04a0279d0795368e6fbda20240acae97a64d9130f7bfff9f20d580136", "02246a97e4af1aaa43de87904fce51c5", "C");
                                                                  
INSERT INTO TABPER (CODAREA, DONO, PERGUNTA, DIFICULDADE, ALTERNATIVAS, CORRETA) VALUES (1, 1,"Quanto é 10 + 20 * 3 - 10?", 3, "60Ѫ80Ѫ-210Ѫ30", 1),
                                                                                        (12,2,"Como é representado a operador XOR em Java?", 3, "*Ѫ>Ѫ!Ѫ^", 4),
                                                                                        (11,3,"Em banco de dados, o que significa a sigla SGBD", 3, "Sistema Gerencial de Bases DocumentadasѪSistema gerenciador de bancos de dadosѪSó grandes bases de dadosѪSistema grande para bancos de dados",2),
                                                                                        (2, 1,"Qual a alternativa que preenche corretamente as lacunas do texto ao lado: 'Recorreu ___ irmã e ___ ela se apegou como ___ uma tábua de salvação.'", 1, "à - à - aѪà - a - àѪà - a - aѪa - a - a", 3);
                                                                                         
INSERT INTO TABPRO (DONO, NOME, AREAGERAL, DIFICULDADE) VALUES (1, "Prova sobre programação!!", 12, 2),
                                                               (2, "Prova geral sobre conteúdos diversos", 13, 3);
                                                               
INSERT INTO TABPROPER (PROVA, PERGUNTA) VALUES (1, 1),
                                               (1, 2),
                                               (1, 3),
                                               (2, 1),
                                               (2, 2),
                                               (2, 3),
                                               (2, 4);
