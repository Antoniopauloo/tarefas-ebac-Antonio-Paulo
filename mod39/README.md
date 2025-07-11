# Sistema de Gerenciamento de Contatos - EBAC Módulo 39

**Aluno**: Antonio Paulo Camarda dos Santos
**Módulo**: 39 - Servlets e JSP

## Descrição do Projeto
Sistema web moderno para gerenciamento de contatos desenvolvido com Java Servlets e JSP. O projeto implementa uma interface responsiva e intuitiva para operações de cadastro e exclusão de contatos.

## Tecnologias Utilizadas

- Java Servlets
- JSP (JavaServer Pages)
- HTML5 & CSS3
- Apache Tomcat 9
- Maven
- Design System próprio

## Estrutura do Projeto
- **Pacote**: `br.com.jeb`
- **Classes Principais**:
  - `Contato`: Modelo de dados para armazenamento das informações de contato
  - `ContatoServlet`: Servlet responsável pelo cadastro de novos contatos
  - `ExcluirContatoServlet`: Servlet responsável pela exclusão de contatos

### Funcionalidades
1. **Cadastro de Contatos**
   - Nome
   - Email
   - Telefone
   - Geração automática de ID

2. **Listagem de Contatos**
   - Visualização em formato de tabela
   - Exibição de todos os contatos cadastrados

3. **Exclusão de Contatos**
   - Remoção de contatos por ID

### Páginas do Sistema

1. **Página Inicial** (index.jsp)
   - Landing page moderna
   - Menu de navegação intuitivo
   - Links rápidos para funcionalidades principais

2. **Formulário de Cadastro** (formulario.jsp)
   - Design clean e responsivo
   - Validação em tempo real
   - Feedback visual de ações
   - Campos otimizados para mobile

3. **Lista de Contatos** (contatos.jsp)
   - Tabela responsiva com scroll horizontal
   - Estado vazio personalizado
   - Ações contextuais por contato
   - Atualização dinâmica após ações

### Instalação e Execução

1. **Pré-requisitos:**
   - Java JDK 11 ou superior
   - Apache Maven
   - Apache Tomcat 9

2. **Compilação:**
   ```bash
   mvn clean package
   ```

3. **Deploy:**
   - Copie o WAR para pasta `webapps` do Tomcat, ou
   - Use os scripts de automação fornecidos:
     ```bash
     deploy.bat   # Deploy da aplicação
     start.bat    # Inicia o Tomcat
     stop.bat     # Para o Tomcat
     ```

4. **Acesso:**
   - URL: `http://localhost:8080/modulo39`

### Screenshots Necessários para Entrega

Para evidenciar o funcionamento completo, capture:

1. 📸 **Página inicial** - Mostrando o design moderno
2. 📸 **Formulário vazio** - Layout responsivo
3. 📸 **Lista sem contatos** - Estado vazio personalizado
4. 📸 **Cadastro de contato** - Preenchimento do formulário
5. 📸 **Lista com contatos** - Exibição dos dados
6. 📸 **Exclusão** - Feedback da ação

## Autor

Desenvolvido por Antonio Paulo Camarda dos Santos - 2025
