<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/27/2025
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${empty cart.cartItems}">
    <p>Cart Empty</p>
</c:if>

<c:if test="${not empty cart.cartItems}">
    <tr>
        <th>Model</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="item" items="${cart.cartItems}">
        <tr>
            <td>${item.product.model}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post"
                      style="display:inline;">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="productId" value="${item.product.id}"/>
                    <input type="number" name="quantity" value="${item.quantity}"
                           min="1"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
            <td>${item.product.price}</td>
            <td>${item.product.price * item.quantity}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post"
                      style="display:inline;">
                    <input type="hidden" name="action" value="remove"/>
                    <input type="hidden" name="productId" value="${item.product.id}"/>
                    <input type="submit" value="Remove"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
    <p><strong>Total: </strong> ${cart.totalPrice}</p>
</c:if>

</body>
</html>
