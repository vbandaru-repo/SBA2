<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Summary</title>
<style>
th{
background-color: #E0E0E0;
}
th,h2, h3{
color:blue
}
table {
  border-spacing: 1px;
  
}
table,th,td{
border: 1px solid purple
}

th,td{
padding: 10px;
}

#delete
{
padding: 30,30,30,30
}

</style>
</head>
<body>

<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h2> Order Summary</h2>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<div><label><b>Customer Name : </b><security:authentication property="principal.username"/> </label></div>
<br>
<div><label><b>Delivery Address : </b>${address} </label></div>
<br>
<div><label><b>Order Date : </b>${date}</label></div>
<br>
<div><label><b>Total Order Amount : </b>${amount}</label></div>
<br>
<br>
<br>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h3>Detailed Summary</h3>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<table>
<tr>

<th>No.</th>
<th>Product Name</th>
<th>Qty.</th>
<th>Amount</th>
</tr>
<core:forEach var="item" items="${cartList}">
<tr>
<td>${item[0]}</td>
<td>${item[1]}</td>
<td>${item[2]}</td>
<td>${item[3]}</td>
</tr>
</core:forEach>
</table>

<div><b><i><h3>Thanks for your order.</h3></i></b></div>
<br>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
Click here to <input type="submit" value="Logout">
</spring-form:form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>