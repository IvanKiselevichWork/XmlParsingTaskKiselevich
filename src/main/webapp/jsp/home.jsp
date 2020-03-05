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
<fmt:bundle basename="pagecontent" prefix="home.">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>
            <fmt:message key="title"/>
        </title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${root}/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="${root}/css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="${root}/css/style.min.css" rel="stylesheet">
        <style type="text/css">
            @media (min-width: 800px) and (max-width: 850px) {
                .navbar:not(.top-nav-collapse) {
                    background: #1C2331 !important;
                }
            }

        </style>
    </head>

    <body>

    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar">
        <div class="container">

            <!-- Brand -->
            <a class="navbar-brand" target="_blank" style="color: white">
                <strong>XML parser</strong>
            </a>

            <!-- Collapse -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Links -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <!-- Left -->
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#home">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>

                <!-- Right -->
                <ul class="navbar-nav nav-flex-icons">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <fmt:message key="change_language"/>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="./?command=CHANGE_LANGUAGE&targetLanguage=en_US">
                                <fmt:message key="english"/>
                            </a>
                            <a class="dropdown-item" href="./?command=CHANGE_LANGUAGE&targetLanguage=ru_RU">
                                <fmt:message key="russian"/>
                            </a>
                        </div>
                    </li>

                    <c:if test="${sessionScope.userRole == 'GUEST'}">
                        <li class="nav-item mr-4">
                            <a href="./?command=SIGN_IN_FORM" class="nav-link">
                                <fmt:message key="sign_in"/>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="./?command=SIGN_UP_FORM" class="nav-link border border-light rounded">
                                <fmt:message key="sign_up"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.userRole == 'USER'}">
                        <li class="nav-item" style="max-width: 50%">
                            <a class="nav-link">
                                ${sessionScope.login}
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="./?command=SIGN_OUT" class="nav-link border border-light rounded">
                                <fmt:message key="sign_out"/>
                            </a>
                        </li>
                    </c:if>
                </ul>

            </div>

        </div>
    </nav>
    <!-- Navbar -->

    <!-- Full Page Intro -->
    <div class="view"
         style="background-image: url(${root}/img/background.jpg); background-repeat: no-repeat; background-size: cover;">

        <!-- Mask & flexbox options-->
        <div class="mask rgba-black-light d-flex justify-content-center align-items-center">

            <!-- Content -->
            <div class="text-center white-text mx-5 wow fadeIn">
                <c:if test="${sessionScope.userRole == 'GUEST'}">
                    <h1 class="mb-4">
                        <strong><fmt:message key="guest_message"/></strong>
                    </h1>
                </c:if>
                <c:if test="${sessionScope.userRole == 'USER'}">
                    <h1 class="mb-4">
                        <strong><fmt:message key="xml_parser"/></strong>
                    </h1>

                    <a href="./?command=XML_UPLOAD_FORM" class="btn btn-outline-white btn-lg">
                        <fmt:message key="parse_xml"/>
                    </a>
                </c:if>
            </div>
            <!-- Content -->

        </div>
        <!-- Mask & flexbox options-->

    </div>
    <!-- Full Page Intro -->

    <!--Footer-->
    <footer class="page-footer text-center font-small wow fadeIn">

        <!--Copyright-->
        <div class="footer-copyright py-3">
            <fmt:message key="copyright"/>
        </div>
        <!--/.Copyright-->

    </footer>
    <!--/.Footer-->

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${root}/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${root}/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${root}/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${root}/js/mdb.min.js"></script>
    <!-- Initializations -->
    <script type="text/javascript">
        // Animations initialization
        new WOW().init();

    </script>
    </body>

    </html>
</fmt:bundle>