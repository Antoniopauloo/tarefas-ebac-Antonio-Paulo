# Projeto JDBC Alunos - Tarefa Módulo 29

## Aluno
**Nome:** Jonathan Euzébio Boza

## Descrição
Este projeto implementa um CRUD completo utilizando JDBC para as entidades Cliente e Produto.

## Estrutura do Projeto

- **Cliente**: Implementação completa de CRUD para Cliente
- **Produto**: Implementação completa de CRUD para Produto

## Funcionalidades Implementadas

### Cliente
- Cadastrar
- Consultar
- Excluir
- Buscar Todos
- Atualizar

### Produto
- Cadastrar
- Consultar
- Excluir
- Buscar Todos
- Atualizar

## Banco de Dados

O projeto utiliza PostgreSQL e assume a existência de um banco de dados chamado `vendas_online_2`.

## Tabelas

O script para criação das tabelas está disponível em `src/main/resources/scripts.sql`.

## Como Executar

1. Execute o script SQL para criar as tabelas necessárias
2. Execute os testes para verificar o funcionamento

## Testes

Os testes estão organizados em:
- `ClienteTest.java`: Testes para a entidade Cliente
- `ProdutoTest.java`: Testes para a entidade Produto
