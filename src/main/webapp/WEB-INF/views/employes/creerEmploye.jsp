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
					modelAttribute="remunerationEmploye" novalidate="novalidate">
					<div class="form-group row">
						<form:label path="matricule"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Matricule</form:label>
						<div class="col-sm-12 col-md-7">
							<form:input path="matricule" type="text"
								class="form-control form-control-lg" id="colFormLabelLg"
								name="matricule" required="required" />
							<div class="invalid-feedback">Le Matricule est obligatoire.</div>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="entreprise"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Entreprise</form:label>
						<div class="col-sm-12 col-md-7">
							<form:select path="entreprise.id" name="entreprise"
								class="custom-select" required="required">
								<form:options items="${ listeEntreprise }"
									itemLabel="denomination" itemValue="id" />
							</form:select>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="profilRemuneration"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Profil</form:label>
						<div class="col-sm-12 col-md-7">
							<form:select path="profilRemuneration.id" name="profil"
								class="custom-select" required="required">
								<form:options items="${ listeProfils }" itemLabel="code"
									itemValue="id" />
							</form:select>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="grade"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Grade</form:label>
						<div class="col-sm-12 col-md-7">
							<form:select path="grade.id" name="grade" class="custom-select"
								required="required">
								<c:forEach items="${ listeGrades }" var="unGrade">
									<form:option value="${ unGrade.id }">
										<c:out
											value="${ unGrade.code } - ${ pu.formaterBigDecimal(unGrade.nbHeuresBase*unGrade.tauxBase*12) }" /> &#x20AC; / an
									</form:option>
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