
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layer/base.jsp"></jsp:include>
</head>
<body>
<jsp:include page="layer/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="profile-nav col-md-4">
                <div class="panel">
                    <div id="user-heading"></div>

                    <ul class="nav nav-pills nav-stacked">
                        <li id="about"><a href="#"> <i class="fa fa-info-circle"></i> About</a></li>
                        <li id="my-events"><a href="#"> <i class="fa fa-users"></i> My events</a></li>
                        <li id="my-images"><a href="#"> <i class="fa fa-file-image-o"></i> My Image</a></li>
                        <li id="my-documents"><a href="#"> <i class="fa fa-edit"></i> My Documents</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-8">
                <div class="panel">
                    <div class="panel-body bio-graph-info">
                        <div id="content">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script id="user-heading-Tmpl" type="text/x-jquery-tmpl">
    <a href="#">
        <img src="/resources/images/default-avatar-icon.png" alt="">
    </a>
    <h1>{%= name %} {%= surname %}</h1>
</script>

<script id="about-Tmpl" type="text/x-jquery-tmpl">
      {%= name %}</br>
      {%= surname %}</br>
      {%= email %}</br>
      {%= birthDay %}</br>
</script>

<script id="my-event-Tmpl" type="text/x-jquery-tmpl">
    {%each members %}
        <a href="/events/{%= event.id %}">{%= event.name %}</a></br>
        {%= userGroup.name %}
    {%/each%}
</script>

<script id="my-documents-Tmpl" type="text/x-jquery-tmpl">
         <div class="row">
             <p>
                  <a class="btn btn-primary" href="/files/upload">Upload new</a>
             </p>
         </div>

         {%each documents %}
            <div class="col-md-3">
                <a href="#">
                    <img class="{%= file.getExtension(name) %}-file-icon" alt="">
                </a>
                <h5 style="overflow: hidden">
                    {%= name %}
                    <a href="/files/{%= id %}">
                        <span class="glyphicon glyphicon-download-alt"></span>
                    </a>
                </h5>
            </div>
       {%/each%}
</script>

<script id="my-images-Tmpl" type="text/x-jquery-tmpl">
         <div class="row">
             <p>
                  <a class="btn btn-primary" href="/files/upload">Upload new</a>
             </p>
         </div>
        {%each images %}
            <div class="col-md-3">
                <a class="thumbnail" href={%= path %} data-lightbox="user-images">
                    <img src={%= path %} style="height: 128px; width: 128px">
                </a>
                <h5>
                    {%= name %}
                </h5>
            </div>
       {%/each%}
</script>


<script>

    $(document).ready(function() {
        $.get("/user", function(data) {
            $('#about-Tmpl').tmpl(data).appendTo("#content");
            $('#user-heading-Tmpl').tmpl(data).appendTo("#user-heading");
        })
    });

    $("#about").on('click', function() {
        $("#content").html("");
        $.get("/user", function(data) {
            $('#about-Tmpl').tmpl(data).appendTo("#content");
        })
    });

    $("#my-events").on('click', function() {
        $("#content").html("");
        $.get("/user/members", function(data) {
            $('#my-event-Tmpl').tmpl({members : data}).appendTo("#content");
        })
    });

    $("#my-documents").on('click', function() {
        $("#content").html("");
        $.get("/user/documents", function(data) {
            $('#my-documents-Tmpl').tmpl({documents : data}).appendTo("#content");
        })
    });

    $("#my-images").on('click', function() {
        $("#content").html("");
        $.get("/user/images", function(data) {
            $('#my-images-Tmpl').tmpl({images : data}).appendTo("#content");
        })
    });

</script>
<jsp:include page="layer/footer.jsp"></jsp:include>
</body>
</html>
