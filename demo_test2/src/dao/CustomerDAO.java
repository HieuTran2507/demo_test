package dao;

import config.ConnectionDB;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static Boolean insert(Customer c){
        Connection conn = ConnectionDB.openConnection();
        String sql = "insert into customer(name) values (?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,c.getName());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static List<Customer> getAll(){
        Connection conn = ConnectionDB.openConnection();
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer c = new Customer(
                        rs.getInt(1),
                        rs.getString(2)
                );
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return customers;
    }


}
