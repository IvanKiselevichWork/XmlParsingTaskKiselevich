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
<fmt:bundle basename="pagecontent" prefix="xml_parser_upload_form.">
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
        <h3><fmt:message key="file_upload"/></h3>
        <fmt:message key="select_file"/><br/>
        <form action="./" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="PARSE_XML"/>
            <input type="file" name="file"/>
            <br/>
            <input type="radio" id="dom" name="parser_type" value="dom" checked="checked">
            <label for="dom"><fmt:message key="dom_parser"/></label><br>
            <input type="radio" id="sax" name="parser_type" value="sax">
            <label for="sax"><fmt:message key="sax_parser"/></label><br>
            <input type="radio" id="stax" name="parser_type" value="stax">
            <label for="stax"><fmt:message key="stax_parser"/></label>
            <br/>
            <button type="submit" class="btn btn-primary"><fmt:message key="upload_and_parse"/></button>
        </form>
    </div>

    <script src="${root}/js/jquery-3.4.1.min.js"></script>
    <script src="${root}/js/bootstrap.min.js"></script>
    </body>
    </html>
</fmt:bundle>