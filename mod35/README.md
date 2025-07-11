# 📚 Projeto Exemplo JPA - Módulo 35

Autor: Antonio Paulo Camarda dos Santos

Este projeto demonstra a configuração e o uso do JPA com Hibernate em uma aplicação Java, utilizando PostgreSQL como banco de dados.

## 🛠 Tecnologias Utilizadas

- Java
- JPA (Java Persistence API)
- Hibernate
- PostgreSQL

## 🗄 Estrutura do Projeto

- **anotacao**: Contém as anotações personalizadas para mapear entidades.
- **br.com.jeb.dao.factory**: Fábricas para conversão de ResultSet em entidades.
- **br.com.jeb.dao.generic.jdbc**: Fábrica de conexão com o banco de dados.
- **br.com.jeb.dao.generic**: Implementação genérica do DAO para persistência de dados.
  
## 🚀 Como Executar
- Clone o repositório.
- Configure o banco de dados PostgreSQL conforme as instruções acima.
- Execute a aplicação.

## 🤝 Contribuições
- Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou pull request.

## 📦 Configuração do Banco de Dados

Para configurar o banco de dados, edite o arquivo de persistência `persistence.xml`:

```xml
<persistence-unit name="ExemploJPA">
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:15432/vendas_online" />
        <property name="javax.persistence.jdbc.user" value="postgres" />
        <property name="javax.persistence.jdbc.password" value="admin" />
    </properties>
</persistence-unit>
```



