package it.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConfiguration {

    private static TestConfiguration testConfiguration;

    protected TestConfiguration() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");;
    }

    protected Connection getDriverConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/homework_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (testConfiguration == null) {
            testConfiguration = new TestConfiguration();
        }
        return testConfiguration.getDriverConnection();
    }
}
