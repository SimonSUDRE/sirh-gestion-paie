<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../includeHeader.jsp"%>
<title>Gestion des bulletins</title>
</head>
<body>
	<nav>
		<ul class="nav nav-pills border-bottom">
			<li class="nav-item "><a class="nav-link"
				href='<c:url value="/mvc/employes/lister" />'>Employ&#xE9;s</a></li>
			<li class="nav-item "><a class="nav-link active"
				href='<c:url value="/mvc/bulletins/lister" />'>Bulletins</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<div class="container">
				<h1>Cr&#xE9;er un Bulletin de Salaire</h1>
			</div>
			<div class="container offset-lg-2">
				<form:form class="needs-validation" method="post"
					modelAttribute="bulletinSalaire" novalidate="novalidate">
					<div class="form-group row">
						<form:label path="periode"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">P&#xE9;riode</form:label>
						<div class="col-sm-12 col-md-7">
							<c:choose>
								<c:when test="${ not periodeOk }">
									<form:select path="periode.id" class="custom-select is-invalid"
										required="required" name="periode">
										<c:forEach items="${ listePeriode }" var="unePeriode">
											<form:option value="${ unePeriode.id }">
												<c:out
													value="${ unePeriode.dateDebut } - ${ unePeriode.dateFin }" />
											</form:option>
										</c:forEach>
									</form:select>
									<span class="text-danger">Il n'y a pas cette p&#xE9;riode dans la base</span>
								</c:when>
								<c:otherwise>
									<form:select path="periode.id" class="custom-select"
										required="required" name="periode">
										<c:forEach items="${ listePeriode }" var="unePeriode">
											<form:option value="${ unePeriode.id }">
												<c:out
													value="${ unePeriode.dateDebut } - ${ unePeriode.dateFin }" />
											</form:option>
										</c:forEach>
									</form:select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="remunerationEmploye"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Matricule</form:label>
						<div class="col-sm-12 col-md-7">
							<c:choose>
								<c:when test="${ not remEmplOk }">
									<form:select path="remunerationEmploye.id" name="remunerationEmploye"
										class="custom-select is-invalid" required="required">
										<form:options items="${ listeEmploye }" itemLabel="matricule"
											itemValue="id" />
									</form:select>
									<span class="text-danger">Il n'y a pas cette remuneration employ&#xE9; dans la base</span>
								</c:when>
								<c:otherwise>
									<form:select path="remunerationEmploye.id" name="remunerationEmploye"
										class="custom-select" required="required">
										<form:options items="${ listeEmploye }" itemLabel="matricule"
											itemValue="id" />
									</form:select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="primeExceptionnelle"
							class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Prime exceptionnelle</form:label>
						<div class="col-sm-12 col-md-7">
							<c:choose>
								<c:when test="${ not primeOk }">
									<form:input path="primeExceptionnelle" type="number"
										class="form-control form-control-lg is-invalid" id="colFormLabelLg"
										name="prime" min="0" step="0.01" value="0" required="required" />
									<span class="text-danger">la prime exceptionnelle doit être sup&#xE9;rieur ou &#xE9;gale à 0</span>
								</c:when>
								<c:otherwise>
									<form:input path="primeExceptionnelle" type="number"
										class="form-control form-control-lg" id="colFormLabelLg"
										name="prime" min="0" step="0.01" value="0" required="required" />
								</c:otherwise>
							</c:choose>
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