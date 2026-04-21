package dao;

import entity.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    public static Boolean addGuest(Connection conn, String name) throws SQLException {
        String sql = "insert into guest(name) values (?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        int rs = ps.executeUpdate();
        return rs>0;
    }

    public static List<Guest> getAll(Connection conn) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "select * from guest;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Guest g = new Guest(
                    rs.getInt(1),
                    rs.getString(2)
            );
            guests.add(g);
        }
        return guests;
    }
}
