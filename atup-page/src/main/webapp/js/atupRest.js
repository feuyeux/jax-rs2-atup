var storage = window.sessionStorage;

var HOST = "http://localhost:8080/";
var ATUP_CASE_BASE_URI = 'atup-case/rest-api';
var ATUP_DEVICE_BASE_URI = 'atup-device/rest-api';
var ATUP_USER_BASE_URI = 'atup-user/rest-api';
var ATUP_PAGE_BASE_URI = 'atup-page/';

var TEST_SUITE_PATH = "/testsuites";
var TEST_CASE_PATH = "/testcases";
var TEST_RESULT_PATH = "/testresults";
var TEST_JOB_PATH = "/testjobs";

var USER_PATH = "/users";
var DEVICE_PATH = "/devices";
var SIGNIN_PATH = "/users/signin";

var NO_RESULT = "There's no result from the server";

var GET_METHOD = 'GET';
var POST_METHOD = 'POST';
var PUT_METHOD = 'PUT';
var DELETE_METHOD = 'DELETE';

var SPAN_BEGIN = "<span style='width:100px;display:inline-block;'>";
var SPAN_BEGIN1 = "<span style='width:150px;display:inline-block;'>";
var SPAN_BEGIN2 = "<span style='width:200px;display:inline-block;'>";

var ROLE_ADMIN = 1;
var ROLE_JOB_KILLER = 2;
var ROLE_DEVICE_MANAGER = 3;
var ROLE_USER = 4;

function rest(restUrl, httpMethod, param, callback) {
    rest0(restUrl, httpMethod, param, "application/json", "json", callback)
}
var normal_status = 9200;
var LOADING = "Loading...";
function rest0(restUrl, httpMethod, param, contenttype, datatype, callback) {
    var resultLine = jQuery('#resultDiv');
    resultLine.html(LOADING);
    var userId = storage.getItem("userId");
    var request = jQuery.ajax({type: httpMethod, url: restUrl, headers: {'Atup-User': userId}, data: param, contentType: contenttype, dataType: datatype,
        crossDomain: true});
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                resultLine.html(NO_RESULT);
            } else if (data.statusCode && data.statusCode != normal_status) {
                resultLine.html("Error:" + data.errorInfo);
            } else if (callback != null) {
                resultLine.html("");
                callback(data);
            }
        } catch (e) {
            resultLine.html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        resultLine.html(errorThrown + " status=" + textStatus.status + " text=" + textStatus.statusText);
    });
    resultLine.append(" DONE!");

}
function getInfoByQuery() {
    var url = jQuery("#queryUrl").val();
    rest(GET_METHOD, null, null, 'json', renderQueryGet);
}
function getInfoByPath() {
    var url = jQuery("#pathUrl").val();
    rest(GET_METHOD, null, null, 'json', renderPathGet);
}
function renderPut(data) {
    jQuery('#resultDiv').html("id=" + data.bookId);
}
/*DELETE*/
function deleteInfo() {
    var url = jQuery("#delUrl").val();
    rest(DELETE_METHOD, null, null, 'text', renderDelete);
}
function checkSignIn() {
    var storageUserId = storage.getItem("userId");
    if (storageUserId == null) {
        window.location.href = HOST + ATUP_PAGE_BASE_URI + "signIn.html";
    } else {
        var user = storage.getItem("userName");
        var welcomeDiv = jQuery('#topDiv').html("Welcome " + user);
    }
}
function getValue(query, key) {
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var parts = vars[i].split("=");
        if (parts[0] == key) {
            return parts[1];
        }
    }
    return null;
}