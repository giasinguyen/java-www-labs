<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/26/2025
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn"  uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Họ và Tên: ${param.hoTen}
    Email: ${param.email}
    <br>

    <c:set var="credit" value="${param.credit}"/>
    <c:out value="${credit}"/>
</body>
</html>
