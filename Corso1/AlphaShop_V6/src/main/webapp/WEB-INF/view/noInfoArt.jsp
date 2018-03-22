<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>Oops!</h1>
                <h2>Articolo NON trovato!</h2>
                <div class="error-details">
                    <P>Spiacente, l'articolo ${codice} non è presente in anagrafica!</P>
                    <p>${url}</p> 
                	<p>${exception}</p> 
                </div>
                <img src="<c:url value="/img/error-404.jpg"></c:url>">
                <div class="error-actions">
                    <a href="<spring:url value="/" />"  class="btn btn-primary btn-lg">
                    	<span class="oi oi-arrow-circle-left"></span> Indietro</a>
                    <a href="#" class="btn btn-default btn-lg">
                    	<span class="oi oi-envelope-closed"></span> Supporto</a>
                </div>
            </div>
        </div>
    </div>
</div>