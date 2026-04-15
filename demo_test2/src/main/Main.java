package main;

import config.ConnectionDB;
import config.inputSuggest;
import entity.*;
import service.OrderService;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        OrderService manager = new OrderService();

        while (true) {
            System.out.println("\n*************** MENU QUẢN LÝ ĐƠN HÀNG ***************");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem danh sách đơn hàng");
            System.out.println("5. Tính tổng tiền đơn hàng");
            System.out.println("6. Xóa đơn hàng");
            System.out.println("7. Thoát");

            Integer choice = inputSuggest.getInt(sc,"nhập lựa chọn của bạn: ");
            switch (choice){
                case 1:
                    manager.addCustomer(sc);
                    break;
                case 2:
                    manager.addProduct(sc);
                    break;
                case 3:
                    manager.createOrder(sc);
                    break;
                case 4:
                    System.out.println("4. Xem danh sách đơn hàng");
                    break;
                case 5:
                    System.out.println("5. Tính tổng tiền đơn hàng");
                    break;
                case 6:
                    System.out.println("6. Xóa đơn hàng");
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("vui lòng chọn từ 1 - 7");
            }
        }
    }
}
