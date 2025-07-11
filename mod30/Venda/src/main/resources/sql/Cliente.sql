-- Criação da sequência para o ID do cliente
CREATE SEQUENCE sq_cliente
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Criação da tabela de cliente
CREATE TABLE tb_cliente (
    id BIGINT NOT NULL DEFAULT nextval('sq_cliente'),
    nome VARCHAR(100) NOT NULL,
    cpf BIGINT NOT NULL,
    tel BIGINT,
    endereco VARCHAR(100),
    numero INTEGER,    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(10),
    email VARCHAR(100),
    CONSTRAINT pk_cliente PRIMARY KEY (id),
    CONSTRAINT uk_cpf UNIQUE (cpf)
);

-- Índices
CREATE INDEX idx_cliente_cpf ON tb_cliente(cpf);
CREATE INDEX idx_cliente_email ON tb_cliente(email);

-- Comentários
COMMENT ON TABLE tb_cliente IS 'Tabela que mantém o cadastro dos clientes';
COMMENT ON COLUMN tb_cliente.id IS 'Identificador único do cliente';
COMMENT ON COLUMN tb_cliente.nome IS 'Nome do cliente';
COMMENT ON COLUMN tb_cliente.cpf IS 'CPF do cliente - deve ser único';
COMMENT ON COLUMN tb_cliente.tel IS 'Telefone do cliente';
COMMENT ON COLUMN tb_cliente.endereco IS 'Endereço do cliente';
COMMENT ON COLUMN tb_cliente.numero IS 'Número do endereço';
COMMENT ON COLUMN tb_cliente.cidade IS 'Cidade do cliente';
COMMENT ON COLUMN tb_cliente.estado IS 'Estado do cliente';
COMMENT ON COLUMN tb_cliente.cep IS 'CEP do endereço do cliente';
COMMENT ON COLUMN tb_cliente.email IS 'Email do cliente';
