<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>FLIGHT EDIT</title>

    <link rel="shortcut icon" href="${ctx}/img/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/flight_edit.css">

    <script defer src="${ctx}/js/lib/jquery-3.4.1.min.js"></script>
    <script defer src="${ctx}/js/locale.js"></script>
    <script defer src="${ctx}/js/flight_edit.js"></script>
</head>

<body>
<div id="content">
    <div id="empty"></div>
    <table id="header">
        <tr>
            <td id="space"></td>
            <td id="mainTab" class="tab"><a href="${ctx}/main">main</a></td>
            <td id="administratorTab" class="picketTab">administrator</td>
            <td id="locale">
                <select id="lang">
                    <option value="default">default</option>
                    <option value="en_US">english</option>
                    <option value="ru_RU">русский</option>
                </select>
            <td id="sign">
                <span class="pseudolink" onclick="signOut()">sign out</span>
            </td>
        </tr>
    </table>
    <form id="formMain">
        <input id="id" type="hidden" value="${flight.getId()}">
        <input id="crewId" type="hidden" value="${flight.getCrew().getId()}">
        <label id="labelFlightNumber" for="flightNumber">flight number:</label><br>
        <input id="flightNumber" type="text" value="<c:if test="${flight.getFlightNumber() > 0}">${flight.getFlightNumber()}</c:if>">
        <span id="messageFlightNumber" class="message"></span><br>
        <label id="labelStartPoint" for="startPoint">from:</label><br>
        <input id="startPoint" type="text" maxlength="30ch" value="${flight.getStartPoint()}">
        <span id="messageStartPoint" class="message"></span><br>
        <label id="labelDestinationPoint" for="destinationPoint">to:</label><br>
        <input id="destinationPoint" type="text" maxlength="30ch" value="${flight.getDestinationPoint()}">
        <span id="messageDestinationPoint" class="message"></span>
        <table>
            <tr>
                <td>
                    <label id="labelDepartureDate" for="departureDate">departure date:</label><br>
                    <input id="departureDate" type="date" value="${flight.getDepartureDate()}">
                </td>
                <td>
                    <label id="labelDepartureTime" for="departureTime">time:</label><br>
                    <input id="departureTime" type="time" value="${flight.getDepartureTime()}">
                </td>
                <td>
                    <br><span id="messageDepartureDateTime" class="message"></span>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="labelArrivalDate" for="arrivalDate">arrival date:</label><br>
                    <input id="arrivalDate" type="date" value="${flight.getArrivalDate()}">
                </td>
                <td>
                    <label id="labelArrivalTime" for="arrivalTime">time:</label><br>
                    <input id="arrivalTime" type="time" value="${flight.getArrivalTime()}">
                </td>
                <td>
                    <br><span id="messageArrivalDateTime" class="message"></span>
                </td>
            </tr>
        </table>
        <label id="labelPlane" for="plane">plane:</label><br>
        <input id="plane" type="text" maxlength="20ch" value="${flight.getPlane()}">
        <span id="messagePlane" class="message"></span><br>
        <input id="buttonSave" type="button" value="Save" onclick="buttonSaveAction()">
        <input id="buttonCancel" type="button" value="Cancel" onclick="location.href='${ctx}/administrator'">
    </form>
</div>
</body>
</html>