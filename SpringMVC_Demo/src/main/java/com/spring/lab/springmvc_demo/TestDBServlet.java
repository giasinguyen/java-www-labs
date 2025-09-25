package com.spring.lab.springmvc_demo;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "testDBServlet", value = "/test-db")
public class TestDBServlet extends HttpServlet {
    
    @Resource(name = "jdbc/MariaDB")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Test Database Connection</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Test Kết Nối Database</h1>");
        
        Connection connection = null;
        try {
            if (dataSource != null) {
                connection = dataSource.getConnection();
                out.println("<p style='color: green;'>✓ Kết nối database thành công!</p>");
                out.println("<p>Connection: " + connection.toString() + "</p>");
                
                // Test basic query
                var stmt = connection.createStatement();
                var rs = stmt.executeQuery("SELECT 1 as test");
                if (rs.next()) {
                    out.println("<p>✓ Query test thành công: " + rs.getInt("test") + "</p>");
                }
                
            } else {
                out.println("<p style='color: red;'>✗ DataSource is null</p>");
            }
            
        } catch (SQLException e) {
            out.println("<p style='color: red;'>✗ Lỗi kết nối database:</p>");
            out.println("<pre>" + e.getMessage() + "</pre>");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    out.println("<p>✓ Đã đóng kết nối</p>");
                } catch (SQLException e) {
                    out.println("<p style='color: orange;'>⚠ Lỗi khi đóng kết nối: " + e.getMessage() + "</p>");
                }
            }
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}
