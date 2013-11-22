/*POST*/
function createUser() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var postData = JSON.stringify({userName: userName, passWord: password, userRole: 1});
    rest(HOST + ATUP_USER_BASE_URI + USER_PATH, POST_METHOD, postData, "application/json", "json", renderCreate);
};

function renderCreate(data) {
    $('#resultDiv').html("DONE! id=" + data.userId);
}