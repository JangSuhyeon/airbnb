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

    // datePicker
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

    $( "#date_start" ).datepicker({
        onSelect: showDays,
        minDate: new Date(startDay),
        maxDate: new Date(endDay),
        onClose: function() {
            var date = new Date($("#date_start").datepicker({dateFormat:"yy/mm/dd"}).val());
            $("#date_end").datepicker({
                onSelect: showDays,
                minDate: date,
                maxDate: new Date(endDay)
            });
        }
    });
});

function showDays() {
    var start = $('#date_start').datepicker('getDate');
    var end   = $('#date_end').datepicker('getDate');
    if(!start || !end)
        return;
    var days   = (end - start)/1000/60/60/24;

    // 예약 일자 기간 * 숙박료
    var total = days * $('.hidden-price').val();
    total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    $('.total-container > p > span').text(total);
}