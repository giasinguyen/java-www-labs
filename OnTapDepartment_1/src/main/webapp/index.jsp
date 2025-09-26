<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>HR Demo</h2>
<ul>
    <li><a href="${pageContext.request.contextPath}/departments">Departments</a></li>
    <li><a href="${pageContext.request.contextPath}/employees">Employees</a></li>
</ul>
</body>
</html>