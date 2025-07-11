package br.com.jeb.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://localhost:15432/vendas_online_2";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123456";
	private static Connection connection;
	private ConnectionFactory(Connection connection) {}
	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = initConnection();
			return connection;
		} return connection;
	}
	private static Connection initConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
