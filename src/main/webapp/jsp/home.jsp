<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>Welcome to home page!</h3>
            Select action: <br/>
            <form action="./" method="get">
                <input type="hidden" name="command" value="XML_UPLOAD_FORM" />
                <input type="submit" value="XML Parser"/>
            </form>
        </div>

        <script src="./js/jquery-3.4.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
    </body>
</html>
