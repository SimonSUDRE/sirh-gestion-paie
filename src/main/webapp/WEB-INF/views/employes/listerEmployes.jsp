<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../includeHeader.jsp"%>
	<title>Gestion des employés</title>
</head>
<body>
	<nav>
		<ul class="nav nav-pills border-bottom">
			<li class="nav-item "><a class="nav-link active"
				href='<c:url value="/mvc/employes/lister" />'>Employés</a></li>
			<li class="nav-item"><a class="nav-link"
				href='<c:url value="/mvc/bulletins/lister" />'>Bulletins</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<div class="container">
				<h1>liste des employés</h1>
			</div>
			<div class="container">
				<a href='<c:url value="/mvc/employes/creer" />'
					class="btn btn-primary btn-default mt-2 mt-sm-0 col-sm-12 col-md-5 col-lg-4 offset-lg-9 offset-md-9">
					Ajouter un employe</a>
			</div>
			<br/>
			<div class="container offset-lg-2">
				<table class="table table-sm table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Matricule</th>
							<th scope="col">Entreprise</th>
							<th scope="col">Profil Remuneration</th>
							<th scope="col">Grade</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>