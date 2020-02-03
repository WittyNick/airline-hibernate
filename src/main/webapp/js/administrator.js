let tmpSelectedRow = null;

$(document).ready(function () {
    localizeAdministrator();
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
            localizeAdministrator();
            $('#tableBody').empty();
            fillTableFlights();
        }
    });
}

function buttonEditAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    let selectedFlightId = $(tmpSelectedRow).children().eq(0).html();
    $('#flightId').attr('value', selectedFlightId);
    $('#formEdit').submit();
}

function buttonAddAction() {
    $('#flightId').attr('value', '0');
    $('#formEdit').submit();
}

function buttonDeleteAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    if (!confirm(dict['flight.confirm.delete'])) {
        return;
    }
    let $selectedFlightCells = $(tmpSelectedRow).children();
    let flight = {
        'id': +$selectedFlightCells.eq(0).html(), // + converts string to int or float, better than use Number(...)
        'flightNumber': +$selectedFlightCells.eq(1).html(),
        'startPoint': $selectedFlightCells.eq(2).html(),
        'destinationPoint': $selectedFlightCells.eq(3).html(),
        'departureDate': $selectedFlightCells.eq(4).html(),
        'departureTime': $selectedFlightCells.eq(5).html(),
        'arrivalDate': $selectedFlightCells.eq(6).html(),
        'arrivalTime': $selectedFlightCells.eq(7).html(),
        'plane': $selectedFlightCells.eq(8).html(),
        'crew': {
            'id': +$selectedFlightCells.eq(9).html()
        }
    };
    $.ajax({
        type: 'POST',
        url: 'flight/delete',
        data: JSON.stringify(flight),
        contentType: 'json',
        success: function () {
            $(tmpSelectedRow).remove();
            tmpSelectedRow = null;
        }
    });
}

function createTableBody(flights) {
    $(flights).each(function (index, flight) {
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
            $(location).attr('href', 'main');
        }
    });
}