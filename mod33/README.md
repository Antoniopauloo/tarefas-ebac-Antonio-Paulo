# Módulo 33 - Exercício JPA

Este projeto demonstra o uso do JPA (Java Persistence API) para gerenciar relacionamentos entre entidades em um sistema de banco de dados de carros.

## Autor
Antonio Paulo Camarda dos Santos

## Estrutura do Projeto

- `Brand`: Entidade que representa as marcas de carros
- `Car`: Entidade que representa os carros com relacionamento muitos-para-um com Brand
- `Acessorio`: Entidade que representa os acessórios com relacionamento muitos-para-muitos com Car

## Configuração do Banco de Dados

O projeto usa banco de dados H2 em memória. O banco de dados é configurado automaticamente com estas configurações:

- URL: jdbc:h2:mem:testdb
- Usuário: sa
- Senha: (vazio)
- URL do Console: http://localhost:8080/h2-console

## Executando o Projeto

1. Clone o repositório
2. Instale as dependências usando Maven
3. Execute a aplicação usando `mvn spring-boot:run`
4. Acesse o console H2 em http://localhost:8080/h2-console (use a URL exata)

## Relacionamentos entre Entidades

- Uma Marca pode ter muitos Carros (Um-para-Muitos)
- Cada Carro pertence a uma Marca (Muitos-para-Um)
- Carros podem ter múltiplos Acessórios (Muitos-para-Muitos)
- Cada Acessório pode estar associado a múltiplos Carros (Muitos-para-Muitos)
- Cars can have multiple Accessories (Many-to-Many)
- Each Accessory can be associated with multiple Cars (Many-to-Many)

## Technologies Used

- Java 11
- Maven
- JPA/Hibernate
- H2 Database

## Sample Queries

You can test the database relationships using these queries in the H2 console:

```sql
-- List all brands
SELECT * FROM BRANDS;

-- List all cars
SELECT * FROM CARS;

-- List all accessories
SELECT * FROM ACESSORIOS;

-- List all cars with their brand names
SELECT c.*, b.name as brand_name 
FROM CARS c 
JOIN BRANDS b ON c.brand_id = b.id;

-- List all cars with their accessories
SELECT c.model, a.nome as acessorio_nome
FROM CARS c 
JOIN CAR_ACESSORIO ca ON c.id = ca.car_id
JOIN ACESSORIOS a ON ca.acessorio_id = a.id;
```
