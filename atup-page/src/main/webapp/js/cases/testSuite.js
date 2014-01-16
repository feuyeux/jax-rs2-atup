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
    var resultDiv = jQuery('#resultDiv');
    resultDiv.html("id=" + data.suiteId);
    resultDiv.append(" name=" + data.suiteName);
    resultDiv.append(" status=" + data.suiteStatus);
    resultDiv.append(" type=" + data.suiteType);
}