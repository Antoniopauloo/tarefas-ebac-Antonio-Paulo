package br.com.jeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.jeb.dao.jdbc.ConnectionFactory;
import br.com.jeb.domain.Produto;

public class ProdutoDao implements IProdutoDao {

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_produto (ID, CODIGO, NOME, PRECO) VALUES (nextval('SQ_PRODUTO'),?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getCodigo());
            stm.setString(2, produto.getNome());
            stm.setDouble(3, produto.getPreco());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Produto consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
            }
            return produto;
        } catch (Exception e) {
            throw e;
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

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
            return produtos;
        } catch (Exception e) {
            throw e;
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

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_produto WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();

            // Verifica se o produto existe antes de tentar atualizá-lo
            PreparedStatement checkStm = connection.prepareStatement("SELECT 1 FROM tb_produto WHERE codigo = ?");
            checkStm.setString(1, produto.getCodigo());
            ResultSet rs = checkStm.executeQuery();

            boolean exists = rs.next();
            rs.close();
            checkStm.close();

            if (!exists) {
                System.err.println("AVISO: Tentativa de atualizar produto inexistente: " + produto.getCodigo());
                return 0; // Nenhum registro atualizado pois não existe
            }

            String sql = "UPDATE tb_produto SET nome = ?, preco = ? WHERE codigo = ?";
            System.out.println("Executando SQL: " + sql);
            System.out.println("Parâmetros: nome=" + produto.getNome() +
                    ", preco=" + produto.getPreco() +
                    ", codigo=" + produto.getCodigo());

            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.setString(3, produto.getCodigo());

            int result = stm.executeUpdate();
            System.out.println("Resultado da atualização: " + result + " registro(s) afetado(s)");
            return result;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
