package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import config.ConnectionDB;
import config.inputSuggest;
import dao.*;
import entity.*;

public class orderService {
    // 1. thêm khách hàng
    public void addCustomer(Scanner sc){
        String name = inputSuggest.getString(sc,"nhập tên khách hàng: ");
        Customer c = new Customer(name);
        Connection conn = ConnectionDB.openConnection();
        try {
            Boolean chk = CustomerDAO.addCustomer(conn,c);
            if (chk) System.out.println("thêm khách hàng thành công");
            else System.out.println("thêm khách hàng thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 2. Thêm sản phẩm
    public void addProduct(Scanner sc){
        String name = inputSuggest.getString(sc, "nhập tên sản phẩm: ");
        Double price = inputSuggest.getDou(sc,"nhập giá sản phẩm: ");
        Product p = new Product(name,price);
        Connection conn = ConnectionDB.openConnection();
        try {
            Boolean chk = ProductDAO.addProduct(conn,p);
            if (chk) System.out.println("thêm sản phẩm thành công");
            else System.out.println("thêm sản phẩm thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 3. tạo order
    public void createOrder(Scanner sc){
        Connection conn = ConnectionDB.openConnection();
        try {
            conn.setAutoCommit(false);
            // 1. add order
            Integer customerID = inputSuggest.getInt(sc,"nhập id khách hàng: ");
            Integer orderID = OrderDAO.addOrder(conn,customerID);
            System.out.println("---------------------");
            // 2. nhập item
            while (true){
                Integer productID = inputSuggest.getInt(sc,"nhập id sản phẩm: ");
                Integer quantity = inputSuggest.getInt(sc,"nhập số lượng sản phẩm: ");
                OrderItem oi = new OrderItem(orderID,productID,quantity);
                Boolean isAddOrderItem = OrderItemDAO.addOrderItem(conn,oi);
                if (!isAddOrderItem) System.out.println("thêm item thất bại");
                else {
                    String flg = inputSuggest.getString(sc,"nhập 1 để tiếp tục,nhập ký tự khác 1 để ngừng: ");
                    if (!flg.equals("1")) {
                        System.out.println("tạo order thành công");
                        conn.commit();
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn!=null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 4. Lấy danh sách đơn hàng
    public void viewOrder(){
        Connection conn = ConnectionDB.openConnection();
        try {
            List<viewOrder> view = ViewOrderDAO.view(conn);
            if(view.isEmpty()) System.out.println("danh sách rỗng");
            else {
                Map<Integer, List<viewOrder>> map = new LinkedHashMap<>();
                for (viewOrder v : view){
                    if (!map.containsKey(v.getOrderID())) map.put(v.getOrderID(),new ArrayList<>());
                    map.get(v.getOrderID()).add(v);
                }

                for(Map.Entry<Integer,List<viewOrder>> entry : map.entrySet()){
                    List<viewOrder> items = entry.getValue();
                    System.out.println("------------------");
                    System.out.print("order id: "+items.get(0).getOrderID());
                    System.out.println(", customer name: "+items.get(0).getCustomerName());

                    for (viewOrder v : items){
                        System.out.print("      product name: "+v.getProductName());
                        System.out.println(", quantity: "+v.getQuantity());
                    }
                    System.out.println("------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 5. Tính tổng tiền đơn hàng
    public void orderTotal(Scanner sc){
        Integer orderID = inputSuggest.getInt(sc,"nhập order id cần tính tổng tiền: ");
        Connection conn = ConnectionDB.openConnection();
        try {
            ViewTotal v = ViewTotalDAO.getTotal(conn,orderID);
            System.out.println(v);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 6. xóa dữ liệu
    public void deleteOrder(Scanner sc){
        Connection conn = ConnectionDB.openConnection();
        Integer orderID = inputSuggest.getInt(sc,"nhập order id cần xóa: ");
        try {
            Boolean oiChk = OrderItemDAO.delete(conn,orderID);
            if (!oiChk) System.out.println("xoóa order item thất bại");
            else {
                Boolean oChk = OrderDAO.delete(conn,orderID);
                if (!oChk) System.out.println("xóa order thất bại");
                else System.out.println("xoas order thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
