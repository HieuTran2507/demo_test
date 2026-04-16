package dao;

import entity.Order;

import java.sql.*;

public class OrderDAO {
    public static Integer addOrder(Connection conn, int customerID) throws SQLException {
        String sql = "insert into orders(customer_id) values (?);";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,customerID);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        else return -1;
    }

    public static Boolean delete(Connection conn, int orderID) throws SQLException {
        String sql = "delete from orders where id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,orderID);
        int rs = ps.executeUpdate();
        if (rs > 0) return true;
        else return false;
    }
}
