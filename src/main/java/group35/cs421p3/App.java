package group35.cs421p3;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {

        Connection conn = Helper.getConnectionToDB();

        // Linear regression to help HR find competitive employee compensation
        try {
            Integer res = Section3.getSalaryFromYOE(conn, "1995-11-12", Section3.Profession.PILOT);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing Connection!");
            conn.close();
        }
    }

}
