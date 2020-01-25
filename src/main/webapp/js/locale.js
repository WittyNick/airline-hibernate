let responseObject;
let messages;

// ---------- main.jsp ----------
function localizeMain() {
    let requestArray = [
        "lang",
        "main",
        "administrator",
        "dispatcher",
        "sign_in",
        "sign_out",
        "schedule",
        "number",
        "from",
        "to",
        "departure_date",
        "departure_time",
        "arrival_date",
        "arrival_time",
        "plane"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyLocaleMain, true);
}

function applyLocaleMain(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("mainTab").innerHTML = responseObject["main"];
    doc.getElementById("administratorTab").firstElementChild.innerText = responseObject["administrator"];
    doc.getElementById("dispatcherTab").firstElementChild.innerText = responseObject["dispatcher"];
    doc.getElementById("sign").children[0].innerText = responseObject["sign_in"];
    doc.getElementById("sign").children[1].innerText = responseObject["sign_out"];
    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("tableCaption").innerText = responseObject["schedule"];
    let headColumns = doc.getElementById("hatRow").children;
    headColumns[0].innerHTML = responseObject["number"];
    headColumns[1].innerHTML = responseObject["from"];
    headColumns[2].innerHTML = responseObject["to"];
    headColumns[3].innerHTML = responseObject["departure_date"];
    headColumns[4].innerHTML = responseObject["departure_time"];
    headColumns[5].innerHTML = responseObject["arrival_date"];
    headColumns[6].innerHTML = responseObject["arrival_time"];
    headColumns[7].innerHTML = responseObject["plane"];
}

// ---------- sign_in.jsp ----------
function localizeSignIn() {
    let requestArray = [
        "lang",
        "legend_sign_in",
        "login",
        "password",
        "enter",
        "cancel",
        "message.sign_in.login",
        "message.sign_in.password",
        "message.sign_in.fail"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyLocaleSignIn, true);
}

function applyLocaleSignIn(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("legendFieldset").innerText = responseObject["legend_sign_in"];
    doc.getElementById("labelLogin").innerText = responseObject["login"];
    doc.getElementById("labelPassword").innerText = responseObject["password"];
    doc.getElementById("buttonSubmit").value = responseObject["enter"];
    doc.getElementById("buttonCancel").value = responseObject["cancel"];

    messages = [
        "",
        responseObject["message.sign_in.login"],
        responseObject["message.sign_in.password"],
        responseObject["message.sign_in.fail"]
    ];
    setMessages();
}

// ---------- administrator.jsp ----------
function localizeAdministrator() {
    let requestArray = [
        "lang",
        "main",
        "administrator",
        "sign_out",
        "flights",
        "number",
        "from",
        "to",
        "departure_date",
        "departure_time",
        "arrival_date",
        "arrival_time",
        "plane",
        "crew",
        "flight.edit",
        "flight.add",
        "flight.delete",
        "flight.confirm.delete"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyLocaleAdministrator, true);
}

function applyLocaleAdministrator(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("mainTab").firstElementChild.innerHTML = responseObject["main"];
    doc.getElementById("administratorTab").innerText = responseObject["administrator"];
    doc.getElementById("sign").firstElementChild.innerText = responseObject["sign_out"];
    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("tableCaption").innerText = responseObject["flights"];
    let headColumns = document.getElementById("hatRow").children;
    headColumns[1].innerHTML = responseObject["number"];
    headColumns[2].innerHTML = responseObject["from"];
    headColumns[3].innerHTML = responseObject["to"];
    headColumns[4].innerHTML = responseObject["departure_date"];
    headColumns[5].innerHTML = responseObject["departure_time"];
    headColumns[6].innerHTML = responseObject["arrival_date"];
    headColumns[7].innerHTML = responseObject["arrival_time"];
    headColumns[8].innerHTML = responseObject["plane"];
    headColumns[10].innerHTML = responseObject["crew"];
    doc.getElementById("buttonEdit").value = responseObject["flight.edit"];
    doc.getElementById("buttonAdd").value = responseObject["flight.add"];
    doc.getElementById("buttonDelete").value = responseObject["flight.delete"];
}

