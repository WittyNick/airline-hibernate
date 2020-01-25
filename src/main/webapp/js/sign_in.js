let messagePasswordIndex = 0;
let messageLoginIndex = 0;
let messageFailIndex = 0;

let inputLogin;
let inputPassword;

window.onload = function() {
    inputLogin = doc.getElementById("login");
    inputPassword = doc.getElementById("password");

    localizeSignIn();
    doc.getElementById("lang").addEventListener("change", changeLocaleEventHandler);

    let buttonSubmit = doc.getElementById("buttonSubmit");
    buttonSubmit.addEventListener("click", buttonSubmitAction, false);
};

function changeLocaleEventHandler() {
    let body = "locale=" + document.getElementById("lang").value;
    ajax("POST", "locale/change", body, changeLocale, true, "application/x-www-form-urlencoded; charset=UTF-8");
}

function changeLocale(responseText) {
    if ("ok" === responseText) {
        localizeSignIn();
    }
}

/*
User Authorization
ajax post request to LoginServlet.
Get user type from servlet:
administrator - go to the page administrator.jsp via AdministratorServlet;
dispatcher - go to the dispatcher.jsp page via DispatcherServlet;
user - stay on the sign_in.jsp page, clear password input field.
 */
function buttonSubmitAction() {
    if (!isValid()) {
        return;
    }
    let body = "login=" + encodeURIComponent(inputLogin.value) +
            "&password=" + encodeURIComponent(inputPassword.value);
    ajax("POST", "signin", body, onSignInAction, true, "application/x-www-form-urlencoded; charset=utf-8");
}

function onSignInAction(responseText) {
    if ("administrator" === responseText) {
        doc.location.href = "administrator";
    } else if ("dispatcher" === responseText) {
        doc.location.href = "dispatcher";
    } else {
        inputPassword.value = "";
        inputLogin.select();
        messageFailIndex = 3;
        doc.getElementById("messageFail").innerText = messages[messageFailIndex];
    }
}

function isValid() {
    let valid = true;
    let inputLogin = doc.getElementById("login");
    let inputPassword = doc.getElementById("password");
    if (!inputPassword.value) {
        messagePasswordIndex = 2;
        inputPassword.focus();
        valid = false;
    } else {
        messagePasswordIndex = 0;
    }
    if (!inputLogin.value.trim()) {
        messageLoginIndex = 1;
        inputLogin.select();
        valid = false;
    } else {
        messageLoginIndex = 0;
    }
    messageFailIndex = 0;
    setMessages();
    return valid;
}

function setMessages() {
    doc.getElementById("messagePassword").innerText = messages[messagePasswordIndex];
    doc.getElementById("messageLogin").innerText = messages[messageLoginIndex];
    doc.getElementById("messageFail").innerText = messages[messageFailIndex];
}