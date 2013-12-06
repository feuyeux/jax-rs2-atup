function initial() {
	var query = window.location.search.substring(1);
	jQuery("#caseId").val(getValue(query, "caseId"));
	jQuery("#caseName").val(getValue(query, "caseName"));
	rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderDeviceList);
	var html = "<option value='3'>HIGH</option><option value='2'>MEDIUM</option><option value='1'>LOW</option>";
	jQuery("#priorityList").empty().append(html);
	jQuery('#resultDiv').html("Done!");
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
function getValue(query, key) {
	var vars = query.split("&");
	var value = "";
	for (i = 0; i < vars.length; i++) {
		var parts = vars[i].split("=");
		if (parts[0] == key) {
			return parts[1];
		}
	}
	return null;
}
function createJob() {}