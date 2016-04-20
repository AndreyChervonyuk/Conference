<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="true" %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Available events</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li>
          <a href="/events/create">Create new</a>
        </li>
        <li>
          <a href="/registration">Registration</a>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
          <li>
            <%--<a id="user-name" href="/account/${sessionScope.user}">Welcome : ${sessionScope.user}</a>--%>
            <a id="user-name" href="/user/account/${sessionScope.user}">Welcome : ${pageContext.request.userPrincipal.name}</a>

          </li>
          <li>
            <form id="logout" action="/logout" method="post" >
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </li>

          <a class="btn btn-danger btn-sm navbar-btn"  onclick="logout()">Sign up</a>

        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">

          <a class="btn btn-success btn-sm navbar-btn"  href="/login">Sign in</a>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>

<script>
  function logout() {
    $("#logout").submit();
  }
</script>