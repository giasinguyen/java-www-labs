<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>SpringMVC Demo - Trang Chủ</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }
    .container { max-width: 800px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    h1 { color: #333; text-align: center; }
    .nav-links { display: flex; justify-content: center; gap: 20px; margin: 30px 0; }
    .nav-link { padding: 12px 24px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; transition: background-color 0.3s; }
    .nav-link:hover { background-color: #45a049; }
    .description { text-align: center; margin: 20px 0; color: #666; }
  </style>
</head>
<body>
<div class="container">
  <h1>🌱 SpringMVC Demo Application</h1>
  <p class="description">Ứng dụng demo SpringMVC với JSP, Servlet và MariaDB</p>
  
  <div class="nav-links">
    <a href="hello-servlet" class="nav-link">Hello Servlet</a>
    <a href="test-db" class="nav-link">Test Database</a>
    <a href="products" class="nav-link">Quản Lý Sản Phẩm</a>
  </div>
  
  <div style="text-align: center; margin-top: 40px; color: #888;">
    <p>Các tính năng có sẵn:</p>
    <ul style="text-align: left; display: inline-block;">
      <li>✅ Hello Servlet - Servlet cơ bản</li>
      <li>✅ Test Database - Kiểm tra kết nối MariaDB</li>
      <li>✅ Product Management - Quản lý sản phẩm (CRUD demo)</li>
      <li>✅ JSP Templates - Sử dụng JSP cho view</li>
      <li>✅ Maven Build - Quản lý dependencies</li>
    </ul>
  </div>
</div>
</body>
</html>