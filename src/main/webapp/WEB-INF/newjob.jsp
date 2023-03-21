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
    <title>Add a Job</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-3">
	<h1>Add a Job</h1>
	<a href="/home">back</a>
	<a href="/logout">logout</a>
	<form:form action="/jobs/new" method="post" modelAttribute="newJob" class="w-50">
		<div class="mb-3">
			<form:label path="title" class="form-label">Title:</form:label>
		    <form:errors path="title" class="text-danger"/>
	        <form:input path="title" class="form-control"/>
		</div>
		<div class="mb-3">
			<form:label path="description" class="form-label">Description:</form:label>
	        <form:errors path="description" class="text-danger"/>
		    <form:textarea path="description" class="form-control"/>
		</div>
		<div class="mb-3">
			<form:label path="location" class="form-label">Location:</form:label>
		    <form:errors path="location" class="text-danger"/>
	        <form:input path="location" class="form-control"/>
		</div>
		<div class="mb-3">
				<form:errors path="postedBy" class="text-danger"/>
				<form:input type="hidden" path="postedBy" value="${thisUser.id}" class="form-control"/>
		</div>
		<div class="mb-3">
				<form:errors path="isClaimed" class="text-danger"/>
				<form:input type="hidden" path="isClaimed" value="${false}" class="form-control"/>
		</div>
		<div class="mb-3">
				<form:errors path="claimedBy" class="text-danger"/>
				<form:input type="hidden" path="claimedBy" value="0" class="form-control"/>
		</div>
		<input type="submit" value="Submit" class="btn btn-primary" />
	</form:form>
</html>