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
    <title>Game Workshop</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-3">
	<div class="ms-3">
	<h1>Welcome to Chore Tracker!</h1>
	</div>
	<div class="d-flex justify-content-between">
		<div class="w-25 ms-3">
		<h1>Register</h1>
	   <form:form action="/register" method="post" modelAttribute="newUser">
	   		<div class="mb-3">
				<form:label path="firstName" class="form-label">First Name:</form:label>
			    <form:errors path="firstName" class="text-danger"/>
				<form:input path="firstName" class="form-control"/>
			</div>
			<div class="mb-3">
				<form:label path="lastName" class="form-label">Last Name:</form:label>
			    <form:errors path="lastName" class="text-danger"/>
				<form:input path="lastName" class="form-control"/>
			</div>
			<div class="mb-3">
				<form:label path="email" class="form-label">Email:</form:label>
			    <form:errors path="email" class="text-danger"/>
				<form:input path="email" class="form-control"/>
			</div>
			<div class="mb-3">
				<form:label path="password" class="form-label">Password:</form:label>
			    <form:errors path="password" class="text-danger"/>
				<form:password path="password" class="form-control"/>
			</div>
			<div class="mb-3">
				<form:label path="confirmPassword" class="form-label">Confirm Password:</form:label>
			    <form:errors path="confirmPassword" class="text-danger"/>
				<form:password path="confirmPassword" class="form-control"/>
			</div>
			<input type="submit" value="Submit" class="btn btn-primary" />
	   </form:form>
	   </div>
	   <div class="w-25 me-3">
		<h1>Log in</h1>
	   <form:form action="/login" method="post" modelAttribute="newLogin">
			<div class="mb-3">
				<form:label path="email" class="form-label">Email:</form:label>
			    <form:errors path="email" class="text-danger"/>
				<form:input path="email" class="form-control"/>
			</div>
			<div class="mb-3">
				<form:label path="password" class="form-label">Password:</form:label>
			    <form:errors path="password" class="text-danger"/>
				<form:password path="password" class="form-control"/>
			</div>
			<input type="submit" value="Submit" class="btn btn-primary" />
	   </form:form>
	   </div>
	</div>
</body>
</html>