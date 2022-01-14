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
  PERGUNTA VARCHAR(500) DEFAULT '',
  DIFICULDADE VARCHAR(50) DEFAULT '',
  ALTERNATIVAS VARCHAR(2000) DEFAULT '',
  CORRETA TINYINT(1) DEFAULT 0,
  SITUACAO CHAR(1) DEFAULT 'A',
  PRIMARY KEY(CODIGO),
  FOREIGN KEY(CODAREA) REFERENCES TABARE(CODIGO)
);

CREATE TABLE TABPRO(
  CODIGO INT AUTO_INCREMENT NOT NULL,
  DONO INT NOT NULL,
  NOME VARCHAR(50) DEFAULT '',
  AREAGERAL INT NOT NULL,
  DIFICULDADE VARCHAR(50) DEFAULT '',
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
  FOREIGN KEY(PROVA) REFERENCES TABPRO(CODIGO),
  FOREIGN KEY(PERGUNTA) REFERENCES TABPER(CODIGO)
);