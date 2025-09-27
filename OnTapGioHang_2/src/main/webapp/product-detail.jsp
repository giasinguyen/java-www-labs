<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/27/2025
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty product}">
    <ul>
        <li>ID: ${product.id}</li>
        <li>MODEL: ${product.model}</li>
        <li>Description: ${product.description}</li>
        <li>Quantity: ${product.quantity}</li>
        <li>Price: ${product.price}</li>
        <img src="${pageContext.request.contextPath}/images/${product.imgURL}" />
    </ul>
</c:if>
<p>
    <a href="${pageContext.request.contextPath}/products">Back to Product List</a>
</p>
</body>
</html>
