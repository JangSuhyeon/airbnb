// Air datepicker
var date = new Date();

    var dp = $('#date_start').datepicker({
    //년-월-일
    startDate: new Date(date.getFullYear(), date.getMonth(), date.getDate()),
    language: 'ko',
    autoClose: true,
    //선택한 날짜를 가져옴
    onSelect: function (date) {
        var endNum = date;
        //종료일 datepicker에 최소날짜를 방금 클릭한 날짜로 설정
        $('#date_end').datepicker({
            minDate: new Date(endNum),
        });
    }
    }).data('datepicker');

    var dp2 = $('#date_end').datepicker({
    startDate: new Date(date.getFullYear(), date.getMonth(), date.getDate()),  // 시간, 분은 00으로 초기 설정
    language: 'ko',
    autoClose: true,
    //선택한 날짜를 가져옴
    onSelect: function (date) {
        var startNum = date;
        $('#date_start').datepicker({
            //시작일 datepicker에 최대날짜를 방금 클릭한 날짜로 설정
            maxDate: new Date(startNum),
        });
    }
}).data('datepicker');



// 게스트 선택 박스
$(function(){ 
    // 게스트 선택 박스 노출 여부
    $('.guest-bot > .guest').focus(function () {
        $('.guest-bot > .guest-select-box').css('display','block');
    });
    $('.guest-bot > .guest-select-box > a').click(function () {
        $('.guest-bot > .guest-select-box').css('display','none');
    });

    // 추가-감소 버튼
    $('.cnt-plus').click(function () {
        var cntInput = $(this).prev('.guest-cnt');
        var cnt = cntInput.val();
        $(cntInput).val(++cnt);
        guestCntChange();
    });
    $('.cnt-minus').click(function () {
        var cntInput = $(this).next('.guest-cnt');
        var cnt = cntInput.val();
        $(cntInput).val(--cnt);
        guestCntChange();
    });

    // 총 게스트 수 노출
    $('.guest-bot > .guest').val('게스트 1명');
    function guestCntChange() {
        var adult = Number($('.adult-cnt').val());
        var kid = Number($('.kid-cnt').val());
        var baby = Number($('.baby-cnt').val());
        var total = adult + kid;
        if(baby > 0){
            $('.guest-bot > .guest').val('게스트 ' +  total + '명, 유아 ' + baby + '명');
        }else{
            $('.guest-bot > .guest').val('게스트 ' +  total + '명');
        }
    }
});