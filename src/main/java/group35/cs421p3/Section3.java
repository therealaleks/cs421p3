package group35.cs421p3;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Section3 {

    public static enum Profession { STAFF, ENGINEER, PILOT };

    public static Integer getSalaryFromYOE(Connection conn, String dateStartProfessionCandidate, Profession pCandidate) throws SQLException {
        
        SimpleRegression regression = new SimpleRegression();
        Statement stmt = null;
        String query = "select * from employees";
        LocalDate now = LocalDate.now();
        LocalDate dateStarted = null;
        LocalDate dateCandidate = LocalDate.parse(dateStartProfessionCandidate);
        Double dateDeltaCandidate = (double) ChronoUnit.DAYS.between(dateCandidate, now);
        Double dateDelta = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String cdate = rs.getString("certification_date");
                Double salary = (double) rs.getInt("salary");
                String ldate = rs.getString("license_date");
                if (pCandidate == Profession.ENGINEER && cdate != null) {
                    dateStarted = LocalDate.parse(cdate);
                } else if (pCandidate == Profession.PILOT && ldate != null) {
                    dateStarted = LocalDate.parse(ldate);
                } else {
                    continue;
                }
                dateDelta = (double) ChronoUnit.DAYS.between(dateStarted, now);
                regression.addData(dateDelta, salary);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        
        return (pCandidate == Profession.STAFF) ? 30000 : ((int) regression.predict(dateDeltaCandidate) / 1000) * 1000;
    }
}