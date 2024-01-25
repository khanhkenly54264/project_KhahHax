<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h2>Customer List</h2>
<form action="${pageContext.request.contextPath}/search" method="get">
    <label >Search by CusUser:</label>
    <input type="text" name="searchCusUser" />
    <input type="submit" value="Search" />
</form>
<table border="1">
    <tr>
        <th>CusID</th>
        <th>CusUser</th>
        <th>CusName</th>
        <th>CusPhone</th>
        <th>CusAdd</th>
        <th>CusEmail</th>
        <th>CusFacebook</th>
        <th>CusSkyper</th>
        <th>CusStatus</th>
        <th>Action</th>
    </tr>
    <c:forEach var="customer" items="${search}">
        <tr>
            <td>${customer.cusID}</td>
            <td>${customer.cusUser}</td>
            <td>${customer.cusName}</td>
            <td>${customer.cusPhone}</td>
            <td>${customer.cusAdd}</td>
            <td>${customer.cusEmail}</td>
            <td>${customer.cusFacebook}</td>
            <td>${customer.cusSkyper}</td>
            <td>${customer.cusStatus}</td>
            <td>
                <a href="${pageContext.request.contextPath}/edit?cusID=${customer.cusID}">Edit</a>
                <a href="${pageContext.request.contextPath}/delete?cusID=${customer.cusID}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/new">Add New Customer</a>
</body>
</html>