// ---------- FlightEditServlet ----------
function localizeFlightEdit() {
    let requestArray = [
        "lang",
        "main",
        "administrator",
        "sign_out",
        "flight.edit.number",
        "flight.edit.from",
        "flight.edit.to",
        "flight.edit.departure_date",
        "flight.edit.departure_time",
        "flight.edit.arrival_date",
        "flight.edit.arrival_time",
        "flight.edit.plane",
        "flight.edit.save",
        "flight.edit.cancel",
        "message.flight.edit.fill_field",
        "message.flight.edit.illegal_value",
        "message.flight.edit.fill_date_time"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyFlightEdit, true);
}

function applyFlightEdit(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("mainTab").firstElementChild.innerHTML = responseObject["main"];
    doc.getElementById("administratorTab").innerText = responseObject["administrator"];
    doc.getElementById("sign").firstElementChild.innerText = responseObject["sign_out"];
    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("labelFlightNumber").innerText = responseObject["flight.edit.number"];
    doc.getElementById("labelStartPoint").innerText = responseObject["flight.edit.from"];
    doc.getElementById("labelDestinationPoint").innerText = responseObject["flight.edit.to"];
    doc.getElementById("labelDepartureDate").innerText = responseObject["flight.edit.departure_date"];
    doc.getElementById("labelDepartureTime").innerText = responseObject["flight.edit.departure_time"];
    doc.getElementById("labelArrivalDate").innerText = responseObject["flight.edit.arrival_date"];
    doc.getElementById("labelArrivalTime").innerText = responseObject["flight.edit.arrival_time"];
    doc.getElementById("labelPlane").innerText = responseObject["flight.edit.plane"];
    doc.getElementById("buttonSave").value = responseObject["flight.edit.save"];
    doc.getElementById("buttonCancel").value = responseObject["flight.edit.cancel"];

    messages = [
        "",
        responseObject["message.flight.edit.fill_field"],
        responseObject["message.flight.edit.illegal_value"],
        responseObject["message.flight.edit.fill_date_time"]
    ];
    setMessages();
}

// ---------- dispatcher.jsp ----------
function localizeDispatcher() {
    let requestArray = [
        "lang",
        "main",
        "dispatcher",
        "sign_out",
        "crews",
        "number",
        "from",
        "to",
        "departure_date",
        "departure_time",
        "arrival_date",
        "arrival_time",
        "plane",
        "crew",
        "crew.edit",
        "crew.delete",
        "crew.confirm.delete"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyLocaleDispatcher, true);
}

function applyLocaleDispatcher(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("mainTab").firstElementChild.innerHTML = responseObject["main"];
    doc.getElementById("dispatcherTab").innerText = responseObject["dispatcher"];
    doc.getElementById("sign").firstElementChild.innerText = responseObject["sign_out"];
    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("tableCaption").innerText = responseObject["crews"];
    let headColumns = document.getElementById("hatRow").children;
    headColumns[1].innerHTML = responseObject["number"];
    headColumns[2].innerHTML = responseObject["from"];
    headColumns[3].innerHTML = responseObject["to"];
    headColumns[4].innerHTML = responseObject["departure_date"];
    headColumns[5].innerHTML = responseObject["departure_time"];
    headColumns[6].innerHTML = responseObject["arrival_date"];
    headColumns[7].innerHTML = responseObject["arrival_time"];
    headColumns[8].innerHTML = responseObject["plane"];
    headColumns[10].innerHTML = responseObject["crew"];
    doc.getElementById("buttonEdit").value = responseObject["crew.edit"];
    doc.getElementById("buttonDelete").value = responseObject["crew.delete"];
}

// ---------- CrewEditServlet ----------
function localizeCrewEdit() {
    let requestArray = [
        "lang",
        "main",
        "dispatcher",
        "sign_out",
        "crew.edit.crew_name",
        "crew.edit.employee_list",
        "crew.edit.employee_base",
        "name",
        "surname",
        "position",
        "pilot",
        "navigator",
        "communicator",
        "stewardess",
        "crew.edit.name",
        "crew.edit.surname",
        "crew.edit.position",
        "crew.edit.remove_from_crew",
        "crew.edit.engage_employee",
        "crew.edit.add_to_crew",
        "crew.edit.fire_employee",
        "crew.edit.save",
        "crew.edit.cancel",
        "crew.edit.confirm.fire_employee",
        "message.crew.edit.enter_crew_name",
        "message.crew.edit.enter_employee_name",
        "message.crew.edit.enter_employee_surname",
        "message.crew.edit.enter_employee_name_and_surname"
    ];
    ajaxPost("locale", JSON.stringify(requestArray), applyCrewEdit, true);
}

