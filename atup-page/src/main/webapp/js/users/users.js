function loadUsers() {
    checkSignIn();
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, null, renderGetAll);
}

function renderGetAll(data) {
    var list = data.userList;
    if (list == null) {
        jQuery("#usersDiv").html("No user found.");
    } else {
        jQuery("#usersDiv")
            .html(
                "<div align='left'><span style='width:100px;display:inline-block;'>User ID</span>" +
                "<span style='width:100px;display:inline-block;'>User Name</span>" +
                "<span style='width:100px;display:inline-block;'>User Role</span></div>");
        jQuery.each(list, function (i, user) {
        	var line = "<div align='left'><span style='width:100px;display:inline-block;'>"+user.userId+
        	"</span><span style='width:100px;display:inline-block;'>"+user.userName+
        	"</span><span style='width:100px;display:inline-block;'>"+user.userRole+"</span></div>";
          jQuery("#usersDiv").append(line);
        });
    }
}