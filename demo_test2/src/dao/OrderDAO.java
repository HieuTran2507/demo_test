package dao;

import config.ConnectionDB;
import entity.Customer;
import entity.Order;

import java.sql.*;

public class OrderDAO {
    // yêu cầu trả về order id vừa tạo
    public static Integer insert(Connection conn, Order o){
        Integer orderID = -1;
        String sql = "insert into orders(customer_id) values (?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getCustomerID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) orderID = rs.getInt(1);
            else orderID = -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return orderID;
    }
}
