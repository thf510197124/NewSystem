$(document).ready(function () {
    var companyName = $("#in-name");
    var comTb = $("#tbcontent");
    $(companyName).blur(function () {
        $(comTb).fadeOut();
    });
    $(companyName).bind("input propertychange",function () {
        var name = $("#in-name").val();
        var baseUrl=$(".nav-serch").attr("id");
        if (name.length > 0){
            $.ajax({
                type:"post",
                url:baseUrl + "company/nameList.json",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(name),
                success:function (data) {
                    $(comTb).html("");
                    if (data!=null){
                        for(var i=0;i<$(data).length;i++){
                            /*$("#tbcontent").addClass("show");*/
                            $(comTb).append("<div class='item' onclick='mousedown(this)'>" + data[i] + "</div>")
                        }
                        $(comTb).slideDown();
                    }
                }/*,
                error:function () {
                    alert("error");
                }*/

            });
        }
    });
    //var thisDataList = $(supplierName).parent().next();
    var supplierName = $(".supplier");
    $(supplierName).blur(function () {
        $(this).parent().next(".tb-supplier").fadeOut();
    });
    $(supplierName).bind("input propertychange",function () {
        var name = $(this).val();
        var baseUrl = this.id;
        var tbSupplier = $(this).parent().next(".tb-supplier");

        var x = $(this).position().left;
        var width = $(this).width();
        $(tbSupplier).css("left", x + 15);
        $(tbSupplier).css("width",width);
        $(tbSupplier).find(".item").css("width",width);
        $(tbSupplier).find(".item").css("overflow","hidden");

        if (name.length > 0){
            $.ajax({
                type:"post",
                url:baseUrl + "supply/nameList.json",
                contentType:"application/json;charset=utf-8",
                data:JSON.stringify(name),
                success:function (data) {
                    $(tbSupplier).html("");
                    if (data != null){
                        for (var i=0;i<$(data).length;i++){
                            $(tbSupplier).append("<div class='item' onclick='mousedown(this)'>" + data[i] + "</div>");
                        }
                        $(tbSupplier).slideDown();
                    }
                }
            })
        }
    });

});
//选择其中的内容
function mousedown(object) {
    /*$("#in-name").val($(object).text());
    $("#tbcontent").fadeOut();*/
    /*alert($(object).closest(".name-tip").find("input[type=text]").html());*/
    $(object).closest(".name-tip").find("input[type=text]").val($(object).text());
    $(object).parent().fadeOut();
}