<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <jsp:include page="layer/base.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="layer/header.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-form">
                    <!-- Form header -->
                    <div class="panel-heading">
                        <h2 class="title">Registration</h2>
                        <p>Already have an account? <a href="/login">Sign in</a>.</p>
                    </div>

                    <div class="panel-body">
                        <form role="form" id="registration-form">
                            <!-- Name -->
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <div class="form-group">
                                        <label for="name" class="control-label">Name <span class="required-field">*</span></label>
                                        <div class="has-feedback">
                                            <input type="text" class="form-control" id="name" name="name" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 form-group">
                                    <div class="form-group">
                                        <label for="surname" class="control-label">Surname <span class="required-field">*</span></label>
                                        <div class="has-feedback">
                                            <input type="text" class="form-control" id="surname" name="surname" required>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Email -->
                            <div class="form-group">
                                <label for="email" class="control-label">Email <span class="required-field">*</span></label>
                                <div class="has-feedback">
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                            </div>

                            <div class="alerts">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <span id="alert-danger-text">Error create account</span>
                                </div>
                                <div class="alert alert-success">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <span id="alert-text">Account created</span>
                                </div>
                            </div>

                            <!-- Passwords -->
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label for="password1" class="control-label">Password <span class="required-field">*</span></label>
                                    <div class="has-feedback">
                                        <input type="password" class="form-control" id="password1" name="password1" required>
                                    </div>
                                    <p class="help-block">At least 6 characters long.</p>
                                </div>

                                <div class="col-sm-6 form-group">
                                    <label for="password2" class="control-label">Re-enter password <span class="required-field">*</span></label>
                                    <div class="has-feedback">
                                        <input type="password" class="form-control" id="password2" name="password2" required>
                                    </div>
                                </div>
                            </div>

                            <!-- Birthday -->
                            <div class="form-group">
                                <label class="control-label">Birthday</label>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <select class="form-control" name="day" required>
                                            <option value="0">Day</option>
                                            <c:forEach begin="1" end="31" var="counter">
                                                <option value="${counter}">${counter}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-sm-4">
                                        <select class="form-control" name="month" id="month">
                                            <option value="0">Month</option>
                                            <option>January</option>
                                            <option>February</option>
                                            <option>March</option>
                                            <option>April</option>
                                            <option>May</option>
                                            <option>June</option>
                                            <option>July</option>
                                            <option>August</option>
                                            <option>September</option>
                                            <option>October</option>
                                            <option>November</option>
                                            <option>December</option>
                                        </select>
                                    </div>

                                    <div class="col-sm-4">
                                        <select class="form-control" name="year">
                                            <option value="0">Year</option>
                                            <c:forEach begin="1900" end="2016" var="counter">
                                                <option value="${counter}">${counter}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <!-- Create button -->
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary" id="btn-create-account">Create Account</button>
                            </div>
                        </form>
                    </div>

                    <!-- Form footer -->
                    <div class="panel-footer">
                        <span class="required-field">*</span> - required field
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
    $(document).ready( function() {

        // TODO need refactor
        $("form#registration-form").submit(function(event){
            event.preventDefault();

            var month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October",  "November", "December"];
            var regInfo = {};
            var user = {};

            $(this).serializeArray().forEach(function(item) {
                regInfo[item.name] = item.value;
            });

            user['birthDay'] = new Date(regInfo.year, month.indexOf(regInfo.month), regInfo.day, 0, 0, 0, 0);
            user['name'] = regInfo.name;
            user['surname'] = regInfo.surname;
            user['email'] = regInfo.email;
            user['password'] = regInfo.password1;

            if(regInfo.password1 === regInfo.password2) {
                $.ajax({
                    type: 'POST',
                    url: '/user/registration',
                    data: JSON.stringify(user),
                    contentType: 'application/json',
                    beforeSend:function(xhr){
                        xhr.setRequestHeader(header, token);
                    },
                    statusCode:{
                        200: function(){
                            $('.alert-success').show()
                        },
                        500: function(){
                            $('.alert-danger').show()
                        }
                    }
                })
            } else {
                $('#alert-danger-text').text("Passwords don't match");
                $('.alert-danger').show()
            }
        });

    })
</script>

</body>
</html>



