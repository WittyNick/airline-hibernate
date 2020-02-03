$(document).ready(function () {
    localizeFlightEdit();
    $('#lang').on('change', changeLocaleEventHandler);
});

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $('#lang').val(),
        dataType: 'text',
        success: localizeFlightEdit
    });
}

function buttonSaveAction() {
    if (!validate()) {
        return;
    }
    let flight = {
        'id': +$('#id').val(),
        'flightNumber': +$('#flightNumber').val(),
        'startPoint': $('#startPoint').val(),
        'destinationPoint': $('#destinationPoint').val(),
        'departureDate': $('#departureDate').val(),
        'departureTime': $('#departureTime').val(),
        'arrivalDate': $('#arrivalDate').val(),
        'arrivalTime': $('#arrivalTime').val(),
        'plane': $('#plane').val(),
        'crew': {
            'id': +$('#crewId').val()
        }
    };
    $.ajax({
        type: 'POST',
        url: '../administrator/save',
        data: JSON.stringify(flight),
        contentType: 'json',
        success: function () {
            $(location).prop('href', '../administrator');
        }
    });
}

function signOut() {
    $.ajax({
        type: 'POST',
        url: '../signout',
        contentType: false,
        success: function () {
            $(location).prop('href', '../main');
        }
    });
}

let messageFlightNumberIndex = 0;
let messageStartPointIndex = 0;
let messageDestinationPointIndex = 0;
let messageDepartureDateTimeIndex = 0;
let messageArrivalDateTimeIndex = 0;
let messagePlaneIndex = 0;

function validate() {
    let isValid = true;
    let $flightNumber = $('#flightNumber');

    if ('' === $flightNumber.val().trim()) {
        messageFlightNumberIndex = 1;
        isValid = false;
    } else if (/[0-9]/.test($flightNumber.val())) {
            messageFlightNumberIndex = 0;
    } else {
            messageFlightNumberIndex = 2;
            isValid = false;
    }

    if ('' === $('#startPoint').val().trim()) {
        messageStartPointIndex = 1;
        isValid = false;
    } else {
        messageStartPointIndex = 0;
    }

    if ('' === $('#destinationPoint').val().trim()) {
        messageDestinationPointIndex = 1;
        isValid = false;
    } else {
        messageDestinationPointIndex = 0;
    }

    if ('' === $('#departureDate').val() || '' === $('#departureTime').val()) {
        messageDepartureDateTimeIndex = 3;
        isValid = false;
    } else {
        messageDepartureDateTimeIndex = 0;
    }

    if ('' === $('#arrivalDate').val() || '' === $('#arrivalTime').val()) {
        messageArrivalDateTimeIndex = 3;
        isValid = false;
    } else {
        messageArrivalDateTimeIndex = 0;
    }

    if ('' === $('#plane').val()) {
        messagePlaneIndex = 1;
        isValid = false;
    } else {
        messagePlaneIndex = 0;
    }
    showErrorMessages();
    return isValid;
}

function showErrorMessages() {
    $('#messageFlightNumber').html(messages[messageFlightNumberIndex]);
    $('#messageStartPoint').html(messages[messageStartPointIndex]);
    $('#messageDestinationPoint').html(messages[messageDestinationPointIndex]);
    $('#messageDepartureDateTime').html(messages[messageDepartureDateTimeIndex]);
    $('#messageArrivalDateTime').html(messages[messageArrivalDateTimeIndex]);
    $('#messagePlane').html(messages[messagePlaneIndex]);
}