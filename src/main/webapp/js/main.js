$(document).ready(function() {
    validateUser();
    localizeMain();
    $("#lang").on("change", changeLocaleEventHandler);
    fillTableFlights();
});

function validateUser() {
    $.ajax({
        type: "POST",
        url: "user/validate",
        contentType: false,
        dataType: "text",
        success: setPageView
    });
}

function fillTableFlights() {
    $.ajax({
        type: "POST",
        url: "main",
        contentType: false,
        dataType: "json",
        success: fillTableFlightsHandler
    });
}

function changeLocaleEventHandler() {
    $.ajax({
        type: "POST",
        url: "locale/change",
        data: "locale=" + $("#lang").val(),
        dataType: "text",
        success: changeLocale
    });
}

function changeLocale(responseText) {
    if ("ok" === responseText) {
        localizeMain();
        $("#tableBody").empty();
        fillTableFlights();
    }
}

function fillTableFlightsHandler(flights) {
    let tableBody = document.getElementById("tableBody");

    for (let i = 0; i < flights.length; i++) {
        let flight = flights[i];

        let row = document.createElement("TR");
        tableBody.appendChild(row);

        let tdFlightNumber = document.createElement("TD");
        let tdStartPoint = document.createElement("TD");
        let tdDestinationPoint = document.createElement("TD");
        let tdDepartureDate = document.createElement("TD");
        let tdDepartureTime = document.createElement("TD");
        let tdArrivalDate = document.createElement("TD");
        let tdArrivalTime = document.createElement("TD");
        let tdPlane = document.createElement("TD");
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

function setPageView(responseText) {
    let sign = doc.getElementById("sign");
    let administratorTab = doc.getElementById("administratorTab");
    let dispatcherTab = doc.getElementById("dispatcherTab");

    if ("guest" === responseText) {
        sign.children[0].classList.remove("hidden");
    } else if ("administrator" === responseText) {
        sign.children[1].classList.remove("hidden");
        administratorTab.classList.remove("hidden");
    } else if ("dispatcher" === responseText) {
        sign.children[1].classList.remove("hidden");
        dispatcherTab.classList.remove("hidden");
    }
}

function signOut() {
    $.ajax({
        type: "POST",
        url: "signout",
        contentType: false,
        success: signOutHandler
    });
    // ajaxPost("signout", null, signOutHandler, true);
}

function signOutHandler() {
    resetPageView();
    // validateUser();

    // ajaxPost("user/validate", null, setPageView, true);
}

function resetPageView() {
    let sign = $("#sign");
    $(sign).children().addClass("hidden");
    $(sign).children([0]).removeClass("hidden");
    $("#administratorTab").addClass("hidden");
    $("#dispatcherTab").addClass("hidden");
}