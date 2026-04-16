package dao;

import entity.viewOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderDAO {
    public static List<viewOrder> view(Connection conn) throws SQLException {
        List<viewOrder> view = new ArrayList<>();
        String sql = "select o.id as order_id, c.name as customer_name, p.name as product_name, oi.quantity " +
                "from orders o inner join customers c on o.customer_id = c.id " +
                "inner join order_items oi on oi.order_id = o.id " +
                "inner join products p on p.id = oi.product_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            viewOrder v = new viewOrder(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );
            view.add(v);
        }
        return view;
    }
}
