function initial() {
    var query = window.location.search.substring(1);
    jQuery("#caseId").val(getValue(query, "caseId"));
    jQuery("#caseName").val(getValue(query, "caseName"));
    rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderDeviceList);
    var html = "<option value='3'>HIGH</option><option value='2'>MEDIUM</option><option value='1'>LOW</option>";
    jQuery("#priorityList").empty().append(html);
}
function renderDeviceList(data) {
    var list = data.deviceList;
    var html = "";
    jQuery.each(list, function (i, device) {
        html += "<option value=\"";
        html += device.deviceHost + "\">";
        html += device.deviceName + "</option>";
    });
    jQuery("#deviceList").empty().append(html);
}
function getValue(query, key) {
    var vars = query.split("&");
    for (i = 0; i < vars.length; i++) {
        var parts = vars[i].split("=");
        if (parts[0] == key) {
            return parts[1];
        }
    }
    return null;
}
function createJob() {
    var deviceIp = jQuery("#deviceList").val();
    var caseId = jQuery("#caseId").val();
    var caseName = jQuery("#caseName").val();
    var priority = jQuery("#priorityList").val();
    var postData = JSON.stringify({deviceIp: deviceIp, caseId: caseId, caseName: caseName, priority: priority});
    rest(HOST + ATUP_CASE_BASE_URI + TEST_JOB_PATH, POST_METHOD, postData, afterCreate);
}
function afterCreate() {
    var resultDiv = jQuery('#resultDiv');
    resultDiv.html(resultDiv.val() + " :: DONE.");
}