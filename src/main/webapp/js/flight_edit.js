window.onload = function() {
    localizeFlightEdit();
    doc.getElementById("lang").addEventListener("change", changeLocaleEventHandler);
};

function changeLocaleEventHandler() {
    let body = "locale=" + document.getElementById("lang").value;
    ajax("POST", "locale/change", body, changeLocale, true, "application/x-www-form-urlencoded; charset=UTF-8");
}

function changeLocale(responseText) {
    if ("ok" === responseText) {
        localizeFlightEdit();
    }
}

function buttonSaveAction() {
    if (!isValid()) {
        return;
    }
    let flight = {
        "id": Number(doc.getElementById("id").value),
        "flightNumber": Number(doc.getElementById("flightNumber").value),
        "startPoint": doc.getElementById("startPoint").value,
        "destinationPoint": doc.getElementById("destinationPoint").value,
        "departureDate": doc.getElementById("departureDate").value,
        "departureTime": doc.getElementById("departureTime").value,
        "arrivalDate": doc.getElementById("arrivalDate").value,
        "arrivalTime": doc.getElementById("arrivalTime").value,
        "plane": doc.getElementById("plane").value,
        "crew": {
            "id": Number(doc.getElementById("crewId").value)
        }
    };
    let json = JSON.stringify(flight);
    ajaxPost("../administrator/save", json, onSaveAction, true);
}

function onSaveAction(responseText) {
    if (responseText === "ok") {
        doc.location.href = "../administrator";
    }
}

function signOut() {
    ajaxPost("../signout", null, sighOutAction, true);
}

function sighOutAction() {
    doc.location.href = "../";
}

let messageFlightNumberIndex = 0;
let messageStartPointIndex = 0;
let messageDestinationPointIndex = 0;
let messageDepartureDateTimeIndex = 0;
let messageArrivalDateTimeIndex = 0;
let messagePlaneIndex = 0;

function isValid() {
    let valid = true;
    let inputFlightNumber = doc.getElementById("flightNumber");
    let inputStartPoint = doc.getElementById("startPoint");
    let inputDestinationPoint = doc.getElementById("destinationPoint");
    let inputDepartureDate = doc.getElementById("departureDate");
    let inputDepartureTime = doc.getElementById("departureTime");
    let inputArrivalDate = doc.getElementById("arrivalDate");
    let inputArrivalTime = doc.getElementById("arrivalTime");
    let inputPlane = doc.getElementById("plane");

    if (!inputFlightNumber.value.trim()) {
        messageFlightNumberIndex = 1;
        valid = false;
    } else {
        if (/[0-9]/.test(inputFlightNumber.value)) {
            messageFlightNumberIndex = 0;
        } else {
            messageFlightNumberIndex = 2;
            valid = false;
        }
    }
    if (!inputStartPoint.value.trim()) {
        messageStartPointIndex = 1;
        valid = false;
    } else {
        messageStartPointIndex = 0;
    }
    if (!inputDestinationPoint.value.trim()) {
        messageDestinationPointIndex = 1;
        valid = false;
    } else {
        messageDestinationPointIndex = 0;
    }
    if (!inputDepartureDate.value || !inputDepartureTime.value) {
        messageDepartureDateTimeIndex = 3;
        valid = false;
    } else {
        messageDepartureDateTimeIndex = 0;
    }
    if (!inputArrivalDate.value || !inputArrivalTime.value) {
        messageArrivalDateTimeIndex = 3;
        valid = false;
    } else {
        messageArrivalDateTimeIndex = 0;
    }
    if (!inputPlane.value) {
        messagePlaneIndex = 1;
        valid = false;
    } else {
        messagePlaneIndex = 0;
    }
    setMessages();
    return valid;
}

function setMessages() {
    doc.getElementById("messageFlightNumber").innerHTML = messages[messageFlightNumberIndex];
    doc.getElementById("messageStartPoint").innerHTML = messages[messageStartPointIndex];
    doc.getElementById("messageDestinationPoint").innerHTML = messages[messageDestinationPointIndex];
    doc.getElementById("messageDepartureDateTime").innerHTML = messages[messageDepartureDateTimeIndex];
    doc.getElementById("messageArrivalDateTime").innerHTML = messages[messageArrivalDateTimeIndex];
    doc.getElementById("messagePlane").innerHTML = messages[messagePlaneIndex];
}