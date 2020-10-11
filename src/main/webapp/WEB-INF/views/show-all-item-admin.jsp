<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product List</title>
<style>
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
padding: 6 px;
}
table,th,td{
border: 1px solid purple
}

#delete
{
padding: 30,30,30,30
}

</style>
</head>
<body>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue">Product List</h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">

<table>
<tr>
<th>Product Name</th>
<th>Product Description</th>
<th>Price/Unit</th>
<th>Action</th>
</tr>
<core:forEach var="products" items="${productList}">
<tr>
<td>${products.productName}</td>
<td>${products.productDescription}</td>
<td>${products.cost}</td>
<td><a href="${pageContext.request.contextPath}/admin/product-delete/${products.id}"><input id="delete" type="button" value="Delete"></a></td>
</tr>
</core:forEach>
</table>

<br/>
<div><a href="${pageContext.request.contextPath}/admin/home"><input type="button" value="Admin Home"></a></div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>