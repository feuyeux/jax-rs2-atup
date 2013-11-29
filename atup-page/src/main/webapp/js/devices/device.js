function createDevice(){
	var deviceIp = $("#deviceIp0").val();
	var deviceName = $("#deviceName0").val();
	var deviceType = $("#deviceType0").val();
	var postData = JSON.stringify({userName : userName, passWord : password, userRole : 4});
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, POST_METHOD, postData, "application/json", "json", renderCreate);
}