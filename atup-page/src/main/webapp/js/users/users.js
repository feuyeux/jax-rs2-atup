loadUsers();
function loadUsers() {
    checkSignIn();
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, null, null, "json", renderGetAll);
}

function checkSignIn() {
    var storageUserId = (storage.getItem("userId"));
    if (storageUserId == null) {
        window.location.href = "signIn.html";
    } else {
        var user = storage.getItem("userName");
        jQuery("#welcomeDiv").html("Welcome " + user);
    }
}

function renderGetAll(data) {
    var list = data.userList;
    if (list == null) {
        jQuery("#usersDiv").html("No user found.");
    } else {
        jQuery("#usersDiv")
            .html(
                "<div><span style='width:100px;display:inline-block;'>User ID</span><span style='width:100px;display:inline-block;'>User Name</span><span style='width:100px;display:inline-block;'>User Role</span></div>");
        jQuery.each(list, function (i, user) {
            jQuery("#usersDiv").append("<div><span style='width:100px;display:inline-block;'>");
            jQuery("#usersDiv").append(user.userId);
            jQuery("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
            jQuery("#usersDiv").append(user.userName);
            jQuery("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
            jQuery("#usersDiv").append(user.userRole);
            jQuery("#usersDiv").append("</span></div>");
        });
    }
}