$(document).ready(function () {
    /*$("#plain-texture,#plain-unit,#plain-in-unit,#plain-out-unit").change(function () {
        plainEvent();
    });
    $("#plain-texture,#plain-thick,#plain-width,#plain-length,#plain-amount,#plain-in-price,#plain-out-price,#plain-in-money,#plain-out-money,#plain-weight").blur(function (){
        plainEvent();
    });
*/
    orderInputRequired();
    $("#plain-thick").blur(function () {
        if (Number($("#plain-realThick").val()) === 0){
            $("#plain-realThick").val($("#plain-thick").val());
        }
    });
    $("#plain-thick,#plain-width,#plain-length,#plain-amount").blur(function (){
        setPlainWeight();setPlainInMoney();setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"))
    });
    $("#plain-texture,#plain-unit").change(function (){
        setPlainWeight();setPlainInMoney();setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"))
    });
    $("#plain-weight,#plain-in-price").blur(function (){
        setPlainInMoney();setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"))
    });
    $("#plain-in-unit").change(function (){
        setPlainInMoney();setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"))
    });
    $("#plain-in-money,#plain-out-price").blur(function () {
        setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"));
    });
    $("#plain-out-unit").change(function () {
        setPlainOutMoney();validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"));
    });
    $("#plain-out-money").blur(function () {
        validateBan();setProfit($("#plain-in-money"),$("#plain-out-money"),$("#plain-profit"));
    });
    $("#guan-realThick,#guan-length,#guan-amount").blur(function () {
        setGuanWeight();setGuanInMoney();setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-texture,#guan-unit").change(function () {
        setGuanWeight();setGuanInMoney();setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-weight,#guan-in-price").blur(function () {
        setGuanInMoney();setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-in-unit").change(function () {
        setGuanInMoney();setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-in-money,#guan-out-price").blur(function () {
        setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-out-unit").blur(function () {
        setGuanOutMoney();validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    $("#guan-out-money").blur(function () {
        validateGuan();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
    });
    //必须确保spec填写正确；
    $("#guan-spec").blur(function () {
        var spec = $("#guan-spec");
        if (($(spec).val().indexOf("*") > 0 && $(spec).val().lastIndexOf("*") < $(spec).val().length -1) ||
            ($(spec).val().indexOf("×") > 0 && $(spec).val().lastIndexOf("×")< $(spec).val().length -1)){
            $(spec).removeClass("validate-error");
            //输入格式正确，则立即对隐藏的单元格赋值；
            var specs = $(spec).val().split("*");
            var diameter = $("#guan-diameter");
            var diameter2=$("#guan-diameter2");
            var thick=$("#guan-thick");
            var realThick=$("#guan-realThick");
            if (specs.length===2){
                var spe1 = parseFloat(specs[0]);
                var spe2=parseFloat(specs[1]);
                $(diameter2).val(0);
                if (spe1>spe2){
                    $(diameter).val(spe1);
                    $(thick).val(spe2);
                } else {
                    $(diameter).val(spe2);
                    $(diameter).val(spe1);
                }
            }
            if (specs.length ===3){
                var sp1=parseFloat(specs[0]);
                var sp2=parseFloat(specs[1]);
                var sp3=parseFloat(specs[2]);
                if (sp1 > sp3 && sp2 > sp3){
                    $(thick).val(sp3);
                    $(diameter).val(sp1);
                    $(diameter2).val(sp2);
                }else if(sp1 < sp2 && sp1 < sp3){
                    $(thick).val(sp1);
                    $(diameter).val(sp2);
                    $(diameter2).val(sp3);
                }else if ( sp2 < sp1 && sp2 < sp3){
                    $(thick).val(sp2);$(diameter).val(sp3);$(diameter2).val(sp1);
                }
            }
            $(realThick).val($(thick).val());
        }else{
            $(spec).addClass("validate-error");

        }
        setGuanWeight();setGuanInMoney();setGuanOutMoney();setProfit($("#guan-in-money"),$("#guan-out-money"),$("#guan-profit"));
        validateGuan();
    });

    $("#xing-xingtype").change(function () {
        var xingType = $("#xing-xingtype");
        var xingSpec=$("#xing-spec");
        var xingUnit = $("#xing-unit");
        if ($(xingType).val() === "槽钢"){
            $(xingSpec).attr("list","cao-list");
        }else if ($(xingType).val() ==="工字钢") {
            $(xingSpec).attr("list","gong-list");
        }else{
            $(xingSpec).removeAttr("list");
        }
        if ($(xingType).val() === "圆饼" || $(xingType).val() ==="圆环"){
            $(xingUnit).val("只");
            $(xingUnit).attr("disabled",true);
        }else{
            $(xingUnit).attr("disabled",false);
        }
        //动态设置placeholder失败
        /*
        //槽钢,角钢,方钢,冷拉扁钢,热轧扁钢,剪板扁钢,毛圆,光元,圆饼,零割,六角棒,八角棒,工字钢,圆环;
        if ($(xingSpec).val() === "槽钢" || $(xingSpec).val() === "工字钢"){setPlaceHolder("#xing-spec","12#");}
        else if ($(xingSpec).val() === "方钢"){setPlaceHolder("#xing-spec","25*38");}
        else if ($(xingSpec).val() === "冷拉扁钢"|| $(xingSpec).val() === "热轧扁钢" || $(xingSpec).val() === "剪板扁钢"
            || $(xingSpec).val() === "圆饼"){
            setPlaceHolder("#xing-spec","5*50");
        }
        else if ($(xingSpec).val() === "毛圆" || $(xingSpec).val() === "光元" || $(xingSpec).val() === "六角棒" || $(xingSpec).val() === "八角棒"){
            setPlaceHolder("#xing-spec","12#");
        }
        else if ($(xingSpec).val() === "角钢" || $(xingSpec).val() === "圆环"){setPlaceHolder("#xing-spec","5*250*250")}
        */
        $(xingSpec).val(null);
        validateXing();
    });
    //用于校验规格格式是否正确
    $("#xing-spec").blur(function () {
        $(this).removeClass("validate-error");
        var xingSpec = $("#xing-spec");
        var xingType = $("#xing-xingtype");
        var caoList=$("#cao-list option");
        var gongList=$("#gong-list option");
        if ($(xingType).val() === "槽钢"){
            var flag1 = false;
            $(caoList).each(function () {
                if ($(this).attr("value") === $(xingSpec).val()){
                    flag1 = true;
                    return 0;
                }
            });
            if (!flag1){
                errorXingSpec("槽钢");
            }
        }else if ($(xingType).val() ==="工字钢") {
            var flag2 = false;
            $(gongList).each(function () {
                if ($(this).attr("value") === $(xingSpec).val()){
                    flag2 = true;
                    return 0;
                }
            });
            if (!flag2) {
                errorXingSpec("工字钢");
            }
        }else if ($(xingType).val() === "角钢" || $(xingType).val() === "圆环") {
            var flag3 = false;
            var specStr1 = $(xingSpec).val();
            if ((specStr1.indexOf("*") > 0 && specStr1.lastIndexOf("*") < specStr1.length -1) ||
                (specStr1.indexOf("×") > 0 && specStr1.lastIndexOf("×")< specStr1.length -1)){
                var specs1 =  specStr1.indexOf("*") > 0 ? specStr1.split("*") : specStr1.split("×");
                //alert(specs1.length);
                if (specs1.length ===3){
                    flag3 = true;
                 }
            }
            if (!flag3) {
                errorXingSpec("格式错误");
            }
        }else if ($(xingType).val()==="冷拉扁钢" || $(xingType).val() === "热轧扁钢" || $(xingType).val() ==="剪板扁钢"
            || $(xingType).val() ==="方钢"
            || $(xingType).val() === "圆饼"){
            var flag4 = false;
            var specStr2 = $(xingSpec).val();
            if ((specStr2.indexOf("*") > 0 &&  specStr2.indexOf("*") < specStr2.length -1) ||
                (specStr2.indexOf("×") > 0 &&  specStr2.indexOf("×") < specStr2.length -1)){
                var specs2 = specStr2.indexOf("*") > 0 ? specStr2.split("*") : specStr2.split("×");
                if (specs2.length ===2){
                    flag4 = true;
                }
            }
            if (!flag4) {
                errorXingSpec("格式错误");
            }
        }else if ($(xingType).val() === "毛圆" || $(xingType).val() === "光元"
            || $(xingType).val()==="六角棒" || $(xingType).val()==="八角棒") {
            var flag5= false;
            if (isNumber($(xingSpec).val())){
                flag5=true;
            }else{
                errorXingSpec("圆钢");
            }
        }
        setXingWeight();setXingInMoney();setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));
        validateXing();
    });
    $("#xing-amount").blur(function () {
        setXingWeight();setXingInMoney();setXingOutMoney();validateXing();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));
    });
    $("#xing-texture,#xing-unit").change(function () {
        setXingWeight();setXingInMoney();setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });
    $("#xing-weight,#xing-in-price").blur(function () {
        setXingInMoney();setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });
    $("#xing-in-unit").change(function () {
        setXingInMoney();setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });
    $("#xing-in-money,#xing-out-price").blur(function () {
        setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });
    $("#xing-out-unit").change(function () {
        setXingOutMoney();setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });
    $("#xing-out-money").blur(function () {
        setProfit($("#xing-in-money"),$("#xing-out-money"),$("#xing-profit"));validateXing();
    });

    /*$("#xing-spec,#xing-amount,#xing-weight,#xing-in-price,#xing-out-price,#xing-in-money,#xing-out-money").blur(function () {
        xingEvent();
    });*/
    /*$("#xing-texture,#xing-in-unit,#xing-out-unit,#xing-unit").change(function () {
        xingEvent();
    });*/
    $("#ling-amount,#ling-in-price,#ling-out-price,#ling-in-money,#ling-out-money").blur(function () {
        lingEvent();validateLing();
    });
    $("#other-amount,#other-in-price,#other-out-price,#other-in-money,#other-out-money").blur(function () {
        otherEvent();validateOther();
    });
    $("#jiagong-amount,#jiagong-in-price,#jiagong-out-price,#jiagong-in-money,#jiagong-out-money").blur(function () {
        jiaGongEvent();validateJia();
    });
    $(".go-up-supplier").click(function (){
        showGoodTypesCheckBox();
        showTexturesCheckBox();
        $(".update-hidden").hide();
        $(".hidden-supplier").show();
    });
    $(".up-supplier").click(function () {
        var url = this.id;
        var supplierName = $("#up-suppliername").val(),
            id=$("#supplier-up-id").val(),simpleName=$("#up-simpleName").val(),
            workAddress=$("#up-workAddress").val(),
            wallAddress=$("#up-wallAddress").val(),
            kaiPingAddress=$("#up-kaiPingAddress").val(),
            others = $("#up-others").val();
            console.log(others);
        var goodType=[],texture=[];
        $.each($("input[name=goodType]:checked"),function () {
            if (this.checked) {
                goodType[goodType.length] = $(this).val();
            }
        });
        $.each($("input[name=textureTypes]:checked"),function () {
            if (this.checked){
                texture[texture.length]=$(this).val();
            }
        });
        /*alert(goodType);*/
        var data = {
            supplierId:id,
            supplierName:supplierName,
            simpleName:simpleName,
            workAddress:workAddress,
            wallAddress:wallAddress,
            kaiPingAddress:kaiPingAddress,
            goodType:goodType,
            textures:texture,
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
});
function errorXingSpec(str) {
    var xingSpec = $("#xing-spec");
    $(xingSpec).addClass("validate-error");
    /*alert(str + "输入格式错误");*/
    /*$(xingSpec).focus();*/
}
function guanWeight(midu,thick,diameter,length) {
    return (3.1415926 * midu *(diameter - thick) * thick * length/1000/1000) * 1.03;
}
function fangGuanWeight(midu,thick,diameter1,diameter2,length) {
    //在文本框中取的数值，原始是字符串，只有直接与数字进行运算，才能强制转换成数字，而两个字符串相加是可以直接计算的，所以会出现错误
    var width = (diameter1 * 2 + diameter2 * 2) /1000;
    return (thick * width * length * midu/1000);
}
function setGuanWeight() {
    var thick=$("#guan-thick");
    var realThick=$("#guan-realThick");
    var diameter = $("#guan-diameter");
    var diameter2=$("#guan-diameter2");
    var length=$("#guan-length");
    var midu = parseFloat($("#guan-texture").find("option:selected").attr("id"));
    var amount=$("#guan-amount");
    var weight = $("#guan-weight");
    if ($(thick).val().trim()==="") $(diameter).val(0);
    if ($(diameter).val().trim()==="") $(diameter).val(0);
    if ($(diameter2).val().trim()==="") $(diameter2).val(0);
    if ($(length).val().trim()==="") $(length).val(0);
    if ($(amount).val().trim()==="") $(amount).val(0);
    if ($(weight).val().trim()==="") $(weight).val(0);

    var unit=$("#guan-unit");
    if (parseFloat($(realThick).val()) ===0){
        $(realThick).val($(thick).val());
    }
    if ($(unit).val()==="Kg"){
        $(weight).val($(amount).val());
    }
    var guanZhong=0;
    if ($(diameter).val() !==0) {
        var houdu ;
        if ($(realThick).val() !==0){
            houdu = $(realThick).val();
        } else if ($(thick).val() !==0) {
            houdu=$(thick).val();
        }
        if (houdu !==0){
            if (parseFloat($(length).val()) ===0 && ($(unit).val()==="支" || $(unit).val()==="只")){
                if ($(weight).val() ===0){
                    $(weight).addClass("validate-error");
                }else{
                    $(weight).removeClass("validate-error");
                    $(weight).val($(weight).val());
                }
            }else if ($(length).val() !==0 && ($(unit).val()==="支" || $(unit).val()==="只")){
                if (parseInt($(diameter2).val()) === 0){
                    guanZhong = guanWeight(midu,parseFloat($(realThick).val()),parseFloat($(diameter).val()),
                        $(length).val()*$(amount).val());
                }else{
                    guanZhong = fangGuanWeight(midu,parseFloat($(realThick).val()),parseFloat($(diameter).val()),
                        parseFloat($(diameter2).val()),$(length).val()*$(amount).val());
                }
            }else if ($(unit).val()==="m"){
                if (parseFloat($(diameter2).val()) ===0){
                    guanZhong = guanWeight(midu,parseFloat($(realThick).val()),parseFloat($(diameter).val()),$(amount).val()*1000);
                }else{
                    guanZhong = fangGuanWeight(midu,parseFloat($(realThick).val()),
                        parseFloat($(diameter).val()),parseFloat($(diameter2).val()),$(amount).val()*1000);
                }
            }else if($(unit).val()==="mm"){
                if (parseFloat($(diameter2).val()) ===0){
                    guanZhong = guanWeight(midu,parseFloat($(realThick).val()),parseFloat($(diameter).val()),$(amount).val());
                }else{
                    guanZhong = fangGuanWeight(midu,parseFloat($(realThick).val()),
                        parseFloat($(diameter).val()),parseFloat($(diameter2).val()),$(amount).val());
                }
            }
        }
    }
    $(weight).val(guanZhong.toFixed(2));
}
function setGuanInMoney() {
    var inPrice = $("#guan-in-price");
    var inUnit = $("#guan-in-unit");
    var inMoney=$("#guan-in-money");
    var amount=$("#guan-amount");
    var weight = $("#guan-weight");
    var unit=$("#guan-unit");
    if ($(inPrice).val().trim()==="") $(inPrice).val(0);
    if ($(inMoney).val().trim()==="") $(inMoney).val(0);

    if ($(inPrice).val() !==0  ){
        if ($(weight).val() !==0 && $(inUnit).val() ==="Kg" ){
            var inMon = $(inPrice).val() * $(weight).val();
            $(inMoney).val(inMon.toFixed(2));
        }else if ($(unit).val()=== $(inUnit).val()) {
            var inMon1 = $(inPrice).val() * $(amount).val();
            $(inMoney).val(inMon1.toFixed(2));
        }
    }
}
function setGuanOutMoney() {
    var outPrice=$("#guan-out-price");
    var outUnit=$("#guan-out-unit");
    var amount=$("#guan-amount");
    var weight = $("#guan-weight");
    var unit=$("#guan-unit");
    var outMoney = $("#guan-out-money");

    if ($(outPrice).val().trim()==="") $(outPrice).val(0);
    if ($(amount).val().trim()==="") $(amount).val(0);
    if ($(weight).val().trim()==="") $(weight).val(0);

    if ($(outPrice).val() !==0 ){
        if ($(unit).val()==="m" && $(outUnit).val() ==="m" && $(amount).val() !== 0 ){
            var outM = $(outPrice).val() * $(amount).val();
            $(outMoney).val(outM.toFixed(2));
        }else if ($(weight).val() !== 0 && $(outUnit).val() ==="Kg"){
            //alert($(outPrice).val());
            //测试可以看得出来，传进来的数据是不会等于0的,0.0也不行
            var outMon = $(outPrice).val() * $(weight).val();
            $(outMoney).val(outMon.toFixed(2));
        }else if ($(unit).val()=== $(outUnit).val()) {
            var outMon2 = $(outPrice).val() * $(amount).val();
            $(outMoney).val(outMon2.toFixed(2));
        }
    }

}
function setProfit(inMoney,outMoney,profit,notTaxed) {
    //以下对毛利润进行计算，并对亏损进行提醒
    var pro=0;
    if (notTaxed){
        pro = $(outMoney).val()*1.13 - $(inMoney).val()*1.13;
    }else{
        pro = $(outMoney).val() - $(inMoney).val();
    }
    $(profit).val(pro.toFixed(2));
    if ($(profit).val() < 0){
        $(profit).addClass("validate-error");
    }else{
        $(profit).removeClass("validate-error");
    }
}

function setPlainWeight() {
    var unit = $("#plain-unit");
    var thick = $("#plain-thick");
    var width = $("#plain-width");
    var length =$("#plain-length");
    var amount = $("#plain-amount");
    var weight = $("#plain-weight");
    var inPrice = $("#plain-in-price");
    var outPrice = $("#plain-out-price");
    if ((inPrice).val().trim() === "") $(inPrice).val(0);
    if ((outPrice).val().trim()==="") $(outPrice).val(0);
    if ($(thick).val().trim()==="") $(thick).val(0);
    if ($(width).val().trim()==="") $(width).val(0);
    if ($(length).val().trim()==="") $(length).val(0);
    if ($(amount).val().trim()==="") $(amount).val(0);
    if ($(weight).val().trim()==="") $(weight).val(0);
    if ($(unit).val() === "Kg"){
        $(weight).val($(amount).val());
    }
    if ($(thick).val()!==0 && $(width).val() !==0 && $(length).val() !==0 && $(amount).val() !==0){
        var midu = parseFloat($("#plain-texture").find("option:selected").attr("id"));
        if($(unit).val() === "张"){
            var realThick = $(thick).val() * $(width).val()/1000 * $(length).val()/1000 * midu  * $(amount).val();
            $(weight).val(realThick.toFixed(2));
        }
    }
}
function setPlainInMoney() {
    var unit = $("#plain-unit");
    var amount = $("#plain-amount");
    var weight = $("#plain-weight");
    var inPrice = $("#plain-in-price");
    var inMoney = $("#plain-in-money");
    var inUnit = $("#plain-in-unit");
    var outPrice = $("#plain-out-price");
    if ((inPrice).val().trim() === "") $(inPrice).val(0);
    if ((outPrice).val().trim()==="") $(outPrice).val(0);
    if ($(inPrice).val() !== 0){
        if ($(inUnit).val() === "张" && $(unit).val() === "张"){
            var realInMoney = $(inPrice).val() * $(amount).val();
            $(inMoney).val(realInMoney.toFixed(2));
        }else if ($(inUnit).val() === "Kg") {
            var realInMoney2 = $(inPrice).val() * $(weight).val();
            $(inMoney).val(realInMoney2.toFixed(2)) ;
        }
    }
}
function setPlainOutMoney() {
    var unit = $("#plain-unit");
    var amount = $("#plain-amount");
    var weight = $("#plain-weight");
    var outPrice = $("#plain-out-price");
    var outMoney = $("#plain-out-money");
    var outUnit = $("#plain-out-unit");
    var inPrice = $("#plain-in-price");
    if ((inPrice).val().trim() === "") $(inPrice).val(0);
    if ((outPrice).val().trim()==="") $(outPrice).val(0);
    if ($(outPrice).val() !== 0){
        if ($(outUnit).val() === "张" && $(unit).val() === "张"){
            var realOutMoney = $(outPrice).val() * $(amount).val();
            $(outMoney).val(realOutMoney.toFixed(2));
        }else if ($(outUnit).val() === "Kg") {
            var realOutMoney2 = $(outPrice).val() * $(weight).val();
            $(outMoney).val(realOutMoney2.toFixed(2));
        }
    }
}

function setXingWeight() {
    var xingType = $("#xing-xingtype");
    var xingSpec = $("#xing-spec");
    var xingAmount = $("#xing-amount");
    var xingUnit = $("#xing-unit");
    var xingWeight=$("#xing-weight");
    var caoList=$("#cao-list option");
    var gongList=$("#gong-list option");
    if ($(xingAmount).val().trim()==="") $(xingAmount).val(0);
    if ($(xingWeight).val().trim()==="") $(xingWeight).val(0);
    var xingZhong = 0;
    if ($(xingUnit).val()==="Kg"){
        $(xingWeight).val($(xingAmount).val());
    }
    if ($(xingSpec).val()!=="" && $(xingAmount).val() !=="" && ($(xingUnit).val() === "m" || $(xingUnit).val() === "mm" || $(xingUnit).val() === "只" )){
        var midu = parseFloat($("#xing-texture").find("option:selected").attr("id"));
        var lenMount = parseInt($(xingAmount).val());
        if ($(xingUnit).val() === "mm") {
            lenMount = lenMount /1000;
        }
        if ($(xingType).val() === "槽钢"){
            var caoWeight=0;
            $(caoList).each(function () {
                if ($(this).attr("value") === $(xingSpec).val()){
                    caoWeight= parseFloat(this.id);
                }
            });
            xingZhong = (caoWeight * midu / 7.93 * lenMount);
        } else if ($(xingType).val() ==="工字钢") {
            var gongWeight=0;
            $(gongList).each(function () {
                if ($(this).attr("value") === $(xingSpec).val()){
                    gongWeight=parseFloat(this.id);
                }
            });
            xingZhong = (gongWeight * midu / 7.93 * lenMount);
        } else if ($(xingType).val() === "角钢") {
            var specs = $(xingSpec).val().indexOf("*") > 0 ? $(xingSpec).val().split("*") :
                $(xingSpec).val().split("×");
            specs = floatArray(specs);
            var arrsInt = specs.sort(function (a, b) {return a - b;});
            xingZhong = getJiaoGangWeight(arrsInt[0],arrsInt[1],arrsInt[2],midu) * lenMount;
            //alert(xingZhong);
        }else if($(xingType).val() === "圆环"){
            var specs = $(xingSpec).val().indexOf("*") > 0 ? $(xingSpec).val().split("*") : $(xingSpec).val().split("×");
            specs = floatArray(specs);
            var arrsInt = specs.sort(function (a, b) {return a - b;});
            xingZhong = getYuanGangWeight(arrsInt[2],midu) * arrsInt[0] / 1000 * lenMount
                - getYuanGangWeight(arrsInt[1],midu) * arrsInt[0] / 1000 * lenMount;
        }
        else if ($(xingType).val()==="冷拉扁钢" || $(xingType).val() === "热轧扁钢" || $(xingType).val()==="剪板扁钢" || $(xingType).val()==="方钢"){
            var bianspecs = $(xingSpec).val().indexOf("*") > 0 ? $(xingSpec).val().split("*") : $(xingSpec).val().split("×");
            var arrsInt = bianspecs.sort(function (a, b) {return a - b;});
            xingZhong = arrsInt[0] * arrsInt[1] * midu /1000 * lenMount;
        }else if($(xingType).val()==="圆饼"){
            var bingspecs = $(xingSpec).val().indexOf("*") > 0 ?
                $(xingSpec).val().split("*") : $(xingSpec).val().split("×");
            var arrsInt = bingspecs.sort(function (a, b) {return a - b;});
            xingZhong = getYuanGangWeight(arrsInt[1],midu) * arrsInt[0] / 1000 * lenMount;
        } else if ($(xingType).val() === "毛圆" || $(xingType).val() === "光元") {
            var zhijing = parseFloat($(xingSpec).val());
            xingZhong = getYuanGangWeight(zhijing,midu) * lenMount;
        } else if ($(xingType).val()==="六角棒"){
            var s = parseFloat($(xingSpec).val());
            xingZhong = getLiuJiaoWeight(s,midu) * lenMount;
        }else if ($(xingType).val()==="八角棒"){
            var s2 = parseFloat($(xingSpec).val());
            xingZhong = getBaJiaoWeight(s2,midu) * lenMount;
        }
        $(xingWeight).val(xingZhong.toFixed(2));
    }
}
function setXingInMoney() {
    var xingAmount = $("#xing-amount");
    var xingUnit = $("#xing-unit");
    var xingWeight=$("#xing-weight");
    var xingInPrice=$("#xing-in-price");
    var xingInUnit=$("#xing-in-unit");
    var xingInMoney=$("#xing-in-money");
    if ($(xingAmount).val().trim()==="") $(xingAmount).val(0);
    if ($(xingWeight).val().trim()==="") $(xingWeight).val(0);
    if ($(xingInPrice).val().trim()==="") $(xingInPrice).val(0);
    if ($(xingInMoney).val().trim()==="") $(xingInMoney).val(0);


    if ($(xingInPrice).val() !==0 ){
        if ($(xingWeight).val() !==0 && $(xingInUnit).val() ==="Kg" ){
            var inMon = $(xingInPrice).val() * $(xingWeight).val();
            $(xingInMoney).val(inMon.toFixed(2));
        }else if ($(xingUnit).val() === $(xingInUnit).val()){
            var inMon1 = $(xingAmount).val() * $(xingInPrice).val();
            $(xingInMoney).val(inMon1.toFixed(2));
        }
    }
}
function setXingOutMoney() {
    var xingAmount = $("#xing-amount");
    var xingUnit = $("#xing-unit");
    var xingWeight=$("#xing-weight");
    var xingInMoney=$("#xing-in-money");
    var xingOutPrice=$("#xing-out-price");
    var xingOutUnit=$("#xing-out-unit");
    var xingOutMoney=$("#xing-out-money");
    if ($(xingAmount).val().trim()==="") $(xingAmount).val(0);
    if ($(xingWeight).val().trim()==="") $(xingWeight).val(0);
    if ($(xingOutPrice).val().trim()==="") $(xingOutPrice).val(0);
    if ($(xingOutMoney).val().trim()==="") $(xingOutMoney).val(0);

    if ($(xingOutPrice).val() !==0 ){
        if ($(xingWeight).val()!==0 && $(xingOutUnit).val() ==="Kg"){
            var outMon = $(xingOutPrice).val() * $(xingWeight).val();
            $(xingOutMoney).val(outMon.toFixed(2));
        }else if ($(xingUnit).val() === $(xingOutUnit).val()){
            var outMon2 = $(xingAmount).val() * $(xingOutPrice).val();
            $(xingOutMoney).val(outMon2.toFixed(2));
        }
    }
}

function lingEvent() {
    var amount = $("#ling-amount");
        if ($(amount).val().trim()==="") $(amount).val(0);
    var inPrice = $("#ling-in-price");
        if ($(inPrice).val().trim()==="") $(inPrice).val(0);
    var inMoney = $("#ling-in-money");
        if ($(inMoney).val().trim()==="") $(inMoney).val(0);
    var outPrice = $("#ling-out-price");
        if ($(outPrice).val().trim()==="") $(outPrice).val(0);
    var outMoney = $("#ling-out-money");
        if ($(outMoney).val().trim()==="") $(outMoney).val(0);
    var profit = $("#ling-profit");
        if ($(profit).val().trim()==="") $(profit).val(0);
    if ($(inPrice).val() !== 0){
        var realInMoney = $(inPrice).val() * $(amount).val();
        $(inMoney).val(realInMoney.toFixed(2));
    }
    if ($(outPrice).val() !== 0){
        var realOutMoney = $(outPrice).val() * $(amount).val();
        $(outMoney).val(realOutMoney.toFixed(2));
        var pro = $(outMoney).val() - $(inMoney).val();
        $(profit).val(pro.toFixed(2));
    }
    if ($(profit).val() < 0){
        $(profit).addClass("validate-error");
    }else{
        $(profit).removeClass("validate-error");
    }
}
function otherEvent() {
    var amount = $("#other-amount");
        if ($(amount).val().trim()==="") $(amount).val(0);
    var inPrice = $("#other-in-price");
        if ($(inPrice).val().trim()==="") $(inPrice).val(0);
    var inMoney = $("#other-in-money");
        if ($(inMoney).val().trim()==="") $(inMoney).val(0);
    var outPrice = $("#other-out-price");
        if ($(outPrice).val().trim()==="") $(outPrice).val(0);
    var outMoney = $("#other-out-money");
        if ($(outMoney).val().trim()==="") $(outMoney).val(0);
    var profit = $("#other-profit");
        if ($(profit).val().trim()==="") $(profit).val(0);
    if ($(inPrice).val() !== 0){
        var realInMoney = $(inPrice).val() * $(amount).val();
        $(inMoney).val(realInMoney.toFixed(2));
    }
    if ($(outPrice).val() !== 0){
        var realOutMoney = $(outPrice).val() * $(amount).val();
        $(outMoney).val(realOutMoney.toFixed(2));
        /*var pro = $(outMoney).val() - $(inMoney).val();
        $(profit).val(pro.toFixed(2));*/
    }
    if ($("#otherServiceType").val() === "物流"){
        alert("测试");
        setProfit($("#other-in-money"),$("#other-out-money"),$("#other-profit"),true);
    }else {
        setProfit($("#other-in-money"),$("#other-out-money"),$("#other-profit"));
    }
    /*if ($(profit).val() < 0){
        $(profit).addClass("validate-error");
    }else{
        $(profit).removeClass("validate-error");
    }*/
}
function jiaGongEvent() {
    var amount = $("#jiagong-amount");if ($(amount).val().trim()==="") $(amount).val(0);
    var inPrice = $("#jiagong-in-price");if ($(inPrice).val().trim()==="") $(inPrice).val(0);
    var inMoney = $("#jiagong-in-money");if ($(inMoney).val().trim()==="") $(inMoney).val(0);
    var outPrice = $("#jiagong-out-price");if ($(outPrice).val().trim()==="") $(outPrice).val(0);
    var outMoney = $("#jiagong-out-money");if ($(outMoney).val().trim()==="") $(outMoney).val(0);
    var profit = $("#jiagong-profit");
        if ($(profit).val().trim()==="") $(profit).val(0);
    if ($(inPrice).val() !== 0){
        var realInMoney = $(inPrice).val() * $(amount).val();
        $(inMoney).val(realInMoney.toFixed(2));
    }
    if ($(outPrice).val() !== 0){
        var realOutMoney = $(outPrice).val() * $(amount).val();
        $(outMoney).val(realOutMoney.toFixed(2));
        var pro = $(outMoney).val() - $(inMoney).val();
        $(profit).val(pro.toFixed(2));
    }
    if ($(profit).val() < 0){
        $(profit).addClass("validate-error");
    }else{
        $(profit).removeClass("validate-error");
    }
}
//长度以毫米为单位，计算1000毫米的重量
function getYuanGangWeight(diameter,midu) {
    return 0.7854 * diameter * diameter /1000 * midu;
}
function  getJiaoGangWeight(thick,width1,width2,midu) {
    return thick * (width1 + width2 - thick) * midu /1000;
}
function getLiuJiaoWeight(s,midu) {
    return s * s * midu * 0.8660 / 1000;
}
function getBaJiaoWeight(s,midu) {
    return s * s * midu * 0.8197 / 1000;
}
function isNumber(value) { //验证是否为数字
     var patrn = /^(-)?\d+(\.\d+)?$/;
     if (patrn.exec(value) == null || value.length === 0) {
         return false
     } else {
         return true
     }
}
function showGoodTypesCheckBox() {
    var checkeds = $("#goodDetailsHidden").val();
    $(".otherCheck").hide();$(".jiaCheck").hide();
    $(".lingCheck").hide();$(".xingCheck").hide();$(".guanCheck");$(".banCheck").hide();
    checkeds = checkeds.replace("[","");
    checkeds = checkeds.replace("]","");
    var checkArray = checkeds.split(",");
    var checkBoxAll = $("input[name='goodType']");
    for (var i=0;i<checkArray.length;i++){
        $.each(checkBoxAll,function (j,checkbox) {
            var checkValue=$(checkbox).val();
            if (checkArray[i].trim()===checkValue.trim()){
                $(checkbox).attr("checked","checked");
                $(checkbox).closest(".goodTypeItem").show();
            }
        })
    }
}
function showTexturesCheckBox() {
    //获取后台返回的数据
    var checkeds = $("#textureHidden").val();
    //剔除多余的[]
    checkeds = checkeds.replace("[","");
    checkeds = checkeds.replace("]","");
    var checkArray = checkeds.split(",");
    var checkBoxAll = $("input[name='textureTypes']");
    for (var i=0;i<checkArray.length;i++){
        $.each(checkBoxAll,function (j,checkbox) {
            var checkValue=$(checkbox).attr("id");
            if (checkArray[i].toString().trim()===checkValue.toString().trim()){
                $(checkbox).attr("checked",true);
                /*alert($(checkbox).attr("checked"));*/
            }
        })
    }
}
function validateBan() {
    inputRequiredValidate($("#plain-thick"));
    inputRequiredValidate($("#plain-width"));
    inputRequiredValidate($("#plain-amount"));
    if ($("#plain-thick").val()!==0 && $("#plain-width").val() !== 0 && $("#plain-amount").val() !== 0){
        $("#add-ban-btn").removeAttr("disabled");
    }else{
        $("#add-ban-btn").attr("disabled",true);
    }
}
function validateGuan() {
    inputRequiredValidate($("#guan-spec"));
    inputRequiredValidate($("#guan-amount"));
    if ($("#guan-spec").val().length !== 0 && $("#guan-amount").val()!==0){
        $("#add-guan-btn").removeAttr("disabled");
    }else{
        $("#add-guan-btn").attr("disabled",true);
    }
}
function validateXing() {
    inputRequiredValidate($("#xing-spec"));
    inputRequiredValidate($("#xing-amount"));
    if ($("#xing-spec").val().length !==0 && $("#xing-amount").val()!==0){
        $("#add-xing-btn").removeAttr("disabled");
    } else{
        $("#add-xing-btn").attr("disabled",true);
    }
}
function validateLing() {
    inputRequiredValidate($("#ling-spec"));
    inputRequiredValidate($("#ling-amount"));
    if ($("#ling-spec").val().length !==0 && $("#ling-amount").val() !==0){
        $("#add-ling-btn").removeAttr("disabled");
    } else{
        $("#add-ling-btn").attr("disabled",true);
    }
}
function validateJia() {
    inputRequiredValidate($("#jiagong-amount"));
    if ($("#jiagong-amount").val()!==0){
        $("#add-jia-btn").removeAttr("disabled");
    } else{
        $("#add-jia-btn").attr("disabled",true);
    }
}
function validateOther() {
    inputRequiredValidate($("#other-amount"));
    if ($("#other-amount").val()!==0){
        $("#add-others-btn").removeAttr("disabled");
    } else{
        $("#add-others-btn").attr("disabled",true);
    }
}
function orderInputRequired() {
    inputRequiredValidate($("#plain-thick"));
    inputRequiredValidate($("#plain-width"));
    inputRequiredValidate($("#plain-amount"));
    inputRequiredValidate($("#guan-spec"));
    inputRequiredValidate($("#guan-amount"));
    inputRequiredValidate($("#xing-spec"));
    inputRequiredValidate($("#xing-amount"));
    inputRequiredValidate($("#ling-spec"));
    inputRequiredValidate($("#ling-amount"));
    inputRequiredValidate($("#jiagong-amount"));
    inputRequiredValidate($("#other-amount"));
    /*$("#plain-thick，#plain-width，#plain-amount，#guan-spec，#guan-amount，#xing-spec，#xing-amount，#ling-spec，#ling-amount，#jiagong-amount，#other-amount").forEach(function () {
        inputRequiredValidate(this);
    });*/
}
function inputRequiredValidate(select) {
    if ($(select).val() !== undefined){
        if(Number($(select).val()) ===0 || $(select).val().length === 0){
            $(select).addClass("required");
        }else{
            $(select).removeClass("required");
        }
    }
}
function setPlaceHolder(ele,str) {
    $(ele).attr("placeholder",str);
}
function floatArray(array) {
    var newArray = [];
    for (i = 0;i<array.length;i++){
        newArray[i] = parseFloat(array[i]);
    }
    /*for (var a in array){
        newArray.add(parseFloat(a));
    }*/
    return newArray;
}
/*
function inputRequiredValidateStr(select) {
    if($(select).val().length ===0){
        $(select).addClass("required");
    }else{
        $(select).removeClass("required");
    }
}*/
