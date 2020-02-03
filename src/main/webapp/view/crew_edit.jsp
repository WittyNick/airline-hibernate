<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>CREW EDIT</title>

    <link rel="shortcut icon" href="${ctx}/img/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/crew_edit.css">

    <script defer src="${ctx}/js/lib/jquery-3.4.1.min.js"></script>
    <script defer src="${ctx}/js/locale.js"></script>
    <script defer src="${ctx}/js/crew_edit.js"></script>
</head>

<body>
<div id="content">
    <div id="empty"></div>
    <table id="header">
        <tr>
            <td id="space"></td>
            <td id="mainTab" class="tab"><a href="${ctx}/main">main</a></td>
            <td id="dispatcherTab" class="picketTab">dispatcher</td>
            <td id="locale">
                <select id="lang">
                    <option value="default">default</option>
                    <option value="en_US">english</option>
                    <option value="ru_RU">русский</option>
                </select>
            </td>
            <td id="sign">
                <span class="pseudolink" onclick="signOut()">sign out</span>
            </td>
        </tr>
    </table>

    <input id="flightId" type="hidden" value="${flightId}">
    <input id="crewId" type="hidden" value="${crew.getId()}">

    <div id="editElements">
        <label id="labelName" for="name">crew name:</label><br>

        <input id="name" type="text" value="${crew.getName()}">

        <span id="messageName" class="message"></span>
        <table id="employeeList">
            <caption id="captionEmployeeList">Employee List</caption>
            <thead>
            <tr id="hatEmployeeListRow">
                <th>id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>positionEnum</th>
                <th>Position</th>
            </tr>
            </thead>
            <tbody id="employeeListBody">
            <c:forEach var="employee" items="${crew.getEmployees()}">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>${employee.getName()}</td>
                    <td>${employee.getSurname()}</td>
                    <td>${employee.getPosition().name()}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input id="buttonRemoveFromCrew" type="button" value="Remove from Crew" onclick="removeFromCrewAction()">
        <table id="tableNewEmployee">
            <tr>
                <td>
                    <label id="labelNewEmployeeName" for="newEmployeeName">name:</label><br>
                    <input id="newEmployeeName" type="text" value="">
                </td>
                <td>
                    <label id="labelNewEmployeeSurname" for="newEmployeeSurname">surname:</label><br>
                    <input id="newEmployeeSurname" type="text">
                </td>
                <td>
                    <label id="labelNewEmployeePosition" for="newEmployeePosition">position:</label><br>
                    <select id="newEmployeePosition">
                        <option value="PILOT">pilot</option>
                        <option value="NAVIGATOR">navigator</option>
                        <option value="COMMUNICATOR">communicator</option>
                        <option value="STEWARDESS">stewardess</option>
                    </select>
                </td>
                <td>
                    <br>
                    <input id="buttonEngageEmployee" type="button" value="Engage Employee"
                           onclick="engageEmployeeAction()">
                </td>
            </tr>
        </table>
        <div id="messageNewEmployee" class="message"></div>
        <table id="employeeBase">
            <caption id="captionEmployeeBase">Employee Base</caption>
            <thead>
            <tr id="hatEmployeeBaseRow">
                <th>id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>positionEnum</th>
                <th>Position</th>
            </tr>
            </thead>

            <tbody id="employeeBaseBody">
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>${employee.getName()}</td>
                    <td>${employee.getSurname()}</td>
                    <td>${employee.getPosition().name()}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input id="buttonAddToCrew" type="button" value="Add to Crew" onclick="addToCrewAction()">
        <input id="buttonFireEmployee" type="button" value="Fire Employee" onclick="fireEmployeeAction()"><br>
    </div>
    <div id="buttons">
        <input id="buttonSave" type="button" value="Save" onclick="saveAction()">
        <input id="buttonCancel" type="button" value="Cancel" onclick="location.href='${ctx}/dispatcher'">
    </div>
</div>
</body>
</html>