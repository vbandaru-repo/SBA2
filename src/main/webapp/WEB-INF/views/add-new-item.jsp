<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<style type="text/css">

.error{
	
	font-family:sans-serif;
	font-size:x-small;
	color: red;

}

</style>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h1 style="color:blue">Add New Product</h1>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<br/>
</head>
<body>
	<spring:form
		action="${pageContext.request.contextPath}/admin/product-save"
		method="POST" modelAttribute="product">

		<div>
			<div>
				<spring:label path="productName"><b>Product Name:</b></spring:label>
				<spring:input path="productName" />
				<spring:errors path="productName" cssClass="error" />
			</div>
		</div>
		<br>
		<div>
			<div>
				<spring:label path="cost"><b>Product Price:</b></spring:label>
				<spring:input path="cost" />
				<spring:errors path="cost" cssClass="error" />
			</div>
		</div>
		<br>
		<div>
			<div>
				<spring:label path="productDescription"><b>Product Description:</b></spring:label>
				<spring:input path="productDescription" />
				<spring:errors path="productDescription" cssClass="error" />
			</div>
		</div>
		<br>
		<div>
		<input type="submit" value="Add Product" />
		<a href="${pageContext.request.contextPath}/admin/product-list"><input type="button" value="List Products"></a>
		<a href="${pageContext.request.contextPath}/admin/home"><input type="button" value="Admin Home"></a>
		</div>
	</spring:form>

</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>