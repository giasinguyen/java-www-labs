package com.spring.lab.springmvc_demo;

import jakarta.annotation.Resource;

import javax.sql.DataSource;
import java.sql.Connection;


import java.sql.DriverManager;

public class TestDB {
    static Connection connection;

    @Resource(name = "jdbc/MariaDB")
    private static DataSource dataSource = null;

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3307/dbtestmvc?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "admin";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công!");
            System.out.println("Chuỗi kết nối: " + connection.toString());

            // Đóng kết nối
            connection.close();
            System.out.println("Đã đóng kết nối.");

        } catch (Exception e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
