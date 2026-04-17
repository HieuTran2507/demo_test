package dao;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static Boolean addProduct(Connection conn, String name, Double price) throws SQLException {
        String sql = "insert into products(name,price) values (?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setDouble(2,price);
        int rs = ps.executeUpdate();
        return rs>0;
    }
    public static List<Product> getAll(Connection conn) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select * from products;";
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
        return products;
    }
}
