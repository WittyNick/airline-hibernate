let tmpSelectedRow = null;

$(document).ready(function () {
    localizeDispatcher();
    $('#lang').on('change', changeLocaleEventHandler);
    fillTableFlights();
});

function fillTableFlights() {
    $.ajax({
        type: 'POST',
        url: 'main',
        contentType: false,
        dataType: 'json',
        success: createTableBody
    });
}

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $('#lang').val(),
        dataType: "text",
        success: function () {
            localizeDispatcher();
            $('#tableBody').empty();
            fillTableFlights();
        }
    });
}

function buttonEditAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    let flightId = $(tmpSelectedRow).children().eq(0).html();
    let crewId = $(tmpSelectedRow).children().eq(9).html();
    $('#flightId').attr('value', flightId);
    $('#crewId').attr('value', crewId);
    $('#formEdit').submit();
}

function buttonDeleteCrewAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    let $selectedFlightCells = $(tmpSelectedRow).children();
    let crewId = +$selectedFlightCells.eq(9).html();
    if (crewId !== 0 && !confirm(dict['crew.confirm.delete'])) {
        return;
    }
    let flight = {
        'id': +$selectedFlightCells.eq(0).html(),
        'flightNumber': +$selectedFlightCells.eq(1).html(),
        'startPoint': $selectedFlightCells.eq(2).html(),
        'destinationPoint': $selectedFlightCells.eq(3).html(),
        'departureDate': $selectedFlightCells.eq(4).html(),
        'departureTime': $selectedFlightCells.eq(5).html(),
        'arrivalDate': $selectedFlightCells.eq(6).html(),
        'arrivalTime': $selectedFlightCells.eq(7).html(),
        'plane': $selectedFlightCells.eq(8).html(),
        'crew': {
            'id': crewId
        }
    };
    $.ajax({
        type: 'POST',
        url: 'crew/delete',
        data: JSON.stringify(flight),
        contentType: 'json',
        success: function () {
            let $selectedFlightCells = $(tmpSelectedRow).children();
            $selectedFlightCells.eq(9).html('0');
            $selectedFlightCells.eq(10).html('');
        }
    });
}

function createTableBody(flights) {
    flights.forEach(function (flight) {
        let $row = $('<tr>').on('click', function () {
            selectTableRow(this);
        });
        let cells = '<td>' + flight.id +
            '</td><td>' + flight.flightNumber +
            '</td><td>' + flight.startPoint +
            '</td><td>' + flight.destinationPoint +
            '</td><td>' + flight.departureDate +
            '</td><td>' + flight.departureTime +
            '</td><td>' + flight.arrivalDate +
            '</td><td>' + flight.arrivalTime +
            '</td><td>' + flight.plane;
        if ('crew' in flight) {
            cells += '</td><td>' + flight.crew.id +
                '</td><td>' + flight.crew.name + '</td>';
        } else {
            cells += '</td><td>0</td><td></td>';
        }
        $row.html(cells);
        $('#tableBody').append($row);
    });
}

function selectTableRow(row) {
    if (tmpSelectedRow != null) {
        $(tmpSelectedRow).removeClass('selected');
    }
    $(row).addClass('selected');
    tmpSelectedRow = row;
}

function signOut() {
    $.ajax({
        type: 'POST',
        url: 'signout',
        contentType: false,
        success: function () {
            $(location).prop('href', 'main');
        }
    });
}