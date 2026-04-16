package menus;

import java.util.Scanner;
import config.inputSuggest;
import service.orderService;

public class Menus {
    public static void menu1(Scanner sc){
        orderService manager = new orderService();
        while (true){
            System.out.println("--- QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem danh sách đơn hàng");
            System.out.println("5. Tính tổng tiền đơn hàng");
            System.out.println("6. Xóa đơn hàng");
            System.out.println("7. Thoát");
            int choice = inputSuggest.getInt(sc,"nhập lựa chọn của bạn: ");
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
                    manager.viewOrder();
                    break;
                case 5:
                    manager.orderTotal(sc);
                    break;
                case 6:
                    manager.deleteOrder(sc);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("vui lòng nhập từ 1 -> 7");
            }
        }
    }
}
