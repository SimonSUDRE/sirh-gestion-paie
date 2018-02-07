<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="../includeHeader.jsp"%>
<title>Gestion des employ&#xE9;s</title>
</head>
<body>
	<sec:authorize access="hasAnyRole('UTILISATEUR', 'ADMINISTRATEUR')">
		<nav>
			<ul class="nav nav-pills border-bottom">
				<li class="nav-item "><a class="nav-link active"
					href='<c:url value="/mvc/employes/lister" />'>Employ&#xE9;s</a></li>
				<li class="nav-item"><a class="nav-link"
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
					<h1>liste des employ&#xE9;s</h1>
				</div>
				<sec:authorize access="hasRole('ADMINISTRATEUR')">
					<div>
						<a href='<c:url value="/mvc/employes/creer" />'
							class="btn btn-primary btn-default mt-2 mt-sm-0 col-sm-12 col-md-5 col-lg-4 offset-lg-9 offset-md-9">
							Ajouter un employ&#xE9;</a>
					</div>
				</sec:authorize>
				<br>
				<div>
					<table class="table table-sm table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col">Matricule</th>
								<th scope="col">Entreprise</th>
								<th scope="col">Profil Remuneration</th>
								<th scope="col">Grade</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ listeEmploye }" var="employe">
								<tr>
									<td>${ employe.matricule }</td>
									<td>${ employe.entreprise.denomination }</td>
									<td>${ employe.profilRemuneration.code }</td>
									<td>${ employe.grade.code }</td>
									<td>
										<sec:authorize access="hasRole('ADMINISTRATEUR')">
											<div>
												<a href='<c:url value="/mvc/employes/supprimer/${ employe.id }" />'
													class="btn btn-danger">Supprimer</a>
											</div>
										</sec:authorize>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</article>
		</section>
	</sec:authorize>
	<%@ include file="../includeFooter.jsp"%>