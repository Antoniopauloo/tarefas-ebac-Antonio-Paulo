# Módulo 32 - Exercício JPA

**Aluno:** Antonio Paulo Camarda dos Santos

Este é um projeto Java que demonstra o uso do JPA (Java Persistence API) com Hibernate para mapeamento objeto-relacional.

## Estrutura do Projeto

- `Produto.java`: Classe de entidade com mapeamento JPA
- `Main.java`: Classe principal para testar a persistência
- `persistence.xml`: Arquivo de configuração do JPA

## Características da Classe Produto

A classe `Produto` possui três propriedades principais:
1. `nome` (String)
2. `preco` (BigDecimal)
3. `quantidade` (Integer)

## Como Executar

1. Certifique-se de ter o Java 11 ou superior instalado
2. Execute a classe `Main` para testar a persistência de um produto

O projeto utiliza um banco de dados H2 em memória para facilitar os testes.
