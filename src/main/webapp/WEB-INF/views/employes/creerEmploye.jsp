<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../includeHeader.jsp" %>
	<title>Gestion des employ&#xE9;s</title>
</head>
<body>
	<sec:authorize access="hasRole('ADMINISTRATEUR')">
		<nav>
			<ul class="nav nav-pills border-bottom">
				<li class="nav-item "><a class="nav-link active"
					href='<c:url value="/mvc/employes/lister" />'>Employ&#xE9;s</a></li>
				<li class="nav-item "><a class="nav-link"
					href='<c:url value="/mvc/bulletins/lister" />'>Bulletins</a></li>
				<li class="nav-item">
					<form class="nav-link" action='<c:url value="/logout" />'
						method="post">
						<sec:csrfInput />
						<input type="submit" class="btn" value="Log out" />
					</form>
				</li>
			</ul>
		</nav>
		<section class="container">
			<article>
				<div>
					<h1>Ajouter un Employ&#xE9;</h1>
				</div>
				<div>
					<form:form class="needs-validation" method="post"
						modelAttribute="remunerationEmploye" novalidate="novalidate">
						<div class="form-group row">
							<form:label path="matricule"
								class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Matricule</form:label>
							<div class="col-sm-12 col-md-7">
								<c:choose>
									<c:when test="${ not matriculeOk }">
										<form:input path="matricule" type="text" id="colFormLabelLg"
											name="matricule" required="required"
											class="form-control form-control-lg is-invalid" />
										<span class="text-danger">Le Matricule est obligatoire
											et doit suivre le model au moins une lettre Majuscule et
											suivi d'un chiffre</span>
									</c:when>
									<c:otherwise>
										<form:input path="matricule" type="text" id="colFormLabelLg"
											name="matricule" required="required"
											class="form-control form-control-lg" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="form-group row">
							<form:label path="entreprise"
								class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Entreprise</form:label>
							<div class="col-sm-12 col-md-7">
								<c:choose>
									<c:when test="${ not entrepriseOk }">
										<form:select path="entreprise.id" name="entreprise"
											class="custom-select is-invalid">
											<form:options items="${ listeEntreprise }"
												itemLabel="denomination" itemValue="id" />
										</form:select>
										<span class="text-danger">Il n'y a pas cette entreprise
											dans la base</span>
									</c:when>
									<c:otherwise>
										<form:select path="entreprise.id" name="entreprise"
											class="custom-select">
											<form:options items="${ listeEntreprise }"
												itemLabel="denomination" itemValue="id" />
										</form:select>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="form-group row">
							<form:label path="profilRemuneration"
								class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Profil</form:label>
							<div class="col-sm-12 col-md-7">
								<c:choose>
									<c:when test="${ not profilOk }">
										<form:select path="profilRemuneration.id" name="profil"
											class="custom-select is-invalid">
											<form:options items="${ listeProfils }" itemLabel="code"
												itemValue="id" />
										</form:select>
										<span class="text-danger">Il n'y a pas ce profil dans
											la base</span>
									</c:when>
									<c:otherwise>
										<form:select path="profilRemuneration.id" name="profil"
											class="custom-select">
											<form:options items="${ listeProfils }" itemLabel="code"
												itemValue="id" />
										</form:select>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="form-group row">
							<form:label path="grade"
								class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Grade</form:label>
							<div class="col-sm-12 col-md-7">
								<c:choose>
									<c:when test="${ not gradeOk }">
										<form:select path="grade.id" name="grade"
											class="custom-select is-invalid">
											<c:forEach items="${ listeGrades }" var="unGrade">
												<form:option value="${ unGrade.id }">
													<c:out
														value="${ unGrade.code } - ${ pu.formaterBigDecimal(unGrade.nbHeuresBase.multiply(unGrade.tauxBase).multiply(12)) }" /> &#x20AC; / an
											</form:option>
											</c:forEach>
										</form:select>
										<span class="text-danger">Il n'y a pas ce grade dans la
											base</span>
									</c:when>
									<c:otherwise>
										<form:select path="grade.id" name="grade"
											class="custom-select">
											<c:forEach items="${ listeGrades }" var="unGrade">
												<form:option value="${ unGrade.id }">
													<c:out
														value="${ unGrade.code } - ${ pu.formaterBigDecimal(unGrade.nbHeuresBase.multiply(unGrade.tauxBase).multiply(12)) }" /> &#x20AC; / an
											</form:option>
											</c:forEach>
										</form:select>
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
	</sec:authorize>
	<%@ include file="../includeFooter.jsp"%>