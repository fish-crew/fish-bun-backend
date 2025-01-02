$(document).ready(function () {
    mainObj.getData();
});

var mainObj = {
    listURL : "/admin/report/list.json",
    getData : function () {
        $.ajax({
            type: "GET",
            contentType:"application/json",
            url: mainObj.listURL,
            success: function (result, status, xhr) {
                console.log("success 호출~");
                const tableBody = $("#table");
                tableBody.html();
                result.data.forEach((row, index) => {
                console.log("row : " + JSON.stringify(row) +"index : " +index);
                var rows = "<tr>";
                rows += `<td>${row.nickname}</td>`;
                rows += `<td>${row.flavor}</td>`;
                rows += `<td>${row.status}</td>`;
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