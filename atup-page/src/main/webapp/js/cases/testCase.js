for ( var i = 0; i < data.length; i++) {
	html += "<option value=\"";
	html += data[i].value + "\">";
	html += data[i].text + "</option>";
}
$("#suiteList").empty().append(html);