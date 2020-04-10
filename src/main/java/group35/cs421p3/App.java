package group35.cs421p3;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {

		final String connectionUrl = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		final String connectionUser = "cs421g35";
		final String connectionPwd = "spacex2020";

		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPwd);
		Statement statement = conn.createStatement();
		try {
			System.out.println("Hello World!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
			conn.close();
		}
		
	}

}
