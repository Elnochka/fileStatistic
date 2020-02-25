package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionJdbc {

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        try (InputStream in = ConnectionJdbc.class.getClassLoader().getResourceAsStream("db/liquibase.properties")) {
            properties.load(in);
        }
        String driver = properties.getProperty("driver");
        if (driver != null) {
            System.setProperty("driver", driver);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return getMariaDbConnection(url, username, password, driver);
    }

    public static Connection getMariaDbConnection(String url, String username, String password, String driver) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection connectionDb = DriverManager.getConnection(url, username, password);
        return connectionDb;
    }
}
