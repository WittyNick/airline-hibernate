let doc = document;

function ajax(method, url, requestBody, callback, async, contentType) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, async);
    if (contentType != null && contentType !== "") {
        xhr.setRequestHeader("Content-Type", contentType);
    }
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            callback(xhr.responseText);
        }
    };
    xhr.send(requestBody);
}

function ajaxGet(url, callback, async) {
    ajax("GET", url, null, callback, async, null);
}

function ajaxPost(url, requestBody, callback, async) {
    let contentType = "application/json; charset=UTF-8";
    ajax("POST", url, requestBody, callback, async, contentType);
}