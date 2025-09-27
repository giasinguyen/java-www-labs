package com.iuh.fit.ontapgiohang_2.dao;

import com.iuh.fit.ontapgiohang_2.model.Product;
import com.iuh.fit.ontapgiohang_2.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE ID = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return Product
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .price(resultSet.getDouble("PRICE"))
                            .quantity(resultSet.getInt("QUANTITY"))
                            .description(resultSet.getString("DESCRIPTION"))
                            .imgURL(resultSet.getString("IMGURL"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                productList.add(Product
                        .builder()
                        .id(resultSet.getInt("ID"))
                        .price(resultSet.getDouble("PRICE"))
                        .quantity(resultSet.getInt("QUANTITY"))
                        .description(resultSet.getString("DESCRIPTION"))
                        .imgURL(resultSet.getString("IMGURL"))
                        .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public void save(Product product) {

    }
}
