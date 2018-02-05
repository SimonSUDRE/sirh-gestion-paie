<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../includeHeader.jsp"%>
	<title>Gestion des employés</title>
</head>
<body>
	<nav>
		<ul class="nav nav-pills border-bottom">
			<li class="nav-item "><a class="nav-link active"
				href='<c:url value="/mvc/employes/lister" />'>Employés</a></li>
			<li class="nav-item "><a class="nav-link"
				href='<c:url value="/mvc/bulletins/lister" />'>Bulletins</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<div class="container">
				<h1>Ajouter un Employé</h1>
			</div>
			<div class="container offset-lg-2">
				<form:form class="needs-validation" method="post"
					action='<c:url value="/mvc/employes/lister" />' modelAttribute="remunerationEmploye" novalidate>
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Matricule</label>
						<div class="col-sm-12 col-md-7">
							<form:input path="matricule" type="text" class="form-control form-control-lg"
								id="colFormLabelLg" name="matricule" required />
							<div class="invalid-feedback">Le Matricule est obligatoire.</div>
						</div>
					</div>
	
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Entreprise</label>
						<div class="col-sm-12 col-md-7">
							<form:select path="entreprise" name="entreprise" class="custom-select" required>
								<c:forEach items="${ listeEntreprise }" var="entreprises">
									<form:option value="${ entreprises.id }">${ entreprises.denomination }</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
	
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Profil</label>
						<div class="col-sm-12 col-md-7">
							<form:select path="profilRemuneration" name="profil" class="custom-select" required>
								<c:forEach items="${ listeProfils }" var="profils">
									<form:option value="${ profils.id }">${ profils.code }</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="colFormLabelLg"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Grade</label>
						<div class="col-sm-12 col-md-7">
							<form:select path="grade" name="grade" class="custom-select" required>
								<c:forEach items="${ listeGrades }" var="grades">
									<option value="${ grades.id }">${ grades.code } - ${ pu.formaterBigDecimal(grades.nbHeuresBase*grades.tauxBase*12) } &euro; / an</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="form-row form">
						<form:button type="submit" id="ajouter" name="ajouter"
							class="btn btn-default mt-2 mt-sm-0 offset-8 col-lg-1">Ajouter</form:button>
					</div>
				</form:form>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>