<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2025
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Employee Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.PNG" height="200" width="100%">

    <h3>Employee Information</h3>
    <form action="${pageContext.request.contextPath}/employees" method="post" class="mt-2">
        <input type="hidden" name="id" value="${employee.id}"/>

        <div class="mb-2">
            <label class="form-label">Name:</label>
            <input class="form-control" type="text" name="name" value="${employee.name}" required/>
        </div>

        <div class="mb-2">
            <label class="form-label">Salary:</label>
            <input class="form-control" type="number" step="0.01" name="salary" value="${employee.salary}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Department:</label>
            <select class="form-select" name="departmentId" required>
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.id}" <c:if test="${employee.departmentId == dep.id}">selected</c:if>>
                            ${dep.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button class="btn btn-success">Save</button>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/employees">Back</a>
    </form>
</div>
</body>
</html>


