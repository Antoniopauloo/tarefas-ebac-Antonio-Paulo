# Módulo 40 - API REST com Spring Boot

Projeto desenvolvido por **Antonio Paulo Camarda dos Santos** como parte do curso de Backend Java da EBAC - Escola Britânica de Artes Criativas e Tecnologia.

## Sobre o Projeto

API REST para gerenciamento de produtos utilizando Spring Boot, demonstrando os conceitos de:
- Arquitetura REST
- Spring Boot
- JPA/Hibernate
- Banco de dados H2
- Testes unitários
- Documentação de API

## Tecnologias Utilizadas

- Java 11
- Spring Boot 2.7.13
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven

## Estrutura do Projeto

```
br.com.jeb.produtos_api
├── controller
│   └── ProdutoController.java    # Endpoints da API REST
├── model
│   └── Produto.java             # Entidade JPA
├── repository
│   └── ProdutoRepository.java   # Repositório Spring Data JPA
└── ProdutosApiApplication.java  # Classe principal
```

## Como Executar

1. Clone o repositório
2. Entre na pasta do projeto:
   ```
   cd backend/mod40
   ```
3. Execute o projeto:
   ```
   mvn spring-boot:run
   ```
4. Acesse a API em: http://localhost:8080/produtos

## Endpoints da API

- `GET /produtos` - Lista todos os produtos
- `GET /produtos/{id}` - Busca um produto específico
- `POST /produtos` - Cria um novo produto
- `PUT /produtos/{id}` - Atualiza um produto existente
- `DELETE /produtos/{id}` - Remove um produto

### Exemplo de Produto (JSON)

```json
{
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 15 polegadas",
    "preco": 3499.99,
    "quantidade": 10
}
```

## Banco de Dados

O projeto utiliza o H2 Database (em memória) com dados iniciais carregados automaticamente.

Para acessar o console do H2:
1. Acesse http://localhost:8080/h2-console
2. Configure:
   - JDBC URL: jdbc:h2:mem:produtosdb
   - User Name: sa
   - Password: (deixe em branco)

## Autor

**Jonathan Euzébio Boza**
- Módulo: 40
- Curso: Backend Java
- Instituição: EBAC

## Licença

Este projeto é parte do curso da EBAC e deve ser utilizado apenas para fins educacionais.
