package dao;

import entity.ViewTotal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ViewTotalDAO {
    public static ViewTotal getTotal(Connection conn, int orderID) throws SQLException {
        ViewTotal view = new ViewTotal();
        String sql = "SELECT oi.order_id, " +
                "SUM(p.price * oi.quantity) " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "WHERE oi.order_id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,orderID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            view.setOrderID(rs.getInt(1));
            view.setTotal(rs.getDouble(2));
        }
        return view;
    }
}
