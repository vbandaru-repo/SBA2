<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
</head>
<body>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue"> User Dashboard </h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">

<div><a href="${pageContext.request.contextPath}/user/show-list"><input type="button" value="Products List"></a></div>
<br/>
<div><a href="${pageContext.request.contextPath}/user/show-kit"><input type="button" value="Kit Details"></a></div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>