<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <title>Parsed XML</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${root}/css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron">
    <h3>Result:</h3>
    <jsp:useBean id="filesList" scope="request" type="java.util.List"/>
    <jsp:useBean id="parserType" scope="request" type="java.lang.String"/>
    <c:if test="${not empty filesList and filesList.size() gt 0}">
        <div>
            Uploaded files:
            <br>
            <c:forEach var="fileName" items="${filesList}">
                ${fileName}
                <br>
            </c:forEach>
            Parser type: ${parserType}
        </div>

        <br>
        <div>
            <jsp:useBean id="medicines" scope="request" type="by.kiselevich.xmlparser.entity.medicins.Medicines"/>
            <table class="table-bordered">
                <caption>XML parsed to table</caption>
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
                    <c:forEach var="version" items="${medicine.versions.version}" varStatus="versionsStatus">
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
    </c:if>
    <c:if test="${empty filesList or filesList.size() le 0}">
        <div>No files uploaded</div>
    </c:if>
</div>

<script src="${root}/js/jquery-3.4.1.min.js"></script>
<script src="${root}/js/bootstrap.min.js"></script>
</body>
</html>
