# Microserviços EBAC - Módulo 42

**Aluno:** Antonio Paulo Camarda dos Santos

Este projeto consiste em dois microserviços desenvolvidos com Spring Boot:
- Client Service (Porta 8081)
- Product Service (Porta 8082)

## 🏗️ Arquitetura

Cada microserviço segue a arquitetura em camadas:
- Controller (API REST)
- Service (Regras de Negócio)
- Repository (Acesso a Dados)
- Model (Entidades)

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- Banco de Dados H2 (em memória)
- Maven

## 📦 Microserviços

### 1️⃣ Client Service (Porta 8081)

Gerenciamento de clientes com operações CRUD.

#### Endpoints:
```
GET    /api/clients     - Lista todos os clientes
GET    /api/clients/{id} - Busca cliente por ID
POST   /api/clients     - Cria novo cliente
PUT    /api/clients/{id} - Atualiza cliente
DELETE /api/clients/{id} - Remove cliente
```

#### Modelo de Cliente:
```json
{
    "name": "João Silva",
    "email": "joao@email.com",
    "cpf": "123.456.789-00"
}
```

### 2️⃣ Product Service (Porta 8082)

Gerenciamento de produtos com operações CRUD.

#### Endpoints:
```
GET    /api/products     - Lista todos os produtos
GET    /api/products/{id} - Busca produto por ID
POST   /api/products     - Cria novo produto
PUT    /api/products/{id} - Atualiza produto
DELETE /api/products/{id} - Remove produto
```

#### Modelo de Produto:
```json
{
    "name": "Notebook Dell",
    "description": "Notebook Dell Inspiron 15",
    "price": 3500.00,
    "stock": 10
}
```

## 🚀 Como Executar

### Client Service:
```bash
cd client-service
mvn spring-boot:run
```
Acesse: http://localhost:8081/api/clients

### Product Service:
```bash
cd product-service
mvn spring-boot:run
```
Acesse: http://localhost:8082/api/products

## 📊 Banco de Dados

### Console H2:
- Client Service: http://localhost:8081/h2-console
- Product Service: http://localhost:8082/h2-console

Configurações do H2:
- JDBC URL: jdbc:h2:mem:clientdb (para clients) ou jdbc:h2:mem:productdb (para products)
- User: sa
- Password: (deixar em branco)

## 🧪 Exemplos de Uso

### Client Service:

```bash
# Criar cliente
curl -X POST http://localhost:8081/api/clients -H "Content-Type: application/json" -d "{\"name\":\"João Silva\",\"email\":\"joao@email.com\",\"cpf\":\"123.456.789-00\"}"

# Listar clientes
curl http://localhost:8081/api/clients
```

### Product Service:

```bash
# Criar produto
curl -X POST http://localhost:8082/api/products -H "Content-Type: application/json" -d "{\"name\":\"Notebook Dell\",\"description\":\"Notebook Dell Inspiron 15\",\"price\":3500.00,\"stock\":10}"

# Listar produtos
curl http://localhost:8082/api/products
```

## 📝 Estrutura do Projeto

```
├── client-service/
│   ├── src/main/java/com/ebac/client/
│   │   ├── controller/
│   │   │   └── ClientController.java
│   │   ├── model/
│   │   │   └── Client.java
│   │   ├── repository/
│   │   │   └── ClientRepository.java
│   │   └── service/
│   │       └── ClientService.java
│   └── src/main/resources/
│       └── application.properties
│
└── product-service/
    ├── src/main/java/com/ebac/product/
    │   ├── controller/
    │   │   └── ProductController.java
    │   ├── model/
    │   │   └── Product.java
    │   ├── repository/
    │   │   └── ProductRepository.java
    │   └── service/
    │       └── ProductService.java
    └── src/main/resources/
        └── application.properties
```

## 🔨 Requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Porta 8081 disponível (Client Service)
- Porta 8082 disponível (Product Service)
