package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import config.*;
import dao.*;
import entity.*;

public class OrderService {
    // 1. thêm và hiển thị toàn bộ khách hàng
    public void addCustomer(Scanner sc){
        String name = inputSuggest.getString(sc,"nhập tên khách hàng: ");
        System.out.println("-----------------------------------");
        Connection conn = ConnectionDB.openConnection();
        try {
            Boolean chk = CustomerDAO.addCustomer(conn,name);
            if (!chk) System.out.println("thêm khách hàng thất bại");
            else {
                List<Customer> customers = CustomerDAO.getAll(conn);
                customers.forEach(System.out::println);
            }
            System.out.println("-----------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 2. thêm sản phẩm
    public void addProduct(Scanner sc){
        String name = inputSuggest.getString(sc,"nhập tên sản phẩm: ");
        Double price = inputSuggest.getDou(sc,"nhập giá sản phẩm: ");
        Connection conn = ConnectionDB.openConnection();
        try {
            Boolean chk = ProductDAO.addProduct(conn,name,price);
            if(!chk) System.out.println("thêm sản phẩm thất bại");
            else {
                List<Product> products = ProductDAO.getAll(conn);
                products.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 3. tạo order
    public void createOrder(Scanner sc){
        Connection conn = ConnectionDB.openConnection();
        Integer customerID = inputSuggest.getInt(sc,"nhập id khách hàng: ");
        System.out.println("-------------------------------------");
        try {
            conn.setAutoCommit(false);
            Integer orderID = OrderDAO.addOrder(conn, customerID);
            if(orderID==-1) System.out.println("thêm order lỗi");
            else {
                while (true){
                    Integer productID = inputSuggest.getInt(sc,"nhập id sản phâm: ");
                    Integer quantity = inputSuggest.getInt(sc,"nhập số lượng sản phẩm: ");
                    OrderItem oi = new OrderItem(orderID,productID,quantity);
                    Boolean oiChk = OrderItemDAO.addOrderItem(conn,oi);
                    if (!oiChk) System.out.println("thêm order item lỗi");
                    else {
                        String flg = inputSuggest.getString(sc,"nhập 1 để thêm tiếp item, nhập ký tự bất kỳ khác 1 để ngừng: ");
                        if (!flg.equals("1")){
                            System.out.println("tạo order thành công");
                            conn.commit();
                            break;
                        }
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
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 4. lấy danh sách order gồm thông tin order và tên khách hàng
    public void findAllOrders(){
        Connection conn = ConnectionDB.openConnection();
        try {
            List<ViewOrders> views = ViewOrdersDAO.getAll(conn);
            if (views.isEmpty()) System.out.println("danh sách trống");
            else {
                Map<Integer,List<ViewOrders>> map = new LinkedHashMap<>();
                for (ViewOrders v : views){
                    if(!map.containsKey(v.getOrderID())) map.put(v.getOrderID(),new ArrayList<>());
                    map.get(v.getOrderID()).add(v);
                }

                for (Map.Entry<Integer, List<ViewOrders>> i : map.entrySet()){
                    List<ViewOrders> items = i.getValue();
                    System.out.println("---------------------------");
                    System.out.println("Order id: "+items.get(0).getOrderID()+
                            ", Customer name: "+items.get(0).getCustomerName());

                    for (ViewOrders v : items){
                        System.out.println("   Product name: "+v.getProductName()+
                                ", quantity: "+v.getQuantity());
                    }
                    System.out.println("---------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 5. tinh tổng tiền đơn hàng
    public void calculateTotal(){
        Connection conn = ConnectionDB.openConnection();
        try {
            List<Total> totals = TotalDAO.getTotal(conn);
            if (totals.isEmpty()) System.out.println("danh sách rỗng");
            else totals.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // 6. xóa order
    public void deleteOrder(Scanner sc){
        Integer orderID = inputSuggest.getInt(sc,"nhập order id cần xóa: ");
        Connection conn = ConnectionDB.openConnection();
        try {
            Boolean oiDeleteChk = OrderItemDAO.delete(conn,orderID);
            if (!oiDeleteChk) System.out.println("xóa items thất bại");
            else {
                Boolean oDeleteChk = OrderDAO.delete(conn,orderID);
                if (!oDeleteChk) System.out.println("xóa order thất bại");
                else System.out.println("xóa order thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

}
