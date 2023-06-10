package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_java?useSSL=false&serverTimezone=UTC";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";

	private Connection connection;
	private Statement statement;

	public Conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Failed to connect to the database");
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		return statement;
	}

	public Connection getConnection() {
		return connection;
	}
	  public void close() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection");
            // e.printStackTrace();
        }
    }
}
