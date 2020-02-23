<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>XML Parser</title>
    </head>
    <body>
        <h3>File Upload:</h3>
        Select a file to upload: <br/>
        <form action="./parser" method="post" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <br/>
            <input type="submit" value="Upload File"/>
        </form>
        <input type="radio" id="dom" name="parser_type" value="dom" checked="checked">
        <label for="dom">DOM Parser</label><br>
        <input type="radio" id="sax" name="parser_type" value="sax">
        <label for="sax">SAX Parser</label><br>
        <input type="radio" id="stax" name="parser_type" value="stax">
        <label for="stax">STAX Parser</label>
    </body>
</html>
