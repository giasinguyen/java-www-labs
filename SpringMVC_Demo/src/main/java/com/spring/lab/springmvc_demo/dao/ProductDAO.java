package com.spring.lab.springmvc_demo.dao;

import jakarta.annotation.Resource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductDAO {
    private Connection connection = null;
    
    @Resource(name = "jdbc/MariaDB")
    private DataSource dataSource = null;

    public ProductDAO() {
        // Constructor mặc định
    }

    public ProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProductDAO(DataSource dataSource, Connection connection) {
        this.dataSource = dataSource;
        this.connection = connection;
    }
    
    // Method để lấy connection từ datasource
    public Connection getConnection() throws SQLException {
        if (dataSource != null && (connection == null || connection.isClosed())) {
            connection = dataSource.getConnection();
        }
        return connection;
    }
    
    // Method để đóng connection
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
