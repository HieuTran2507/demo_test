package dao;

import config.ConnectionDB;
import entity.Customer;
import entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {
    public static Boolean insert(Connection conn, OrderItem oi){
        String sql = "insert into order_item(order_id,product_id,quantity) values (?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,oi.getOrderID());
            ps.setInt(2,oi.getProductID());
            ps.setInt(3,oi.getQuantity());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
