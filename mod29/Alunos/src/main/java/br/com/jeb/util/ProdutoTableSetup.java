package br.com.jeb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.jeb.dao.jdbc.ConnectionFactory;

/**
 * Classe utilitária para criar a tabela do produto se ela não existir
 */
public class ProdutoTableSetup {

    public static void main(String[] args) {
        try {
            createTableIfNotExists();
            System.out.println("Configuração da tabela concluída com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cria a tabela de produtos se ela não existir
     */
    public static void createTableIfNotExists() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();

            // Verifica se a sequence existe
            boolean sequenceExists = checkIfExists(connection,
                    "SELECT 1 FROM pg_sequences WHERE sequencename = 'sq_produto'");

            if (!sequenceExists) {
                System.out.println("Criando sequence SQ_PRODUTO...");
                stm = connection.prepareStatement("CREATE SEQUENCE SQ_PRODUTO START WITH 1 INCREMENT BY 1");
                stm.executeUpdate();
                stm.close();
                System.out.println("Sequence SQ_PRODUTO criada com sucesso!");
            } else {
                System.out.println("Sequence SQ_PRODUTO já existe");
            }

            // Verifica se a tabela existe
            boolean tableExists = checkIfExists(connection,
                    "SELECT 1 FROM information_schema.tables WHERE table_name = 'tb_produto'");

            if (!tableExists) {
                System.out.println("Criando tabela tb_produto...");
                String createTableSQL = "CREATE TABLE tb_produto (" +
                        "id BIGINT NOT NULL, " +
                        "codigo VARCHAR(10) NOT NULL UNIQUE, " +
                        "nome VARCHAR(50) NOT NULL, " +
                        "preco NUMERIC(10,2) NOT NULL, " +
                        "PRIMARY KEY (id))";

                stm = connection.prepareStatement(createTableSQL);
                stm.executeUpdate();
                stm.close();
                System.out.println("Tabela tb_produto criada com sucesso!");
            } else {
                System.out.println("Tabela tb_produto já existe");

                // Verifica estrutura da tabela
                System.out.println("Verificando estrutura da tabela...");
                stm = connection.prepareStatement("SELECT * FROM tb_produto WHERE 1=0");
                ResultSet rs = stm.executeQuery();

                System.out.println("Colunas da tabela tb_produto:");
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(" - " + rs.getMetaData().getColumnName(i) +
                            " (" + rs.getMetaData().getColumnTypeName(i) + ")");
                }

                rs.close();
                stm.close();
            }

            // Inserir um produto de teste
            if (!checkIfExists(connection, "SELECT 1 FROM tb_produto WHERE codigo = 'TEST'")) {
                System.out.println("Inserindo produto de teste...");
                stm = connection.prepareStatement(
                        "INSERT INTO tb_produto (id, codigo, nome, preco) VALUES (nextval('SQ_PRODUTO'), 'TEST', 'Produto Teste', 10.0)");
                stm.executeUpdate();
                stm.close();
                System.out.println("Produto de teste inserido com sucesso!");
            } else {
                System.out.println("Produto de teste já existe");
            }

        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * Verifica se um registro existe executando uma consulta SQL
     */
    private static boolean checkIfExists(Connection connection, String sql) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
        }
    }
}
