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

// 날짜 범위 계산
$('#date_end').focus(function () {
    var start = $('#date_start').datepicker('getDate');
    var end = $('#date_end').datepicker('getDate');
    if (start || end) {
        var days = (end - start) / 1000 / 60 / 60 / 24;
        $('#num_nights').val(days);
        console.log(days);
    }
});