function applyCrewEdit(responseText) {
    responseObject = JSON.parse(responseText);
    selectActualLangOption();

    doc.getElementById("mainTab").firstElementChild.innerHTML = responseObject["main"];
    doc.getElementById("dispatcherTab").innerText = responseObject["dispatcher"];
    doc.getElementById("sign").firstElementChild.innerText = responseObject["sign_out"];
    doc.getElementById("content").lang = responseObject["lang"];
    doc.getElementById("labelName").innerText = responseObject["crew.edit.crew_name"];
    doc.getElementById("captionEmployeeList").innerText = responseObject["crew.edit.employee_list"];
    doc.getElementById("captionEmployeeBase").innerText = responseObject["crew.edit.employee_base"];
    let headEmployeeListColumns = doc.getElementById("hatEmployeeListRow").children;
    headEmployeeListColumns[1].innerHTML = responseObject["name"];
    headEmployeeListColumns[2].innerHTML = responseObject["surname"];
    headEmployeeListColumns[4].innerHTML = responseObject["position"];
    let headEmployeeBaseColumns = doc.getElementById("hatEmployeeBaseRow").children;
    headEmployeeBaseColumns[1].innerHTML = responseObject["name"];
    headEmployeeBaseColumns[2].innerHTML = responseObject["surname"];
    headEmployeeBaseColumns[4].innerHTML = responseObject["position"];
    doc.getElementById("labelNewEmployeeName").innerText = responseObject["crew.edit.name"];
    doc.getElementById("labelNewEmployeeSurname").innerText = responseObject["crew.edit.surname"];
    doc.getElementById("labelNewEmployeePosition").innerText = responseObject["crew.edit.position"];
    doc.getElementById("buttonRemoveFromCrew").value = responseObject["crew.edit.remove_from_crew"];
    doc.getElementById("buttonEngageEmployee").value = responseObject["crew.edit.engage_employee"];
    doc.getElementById("buttonAddToCrew").value = responseObject["crew.edit.add_to_crew"];
    doc.getElementById("buttonFireEmployee").value = responseObject["crew.edit.fire_employee"];
    doc.getElementById("buttonSave").value = responseObject["crew.edit.save"];
    doc.getElementById("buttonCancel").value = responseObject["crew.edit.cancel"];
    let employeeListRows = doc.getElementById("employeeListBody").children;
    for (let i = 0; i < employeeListRows.length; i++) {
        employeeListRows[i].children[4].innerText = responseObject[employeeListRows[i].children[3].innerText.toLowerCase()];
    }
    let employeeBaseRows = doc.getElementById("employeeBaseBody").children;
    for (let j = 0; j < employeeBaseRows.length; j++) {
        employeeBaseRows[j].children[4].innerText = responseObject[employeeBaseRows[j].children[3].innerText.toLowerCase()];
    }
    let positionSelectOptions = doc.getElementById("newEmployeePosition").children;
    for (let k = 0; k < positionSelectOptions.length; k++) {
        let option = positionSelectOptions[k];
        option.innerHTML = responseObject[positionSelectOptions[k].value.toLowerCase()];
    }

    messages = [
        "",
        responseObject["message.crew.edit.enter_crew_name"],
        responseObject["message.crew.edit.enter_employee_name"],
        responseObject["message.crew.edit.enter_employee_surname"],
        responseObject["message.crew.edit.enter_employee_name_and_surname"]
    ];
    setMessages();
}

// --------------------

function selectActualLangOption() {
    let lang = doc.getElementById("lang");
    if ("default" === lang.value) {
        let langOptions = lang.children;
        for (let i = 1; i < langOptions.length; i++) {
            if (langOptions[i].value === responseObject["locale"]) {
                langOptions[i].selected = true;
                return;
            }
        }
        langOptions[0].selected = true;
    }
}