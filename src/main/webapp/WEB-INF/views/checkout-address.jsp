<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shipping Address</title>
</head>
<body>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<h2 style="color:blue">Confirm Details</h2>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
	<spring:form action="${pageContext.request.contextPath}/user/finalize"
		method="POST" modelAttribute="ck">

		<div>
			<div>
				<spring:label path="orderDate"> <b> Order Date : </b> ${ck.orderDate} </spring:label>
			</div>
		</div>
		<br>
		<div>
			<div>
				<spring:label path="totalAmount"><b> Total Kit Amount : </b> ${ck.totalAmount}</spring:label>
			</div>
			<br>

			<div>
				<div>
					<spring:label path="deliveryAddress"><b> Enter Shipping Address : </b> </spring:label>
					<spring:input path="deliveryAddress" />
					<spring:errors path="deliveryAddress" cssClass="error" />
				</div>
			</div>
			<br>

			<span>
				<input type="submit" value="Place Order" />
			</span>
			<span>
				<a href="${pageContext.request.contextPath}/user/show-kit"><input type="button" value="View Cart" /> </a>
			</span>
		</div>
	</spring:form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>