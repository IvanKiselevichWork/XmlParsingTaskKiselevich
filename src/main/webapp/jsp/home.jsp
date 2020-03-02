<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU"/>
<fmt:bundle basename="resources.pagecontent" prefix="home.">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <html lang="en">
    <head>
        <title>
            <fmt:message key="title"/>
        </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
    </head>
    <body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-us"> </span>
                            English</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#ru"><span class="flag-icon flag-icon-ru"> </span>
                                Russian</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="jumbotron text-lg-left">
        <h3><fmt:message key="welcome"/></h3>
        <br/>
        <c:if test="${sessionScope.userRole == 'GUEST'}">
            <fmt:message key="hello_guest"/>
            <br>
            <fmt:message key="select_action"/>
            <br>
            <form action="./" method="get">
                <input type="hidden" name="command" value="SIGN_IN_FORM"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="sign_in"/></button>
            </form>
            <br>
            <form action="./" method="get">
                <input type="hidden" name="command" value="SIGN_UP_FORM"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="sign_up"/></button>
            </form>
        </c:if>
        <c:if test="${sessionScope.userRole == 'USER'}">
            <fmt:message key="hello_user"/> ${sessionScope.login}
            <br>
            <fmt:message key="select_action"/>
            <br>
            <form action="./" method="get">
                <input type="hidden" name="command" value="XML_UPLOAD_FORM"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="xml_parser"/></button>
            </form>
            <br>
            <form action="./" method="post">
                <input type="hidden" name="command" value="SIGN_OUT"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="sign_out"/></button>
            </form>
        </c:if>
    </div>

    <script src="${root}/js/jquery-3.4.1.min.js"></script>
    <script src="${root}/js/bootstrap.min.js"></script>
    </body>
    </html>
</fmt:bundle>