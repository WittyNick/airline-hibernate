$(document).ready(function() {
    validateUser();
    localizeMain();
    $('#lang').on('change', changeLocaleEventHandler);
    fillTableFlights();
});

function validateUser() {
    $.ajax({
        type: 'POST',
        url: 'user/validate',
        contentType: false,
        dataType: 'text',
        success: setPageView
    });
}

function fillTableFlights() {
    $.ajax({
        type: 'POST',
        url: 'main',
        contentType: false,
        dataType: 'json',
        success: addTableFlightsContent
    });
}

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $('#lang').val(),
        dataType: 'text',
        success: function () {
            localizeMain();
            $('#tableBody').empty();
            fillTableFlights();
        }
    });
}

function addTableFlightsContent(flights) {
    let rows = '';
    flights.forEach(function(flight) {
        rows += '<tr><td>' + flight.flightNumber +
            '</td><td>' + flight.startPoint +
            '</td><td>' + flight.destinationPoint +
            '</td><td>' + flight.departureDate +
            '</td><td>' + flight.departureTime +
            '</td><td>' + flight.arrivalDate +
            '</td><td>' + flight.arrivalTime +
            '</td><td>' + flight.plane + '</td></tr>';
    });
    $('#tableBody').html(rows);
}

function setPageView(responseText) {
    let $spans = $('#sign').children();
    if ('administrator' === responseText) {
        $spans.eq(1).removeClass('hidden');
        $('#administratorTab').removeClass('hidden');
    } else if ('dispatcher' === responseText) {
        $spans.eq(1).removeClass('hidden');
        $('#dispatcherTab').removeClass('hidden');
    } else { // 'guest'
        $spans.eq(0).removeClass('hidden');
    }
}

function signOut() {
    $.ajax({
        type: 'POST',
        url: 'signout',
        contentType: false,
        success: setPageViewGuest
    });
}

function setPageViewGuest() {
    let $spans = $('#sign').children();
    $spans.eq(1).addClass('hidden');
    $spans.eq(0).removeClass('hidden');
    $('#administratorTab').addClass('hidden');
    $('#dispatcherTab').addClass('hidden');
}