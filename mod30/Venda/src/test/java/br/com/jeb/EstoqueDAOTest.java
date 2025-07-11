package br.com.jeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.After;
import org.junit.Test;

import br.com.jeb.dao.EstoqueDAO;
import br.com.jeb.dao.IEstoqueDAO;
import br.com.jeb.dao.generic.jdbc.ConnectionFactory;
import br.com.jeb.dao.IProdutoDAO;
import br.com.jeb.dao.ProdutoDAO;
import br.com.jeb.domain.Estoque;
import br.com.jeb.domain.Produto;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.MaisDeUmRegistroException;
import br.com.jeb.exceptions.TableException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueDAOTest {

    private IEstoqueDAO estoqueDao;
    private IProdutoDAO produtoDao;
    private Produto produto;

    public EstoqueDAOTest() {
        estoqueDao = new EstoqueDAO();
        produtoDao = new ProdutoDAO();
    }

    @After
    public void end() throws DAOException {
        excluirEstoque();
        excluirProduto();
    }

    private void excluirProduto() throws DAOException {
        this.produtoDao.excluir(this.produto.getCodigo());
    }

    private void excluirEstoque() throws DAOException {
        String sql = "DELETE FROM TB_ESTOQUE";
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERRO EXCLUINDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    protected Connection getConnection() throws DAOException {
        try {
            return ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new DAOException("ERRO ABRINDO CONEXAO COM O BANCO DE DADOS ", e);
        }
    }

    @Test
    public void cadastrarEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E1", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);
        assertNotNull(estoque.getId());
    }

    @Test
    public void buscarEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E2", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);

        Estoque estoqueBD = estoqueDao.buscarPorProduto(produto.getId());
        assertNotNull(estoqueBD);
        assertEquals(estoque.getId(), estoqueBD.getId());
        assertEquals(estoque.getQuantidade(), estoqueBD.getQuantidade());
    }

    @Test
    public void atualizarEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E3", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);

        estoque.setQuantidade(20);
        estoqueDao.atualizarEstoque(estoque);

        Estoque estoqueBD = estoqueDao.buscarPorProduto(produto.getId());
        assertNotNull(estoqueBD);
        assertEquals(Integer.valueOf(20), estoqueBD.getQuantidade());
    }

    @Test
    public void baixarEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E4", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);

        estoque.baixarEstoque(2);
        estoqueDao.atualizarEstoque(estoque);

        Estoque estoqueBD = estoqueDao.buscarPorProduto(produto.getId());
        assertNotNull(estoqueBD);
        assertEquals(Integer.valueOf(8), estoqueBD.getQuantidade());
    }

    @Test(expected = IllegalStateException.class)
    public void tentarBaixarMaisDoQueTemNoEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E5", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);

        estoque.baixarEstoque(20);
    }

    @Test
    public void reporEstoque()
            throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        produto = cadastrarProduto("E6", BigDecimal.TEN);
        Estoque estoque = criarEstoque(10);
        assertNotNull(estoque);

        estoque.adicionarEstoque(5);
        estoqueDao.atualizarEstoque(estoque);

        Estoque estoqueBD = estoqueDao.buscarPorProduto(produto.getId());
        assertNotNull(estoqueBD);
        assertEquals(Integer.valueOf(15), estoqueBD.getQuantidade());
    }

    private Produto cadastrarProduto(String codigo, BigDecimal valor)
            throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(valor);
        produto.setCategoria("categoria");
        produto.setMarca("marca");
        produtoDao.cadastrar(produto);
        return produto;
    }

    private Estoque criarEstoque(Integer quantidade) throws TipoChaveNaoEncontradaException, DAOException {
        Estoque estoque = new Estoque();
        estoque.setProduto(this.produto);
        estoque.setQuantidade(quantidade);
        estoque.setQuantidadeMinima(5);
        estoque.setUltimaAtualizacao(Instant.now());
        estoqueDao.cadastrar(estoque);
        return estoque;
    }
}
