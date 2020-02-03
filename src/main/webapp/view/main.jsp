<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>MAIN</title>
    <link rel="shortcut icon" href="${ctx}/img/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">

    <script defer src="${ctx}/js/lib/jquery-3.4.1.min.js"></script>
    <script defer src="${ctx}/js/locale.js"></script>
    <script defer src="${ctx}/js/main.js"></script>
</head>
<body>
<div id="content">
    <div id="empty"></div>
    <table id="header">
        <tr>
            <td id="space"></td>
            <td id="mainTab" class="picketTab">main</td>
            <td id="administratorTab" class="tab hidden"><a href="${ctx}/administrator">administrator</a></td>
            <td id="dispatcherTab" class="tab hidden"><a href="${ctx}/dispatcher">dispatcher</a></td>
            <td id="locale">
                <select id="lang">
                    <option value="default">default</option>
                    <option value="en_US">english</option>
                    <option value="ru_RU">русский</option>
                </select>
            </td>
            <td id="sign">
                <span class="pseudolink hidden" onclick="location.href='${ctx}/signin'">sign in</span>
                <span class="pseudolink hidden" onclick="signOut()">sign out</span>
            </td>
        </tr>
    </table>

    <table id="tableFlights">
        <caption id="tableCaption">Flights</caption>
        <thead>
        <tr id="hatRow">
            <th>Number</th>
            <th>From</th>
            <th>To</th>
            <th>Departure Date</th>
            <th>Departure Time</th>
            <th>Arrival Date</th>
            <th>Arrival Time</th>
            <th>Plane</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        </tbody>
    </table>
</div>
</body>
</html>
