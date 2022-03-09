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
    <title>Welcome, ${user.firstName }</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<a href="/logout">Log Out</a>
		<h1>Welcome, ${user.firstName }</h1>
	</div>
	<div>
		<p>Your Account Balance $____.__</p>
		<a href="/">Deposit/Withdraw Funds</a>
		<a href="/">Send Money</a>
	</div>
	<div>
		<p>Funded Loans</p>
		<a href="/">Loan# #### - Borrower Name - Outstanding Balance </a>
		<a href="/">Loan# #### - Borrower Name - Outstanding Balance </a>
		<a href="/">FUND A NEW LOAN</a>
	</div>
	<div>
		<p>Total Borrowing:$____.__</p>
		<a href="/">Loan# #### - Lender Name - Outstanding Balance </a>
		<a href="/">Loan# #### - Lender Name - Outstanding Balance </a>
	</div>
	<div>
		<p>Cash Balance</p>
		<a href="/">Deposit/Withdraw Funds</a>
		<a href="/">Send Money</a>
	</div>			
   
</body>
</html>

