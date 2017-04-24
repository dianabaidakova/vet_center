<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ranga Reddy">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Information</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                Employee Details
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="userRegisterForm" cssClass="form-horizontal" modelAttribute="user" method="post" action="saveUser">

                <div class="form-group">
                    <form:label path="login" cssClass="control-label col-xs-3">Login</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="login" value="${userObject.login}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="password" cssClass="control-label col-xs-3">Password</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="password" value="${userObject.password}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="id_role" cssClass="control-label col-xs-3">Password</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="id_role" value="${userObject.id_role}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveUser" class="btn btn-primary" value="Save" onclick="return submitUserForm();"/>
                        </div>
                        <div class="col-xs-4">
                        </div>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function submitUserForm() {

        // getting the employee form values
        var login = $('#login').val().trim();
        var password = $('#password').val();
        var id_role = $('#id_role').val();
        if(login.length ==0) {
            alert('Please enter login');
            $('#login').focus();
            return false;
        }
        return true;
    };
</script>
</body>
</html>