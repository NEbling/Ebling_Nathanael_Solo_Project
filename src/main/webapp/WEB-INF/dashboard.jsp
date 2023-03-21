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
    <title>Chore Manager Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-3">
	<h1>Welcome, <c:out value="${thisUser.firstName}"/></h1>
	<a href="/logout">logout</a>
	<a href="/addJob" class="btn btn-primary">Add a Job</a>
	<div class="d-flex">
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Job</th>
						<th>Location</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="job" items="${jobs}">
						<c:if test="${job.isClaimed == false}">
							<tr>
								<td><c:out value="${job.title}"/></td>
								<td><c:out value="${job.location}"/></td>
								<td>
									<a href="/view/${job.id}">view</a> 
									<a href="/acceptJob/${job.id}">add</a>				
									<c:if test="${job.postedBy.id == thisUser.id}">
										<a href="/edit/${job.id}">edit</a> 
										<a href="/delete/${job.id}">cancel</a
									></c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>	
				</tbody>
			</table>
		</div>
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>My Jobs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="job" items="${jobs}">
						<c:if test="${job.isClaimed == true && job.claimedBy == thisUser.id}">
							<tr>
								<td>
									<c:out value="${job.title}"/>
									<a href="/view/${job.id}">view</a> 
									<a href="/delete/${job.id}">done</a>
								</td>
							</tr>
						</c:if>
					</c:forEach>	
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>