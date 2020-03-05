<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}">
    <fmt:setLocale value="en_US"/>
</c:if>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:bundle basename="pagecontent" prefix="sign_in.">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <html lang="en">
    <head>
        <title><fmt:message key="title"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
    </head>
    <body>
    <div class="jumbotron text-lg-left">
        <h3><fmt:message key="sign_in"/></h3>
        <form class="mx-auto" style="width: 30%;" action="./" method="post">
            <input type="hidden" name="command" value="SIGN_IN"/>
            <div class="form-group">
                <label for="user-name-label"><fmt:message key="login"/></label>
                <input type="text" class="form-control" id="user-name-label" name="login" aria-describedby="loginHelp"
                       placeholder="<fmt:message key="enter_login"/>">
                <small id="loginHelp" class="form-text text-muted"><fmt:message key="login_text"/>.</small>
            </div>
            <div class="form-group">
                <label for="password-label"><fmt:message key="password"/></label>
                <input type="password" class="form-control" id="password-label" name="password" placeholder="<fmt:message key="enter_password"/>">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="sign_in"/></button>
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
</fmt:bundle>