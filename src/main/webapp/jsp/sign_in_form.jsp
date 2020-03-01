<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron text-lg-left">
    <h3>Sign in</h3>
    <form action="./" method="post">
        <input type="hidden" name="command" value="SIGN_IN"/>
        <div class="form-group">
            <label for="user-name-label">Login</label>
            <input type="text" class="form-control" id="user-name-label" name="login" aria-describedby="loginHelp"
                   placeholder="Enter login">
            <small id="loginHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password-label">Password</label>
            <input type="password" class="form-control" id="password-label" name="password" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
    <br>
    <c:if test="${message != null}">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </c:if>

</div>

<script src="${root}/js/jquery-3.4.1.min.js"></script>
<script src="${root}/js/bootstrap.min.js"></script>
</body>
</html>
