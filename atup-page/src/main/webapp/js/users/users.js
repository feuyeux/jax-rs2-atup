function loadUsers() {
    checkSignIn();
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, renderGetAll);
}
function renderGetAll(data) {
    var list = data.userList;
    if (list == null || list.length == 0) {
        jQuery("#usersDiv").html("No user found.");
    } else {
        jQuery("#usersDiv").html(
            "<div align='left'><span style='width:100px;display:inline-block;'>User ID</span>"
                + "<span style='width:160px;display:inline-block;'>User Name</span>"
                + "<span style='width:100px;display:inline-block;'>User Role</span>"
                + "<span style='width:100px;display:inline-block;'>User Status</span>"
                + "<span style='width:100px;display:inline-block;'>Manage</span></div>");
        jQuery.each(list, function (i, user) {
            var line = "<div align='left'><span style='width:100px;display:inline-block;'>"
                + user.userId + "</span><span style='width:160px;display:inline-block;'>"
                + user.userName + "</span><span style='width:100px;display:inline-block;'>"
                + user.userRole + "</span><span style='width:100px;display:inline-block;'>"
                + user.status + "</span>"
                + "<input type='button' value='DISABLE' onclick='disableUser(" + user.userId + ");'/></div>";
            jQuery("#usersDiv").append(line);
        });
    }
}

function disableUser(userId) {
    var putData = JSON.stringify({userId: userId, status: 1});
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, PUT_METHOD, putData, loadUsers);
}