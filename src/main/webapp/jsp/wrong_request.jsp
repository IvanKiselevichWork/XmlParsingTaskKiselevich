<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
    <head>
        <title>Wrong request</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>Something went wrong...</h3>
            Please go home page: <br/>
            <form action="${pageContext.request.contextPath}" method="get">
                <input type="hidden" name="command" value="HOME" />
                <input type="submit" value="Go home"/>
            </form>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
