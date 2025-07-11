-- Criação da sequência para o ID do produto
CREATE SEQUENCE sq_produto
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Criação da tabela de produto
CREATE TABLE tb_produto (
    id BIGINT NOT NULL DEFAULT nextval('sq_produto'),
    codigo VARCHAR(50) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor NUMERIC(10,2) NOT NULL,
    categoria VARCHAR(50),
    marca VARCHAR(50),
    CONSTRAINT pk_produto PRIMARY KEY (id),
    CONSTRAINT uk_codigo UNIQUE (codigo)
);

-- Índices
CREATE INDEX idx_produto_codigo ON tb_produto(codigo);
CREATE INDEX idx_produto_categoria ON tb_produto(categoria);
CREATE INDEX idx_produto_marca ON tb_produto(marca);

-- Comentários
COMMENT ON TABLE tb_produto IS 'Tabela que mantém o cadastro dos produtos';
COMMENT ON COLUMN tb_produto.id IS 'Identificador único do produto';
COMMENT ON COLUMN tb_produto.codigo IS 'Código único do produto';
COMMENT ON COLUMN tb_produto.nome IS 'Nome do produto';
COMMENT ON COLUMN tb_produto.descricao IS 'Descrição detalhada do produto';
COMMENT ON COLUMN tb_produto.valor IS 'Valor unitário do produto';
COMMENT ON COLUMN tb_produto.categoria IS 'Categoria do produto';
COMMENT ON COLUMN tb_produto.marca IS 'Marca do produto';
