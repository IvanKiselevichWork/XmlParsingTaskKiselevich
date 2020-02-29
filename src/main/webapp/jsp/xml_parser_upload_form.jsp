<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
    <head>
        <title>XML Parser</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron text-lg-left">
            <h3>File Upload:</h3>
            Select a file to upload: <br/>
            <form action="./" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="PARSE_XML" />
                <input type="file" name="file"/>
                <br/>
                <input type="radio" id="dom" name="parser_type" value="dom" checked="checked">
                <label for="dom">DOM Parser</label><br>
                <input type="radio" id="sax" name="parser_type" value="sax">
                <label for="sax">SAX Parser</label><br>
                <input type="radio" id="stax" name="parser_type" value="stax">
                <label for="stax">STAX Parser</label>
                <br/>
                <input type="submit" value="Upload File"/>
            </form>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
