let tmpSelectedRow = null;

window.onload = function() {
    localizeAdministrator();
    doc.getElementById("lang").addEventListener("change", changeLocaleEventHandler);
    ajaxPost("main", null, createTableBody, true);
};

function changeLocale(responseText) {
    if ("ok" === responseText) {
        localizeAdministrator();
        clearMainTable();
        ajaxPost("main", null, createTableBody, true);
    }
}

function changeLocaleEventHandler() {
    let body = "locale=" + document.getElementById("lang").value;
    ajax("POST", "locale/change", body, changeLocale, true, "application/x-www-form-urlencoded; charset=UTF-8");
}

function clearMainTable() {
    let tableBody = doc.getElementById("tableBody");
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.lastChild);
    }
}

function buttonEditAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    let inputFlightId = doc.getElementById("flightId");
    let selectedId = tmpSelectedRow.children[0].innerText; // selected flight id
    inputFlightId.setAttribute("value", selectedId);
    doc.getElementById("formEdit").submit();
}

function buttonAddAction() {
    let inputFlightId = doc.getElementById("flightId");
    inputFlightId.setAttribute("value", "0");
    doc.getElementById("formEdit").submit();
}

function buttonDeleteAction() {
    if (tmpSelectedRow == null) {
        return;
    }
    if (!confirm(responseObject["flight.confirm.delete"])) {
        return;
    }

    let selectedFlightArray = tmpSelectedRow.children;
    let flight = {
        "id": Number(selectedFlightArray[0].innerText),
        "flightNumber": Number(selectedFlightArray[1].innerText),
        "startPoint": selectedFlightArray[2].innerText,
        "destinationPoint": selectedFlightArray[3].innerText,
        "departureDate": selectedFlightArray[4].innerText,
        "departureTime": selectedFlightArray[5].innerText,
        "arrivalDate": selectedFlightArray[6].innerText,
        "arrivalTime": selectedFlightArray[7].innerText,
        "plane": selectedFlightArray[8].innerText,
        "crew": {
            "id": Number(selectedFlightArray[9].innerText)
        }
    };
    let body = JSON.stringify(flight);
    ajaxPost("flight/delete", body, deleteFlight, true);
}

function deleteFlight(responseText) {
    if ("ok" === responseText) {
        let tableBody = doc.getElementById("tableBody");
        tableBody.removeChild(tmpSelectedRow);
        tmpSelectedRow = null;
    }
}

function createTableBody(json) {
    let flights = JSON.parse(json);
    let tableBody = doc.getElementById("tableBody");

    for (let i = 0; i < flights.length; i++) {
        let flight = flights[i];

        let row = doc.createElement("TR");
        tableBody.appendChild(row);
        row.addEventListener("click", function() {
            selectTableRow(this);
        }, false);

        let tdId = doc.createElement("TD");
        let tdFlightNumber = doc.createElement("TD");
        let tdStartPoint = doc.createElement("TD");
        let tdDestinationPoint = doc.createElement("TD");
        let tdDepartureDate = doc.createElement("TD");
        let tdDepartureTime = doc.createElement("TD");
        let tdArrivalDate = doc.createElement("TD");
        let tdArrivalTime = doc.createElement("TD");
        let tdPlane = doc.createElement("TD");
        let tdCrewId = doc.createElement("TD");
        let tdCrewName = doc.createElement("TD");
        row.appendChild(tdId);
        row.appendChild(tdFlightNumber);
        row.appendChild(tdStartPoint);
        row.appendChild(tdDestinationPoint);
        row.appendChild(tdDepartureDate);
        row.appendChild(tdDepartureTime);
        row.appendChild(tdArrivalDate);
        row.appendChild(tdArrivalTime);
        row.appendChild(tdPlane);
        row.appendChild(tdCrewId);
        row.appendChild(tdCrewName);

        tdId.innerHTML = flight["id"];
        tdFlightNumber.innerHTML = flight["flightNumber"];
        tdStartPoint.innerHTML = flight["startPoint"];
        tdDestinationPoint.innerHTML = flight["destinationPoint"];
        tdDepartureDate.innerHTML = flight["departureDate"];
        tdDepartureTime.innerHTML = flight["departureTime"];
        tdArrivalDate.innerHTML = flight["arrivalDate"];
        tdArrivalTime.innerHTML = flight["arrivalTime"];
        tdPlane.innerHTML = flight["plane"];
        if (flight.hasOwnProperty("crew")) {
            tdCrewId.innerHTML = flight["crew"]["id"];
            tdCrewName.innerHTML = flight["crew"]["name"];
        } else {
            tdCrewId.innerHTML = "0";
        }
    }
}

function selectTableRow(row) {
    if (tmpSelectedRow != null) {
        tmpSelectedRow.classList.remove("selected");
    }
    row.classList.add("selected");
    tmpSelectedRow = row;
}

function signOut() {
    ajaxPost("signout", null, sighOutAction, true);
}

function sighOutAction() {
    doc.location.href = "./";
}