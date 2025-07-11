# Guia de Testes - EBAC Módulo 25

Este documento explica como executar e depurar os testes automatizados neste projeto Maven. O projeto utiliza JUnit 4 para testes e possui uma estrutura com DAOs que armazenam dados em um SingletonMap.

## Estrutura do Projeto

```
src/
  main/
    java/
      br/com/jeb/
        dao/           # Classes DAO para acesso a dados
          generic/     # Framework genérico para DAO
          ClienteDAO   # DAO específico para Cliente
          ProdutoDAO   # DAO específico para Produto
          VendaDAO     # DAO específico para Venda
        domain/        # Entidades de domínio
        exceptions/    # Exceções personalizadas
        services/      # Serviços de negócio
  test/
    java/
      br/com/jeb/      # Classes de teste
```

## Configuração do Ambiente

### Pré-requisitos
- JDK 11+
- Maven 3.6+
- Visual Studio Code (ou outro IDE de sua preferência)

### Configuração do VS Code
O projeto já inclui os seguintes arquivos de configuração para o VS Code:

- `.vscode/settings.json` - Configurações de Java
- `.vscode/launch.json` - Configurações de debug
- `.vscode/tasks.json` - Tarefas para build e testes

## Como Executar os Testes

### Via Maven (linha de comando)

1. Para executar todos os testes:
   ```
   mvn test
   ```

2. Para executar um teste específico:
   ```
   mvn test -Dtest=ClienteDAOTest
   mvn test -Dtest=ProdutoDAOTest
   mvn test -Dtest=VendaDAOTest
   ```

3. Para limpar e executar os testes:
   ```
   mvn clean test
   ```

### Via VS Code

1. Utilize as tarefas configuradas em `.vscode/tasks.json`:
   - `Build with Maven` - Compila o projeto
   - `Test with Maven` - Executa todos os testes
   - `Run ClienteDAOTest` - Executa apenas o ClienteDAOTest
   - `Run ProdutoDAOTest` - Executa apenas o ProdutoDAOTest
   - `Run VendaDAOTest` - Executa apenas o VendaDAOTest

2. Para acessar as tarefas:
   - Pressione `Ctrl+Shift+P` (Windows/Linux) ou `Cmd+Shift+P` (macOS)
   - Digite "Tasks: Run Task" e selecione a tarefa desejada

3. Para depurar, use as configurações em `.vscode/launch.json`:
   - `Run AllTests` - Executa e depura todos os testes
   - `Run ClienteDAOTest` - Executa e depura apenas o ClienteDAOTest
   - etc.

## Depuração e Limpeza dos Testes

### Problema de Singleton e Estado Compartilhado

Este projeto utiliza um `SingletonMap` para armazenar dados em memória, o que pode causar problemas ao executar testes sequenciais que dependem de um estado limpo.

#### Solução implementada:

1. Cada classe de teste reinicia o SingletonMap antes de cada teste:
   ```java
   @Before
   public void init() {
       // Limpa a instância do SingletonMap 
       br.com.jeb.dao.generic.SingletonMap.getInstance().getMap().clear();
       
       // Reinicializa os DAOs
       // ...
   }
   ```

2. A classe `AllTests` também limpa o SingletonMap antes de executar a suite de testes:
   ```java
   @BeforeClass
   public static void init() {
       SingletonMap.getInstance().getMap().clear();
       System.out.println("*** Iniciando execução de todos os testes ***");
   }
   ```

### Logs para Depuração

Os testes incluem logs via `System.out.println()` para facilitar a depuração:

```java
System.out.println("=================== Teste salvar Venda ===================");
System.out.println("Código da venda: " + venda.getCodigo());
// ... outros logs ...
```

## Dicas para Criar Novos Testes

### Padrão para Testes

1. **Preparação**: Configure os dados necessários para o teste
2. **Execução**: Execute a operação que está sendo testada
3. **Verificação**: Verifique se o resultado está correto

Exemplo:
```java
@Test
public void salvar() throws TipoChaveNaoEncontradaException {
    // Preparação
    produto.setCodigo("A2");
    
    // Execução
    Boolean retorno = produtoDao.cadastrar(produto);
    
    // Verificação
    Assert.assertTrue(retorno);
}
```

### Dicas Importantes

1. **Isolamento de Testes**: Cada teste deve ser independente e não depender do estado de outros testes
   ```java
   @Before
   public void init() {
       // Limpe dados e reinicie o estado antes de cada teste
   }
   ```

2. **Dados de Teste Explícitos**: Crie todos os dados necessários dentro do teste
   ```java
   @Test
   public void buscarTodos() throws TipoChaveNaoEncontradaException {
       // Crie explicitamente todos os dados que o teste precisa
       Cliente cliente2 = new Cliente();
       cliente2.setCpf(98765432100L);
       // ... configure outros dados ...
       clienteDao.cadastrar(cliente2);
       
       // Agora execute o teste
   }
   ```

3. **Mensagens de Assertion**: Use mensagens descritivas nos assertions
   ```java
   assertTrue("A lista deveria ter 2 clientes", list.size() == 2);
   ```

4. **Adicione Logs de Depuração**: Para facilitar a identificação de problemas
   ```java
   System.out.println("Valor total da venda: " + venda.getValorTotal());
   ```

## Problemas Comuns e Soluções

1. **Testes com Estado Compartilhado**
   - **Sintoma**: Testes passam individualmente, mas falham quando executados em conjunto
   - **Solução**: Limpe o estado no método `@Before` de cada classe de teste

2. **Métodos Ausentes em Interfaces**
   - **Sintoma**: Erro `cannot find symbol method`
   - **Solução**: Adicione o método à interface apropriada e implemente-o na classe concreta

3. **Testes Esperando Dados Específicos**
   - **Sintoma**: Asserts falhando ao verificar o tamanho de uma coleção
   - **Solução**: Crie explicitamente todos os dados necessários dentro do próprio teste

## Contribuição

Para contribuir com novos testes ou melhorias:

1. Certifique-se que os testes existentes estão passando
2. Adicione novos testes seguindo o padrão existente
3. Execute `mvn clean test` para garantir que tudo continua funcionando
