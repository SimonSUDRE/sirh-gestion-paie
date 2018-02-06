<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<section class="container">
		<article>
			<div >
				<h1>Liste des bulletins</h1>
			</div>
			<div>
				<a href='<c:url value="/mvc/bulletins/creer" />'
					class="btn btn-primary btn-default mt-2 mt-sm-0 col-sm-12 col-md-5 col-lg-4 offset-lg-9 offset-md-9">
					Cr&#xE9;er un nouveau bulletin</a>
			</div>
			<br/>
			<div>
				<table class="table table-sm table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">P&#xE9;riode</th>
							<th scope="col">Matricule</th>
							<th scope="col">SalaireBrut</th>
							<th scope="col">Net Imposable</th>
							<th scope="col">Net A Payer</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ listeBulletin }" var="bulletin">						
							<tr>
								<td>${ bulletin.key.periode.dateDebut } - ${ bulletin.key.periode.dateFin }</td>
								<td>${ bulletin.key.remunerationEmploye.matricule }</td>
								<td>${ bulletin.value.salaireBrut }</td>
								<td>${ bulletin.value.netImposable }</td>
								<td>${ bulletin.value.netAPayer }</td>
								<td><a href='<c:url value="/mvc/bulletins/visualiser/${ bulletin.key.id }"/>'>Visualiser</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</article>
	</section>
<%@ include file="../includeFooter.jsp"%>