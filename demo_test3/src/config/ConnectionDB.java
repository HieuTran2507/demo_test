package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    public static Connection openConnection(){
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/demo_test3?createDatabaseIfNotExist=true";
        String USERNAME = "root";
        String PASSWORD = "123456";
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection conn){
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
