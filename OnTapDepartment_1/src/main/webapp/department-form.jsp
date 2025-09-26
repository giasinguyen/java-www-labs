<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/22/2025
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Department Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.PNG" height="200" width="100%">
    <h3>Department Information</h3>

    <form method="post" action="${pageContext.request.contextPath}/departments" class="mt-2">
        <input type="hidden" name="id" value="${dep.id}">
        <div class="mb-2">
            <label class="form-label">Name:</label>
            <input class="form-control" type="text" name="name" value="${dep.name}" required>
        </div>
        <button class="btn btn-success">Save</button>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Back</a>
    </form>
</div>
</body>
</html>

