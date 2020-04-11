package group35.cs421p3;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Helper {

    public static Connection getConnectionToDB() {

		final String connectionUrl = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		final String connectionUser = "cs421g35";
        final String connectionPwd = "spacex2020";
        Connection conn = null;

		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}
        
        try {
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}