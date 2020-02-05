<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>LOGIN</title>

    <link rel="shortcut icon" href="${ctx}/img/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/sign_in.css">

    <script defer src="${ctx}/js/lib/jquery-3.4.1.min.js"></script>
    <script defer src="${ctx}/js/locale.js"></script>
    <script defer src="${ctx}/js/sign_in.js"></script>
</head>
<body>
<div id="content">
    <div id="empty"></div>
    <table id="header">
        <tr>
            <td id="locale">
                <select id="lang">
                    <option value="default">default</option>
                    <option value="en_US">english</option>
                    <option value="ru_RU">русский</option>
                </select>
            </td>
            <td id="sign"></td>
        </tr>
    </table>

    <fieldset id="fieldsetLogIn">
        <legend id="legendFieldset">Sign In</legend>
        <form>
            <label id="labelLogin" for="login">login:</label><br>
            <input id="login" name="login" type="text" tabindex="1" autofocus>
            <span id="messageLogin" class="message"></span><br>

            <label id="labelPassword" for="password">password:</label><br>
            <input id="password" name="password" type="password" tabindex="2" autofocus>
            <span id="messagePassword" class="message"></span><br>
            <div id="messageFail" class="message"></div>

            <input id="buttonSubmit" type="button" value="Enter" tabindex="3" autofocus>
            <input id="buttonCancel" type="button" value="Cancel" onclick="location.href='${ctx}'">
        </form>
    </fieldset>
    <br>
    <div class="container">
        <i>
            <b>* default accounts:</b> <br>
            <b>login:</b> admin | <b>password:</b> admin<br>
            <b>login:</b> dispatcher | <b>password:</b> dispatcher
        </i>
    </div>
</div>
</body>
</html>
