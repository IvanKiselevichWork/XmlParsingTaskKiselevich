<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <h3>Welcome to home page!</h3>
        Select action: <br/>
        <form action="parser" method="get">
            <input type="hidden" name="command" value="XML_UPLOAD_FORM" />
            <input type="submit" value="XML Parser"/>
        </form>
    </body>
</html>
