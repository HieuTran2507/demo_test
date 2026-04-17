package dao;

import config.ConnectionDB;
import entity.Total;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalDAO {
    public static List<Total> getTotal(Connection conn) throws SQLException {
        List<Total> totals = new ArrayList<>();
        String sql = "select o.id as order_id, " +
                "sum(oi.quantity * p.price) as total " +
                "from order_items oi " +
                "inner join orders o on oi.order_id = o.id " +
                "inner join products p on oi.product_id = p.id " +
                "group by o.id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Total t = new Total(
                    rs.getInt(1),
                    rs.getDouble(2)
            );
            totals.add(t);
        }
        return totals;
    }
}
