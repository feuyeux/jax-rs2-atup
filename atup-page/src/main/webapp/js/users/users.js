loadUsers();
function loadUsers() {
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, null, null, "json", renderGetAll);

}

function renderGetAll(data) {
    $.each(data.userList, function (i, user) {
        $("#usersDiv").html("");
        $("#usersDiv").append("<div><span style='width:300px;display:inline-block;'>User ID </span>");
        $("#usersDiv").append(user.userId);
        $("#usersDiv").append("<div><span style='width:300px;display:inline-block;'>User Name </span>");
        $("#usersDiv").append(user.userName);
        $("#usersDiv").append("<div><span style='width:300px;display:inline-block;'>User Role </span>");
        $("#usersDiv").append(user.userRole);
        $("#usersDiv").append("</div>");
    });
}