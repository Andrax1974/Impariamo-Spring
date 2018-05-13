<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
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
		<div class = "col-md-6 col-sm-6">
			<h3 class="page-title">Risultati Ricerca: <small>Trovati ${NumArt} Articoli</small></h3>
		</div>
		<div class="col-md-6 col-sm-6">
			<a href="<spring:url value="/articoli/aggiungi" /> "  style="margin-left: 20px;" class="btn btn-success float-right">Nuovo Articolo</a>
			<div id="rep" class="datafilter">
				<label>
					Pagine: 
					 <select name="numpage" aria-controls="sample_1" class="form-control input-sm input-xsmall input-inline">
						 <option value="10">10</option>
						 <option value="15">15</option>
						 <option value="20">20</option>
						 <option value="-1">Tutti</option>
					 </select>
				</label>
			</div>
		</div>
			<table id="articoli" class="table table-striped table-bordered">
				<thead>
		            <tr>
		            	<th>Riga</th>
		                <th>CodArt</th>
		                <th>Descrizione</th>
		                <th>Peso(KG/LT)</th>
		                <th>Prezzo</th>
		                <th>Categoria</th>
		                <th></th>
		                <th></th>
		                <th></th>
		            </tr>
	        	</thead>
	        	<tfoot>
	        	</tfoot>
	        	<tbody>
	        		<c:forEach items="${Articoli}" var="Articoli">
						<tr>
							<td>${Articoli.riga}</td>
							<td>${Articoli.codArt}</td>
							<td>${Articoli.descrizione}</td>
							<td>
							<fmt:formatNumber value = "${Articoli.pesoNetto}"  minFractionDigits = "2" type = "number"/>
							</td>
							<td>
							<fmt:formatNumber value = "${Articoli.prezzo}"  minFractionDigits = "2" type = "number"/>
							</td>
							<td>${Articoli.desFamAss}</td>
							<td>
								<a href="<spring:url value="/articoli/infoart/${Articoli.codArt}" /> " class="btn btn-primary">
								<span class="oi oi-plus"/></span> Dettaglio 
      							</a> 
      						</td>
      						<td>
								<a href="<spring:url value="/articoli/modifica/${Articoli.codArt}" /> " class="btn btn-warning table-buttons">
								<span class="oi oi-pencil"></span> Modifica 
      							</a> 
      						</td>
      						<td>
								<a href="<spring:url value="/articoli/elimina/${Articoli.codArt}" /> " class="btn btn-danger table-buttons">
								<span class="oi oi-trash"/></span> Elimina 
      							</a> 
      						</td>
						</tr>
					</c:forEach>
	        	</tbody>
			</table>
		</div>
	</section>

