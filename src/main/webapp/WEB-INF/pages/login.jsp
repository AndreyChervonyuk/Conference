<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<head>
	<jsp:include page="leyer/base.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="leyer/header.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-form">

					<div class="panel-heading">
						<h2 class="title">Login</h2>
						<p>Don't have an account? <a href="/registration">Create one</a>.</p>
					</div>

					<div class="panel-body">
						<form id='loginForm' action="/login" method='POST'>
							<!-- Username or email -->
							<div class="form-group">
								<label for="email" class="control-label">Email</label>
								<div class="has-feedback">
									<input type="text" class="form-control" id="email" name="email">
								</div>
							</div>

							<!-- Password -->
							<div class="form-group">
								<label for="password" class="control-label">Password</label>
								<div class="has-feedback">
									<input type="password" class="form-control" id="password" name="password">
								</div>
							</div>

							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

							<c:if test="${not empty error}">
								<div class="alerts">
									<div class="alert alert-danger" style="display: block">
										<a href="#" class="close" data-dismiss="alert">&times;</a>
										<span id="alert-danger-text">${error}</span>
									</div>
								</div>
							</c:if>


							<!-- Logun button -->
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>