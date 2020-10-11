<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>

<style>
table,th,td{
border: 1px solid purple
}

th{
background-color: #E0E0E0;
}
th,h1{
color:blue
}
table {
  border-spacing: 1px;
  
}
th,td{
padding: 4px;
}


#atk
{
padding: 30,30,30,30
}
</style>
</head>
<body>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1>Cart Items For User : <security:authentication property="principal.username"/></h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<table>
<tr>
<th>Product Name</th>
<th>Qty.</th>
<th>Total Amount</th>
<th>Action</th>
</tr>
<core:forEach var="item" items="${cartList}">
<tr>
<td>${item[0]}</td>
<td>${item[1]}</td>
<td>${item[2]}</td>
<td><a href="${pageContext.request.contextPath}/user/delete/${item[3]}"><input id="atk" type="button" value="Remove"></a></td>
</tr>
</core:forEach>
</table>
<hr>
<span><a href="${pageContext.request.contextPath}/user/show-list"><input  type="button" value="View Product List"></a></span>
<span><a href="${pageContext.request.contextPath}/user/checkout"><input  type="button" value="Checkout"></a></span>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>