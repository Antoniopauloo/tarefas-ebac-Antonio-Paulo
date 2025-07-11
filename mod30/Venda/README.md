# Projeto de Vendas - EBAC (Módulo 30)

Desenvolvido por: **Antonio Paulo Camarda dos Santos**

## Descrição
Este projeto implementa um sistema de vendas completo com gerenciamento de estoque integrado. O sistema inclui:
- Cadastro de Clientes (com email)
- Cadastro de Produtos (com marca)
- Registro e Gerenciamento de Vendas
- Controle de Estoque Automático
  - Validação em tempo real
  - Baixa automática na venda
  - Controle de quantidade mínima
- Transações Atômicas
  - Garantia de consistência entre venda e estoque
  - Rollback automático em caso de falhas

## Estrutura do Projeto

### Scripts SQL
Os scripts SQL estão localizados em: `src/main/resources/sql/`

- `Cliente.sql`: Estrutura da tabela de clientes
- `Produto.sql`: Estrutura da tabela de produtos
- `Venda.sql`: Estrutura da tabela de vendas
- `produto_quantidade.sql`: Estrutura da tabela de relacionamento entre produtos e vendas

### Classes Principais
- `Cliente`: Representa o cadastro de clientes
- `Produto`: Representa o cadastro de produtos
- `Venda`: Gerencia as vendas
- `ProdutoQuantidade`: Gerencia a quantidade de produtos em cada venda

### Camada DAO
- `ClienteDAO`: Persistência e validações de clientes
- `ProdutoDAO`: Persistência e validações de produtos
- `VendaDAO`: Gerenciamento transacional de vendas
- `EstoqueDAO`: Controle e validação de estoque

## Funcionalidades

### Clientes
- Cadastro completo com validação de email
- Consulta, atualização e exclusão
- Verificação de duplicidade de CPF

### Produtos
- Cadastro com marca e categoria
- Gerenciamento de preço e descrição
- Integração com controle de estoque
- Validação de código único

### Vendas
- Suporte a múltiplos produtos por venda
- Status: INICIADA, CONCLUIDA, CANCELADA
- Validação automática de estoque
- Cálculo automático do valor total
- Transações atômicas garantindo consistência
- Rollback automático em caso de erros

### Controle de Estoque
- Validação em tempo real de disponibilidade
- Baixa automática na conclusão da venda
- Controle de quantidade mínima
- Registro de última atualização (timestamp)
- Prevenção de vendas sem estoque
- Atualização transacional

## Configuração e Execução

### 1. Preparação do Banco de Dados
```sql
-- Limpeza inicial (se necessário)
DROP TABLE IF EXISTS tb_produto_quantidade CASCADE;
DROP TABLE IF EXISTS tb_venda CASCADE;
DROP TABLE IF EXISTS tb_estoque CASCADE;
DROP TABLE IF EXISTS tb_produto CASCADE;
DROP TABLE IF EXISTS tb_cliente CASCADE;

DROP SEQUENCE IF EXISTS sq_produto_quantidade;
DROP SEQUENCE IF EXISTS sq_venda;
DROP SEQUENCE IF EXISTS sq_estoque;
DROP SEQUENCE IF EXISTS sq_produto;
DROP SEQUENCE IF EXISTS sq_cliente;
```

### 2. Ordem de Execução dos Scripts
Os scripts estão em `src/main/resources/sql/` e devem ser executados na seguinte ordem:
1. `Cliente.sql` - Tabela de clientes com email
2. `Produto.sql` - Tabela de produtos com marca
3. `Estoque.sql` - Controle de estoque
4. `Venda.sql` - Registro de vendas
5. `produto_quantidade.sql` - Relacionamento venda-produtos

### 3. Execução dos Testes
Os testes unitários validam todas as funcionalidades:
```bash
mvn test
```

## Tecnologias e Ferramentas
- Java 11+
- PostgreSQL 12+
- JUnit 4 para testes
- Maven para build e dependências
- Transações ACID
- Padrão DAO

## Testes Unitários
Cobertura completa das funcionalidades:
- `ClienteDAOTest`: CRUD de clientes
- `ProdutoDAOTest`: CRUD de produtos
- `EstoqueDAOTest`: Controle de estoque
- `VendaDAOTest`: Fluxo completo de vendas
  - Criação de vendas
  - Adição/remoção de produtos
  - Validação de estoque
  - Finalização e cancelamento
  - Transações e rollback
