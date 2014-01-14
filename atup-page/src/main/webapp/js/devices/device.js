var query = window.location.search.substring(1);

function initial() {
    checkSignIn();
    if (query) {
        jQuery("#deviceIp").val(getValue(query, "deviceIp"));
        jQuery("#deviceName").val(getValue(query, "deviceName"));
        jQuery("#deviceType").val(getValue(query, "deviceType"));
        jQuery("#deviceStatus").val(getValue(query, "deviceStatus"));
    }
    restGet(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, renderUserList);
}

function renderUserList(data) {
    var list = data.userList;
    var html = "";
    var userId;
    if (query) {
        userId = getValue(query, "userId");
    }
    jQuery.each(list, function (i, user) {
        if (user.userId == userId) {
            html += "<option selected='selected' value='";
        } else {
            html += "<option value='";
        }
        html += user.userId + "'>";
        html += user.userName + "</option>";
    });
    jQuery("#userList").empty().append(html);
}

function createDevice() {
    var deviceIp = $("#deviceIp0").val();
    var deviceName = $("#deviceName0").val();
    var deviceType = $("#deviceType0").val();
    var postData = JSON.stringify({deviceHost: deviceIp, deviceName: deviceName, deviceType: deviceType, deviceStatus: '1'});
    restSet(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, POST_METHOD, postData, renderEdit);
}
function updateDevice() {
    var deviceIp = $("#deviceIp").val();
    var deviceName = $("#deviceName").val();
    var deviceType = $("#deviceType").val();
    var deviceStatus = $("#deviceStatus").val();
    var userId = $("#userList").val();
    var putData = JSON.stringify({deviceHost: deviceIp, deviceName: deviceName, deviceType: deviceType, deviceStatus: deviceStatus, user: {userId: userId}});
    restSet(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, PUT_METHOD, putData, renderEdit);
}
function renderEdit(data) {
    jQuery('#resultDiv').html("id=" + data.deviceId);
    jQuery('#resultDiv').append(" name=" + data.deviceName);
    jQuery('#resultDiv').append(" host=" + data.deviceHost);
    jQuery('#resultDiv').append(" status=" + data.deviceStatus);
    jQuery('#resultDiv').append(" type=" + data.deviceType);
}