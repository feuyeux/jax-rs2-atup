function createSuite() {
    var suiteName = $("#suiteName0").val();
    var postData = JSON.stringify({suiteName: suiteName, suiteStatus: '1', suiteType: '1'});
    restSet(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH, POST_METHOD, postData, renderEdit);
}
function updateSuite() {
    var suiteName = $("#suiteName").val();
    var suiteStatus = $("#suiteStatus").val();
    var suiteType = $("#suiteType").val();
    var putData = JSON.stringify({suiteName: suiteName, suiteStatus: suiteStatus, suiteType: suiteType});
    restSet(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH, PUT_METHOD, putData, renderEdit);
}
function renderEdit(data) {
    jQuery('#resultDiv').html("id=" + data.suiteId);
    jQuery('#resultDiv').append(" name=" + data.suiteName);
    jQuery('#resultDiv').append(" status=" + data.suiteStatus);
    jQuery('#resultDiv').append(" type=" + data.suiteType);
}