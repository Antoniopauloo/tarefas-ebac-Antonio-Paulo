# 📚 Projeto de Persistência em JPA - Módulo 36
### 👤 Autor: Antonio Paulo Camarda dos Santos

## 🌟 Descrição
Este projeto é uma implementação de persistência de dados utilizando JPA com suporte a múltiplos bancos de dados, como PostgreSQL e MySQL. O objetivo é permitir a gestão eficiente de entidades relacionadas a vendas, clientes e produtos.

## ⚙️ Estrutura do Projeto
- `persistence.xml`: Configurações de persistência para diferentes bancos de dados.
- `ClienteJpa`: Classe de entidade que representa um cliente.
- `VendaExclusaoJpaDAO`: Classe responsável pela exclusão de vendas.
- `AllTests`: Classe de suíte para execução de testes.
- `ClienteJpaDao2BancosTest`: Classe de teste para operações de cliente em múltiplos bancos de dados.

## 🛠️ Dependências
- Hibernate
- PostgreSQL JDBC Driver
- MySQL JDBC Driver

## 🚀 Como Usar
1. Configure seu banco de dados no arquivo `persistence.xml`.
2. Implemente suas entidades de acordo com as classes fornecidas.
3. Utilize os DAOs para gerenciar as operações de CRUD.

## 🧪 Testes
Os testes são realizados utilizando JUnit. Certifique-se de que os bancos de dados estão configurados corretamente antes de executar os testes.

## 📜 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues.
