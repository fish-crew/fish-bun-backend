$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    commonLoginURL : "/admin/login",
    homeURL : "/admin/report/list",
    init : function(){
        var me = this;
        me.bind();
    },
    bind : function () {
        $('#commonLoginBtn').bind('click', function () {
            pageObject.processCommonLogin();
        });
    },
    processCommonLogin : function () {
        var obj = {
            "email" : $("#email").val(),
            "password" : $("#password").val()
        }
        $.ajax({
            type: "POST",
            url: pageObject.commonLoginURL,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function (data, status, xhr) {
                if (data.result == 1) {
                    alert("로그인이 성공되었습니다.");
                    window.location.href = pageObject.homeURL;
                    return false;
                }
                alert("로그인에 실패하였습니다.\n"+
                    "아이디 또는 비밀번호를 확인해주세요.");
                return false;
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    },
}