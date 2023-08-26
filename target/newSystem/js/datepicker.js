$(document).ready(function () {
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 0,
        todayBtn:  1,
        autoClose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: true,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        bootcssVer:3,
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  1,
        autoClose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: true
    });
    $(".form_next").datetimepicker({
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  true,
        autoClose:true,
        todayHighlight: true,
        startView: 2,
        minView: 2,
        forceParse: true,
        startDate:new Date()
    });
    $(".form_estab").datetimepicker({
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  1,
        autoClose: true,
        todayHighlight: true,
        startView: 2,
        minView: 2,
        forceParse: true,
        endDate:new Date()
    });
    $('.form_time').datetimepicker({
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  1,
        autoClose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse:true
    });
});