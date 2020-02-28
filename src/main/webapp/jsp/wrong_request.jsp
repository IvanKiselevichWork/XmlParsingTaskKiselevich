<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Wrong request</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>Something went wrong...</h3>
            Please go home page: <br/>
            <form action="./" method="get">
                <input type="hidden" name="command" value="HOME" />
                <input type="submit" value="Go home"/>
            </form>
        </div>

        <script src="./js/jquery-3.4.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
    </body>
</html>
