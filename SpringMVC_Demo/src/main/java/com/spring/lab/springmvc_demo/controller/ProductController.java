package com.spring.lab.springmvc_demo.controller;

import com.spring.lab.springmvc_demo.dao.ProductDAO;
import com.spring.lab.springmvc_demo.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productController", urlPatterns = {"/products", "/product"})
public class ProductController extends HttpServlet {
    
    @Resource(name = "jdbc/MariaDB")
    private DataSource dataSource;
    
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO(dataSource);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        if (action == null) action = "list";
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Product Management</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".btn { padding: 10px 15px; margin: 5px; text-decoration: none; background-color: #4CAF50; color: white; border: none; cursor: pointer; }");
        out.println(".btn:hover { background-color: #45a049; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        switch (action) {
            case "list":
                showProductList(out);
                break;
            case "add":
                showAddForm(out);
                break;
            default:
                showProductList(out);
        }
        
        out.println("</body>");
        out.println("</html>");
    }
    
    private void showProductList(PrintWriter out) {
        out.println("<h1>Danh Sách Sản Phẩm</h1>");
        out.println("<a href='products?action=add' class='btn'>Thêm Sản Phẩm</a>");
        out.println("<br><br>");
        
        // Demo data (trong thực tế sẽ lấy từ database)
        List<Product> products = getDemoProducts();
        
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Tên</th>");
        out.println("<th>Giá</th>");
        out.println("<th>Mô Tả</th>");
        out.println("<th>Thao Tác</th>");
        out.println("</tr>");
        
        for (Product product : products) {
            out.println("<tr>");
            out.println("<td>" + product.getId() + "</td>");
            out.println("<td>" + product.getName() + "</td>");
            out.println("<td>" + product.getPrice() + " VND</td>");
            out.println("<td>" + product.getDescription() + "</td>");
            out.println("<td>");
            out.println("<a href='products?action=edit&id=" + product.getId() + "' class='btn'>Sửa</a>");
            out.println("<a href='products?action=delete&id=" + product.getId() + "' class='btn' style='background-color: #f44336;'>Xóa</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
    }
    
    private void showAddForm(PrintWriter out) {
        out.println("<h1>Thêm Sản Phẩm</h1>");
        out.println("<form method='post' action='products'>");
        out.println("<input type='hidden' name='action' value='save'>");
        out.println("<table>");
        out.println("<tr><td>Tên:</td><td><input type='text' name='name' required></td></tr>");
        out.println("<tr><td>Giá:</td><td><input type='number' name='price' step='0.01' required></td></tr>");
        out.println("<tr><td>Mô Tả:</td><td><textarea name='description' rows='3' cols='30'></textarea></td></tr>");
        out.println("<tr><td colspan='2'>");
        out.println("<input type='submit' value='Lưu' class='btn'>");
        out.println("<a href='products' class='btn' style='background-color: #f44336;'>Hủy</a>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</form>");
    }
    
    private List<Product> getDemoProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Laptop Dell", new BigDecimal("15000000"), "Laptop Dell Inspiron 15"));
        products.add(new Product(2L, "iPhone 15", new BigDecimal("25000000"), "iPhone 15 Pro Max"));
        products.add(new Product(3L, "Samsung Galaxy S24", new BigDecimal("20000000"), "Samsung Galaxy S24 Ultra"));
        return products;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("save".equals(action)) {
            // Xử lý lưu sản phẩm (demo)
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String description = request.getParameter("description");
            
            // Trong thực tế sẽ lưu vào database
            System.out.println("Đã lưu sản phẩm: " + name + " - " + price + " - " + description);
            
            response.sendRedirect("products");
        }
    }
}
