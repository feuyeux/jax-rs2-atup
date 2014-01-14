function loadJobs() {
    checkSignIn();
    jQuery("#jobsDiv").html("Loading...");
    restGet(HOST + ATUP_CASE_BASE_URI + TEST_JOB_PATH, GET_METHOD, renderGetAll);
}
function renderGetAll(data) {
    var list = data.jobList;
    if (list == null || list.length == 0) {
        jQuery("#jobsDiv").html("No job is arranging.");
    } else {
        var userRole = storage.getItem("userRole");
        if (ROLE_JOB_KILLER == userRole) {
            jQuery("#jobsDiv").html(
                "<div align='left'>" + SPAN_BEGIN
                    + "Job Id</span>" + SPAN_BEGIN
                    + "Case Id</span>" + SPAN_BEGIN
                    + "Device Ip</span>" + SPAN_BEGIN
                    + "User Id</span>" + SPAN_BEGIN2
                    + "Job Priority</span>" + SPAN_BEGIN
                    + "Create Time</span>" + SPAN_BEGIN
                    + "Manage</span></div>");
            jQuery.each(list, function (i, jobInfo) {
                var line = "<div align='left'>" + SPAN_BEGIN
                    + jobInfo.jobId + "</span>" + SPAN_BEGIN
                    + jobInfo.caseId + "</span>" + SPAN_BEGIN
                    + jobInfo.deviceIp + "</span>" + SPAN_BEGIN
                    + jobInfo.userId + "</span>" + SPAN_BEGIN
                    + jobInfo.priority + "</span>" + SPAN_BEGIN
                    + jobInfo.generatedTime + "</span>" + SPAN_BEGIN
                    + "<input type='button' value='REMOVE' onclick='removeJob(" + jobInfo.jobId + ");'/></span></div>";
                jQuery("#jobsDiv").append(line);
            });
        } else {
            jQuery("#jobsDiv").html(
                "<div align='left'>" + SPAN_BEGIN
                    + "Job Id</span>" + SPAN_BEGIN
                    + "Case Id</span>" + SPAN_BEGIN
                    + "Device Ip</span>" + SPAN_BEGIN
                    + "User Id</span>" + SPAN_BEGIN2
                    + "Job Priority</span>" + SPAN_BEGIN
                    + "Create Time</span></div>");
            jQuery.each(list, function (i, jobInfo) {
                var line = "<div align='left'>" + SPAN_BEGIN
                    + jobInfo.jobId + "</span>" + SPAN_BEGIN
                    + jobInfo.caseId + "</span>" + SPAN_BEGIN
                    + jobInfo.deviceIp + "</span>" + SPAN_BEGIN
                    + jobInfo.userId + "</span>" + SPAN_BEGIN
                    + jobInfo.priority + "</span>" + SPAN_BEGIN
                    + jobInfo.generatedTime + "</span></div>";
                jQuery("#jobsDiv").append(line);
            });
        }
    }
}

function removeJob(jobId) {
    var jobInfo = JSON.stringify({jobId: jobId});
    restSet(HOST + ATUP_CASE_BASE_URI + TEST_JOB_PATH, DELETE_METHOD, jobInfo, loadJobs());
}