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
	            	<a class="nav-link active" id="dati-tab" data-toggle="tab" href="#dati" role="tab" aria-controls="dati" aria-selected="true">Dati Clienti</a>
	            </li>
	            <li class="nav-item">
	            	<a class="nav-link" id="avatar-tab" data-toggle="tab" href="#avatar" role="tab" aria-controls="avatar" aria-selected="false">Modifica Avatar</a>
				</li>
 				<li>
					<a class="nav-link" id="utenti-tab" data-toggle="tab" href="#utenti" role="tab" aria-controls="utenti" aria-selected="false">Gestione Utenti</a>
			    </li>
            </ul>
            
            <section class = "locale-link-left"> 
            	<a href="?language=en"><img src="<c:url value="/img/US.png" />"></a> - 
            	<a href="?language=it"><img src="<c:url value="/img/IT.png" />"></a>                
      		</section> 
      		
		</div>
		<div class="portlet-body form">
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="dati" role="tabpanel" aria-labelledby="dati-tab">
					<form:form  method="POST" modelAttribute="newCliente" enctype="multipart/form-data">
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
								<form:input id="nome" path="nome" type="text" value="0" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-6">
								<label for="cognome"><spring:message code="inscliente.form.cognome.label"/></label>
								<form:input id="cognome" path="cognome" type="text" value="0" class="form-control"/>  
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
								<form:input id="comune" path="comune" type="text" value="0" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-3">
								<label for="cap"><spring:message code="inscliente.form.cap.label"/></label>
								<form:input id="cap" path="cap" type="text" value="0" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-3">
								<label for="prov"><spring:message code="inscliente.form.prov.label"/></label>
								<form:input id="prov" path="prov" type="text" value="0" class="form-control"/>  
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-6" >
								<label for="telefono"><spring:message code="inscliente.form.telefono.label"/></label>
								<form:input id="telefono" path="telefono" type="text" value="0" class="form-control"/>  
							</div>
							
							<div class="form-group col-md-6">
								<label for="mail"><spring:message code="inscliente.form.mail.label"/></label>
								<form:input id="mail" path="mail" type="text" value="0" class="form-control"/>  
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
							<input type="submit" id="btnAbort" class="btn btn-default form-buttons" value = <spring:message code="insarticolo.form.btnAbort.label"/> />
						</div>
						
					</div>
					
					</form:form>
				</div>
				<div class="tab-pane fade" id="avatar" role="tabpanel" aria-labelledby="avatar-tab"><p>Test1</p></div>
				<div class="tab-pane fade" id="utenti" role="tabpanel" aria-labelledby="utenti-tab"><p>Test2</p></div>
			</div>
		</div>
	</div>
</section>