<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/27/2025
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${products}" var="p">
    <div>
        <p>Product Model: ${p.model}</p>
        <p>Price: ${p.price}</p>
        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="id" value="${p.id}">
            <input type="hidden" name="id" value="${p.id}">
            <input type="hidden" name="price" value="${p.price}">
            <input type="hidden" name="model" value="${p.model}">
            <input type="hidden" name="action" value="add"><br/>
            <input type="submit" name="addToCart" value="Add To Cart"><br/>
        </form>
        <a href="${pageContext.request.contextPath}/product?productId=${p.id}">Product Detail</a>
    </div>
</c:forEach>
</body>
</html>
