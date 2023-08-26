<%@ page import="com.taiquan.domain.customerEnums.CustomerType" %>
<%@ page import="com.taiquan.domain.customerEnums.CapitalType" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">添加客户</h3>
    </div>
    <hr/>
    <form:form class="" action="addCompany.html" method="post" modelAttribute="companyBean">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">企业名称</span>
                    <form:input type="text" class="form-control companyName" placeholder="请输入客户名称" path="companyName"
                                id="${basepath}/validate/companyName.json" autocomplete="off"
                                autofocus="true" required="true" oninvalid="validatelt(this,'客户名称不能为空')" maxlength="30"/>
                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
                <div class="input-group">
                    <span class="input-group-addon">客户类型</span>
                    <form:select class="form-control" path="customerType">
                        <c:forEach var="entry" items="<%=CustomerType.values()%>">
                            <option value="${entry.ordinal()}">${entry.name()}</option>
                        </c:forEach>
                    </form:select>
                        <%--<form:input class="form-control select-input" path="customerType" type="text"/>
                        <select class="select-item">
                            <c:forEach var="entry" items="<%=CustomerType.values()%>">
                                <option value="${entry.ordinal()}">${entry.name()}</option>
                            </c:forEach>
                        </select>--%>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
                <div class="input-group">
                    <span class="input-group-addon">下次联系</span>
                        <%--<form:input type="date" class="form-control" placeholder="请输入下次联系的日期" path="nextDate" min="<%=today%>" value="2050-12-31"/>--%>
                    <form:input type="text" class="form-control form_next" path="nextDate" data-date-format="yyyy-mm-dd" readonly="true"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">法人姓名</span>
                    <form:input type="text" class="form-control" placeholder="请输入法人姓名" path="owner" maxlength="30"/>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">注册资本</span>
                    <form:input type="number" step="0.01" class="form-control" placeholder="请输入注册资本" path="capital" oninvalid="validatelt(this,'只能输入数字')" />
                    <form:select class="form-control" path="capitalType">
                        <c:forEach var="huobi" items="<%=CapitalType.values()%>">
                            <option value="${huobi.ordinal()}">${huobi.name()}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">成立日期</span>
                    <!--这个表单需要js校验，或者可以用jsp标签或jspBean解决这个问题,动态更改max的值-->
                        <%--<form:input type="date" class="form-control establish-validate" placeholder="请输入创立日期" path="establishedDate" max="<%=today%>" value="1949-01-01"/>--%>
                    <form:input type="text" class="form-control form_estab" path="establishedDate" data-date-format="yyyy-mm-dd"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">注册地址</span>
                    <form:input type="text" class="form-control" placeholder="请输入注册地址" path="address" value="${companyBean.address.simple}"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">企业网址</span>
                    <form:input type="text" class="form-control" placeholder="请输入主页网址" path="web"/>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">联系电话</span>
                    <form:input type="text" class="form-control" placeholder="请输入联系电话" path="phoneNumber" maxlength="15" value="${companyBean.phoneNumber.numbers}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">经营范围</span>
                    <form:textarea name="details" id="details" type="textarea" placeholder="请输入业务范围"
                                   class="form-control" rows="3" path="businesses"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon">其他信息</span>
                    <form:textarea name="others" id="others" type="textarea" path="others" placeholder="客户来源，产品信息等"
                                   class="form-control" rows="2"/>
                </div>
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
                    <input type="submit" class="form-control btn-success" id="validate-btn" value="添加" disabled>
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