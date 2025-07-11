-- Script para criar as tabelas necessárias

-- Tabela de Cliente
CREATE SEQUENCE IF NOT EXISTS SQ_CLIENTE_2 START WITH 1 INCREMENT BY 1;

-- Drop e recrie a tabela se estiver com problemas
-- DROP TABLE IF EXISTS tb_cliente_2;

CREATE TABLE IF NOT EXISTS tb_cliente_2 (
    id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela de Produto
CREATE SEQUENCE IF NOT EXISTS SQ_PRODUTO START WITH 1 INCREMENT BY 1;

-- Drop e recrie a tabela se estiver com problemas
-- DROP TABLE IF EXISTS tb_produto;

CREATE TABLE IF NOT EXISTS tb_produto (
    id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nome VARCHAR(50) NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (id)
);
