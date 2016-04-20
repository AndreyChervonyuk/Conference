<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="layer/base.jsp"></jsp:include>
</head>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>

	<c:choose>
		<c:when test="${empty user}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>User : ${user} <br/>You do not have permission to access this resource!</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>