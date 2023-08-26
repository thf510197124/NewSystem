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
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(date);
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
        .hidden-supplier,.hidden-employee{
            display: none;
        }
        .banCheck,.guanCheck,.xingCheck,.lingCheck,.jiaCheck,.otherCheck{
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <%--<div class="row text-title">
        &lt;%&ndash;<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
        </div>
        <div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
            <span class="h3 text-center text-danger">${supplier.supplierName}</span>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
        </div>&ndash;%&gt;

    </div>--%>
        <hr/>
    <h3 class="text-center text-danger">${supplier.supplierName}</h3>
    <hr/>
    <div class="row text-add-order">
        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
            <c:if test="${index!=null && index > 0}">
                <a href="<%=basePath%>supply/supplierDetail/${supplier.supplierId}/${index-1}.html" class="btn btn-info">上一个</a>
            </c:if>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
            <c:if test="${index!=null && index < supplierSize-1}">
                <a href="<%=basePath%>supply/supplierDetail/${supplier.supplierId}/${index+1}.html" class="btn btn-info">下一个</a>
            </c:if>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
            <c:if test="${goodSize > 0}">
                <a href="<%=basePath%>supply/supplierGoods/${supplier.supplierId}.html" class="btn btn-success ">查看订单</a>
            </c:if>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
            <div class="row">
                <form:form   method="post" modelAttribute="supplier">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 supplier-detail">
                        <div class="row">
                            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                                <div class="input-group">
                                    <span class="input-group-addon">供应商名称</span>
                                    <span class="form-control update-hidden">${supplier.supplierName}</span>
                                    <form:input type="text" class="form-control hidden-supplier" placeholder="请输入供应商名称" path="supplierName"
                                                autocomplete="off" value="${supplier.supplierName}" id="up-suppliername"
                                                autofocus="true" required="true" oninvalid="validatelt(this,'供应商名称不能为空')" maxlength="30"/>
                                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                                    <form:input type="text" class="form-control" path="supplierId" value="${supplier.supplierId}" hidden="hidden" id="supplier-up-id"/>
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                <div class="input-group">
                                    <span class="input-group-addon">企业简称</span>
                                    <span class="form-control update-hidden">${supplier.simpleName}</span>
                                    <form:input type="text" class="form-control hidden-supplier" placeholder="请输入供应商简称" path="simpleName"
                                                id="up-simpleName" autocomplete="off" value="${supplier.simpleName}"
                                                autofocus="true" required="true" oninvalid="validatelt(this,'供应商简称不能为空')" maxlength="30"/>
                                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">办公地址</span>
                                    <span class="form-control update-hidden">${supplier.workAddress}</span>
                                    <form:input type="text" class="form-control hidden-supplier" placeholder="请输入注册地址"
                                                path="workAddress" value="${supplier.workAddress}" id="up-workAddress"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">仓库地址</span>
                                    <span class="form-control update-hidden">${supplier.wallAddress}</span>
                                    <form:input type="text" class="form-control hidden-supplier" placeholder="请输入注册地址" path="wallAddress"
                                                value="${supplier.wallAddress}" id="up-wallAddress"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">其他地址</span>
                                    <span class="form-control update-hidden">${supplier.kaiPingAddress}</span>
                                    <form:input type="text" class="form-control hidden-supplier" placeholder="请输入其他地址，如开平厂等"
                                                path="kaiPingAddress" value="${supplier.kaiPingAddress}" id="up-kaiPingAddress"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">备&nbsp;&nbsp;&nbsp;&nbsp;注</span>
                                    <span class="span-control update-hidden">${supplier.others}</span>
                                    <form:textarea type="textarea" row="4" class="form-control hidden-supplier"
                                                   placeholder="经营的其他信息" path="others" value="${supplier.others}" id="up-others"/>
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
                        <div class="row control-checkBox update-hidden">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv">
                                <c:forEach var="banType" items="${supplier.goodType}">
                                    <div class="checkbox-custom checkbox-default  goodTypeString">
                                        <label class="item-label">
                                                ${banType}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row control-checkBox hidden-supplier">
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
                            <input type="hidden" id="goodDetailsHidden" value="${supplier.goodType}">
                            <%--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv">
                                <c:forEach var="banType" items="<%=GoodDetailType.values()%>">
                                    <div class="checkbox-custom checkbox-default  goodTypeItem">
                                        <input type="checkbox" class="goodTypeBox" value="${banType.name()}" name="goodType" id="${banType.name()}">
                                        <label class="form-check-label" for="${banType.name()}">
                                                ${banType.name()}
                                        </label>
                                    </div>
                                </c:forEach>
                                <input type="hidden" id="goodDetailsHidden" value="${supplier.goodType}">
                                <!--作用是把supplier的goodType反应到前端，在通过JS来显示哪些是已经选中的-->
                            </div>--%>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div>
                                    <span class="input-group-addon">经营材质</span>
                                </div>
                            </div>
                        </div>
                        <div class="row control-checkBox update-hidden ">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv">
                                <c:forEach var="textureType" items="${supplier.textureTypes}">
                                    <div class="checkbox-custom checkbox-default  goodTypeString">
                                        <label class="item-label">
                                                ${textureType.name}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row control-checkBox hidden-supplier">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 goodTypeDiv">
                                <c:forEach var="textureType" items="<%=TextureType.values()%>">
                                    <div class="checkbox-custom checkbox-default textureItem">
                                        <input type="checkbox" class="textureBox" value="${textureType.name}" name="textureTypes" id="${textureType.name}">
                                        <label class="form-check-label" for="${textureType.name}">
                                                ${textureType.name}
                                        </label>
                                    </div>
                                </c:forEach>
                                <input type="hidden" id="textureHidden" value="${supplier.textureTypes}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-9 ol-md-9 col-sm-3 col-xs-3">
                            </div>
                                <%--<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
                                    <button type="button" class="form-control btn btn-success go-up-company addContactsBtn">修改客户资料</button>
                                </div>--%>
                            <div class="col-lg-3 ol-md-3 col-sm-3 col-xs-3">
                                <div class="input-group">
                                    <button type="button" class="form-control btn btn-success go-up-supplier update-hidden">修改供应商资料</button>
                                    <button type="button" class="form-control btn btn-success hidden-supplier up-supplier"
                                            id="${basepath}supply/updateSupplier/${supplier.supplierId}.json">
                                        修改
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form:form>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 employee-list">
                    <div class="">
                        <jsp:include page="../parts/employeeList.jsp"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 employee-box">
                    <div class="add-new-employee">
                        <jsp:include page="../parts/addEmployee.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basepath}js/myscript.js"></script>
<script type="text/javascript" src="${basepath}js/order.js"></script>
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