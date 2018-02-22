<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="jumbotron jumbotron-billboard">
  <div class="img"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
              <h2>${Titolo}</h2>
                <p>
                    ${Titolo2}
                </p>
            </div>
        </div>
    </div>
</div>
<section class="container">
	<div class="row">
		
		<div class="card">
			<img class="card-img-top card-image" src="<c:url value="/img/articoli/${articolo.codArt}.png"></c:url>" alt="Card image cap">
			<div class="card-body">
    			<h3 class="card-title price-art">
    				Euro <fmt:formatNumber value = "${articolo.prezzo}"  minFractionDigits = "2" type = "number"/> 
    				<!-- <span class="badge badge-info">Promo</span> -->
    			</h3>
    			<p class="card-text desc-art">${articolo.descrizione}</p>
 		 	</div>
 		 	<ul class="list-group list-group-flush">
 		 		<li class="list-group-item">Codice: <span class="info-art">${articolo.codArt}</span></li>
	   			<li class="list-group-item">Peso Netto (LT|KG): <span class="info-art">
	   				<fmt:formatNumber value = "${articolo.pesoNetto}"  minFractionDigits = "2" type = "number"/></span></li>
	   	 		<li class="list-group-item">Prezzo Euro al Lt|KG: <span class="info-art">
	   	 			<fmt:formatNumber value = "${articolo.prezzoKg}"  minFractionDigits = "2" type = "number"/></span></li>
	    		<li class="list-group-item">Disponibilit� (PZ/KG): <span class="info-art">
	    			<fmt:formatNumber value = "${articolo.qtaMag}"  minFractionDigits = "2" type = "number"/></span></li>
	    		<li class="list-group-item">Stato: <span class="badge badge-info">Normalit�</span></li>
  			</ul>
  			<div class="card-body">
    		<a href="#" class="card-link">Acquista</a>
    		<a href="#" class="card-link">Annulla</a>
  			</div>
		</div>
	</div>
</section>