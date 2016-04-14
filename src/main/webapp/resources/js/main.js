    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var currentEventId;

    eventPageBuilder = {

        init : function() {
            currentEventId = $("#eventId").val();
            permissionUtil.loadUserPermission()
        },

        loadTemplate : function(path) {
            $.get(path, function(template) {
                $("#tabContent").prepend(template);
            });
        }
    };



    documenstUtil = {
        init : function() {
            eventPageBuilder.loadTemplate("/resources/template/documents.html");
            $('#documentsTmplTab').tmpl().appendTo('#tab-list');
        },

        getEventDocuments : function () {
            $.get("/files/event/" + currentEventId, function (data) {
                $('#event-document-Tmpl').tmpl({documents : data}).replaceAll('#event-documents-list');
            })
        }
    };

    photosUtil = {
        init : function() {
            eventPageBuilder.loadTemplate("/resources/template/photo.html");
            $('#photosTmplTab').tmpl().appendTo('#tab-list');
        }
    };

    notificationUtil = {

        permission : {
            leaveComments : false,
            viewComments : false,
            sendEmail : false
        },

        init : function() {
            eventPageBuilder.loadTemplate("/resources/template/notification.html");
            $('#notificationTmplTab').tmpl().appendTo('#tab-list');
            notificationUtil.setPermission();
        },

        setPermission : function() {
            this.permission.leaveComments = permissionUtil.hasPermission('Notifications', 'create_notification_comments');
            this.permission.viewComments = permissionUtil.hasPermission('Notifications', 'view_notification_comments');
            this.permission.sendEmail = permissionUtil.hasPermission('Notifications', 'send_email')
        },

        loadNotifications : function () {
            $('#notificationList').html("");
            $.get("/notification/event/" + currentEventId, function (data) {
                $('#notificationTmpl').tmpl({notification : data}).appendTo('#notificationList');
            })
        },

        loadNotification : function (id) {
            $('#notification-details-' + id).html("");
            $.get("/notification/" + id, function (data) {
                $('#notificationDetailsTmpl').tmpl({notification : data, permission : notificationUtil.permission}).appendTo('#notification-details-' + id);
            })
        },

        addComment : function (notificationId) {
            var data = $("form#comment-form-" + notificationId).serializeObject();
            data.eventId = currentEventId;

            $.ajax({
                type: 'POST',
                url: '/comments/notification',
                data: data,
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success :  function(data) {
                    $('#notificationCommentsTmpl').tmpl({comments : data, canReply : true}).appendTo('#comments-list-' + notificationId);
                }
            })
        },

        loadComments : function(id) {
            $('#comments-list-' +  id).html();
            $.get("/comments/notification/" + currentEventId + "/" + id, function (data) {
                $('#comments-list-' + id).html("")
                $.each(data, function(key, value) {
                    $('#notificationCommentsTmpl').tmpl({comments : value.comment, canReply : true}).appendTo('#comments-list-' + id);
                });
            })
        },

        createNotification : function () {
            var data = $('form#notification-form').serializeObject();
            data.eventId = currentEventId;

            if( typeof data['fileAttachment[]'] === 'string' ) {
                data['fileAttachment[]'] = [ data['fileAttachment[]'] ];
            }

            $.ajax({
                type: 'POST',
                url: '/notification',
                data: JSON.stringify(data),
                contentType: "application/json",
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                statusCode:{
                    200: function(data){
                        $('#notificationTmpl').tmpl({notification : [data], permission : notificationUtil.permission}).appendTo('#notificationList');
                        alert("Created")
                        $("form#notification-form")[0].reset()
                        $("#fileAttachmentList").html("")

                    },
                    500: function(data){
                        alert("Error create notification")
                    },
                    401 : function() {
                       window.location = "/login"
                    }
                }
            })

        },

        detachFile : function (id) {
            $("#file-attachment-" + id).remove();
        },

        sendLetter : function() {
            var data = $("#notification-form").serializeObject()
            data.eventId = currentEventId;

            if( typeof data['setTo[]'] === 'string' ) {
                data['setTo[]'] = [ data['setTo[]'] ];
            }

            if( typeof data['fileAttachment[]'] === 'string' ) {
                data['fileAttachment[]'] = [ data['fileAttachment[]'] ];
            }

            $.ajax({
                type: 'POST',
                url: '/mail',
                data: JSON.stringify(data),
                contentType: "application/json",
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success :  function() {
                    console.log("Letter sent");
                }
            })

        }

    };

    permissionUtil = {
        userPermission : [],
        eventPermission : [],

        permission : {
            editable : false
        },

        loadUserPermission : function () {
            $.get("/permission/user/" + currentEventId, function(data) {
                permissionUtil.userPermission = data.permission;

                if (permissionUtil.hasPermission('Notifications', 'view_notifications')) {
                    notificationUtil.init();
                }

                if (permissionUtil.hasPermission('Members', 'view_members')) {
                    memberUtil.init();
                }

                if (permissionUtil.hasPermission('Permissions', 'view_permission')) {
                    permissionUtil.init();
                }

                if (permissionUtil.hasPermission('Documents', 'view_documents')) {
                    documenstUtil.init();
                }
            })
        },

        init : function() {
            eventPageBuilder.loadTemplate("/resources/template/permission.html");
            $('#permissionTmplTab').tmpl().appendTo('#tab-list');
        },

        loadPermission : function() {
            $.get("/permission/" + currentEventId,  function (data){
                permissionUtil.eventPermission = data.permission;
                permissionUtil.setPermission()
                permissionUtil.setInfo()
            });
        },


        hasPermission : function (category, name) {
            if ($.isEmptyObject(this.userPermission) || this.userPermission[category] == null) {
                return false;
            } else {
                return this.getPermission(category, name) != 0;
            }
        },

        getPermission : function(category, name) {
            return $.grep(this.userPermission[category], function (e) {
                return e.permission.name == name;
            });
        },

        update : function(permissionId) {
            $(".update-permission").hide();
            $(".edit-permission").show();

            var data = {};

            data.userGroupId = $("#new-member-group option:selected").val();
            data.permissionId =  permissionId;
            data.eventId = currentEventId;

            $.ajax({
                type: "PUT",
                url: "/permission/update",
                data: JSON.stringify(data),
                contentType: 'application/json',
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function(data) {
                    permissionUtil.loadPermission();
                },
                error: function() {
                    alert("Error update permission!");
                    permissionUtil.loadPermission();
                }
            });
        },

        edit : function(element) {
            $.get("/groups", function (data) {
                $(".edit-permission").hide();
                $(element).closest('tr').find(".update-permission").show()
                var groupTd = $(element).closest('tr').find(".permission-group");
                var currentGroup = groupTd.html();
                groupTd.html("");
                $('#groupsTmpl').tmpl({userGroup : data}).appendTo($(groupTd));
                $("#new-member-group option").filter(function() {
                    return $(this).text() == currentGroup;
                }).prop('selected', true);
            });
        },

        editAll : function () {
            $.get("/groups", function (data) {
                $(".permission-group").each(function () {
                    var currentGroup = $(this).text();
                    var name = $(this).attr('id');
                    $('#groupsTmpl').tmpl({name : name, userGroup : data}).replaceAll($(this));
                    $("select#" + name + ' option').filter(function() {
                        return $(this).text() == currentGroup;
                    }).prop('selected', true);
                })
            });
        },

        chancel : function() {
            this.setInfo();
        },

        setInfo : function() {
            $("#permissions").text("");
            for(var category in permissionUtil.eventPermission){
                $('#permissionTmpl').tmpl({category : category, permissions : permissionUtil.eventPermission[category], p : permissionUtil.permission}).appendTo('#permissions');
            }
        },

        loadDefault : function () {
            $.get("/permission/default",  function (data){
                permissionUtil.eventPermission = data.permission;
                permissionUtil.setInfo();
                permissionUtil.editAll();
            });
        },

        setPermission : function() {
            this.permission.editable = permissionUtil.hasPermission('Permissions', 'edit_permission')
        }

    };

    memberUtil = {

        members : [],
        view : [],

        permission : {
            editable : false
        },

        categoryIcon  : {
            documents : "",
            notification : ""
        },

        init : function() {
            eventPageBuilder.loadTemplate("/resources/template/members.html");
            $('#membersTmplTab').tmpl().appendTo('#tab-list');
            this.setPermission();
            this.setMembersRequest();
            $("#members-tab").on('click', this.getMembers);
        },

        join : function () {
            $.ajax({
                url : "/members/join/" + currentEventId,
                type: 'POST',
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                statusCode:{
                    200: function(){
                        $("#join-to-group").text("Wait for confirmation")
                    },
                    500: function(){
                        alert("Error")
                    }
                }
            });
        },

        leave : function () {
            $.get("/members/leave/" +  currentEventId)
            location.reload(true);
        },

        setPermission : function() {
            this.permission.editable = permissionUtil.hasPermission('Members', 'edit_members')
        },

        setMembersRequest : function() {
            $.get("/members/request/" + currentEventId, function(count) {
                if (count > 0)
                    $("#requests-count").text(count);
            });
        },

        sortMembers : function(data) {
            var members = [];
            var view = [];
            data.forEach(function(value) {
                if(value.userGroup.name === 'WAIT') {
                    view.push(value);
                } else {
                    members.push(value);
                }
            });
            this.members = members;
            this.view = view;
        },

        setTableValue : function() {
            $("#membersList").html("");
            $('#membersTmpl').tmpl({members : this.members, permission : this.permission}).appendTo('#membersList');
            $("#user-request").html("");
            $('#user-request-Tmpl').tmpl(this.view).appendTo('#user-request');
        },

        removeMember : function(row, email) {
            $.get("/members/remove/" + email + "/" + currentEventId,  function removeTr(){
                row.closest('tr').remove();

                if ($(row).hasClass('remove-member-request')) {
                    $("#requests-count").text($('#user-request-table tr').length - 1);
                }
            })
        },

        addMember : function(row, email) {
            $.get("/members/add/" + email + "/" + currentEventId,  function addMember(data){
                row.closest('tr').remove();
                $('#membersTmpl').tmpl({members : [data], permission : memberUtil.permission}).appendTo('#membersList');
                $("#requests-count").text($('#user-request-table tr').length - 1);
            })
        },

        editMember : function(row, email) {
            $.get("/groups", function (data) {
                $(row).hide();
                $(row).closest('tr').find(".update-member").show()
                var groupTd = $(row).closest('tr').find(".memberGroup");
                var currentGroup = groupTd.html();
                groupTd.html("");
                $('#groupsTmpl').tmpl({userGroup : data}).appendTo($(groupTd));

                $("#new-member-group option").filter(function() {
                    return $(this).text() == currentGroup;
                }).prop('selected', true);
            })
        },

        update : function(row, email) {
            $(row).hide();
            $(row).closest('tr').find(".edit-member").show()
            var userGroup = $("#new-member-group option:selected").val();
            $.ajax({
                url : "/members/update",
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    'eventId' : currentEventId,
                    'userEmail' : email,
                    'userGroupId' : userGroup
                }),
                beforeSend:function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function(data) {
                    var groupTd = $(row).closest('tr').find(".memberGroup");
                    groupTd.html(data.userGroup.name)
                    $(row).closest('tr').find(".edit-member").show()
                }
            })
        },

        getMembers : function() {
            $.get("/members/" + currentEventId,  function setData(data){
                memberUtil.sortMembers(data);
                memberUtil.setTableValue();
            })
        },

        getMembersForLetter : function () {
            $.get("/members/" + currentEventId, function setData(data) {
                $("#membersEmail").html("");
                $('#membersLetterTmpl').tmpl({members : data}).appendTo('#membersEmail');
            })
        },

        selectAllMembers : function() {
            var checkboxes = $("#membersEmail").find(':checkbox');
            if($("#select-all-members").is(':checked')) {
                checkboxes.prop('checked', true);
            } else {
                checkboxes.prop('checked', false);
            }
        }

    };

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