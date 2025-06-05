window.addEventListener("load", () => {
    initDataTable("userTable", {
        columns: col,
        ajaxInfo,
        columnOrders: "Y",
        searchOption: [
            'searchText',
            'faqTypeCd',
            'startDateTime',
            'endDateTime'
        ]
    });
});

let ajaxInfo = {
    type: "GET",
    contentType:"application/json",
    url: '/admin/user/list.json',
    data: function (d) {
        d.length = $('select[name=faqTable_length]').val();
        d.searchText = $('#searchText').val();
        d.faqTypeCd = $("select#faqType").val();
    },
    dataSrc: function (json) {
        //리턴 값 재조정
        // debugger;
        // json.recordsTotal = json.data.recordsTotal;
        // json.recordsFiltered = json.data.recordsFiltered;
        // json.data = json.data.data;
        return json.data;
    },
    error: function (e) {
        openDialog('alert', 'N', 'primary', '내부오류가 발생했습니다.', '알림', '확인', '', null, null);
    },
    complete: function () {
        // mask.close();
    }
};

    // 컬럼 정보
let col = [
    {data: "nickname", title: "닉네임", width: '10%', align: "center"},
    {data: "providerProfile", title: "사진", width: '10%', align: "center",
        render: function (data, type, row) {
            return `<div class="pic"><img src="${data}" alt="profile"/></div>`;
        }
    },
    {data: "providerType", title: "가입유형", width: '10%', align: "center"},
    {data: "regDate", title: "가입일", width: '10%', align: "center"},
    {data: "lastDate", title: "최근접속일시", width: '10%', align: "center"},
]

var mainObj = {
    listURL : "/admin/user/list.json",
}