package config;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection openConnection(){
        String url = "jdbc:mysql://localhost:3306/demo_test6";
        String username = "root";
        String password = "123456";
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
