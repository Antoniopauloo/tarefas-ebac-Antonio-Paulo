package br.com.jeb.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.jeb.dao.jdbc.ConnectionFactory;

/**
 * Classe utilitária para ajudar a diagnosticar problemas de banco de dados
 */
public class DatabaseHelper {

    public static void main(String[] args) {
        try {
            testConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Testa a conexão com o banco de dados e verifica as tabelas existentes
     */
    public static void testConnection() throws SQLException {
        Connection connection = null;

        try {
            System.out.println("Testando conexão com o banco de dados...");
            connection = ConnectionFactory.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");

            System.out.println("\nVerificando tabelas...");
            DatabaseMetaData metaData = connection.getMetaData();

            // Verifica tabela tb_cliente_2
            checkTable(connection, metaData, "tb_cliente_2");

            // Verifica tabela tb_produto
            checkTable(connection, metaData, "tb_produto");

            // Verifica sequences
            checkSequence(connection, "SQ_CLIENTE_2");
            checkSequence(connection, "SQ_PRODUTO");

        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * Verifica se a tabela existe e conta os registros
     */
    private static void checkTable(Connection connection, DatabaseMetaData metaData, String tableName)
            throws SQLException {
        ResultSet tables = metaData.getTables(null, null, tableName, null);

        if (tables.next()) {
            System.out.println("Tabela " + tableName + " encontrada!");

            // Conta registros
            PreparedStatement stm = null;
            ResultSet rs = null;

            try {
                String sql = "SELECT COUNT(*) FROM " + tableName;
                stm = connection.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    System.out.println("  - Total de registros: " + rs.getInt(1));
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null && !stm.isClosed()) {
                    stm.close();
                }
            }

            // Lista colunas
            System.out.println("  - Colunas:");
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                System.out.println("    * " + columns.getString("COLUMN_NAME") +
                        " (" + columns.getString("TYPE_NAME") + ")");
            }
            columns.close();

        } else {
            System.out.println("AVISO: Tabela " + tableName + " não encontrada!");
        }
        tables.close();
    }

    /**
     * Verifica se a sequence existe
     */
    private static void checkSequence(Connection connection, String sequenceName) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            System.out.println("\nVerificando sequence " + sequenceName + "...");
            String sql = "SELECT EXISTS (SELECT FROM pg_sequences WHERE sequencename = ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sequenceName.toLowerCase());
            rs = stm.executeQuery();

            if (rs.next() && rs.getBoolean(1)) {
                System.out.println("Sequence " + sequenceName + " encontrada!");

                // Pega o valor atual da sequence
                PreparedStatement stmSeq = connection
                        .prepareStatement("SELECT last_value FROM " + sequenceName.toLowerCase());
                ResultSet rsSeq = stmSeq.executeQuery();
                if (rsSeq.next()) {
                    System.out.println("  - Último valor: " + rsSeq.getLong(1));
                }
                rsSeq.close();
                stmSeq.close();
            } else {
                System.out.println("AVISO: Sequence " + sequenceName + " não encontrada!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar sequence: " + e.getMessage());
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
