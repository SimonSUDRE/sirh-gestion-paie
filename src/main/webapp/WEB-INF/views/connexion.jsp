<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>
<%@ include file="includeHeader.jsp"%>
<title>Paie - App</title>
</head>
<body class="container">
	<h1>Connexion</h1>
	<form:form class="needs-validation" method="post">
		<c:choose>
			<c:when test="${param.error !=null}">
				<span class="text-danger">login ou mot de passe incorrect !</span>
				<div class="form-group row">
					<label class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Login</label>
					<input type="text" name="username"
						class="form-control form-control-lg" placeholder="Login" required="required"/>
				</div>
				<div class="form-group row">
					<label class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Password</label>
					<input type="password" name="password"
						class="form-control form-control-lg" placeholder="**********" required="required"/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group row">
					<label class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Login</label>
					<input type="text" name="username"
						class="form-control form-control-lg" placeholder="Login" required="required"/>
				</div>
				<div class="form-group row">
					<label class="col-md-5 col-sm-12 col-form-label col-form-label-lg">Password</label>
					<input type="password" name="password"
						class="form-control form-control-lg"  placeholder="**********" required="required"/>
				</div>
			</c:otherwise>
		</c:choose>
		<sec:csrfInput />
		<div class="form-group row">
			<input type="submit" id="connexion" name="connexion"
				class="btn btn-default mt-2 mt-sm-0 offset-8 col-lg-1"
				value="Se connecter" />
		</div>
	</form:form>
	<%@ include file="includeFooter.jsp"%>