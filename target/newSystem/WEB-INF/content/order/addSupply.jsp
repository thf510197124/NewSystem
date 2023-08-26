<%@ page import="com.taiquan.domain.customerEnums.CustomerType" %>
<%@ page import="com.taiquan.domain.customerEnums.CapitalType" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.order.enums.*" %>
<%@ page import="com.taiquan.domain.order.enums.goodDetailType.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basepath",basePath);
%>

<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>客户管理系统</title>
    <link rel="stylesheet" href="${basepath}css/bootstrap.css">
    <link rel="stylesheet" href="${basepath}css/main.css">
    <link rel="stylesheet" href="${basepath}css/bootstrap_datetimepicker.css">
    <link rel="stylesheet" href="${basepath}css/datepicker.css">

    <style>
        .banCheck,.guanCheck,.xingCheck,.lingCheck,.jiaCheck,.otherCheck{
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">添加供应商</h3>
    </div>
    <hr/>
    <form:form class="" action="addSupplier.html" method="post" modelAttribute="supplier">
        <div class="row">
            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                <div class="input-group">
                    <span class="input-group-addon">供应商名称</span>
                    <form:input type="text" class="form-control supplierName" placeholder="请输入供应商名称" path="supplierName"
                                id="${basepath}/validate/supplierName.json" autocomplete="off"
                                autofocus="true" required="true" oninvalid="validatelt(this,'供应商名称不能为空')" maxlength="30"/>
                    <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon">供应商简称</span>
                    <form:input type="text" class="form-control simpleName" placeholder="请输入供应商简称" path="simpleName"
                                id="${basepath}/validate/simpleName.json" autocomplete="off"
                                autofocus="true" required="true" oninvalid="validatelt(this,'供应商简称不能为空')" maxlength="30"/>
                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">办公地址</span>
                    <form:input type="text" class="form-control" placeholder="请输入注册地址" path="workAddress"/>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">仓库地址</span>
                    <form:input type="text" class="form-control" placeholder="请输入注册地址" path="wallAddress"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">其他地址</span>
                    <form:input type="text" class="form-control" placeholder="请输入其他地址，如开平厂等" path="kaiPingAddress"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">备&nbsp;&nbsp;&nbsp;&nbsp;注</span>
                    <form:textarea type="textarea" row="4" class="form-control" placeholder="经营的其他信息" path="others"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="">
                    <span class="input-group-addon">产品种类</span>
                </div>
            </div>
        </div>

        <div class="row control-checkBox">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv" id="goodSumaryTypeDiv">
                <c:forEach var="goodType" items="<%=GoodType.values()%>">
                    <div class="checkbox-custom checkbox-default goodTypeItem">
                        <input type="checkbox" class="goodTypeBox goodSumaryTypeBox" value="${goodType.name()}" id="${goodType.name()}">
                        <label class="form-check-label" for="${goodType.name()}">
                                ${goodType.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
            <hr/>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv">
                <c:forEach var="banType" items="<%=PlainType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem banCheck">
                        <input type="checkbox" class="goodTypeBox" value="${banType.name()}" name="goodType" id="${banType.name()}">
                        <label class="form-check-label" for="${banType.name()}">
                            ${banType.name()}
                        </label>
                    </div>
                </c:forEach>
                <c:forEach var="guanType" items="<%=PupeType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem guanCheck">
                        <input type="checkbox" class="goodTypeBox" value="${guanType.name()}" name="goodType" id="${guanType.name()}">
                        <label class="form-check-label" for="${guanType.name()}">
                                ${guanType.name()}
                        </label>
                    </div>
                </c:forEach>
                <c:forEach var="xingType" items="<%=XingCaiType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem xingCheck">
                        <input type="checkbox" class="goodTypeBox" value="${xingType.name()}" name="goodType" id="${xingType.name()}">
                        <label class="form-check-label" for="${xingType.name()}">
                                ${xingType.name()}
                        </label>
                    </div>
                </c:forEach>
                <c:forEach var="lingType" items="<%=LingBuJianType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem lingCheck">
                        <input type="checkbox" class="goodTypeBox" value="${lingType.name()}" name="goodType" id="${lingType.name()}">
                        <label class="form-check-label" for="${lingType.name()}">
                                ${lingType.name()}
                        </label>
                    </div>
                </c:forEach>
                <c:forEach var="jiaType" items="<%=JiaGongType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem jiaCheck">
                        <input type="checkbox" class="goodTypeBox" value="${jiaType.name()}" name="goodType" id="${jiaType.name()}">
                        <label class="form-check-label" for="${jiaType.name()}">
                                ${jiaType.name()}
                        </label>
                    </div>
                </c:forEach>
                <c:forEach var="otherType" items="<%=OtherServiceType.values()%>">
                    <div class="checkbox-custom checkbox-default  goodTypeItem otherCheck">
                        <input type="checkbox" class="goodTypeBox" value="${otherType.name()}" name="goodType" id="${otherType.name()}">
                        <label class="form-check-label" for="${otherType.name()}">
                                ${otherType.name()}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div>
                    <span class="input-group-addon">经营材质</span>
                </div>
            </div>
        </div>
        <div class="row control-checkBox">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 textureDiv">
                <c:forEach var="textureType" items="<%=TextureType.values()%>">
                    <div class="checkbox-custom checkbox-default textureItem">
                        <input type="checkbox" class="textureBox" value="${textureType.name()}" name="textureTypes" id="${textureType.name}">
                        <label class="form-check-label" for="${textureType.name}">
                            ${textureType.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-2 ol-md-2 col-sm-1 col-xs-1">
            </div>
            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="reset" class="form-control btn-danger" value="重置">
                </div>
            </div>
            <div class="col-lg-2 ol-md-2 col-sm-1 col-xs-1">
            </div>
            <div class="col-lg-2 ol-md-2 col-sm-1 col-xs-1">
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                <div class="input-group">
                    <input type="submit" class="form-control btn-success" id="validate-btn" value="添加">
                </div>
            </div>
            <div class="col-lg-2 ol-md-2 col-sm-1 col-xs-1">
            </div>
        </div>
       </form:form>
    </div>
<script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basepath}js/myscript.js"></script>
<script type="text/javascript" src="${basepath}js/dynamicTip.js"></script>
<script src="${basepath}js/jquery.form.js"></script>
<script src="${basepath}js/jquery.serializejson.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${basepath}js/datepicker.js"></script>
<script type="text/javascript">
    function validatelt(inputelement,err){
        if(inputelement.validity.patternMismatch){
            inputelement.setCustomValidity(err);
        }else{
            inputelement.setCustomValidity("");
            return true;
        }
    }
</script>

</body>
</html>