-- Criação da sequência para o ID da venda
CREATE SEQUENCE sq_venda
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Criação da tabela de venda
CREATE TABLE tb_venda (
    id BIGINT NOT NULL DEFAULT nextval('sq_venda'),
    codigo VARCHAR(50) NOT NULL,
    id_cliente BIGINT NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    data_venda TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT pk_venda PRIMARY KEY (id),
    CONSTRAINT uk_codigo_venda UNIQUE (codigo),
    CONSTRAINT fk_venda_cliente FOREIGN KEY (id_cliente)
        REFERENCES tb_cliente (id)
);

-- Índices
CREATE INDEX idx_venda_codigo ON tb_venda(codigo);
CREATE INDEX idx_venda_cliente ON tb_venda(id_cliente);
CREATE INDEX idx_venda_data ON tb_venda(data_venda);
CREATE INDEX idx_venda_status ON tb_venda(status);

-- Comentários
COMMENT ON TABLE tb_venda IS 'Tabela que mantém as vendas realizadas';
COMMENT ON COLUMN tb_venda.id IS 'Identificador único da venda';
COMMENT ON COLUMN tb_venda.codigo IS 'Código único da venda';
COMMENT ON COLUMN tb_venda.id_cliente IS 'Identificador do cliente que realizou a compra';
COMMENT ON COLUMN tb_venda.valor_total IS 'Valor total da venda';
COMMENT ON COLUMN tb_venda.data_venda IS 'Data e hora da venda';
COMMENT ON COLUMN tb_venda.status IS 'Status da venda (INICIADA, CONCLUIDA, CANCELADA)';
