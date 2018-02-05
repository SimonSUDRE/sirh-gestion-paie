<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1>Bulletin de salaire</h1>
			</div>
			<div class="container offset-lg-2">
				<div class="offset-9 col-3">
					<b>Période</b><br/>
					<span>Du ${ bulletin.periode.dateDebut } au ${ bulletin.periode.dateFin }</span>
				</div>
				<div class="col-3">
					<b>Entreprise</b><br/>
					${ bulletin.entreprise.denomination }<br/>
					<span>SIRET : </span>${ bulletin.entreprise.siret }
				</div>
				<div class="offset-6 col-3">
					<b>Matricule</b> ${ bulletin.remunerationEmploye.matricule }
				</div>
				<table class="table table-sm table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Période</th>
							<th scope="col">Matricule</th>
							<th scope="col">SalaireBrut</th>
							<th scope="col">Net Imposable</th>
							<th scope="col">Net A Payer</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>