function renderGetAll(data) {
    var entityList = data.bookList.book;
    var result = "";
    for (var i = 0; i < entityList.length; i++) {
        result += entityList[i].bookId + "-" + entityList[i].bookName + "-" + entityList[i].publisher + "<br/>";
    }
    $('#resultDiv').html(result);
}
function renderQueryGet(data) {
    $('#resultDiv').html("query result: " + data.bookId + "-" + data.bookName + "-" + data.publisher);
}
function renderPathGet(data) {
    $('#resultDiv').html("path result: " + data.bookId + "-" + data.bookName + "-" + data.publisher);
}

function renderDelete(data) {
    $('#resultDiv').html(data);
}