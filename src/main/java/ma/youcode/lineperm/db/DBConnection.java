package ma.youcode.lineperm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {
    };

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // String URL = "jdbc:sqlite:C:/Users/HP/Desktop/LogAnalyzer/audit.db";
                String URL = "jdbc:sqlite:audit.db";
                connection = DriverManager.getConnection(URL);
                System.out.println("bon connexion");
            } catch (SQLException e) {
                System.out.println("erreur : " + e.getMessage());
            }
        }
        return connection;
    }
}
