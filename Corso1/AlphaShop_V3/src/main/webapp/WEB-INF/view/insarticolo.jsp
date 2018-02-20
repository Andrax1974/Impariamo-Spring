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
		 <div class="portlet-title">
		 	 <div class="caption font-red-sunglo">
            	<i class="icon-settings oi oi-pencil"></i>
             	<span class="caption-subject bold uppercase"><spring:message code="insarticolo.form.titolo.label"/></span>
            </div>
		 </div>
		<div class="portlet-body form">
				<form:form  method="POST" modelAttribute="newArticolo">
				<div class="form-body">
				
					<div class="form-group">
						<label for="codArt"><spring:message code="insarticolo.form.codArt.label"/></label>
						<form:input id="codArt" path="codArt" type="text" class="form-control" placeholder="Codice Articolo"/>  
					</div>
					
					<div class="form-group">
						<label for="descrizione"><spring:message code="insarticolo.form.descrizione.label"/></label>
						<form:input id="descrizione" path="descrizione" type="text" class="form-control" placeholder="Descrizione Articolo"/> 	 
					</div>
					
					<div class="form-group">
						<label for="um"><spring:message code="insarticolo.form.um.label"/></label>
						<form:select path="um" class="form-control">
							<form:option value="PZ" label="Pezzi"/>
							<form:option value="LT" label="Litri"/>
							<form:option value="KG" label="Kilogrammi"/>
						</form:select>
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-4" >
							<label for="pzCart"><spring:message code="insarticolo.form.pzCart.label"/></label>
							<form:input id="pzCart" path="pzCart" type="text" value="0" class="form-control"/>  
						</div>
				
						<div class="form-group col-md-4">
							<label for="pesoNetto"><spring:message code="insarticolo.form.pesoNetto.label"/></label>
							<form:input id="pesoNetto" path="pesoNetto" type="text" value="0" class="form-control"/>  
						</div>
						
						<div class="form-group col-md-4">
							<label for="idIva"><spring:message code="insarticolo.form.idIva.label"/></label>
							<form:select path="idIva" class="form-control">
								 <form:options items="${Iva}" itemValue="Id" itemLabel="Descrizione" />
							</form:select>
						</div>
					</div>
					        
					<div class="form-group">
						<label for="idStatoArt"><spring:message code="insarticolo.form.idStatoArt.label"/></label>
						<div class="mt-radio-inline">
							<label class="mt-radio">
								<form:radiobutton id="idStatoArt" class="form-check-input" path="idStatoArt" value="1" checked="true" /> 
								Attivo 
								<span></span> 
							</label>
							<label class="mt-radio">
								<form:radiobutton class="form-check-input" path="idStatoArt" value="2" /> 
								Sospeso
								<span></span> 
							</label>
							<label class="mt-radio">
							<form:radiobutton class="form-check-input" path="idStatoArt" value="3" /> 
								Eliminato  
								<span></span> 
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="idFamAss"><spring:message code="insarticolo.form.idFamAss.label"/></label>
						<form:select path="idFamAss" class="form-control">
							 <form:options items="${famAssort}" itemValue="Id" itemLabel="Descrizione" />
						</form:select>
					</div>
					
				</div>
				
				<hr class="line-form">
				
				<div class="form-actions">
					<input type="submit" id="btnAdd" class="btn btn-primary form-buttons" value = <spring:message code="insarticolo.form.btnAdd.label"/> />
					<input type="submit" id="btnAbort" class="btn btn-default form-buttons" value = <spring:message code="insarticolo.form.btnAbort.label"/> />
				</div>
			
				</form:form>
			</div>
	</div>
</section>