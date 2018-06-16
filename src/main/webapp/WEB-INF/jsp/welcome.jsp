<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script> 
<script type="text/javascript" src="/js/main.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>MR ROBOT - PONTO STEFANINI LOG</h1>
			<br/>DELAY: ${currentDelay}
			<table>
		    <tr>
		        <th>DATA</th>
		        <th>LOG</th>
		    </tr>
                <c:forEach items="${listLogPonto}" var="logPonto">
				    <tr>
				        <td>
				        <p><fmt:formatDate type = "both" value = "${logPonto.data}" /></p>
				        </td>
						<td><a href="/getLog?logID=${logPonto.logID}"><span style="cursor: pointer;" class="glyphicon glyphicon-search"></span></a></td>
				    </tr>
		    	</c:forEach>
			</table>
		</div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>
