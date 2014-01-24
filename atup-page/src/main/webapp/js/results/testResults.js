function loadResults(start, size) {
    checkSignIn();
    jQuery("#resultsDiv").html("Loading...");
    restGet(HOST + ATUP_CASE_BASE_URI + TEST_RESULT_PATH + "/results?start=" + start + "&size=" + size, GET_METHOD, renderGetAll);
}
function renderGetAll(data) {
    var list = data.resultList;
    if (list == null) {
        jQuery("#resultsDiv").html("No Test Result found.");
    } else {
        jQuery("#resultsDiv").html(
            "<div align='left'>" + SPAN_BEGIN
                + "Result ID</span>" + SPAN_BEGIN2
                + "Result Status</span>" + SPAN_BEGIN
                + "Owner</span>" + SPAN_BEGIN
                + "Case Info</span>" + SPAN_BEGIN4
                + "Result Body</span></div>");
        jQuery.each(list, function (i, testResult) {
            var line = "<div align='left'>" + SPAN_BEGIN
                + testResult.resultId + "</span>" + SPAN_BEGIN2
                + testResult.resultStatus + "</span>" + SPAN_BEGIN
                + testResult.user.userName + "</span>" + SPAN_BEGIN
                + testResult.testCase.caseName + "</span>" + SPAN_BEGIN4
                + testResult.createTime + "..." + testResult.updateTime + "..." + testResult.resultBody +
                "</span></div>";
            jQuery("#resultsDiv").append(line);
        });
    }
}