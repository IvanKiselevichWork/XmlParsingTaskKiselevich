<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Wrong request</title>
    </head>
    <body>
        <h3>Something went wrong...</h3>
        Please go home page: <br/>
        <form action="parser" method="get">
            <input type="hidden" name="command" value="HOME" />
            <input type="submit" value="Go home"/>
        </form>
    </body>
</html>
