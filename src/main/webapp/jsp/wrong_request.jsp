<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html lang="en">
    <head>
        <title>Wrong request</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>Something went wrong...</h3>
            Please go home page: <br/>
            <form action="${pageContext.request.contextPath}" method="get">
                <input type="hidden" name="command" value="HOME" />
                <button type="submit" class="btn btn-primary">Home</button>
            </form>
        </div>

        <script src="${root}/js/jquery-3.4.1.min.js"></script>
        <script src="${root}/js/bootstrap.min.js"></script>
    </body>
</html>
