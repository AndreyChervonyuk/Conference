<!DOCTYPE html>
<html>
<head lang="en">
    <jsp:include page="layer/base.jsp"></jsp:include>
</head>
<body onload="file.setUpListeners()">
<jsp:include page="layer/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">

    <div class="col-lg-7">
        <span class="btn btn-success fileinput-button">
            <i class="glyphicon glyphicon-plus"></i>
            <span>Add files...</span>
            <input type="file" name="file" id="select-file" class="file-loader" multiple>
        </span>

        <button type="submit" class="btn btn-primary" id="upload-file">
            <i class="glyphicon glyphicon-upload"></i>
            <span>Start upload</span>
        </button>

        <button type="button" class="btn btn-danger" id="remove-all-file">
            <i class="glyphicon glyphicon-trash"></i>
            <span>Delete selected file</span>
        </button>

        <p>
            <input class="file-type" type="radio" name="file-type" value="doc" checked>Documents<Br>
            <input class="file-type" type="radio" name="file-type" value="image">Image<Br>
        </p>
    </div>

    <div id="documents-table">
        <script id="loadImageTmpl" type="text/x-jquery-tmpl">
                <tr>
                    <td><img class="file-icon" id="file-icon" alt=""/></td>
                    <td class="file-name">{%= name%}</td>
                    <td>{%= (size / (1024*1024)).toFixed(2)%} MB</td>
                    <td>{%=  lastModifiedDate%} </td>
                    <td><input type="checkbox" class="remove-file"></td>
                </tr>
        </script>

        <table class="table table-striped">
            <thead>
                <th>Preview</th>
                <th>Name</th>
                <th>Size</th>
                <th>last modified</th>
                <th><input type="checkbox" id="select-all-file"></th>
            </thead>
            <tbody id="loadImageList"></tbody>
        </table>
    </div>
    <div id="load-message">

    </div>
</div>

</div>
    </div>
</body>
</html>