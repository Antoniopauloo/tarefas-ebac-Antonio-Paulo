package br.com.jeb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexões com o banco de dados.
 * Esta implementação cria uma nova conexão a cada chamada ao método
 * getConnection(),
 * garantindo que cada operação tenha sua própria conexão.
 */
public class ConnectionFactory {

    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://localhost:15432/vendas_online_2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private ConnectionFactory() {
        // Construtor privado para evitar instanciação
    }

    /**
     * Obtém uma nova conexão com o banco de dados
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
