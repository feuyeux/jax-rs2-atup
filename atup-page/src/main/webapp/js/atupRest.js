function rest(restUrl, httpMethod, param, contenttype, datatype, callback) {
    jQuery('#resultDiv').html("Loading...");
    var request = jQuery.ajax({type: httpMethod, url: restUrl, data: param, contentType: contenttype, dataType: datatype, crossDomain :true});
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                jQuery('#resultDiv').html(NO_RESULT);
            } else {
                callback(data);
            }
        } catch (e) {
            jQuery('#resultDiv').html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
    });
}


function getInfoByQuery() {
    var url = $("#queryUrl").val();
    rest(GET_METHOD, null, null, 'json', renderQueryGet);
}
function getInfoByPath() {
    var url = $("#pathUrl").val();
    rest(GET_METHOD, null, null, 'json', renderPathGet);
}

function renderPut(data) {
    $('#resultDiv').html("DONE! id=" + data.bookId);
}

/*DELETE*/
function deleteInfo() {
    var url = $("#delUrl").val();
    rest(DELETE_METHOD, null, null, 'text', renderDelete);
}