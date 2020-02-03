let messagePasswordIndex = 0;
let messageLoginIndex = 0;
let messageFailIndex = 0;

let inputLogin;
let inputPassword;

$(document).ready(function() {
    inputLogin = $('#login');
    inputPassword = $('#password');
    localizeSignIn();
    $('#lang').on('change', changeLocaleEventHandler);
    $('#buttonSubmit').on('click', buttonSubmitAction);
});

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $("#lang").val(),
        dataType: "text",
        success: localizeSignIn
    });
}

function buttonSubmitAction() {
    if (!validate()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'signin',
        data: 'login=' + $(inputLogin).val() + '&password=' + $(inputPassword).val(),
        dataType: "text",
        success: onSignInAction
    });
}

function onSignInAction(userRole) {
    if ('administrator' === userRole) {
        $(location).prop('href', 'administrator');
    } else if ('dispatcher' === userRole) {
        $(location).prop('href', 'dispatcher');
    } else {
        $(inputPassword).val('');
        $(inputLogin).select();
        messageFailIndex = 3;
        $('#messageFail').html(messages[messageFailIndex]);
    }
}

function validate() {
    let isValid = true;
    if ('' === $(inputPassword).val()) {
        messagePasswordIndex = 2;
        $(inputPassword).focus();
        isValid = false;
    } else {
        messagePasswordIndex = 0;
    }
    if ('' === $(inputLogin).val().trim()) {
        messageLoginIndex = 1;
        inputLogin.select();
        isValid = false;
    } else {
        messageLoginIndex = 0;
    }
    messageFailIndex = 0;
    showErrorMessages();
    return isValid;
}

function showErrorMessages() {
    $('#messagePassword').html(messages[messagePasswordIndex]);
    $('#messageLogin').html(messages[messageLoginIndex]);
    $('#messageFail').html(messages[messageFailIndex]);
}