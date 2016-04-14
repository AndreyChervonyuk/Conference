<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
  <jsp:include page="leyer/base.jsp"></jsp:include>
</head>
<body onload="permissionUtil.loadDefault()">
<jsp:include page="leyer/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" id="event-information-form">

                            <div class="form-group">
                                <label for="inputName" class="col-sm-2 control-label">Event name</label>
                                <div class="col-sm-10">
                                    <input type="text" name="name" class="form-control" id="inputName" placeholder="Event name" maxlength="10">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputDescription" class="col-sm-2 control-label">Description</label>
                                <div class="col-sm-10">
                                    <textarea rows="2"  name="description" class="form-control" id="inputDescription" placeholder="Description"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputInformation" class="col-sm-2 control-label">Information</label>
                                <div class="col-sm-10">
                                    <textarea rows="5" name="information" class="form-control" id="inputInformation" placeholder="Information"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputStartDate" class="col-sm-2 control-label">Start date</label>
                                <div class="col-sm-4">
                                    <input type="date" name="startDate" id="inputStartDate" class="form-control">
                                </div>

                                <label for="inputFinishDate" class="col-sm-2 control-label">Finish date</label>
                                <div class="col-sm-4">
                                    <input type="date" name="finishDate" id="inputFinishDate" class="form-control">
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="inputAddress" class="col-sm-2 control-label">Address</label>
                                <div class="col-sm-10">
                                    <input type="text" name="address" id="inputAddress" class="form-control">
                                </div>
                            </div>
                        </form>

                        <form id="event-poster-form">
                            <div class="form-group">
                                <label for="fileAttachmentList" class="col-sm-2 control-label">Poster</label>
                                <div class="col-sm-10">
                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#documentsModal" id="user-documents-loader" onclick="file.loadUserImage()">Select poster image</button>
                                    <div id="fileAttachmentList"></div>
                                </div>
                            </div>
                        </form>

                        <form id="event-permission-form">
                            <div class="form-group">
                                <label for="permissions" class="col-sm-2 control-label">Event permission</label>
                                <div class="form-group">
                                    <div id="permissions" class="col-md-11 col-md-offset-1"></div>
                                </div>
                            </div>
                        </form>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" class="btn btn-success" id="create-event">Submit</button>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="documentsModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">My documents</h4>
            </div>
            <a href="/documents/upload" class="btn btn-default">Upload new file</a>
            <div class="modal-body" id="userDocumentsList">
            </div>
            <button type="button" class="btn btn-primary" id="load-more-documents" onclick="file.attachListener()">Load more</button>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



<script id="userDocumentsTmpl" type="text/x-jquery-tmpl">
        <tr>
            <td><img src={%= path %} height="128" width="128" alt=""/></td>
            <td class="file-name">
                {%= name%}
                <span class="glyphicon glyphicon-plus-sign select-poster" id="file-id-{%= id %}" ></span>
            </td>
        </tr>
</script>

<script id="posterTmpl" type="text/x-jquery-tmpl">
        <div class="col-md-2" id="file-attachment-{%= id %}">
            <span class="glyphicon glyphicon-remove" onclick="notificationUtil.detachFile('{%= id %}')"></span>
            <a href="#">
                <img src={%= path %} alt="" height="256" width="256" >
            </a>
            <input type="hidden" name="poster" value="{%= id %}" id="poster-id">
        </div>
    </script>


<script id="permissionInfoTmpl" type="text/x-jquery-tmpl">
        <tr>
            <td  name={%= name %}>{%= permission.description %}</td>
            <td class="permission-group" id={%= permission.name %}>{%= userGroup.name %}</td>
        </tr>
    </script>

<script id="permissionTmpl" type="text/x-jquery-tmpl">
                <div class="media col-md-6">
                    <a class="pull-left" href="#">
                        <img class="permission-{%= category %}-icon"  alt="">
                    </a>

                    <div class="media-body">
                        <div class="pull-left meta">
                            <div class="title h5">
                                <h3>{%= category %}</h3>
                                <table class="table">
                                    {%tmpl(permissions) permissionInfoTmpl %}
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
    </script>

<script id="groupsTmpl" type="text/x-jquery-tmpl">
        <div class="groups">
            <select id='{%= name %}' name='{%= name %}'  class="form-control">
                {%each userGroup %}
                    <option value={%= id %}>{%= name %}</option>
                {%/each%}
            </select>
        </div>
</script>



<script>
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    $("#create-event").on('click', function(event){

        var data = {};
        data.event = $("form#event-information-form").serializeObject();
        data.eventPermission = $("form#event-permission-form").serializeObject();
        data.poster = $("#poster-id").val();

        $.ajax({
            type: "POST",
            url:'/events',
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend:function(xhr){
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            window.location.href = "/"
        }).fail(function() {
            alert("Error create event")
        });
    });

</script>

</body>
</html>
