<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../includeHeader.jsp"%>
<title>Gestion des employ&#xE9;s</title>
</head>
<body>
	<sec:authorize access="hasAnyRole('ADMINISTRATEUR')">
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
					<h1>Supprimer un employ&#xE9;</h1>
				</div>
				<br>
				<div>
					<h3>Voulez-vous vraiment supprimer cette remunaration
						employ&#xE9; :</h3>
					<span class="col-6">Matricule : </span><span class="col-6">${ employe.matricule }</span>
					<span class="col-6">Entreprise : </span><span class="col-6">${ employe.entreprise.denomination }</span>
					<span class="col-6">Profil Remuneration : </span><span
						class="col-6">${ employe.profilRemuneration.code }</span> <span
						class="col-6">Grade : </span><span class="col-6">${ employe.grade.code }</span>
					<a href='<c:url value="/mvc/employes/lister" />'
						class="btn btn-primary">Annuler</a>
					<form method="post">
						<input type="hidden" value="${ employe.id }" name="emplId" />
						<button type="button" class="btn btn-danger">Supprimer</button>
					</form>
				</div>
			</article>
		</section>
	</sec:authorize>
	<%@ include file="../includeFooter.jsp"%>