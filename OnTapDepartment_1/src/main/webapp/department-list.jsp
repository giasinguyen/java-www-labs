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
    <title>Departments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.PNG" height="200" width="100%">
    <h2>Departments List</h2>

    <a href="${pageContext.request.contextPath}/departments?action=new">Add Department</a>

    <form class="row g-2 mt-2 mb-3" method="get" action="${pageContext.request.contextPath}/departments">
        <input type="hidden" name="action" value="list"/>
        <div class="col-auto">
            <input class="form-control" type="text" name="q" placeholder="Tìm phòng ban..." value="${param.q}">
        </div>
        <div class="col-auto"><button class="btn btn-primary">Search</button></div>
    </form>

    <table class="table table-striped">
        <thead><tr><th>DEPT ID</th><th>Name Department</th><th>Action</th></tr></thead>
        <tbody>
        <c:forEach var="d" items="${departments}">
            <tr>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/departments?action=edit&id=${d.id}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/departments?action=delete&id=${d.id}"
                       onclick="return confirm('Delete?')">Delete</a> |
                    <a href="${pageContext.request.contextPath}/departments?action=employees&id=${d.id}">Employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/employees">Employees</a>
</div>
</body>
</html>

