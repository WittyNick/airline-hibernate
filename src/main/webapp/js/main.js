/*
Send a POST request to the servlet.
Gets an array of JSON Flights.
Calls the fillTable() function.
*/
window.onload = function() {
    ajaxPost("user/validate", null, setUserPageView, true);
    localizeMain();
    doc.getElementById("lang").addEventListener("change", changeLocaleEventHandler);
    ajaxPost("main", null, fillTableBody, true);
};

function changeLocaleEventHandler() {
    let body = "locale=" + document.getElementById("lang").value;
    ajax("POST", "locale/change", body, changeLocale, true, "application/x-www-form-urlencoded; charset=UTF-8");
}

function changeLocale(responseText) {
    if ("ok" === responseText) {
        localizeMain();
        clearMainTable();
        ajaxPost("main", null, fillTableBody, true);
    }
}

function clearMainTable() {
    let tableBody = doc.getElementById("tableBody");
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.lastChild);
    }
}

function fillTableBody(json) {
    let flights = JSON.parse(json);
    let tableBody = doc.getElementById("tableBody");

    for (let i = 0; i < flights.length; i++) {
        let flight = flights[i];

        let row = doc.createElement("TR");
        tableBody.appendChild(row);

        let tdFlightNumber = doc.createElement("TD");
        let tdStartPoint = doc.createElement("TD");
        let tdDestinationPoint = doc.createElement("TD");
        let tdDepartureDate = doc.createElement("TD");
        let tdDepartureTime = doc.createElement("TD");
        let tdArrivalDate = doc.createElement("TD");
        let tdArrivalTime = doc.createElement("TD");
        let tdPlane = doc.createElement("TD");
        row.appendChild(tdFlightNumber);
        row.appendChild(tdStartPoint);
        row.appendChild(tdDestinationPoint);
        row.appendChild(tdDepartureDate);
        row.appendChild(tdDepartureTime);
        row.appendChild(tdArrivalDate);
        row.appendChild(tdArrivalTime);
        row.appendChild(tdPlane);

        tdFlightNumber.innerHTML = flight["flightNumber"];
        tdStartPoint.innerHTML = flight["startPoint"];
        tdDestinationPoint.innerHTML = flight["destinationPoint"];
        tdDepartureDate.innerHTML = flight["departureDate"];
        tdDepartureTime.innerHTML = flight["departureTime"];
        tdArrivalDate.innerHTML = flight["arrivalDate"];
        tdArrivalTime.innerHTML = flight["arrivalTime"];
        tdPlane.innerHTML = flight["plane"];
    }
}

function setUserPageView(responseText) {
    let sign = doc.getElementById("sign");
    let administratorTab = doc.getElementById("administratorTab");
    let dispatcherTab = doc.getElementById("dispatcherTab");
    if ("guest" === responseText) {
        sign.children[1].classList.add("hidden");
        administratorTab.classList.add("hidden");
        dispatcherTab.classList.add("hidden");
    } else if ("administrator" === responseText) {
        sign.children[0].classList.add("hidden");
        dispatcherTab.classList.add("hidden");
    } else if ("dispatcher" === responseText) {
        sign.children[0].classList.add("hidden");
        administratorTab.classList.add("hidden");
    }
}

function signOut() {
    ajaxPost("signout", null, signOutAction, true);
}

function signOutAction() {
    doc.getElementById("sign").children[0].classList.remove("hidden");
    ajaxPost("user/validate", null, setUserPageView, true);
}