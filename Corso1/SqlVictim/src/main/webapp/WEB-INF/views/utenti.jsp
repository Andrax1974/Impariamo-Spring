<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anagrafica Utenti</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" >
				<div class="container">
					<span class="lead">Lista degli Utenti</span>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>CodFidelity</th>
						<th>UserId</th>
						<th>Password</th>
						<th>Abilitato</th>
						<th width="100"></th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Utenti}" var="utenti">
						<tr>
							<td>${utenti.codFidelity}</td>
							<td>${utenti.userId}</td>
							<td>${utenti.pwd}</td>
							<td>${utenti.abilitato}</td>
							<td><a href="<c:url value='/updcliente/${clienti.id}' />"
								class="btn btn-success custom-width">Modifica</a></td>
							<td><a href="<c:url value='/delcliente/${clienti.id}' />"
								class="btn btn-danger custom-width">Elimina</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="well">
			 <h4>Parametro 1: "${Par1}"</h4>
			 <h4>Parametro 2: "${Par2}"</h4>
		</div>
	</div>
</body>
</html>