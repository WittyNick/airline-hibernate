let tmpSelectedCrewRow = null;
let tmpSelectedBaseRow = null;

$(document).ready(function () {
    localizeCrewEdit();
    $('#lang').on('change', changeLocaleEventHandler);
    addEmployeeListSelect();
    addEmployeeBaseSelect();
});

function changeLocaleEventHandler() {
    $.ajax({
        type: 'POST',
        url: 'locale/change',
        data: 'locale=' + $('#lang').val(),
        dataType: "text",
        success: localizeCrewEdit
    });
}

function addEmployeeListSelect() {
    $('#employeeListBody').children().each(function (index, row) {
        $(row).on('click', onEmployeeListRowClick);
    });
}

function onEmployeeListRowClick() {
    if (tmpSelectedCrewRow != null) {
        $(tmpSelectedCrewRow).removeClass('selected');
    }
    $(this).addClass('selected');
    tmpSelectedCrewRow = this;
}

function addEmployeeBaseSelect() {
    $('#employeeBaseBody').children().each(function (index, row) {
        $(row).on('click', onEmployeeBaseRowClick);
    });
}

function onEmployeeBaseRowClick() {
    if (tmpSelectedBaseRow != null) {
        $(tmpSelectedBaseRow).removeClass('selected');
    }
    $(this).addClass('selected');
    tmpSelectedBaseRow = this;
}

function removeFromCrewAction() {
    if (tmpSelectedCrewRow == null) {
        return;
    }
    if (tmpSelectedBaseRow != null) {
        $(tmpSelectedBaseRow).removeClass('selected');
    }
    $(tmpSelectedCrewRow).off('click');
    $(tmpSelectedCrewRow).detach();
    $('#employeeBaseBody').append(tmpSelectedCrewRow);
    $(tmpSelectedCrewRow).on('click', onEmployeeBaseRowClick);
    tmpSelectedBaseRow = tmpSelectedCrewRow;
    tmpSelectedCrewRow = null;
}

function addToCrewAction() {
    if (tmpSelectedBaseRow == null) {
        return;
    }
    if (tmpSelectedCrewRow != null) {
        $(tmpSelectedCrewRow).removeClass('selected');
    }
    $(tmpSelectedBaseRow).off('click');
    $(tmpSelectedBaseRow).detach();
    $('#employeeListBody').append(tmpSelectedBaseRow);
    $(tmpSelectedBaseRow).on('click', onEmployeeListRowClick);
    tmpSelectedCrewRow = tmpSelectedBaseRow;
    tmpSelectedBaseRow = null;
}

function engageEmployeeAction() {
    if (!validateNewEmployee()) {
        return;
    }
    let employee = {
        'id': 0,
        'name': $('#newEmployeeName').val(),
        'surname': $('#newEmployeeSurname').val(),
        'position': $('#newEmployeePosition').val()
    };
    $.ajax({
        type: 'POST',
        url: 'employee/add',
        data: JSON.stringify(employee),
        contentType: 'json',
        dataType: 'json',
        success: onEngageEmployeeCallback
    });
}

function onEngageEmployeeCallback(employee) {
    let $row = $('<tr>').on('click', onEmployeeListRowClick);
    let cells = '<td>' + employee.id +
        '</td><td>' + employee.name +
        '</td><td>' + employee.surname +
        '</td><td>' + employee.position +
        '</td><td>' + dict[employee.position.toLowerCase()] + '</td>';

    $row.html(cells);
    $('#employeeListBody').append($row);
    $row.click();

    $('#newEmployeeName').val('');
    $('#newEmployeeSurname').val('');
}

function fireEmployeeAction() {
    if (tmpSelectedBaseRow == null) {
        return;
    }
    if (!confirm(dict['crew.edit.confirm.fire_employee'])) {
        return;
    }
    let $cells = $(tmpSelectedBaseRow).children();
    let employee = {
        'id': $cells.eq(0).html(),
        'name': $cells.eq(1).html(),
        'surname': $cells.eq(2).html(),
        'position': $cells.eq(3).html()
    };
    $.ajax({
        type: 'POST',
        url: 'employee/delete',
        data: JSON.stringify(employee),
        contentType: 'json',
        success: function () {
            $(tmpSelectedBaseRow).remove();
            tmpSelectedBaseRow = null;
        }
    });
}

function saveAction() {
    if (!validateCrew()) {
        return;
    }
    let bobtailFlight = {
        'id': $('#flightId').val(),
        'crew': {
            'id': $('#crewId').val(),
            'name': $('#name').val(),
            'employees': []
        }
    };
    $('#employeeListBody').children().each(function (index, row) {
        let $cells = $(row).children();
        let employee = {
            'id': $cells.eq(0).html(),
            'name': $cells.eq(1).html(),
            'surname': $cells.eq(2).html(),
            'position': $cells.eq(3).html()
        };
        bobtailFlight.crew.employees.push(employee);
    });
    $.ajax({
        type: 'POST',
        url: 'save',
        data: JSON.stringify(bobtailFlight),
        contentType: 'json',
        success: function () {
            $(location).prop('href', '../dispatcher');
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

let messageCrewNameIndex = 0;
let messageNewEmployeeIndex = 0;

function validateCrew() {
    let isValid = true;
    if ('' === $('#name').val().trim()) {
        messageCrewNameIndex = 1;
        isValid = false;
    } else {
        messageCrewNameIndex = 0;
    }
    showErrorMessages();
    return isValid;
}

function validateNewEmployee() {
    let isValid = true;
    let $newEmployeeName = $('#newEmployeeName');
    let $newEmployeeSurname = $('#newEmployeeSurname');
    if ('' === $newEmployeeName.val().trim() && '' === $newEmployeeSurname.val().trim()) {
        messageNewEmployeeIndex = 4;
        isValid = false;
    } else if ('' === $newEmployeeName.val().trim()) {
        messageNewEmployeeIndex = 2;
        isValid = false;
    } else if ('' === $newEmployeeSurname.val().trim()) {
        messageNewEmployeeIndex = 3;
        isValid = false;
    } else {
        messageNewEmployeeIndex = 0;
    }
    $('#messageNewEmployee').html(messages[messageNewEmployeeIndex]);

    setTimeout(function () {
        messageNewEmployeeIndex = 0;
        $('#messageNewEmployee').html(messages[messageNewEmployeeIndex]);
    }, 2000);
    return isValid;
}

function showErrorMessages() {
    $('#messageName').html(messages[messageCrewNameIndex]);
}