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