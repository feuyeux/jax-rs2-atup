function loadDevices() {
    checkSignIn();
    jQuery("#devicesDiv").html("Loading...");
    var userRole = storage.getItem("userRole");
    if (ROLE_DEVICE_MANAGER == userRole) {
        rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH + "/all", GET_METHOD, null, renderGetAll);
    } else {
        rest(HOST + ATUP_DEVICE_BASE_URI + DEVICE_PATH, GET_METHOD, null, renderGetAll);
    }
}
function renderGetAll(data) {
    var list = data.deviceList;
    if (list == null || list.length == 0) {
        jQuery("#devicesDiv").html("No device for the current user.");
    } else {
        var userRole = storage.getItem("userRole");
        if (ROLE_DEVICE_MANAGER == userRole) {
            jQuery("#devicesDiv").html(
                "<div align='left'>" + SPAN_BEGIN
                    + "Device ID</span>" + SPAN_BEGIN2
                    + "Device Name</span>" + SPAN_BEGIN2
                    + "Device IP</span>" + SPAN_BEGIN
                    + "Status</span>" + SPAN_BEGIN2
                    + "Last Updated</span>" + SPAN_BEGIN2
                    + "User</span>" + SPAN_BEGIN2
                    + "Manage</span></div>");
            jQuery.each(list, function (i, device) {
                var line = "<div align='left'>" + SPAN_BEGIN
                    + device.deviceId + "</span>" + SPAN_BEGIN2
                    + device.deviceName + "</span>" + SPAN_BEGIN2
                    + device.deviceHost + "</span>" + SPAN_BEGIN
                    + device.deviceStatus + "</span>" + SPAN_BEGIN2
                    + device.updateTime + "</span>" + SPAN_BEGIN2
                    + device.user.userName + "</span>" + SPAN_BEGIN2
                    + "<a href='device.html?deviceIp=" + device.deviceHost + "&deviceName=" + device.deviceName + "&deviceType="
                    + device.deviceType + "&deviceStatus=" + device.deviceStatus + "&userId=" + device.user.userId
                    + "'>edit</a></span></div>";
                jQuery("#devicesDiv").append(line);
            });
        } else {
            jQuery("#devicesDiv").html(
                "<div align='left'>" + SPAN_BEGIN
                    + "Device ID</span>" + SPAN_BEGIN2
                    + "Device Name</span>" + SPAN_BEGIN2
                    + "Device IP</span>" + SPAN_BEGIN
                    + "Status</span>" + SPAN_BEGIN2
                    + "Last Updated</span></div>");
            jQuery.each(list, function (i, device) {
                var line = "<div align='left'>" + SPAN_BEGIN
                    + device.deviceId + "</span>" + SPAN_BEGIN2
                    + device.deviceName + "</span>" + SPAN_BEGIN2
                    + device.deviceHost + "</span>" + SPAN_BEGIN
                    + device.deviceStatus + "</span>" + SPAN_BEGIN2
                    + device.updateTime + "</span>" + "</span></div>";
                jQuery("#devicesDiv").append(line);
            });
        }
    }
}