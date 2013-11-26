/*POST*/
function createUser() {
	var userName = $("#userName0").val();
	var password = $("#password0").val();
	var postData = JSON.stringify({userName : userName, passWord : password, userRole : 4});
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, POST_METHOD, postData, "application/json", "json", renderCreate);
};
/*PUT*/
function updateUser() {
	var userName = $("#userName").val();
	var password = $("#password").val();
	var userRole = $("#role").val();
	var putData = JSON.stringify({userName : userName, passWord : password, userRole : userRole});
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, PUT_METHOD, putData, "application/json", "json", renderUpdate);
}
function renderCreate(data) {
	$('#resultDiv').html("DONE! id=" + data.userId);
}
function renderUpdate(data) {
	$("#usersDiv").html("<div><span style='width:100px;display:inline-block;'>User ID</span>");
	$("#usersDiv").append("<span style='width:100px;display:inline-block;'>User Name</span>");
	$("#usersDiv").append("<span style='width:100px;display:inline-block;'>User Role</span></div>");
	$("#usersDiv").append("<div><span style='width:100px;display:inline-block;'>");
	$("#usersDiv").append(data.userId);
	$("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
	$("#usersDiv").append(data.userName);
	$("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
	$("#usersDiv").append(data.userRole);
	$("#usersDiv").append("</span></div>");
}