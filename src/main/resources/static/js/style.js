$(document).ready(function () {

    // 로그인 박스 노출 여부
    $('.header-container .subMenu .person').click(function () {
        $('.header-container .subMenu .person > .login-box').toggle();
    });

});


// 로그인 모달 창
function showLogin() {
    $('#myModal').show();
};
//팝업 Close 기능
function close_pop(flag) {
    $('#myModal').hide();
};