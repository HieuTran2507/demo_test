package dao;

import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static Boolean addCustomer(Connection conn, String name) throws SQLException {
        String sql = "insert into customers(name) values (?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        int rs = ps.executeUpdate();
        return rs>0;
    }
    public static List<Customer> getAll(Connection conn) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customers;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Customer c =new Customer(
                    rs.getInt(1),
                    rs.getString(2)
            );
            customers.add(c);
        }
        return customers;
    }
}
