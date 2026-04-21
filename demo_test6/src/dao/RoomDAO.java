package dao;

import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public static Boolean addRoom(Connection conn, Room r) throws SQLException {
        String sql = "insert into room(room_number, price, status) values (?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,r.getRoomNumber());
        ps.setDouble(2,r.getPrice());
        ps.setString(3,r.getStatus());
        int rs = ps.executeUpdate();
        return rs>0;
    }

    public static List<Room> getAll(Connection conn) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "select * from room;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Room r = new Room(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4)
            );
            rooms.add(r);
        }
        return rooms;
    }

    public static Boolean updateStatus(Connection conn, String status, Integer id) throws SQLException {
        String sql = "update room set status = ? where id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,status);
        ps.setInt(2,id);
        int rs = ps.executeUpdate();
        return rs>0;
    }

    public static Boolean updateStatusByBKID(Connection conn, String status, Integer BKID) throws SQLException {
        String sql = "update room r " +
                "inner join booking_item bi on bi.room_id = r.id " +
                "set r.status = ? " +
                "where bi.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,status);
        ps.setInt(2,BKID);
        int rs = ps.executeUpdate();
        return rs>0;
    }



    public static List<Room> isAvailable(Connection conn) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "select * from room where status = 'available';";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Room r = new Room(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4)
            );
            rooms.add(r);
        }
        return rooms;
    }
}
