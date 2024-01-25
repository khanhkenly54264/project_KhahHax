<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Form</title>
</head>
<body>
<h2>Customer Form</h2>
<form action="${pageContext.request.contextPath}/new" method="post">
    <input type="hidden" name="cusID" value="${customer != null ? customer.cusID : ''}" />

    <label >CusUser:</label>
    <input type="text" name="cusUser" value="${customer != null ? customer.cusUser : ''}" required /><br/>

    <label >CusPass:</label>
    <input type="password" name="cusPass" value="${customer != null ? customer.cusPass : ''}" required /><br/>

    <label >CusName:</label>
    <input type="text" name="cusName" value="${customer != null ? customer.cusName : ''}" required /><br/>

    <label >CusPhone:</label>
    <input type="text" name="cusPhone" value="${customer != null ? customer.cusPhone : ''}" /><br/>

    <label >CusAdd:</label>
    <input type="text" name="cusAdd" value="${customer != null ? customer.cusAdd : ''}" /><br/>

    <label >CusEmail:</label>
    <input type="text" name="cusEmail" value="${customer != null ? customer.cusEmail : ''}" /><br/>

    <label>CusFacebook:</label>
    <input type="text" name="cusFacebook" value="${customer != null ? customer.cusFacebook : ''}" /><br/>

    <label >CusSkyper:</label>
    <input type="text" name="cusSkyper" value="${customer != null ? customer.cusSkyper : ''}" /><br/>

    <label>CusStatus:</label>
    <input type="text" name="cusStatus" value="${customer != null ? customer.cusStatus : ''}" required /><br/>

    <input type="submit" value="${customer != null ? 'Update' : 'Insert'}" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/customer">Back to Customer List</a>
</body>
</html>