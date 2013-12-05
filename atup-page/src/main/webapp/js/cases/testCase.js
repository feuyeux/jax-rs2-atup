function initial() {
	rest(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH + "/suites?start=0&size=100", GET_METHOD, null, renderSuiteList);
	rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderDeviceList);
	jQuery('#resultDiv').html("Done!");
}
function renderSuiteList(data) {
	var list = data.suiteList;
	var html = "";
	jQuery.each(list, function(i, suite) {
		html += "<option value=\"";
		html += suite.suiteId + "\">";
		html += suite.suiteName + "</option>";
	});
	jQuery("#suiteList").empty().append(html);
}
function renderDeviceList(data) {
	var list = data.deviceList;
	var html = "";
	jQuery.each(list, function(i, device) {
		html += "<option value=\"";
		html += device.deviceId + "\">";
		html += device.deviceName + "</option>";
	});
	jQuery("#deviceList").empty().append(html);
}
