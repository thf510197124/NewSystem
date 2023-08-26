<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.taiquan.domain.customerEnums.CapitalType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basepath",basePath);
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(date);
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
        .addAddress-form,.hidden-employee,.hidden-company,.employee-box,.addContactsBtn{
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div class="container">
    <%--<div class="row text-title">
        &lt;%&ndash;<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
        </div>
        <div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
            <span class="h3 text-center text-danger">${company.companyName}</span>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
        </div>&ndash;%&gt;

    </div>--%>
    <hr/>
    <h3 class="text-center text-danger">${company.companyName}</h3>
    <hr/>
    <div class="row text-add-order">
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
            <c:if test="${index!=null && index > 0}">
                <a href="<%=basePath%>company/customerDetails/${customer.customerId}/${index-1}.html" class="btn btn-info">上一个</a>
            </c:if>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
            <button class="btn btn-danger deleteCustomer" id="<%=basePath%>customer/deleteCustomer/${customer.customerId}.html">删除</button>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
            <c:if test="${index!=null && index < customerListSize-1}">
                <a href="<%=basePath%>company/customerDetails/${customer.customerId}/${index+1}.html" class="btn btn-info">下一个</a>
            </c:if>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
            <a href="<%=basePath%>order/addOrder/${customer.customerId}.html" class="btn btn-success ">添加订单</a>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
            <c:if test="${orderSize > 0}">
                <a href="<%=basePath%>order/customerOrders/${customer.customerId}.html" class="btn btn-success ">查看订单</a>
            </c:if>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
            <div class="row">
                <form:form modelAttribute="company" id="up-company-form">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-company">
                        <div class="input-group">
                            <span class="input-group-addon">企业名称</span>
                            <form:input type="text" class="form-control" placeholder="请输入客户名称" path="companyName" required="true" oninvalid="validatelt(this,'客户名称不能为空')" maxlength="30" id="up-name"/>
                            <form:input type="text" class="form-control" path="companyId" value="${company.companyId}" hidden="hidden" id="up-id"/>
                        </div>
                    </div>
                    <div class="customer-box col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="row update-hidden">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon">客户类型</span>
                                    <span type="text" class="form-control customerType callbackCutomerTypeText">${customer.contactPlan.customerType.name()}</span>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon">下次联系</span>
                                    <span type="date" class="form-control customerType callbackNestDateText" id="nextDate">${customer.contactPlan.nextDate}</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon">法人姓名</span>
                                    <span type="text" class="form-control update-hidden" >${company.owner}</span>
                                    <form:input type="text" class="form-control hidden-company" placeholder="请输入法人姓名" path="owner" maxlength="30" id="up-owner"/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon">注册资本</span>
                                    <span class="form-control update-hidden">${company.capital}</span>
                                    <form:input type="number" step="0.01" class="form-control hidden-company" placeholder="请输入注册资本"
                                                path="capital" oninvalid="validatelt(this,'只能输入数字')" id="up-capital"/>
                                    <span class="form-control update-hidden">${company.capitalType.name()}</span>
                                    <form:select class="form-control hidden-company" path="capitalType" id="up-capitalType">
                                        <c:forEach var="huobi" items="<%=CapitalType.values()%>">
                                            <option value="${huobi.ordinal()}">${huobi.name()}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <c:if test="${addresses.size() > 0}">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 address-list">
                                    <c:forEach var="address" items="${addresses}">
                                        <div class="input-group">
                                            <span class="input-group-addon">${address.addressType}</span>
                                            <span class="form-control auto-hidden">${address.simple}</span>
                                            <span>
                                                <button class="deleteAddress btn btn-danger" id="${basePath}company/deleteAddress/${address.addressId}.html" type="button">
                                                    删除
                                                </button>
                                            </span>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <div class="addAddress-form">
                            <jsp:include page="parts/addAddress.jsp"/>
                        </div>
                        <div class="row">
                            <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">公司固话</span>
                                    <span type="text" class="form-control input phone-number update-hidden" >${company.phoneNumber}</span>
                                    <form:input type="text" class="form-control hidden-company" path="phoneNumber" id="up-phoneNumber"/>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <button class="btn btn-success addAddressButton" type="button">添加地址</button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">成立日期</span>
                                    <!--这个表单需要js校验，或者可以用jsp标签或jspBean解决这个问题,动态更改max的值-->
                                    <span type="date" class="form-control update-hidden"><fmt:formatDate value="${company.establishedDate}" pattern="yyyy-MM-dd" /></span>
                                    <%--<form:input type="date" class="form-control hidden-company form_estab" placeholder="请输入创立日期" path="establishedDate"
                                                max="<%=today%>" id="up-establishedDate" value="${company.establishedDate}"/>--%>
                                    <form:input type="text" class="form-control hidden-company form_estab" placeholder="请输入创立日期" path="establishedDate"
                                                data-date-format="yyyy-mm-dd" pattern="yyyy-MM-dd"
                                                id="up-establishedDate"/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">企业网址</span>
                                    <span class="form-control update-hidden auto-hidden"><a href="http://${company.web}" target="_blank">${company.web}</a></span>
                                    <form:input type="text" class="form-control hidden-company" placeholder="请输入主页网址" path="web" id="up-web"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">经营范围</span>
                                    <%--<span id="details" class="form-control">${company.businesses}</span>--%>
                                    <span id="details" class="span-control update-hidden">${company.businesses}</span>
                                    <form:textarea name="details" id="up-details" type="textarea" placeholder="请输入业务范围"
                                                   class="form-control hidden-company" rows="3" path="businesses"/>
                                </div>
                            </div>
                        </div>
                        <c:if test="${company.others != null && company.others != ''}">
                            <div class="row update-hidden">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="input-group">
                                        <span class="input-group-addon">其他信息</span>
                                            <%--<span  id="others" class="form-control" >${company.others}</span>--%>
                                        <span  id="others" class="span-control">${company.others}</span>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="row hidden-company">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon">其他信息</span>
                                        <%--<span  id="others" class="form-control" >${company.others}</span>--%>
                                    <form:textarea name="others" id="up-others" type="textarea" path="others" placeholder="客户来源，产品信息等"
                                                   class="form-control" rows="2"/>
                                </div>
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
                                    <button type="button" class="form-control btn btn-success go-up-company update-hidden">修改客户资料</button>
                                    <button type="button" class="form-control btn btn-success hidden-company up-company"
                                            id="${basePath}/company/updateCompany/${company.companyId}.json">
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
                        <jsp:include page="parts/employeeList.jsp"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="row">
                <div class="input-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                </div>
                <div class="input-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <button class="btn btn-danger addEmployeeButton pull-right" type="button">添加联系人</button>
                    <button class="btn btn-danger addContactsBtn pull-right" type="button">添加联系情况</button>
                </div>
                <hr/>
            </div>

            <div class="row">
                <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-box">
                    <div class="add-new-contactDetail">
                        <jsp:include page="parts/addContactDetailsForm.jsp"/>
                    </div>
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 employee-box">
                    <div class="add-new-employee">
                        <jsp:include page="parts/addEmployee.jsp"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-list">
                    <div class="">
                        <jsp:include page="parts/contactDetailsList.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
<script src="${basepath}js/jquery.form.js"></script>
<script src="${basepath}js/jquery.serializejson.js"></script>
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