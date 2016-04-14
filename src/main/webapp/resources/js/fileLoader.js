var file = (function() {

    var app = {};

    app.setSettings = function(data) {
        console.log(data.fileType)
        fileLoader.settings = $.extend(fileLoader.settings, data);
        if (!data.currentExtensions) {
            fileLoader.setFileExtensions();
        }
    };

    app.setUpListeners = function () {
        $('.file-type').change(fileLoader.setFileType);
        $('#select-file').change(fileLoader.loadPreview);
        $('#upload-file').on('click', fileLoader.loadAllFile);
        $('#remove-all-file').on('click', fileLoader.removeAllFile);
        $('#select-all-file').change(fileLoader.selectAllFile);
    };

    app.loadUserFiles = function() {
        fileLoader.loadUserFiles();
        $('#load-more-documents').on('click', fileLoader.showFiles);
        $(".attach-file-btn").on('click', function (event) {
            fileLoader.attachFile(event)
        })
    };

    app.loadUserImage = function() {
        fileLoader.loadUserImage();
        $('#load-more-documents').on('click', fileLoader.showFiles);
        $(".select-poster").on('click', function (event) {
            fileLoader.loadPoster(event)
        })
    };

    app.getExtension = function(fileName) {
        return fileLoader.getExtension(fileName)
    };

    var fileLoader = {

        settings : {
            fileType : "doc",
            currentExtensions : ['doc', 'docx', 'pdf', 'xls', 'xlsx', 'txt', 'rar', 'ppt', 'pptx']
        },

        attachFiles : [],
        uploadFiles : [],
        uploadedFiles : {
            data : [],
            shown : 0,
            step : 10
        },

        defaultExtensions : {
            image : ['jpeg', 'jpg', 'png', 'gif', 'bmp'],
            doc : ['doc', 'docx', 'pdf', 'xls', 'xlsx', 'txt', 'rar', 'ppt', 'pptx']
        },

        setFileExtensions : function () {
            if(this.settings.fileType !== null) {
                switch (this.settings.fileType) {
                    case 'image' :
                        fileLoader.settings.currentExtensions = this.defaultExtensions.image;
                        break;
                    case 'doc' :
                        fileLoader.settings.currentExtensions = this.defaultExtensions.doc;
                        break;
                    default :
                        console.error("No default extensions for file type : " + this.settings.fileType)
                }
            } else {
                console.error("Error not select files type")
            }
        },

        loadPreview : function(event) {
            var file = event.target.files[0];

            if (file !== undefined) {
                if (fileLoader.checkFile(file.name, fileLoader.settings.currentExtensions)) {
                    fileLoader.uploadFiles.push(file);
                    $('#loadImageTmpl').tmpl(file).appendTo('#loadImageList');
                    $("#load-message").html("");

                    switch (fileLoader.settings.fileType) {
                        case 'image' :
                            $("#file-icon").attr('src', URL.createObjectURL(file));
                            break;
                        case 'doc' :
                            $("#file-icon").addClass(fileLoader.getExtension(file.name) + '-file-icon');
                            break
                    }

                    $("#file-icon").removeAttr('id');
                }
            }
        },

        loadIcon : function (fileName) {
            $("#file-icon").addClass(fileLoader.getExtension(fileName) + '-file-icon');
            $("#file-icon").removeAttr('id');
        },

        setFileType : function () {
            fileLoader.settings.fileType = $(this).val();
            fileLoader.setFileExtensions();
            fileLoader.uploadFiles = [];
            $("#loadImageList").html("")
        },

        loadUserFiles : function () {
            $('#userDocumentsList').html("");

            $.ajax({
                url: "/files/user",
                method : 'GET',
                success: function(data) {
                    fileLoader.uploadedFiles.data = data;
                    fileLoader.uploadedFiles.shown = 0;
                    fileLoader.showFiles();
                }
            });
        },

        loadUserImage : function () {
            $('#userDocumentsList').html("");

            $.ajax({
                url: "/images/user",
                method : 'GET',
                success: function(data) {
                    fileLoader.uploadedFiles.data = data;
                    fileLoader.uploadedFiles.shown = 0;
                    fileLoader.showFiles();
                }
            });
        },

        attachFile : function (event) {
            var document = $.grep(fileLoader.uploadedFiles.data, function(value, i){
                    return  value.id == event.target.id.replace('file-id-', '');
            });
            fileLoader.attachFiles.push(document[0].id);
            $('#' + event.target.id).hide();
            $('#fileAttachmentTmpl').tmpl(document).appendTo('#fileAttachmentList');
        },

        loadPoster : function (event) {
            var document = $.grep(fileLoader.uploadedFiles.data, function(value, i){
                return  value.id == event.target.id.replace('file-id-', '');
            });
            $("#fileAttachmentList").html("");
            $('#posterTmpl').tmpl(document).appendTo('#fileAttachmentList');
        },

        showFiles : function () {
            var start = fileLoader.uploadedFiles.shown;
            var end;
            var step = fileLoader.uploadedFiles.step;

            fileLoader.uploadedFiles.data.length > start + step ? end = start + step : end = fileLoader.uploadedFiles.data.length;
            for (var i = start; i < end; i++) {
                $('#userDocumentsTmpl').tmpl(fileLoader.uploadedFiles.data[i]).appendTo('#userDocumentsList');
                fileLoader.loadIcon(fileLoader.uploadedFiles.data[i].name)
            }

            fileLoader.uploadedFiles.shown += step + 1;

            $(".attach-file-btn").on('click', function (event) {
                fileLoader.attachFile(event)
            })

            $(".select-poster").on('click', function (event) {
                fileLoader.loadPoster(event)
            })
        },

        loadAllFile : function() {
            if (fileLoader.uploadFiles.length != 0) {
                var data = new FormData();

                fileLoader.uploadFiles.forEach(function(value) {
                    data.append("file", value);
                });


                $.ajax({
                    url: '/files',
                    data: data,
                    cache: false,
                    contentType: false,
                    processData: false,
                    type: 'POST',
                    beforeSend:function(xhr){
                        xhr.setRequestHeader(header, token);
                    }
                })
                    .done(function(msg) {
                        $("#loadImageList").html("");
                        $("#load-message").html("<div class='bg-info'>" + msg + "</div>")
                        fileLoader.uploadFiles = [];
                    })
                    .fail(function(msg) {
                        console.log(msg)
                        $("#load-message").html("<div class='bg-danger'>" + msg.responseText + "</div>")
                    })
            }
        },

        removeAllFile : function() {
            $('#loadImageList input:checked').each(function() {
                var tr = $(this).closest('tr');
                fileLoader.uploadFiles = $.grep(fileLoader.uploadFiles, function(value){
                    return  value.name !== tr.find(".file-name").text();
                });
                tr.remove();
             //   var lastLoad = $("#selectFile");
             //   lastLoad.replaceWith( lastLoad = lastLoad.clone( true ) );
                $("#load-message").html("")

            });

        },

        selectAllFile : function() {
            var checkboxes = $("#loadImageList").find(':checkbox');
            if($(this).is(':checked')) {
                checkboxes.prop('checked', true);
            } else {
                checkboxes.prop('checked', false);
            }
        },

        getExtension : function(fileName) {
            return fileName.split('.').pop().toLowerCase();
        },

        checkFile : function(fileName, extentions) {
            if ($.inArray(fileLoader.getExtension(fileName), extentions) == -1) {
                alert('Invalid extension! Only formats are allowed : ' + extentions.join(', '));
                return false
            }
            return true;
        }
    };

    return app;
}());



