package dao;

import java.sql.*;

public class OrderDAO {
    public static Integer addOrder(Connection conn, Integer customer_id) throws SQLException {
        String sql = "insert into orders(customer_id) values (?);";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,customer_id);
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
        return rs>0;
    }
}
