/*POST*/
function createUser() {
	var userName = $("#userName0").val();
	var password = $("#password0").val();
	var hashPassword = md5(password);
	var postData = JSON.stringify({userName : userName, passWord : hashPassword, userRole : 4});
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, POST_METHOD, postData, "application/json", "json", renderCreate);
};
/*PUT*/
function updateUser() {
	var userName = $("#userName").val();
	var password = $("#password").val();
	var hashPassword = md5(password);
	var userRole = $("#role").val();
	var putData = JSON.stringify({userName : userName, passWord : hashPassword, userRole : userRole});
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, PUT_METHOD, putData, "application/json", "json", renderUpdate);
}
/*GET*/
function getUser() {}
function signIn() {
	var userName = $("#userName").val();
	var password = $("#password").val();
	var hashPassword = md5(password);
	var url = HOST + ATUP_USER_BASE_URI + SIGNIN_PATH + "?user=" + userName + "&password=" + hashPassword;
	rest(url, GET_METHOD, "", "application/json", "json", renderSignIn);
}
/*RENDER*/
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
function renderSignIn(data) {
	storage.setItem("userId",  data.userId);
	window.location.href = "index.html";
}