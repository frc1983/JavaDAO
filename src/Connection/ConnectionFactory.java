package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String URL = "jdbc:derby://localhost:1527/sample;create=true";
    public static final String USER = "app";
    public static final String PASSWORD = "app";
    public static final String DRIVER_CLASS = "org.apache.derby.jdbc.ClientDriver"; 
     
    
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
