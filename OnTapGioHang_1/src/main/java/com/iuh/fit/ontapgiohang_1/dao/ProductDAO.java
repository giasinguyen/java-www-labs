package com.iuh.fit.ontapgiohang_1.dao;

import com.iuh.fit.ontapgiohang_1.model.Product;
import com.iuh.fit.ontapgiohang_1.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = dbUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                products.add(Product
                        .builder()
                        .id(resultSet.getInt("ID"))
                        .imgURL(resultSet.getString("IMGURL"))
                        .description(resultSet.getString("DESCRIPTION"))
                        .model(resultSet.getString("MODEL"))
                        .price(resultSet.getDouble("PRICE"))
                        .quantity(resultSet.getInt("QUANTITY"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE ID = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Product
                        .builder()
                        .id(resultSet.getInt("ID"))
                        .imgURL(resultSet.getString("IMGURL"))
                        .description(resultSet.getString("DESCRIPTION"))
                        .model(resultSet.getString("MODEL"))
                        .price(resultSet.getDouble("PRICE"))
                        .quantity(resultSet.getInt("QUANTITY"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
