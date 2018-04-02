<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 

<div class="jumbotron jumbotron-billboard">
  <div class="img"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
              <h2>${intestazione}</h2>
                <p>
                    ${saluti}
                </p>
                <a href="<spring:url value="/login/form" /> " id="SignUp" class="btn btn btn-primary btn-lg">Accedi</a>
                <a href="#" id="SignIn" class="btn btn-success btn-lg">Registrati</a>
            </div>
        </div>
    </div>
</div>