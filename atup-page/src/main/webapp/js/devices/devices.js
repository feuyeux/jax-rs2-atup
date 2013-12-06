function loadDevices() {
	checkSignIn();
	rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderGetAll);
}
function renderGetAll(data) {
	var list = data.deviceList;
	if (list == null) {
		jQuery("#devicesDiv").html("No device for the current user.");
	} else {
		jQuery("#devicesDiv").html(
				"<div align='left'>" + SPAN_BEGIN + "Device ID</span>" + SPAN_BEGIN + "Device Name</span>" + SPAN_BEGIN + "Device IP</span>" + SPAN_BEGIN
						+ "Status</span>" + SPAN_BEGIN2 + "Last Updated</span></div>");
		jQuery.each(list, function(i, device) {
			var line = "<div align='left'>" + SPAN_BEGIN + device.deviceId + "</span>" + SPAN_BEGIN + device.deviceName + "</span>" + SPAN_BEGIN + device.deviceHost
					+ "</span>" + SPAN_BEGIN + device.deviceStatus + "</span>" + SPAN_BEGIN2 + device.updateTime + "</span>" + "</span></div>";
			jQuery("#devicesDiv").append(line);
		});
	}
}