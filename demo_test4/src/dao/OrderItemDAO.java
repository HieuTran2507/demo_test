package dao;

import entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {
    public static Boolean addOrderItem(Connection conn, OrderItem oi) throws SQLException {
        String sql = "insert into order_items(order_id,product_id,quantity) values (?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,oi.getOrder_id());
        ps.setInt(2,oi.getProduct_id());
        ps.setInt(3,oi.getQuantity());
        int rs = ps.executeUpdate();
        return rs>0;
    }

    public static Boolean delete(Connection conn, int orderID) throws SQLException {
        String sql = "delete from order_items where order_id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,orderID);
        int rs = ps.executeUpdate();
        return rs>0;
    }
}
