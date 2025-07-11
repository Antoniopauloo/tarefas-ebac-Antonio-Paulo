-- Criação da sequência para o ID do produto_quantidade
CREATE SEQUENCE sq_produto_quantidade
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Criação da tabela de produto_quantidade (relacionamento entre Venda e Produto)
CREATE TABLE tb_produto_quantidade (
    id BIGINT NOT NULL DEFAULT nextval('sq_produto_quantidade'),
    id_venda BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    CONSTRAINT pk_prod_qtd PRIMARY KEY (id),
    CONSTRAINT uk_prod_qtd_venda_produto UNIQUE (id_venda, id_produto),
    CONSTRAINT fk_prod_qtd_venda FOREIGN KEY (id_venda) 
        REFERENCES tb_venda (id),
    CONSTRAINT fk_prod_qtd_produto FOREIGN KEY (id_produto) 
        REFERENCES tb_produto (id)
);

-- Índices
CREATE INDEX idx_prod_qtd_venda ON tb_produto_quantidade(id_venda);
CREATE INDEX idx_prod_qtd_produto ON tb_produto_quantidade(id_produto);

-- Comentários
COMMENT ON TABLE tb_produto_quantidade IS 'Tabela que mantém a quantidade de produtos em cada venda';
COMMENT ON COLUMN tb_produto_quantidade.id_venda IS 'Identificador da venda';
COMMENT ON COLUMN tb_produto_quantidade.id_produto IS 'Identificador do produto';
COMMENT ON COLUMN tb_produto_quantidade.quantidade IS 'Quantidade do produto na venda';
COMMENT ON COLUMN tb_produto_quantidade.valor_total IS 'Valor total do produto na venda';
