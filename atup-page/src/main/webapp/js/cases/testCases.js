function loadCases(start, size) {
	checkSignIn();
	rest(HOST + ATUP_CASE_BASE_URI + TEST_CASE_PATH + "/cases?start=" + start + "&size=" + size, GET_METHOD, null, renderGetAll);
}
function renderGetAll(data) {
	var list = data.testCaseList;
	if (list == null) {
		jQuery("#CasesDiv").html("No Test Case found.");
	} else {
		jQuery("#CasesDiv").html(
				"<div align='left'>" + SPAN_BEGIN + "Case ID</span>" + SPAN_BEGIN + "Case Name</span>" + SPAN_BEGIN + "Case Status</span>" + SPAN_BEGIN
						+ "Case body</span>" + SPAN_BEGIN2 + "Last Updated</span></div>");
		jQuery.each(list, function(i, testCase) {
			var line = "<div align='left'>" + SPAN_BEGIN + testCase.caseId + "</span>" + SPAN_BEGIN + testCase.caseName + "</span>" + SPAN_BEGIN
					+ testCase.caseStatus + "</span>" + SPAN_BEGIN + testCase.caseBody + "</span>" + SPAN_BEGIN + testCase.updateTime + "</span></div>";
			jQuery("#casesDiv").append(line);
		});
	}
}