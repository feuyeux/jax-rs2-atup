function initial() {
    checkSignIn();
    restGet(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH + "/suites?start=0&size=100", GET_METHOD, renderSuiteList0);
    jQuery("#caseName").change(function () {
        restGet(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH + "/suites?start=0&size=100", GET_METHOD, renderSuiteList);
        var caseName = jQuery("#caseName").val();
        restGet(HOST + ATUP_CASE_BASE_URI + TEST_CASE_PATH + "/" + caseName, GET_METHOD, fresh4Update);
    });
}
function renderSuites(data) {
    var list = data.suiteList;
    var html = "";
    jQuery.each(list, function (i, suite) {
        html += "<option value=\"";
        html += suite.suiteId + "\">";
        html += suite.suiteName + "</option>";
    });
    return html;
}
function renderSuiteList0(data) {
    var html = renderSuites(data);
    jQuery("#suiteList0").empty().append(html);
}
function renderSuiteList(data) {
    var html = renderSuites(data);
    jQuery("#suiteList").empty().append(html);
}
function createCase() {
    var suiteId = jQuery("#suiteList0").val();
    var caseName = jQuery("#caseName0").val();
    var caseBody = jQuery("#caseBody0").val();
    var postData = JSON.stringify({caseName: caseName, caseBody: caseBody, caseStatus: 1, suite: {suiteId: suiteId}});
    restSet(HOST + ATUP_CASE_BASE_URI + TEST_CASE_PATH, POST_METHOD, postData, renderEdit);
}
function updateCase() {
    var suiteId = jQuery("#suiteList").val();
    var caseName = jQuery("#caseName").val();
    var caseBody = jQuery("#caseBody").val();
    var caseStatus = jQuery("#caseStatus").val();
    var putData = JSON.stringify({caseName: caseName, caseBody: caseBody, caseStatus: caseStatus, suite: {suiteId: suiteId}});
    restSet(HOST + ATUP_CASE_BASE_URI + TEST_CASE_PATH, PUT_METHOD, putData, renderEdit);
}
function renderEdit(data) {
    jQuery('#resultDiv').html("id=" + data.caseId);
    jQuery('#resultDiv').append(" name=" + data.caseName);
    jQuery('#resultDiv').append(" status=" + data.caseStatus);
    jQuery('#resultDiv').append(" type=" + data.caseBody);
    jQuery('#resultDiv').append(" suite=" + data.suite.suiteId + "," + data.suite.suiteName);
}

function fresh4Update(data) {
    if (data) {
        jQuery('#suiteList').val(data.suite.suiteId);
        jQuery("#caseName").val(data.caseName);
        jQuery("#caseStatus").val(data.caseStatus);
        jQuery("#caseBody").val(data.caseBody);
    } else {
        jQuery("#caseName").val(data.caseName);
        jQuery("#caseStatus").val("");
        jQuery("#caseBody").val("");
    }
}