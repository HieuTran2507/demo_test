package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import config.ConnectionDB;
import config.inputSuggest;
import dao.*;
import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.Product;

public class OrderService {
    // 1. thêm khách hàng
    public void addCustomer(Scanner sc){
        String name = inputSuggest.getString(sc,"nhập tên khách hàng: ");
        Customer c = new Customer(name);
        Boolean chk = CustomerDAO.insert(c);
        if (chk) System.out.println("thêm khách hàng thành công");
        else System.out.println("thêm khách hàng thất bại");
    }

    // 2. thêm sản phẩm
    public void addProduct(Scanner sc){
        String name = inputSuggest.getString(sc,"nhập tên sản phẩm: ");
        Double price = inputSuggest.getDou(sc,"nhập giá sản phẩm: ");
        Product p = new Product(name,price);
        Boolean chk = ProductDAO.addProduct(p);
        if (chk) System.out.println("thêm sản phẩm thành công");
        else System.out.println("thêm sản phẩm không thành công");
    }

    // 3. tạo đơn hàng
    public void createOrder(Scanner sc){
        // nhập id khách hàng
        Integer customer_id = inputSuggest.getInt(sc,"nhập id khách hàng: ");

        Connection conn = ConnectionDB.openConnection();
        try {
            conn.setAutoCommit(false);
            // tạo order
            Integer orderID = OrderDAO.insert(conn, new Order(customer_id));
            if (orderID == -1) throw new SQLException("tạo order thất bại");
            // nhập item
            String flg = "";
            do {
                Integer product_id = inputSuggest.getInt(sc,"nhập id sản phẩm: ");
                Integer quantity = inputSuggest.getInt(sc,"nhập số lượng sản phẩm: ");
                // inser order item
                OrderItem item = new OrderItem(orderID,product_id,quantity);
                System.out.println(item);
                Boolean oiChk = OrderItemDAO.insert(conn,item);
                if (!oiChk) throw new SQLException("thêm item thaast bại");
                flg = inputSuggest.getString(sc,"nhập ký tự khác 1 để ngừng: ");
            }while (flg.equals("1"));
            conn.commit();
            System.out.println("tạo đơn hàng thành công");

        } catch (SQLException e) {
            System.out.println("lỗi tạo đơn hàng, rollback");
            if(conn!=null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }



    }
}
