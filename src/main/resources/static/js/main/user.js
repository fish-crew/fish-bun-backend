$(document).ready(function () {
    mainObj.getData();
});

function formattedDate(date) {
    return date == null ? "-" : date.split('T')[0] + ' ' + date.split('T')[1].split('.')[0];
}

function formattedStr(str) {
    return str == null ? "-" : str;
}

var mainObj = {
    listURL : "/admin/user/list.json",
    getData : function () {
        $.ajax({
            type: "GET",
            contentType:"application/json",
            url: mainObj.listURL,
            success: function (result, status, xhr) {
                const tableBody = $("#table");
                tableBody.html();
                result.data.forEach((row, index) => {
                    var rows = "<tr>";
                    rows += `<td><a href="/admin/user/detail?userId=${row.userId}">${formattedStr(row.nickname)}</a></td>`;
                    rows += `<td>${row.eatenCount}</td>`;
                    rows += `<td>${formattedStr(row.lastEatenDate)}</td>`;
                    rows += `<td>${formattedDate(row.regDate)}</td>`;
                    rows += `<td>${formattedDate(row.lastDate)}</td>`;
                    rows += "</tr>";
                    tableBody.append(rows);
            });
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    },
}