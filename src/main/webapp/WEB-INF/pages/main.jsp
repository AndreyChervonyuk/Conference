<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <jsp:include page="leyer/base.jsp"></jsp:include>
</head>
<body>
<jsp:include page="leyer/header.jsp"></jsp:include>

<div class="container" id="wrapper">
    <div class="row">
        <div class="col-md-12">
            <c:forEach items="${events}" var="event">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h2>
                            <a href="/events/${event.id}">${event.name}</a>
                        </h2>
                        <p class="lead">
                            by <a href="#">${event.creator.name} ${event.creator.surname}</a>
                        </p>
                        <c:choose>
                            <c:when test="${event.startDate eq event.finishDate}">
                                <p><span class="glyphicon glyphicon-time"></span> Date: ${event.startDate}</p>
                            </c:when>
                            <c:otherwise>
                                <p><span class="glyphicon glyphicon-time"></span> Date:  ${event.startDate} -  ${event.finishDate}</p>
                            </c:otherwise>
                        </c:choose>

                        <hr>
                        <img class="img-responsive" src="<c:url value="${event.posterPath}"/>" alt="">
                        <hr>
                        <p>${event.description}</p>
                        <a class="btn btn-primary" href="/events/${event.id}">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
                        <hr>
                    </div>
                </div>
            </c:forEach>

            <ul class="pager">
                <li class="previous">
                    <a href="#">← Older</a>
                </li>
                <li class="next">
                    <a href="#">Newer →</a>
                </li>
            </ul>
            <hr>
        </div>
    </div>
</div>
<jsp:include page="leyer/footer.jsp"></jsp:include>

</body>
</html>
