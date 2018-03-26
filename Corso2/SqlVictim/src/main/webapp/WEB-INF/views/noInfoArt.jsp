<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errore Ricerca Articoli</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body class="error-page">
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h2>Non Ho Trovato Niente!</h2>
                <div class="error-details">
                    <h2>Spiacente, il parametro ${parametro} non ha restituito alcun risultato!</h2>
                    <p>${url}</p> 
                	<p>${exception}</p> 
                </div>
                <img src="<c:url value="/static/imgs/error-404.jpg"></c:url>">
            </div>
        </div>
    </div>
</div>
</body>
</html>