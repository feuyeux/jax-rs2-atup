function initial() {
    checkSignIn();
    var query = window.location.search.substring(1);
    jQuery("#caseId").val(getValue(query, "caseId"));
    jQuery("#caseName").val(getValue(query, "caseName"));
    restGet(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, renderDeviceList);
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

function createJob() {
    var deviceIp = jQuery("#deviceList").val();
    var caseId = jQuery("#caseId").val();
    var caseName = jQuery("#caseName").val();
    var priority = jQuery("#priorityList").val();
    var userId = storage.getItem("userId");


    if (deviceIp && caseId && caseName && priority) {
        var postData = JSON.stringify({deviceIp: deviceIp, caseId: caseId, userId: userId, priority: priority, generatedTime: new Date()});
        restSet(HOST + ATUP_CASE_BASE_URI + TEST_JOB_PATH, POST_METHOD, postData, afterCreate());
    } else {
        jQuery('#resultDiv').html("Required parameter is missing, check the input parameters");
    }
}

function afterCreate() {
    var resultDiv = jQuery('#resultDiv');
    resultDiv.html(resultDiv.val() + " :: DONE.");
}