package service;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import config.inputSuggest;
import dao.*;
import entity.*;

public class BookingService {
    // 1. thêm khách
    public void addGuest(Scanner sc){
        try (Connection conn = ConnectionDB.openConnection()){
            String name = inputSuggest.getString(sc,"nhập tên khách hàng: ");
            Boolean chk = GuestDAO.addGuest(conn,name);
            if (chk) System.out.println("thêm khch hàng thành công");
            else System.out.println("thêm khách hàng thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. thêm phòng
    public void addRoom(Scanner sc){
        try (Connection conn = ConnectionDB.openConnection()){
            String roomNumber = inputSuggest.getString(sc,"nhập room number: ");
            Double price = inputSuggest.getDou(sc,"nhập giá phòng: ");
            String status = inputSuggest.getStt(sc,"nhập trạng thái phòng (available/occupied): ");
            Room r = new Room(roomNumber,price,status);
            Boolean chk = RoomDAO.addRoom(conn,r);
            if (chk) System.out.println("thêm phòng thành công");
            else System.out.println("thêm phòng thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. đặt phòng
    public void orderRoom(Scanner sc){
        try (Connection conn = ConnectionDB.openConnection()){
            conn.setAutoCommit(false);
            try {
                // nhập khách hàng
                List<Guest> guests = GuestDAO.getAll(conn);
                System.out.println("-----------");
                guests.forEach(System.out::println);
                System.out.println("-----------");
                Integer bookingID;
                while (true){
                    Integer guestID = inputSuggest.getInt(sc,"nhập id khách hàng: ");
                    Boolean guestFound = guests.stream().anyMatch(g -> g.getId() == guestID);
                    if (!guestFound) {
                        System.out.println("vui lòng chọn khách trong danh sách");
                        continue;
                    }
                    if (guestFound){
                        LocalDate checkin;
                        LocalDate checkout;
                        while (true){
                            checkin = inputSuggest.getDate(sc,"nhập ngày checkin: ");
                            checkout = inputSuggest.getDate(sc,"nhập ngày checkout: ");
                            if (checkout.isAfter(checkin)) break;
                            else System.out.println("nhập ngày checkout sau ngày checkin");
                        }

                        Booking b = new Booking(guestID,checkin,checkout);
                        // tạo booking
                        bookingID = BookingDAO.addBooking(conn,b);
                        if (bookingID == -1) throw new SQLException("lỗi tạo booking");
                        else System.out.println("đã tạo booking");
                        break;
                    }
                }

                //Kiểm tra tất cả phòng phải là AVAILABLE

                while (true){
                    List<Room> availableRooms = RoomDAO.isAvailable(conn);
                    System.out.println("-----------");
                    availableRooms.forEach(System.out::println);
                    System.out.println("-----------");
                    if (availableRooms.isEmpty()){
                        System.out.println("hết phòng, vui lòng đặt lại sau");
                        break;
                    }
                    Integer roomID = inputSuggest.getInt(sc,"nhập id phòng muốn đặt: ");
                    Boolean roomFound = availableRooms.stream().anyMatch(r->r.getId()==roomID);
                    if (!roomFound){
                        System.out.println("vui lonòng chọn phòng có sẵn trong danh sách");
                        continue;
                    }
                    if (roomFound){
                        // thêm booking item
                        BookingItem bi = new BookingItem(bookingID,roomID);
                        Boolean biChk = BookingItemDAO.addBookingItem(conn,bi);
                        if (!biChk) throw new SQLException("thêm booking item lỗi");
                        else {
                            // update status
                            Boolean updateFlg = RoomDAO.updateStatus(conn,"occupied",roomID);
                            if (!updateFlg) throw new SQLException("lôi update status");
                            else {
                                String stopFlg = inputSuggest.getString(sc,"nhập 1 để thêm tiếp, nhập ký tự khác 1 để ngừng: ");
                                if (!stopFlg.equals("1")){
                                    System.out.println("đặt phòng thành công");
                                    conn.commit();
                                    break;
                                }
                            }
                        }
                    }
                }

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. xem danh sách booking
    public void viewBooking(){
        try (Connection conn = ConnectionDB.openConnection()){
            List<ViewBooking> views = ViewBookingDAO.getAll(conn);
            if (views.isEmpty()) System.out.println("danh sách trống");
            else {
                Map<String,List<ViewBooking>> map = new LinkedHashMap<>();
                for (ViewBooking v : views){
                    if (!map.containsKey(v.getGuestName())) map.put(v.getGuestName(), new ArrayList<>());
                    map.get(v.getGuestName()).add(v);
                }

                for (Map.Entry<String, List<ViewBooking>> detail : map.entrySet()){
                    List<ViewBooking> details = detail.getValue();
                    System.out.println("--------------------------------");
                    System.out.print("Customer name: "+ details.get(0).getGuestName());
                    System.out.print(", checkin: "+ details.get(0).getCheckin());
                    System.out.println(", checkout: "+details.get(0).getCheckout());

                    for (ViewBooking v : details){
                        System.out.print("     Room number: "+v.getRoomNumber());
                        System.out.println(", price: "+ v.getPrice());
                    }
                    System.out.println("--------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Tính tổng tiền
    public void total(Scanner sc){
        try (Connection conn = ConnectionDB.openConnection()){
            Integer bkID = inputSuggest.getInt(sc,"nhập id booking: ");
            ViewTotal b = ViewTotalDAO.total(conn,bkID);
            System.out.println(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6. trả phòng
    public void checkout(Scanner sc){
        try (Connection conn = ConnectionDB.openConnection()){
            conn.setAutoCommit(false);
            try {
                // lấy booking id cần xóa
                Integer bkID = inputSuggest.getInt(sc,"nhập id booking cần trả phòng: ");
                Boolean flg1 = RoomDAO.updateStatusByBKID(conn,"available",bkID);
                if (!flg1) throw new SQLException("flag 1: lỗi trả phòng");
                else {
                    Boolean flg2 = BookingItemDAO.delete(conn,bkID);
                    if (!flg2) throw new SQLException("flag 2: lỗi xóa booking item");
                    else {
                        Boolean flg3 = BookingDAO.delete(conn,bkID);
                        if (!flg3) throw new SQLException("flag 3: lỗi xóa booking");
                        else {
                            System.out.println("trả phòng thành công");
                            conn.commit();
                        }
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
