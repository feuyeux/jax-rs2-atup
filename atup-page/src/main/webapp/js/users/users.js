loadUsers();
function loadUsers() {
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, GET_METHOD, null, null, "json", renderGetAll);

}

function renderGetAll(data) {
    $.each(data.userList, function (i, user) {
        $("#usersDiv").html("");
        $("#usersDiv").append("<div>User ID " + user.userId + " User Name " + user.userName + " User Role " + user.userRole + "</div>");
    });
}