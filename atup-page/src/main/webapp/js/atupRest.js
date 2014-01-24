var storage = window.sessionStorage;

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
var SPAN_BEGIN2 = "<span style='width:200px;display:inline-block;'>";
var SPAN_BEGIN4 = "<span style='width:400px;display:inline-block;'>";

var ROLE_ADMIN = 1;
var ROLE_JOB_KILLER = 2;
var ROLE_DEVICE_MANAGER = 3;
var ROLE_USER = 4;

var DEVICE_STATUS_IDLE = "0";
var DEVICE_STATUS_BUSY = "1";
var DEVICE_STATUS_ERROR = "2";

var NORMAL_STATUS = 9200;
var LOADING = "Loading...";
function restGet(restUrl, httpMethod, callback) {
    rest(restUrl, httpMethod, "", "application/json", "json", callback);
}
function restSet(restUrl, httpMethod, entity, callback) {
    rest(restUrl, httpMethod, entity, "application/json", "json", callback);
}
function rest(restUrl, httpMethod, entity, contentType, dataType, callback) {
    var resultLine = jQuery('#resultDiv');
    resultLine.html(LOADING);
    var userId = storage.getItem("userId");
    var userRole = storage.getItem("userRole");
    var request = jQuery.ajax({type: httpMethod, url: restUrl, headers: {'Atup-User': userId, 'Atup-UserRole': userRole}, data: entity, contentType: contentType, dataType: dataType,
        crossDomain: true});
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                resultLine.html(NO_RESULT);
            } else if (data.statusCode && data.statusCode != NORMAL_STATUS) {
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

function checkSignIn() {
    var storageUserId = storage.getItem("userId");
    if (storageUserId == null) {
        window.location.href = HOST + ATUP_PAGE_BASE_URI + "signIn.html";
    } else {
        var user = storage.getItem("userName");
        jQuery('#topDiv').html("Welcome " + user);
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