<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Deposit/Withdraw Funds</title>
	    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
	    <script src="/webjars/jquery/jquery.min.js"></script>
	    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	   	<div class="container mt-5">
		   	<div>
			   	<h1>Deposit/Withdraw Funds</h1>
		   	</div>
		   	<div>
		   		<p>Current account balance: ${acctBal }</p>
		   	</div>
		   	<div>
		   		<form action="/cashops" method="POST">
		   			<input type="hidden" name="acctId" value="${user.account.id }" />
		   			<select>
		   				<option value="deposit">Deposit</option>
		   				<option value="withdraw">Withdraw</option>	   				
		   			</select>
		   			<input type="number" step="0.01" name="amount" placeholder="0.00"/>
		   			<button>Submit</button>
		   		</form>
		   	</div>
	   	</div>
	   	
	</body>
</html>

