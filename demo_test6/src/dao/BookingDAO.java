package dao;

import entity.Booking;

import java.sql.*;
import java.util.List;

public class BookingDAO {
    public static Integer addBooking(Connection conn, Booking b) throws SQLException {
        String sql = "insert into booking(guest_id,check_in,check_out) values (?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,b.getGuestID());
        ps.setDate(2,java.sql.Date.valueOf(b.getCheckin()));
        ps.setDate(3,java.sql.Date.valueOf(b.getCheckout()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        else return -1;
    }

    public static Boolean delete(Connection conn,int bkID) throws SQLException {
        String sql = "delete from booking where id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,bkID);
        int rs = ps.executeUpdate();
        return rs>0;
    }
}
