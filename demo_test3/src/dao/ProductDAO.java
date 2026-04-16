package dao;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAO {
    public static Boolean addProduct(Connection conn, Product p) throws SQLException {
        String sql = "insert into products(name,price) values (?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,p.getName());
        ps.setDouble(2,p.getPrice());
        Integer rs = ps.executeUpdate();
        if (rs>0) return true;
        else return false;
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
