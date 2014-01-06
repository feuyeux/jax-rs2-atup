function loadLinkByRole() {
    var userRole = storage.getItem("userRole");
    if (userRole == ROLE_ADMIN) {
        jQuery('#userDiv').html(
            "<div><h5>Users</h5></div>" +
                "<a href='http://localhost:8080/atup-page/users/users.html'>User List</a><br/>" +
                "<a href='http://localhost:8080/atup-page/users/user.html'>Manage User</a><br/>");
    } else if (userRole == ROLE_JOB_KILLER) {

    } else if (userRole == ROLE_DEVICE_MANAGER) {
        jQuery('#deviceDiv').append("<a href='http://localhost:8080/atup-page/devices/device.html'>Manage Device</a><br/>");
    } else if (userRole == ROLE_USER) {

    }
}