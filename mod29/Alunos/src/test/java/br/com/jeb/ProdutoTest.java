package br.com.jeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.IProdutoDao;
import br.com.jeb.dao.ProdutoDao;
import br.com.jeb.dao.jdbc.ConnectionFactory;
import br.com.jeb.domain.Produto;

public class ProdutoTest {

    private IProdutoDao dao;
    private Produto produto;

    @Before
    public void init() throws Exception {
        dao = new ProdutoDao();
        produto = new Produto();
        produto.setCodigo("P001");
        produto.setNome("Produto Teste");
        produto.setPreco(99.99);
        dao.cadastrar(produto);
    }

    @After
    public void end() throws Exception {
        Produto produtoBD = dao.consultar(produto.getCodigo());
        if (produtoBD != null) {
            dao.excluir(produtoBD);
        }
    }

    @Test
    public void testarConexao() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

            // Verifica se a tabela tb_produto existe
            String sql = "SELECT 1 FROM tb_produto LIMIT 1";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            assertTrue("Tabela tb_produto não existe ou está vazia", rs.next());
            System.out.println("Tabela tb_produto verificada com sucesso!");

            // Verifica a estrutura da tabela
            stm = connection.prepareStatement("SELECT * FROM tb_produto WHERE 1=0");
            ResultSet rsMetaData = stm.executeQuery();
            int columnCount = rsMetaData.getMetaData().getColumnCount();

            System.out.println("Estrutura da tabela tb_produto:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(" - " + rsMetaData.getMetaData().getColumnName(i) +
                        " (" + rsMetaData.getMetaData().getColumnTypeName(i) + ")");
            }

            assertTrue("A tabela tb_produto não tem o número esperado de colunas", columnCount >= 4);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Test
    public void cadastrarTest() throws Exception {
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getPreco(), produtoBD.getPreco());
    }

    @Test
    public void buscarTodosTest() throws Exception {
        List<Produto> produtos = dao.buscarTodos();
        assertNotNull(produtos);
        assertTrue(produtos.size() > 0);
    }

    @Test
    public void atualizarTest() throws Exception {
        // 1. Primeiro, garantimos que podemos buscar o produto
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull("Produto não encontrado no banco de dados", produtoBD);
        System.out.println("Produto encontrado para atualização: ID=" + produtoBD.getId() +
                ", Código=" + produtoBD.getCodigo() +
                ", Nome=" + produtoBD.getNome()); // 2. Alteramos os dados do produto
        produtoBD.setNome("Produto Atualizado");
        produtoBD.setPreco(149.99);

        try {
            // 3. Atualizamos o produto no banco de dados
            Integer qtde = dao.atualizar(produtoBD);
            System.out.println("Número de registros atualizados: " + qtde);

            // Assumimos que o teste passa se a atualização retornou 1 (1 registro
            // atualizado)
            assertTrue("A atualização não retornou 1 como esperado. Retorno: " + qtde, qtde == 1);

            // 4. Buscamos o produto novamente para confirmar a atualização
            Produto produtoAtualizado = dao.consultar(produto.getCodigo());
            assertNotNull("Produto não encontrado após atualização", produtoAtualizado);
            System.out.println("Produto após atualização: Nome=" + produtoAtualizado.getNome() +
                    ", Preço=" + produtoAtualizado.getPreco());
            assertEquals("O nome não foi atualizado corretamente", "Produto Atualizado", produtoAtualizado.getNome());
            assertEquals("O preço não foi atualizado corretamente", Double.valueOf(149.99),
                    produtoAtualizado.getPreco());
            assertEquals("O código foi alterado incorretamente", produto.getCodigo(), produtoAtualizado.getCodigo());
        } catch (Exception e) {
            System.err.println("Erro durante a atualização: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void excluirTest() throws Exception {
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);

        Integer qtdeDel = dao.excluir(produtoBD);
        assertTrue(qtdeDel == 1);
    }
}
