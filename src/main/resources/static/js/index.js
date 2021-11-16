// datePicker
$(function(){

    var bodyOffset = jQuery('body').offset();
    $(window).scroll(function() { // 윈도우 스크롤 이벤트 시
        if ($(document).scrollTop() > bodyOffset.top) {
            $('header').addClass('scroll');
            $('.header-container .logo > img').attr("src", 'images/logo_pk.png');
        } else {
            $('header').removeClass('scroll');
            $('.header-container .logo > img').attr("src", 'images/logo_wh.png');
        }
    });

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
        minDate: new Date(),
        onClose: function() {
            let date = new Date($("#date_start").datepicker({dateFormat:"yy-mm-dd"}).val());
            $("#date_end").datepicker({
                onSelect: showDays,
                minDate: date
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
}