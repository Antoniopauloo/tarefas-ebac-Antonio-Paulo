-- Criação da sequência para o ID do estoque
CREATE SEQUENCE sq_estoque
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Criação da tabela de estoque
CREATE TABLE tb_estoque (
    id BIGINT NOT NULL DEFAULT nextval('sq_estoque'),
    id_produto BIGINT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    quantidade_minima INTEGER NOT NULL DEFAULT 1,
    ultima_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_estoque PRIMARY KEY (id),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (id_produto) 
        REFERENCES tb_produto (id),
    CONSTRAINT uk_produto_estoque UNIQUE (id_produto),
    CONSTRAINT ck_quantidade_positiva CHECK (quantidade >= 0),
    CONSTRAINT ck_quantidade_minima_positiva CHECK (quantidade_minima > 0)
);

-- Índices
CREATE INDEX idx_estoque_produto ON tb_estoque(id_produto);

-- Comentários
COMMENT ON TABLE tb_estoque IS 'Tabela que mantém o controle de estoque dos produtos';
COMMENT ON COLUMN tb_estoque.id IS 'Identificador único do registro de estoque';
COMMENT ON COLUMN tb_estoque.id_produto IS 'Identificador do produto relacionado';
COMMENT ON COLUMN tb_estoque.quantidade IS 'Quantidade atual em estoque';
COMMENT ON COLUMN tb_estoque.quantidade_minima IS 'Quantidade mínima permitida em estoque';
COMMENT ON COLUMN tb_estoque.ultima_atualizacao IS 'Data e hora da última atualização do estoque';
