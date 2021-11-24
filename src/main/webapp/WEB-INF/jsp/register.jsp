<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center align-items-center" style="height:100vh">
        <div class="col-4">
            <div class="card">
                <div class="card-body">
                    <h1>Register</h1>
                    <c:if test="${not empty message}">
                        <div class="alert alert-secondary" role="alert">${message}
                        </div>
                    </c:if>
                    <form action="<%=request.getContextPath()%>/register" method="post" class="container">
                        <div class="form-group">
                            <label for="user">User:</label>
                            <input type="text" id="user" class="form-control" name="user"><br>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" class="form-control" name="password"
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                   required><br>
                        </div>
                        <div class="form-group">
                            <label for="passwordConfirmation">Repeat Password:</label>
                            <input type="password" id="passwordConfirmation" class="form-control" name="passwordConfirmation"
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                   required><br>
                        </div>
                        <button type="submit" id="register" class="btn btn-primary">Register</button>
                        <a onclick="window.location.href='login.jsp';" id="back">Back</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

