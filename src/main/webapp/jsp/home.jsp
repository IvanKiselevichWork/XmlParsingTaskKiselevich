<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>Welcome to home page!</h3>
            <br/>
            <c:if test="${userType == 'GUEST'}">
                Hello guest!
                <br>
                Select action:
                <br>
                <form action="./" method="get">
                    <input type="hidden" name="command" value="SIGN_IN_FORM" />
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
                <br>
                <form action="./" method="get">
                    <input type="hidden" name="command" value="SIGN_UP_FORM" />
                    <button type="submit" class="btn btn-primary">Sign up</button>
                </form>
            </c:if>
            <c:if test="${userType == 'USER'}">
                Hello user!
                <br>
                Select action:
                <br>
                <form action="./" method="get">
                    <input type="hidden" name="command" value="XML_UPLOAD_FORM" />
                    <button type="submit" class="btn btn-primary">XML Parser</button>
                </form>
                <br>
                <form action="./" method="post">
                    <input type="hidden" name="command" value="SIGN_OUT" />
                    <button type="submit" class="btn btn-primary">Sign out</button>
                </form>
            </c:if>
        </div>

        <script src="${root}/js/jquery-3.4.1.min.js"></script>
        <script src="${root}/js/bootstrap.min.js"></script>
    </body>
</html>
