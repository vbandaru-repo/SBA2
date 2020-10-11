<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring-form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue">Login Page</h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<core:if test="${param.error != null}">
	<i style="color:red">Invalid Username or Password</i>
</core:if>

<spring-form:form action="${pageContext.request.contextPath}/validate" method="POST">
	<br/>
	<label><b>UserName</b></label>
	<input type="text" name="username" />
	<br/>
	<br/>
	<label><b>Password</b></label>
	<input type="password" name="password" />
	<br/>
	<br/>
	<input type="submit" value="Login" />
</spring-form:form>

<core:if test="${param.logout != null}">
	<i style="color:blue">You have been logged out successfully!!!</i>
</core:if>


</body>
</html>