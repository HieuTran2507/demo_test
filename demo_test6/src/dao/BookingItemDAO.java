package dao;

import entity.BookingItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingItemDAO {
    public static Boolean addBookingItem(Connection conn, BookingItem bi) throws SQLException {
        String sql = "insert into booking_item(booking_id,room_id) values (?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,bi.getBookingID());
        ps.setInt(2,bi.getRoomID());
        int rs = ps.executeUpdate();
        return rs>0;
    }

    public static Boolean delete(Connection conn,int bkID) throws SQLException {
        String sql = "delete from booking_item where booking_id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,bkID);
        int rs = ps.executeUpdate();
        return rs>0;
    }
}
