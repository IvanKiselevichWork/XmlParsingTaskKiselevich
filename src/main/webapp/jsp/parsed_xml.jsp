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
<fmt:bundle basename="pagecontent">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>
            <fmt:message key="parsed_xml"/>
        </title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${root}/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="${root}/css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="${root}/css/style.min.css" rel="stylesheet">
        <!-- My custom css -->
        <link href="${root}/css/custom.css" rel="stylesheet">
    </head>

    <body style="background-image: url(${root}/img/background.jpg); background-repeat: no-repeat; background-size: cover; background-attachment: fixed;">

    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar flex-column flex-md-row">
        <a class="navbar-brand mr-0 mr-md-2" style="color: white">
            <strong><fmt:message key="parsed_xml"/></strong>
        </a>
        <div class="navbar-nav-scroll">
            <ul class="navbar-nav bd-navbar-nav flex-row">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}">
                        <fmt:message key="home_page"/>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Navbar -->


    <jsp:useBean id="isXmlValid" scope="request" type="java.lang.Boolean"/>
    <c:if test="${isXmlValid == true}">
        <!--Main layout-->
        <main>
            <div class="strokeme">
                <!--Section: Main info-->
                <section>
                    <!-- Full Page Intro -->
                    <div class="mask rgba-black-light wow fadeIn"
                         style="padding-top: 4em; padding-left: 1em; padding-right: 1em;">
                        <h3><fmt:message key="result"/></h3>

                        <jsp:useBean id="filesList" scope="request" type="java.util.List"/>
                        <jsp:useBean id="parserType" scope="request" type="java.lang.String"/>
                        <div>
                            <fmt:message key="uploaded_files"/>
                            <br>
                            <c:forEach var="fileName" items="${filesList}">
                                ${fileName}
                                <br>
                            </c:forEach>
                            <fmt:message key="parser_type"/> ${parserType}
                        </div>

                        <br>
                        <div>
                            <jsp:useBean id="medicines" scope="request"
                                         type="by.kiselevich.xmlparser.entity.medicins.Medicines"/>
                            <table class="table-bordered">
                                <caption><fmt:message key="table_caption"/></caption>
                                <tr>
                                    <th id="Id" rowspan="3"><br>Id</th>
                                    <th id="Nickname" rowspan="3">Nickname</th>
                                    <th id="Name" rowspan="3">Name</th>
                                    <th id="Pharm" rowspan="3">Pharm</th>
                                    <th id="Group" rowspan="3">Group</th>
                                    <th id="Analogs" rowspan="3">Analogs</th>
                                    <th id="Versions" colspan="11" style="text-align:center">Versions</th>
                                </tr>
                                <tr>
                                    <td rowspan="2">Form</td>
                                    <td rowspan="2">Manufacturer</td>
                                    <td colspan="4">Certificate</td>
                                    <td colspan="3">Package</td>
                                    <td colspan="2">Dosage</td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td>StartDate</td>
                                    <td>EndDate</td>
                                    <td>Organization</td>
                                    <td>Type</td>
                                    <td>Amount</td>
                                    <td>Price</td>
                                    <td>Count</td>
                                    <td>Periodicity</td>
                                </tr>
                                <c:forEach var="medicine" items="${medicines.medicine}" varStatus="status">
                                <tr>
                                    <td rowspan="${medicine.versions.version.size()}">
                                            ${medicine.id}
                                    </td>
                                    <td rowspan="${medicine.versions.version.size()}">
                                            ${medicine.nickname}
                                    </td>
                                    <td rowspan="${medicine.versions.version.size()}">
                                            ${medicine.name}
                                    </td>
                                    <td rowspan="${medicine.versions.version.size()}">
                                            ${medicine.pharm}
                                    </td>
                                    <td rowspan="${medicine.versions.version.size()}">
                                            ${medicine.group}
                                    </td>
                                    <td rowspan="${medicine.versions.version.size()}">
                                        <c:forEach var="analog" items="${medicine.analogs.analogId}">
                                            ${analog.value.toString()}
                                            <br>
                                        </c:forEach>
                                    </td>
                                    <c:forEach var="version" items="${medicine.versions.version}"
                                               varStatus="versionsStatus">
                                    <td>
                                            ${version.form}
                                    </td>
                                    <td>
                                            ${version.manufacturer}
                                    </td>
                                    <td>
                                            ${version.certificate.number}
                                    </td>
                                    <td>
                                            ${version.certificate.startDate}
                                    </td>
                                    <td>
                                            ${version.certificate.endDate}
                                    </td>
                                    <td>
                                            ${version.certificate.organization}
                                    </td>
                                    <td>
                                            ${version.packageType.type}
                                    </td>
                                    <td>
                                            ${version.packageType.amount}
                                    </td>
                                    <td>
                                            ${version.packageType.price}
                                    </td>
                                    <td>
                                            ${version.dosage.count}
                                    </td>
                                    <td>
                                            ${version.dosage.count}
                                    </td>
                                </tr>
                                <c:if test="${versionsStatus} ne ${medicine.versions.version.size() - 1}">
                                <tr>
                                    </c:if>
                                    </c:forEach>
                                    </c:forEach>
                            </table>
                        </div>
                    </div>
                    <!-- Full Page Intro -->
                </section>
                <!--Section: More-->
            </div>
        </main>
        <!--Main layout-->
    </c:if>

    <c:if test="${isXmlValid == false}">
        <!--Main layout-->
        <div class="view strokeme">
            <!--Section: Main info-->
            <section>
                <!-- Full Page Intro -->
                <div class="mask rgba-black-light wow fadeIn"
                     style="padding-top: 4em; padding-left: 1em; padding-right: 1em;">
                    <h3><fmt:message key="result"/></h3>
                    <jsp:useBean id="message" scope="request" type="java.lang.String"/>
                    <div>
                        <fmt:message key="${message}"/>
                    </div>
                </div>
                <!-- Full Page Intro -->
            </section>
            <!--Section: More-->
        </div>
        <!--Main layout-->
    </c:if>

    <!--Footer-->
    <footer class="page-footer text-center font-small">

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