<%@ page import="java.util.Date" %>
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
    <link rel="stylesheet" href="${basepath}fontawesome/css/font-awesome.css">
    <style>
        .add-good-ban,.add-good-guan,.add-good-ling,.add-good-xing,.add-good-jia,.add-good-other{
            display: none;
        }
        i{
            font-size:50px;
        }
    </style>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">${supplier.supplierName}</h3>
    </div>
    <hr/>
    <div class="row">
        <form action="${basepath}supply/supplierGoods/${supplierId}.html" method="get" class="form-inline">
            <div class="col-lg-2 col-md-2 col-sm-6">
                <label class="radio-block-goodFilt input-group-addon">
                    <i class="fa fa-square-o login-icon" id="thisMonth"></i>
                    <input name="thisMonth" class="radio" type="radio" value="true" />只看本月
                </label>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-6">
                <label class="radio-block-goodFilt input-group-addon">
                    <i class="fa fa-square-o login-icon" id="onlyMe"></i>
                    <input name="onlyMe" class="radio" type="radio" value="true"/>只看自己
                </label>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-5 ">
                <div  class="input-group form-inline">
                    <span class="input-group-addon">起始</span>
                    <input type="text" name="fromDate" placeholder="起始日期" class="form_date form-control" data-date-format="yyyy-mm-dd">
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-5">
                <div  class="input-group form-inline">
                    <span class="input-group-addon">截至</span>
                    <input type="text" name="toDate" placeholder="截至日期" class="form_date form-control" data-date-format="yyyy-mm-dd">
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <button class="btn btn-sm btn-success">查询</button>
            </div>
        </form>
        <%--<div class="col-lg-3 col-md-3 col-sm-6">
            <a class="form-control btn btn-info" href="${basepath}supply/supplierGoods/${supplierId}.html?thisMonth=true">查看本月</a>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6">
            <a class="form-control btn btn-info" href="${basepath}supply/supplierGoods/${supplierId}.html?onlyMe=true">只看自己</a>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-12">
            <form class="form-inline">
                <span class="input-group-prepend">起始</span>
                <input type="date" name="fromDate" placeholder="起始日期">
                <span class="input-group-prepend">截至</span>
                <input type="date" name="toDate" placeholder="截至日期">
                <button class="btn btn-sm btn-success">查询</button>
            </form>
        </div>--%>
    </div>
    <hr/>
    <c:forEach items="${orderBeans}" var="orderBean">
        <h5 class="text-left text-info">${orderBean.orderName}</h5>
        <table class="table-responsive table-striped table-hover table-bordered order-table">
                <%--<caption>订单详情</caption>--%>
            <tr class="tr-title">
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
            <c:forEach var="good" items="${orderBean.goods}" varStatus="index">
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
            <tr class="tr-title">
                <td class="" colspan="4">合计</td>
                <td class="td-0-5 small-font">${orderBean.totalAmount}</td>
                <td class="td-0-5 small-font">${orderBean.totalWeight}</td>
                <td class="td-0-5 small-font"></td>
                <td class="td-1 small-font">${orderBean.totalCost}</td>
                <td class="td-0-5 small-font"></td>
                <td class="td-1 small-font">${orderBean.totalSums}</td>
                <td class="td-0-5 small-font">
                    <c:if test="${orderBean.profit ==0}">
                        ${orderBean.totalSums - orderBean.totalCost}
                    </c:if>
                    <c:if test="${orderBean.profit !=0}">
                        ${orderBean.profit}
                    </c:if>
                </td>
                <td class="" colspan="4"></td>
            </tr>
        </table>
        <hr/>
    </c:forEach>

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