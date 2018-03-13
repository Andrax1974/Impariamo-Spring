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
			<h3 class="page-title">Risultati Ricerca: <small>Trovati ${NumArt} Clienti</small></h3>
		</div>
		<div class="col-md-6 col-sm-6">
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
			<table id="clienti" class="table table-striped table-bordered">
				<thead>
		            <tr>
		            	<th>
		            		Fidelity
		            		<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=0,${OrderType},1;paging=1,${RecPage}" /> " class="btn btn-xs">
		            		<span class="oi oi-elevator"></span>
		            		</a>
		            	</th>
		                <th>
		                	Nominativo
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=1,${OrderType},1;paging=1,${RecPage}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Comune
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=2,${OrderType},1;paging=1,${RecPage}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Bollini
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=3,${OrderType},1;paging=1,${RecPage}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Ultima Spesa
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=4,${OrderType},1;paging=1,${RecPage}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th></th>
		                <th></th>
		            </tr>
	        	</thead>
	        	<tfoot>
	        	</tfoot>
	        	<tbody>
	        		<c:forEach items="${clienti}" var="Clienti">
						<tr>
							<td>${Clienti.codFidelity}</td>
							<td> ${Clienti.cognome} ${Clienti.nome}</td>
							<td>${Clienti.comune}</td>
							<td>
							<fmt:formatNumber value = "${Clienti.card.bollini}"  minFractionDigits = "0" type = "number"/>
							</td>
							<td>
							<fmt:formatDate value="${Clienti.card.ultimaSpesa}" pattern="dd-MM-yyyy" /> 
							</td>
							<td>
								<a href="<spring:url value="/clienti/modifica/${Clienti.codFidelity}" /> " class="btn btn-primary">
								<span class="oi oi-plus"/></span> Dettaglio 
      							</a> 
      						</td>
      						<td>
								<a href="<spring:url value="/clienti/elimina/${Clienti.codFidelity}" /> " class="btn btn-danger">
								<span class="oi oi-trash"/></span> Elimina 
      							</a> 
      						</td>
						</tr>
					</c:forEach>
	        	</tbody>
			</table>
			<nav aria-label="Page navigation example">
				 <ul class="pagination">
				 	<li class="page-item">
				 		 <a class="page-link" href="#" aria-label="Previous">
        				 	<span aria-hidden="true">&laquo;</span>
        				 	<span class="sr-only">Previous</span>
      					</a>
				 	</li>
				 	<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=1,${RecPage}" /> ">1</a></li>
    				<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=2,${RecPage}" /> ">2</a></li>
    				<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=3,${RecPage}" /> ">3</a></li>
    				<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=4,${RecPage}" /> ">4</a></li>
    				<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=5,${RecPage}" /> ">5</a></li>
    				<li class="page-item"><a class="page-link" href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=${OrderBy},${OrderType},0;paging=6,${RecPage}" /> ">6</a></li>
    				<li class="page-item">
    					<a class="page-link" href="#" aria-label="Next">
        					<span aria-hidden="true">&raquo;</span>
        					<span class="sr-only">Next</span>
      					</a>
    				</li>
				 </ul>
			</nav>
		</div>
	</section>

