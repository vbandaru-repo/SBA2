<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>

<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue"> Admin Dashboard </h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">

<br/>
<div><a href="${pageContext.request.contextPath}/admin/product-list"><input type="button" value="View Products"></a></div>
<br/>
<div><a href="${pageContext.request.contextPath}/admin/product-entry"><input type="button" value="Add New Product"></a></div>
<br/>
<div><a href="${pageContext.request.contextPath}/home"><input type="button" value="Main Menu"></a></div>
<br/>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>