function createDevice() {
    var deviceIp = $("#deviceIp0").val();
    var deviceName = $("#deviceName0").val();
    var deviceType = $("#deviceType0").val();
    var postData = JSON.stringify({deviceHost: deviceIp, deviceName: deviceName, deviceType: deviceType, deviceStatus: '1'});
    rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, POST_METHOD, postData, renderEdit);
}
function updateDevice() {
    var deviceIp = $("#deviceIp").val();
    var deviceName = $("#deviceName").val();
    var deviceType = $("#deviceType").val();
    var deviceStatus = $("#deviceStatus").val();
    var putData = JSON.stringify({deviceHost: deviceIp, deviceName: deviceName, deviceType: deviceType, deviceStatus: deviceStatus});
    rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, PUT_METHOD, putData, renderEdit);
}
function renderEdit(data) {
    jQuery('#resultDiv').html("id=" + data.deviceId);
    jQuery('#resultDiv').append(" name=" + data.deviceName);
    jQuery('#resultDiv').append(" host=" + data.deviceHost);
    jQuery('#resultDiv').append(" status=" + data.deviceStatus);
    jQuery('#resultDiv').append(" type=" + data.deviceType);
}