<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.order.enums.OrderType" %>
<%@ page import="com.taiquan.domain.order.enums.orderStatus.OrderStatus" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Date date = new Date();
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
        .add-good-ban,.add-good-guan,.add-good-ling,.add-good-xing,.add-good-jia,.add-good-other{
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">${order.orderName}</h3>
    </div>
    <hr/>
    <div class="row text-add-order">
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">添加时间</span>
                <span class="form-control input">${order.createTime}</span>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">订单类型</span>
                <span class="form-control input">${order.orderStatus}</span>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">订单状态</span>
                <span class="form-control input">${order.orderType}</span>
            </div>
        </div>
    </div>
    <hr/>

    <table class="table-responsive table-striped table-hover table-bordered order-table">
        <%--<caption>订单详情</caption>--%>
        <tr class="bg-success">
            <td class="td-0-5 small-font">序号</td>
            <td class="td-0-5 small-font">种类</td>
            <td class="td-0-5 small-font">材质</td>
            <td class="td-1 small-font">规格</td>
            <td class="td-0-5 small-font">数量</td>
            <td class="td-0-5 small-font">重量(Kg)</td>
            <td class="td-0-5 small-font">进价(￥)</td>
            <td class="td-1 small-font">进额(￥)</td>
            <td class="td-0-5 small-font">售价(￥)</td>
            <td class="td-1 small-font">出额(￥)</td>
            <td class="td-0-5 small-font">毛利(￥)</td>
            <td class="td-0-5 small-font">公差产地</td>
            <td class="td-0-5 small-font">供应商</td>
            <td class="td-1 small-font">备注</td>
            <td class="td-1 small-font">操作</td>
        </tr>
        <c:if test="${innerGoods != null}">
            <c:forEach var="good" items="${innerGoods}" varStatus="index">
                <tr class="">
                    <td class="td-0-5 small-font">${index.index+1}</td>
                    <td class="td-0-5 small-font">${good.detailGoodType}</td>
                    <td class="td-0-5 small-font">${good.textureType}</td>
                    <td class="td-1 small-font">${good.spec}</td>
                    <td class="td-0-5 small-font">${good.amount} &nbsp;${good.unitType}</td>
                    <td class="td-0-5 small-font">
                        <c:if test="${good.weight != 0}">
                            ${good.weight}
                        </c:if>
                    </td>
                    <td class="td-0-5 small-font">
                            ${good.buyPrice}
                            <c:if test="${good.buyUnit != null}">
                                /${good.buyUnit}
                            </c:if>
                    </td>
                    <td class="td-1 small-font">${good.sumOfBuyMoney}</td>
                    <td class="td-0-5 small-font">
                            ${good.salPrice}
                            <c:if test="${good.salUnit != null}">
                               /${good.salUnit}
                            </c:if>
                    </td>
                    <td class="td-1 small-font">${good.sumOfSalsMoney}</td>
                    <td class="td-0-5 small-font">${good.profit}</td>
                    <td class="td-0-5 small-font">
                        <c:if test="${good.realThick !=0}">
                            ${good.realThick}&nbsp;
                        </c:if>
                            ${good.chanDi}
                    </td>
                    <td class="td-0-5 small-font">${good.supplier}</td>
                    <td class="td-1 small-font">${good.others}</td>
                    <td class="td-1 small-font">
                        <button id="${basepath}order/deleteGood/${good.goodId}.html" class="btn-sm btn-danger good-delete">删除</button>
                        <button id="${basepath}order/updateGood/${good.goodId}.html" class="btn-sm btn-info good-update">修改</button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <tr class="bg-primary">
            <td class="" colspan="4">合计</td>
            <td class="td-0-5 small-font">${order.totalAmount}</td>
            <td class="td-0-5 small-font">${order.totalWeight}</td>
            <td class="td-0-5 small-font"></td>
            <td class="td-1 small-font">${order.totalCost}</td>
            <td class="td-0-5 small-font"></td>
            <td class="td-1 small-font">${order.totalSums}</td>
            <td class="td-0-5 small-font">
                <c:if test="${order.profit ==0}">
                    ${order.totalSums - order.totalCost}
                </c:if>
                <c:if test="${order.profit !=0}">
                    ${order.profit}
                </c:if>
            </td>
            <td class="" colspan="4"></td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basepath}js/myscript.js"></script>
<script type="text/javascript" src="${basepath}js/dynamicTip.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${basepath}js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${basepath}js/datepicker.js"></script>
<script type="text/javascript" src="${basepath}js/order.js"></script>
</body>
</html>