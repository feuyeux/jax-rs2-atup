function loadJobs() {
	checkSignIn();
	jQuery("#jobsDiv").html("Loading...");
	rest(HOST + ATUP_CASE_BASE_URI + TEST_JOB_PATH, GET_METHOD, null, renderGetAll);
}
function renderGetAll(data) {
	var list = data.jobList;
	if (list == null || list.length == 0) {
		jQuery("#jobsDiv").html("No device for the current user.");
	} else {
		jQuery("#jobsDiv").html(
				"<div align='left'>" + SPAN_BEGIN + "Job Id</span>" + SPAN_BEGIN + "Case Id</span>" + SPAN_BEGIN + "Device Id</span>" + SPAN_BEGIN + "User Id</span>"
						+ SPAN_BEGIN2 + "Job Priority</span></div>");
		jQuery.each(list, function(i, jobInfo) {
			var line = "<div align='left'>" + SPAN_BEGIN + jobInfo.jobId + "</span>" + SPAN_BEGIN + jobInfo.caseId + "</span>" + SPAN_BEGIN
					+ jobInfo.deviceId + "</span>" + SPAN_BEGIN + jobInfo.userId + "</span>" + SPAN_BEGIN + jobInfo.priority + "</span>" + "</span></div>";
			jQuery("#jobsDiv").append(line);
		});
	}
}