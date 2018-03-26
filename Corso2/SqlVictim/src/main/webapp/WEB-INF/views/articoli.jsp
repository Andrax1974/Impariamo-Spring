<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anagrafica Articoli</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" >
				<div class="container">
					<span class="lead">Lista degli Articoli</span>
					<div class="row">
						<form:form method="GET" action="/articoli/cerca">
							<div class="col-xs-8 col-xs-offset-2">
								<div class="input-group">
									<input type="text" onClick="this.select();" class="form-control" name="Par1" value="${Par1}" placeholder="Cerca articoli...">
									<span class="input-group-btn">
										<button id="btnSearch" type="submit" class="btn btn-default">
										<span class="glyphicon glyphicon-search"></span> Cerca </button>
									</span>
								</div>
							</div>
						 </form:form>
					</div>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>CodArt</th>
						<th>Descrizione</th>
						<th>Um</th>
						<th>Categoria</th>
						<th>Prezzo</th>
						<th width="100"></th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Articoli}" var="articoli">
						<tr>
							<td>${articoli.codArt}</td>
							<td>${articoli.descrizione}</td>
							<td>${articoli.um}</td>
							<td>${articoli.idFamAss} - ${articoli.desFamAss}</td>
							<td>${articoli.prezzo}</td>
							<td><a href="<c:url value='/updcliente/${articoli.codArt}' />"
								class="btn btn-success custom-width">Modifica</a></td>
							<td><a href="<c:url value='/delcliente/${articoli.codArt}' />"
								class="btn btn-danger custom-width">Elimina</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="well">
			 <h4>Parametro 1: "${Par1}"</h4>
		</div>
	</div>
</body>
</html>