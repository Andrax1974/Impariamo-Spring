<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="jumbotron jumbotron-billboard">
	<div class="img"></div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2>${Titolo}</h2>
				<p>${Titolo2}</p>
			</div>
		</div>
	</div>
</div>
<section class="container">
	<div class="portlet light bordered">
		<div class="portlet-title tabbable-line">
		 	 <div class="caption font-blue-madison">
            	<i class="icon-settings oi oi-person"></i>
             	<span class="caption-subject bold uppercase"><spring:message code="inscliente.form.titolo.label"/></span>
            </div>
            
            <ul class="nav nav-form" id="account-tabs" role="tablist">
	            <li class="nav-item">
	            	<a class="nav-link active" id="dati-tab" data-toggle="tab" href="#dati" role="tab" aria-controls="dati" aria-selected="true">
	            		<spring:message code="inscliente.form.tab1.label"/>
	            	</a>
	            </li>
	            <li class="nav-item">
	            	<a class="nav-link" id="avatar-tab" data-toggle="tab" href="#avatar" role="tab" aria-controls="avatar" aria-selected="false">
	            		<spring:message code="inscliente.form.tab2.label"/>
	            	</a>
				</li>
 				<li>
					<a class="nav-link" id="utenti-tab" data-toggle="tab" href="#utenti" role="tab" aria-controls="utenti" aria-selected="false">
						<spring:message code="inscliente.form.tab3.label"/>
					</a>
			    </li>
            </ul>
            
            <section class = "locale-link-left"> 
            	<a href="?language=en"><img src="<c:url value="/img/US.png" />"></a> - 
            	<a href="?language=it"><img src="<c:url value="/img/IT.png" />"></a>                
      		</section> 
      		
		</div>
		<div class="portlet-body form">
			<div class="tab-content" id="myTabContent">
			
				<c:if test="${saved}">
					<div class="alert alert-success" role="alert">
	  					<spring:message code="inscliente.form.alert.label"/>
					</div>
				</c:if>
				
				<div class="tab-pane fade show active" id="dati" role="tabpanel" aria-labelledby="dati-tab">
					<form:form  method="POST" modelAttribute="Cliente">
					<form:errors path="*" cssClass="alert alert-danger" element="div"/> 
					
					<div class="form-body">
					
						<div class="form-group">
							<label for="codArt"><spring:message code="inscliente.form.codFidelity.label"/></label>
							<form:input id="codFidelity" path="codFidelity" type="text" class="form-control" placeholder="Codice Fidelity"/>  
							<form:errors path="codFidelity" cssClass="text-danger"/>
						</div>
								
						<div class="form-row">
							<div class="form-group col-md-6" >
								<label for="nome"><spring:message code="inscliente.form.nome.label"/></label>
								<form:input id="nome" path="nome" type="text" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-6">
								<label for="cognome"><spring:message code="inscliente.form.cognome.label"/></label>
								<form:input id="cognome" path="cognome" type="text" class="form-control"/>  
							</div>
						</div>
						
						<div class="form-group">
							<label for="indirizzo"><spring:message code="inscliente.form.indirizzo.label"/></label>
							<form:input id="indirizzo" path="indirizzo" type="text" class="form-control" placeholder="Indirizzo"/>  
							<form:errors path="codFidelity" cssClass="text-danger"/>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-6" >
								<label for="comune"><spring:message code="inscliente.form.comune.label"/></label>
								<form:input id="comune" path="comune" type="text" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-3">
								<label for="cap"><spring:message code="inscliente.form.cap.label"/></label>
								<form:input id="cap" path="cap" type="text" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-3">
								<label for="prov"><spring:message code="inscliente.form.prov.label"/></label>
								<form:input id="prov" path="prov" type="text" class="form-control"/>  
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-6" >
								<label for="telefono"><spring:message code="inscliente.form.telefono.label"/></label>
								<form:input id="telefono" path="telefono" type="text" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-6">
								<label for="mail"><spring:message code="inscliente.form.mail.label"/></label>
								<form:input id="mail" path="mail" type="text" class="form-control"/>  
							</div>
						</div>
						
						<div class="form-group">
							<label for="stato"><spring:message code="inscliente.form.stato.label"/></label>
							<div class="mt-radio-inline">
								<label class="mt-radio">
									<form:radiobutton id="stato" class="form-check-input" path="stato" value="1" checked="true" /> 
									Attivo 
									<span></span> 
								</label>
								<label class="mt-radio">
									<form:radiobutton class="form-check-input" path="stato" value="2" /> 
									Sospeso
									<span></span> 
								</label>
							</div>
						</div>
						
						<hr class="line-form">
						
						<div class="form-actions">
							<input type="submit" id="btnAdd" class="btn btn-green form-buttons" value = <spring:message code="insarticolo.form.btnAdd.label"/> />
							
							<a href="<spring:url value="/clienti/" /> " id="btnAbort" class="btn btn-default form-buttons" >
								<spring:message code="insarticolo.form.btnAbort.label"/>
							</a>
						</div>
					</div>
					
					</form:form>
				</div>
				<div class="tab-pane fade" id="avatar" role="tabpanel" aria-labelledby="avatar-tab"> 
				
					<form:form  method="POST" modelAttribute="Utente">
					<form:errors path="*" cssClass="alert alert-danger" element="div"/> 
					
					<div class="form-body">
						<div class="form-group">
							<label for="userId"><spring:message code="inscliente.form.codFidelity.label"/></label>
							<form:input id="codFidelity_utenti" path="codFidelity" type="text" class="form-control" readonly = "true"/>  
							<form:errors path="codFidelity" cssClass="text-danger"/>
						</div>
								
						<div class="form-group">
							<label for="userId"><spring:message code="inscliente.form.userId.label"/></label>
							<form:input id="userId" path="userId" type="text" class="form-control" placeholder=""/>  
							<form:errors path="userId" cssClass="text-danger"/>
						</div>
								
						<div class="form-group">
							<label for="password"><spring:message code="inscliente.form.password.label"/></label>
							<form:input id="password" path="pwd" type="password" class="form-control" placeholder="password"/>  
							<form:errors path="pwd" cssClass="text-danger"/>
						</div>
								
						<div class="form-group">
							<label for="abilitato"><spring:message code="inscliente.form.stato.label"/></label>
							<div class="mt-radio-inline">
								<label class="mt-radio">
									<form:radiobutton id="abilitato" class="form-check-input" path="abilitato" value="Si" checked="true" /> 
									Attivo 
									<span></span> 
								</label>
								<label class="mt-radio">
									<form:radiobutton class="form-check-input" path="abilitato" value="No" /> 
									Sospeso
									<span></span> 
								</label>
							</div>
						</div>
						
						<hr class="line-form">
						
						<div class="form-actions">
							 <c:choose>
							 	<c:when test="${edit}">
							 		<input type="submit" id="btnAdd" class="btn btn-green form-buttons" value = <spring:message code="insarticolo.form.btnAdd.label"/> />
							 	</c:when>
							 	<c:otherwise>
							 		<input type="submit" id="btnAdd" class="btn btn-green form-buttons" value = <spring:message code="insarticolo.form.btnAdd.label"/> disabled />
							 	</c:otherwise>
							</c:choose>
							
							<a href="<spring:url value="/clienti/" /> " id="btnAbort" class="btn btn-default form-buttons" >
								<spring:message code="insarticolo.form.btnAbort.label"/>
							</a>
							
						</div>
					</div>
					
					</form:form>
				</div>
				<div class="tab-pane fade" id="utenti" role="tabpanel" aria-labelledby="utenti-tab">
				<form:form  method="POST" modelAttribute="Profilo">
				
				<div class="form-group">
					<label for="tipo">Profilo:</label>
					<form:select path="tipo" class="form-control">
						<form:option value="USER" label="Utente"/>
						<form:option value="COMM" label="Commerciale"/>
						<form:option value="ADMIN" label="Amministratore"/>
					</form:select>
				</div>
				
				<table id="profili" class="table table-striped table-bordered">
					<thead>
						<tr>
						    <th>Id</th>
						    <th>Profilo</th>
						    <th></th>
					    </tr>
			        </thead>
			        <tbody>
				       <c:forEach items="${Cliente.utenti.profili}" var="prof">
				       	<tr>
				       		<td>${prof.id}</td>
				       		<td>${prof.tipo}</td>
				       		<td><a href="<c:url value='delprofilo/${Cliente.codFidelity}/${prof.id}' />" class="btn btn-danger">Elimina</a></td>
				       </tr>
				       </c:forEach>
			       </tbody>
				</table>

				<div class="form-actions">
					<input type="submit" id="btnAdd" class="btn btn-green form-buttons" value = <spring:message code="insarticolo.form.btnAdd.label"/> />
					<input type="reset" id="btnAbort" class="btn btn-default form-buttons" value = <spring:message code="insarticolo.form.btnAbort.label"/> />
				</div>
				
				</form:form>
				</div>
			</div>
		</div>
	</div>
</section>