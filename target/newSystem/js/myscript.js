$(document).ready(function () {

    var addAddress = $(".addAddress-form");
    /*$(".btn-pageTo").disabled();*/
    //addAddress.hide();
    /*var inp = $("input[type=number]");
    if ($(inp).val()===""){
        $(inp).val(0);
    }*/
    //删除评论
    $(document).on("click",".details-delete",function () {
         var url = this.id;
         var needHidden = $(this).parent().parent().parent();
         $.ajax({
             url:url,
             type:"GET"
         }).done(function () {
             alert("删除成功");
             needHidden.hide();
         }).fail(function () {
             alert("删除失败")
         });
        return false;
    });
    $("#addContactDetails").click(function () {
        var customerType = $(".contDetailsForm #form-customerType").val();
        var customerTypeStr = $(".contDetailsForm #form-customerType option:selected").text();
        var nextDateForm = $(".contDetailsForm #form-nextDate");
        var nextDate = nextDateForm.val();
        if (nextDate.length > 0 && formatDate(nextDate)===false){
            nextDateForm.addClass("validate-error");
            nextDateForm.after("<span class='input-group-addon'>日期格式错误</span>");
            return false;
        } else if (nextDate.length >0 && formatDate(nextDate)!==false){
            var thisDate = formatDate(nextDate);
            thisDate=thisDate.split("-");
            var date = new Date();
            var dateyear = date.getFullYear();
            var dateMonth = date.getMonth()+1;
            var dateDay = date.getDate();
            var dateStr = dateyear + "-" + dateMonth + "-" + dateDay;
            var parseDate = new Date(parseInt(thisDate[0]),parseInt(thisDate[1])-1,parseInt((thisDate[2])));
            if (date > parseDate){
                nextDateForm.addClass("validate-error");
                nextDateForm.after("<span class='input-group-addon'>过去时间</span>");
                return false;
            }
        }
        var contactDetails = $(".contDetailsForm #form-contactDetails").val();
        var urlString = $(".contDetailsForm").attr("id");
        var contactDetail= {
            "customerType":customerType,
            "nextDate":formatDate(nextDate),
            "contactDetails":contactDetails
        };
        /*var details=$(".contact-details:first");*/
        //alert(details.html());
        $.ajax({
            url:urlString,
            data:JSON.stringify(contactDetail),
            contentType:'application/json;charset=utf-8',
            type:"POST",
            success:function (data) {
                /*alert(details.html());*/
                $(".callbackCutomerTypeText").text(customerTypeStr);
                $(".callbackCutomerType").val(customerType);
                $(".callbackNestDateText").text(formatDate(nextDate));
                $(".callbackNestDate").val(formatDate(dateStr));
                if (parseInt(data)!==0){
                    var basepath = "http://localhost:8080/newSystem";
                    var details = "<div class='contact-details'>" +
                        "<div class=\"contact-day-details\">\n" +
                        "            <span class=\"h6\">" + formatDate(dateStr)+": &nbsp;&nbsp;" + "</span>\n" +
                        "            <span class=\"details-details\">" + contactDetails+ "</span>\n" +
                        "        </div>\n" +
                        "        <div class=\"\">\n" +
                        "            <div class=\"text-right h6\">\n" +
                        "                <a id=\"" + basepath + "/customer/deleteContactDetails/" + parseInt(data) + ".html\" class=\"btn btn-danger btn-sm details-delete\">删除</a>\n" +
                        "            </div>\n" +
                        "        </div>" +
                        "</div>";

                    $(".comment-list div h5.text-info").siblings("hr")[0].after($(details).get(0));
                }
                /*window.location.reload(true);*/
            }
        });
        return false;
    });
    $(".addAddressButton").click(
        function () {
            addAddress.toggle();
        }
    );
    $(".addAddress-submit").attr('disabled',true).click(function () {
        var addressList = $(".address-list");
        var addressType = $("#addressType").val();
        var simple = $(".address-string").val();
        var url = $(".addAddress-form .address-form").attr("id");
        var data={
            "addressType":addressType,
            "simple":simple
        };
        addAddress.toggle();
        $.ajax({
            url:url,
            data:JSON.stringify(data),
            contentType:'application/json;charset=utf-8',
            type:"POST",
            success:function (data) {
                /*data = eval(data);
                addressList.append("<div class=\"input-group\"><span class=\"input-group-addon\">" +
                    data['addressType'] + "</span><span class=\"form-control\">" + data['simple'] + "</span></div>");*/
                window.location.reload(true);
            },
            error:function () {
                alert("false");
            }
        })
    });
    $(".address-string").blur(function () {
        var addressString = $(this).val();
        var url = this.id;
        var addinput = $(this);
        $.ajax({
            url:url,
            type:"post",
            contentType:'application/json;charset=utf-8',
            data:addressString,
            success:function(data){
                if(data){
                    addinput.css("background","green");
                    $(".addAddress-submit").attr('disabled',false);
                }else{
                    addinput.css("background","red");
                    //$(this).after("<span><p style='color:red'>地址无效</p></span>");
                    $(".addAddress-submit").attr('disabled',true);
                }
            }
        })
    }).focus(function () {
        var addinput = $(this);
        addinput.css("background","#eeeeee");
    });
    $(".deleteAddress").click(function () {
        var urlString = this.id;
        //var needHidden1 =  $(this).parent().parent();
        //alert(urlString);
        $.ajax({
            url:urlString,
            type:"get",
            success:function () {
                window.location.reload(true);
            }
        });
    });


    //$(".contactPad").hide();
    $("#addContacts").click(function () {
        var contactPad = $(".contactPad").length;
        var contactPadHtml = $(".contactPad:last").prop("outerHTML");
        //alert(contactPadHtml);
        $(".contactPad:last").after(contactPadHtml);
        //alert($(".contactPad").length);
        $(".contactPad:last select").attr("name","contactes[" + contactPad + "].phoneType");
        $(".contactPad:last input").attr("name","contactes[" + contactPad + "].phoneNumber");
    });
    $(".addEmployeeButton").click(function () {
        $(".comment-box").hide();
        $(".employee-box").show();
        $(this).hide();
        $(".addContactsBtn").show();
    });
    $(".addContactsBtn").click(function () {
        $(".employee-box").hide();
        $(".comment-box").show();
        $(this).hide();
        $(".addEmployeeButton").show();

    });
    $("#addEmployee").click(function (event) {
       /* alert("Come To addEmployee");*/
        var emp = $(".addEmployee-form").serializeJSON();
        var url = $(".addEmployee-form").attr("id");
        /*alert("url id = " + url);*/
        $.ajax({
            url:url,
            data:JSON.stringify(emp),
            contentType:'application/json;charset=utf-8',
            type:"POST",
            success:function (data) {
                window.location.reload(true);
            },
            error:function (XMLhtttpServlet) {
                if(XMLhtttpServlet.status === 502){
                    alert("请添加号码!");
                }else if(XMLhtttpServlet.status=== 406){
                    alert("别添加重复号码!");
                }else if(XMLhtttpServlet.status === 200){
                    //出现了这种类型的问题
                    window.location.reload(true);
                }else{
                    alert("添加失败");
                }
            }
        });
    });
    $(".deleteEmployee").click(function () {
        var url = this.id;
        var needDelete = $(this).closest(".employee");
        var nextDelete =needDelete.next();
        $.ajax({
            url:url,
            type:"get",
            success:function () {
                needDelete.remove();
                nextDelete.remove();
            },
            error:function () {
                alert("删除失败！");
            }
        })
    });
    //默认这些事隐藏的
    /*$(".hidden-employee").hide();*/
    $(".updateEmployee").click(function () {
        var needHidden = $(this).closest(".employee");
        var nextShow = needHidden.next();
        needHidden.hide();
        nextShow.show();
    });
    $(".updateEmpPost").click(function () {
        var forms = $(this).closest(".updateEmp-form");
        var emp = forms.serializeJSON();
        var url = this.id;
        $.ajax({
            url:url,
            data:JSON.stringify(emp),
            contentType:'application/json;charset=utf-8',
            type:"post",
            success:function(){
                window.location.reload(true);
            },
            error:function (XMLhtttpServlet) {
                if(XMLhtttpServlet.status=== 406){
                    alert("别添加重复号码!");
                }else if(XMLhtttpServlet.status=== 200){
                    window.location.reload(true);
                }else{
                    alert(XMLhtttpServlet.status);
                    alert("添加失败");
                }
            }
        })
    });
    $(".addUpdateContacts").click(function () {
        var thisform = $(this).closest(".hidden-employee").find(".contact-update");
        var contsLength = thisform.length;
        var contactUp = thisform.last();
        var contactPadHtml= contactUp.prop("outerHTML");
        thisform.last().after(contactPadHtml);

        var lastEle = $(this).closest(".hidden-employee").find(".contact-update").last();
        //lastEle.addClass("add-con");
        lastEle.find("input:first").attr("name","contactes[" + (contsLength) + "].contactsId");
        lastEle.find("input:first").attr("value","0");
        lastEle.find("select").attr("name","contactes[" + (contsLength) + "].phoneType");
        lastEle.find("input:last").attr("name","contactes[" + (contsLength) + "].phoneNumber").addClass("phone-number");
    });

    /*$(".hidden-company").hide();*/
    $(".go-up-company").click(function () {
        $(".update-hidden").hide();
        $(".hidden-company").show();
    });
    $(".up-company").click(function () {
        var url = this.id;
        var name = $("#up-name").val(),id=$("#up-id").val(),owner=$("#up-owner").val(),
            capital=$("#up-capital").val(),capitalType=$("#up-capitalType").val(),phoneNumber=$("#up-phoneNumber").val();
        var estaDate = $("#up-establishedDate").val(),web=$("#up-web").val(),details=$("#up-details").val(),others=$("#up-others").val();
        if (estaDate.length > 0 && formatDate(estaDate)===false){
            $("#up-establishedDate").addClass("validate-error");
            return false;
        }
        if (phoneNumber.length>0){
            phoneNumber = phoneNumber.match(/\d+/g);
            phoneNumber=phoneNumber.join('');
        }
        var data = {
                companyId:id,
                companyName:name,
                owner:owner,
                phoneNumber:phoneNumber,
                capital:capital,
                capitalType:capitalType,
                establishedDate:formatDate(estaDate),
                web:web,
                businesses:details,
                others:others
        };
        /*alert(JSON.stringify(data));*/
        $.ajax({
            url:url,
            data:JSON.stringify(data),
            contentType:'application/json;charset=utf-8',
            type:"post",
            success:function () {
                window.location.reload(true);
            },
            error:function () {
                alert("修改失败")
            }
        })
    });
    $(".pageTo").change(function () {
        testInput();
    });
    $(".btn-pageTo").click(function () {
        var pageTo = $(".pageTo").val();
        if(pageTo > 0){
            window.location.href=this.id + "/" + pageTo + ".html";
        }else{
            alert("请输入与正确的页码");
        }
    });
    //客户名称输入校验
    validateThis($(".companyName"));
    /*$(".companyName").on("input",function () {
        $(this).blur(function () {
            var name = $.trim($(this).val());
            /!*alert(name);*!/
            if(name.length>0){
                $.ajax({
                    data:JSON.stringify(name),
                    url:this.id,
                    type:"post",
                    contentType:'application/json;charset=utf-8',
                    success:function (data) {
                        changeCompanyNameInput( !data );
                    }
                });
            }else{
                $(".companyName").css("background-color","red");
                $("#validate-btn").attr("disabled",true);
            }
        });
    });*/
    //供应商名称输入校验
   validateThis($(".supplierName"));
   validateThis($(".simpleName"));
    $("#modal-userDetail").on("show.bs.modal",function () {
        var url = $(".userDetail").attr("id");
        $.ajax({
            url:url,
            method:"get",
            success:function (data) {
               /* alert(JSON.stringify(data));*/
                $("#myModalLabel").html(data["name"]);
                $("#user-username").html(data["username"]);
                $("#user-name").html(data["name"]);
                $("#user-phonenumber").html(data["phoneNumber"]);
            }
        })
    });
    $(".deleteCustomer").click(function () {
        var url=$(this).attr("id");
        var now = $(this);
        if (confirm("确认删除客户？")){
            $.ajax({
                url:url,
                method:"get",
                success:function () {
                    now.closest("div").remove();
                }
            });
        }
        return false;
    });
    $("#form-customerType").change(function () {
        var value = $(this).val();
        var year, month, day;
        var nextDate = $("#form-nextDate");
        var getNextDate = nextDateTime(value);
        if (getNextDate.getDay()===0){
            getNextDate.setDate(getNextDate.getDate()+1);
        }
        if (getNextDate.getDay()===6){
            getNextDate.setDate(getNextDate.getDate()+2);
        }
        year=getNextDate.getFullYear().toString();
        month=(getNextDate.getMonth()+1).toString();
        day = getNextDate.getDate().toString();
        if (month.length===1){month='0'+month;}
        if (day.length===1){day='0'+day;}
        nextDate.val(year+'-'+ month+'-'+day);
    });
    /*var goodBan=$(".add-good-ban");
    var goodGuan=$(".add-good-guan");
    var goodXing=$(".add-good-xing");
    var goodLing=$(".add-good-ling");
    var goodJia=$(".add-good-jia");
    var goodOther=$(".add-good-other");
    var goodAmount=$(".good-amount");
    $("#add-ban-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        $(".add-good-ban .good-amount").val(1);
        goodBan.show();
        goodGuan.hide();goodXing.hide();goodLing.hide();goodJia.hide();goodOther.hide();

    });
    $("#add-guan-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        goodBan.hide();
        goodGuan.show(); $(".add-good-guan .good-amount").val(1);
        goodXing.hide();        goodLing.hide();        goodJia.hide();        goodOther.hide();
    });
    $("#add-xing-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        goodBan.hide();        goodGuan.hide();
        goodXing.show(); $(".add-good-xing .good-amount").val(1);
        goodLing.hide();        goodJia.hide();        goodOther.hide();
    });
    $("#add-ling-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        goodBan.hide();        goodGuan.hide();goodXing.hide();
        goodLing.show();        $(".add-good-ling .good-amount").val(1);
        goodJia.hide();        goodOther.hide();
    });
    $("#add-jia-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        goodBan.hide();        goodGuan.hide();goodXing.hide();
        goodLing.hide();        goodJia.show();      $(".add-good-jia .good-amount").val(1);

        goodOther.hide();
    });
    $("#add-others-btn").click(function () {
        $(".good-amount").each(function () {
            $(this).val(0.0);
        });
        goodBan.hide();        goodGuan.hide();goodXing.hide();
        goodLing.hide();        goodJia.hide();        goodOther.show(); $(".add-good-other .good-amount").val(1);
    });*/
    $(".delete-import").click(function () {
        var url = this.id;
        if (window.confirm("确定删除供应商吗？")){
            $.ajax({
                url:url,
                contentType:'application/json;charset=utf-8',
                type:"get",
                success:function () {
                    window.location.reload(true);
                },
                error:function (XMLhtttpServlet) {
                    if(XMLhtttpServlet.status === 200){
                        //出现了这种类型的问题
                        window.location.reload(true);
                    }else{
                        alert("删除失败");
                    }
                }
            })
        }

    });
    $(".goodSumaryTypeBox").click(function () {
        if (this.id === "BANJUAN"){
            if ($(this).is(":checked")){
                $(".banCheck").show();
            }else {
                $(".banCheck").hide();
            }
        }
        if (this.id === "GUAN"){
            if ($(this).is(":checked")){
                $(".guanCheck").show();
            }else {
                $(".guanCheck").hide();
            }
        }
        if (this.id === "XING"){
            if ($(this).is(":checked")){
                $(".xingCheck").show();
            }else {
                $(".xingCheck").hide();
            }
        }
        if (this.id === "LING"){
            if ($(this).is(":checked")){
                $(".lingCheck").show();
            }else {
                $(".lingCheck").hide();
            }
        }
        if (this.id === "JIAGONG"){
            if ($(this).is(":checked")){
                $(".jiaCheck").show();
            }else {
                $(".jiaCheck").hide();
            }
        }
        if (this.id === "OTHER"){
            if ($(this).is(":checked")){
                $(".otherCheck").show();
            }else {
                $(".otherCheck").hide();
            }
        }
    });

    $(".radio-block").click(function () {
        var thisRadio = $(this).find(".radio")[0];
        var icon = $(this).find("i")[0];
        if (thisRadio.checked){
            $(thisRadio).removeAttr("checked");
            $(icon).removeClass("fa-check-square-o");
            $(icon).addClass("fa-square-o");
        }else{
            $(this).closest(".user-diff").find(".radio").removeAttr("checked");
            $(this).closest(".user-diff").find("i").removeClass("fa-check-square-o");
            $(this).closest(".user-diff").find("i").addClass("fa-square-o");
            $(thisRadio).attr("checked","checked");
            $(icon).removeClass("fa-square-o");
            $(icon).addClass("fa-check-square-o");
        }
        return false;
    });
    $(".radio-block-goodFilt").click(function () {
        var thisRadio = $(this).find(".radio")[0];
        var icon = $(this).find("i")[0];
        //console.log(thisRadio);
        //下面这里不适用$(thisRadio)，不知道为什么
        if (thisRadio.checked){
            $(thisRadio).removeAttr("checked");
            $(icon).removeClass("fa-check-square-o");
            $(icon).addClass("fa-square-o");
        }else{
            console.log("到了 点击中");
            $(thisRadio).attr("checked","checked");
            $(icon).removeClass("fa-square-o");
            $(icon).addClass("fa-check-square-o");
        }
        //使用这句可以组织冒泡；
        return false;
    })
    //解决载入时,active不会触发的问题
    /*$("#add-update-good .nav-item").click(function () {
        $("#add-update-good .nav-item").each(function () {
            $(this).removeClass("show");
            $(this).removeClass("active");
            var thatId = $(this).find("a").attr("href");
            alert("thatId = " + thatId);
            $(thatId).removeClass("active");
        });
        $(this).addClass("show");
        var thisId = $(this).find("a").attr("href");
        alert("thisId = " + thisId);
        $(thisId).addClass("active");
    })*/
});
function validateThis(element) {
    $(element).on("input",function () {
        $(this).blur(function () {
            var name = $.trim($(this).val());
            if (name.length > 0){
                $.ajax({
                    data:JSON.stringify(name),
                    url:this.id,
                    type:"POST",
                    contentType:'application/json;charset=utf-8',
                    success:function (data) {
                        changeCompanyNameInput(element,!data);
                    }
                })
            }else{
                $(element).removeClass("validate-ok");
                $(element).addClass("validate-error");
                $(element).closest("form").find("#validate-btn").attr("disabled",true);
                /*$("#validate-btn").attr("disabled",true);*/
            }
        })
    })
}
function changeCompanyNameInput(element,result) {
    if(result){
        $(element).removeClass("validate-error");
        $(element).addClass("validate-ok");
        $(element).closest("form").find("#validate-btn").removeAttr("disabled");
    }else{
        /*$(element).css("background-color","red");*/
        $(element).removeClass("validate-ok");
        $(element).addClass("validate-error");
        $(element).closest("form").find("#validate-btn").attr('disabled',true);
    }
}
function testInput() {
    var pageTo =$(".pageTo").val();
    if(pageTo !== 0){
        $(".btn-pageTo").removeAttr("disabled");
    }
    if(pageTo ==="" || pageTo === 0 || pageTo.length === 0){
        $(".btn-pageTo").attr("disable","true");
    }
}
function queryCustomerByName() {
    var name = $("#in-name").val();
    var baseUrl = $(".nav-serch").attr("id");
    $.ajax({
        url:baseUrl + "company/getCustomerByName.html",
        method:"post",
        data:JSON.stringify(name),
        contentType:"application/json;charset=utf-8"
    })
}
/*function logout() {
    var url = $(".logout").attr("id");
    $.ajax({
        url:url,
        method:"get"
    });
    window.location.href="http://localhost:8080/steelsystem";
    return false;
}*/
function deleteUser() {
    var url = $(".deleteUser").attr("id");
    if(window.confirm('你确定要解雇该员工吗？')){
        $.ajax({
            url:url,
            method:"get",
            success:function () {
                window.location.reload(true);
            }
        });
    }else{
        return false;
    }

}
function passUser() {
    var url = $(".passUser").attr("id");
    if(window.confirm('你确定要雇佣该员工吗？')){
        $.ajax({
            url:url,
            method:"get",
            success:function () {
                window.location.reload(true);
            }
        });
    }else{
        return false;
    }
}
function formatDate(strdate) {
    if (strdate.length===0){
        return strdate;
    }
    var dates = strdate.split("-");
    if (dates.length > 0 && dates.length!==3){
        return false;
    }
    if (dates[0].length!==4){
        return false;
    }
    if (dates[1].length===1){
        dates[1]="0"+dates[1];
    }
    if (dates[2].length===1){
        dates[2]="0"+dates[2];
    }
    return dates.join("-");
}
function nextDateTime(value) {
    value = parseInt(value);
    var today = new Date();
    //尚未联系
    if (parseInt(value) === 0) {
        return new Date(2050, 11, 31);
    }
    //合作中
    if (value === 1) {
        today.setDate(today.getDate() + 20 + Math.random() * 10);
        return today;
    }
    //经常使用且用量大
    if (value === 2) {
        today.setDate(today.getDate() + 15 + Math.random() * 5);
        return today;
    }
    //最近有计划
    if (value === 3) {
        today.setDate(today.getDate() + 4 + Math.random() * 3);
        return today;
    }
    //可能重要
    if (value === 4) {
        today.setDate(today.getDate() + 17 * Math.random() * 8);
        return today;
    }
    //经常用到
    if (value === 5) {
        today.setDate(today.getDate() + 7 * Math.random() * 8);
        return today;
    }
    //用到
    if (value === 6) {
        today.setDate(today.getDate() + 20 + Math.random() * 10);
        return today;
    }
    //可能用到或间歇性用到
    if (value === 7 || value === 8 || value === 9) {
        today.setDate(today.getDate() + 30 + Math.random() * 10);
        return today;
    }
    //或者联系失败或者资料不完整,未联系到负责人，电话错误
    if (value >= 10 && value<=13) {
        return new Date(2040,11,31);
    }
    //跟别人合作，用量很小或不用
    if (value===14 || value===15 || value === 20){
        return new Date(2045,11,31);
    }
    //根本不用，暂不联系，停业，倒闭
    if (value>=16 && value<=19){
        return new Date(today.setFullYear(2050,11,31));
    }
}