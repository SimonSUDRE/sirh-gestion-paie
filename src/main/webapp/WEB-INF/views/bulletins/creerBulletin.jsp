<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../includeHeader.jsp"%>
	<title>Gestion des bulletins</title>
</head>
<body>
	<nav>
		<ul class="nav nav-pills border-bottom">
			<li class="nav-item "><a class="nav-link"
				href='<c:url value="/mvc/employes/lister" />'>Employés</a></li>
			<li class="nav-item "><a class="nav-link active"
				href='<c:url value="/mvc/bulletins/lister" />'>Bulletins</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<div class="container">
				<h1>Créer un Bulletin de Salaire</h1>
			</div>
			<div class="container offset-lg-2">
				<form:form class="needs-validation" method="post"
					action='<c:url value="/mvc/bulletins/lister" />' modelAttribute="bulletinSalaire" novalidate>
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Entreprise</label>
						<div class="col-sm-12 col-md-7">
							<form:select path="periode" name="periode" class="custom-select" required>
								<c:forEach items="${ listePeriode }" var="periodes">
									<form:option value="${ periodes.id }">${ periodes.dateDebut.toString() } - ${ periodes.dateFin.toString() }</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
	
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Profil</label>
						<div class="col-sm-12 col-md-7">
							<form:select path="remunerationEmploye" name="matricule" class="custom-select" required>
								<c:forEach items="${ listeEmploye }" var="employes">
									<form:option value="${ employes.id }">${ employes.matricule }</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Prime exceptionnelle</label>
						<div class="col-sm-12 col-md-7">
							<form:input path="primeExceptionnelle" type="number" class="form-control form-control-lg"
								id="colFormLabelLg" name="prime" min="0" step="0.01" value="0" required />
							<div class="invalid-feedback">La Prime exceptionnelle est obligatoire.</div>
						</div>
					</div>
					
					<div class="form-row form">
						<form:button type="submit" id="ajouter" name="ajouter"
							class="btn btn-default mt-2 mt-sm-0 offset-8 col-lg-1">Ajouter</button>
					</div>
				</form:form>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>