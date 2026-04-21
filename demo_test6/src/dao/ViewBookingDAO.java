package dao;

import entity.ViewBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewBookingDAO {
    public static List<ViewBooking> getAll(Connection conn) throws SQLException {
        List<ViewBooking> bookings = new ArrayList<>();
        String sql = "select g.name, b.check_in, b.check_out, r.room_number, r.price " +
                "from guest g inner join booking b on g.id = b.guest_id " +
                "inner join booking_item bi on bi.booking_id = b.id " +
                "inner join room r on r.id = bi.room_id " +
                "order by g.name;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            ViewBooking v = new ViewBooking(
                    rs.getString(1),
                    rs.getDate(2).toLocalDate(),
                    rs.getDate(3).toLocalDate(),
                    rs.getString(4),
                    rs.getDouble(5)
            );
            bookings.add(v);
        }
        return bookings;
    }
}
