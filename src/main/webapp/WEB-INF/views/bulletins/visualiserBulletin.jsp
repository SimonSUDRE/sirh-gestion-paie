<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1>Bulletin de salaire</h1>
			</div>
			<div class="container offset-lg-2">
				<div class="offset-9 col-3">
					<b>P&#xE9;riode</b><br/>
					<span>Du ${ bulletin.periode.dateDebut } au ${ bulletin.periode.dateFin }</span>
				</div>
				<div class="col-3">
					<b>Entreprise</b><br/>
					${ infoBulletin.entreprise.denomination }<br/>
					<span>SIRET : </span>${ infoBulletin.entreprise.siret }
				</div>
				<div class="offset-6 col-3">
					<b>Matricule</b> ${ bulletin.remunerationEmploye.matricule }
				</div>
				<br/>
				<table class="table table-sm table-striped">
					<caption class="col-3"><b>Salaire</b></caption>
					<thead class="thead-dark">
						<tr>
							<th scope="col">Rubriques</th>
							<th scope="col">Base</th>
							<th scope="col">Taux Salarial</th>
							<th scope="col">Montant Salarial</th>
							<th scope="col">Taux patronal</th>
							<th scope="col">Cot. patronales</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Salaire de Base</td>
							<td>${ infoBulletin.grade.nbHeuresBase }</td>
							<td>${ infoBulletin.grade.tauxBase }</td>
							<td>${ calculBulletin.salaireDeBase }</td>
						</tr>
						<tr>
							<td>Prime Except.</td>
							<td></td>
							<td></td>
							<td>${ bulletin.primeExceptionnelle }</td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td>Salaire Brut</td>
							<td></td>
							<td></td>
							<td>${ calculBulletin.salaireBrut }</td>
						</tr>
					</tbody>
				</table>
				<br/>
				<table class="table table-sm table-striped">
					<caption class="col-3"><b>Cotisations</b></caption>
					<thead class="thead-dark">
						<tr>
							<th scope="col">Rubriques</th>
							<th scope="col">Base</th>
							<th scope="col">Taux Salarial</th>
							<th scope="col">Montant Salarial</th>
							<th scope="col">Taux patronal</th>
							<th scope="col">Cot. patronales</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ infoBulletin.profil.cotisationsNonImposables }" var="cotNonImpo">
							<tr>
								<td>${ cotNonImpo.code } ${ cotNonImpo.libelle }</td>
								<td>${ calculBulletin.salaireBrut }</td>
								<td>
									<c:if test="${ not empty cotNonImpo.tauxSalarial }">
										${ cotNonImpo.tauxSalarial }
									</c:if>
								</td>
								<td>
									<c:if test="${ not empty cotNonImpo.tauxSalarial }">
										${ cotNonImpo.tauxSalarial.multiply(calculBulletin.salaireBrut) }
									</c:if>
								</td>
								<td>
									<c:if test="${ not empty cotNonImpo.tauxPatronal }">
										${ cotNonImpo.tauxPatronal }
									</c:if>
								</td>
								<td>
									<c:if test="${ not empty cotNonImpo.tauxPatronal }">
										${ cotNonImpo.tauxPatronal.multiply(calculBulletin.salaireBrut) }
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td>Total Retenue</td>
							<td></td>
							<td></td>
							<td>${ calculBulletin.totalRetenueSalarial }</td>
							<td></td>
							<td>${ calculBulletin.totalCotisationsPatronales }</td>
						</tr>
					</tbody>
				</table>
				<br/>
				<table class="table table-sm table-striped">
					<caption class="col-3"><b>Net Imposable : ${ calculBulletin.netImposable }</b></caption>
					<thead class="thead-dark">
						<tr>
							<th scope="col">Rubriques</th>
							<th scope="col">Base</th>
							<th scope="col">Taux Salarial</th>
							<th scope="col">Montant Salarial</th>
							<th scope="col">Taux patronal</th>
							<th scope="col">Cot. patronales</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ infoBulletin.profil.cotisationsImposables }" var="cotImpo">
							<tr>
								<td>${ cotImpo.code } ${ cotImpo.libelle }</td>
								<td>${ calculBulletin.salaireBrut }</td>
								<td>
									<c:if test="${ not empty cotImpo.tauxSalarial }">
										${ cotImpo.tauxSalarial }
									</c:if>
								</td>
								<td>
									<c:if test="${ not empty cotImpo.tauxSalarial }">
										${ cotImpo.tauxSalarial.multiply(calculBulletin.salaireBrut) }
									</c:if>	
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<b class="col-4 offset-8">NET A PAYER : ${ calculBulletin.netAPayer }</b>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>