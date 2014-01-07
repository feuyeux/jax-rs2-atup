function load() {
    checkSignIn();
    markVersion();
    loadLinkByRole();
}
function loadLinkByRole() {
    var userRole = storage.getItem("userRole");
    if (userRole == ROLE_ADMIN) {
        jQuery('#userDiv').html(
            "<div><h5>Users</h5></div>" +
                "<a href='users/users.html'>User List</a><br/>" +
                "<a href='users/user.html'>Manage User</a><br/>");
    } else if (userRole == ROLE_JOB_KILLER) {

    } else if (userRole == ROLE_DEVICE_MANAGER) {
        jQuery('#deviceDiv').append("<a href='devices/device.html'>Manage Device</a><br/>");
    } else if (userRole == ROLE_USER) {

    }
}
function markVersion() {
    jQuery('#buildDiv').html("Build Time: @buildtime@");
}