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
<fmt:bundle basename="pagecontent" prefix="wrong_request.">
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
        <h3><fmt:message key="message"/></h3>
        <fmt:message key="go_home"/><br/>
        <form action="${pageContext.request.contextPath}" method="get">
            <input type="hidden" name="command" value="HOME"/>
            <button type="submit" class="btn btn-primary"><fmt:message key="home"/></button>
        </form>
    </div>

    <script src="${root}/js/jquery-3.4.1.min.js"></script>
    <script src="${root}/js/bootstrap.min.js"></script>
    </body>
    </html>
</fmt:bundle>