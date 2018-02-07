<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="includeHeader.jsp"%>
<title>Paie - App</title>
</head>
<body class="container">
	<h1>Connexion</h1>
	<form:form class="needs-validation" method="post">
		<div class="form-group row">
			<c:choose>
				<c:when test="${param.error !=null}">
					<span class="text-danger">login ou mot de passe incorrect !</span>
					<input type="text" name="username"
						class="form-control form-control-lg" />
					<input type="password" name="password"
						class="form-control form-control-lg" />s
				</c:when>
				<c:otherwise>
					<input type="text" name="username"
						class="form-control form-control-lg" />
					<input type="password" name="password"
						class="form-control form-control-lg" />
				</c:otherwise>
			</c:choose>
			<sec:csrfInput/>
			<form:button type="submit" id="connexion" name="connexion"
				class="btn btn-default mt-2 mt-sm-0 offset-8 col-lg-1">Se connecter</form:button>
		</div>
	</form:form>
<%@ include file="includeFooter.jsp"%>