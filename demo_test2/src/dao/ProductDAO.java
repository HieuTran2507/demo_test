package dao;

import config.ConnectionDB;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static Boolean addProduct(Product p){
        Connection conn = ConnectionDB.openConnection();
        String sql = "insert into product(name,price) values (?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setDouble(2,p.getPrice());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        String sql = "select * from product;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return products;
    }
}
