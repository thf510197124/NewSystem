<%@ page import="com.taiquan.domain.customerEnums.CustomerType" %>
<%@ page import="com.taiquan.domain.customerEnums.CapitalType" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.customerEnums.AddressType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: WIN10
  Date: 2018/11/11
  Time: 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <link rel="stylesheet" href="${basepath}css/bootstrap-theme.css">
    <link rel="stylesheet" href="${basepath}css/datepicker.css">
    <script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basepath}js/myscript.js"></script>
    <script type="text/javascript" src="${basepath}js/dynamicTip.js"></script>
    <script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${basepath}js/datepicker.js"></script>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">${customer.customerName}</h3>
    </div>
    <div class="row">
        <div class="customer-box col-lg-8 col-md-8 col-sm-12 col-xs-12">
            <form:form modelAttribute="customer1" method="post" id="contactPlan">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">客户类型</span>
                            <form:select class="form-control" path="contactPlan.customerType" id="customerType">
                                <c:forEach var="entry" items="<%=CustomerType.values()%>">
                                    <option value="${entry.ordinal()}"
                                            <c:if test="${entry.name()} == ${customer.customerType.name()}">
                                                selected="selected"
                                            </c:if>
                                    >
                                            ${entry.name()}
                                    </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">下次联系</span>
                            <form:input type="date" class="form-control form_next" value="${customer.nextDate}" path="contactPlan.nextDate" id="nextDate" readonly="true"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">法人姓名</span>
                            <span type="text" class="form-control" >${customer.owner}</span>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">注册资本</span>
                            <span type="number" class="form-control">${customer.capital}</span>
                            <span class="form-control">${customer.capitalType.name()}</span>
                        </div>
                    </div>
                </div>
                <c:if test="${customer.addresses.size() == 0}">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                            <div class="input-group" data-toggle="modal" data-target="#modal-address">
                                <a class="btn btn-success btn-sm" href="#">添加地址</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${customer.addresses.size() > 0}">
                    <div class="row">
                        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                            <c:forEach var="address" items="${customer.addresses}">
                                <div class="input-group">
                                    <span class="input-group-addon">${address.addressType}</span>
                                    <span class="form-control">${address.simple}</span>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="input-group" data-toggle="modal" data-target="#modal-address">
                                <a class="btn btn-success" href="#">添加地址</a>
                            </div>
                        </div>
                    </div>

                </c:if>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">成立日期</span>
                            <!--这个表单需要js校验，或者可以用jsp标签或jspBean解决这个问题,动态更改max的值-->
                            <span type="date" class="form-control"><fmt:formatDate value="${customer.establishedDate}" pattern="yyyy-MM-dd" /></span>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">企业网址</span>
                            <span class="form-control"><a href="${customer.web}">${customer.web}</a></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">经营范围</span>
                            <span  id="details" class="form-control">${customer.businesses}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">其他信息</span>
                            <span  id="others" class="form-control" >${customer.others}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 ol-md-3 col-sm-1 col-xs-1">
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
                        <div class="input-group">
                            <a type="reset" class="form-control btn btn-danger" href="/updateCustomer/${customer.customerId}.html">修改客户资料</a>
                        </div>
                    </div>
                    <div class="col-lg-3 ol-md-3 col-sm-1 col-xs-1">
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
                        <div class="input-group">
                            <div hidden>
                                <form:input path="customerId" value="${customer.customerId}" />
                            </div>
                            <input type="submit" class="form-control btn-success" value="重置联系情况"/>
                        </div>
                    </div>
                    <div class="col-lg-3 ol-md-3 col-sm-1 col-xs-1"></div>
                </div>
            </form:form>
        </div>
        <div class="modal fade" id="modal-address" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel1">添加客户地址</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post">
                            <div class="row">
                                <div class="input-group">
                                    <span class="input-group-addon">地址类型</span>
                                    <select name="addressType" class="form-control" id="addressType">
                                        <c:forEach items="<%=AddressType.values()%>" var="addressType">
                                            <option value="${addressType.ordinal()}">${addressType.name()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-group">
                                    <span class="input-group-addon">客户地址</span>
                                    <input name="simple" class="form-control" type="text" placeholder="请输入地址" id="simple"/>
                                </div>
                            </div>
                            <div hidden>
                                <input name="customerId" value="${customer.customerId}" id="customerId"/>
                            </div>
                            <div class="row text-center">
                                <div class="input-group">
                                    <button class="form-control btn btn-success btn-lg" onclick="submitAddress()" data-dismiss="modal">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="comment-box col-lg-4 col-md-4 col-sm-12 col-xs-12">

        </div>
    </div>
</div>




<script type="text/javascript">
    $(document).ready(function () {
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