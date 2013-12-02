/*POST*/
function createUser() {
    var userName = jQuery("#userName0").val();
    var password = jQuery("#password0").val();
    var hashPassword = md5(password);
    var postData = JSON.stringify({userName: userName, passWord: hashPassword, userRole: 4});
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, POST_METHOD, postData, "application/json", "json", renderCreate);
};
/*PUT*/
function updateUser() {
    var userName = jQuery("#userName").val();
    var password = jQuery("#password").val();
    var hashPassword = md5(password);
    var userRole = jQuery("#role").val();
    var putData = JSON.stringify({userName: userName, passWord: hashPassword, userRole: userRole});
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, PUT_METHOD, putData, "application/json", "json", renderUpdate);
}
/*GET*/
function getUser() {
}
function signIn() {
    var userName = jQuery("#userName").val();
    var password = jQuery("#password").val();
    var hashPassword = md5(password);
    var url = HOST + ATUP_USER_BASE_URI + SIGNIN_PATH + "?user=" + userName + "&password=" + hashPassword;
    rest(url, GET_METHOD, "", "application/json", "json", renderSignIn);
}
/*RENDER*/
function renderCreate(data) {
    jQuery('#resultDiv').html("DONE! id=" + data.userId);
}
function renderUpdate(data) {
    jQuery("#usersDiv").html("<div><span style='width:100px;display:inline-block;'>User ID</span>");
    jQuery("#usersDiv").append("<span style='width:100px;display:inline-block;'>User Name</span>");
    jQuery("#usersDiv").append("<span style='width:100px;display:inline-block;'>User Role</span></div>");
    jQuery("#usersDiv").append("<div><span style='width:100px;display:inline-block;'>");
    jQuery("#usersDiv").append(data.userId);
    jQuery("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
    jQuery("#usersDiv").append(data.userName);
    jQuery("#usersDiv").append("</span><span style='width:100px;display:inline-block;'>");
    jQuery("#usersDiv").append(data.userRole);
    jQuery("#usersDiv").append("</span></div>");
}
function renderSignIn(data) {
    storage.setItem("userId", data.userId);
    storage.setItem("userName", data.userName);
    window.location.href = "index.html";
}