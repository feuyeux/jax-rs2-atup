function initial() {
	rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderDeviceList);
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
	jQuery("#deviceList0").empty().append(html);
}
