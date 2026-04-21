package main;

import config.ConnectionDB;

import java.sql.Connection;
import java.util.Scanner;
import config.inputSuggest;
import service.BookingService;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingService manager = new BookingService();
        while (true){
            System.out.println("--- QUẢN LÝ ĐẶT PHÒNG ---");
            System.out.println("1. Thêm khách");
            System.out.println("2. Thêm phòng");
            System.out.println("3. Đặt phòng");
            System.out.println("4. Xem danh sách booking");
            System.out.println("5. Tính tiền");
            System.out.println("6. Trả phòng");
            System.out.println("7. Thoát");

            Integer choice = inputSuggest.getInt(sc,"nhập lựa chọn của bạn: ");
            switch (choice){
                case 1:
                    manager.addGuest(sc);
                    break;
                case 2:
                    manager.addRoom(sc);
                    break;
                case 3:
                    manager.orderRoom(sc);
                    break;
                case 4:
                    manager.viewBooking();
                    break;
                case 5:
                    manager.total(sc);
                    break;
                case 6:
                    manager.checkout(sc);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn từ 1 - 7");
            }
        }
    }
}
