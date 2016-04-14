<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <jsp:include page="layer/base.jsp"></jsp:include>
</head>
<body onload="eventPageBuilder.init()">
    <jsp:include page="layer/header.jsp"></jsp:include>

    <input type="hidden" value=${currentEventId} id="eventId"/>


        <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading test">
                        <ul class="nav nav-tabs" id="tab-list">
                            <li class="active">
                                <a data-toggle="tab" href="#event-information">
                                    Information
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                </a>
                            </li>
                        </ul>
                        <div class="panel-body">
                            <div id="tabContent" class="tab-content">
                                <div class="tab-pane fade in active" id="event-information">
                                    <h1>${event.name}</h1>
                                    <p class="lead">by <a href="#">${event.creator.name} ${event.creator.surname}</a></p>
                                    <hr>
                                    <p><span class="glyphicon glyphicon-time"></span> Posted on August 24, 2013 at 9:00 PM</p>

                                    <div id="join-to-group">
                                        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
                                            <c:choose>
                                                <c:when test="${userGroup eq 'NOT_MEMBERS'}">
                                                    <button class="btn btn-success" onclick="memberUtil.join()">Join to group</button>
                                                </c:when>
                                                <c:when test="${userGroup eq 'WAIT'}">
                                                    Wait for confirmation
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${userGroup ne 'GROUP_ADMINS'}">
                                                        <button class="btn btn-success" onclick="memberUtil.leave()">Leave from group</button>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                                            <a href="/login">Login</a> for join to group
                                        </sec:authorize>
                                    </div>

                                    <hr>
                                    <img class="img-responsive" src="<c:url value="${event.posterPath}"/>" alt="">
                                    <hr>
                                    <p class="lead">${event.information}</p>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script id="membersTmplTab" type="text/x-jquery-tmpl">
        <li>
            <a data-toggle="tab" href="#members" id="members-tab">
                Members
                <span class="badge" id="requests-count"></span>
                <span class="glyphicon glyphicon-user"></span>
            </a>
        </li>
    </script>

    <script id="documentsTmplTab" type="text/x-jquery-tmpl">
        <li>
            <a data-toggle="tab" id="event-documents-tab" href="#event-documents" onclick="documenstUtil.getEventDocuments()">
                Documents
                <span class="glyphicon glyphicon-file"></span>
            </a>
        </li>
    </script>

    <script id="permissionTmplTab" type="text/x-jquery-tmpl">
        <li>
            <a data-toggle="tab" href="#event-permission" id="event-permission-tab" onclick="permissionUtil.loadPermission()">
                    Permission
                    <span class="glyphicon glyphicon-lock"></span>
            </a>
        </li>
    </script>

    <script id="notificationTmplTab" type="text/x-jquery-tmpl">
        <li>
            <a data-toggle="tab" href="#notification" id="notification-tab" onclick="notificationUtil.loadNotifications()">
                Notification
                <span class="glyphicon glyphicon-bell"></span>
            </a>
        </li>
    </script>

    <script id="photosTmplTab" type="text/x-jquery-tmpl">
        <li>
            <a data-toggle="tab" href="#photos" id="photos-tab">
                Photos
                <span class="glyphicon glyphicon-camera"></span>
            </a>
        </li>
    </script>

    <jsp:include page="leyer/footer.jsp"></jsp:include>
</body>
</html>