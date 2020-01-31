let tmpSelectedRow = null;

$(document).ready(function() {
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

function changeLocale(responseText) {
    if ('ok' === responseText) {
        localizeAdministrator();
        $('#tableBody').empty();
        fillTableFlights();
    }
}

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $('#lang').val(),
        dataType: "text",
        success: changeLocale
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
    let selectedFlightArray = $(tmpSelectedRow).children();
    let flight = {
        'id': +$(selectedFlightArray).eq(0).html(), // + converts string to int or float, better than use Number(...)
        'flightNumber': +$(selectedFlightArray).eq(1).html(),
        'startPoint': $(selectedFlightArray).eq(2).html(),
        'destinationPoint': $(selectedFlightArray).eq(3).html(),
        'departureDate': $(selectedFlightArray).eq(4).html(),
        'departureTime': $(selectedFlightArray).eq(5).html(),
        'arrivalDate': $(selectedFlightArray).eq(6).html(),
        'arrivalTime': $(selectedFlightArray).eq(7).html(),
        'plane': $(selectedFlightArray).eq(8).html(),
        'crew': {
            'id': +$(selectedFlightArray).eq(9).html()
        }
    };
    $.ajax({
        type: 'POST',
        url: 'flight/delete',
        data: JSON.stringify(flight),
        contentType: 'json',
        dataType: 'text',
        success: deleteFlight
    });
}

function deleteFlight(responseText) {
    if ('ok' === responseText) {
        $(tmpSelectedRow).remove();
        tmpSelectedRow = null;
    }
}

function createTableBody(flights) {
    let tableBody = $('#tableBody');

    flights.forEach(function(flight) {
        let row = $.create('tr').on('click', function() {
            selectTableRow(this);
        });
        $(tableBody).append(row);

        let columns = '<td>' + flight.id +
            '</td><td>' + flight.flightNumber +
            '</td><td>' + flight.startPoint +
            '</td><td>' + flight.destinationPoint +
            '</td><td>' + flight.departureDate +
            '</td><td>' + flight.departureTime +
            '</td><td>' + flight.arrivalDate +
            '</td><td>' + flight.arrivalTime +
            '</td><td>' + flight.plane;

        if (flight.hasOwnProperty('crew')) {
            tdCrewId.innerHTML = flight['crew']['id'];
            tdCrewName.innerHTML = flight['crew']['name'];
        } else {
            tdCrewId.innerHTML = '0';
        }

    });
}

// function createTableBody(flights) {
//     let tableBody = doc.getElementById('tableBody');
//
//     for (let i = 0; i < flights.length; i++) {
//         let flight = flights[i];
//
//         let row = doc.createElement('TR');
//         tableBody.appendChild(row);
//         row.addEventListener('click', function() {
//             selectTableRow(this);
//         }, false);
//
//         let tdId = doc.createElement('TD');
//         let tdFlightNumber = doc.createElement('TD');
//         let tdStartPoint = doc.createElement('TD');
//         let tdDestinationPoint = doc.createElement('TD');
//         let tdDepartureDate = doc.createElement('TD');
//         let tdDepartureTime = doc.createElement('TD');
//         let tdArrivalDate = doc.createElement('TD');
//         let tdArrivalTime = doc.createElement('TD');
//         let tdPlane = doc.createElement('TD');
//         let tdCrewId = doc.createElement('TD');
//         let tdCrewName = doc.createElement('TD');
//         row.appendChild(tdId);
//         row.appendChild(tdFlightNumber);
//         row.appendChild(tdStartPoint);
//         row.appendChild(tdDestinationPoint);
//         row.appendChild(tdDepartureDate);
//         row.appendChild(tdDepartureTime);
//         row.appendChild(tdArrivalDate);
//         row.appendChild(tdArrivalTime);
//         row.appendChild(tdPlane);
//         row.appendChild(tdCrewId);
//         row.appendChild(tdCrewName);
//
//         tdId.innerHTML = flight['id'];
//         tdFlightNumber.innerHTML = flight['flightNumber'];
//         tdStartPoint.innerHTML = flight['startPoint'];
//         tdDestinationPoint.innerHTML = flight['destinationPoint'];
//         tdDepartureDate.innerHTML = flight['departureDate'];
//         tdDepartureTime.innerHTML = flight['departureTime'];
//         tdArrivalDate.innerHTML = flight['arrivalDate'];
//         tdArrivalTime.innerHTML = flight['arrivalTime'];
//         tdPlane.innerHTML = flight['plane'];
//         if (flight.hasOwnProperty('crew')) {
//             tdCrewId.innerHTML = flight['crew']['id'];
//             tdCrewName.innerHTML = flight['crew']['name'];
//         } else {
//             tdCrewId.innerHTML = '0';
//         }
//     }
// }

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
        success: function() {
            $(location).attr('href', 'main');
        }
    });
}