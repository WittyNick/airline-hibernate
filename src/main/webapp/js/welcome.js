let doc = document;

/*
Send a POST request to the servlet.
Gets an array of JSON Flights.
Calls the fillTable() function.
*/
window.onload = function() {
    setUserPageView();
    localizeWelcome();
    doc.getElementById("lang").addEventListener("change", function() {
        let body = "locale=" + document.getElementById("lang").value;
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "locale/change", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                if ("ok" === xhr.responseText) {
                    localizeWelcome();
                    clearMainTable();
                    fillMainTable();
                }
            }
        };
        xhr.send(body);
    });
    fillMainTable();
};

function clearMainTable() {
    let tableBody = doc.getElementById("tableBody");
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.lastChild);
    }
}

function fillMainTable() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "welcome", true);
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let flights = JSON.parse(this.responseText);
            fillTableBody(flights);
        }
    };
    xhr.send();
}

function fillTableBody(flights) {
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

function setUserPageView() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "user/validate", true);
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let sign = doc.getElementById("sign");
            let administratorTab = doc.getElementById("administratorTab");
            let dispatcherTab = doc.getElementById("dispatcherTab");
            if ("guest" === xhr.responseText) {
                sign.children[1].classList.add("hidden");
                administratorTab.classList.add("hidden");
                dispatcherTab.classList.add("hidden");
            } else if ("administrator" === xhr.responseText) {
                sign.children[0].classList.add("hidden");
                dispatcherTab.classList.add("hidden");
            } else if ("dispatcher" === xhr.responseText) {
                sign.children[0].classList.add("hidden");
                administratorTab.classList.add("hidden");
            }
        }
    };
    xhr.send();
}

function signOut() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "signout", true);
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            doc.getElementById("sign").children[0].classList.remove("hidden");
            setUserPageView();
        }
    };
    xhr.send();
}