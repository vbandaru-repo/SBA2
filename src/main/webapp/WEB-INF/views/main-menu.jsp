<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>


<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue"><i>Welcome to Corona Kit Application</i></h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">

<h2>Hello <security:authentication property="principal.username"/></h2>


<security:authorize access="hasRole('ADMIN')">
	<a href="${pageContext.request.contextPath}/admin/home"><i>ADMIN DASHBOARD</i></a>
</security:authorize>

<security:authorize access="hasRole('USER')">
	<a href="${pageContext.request.contextPath}/user/home"><i>USER DASHBOARD</i></a>
</security:authorize>

<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
</br>Click here to <input type="submit" value="Logout">
</spring-form:form>


</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>