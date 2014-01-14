function loadSuites(start, size) {
	checkSignIn();
	jQuery("#suitesDiv").html("Loading...");
	rest(HOST + ATUP_CASE_BASE_URI + TEST_SUITE_PATH + "/suites?start=" + start + "&size=" + size, GET_METHOD, renderGetAll);
}
function renderGetAll(data) {
	var list = data.suiteList;
	if (list == null || list.length == 0) {
		jQuery("#suitesDiv").html("No Test Suite found.");
	} else {
		jQuery("#suitesDiv").html(
				"<div align='left'>" + SPAN_BEGIN + "Suite ID</span>" + SPAN_BEGIN + "Suite Name</span>" + SPAN_BEGIN + "Suite Status</span>" + SPAN_BEGIN
						+ "Suite Type</span></div>");
		jQuery.each(list, function(i, suite) {
			var line = "<div align='left'>" + SPAN_BEGIN + suite.suiteId + "</span>" + SPAN_BEGIN + suite.suiteName + "</span>" + SPAN_BEGIN + suite.suiteStatus
					+ "</span>" + SPAN_BEGIN + suite.suiteType + "</span></div>";
			jQuery("#suitesDiv").append(line);
		});
	}
}
