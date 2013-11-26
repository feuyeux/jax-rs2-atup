loadUsers();
function loadUsers() {
	rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, null, null, "json", renderGetAll);
}
function renderGetAll(data) {
	var list = data.userList;
	if (list == null) {
		$("#usersDiv").html("No user found.");
	} else {
		$("#usersDiv")
				.html(
						"<div><span style='width:100px;display:inline-block;'>User ID</span><span style='width:100px;display:inline-block;'>User Name</span><span style='width:100px;display:inline-block;'>User Role</span></div>");
		$.each(list, function(i, user) {
			$("#usersDiv").append("<div><span style='width:100px;display:inline-block;'>");
			$("#usersDiv").append(user.userId);
			$("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
			$("#usersDiv").append(user.userName);
			$("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
			$("#usersDiv").append(user.userRole);
			$("#usersDiv").append("</span></div>");
		});
	}
}