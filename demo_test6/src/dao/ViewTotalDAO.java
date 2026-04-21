package dao;

import entity.Booking;
import entity.ViewTotal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTotalDAO {
    public static ViewTotal total(Connection conn, int bookingID) throws SQLException {
        ViewTotal b = new ViewTotal();
        String sql = "SELECT " +
                "b.id, " +
                "SUM(r.price * DATEDIFF(b.check_out, b.check_in)) as total " +
                "FROM booking_item bi " +
                "JOIN room r ON bi.room_id = r.id " +
                "JOIN booking b ON bi.booking_id = b.id " +
                "WHERE b.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,bookingID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            b.setId(rs.getInt(1));
            b.setTotal(rs.getDouble(2));
        }
        return b;
    }
}
