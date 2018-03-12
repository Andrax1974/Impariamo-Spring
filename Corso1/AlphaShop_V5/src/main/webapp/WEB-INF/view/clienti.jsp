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
		            		<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=0,${Order};paging=${FromPaging},${ToPaging}" /> " class="btn btn-xs">
		            		<span class="oi oi-elevator"></span>
		            		</a>
		            	</th>
		                <th>
		                	Nominativo
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=1,${Order};paging=${FromPaging},${ToPaging}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Comune
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=2,${Order};paging=${FromPaging},${ToPaging}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Bollini
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=3,${Order};paging=${FromPaging},${ToPaging}" /> " class="btn btn-xs">
		                	<span class="oi oi-elevator"></span>
		                	</a>
		                </th>
		                <th>
		                	Ultima Spesa
		                	<a href="<spring:url value="/clienti/cerca/parametri;filtro=${filter},0;orderby=4,${Order};paging=${FromPaging},${ToPaging}" /> " class="btn btn-xs">
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
		</div>
	</section>

