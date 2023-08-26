<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.order.enums.OrderType" %>
<%@ page import="com.taiquan.domain.order.enums.orderStatus.OrderStatus" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
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
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <c:if test="${updateStatus ==2}">
            <h3 class="text-center text-danger">${customer.company.companyName}-----订单详情</h3>
        </c:if>
        <c:if test="${updateStatus ==0}">
            <h3 class="text-center text-danger">${customer.company.companyName}----------添加订单</h3>
        </c:if>
        <c:if test="${updateStatus ==1}">
            <h3 class="text-center text-danger">${customer.company.companyName}----------修改订单</h3>
        </c:if>
    </div>
    <hr/>
    <form:form action="${basePath}order/updateOrder.html" modelAttribute="orderBean">
        <div class="row order-status-row">
            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 ">
                <div class="input-group">
                    <span class="input-group-addon">订单类型</span>
                    <form:select path="orderType" class="form-control" id="order-type" value="${orderType}">
                        <c:forEach var="entry" items="<%=OrderType.values()%>">
                            <c:choose>
                                <c:when test="${orderBean.orderType == entry.name()}">
                                    <option value="${entry.name()}" selected="selected">${entry.name()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${entry.name()}">${entry.name()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 ">
                <div class="input-group">
                    <span class="input-group-addon">订单状态</span>
                    <form:select path="orderStatus" class="form-control" id="order-status" itemValue="">
                        <c:forEach var="entry" items="<%=OrderStatus.values()%>">
                            <c:choose>
                                <c:when test="${orderBean.orderStatus == entry.name()}">
                                    <option value="${entry.name()}" selected="selected">${entry.name()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${entry.name()}">${entry.name()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-2">
                <div class="input-group">
                    <button type="submit" class="form-control btn-primary">提交</button>
                </div>

            </div>
        </div>
    </form:form>
    <c:if test="${updateStatus != 1}">
        <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a href="#add-ban" class="nav-link active" data-toggle="tab">添加板卷</a>
                </li>
                <li class="nav-item">
                    <a href="#add-guan" class="nav-link" data-toggle="tab">添加钢管</a>
                </li>
                <li class="nav-item">
                    <a href="#add-xing" class="nav-link" data-toggle="tab">添加型材</a>
                </li>
                <li class="nav-item">
                    <a href="#add-ling" class="nav-link" data-toggle="tab">添加零部件</a>
                </li>
                <li class="nav-item">
                    <a href="#add-jia" class="nav-link" data-toggle="tab">添加加工</a>
                </li>
                <li class="nav-item">
                    <a href="#add-others" class="nav-link" data-toggle="tab">添加其他</a>
                </li>
            </ul>
        <div id="myTabContent" class="tab-content">
            <jsp:include page="addGood.jsp"/>
        </div>
    </c:if>
    <c:if test="${updateStatus ==1}">
        <ul class="nav nav-tabs" role="tablist">
            <c:if test="${ban != null}">
                <li class="nav-item">
                    <a href="#update-ban-btn" class="nav-link active" data-toggle="tab">修改板卷</a>
                </li>
            </c:if>
            <c:if test="${guan != null}">
                <li class="nav-item">
                    <a href="#update-guan-btn" class="nav-link active" data-toggle="tab">修改钢管</a>
                </li>
            </c:if>
            <c:if test="${xing != null}">
                <li class="nav-item">
                    <a href="#update-xing-btn" class="nav-link active" data-toggle="tab">修改型材</a>
                </li>
            </c:if>
            <c:if test="${ling != null}">
                <li class="nav-item">
                    <a href="#update-ling-btn" class="nav-link active" data-toggle="tab">修改零部件</a>
                </li>
            </c:if>
            <c:if test="${jia != null}">
                <li class="nav-item">
                    <a href="#update-jia-btn" class="nav-link active" data-toggle="tab">修改加工</a>
                </li>
            </c:if>
            <c:if test="${other != null}">
                <li class="nav-item">
                    <a href="#update-others-btn" class="nav-link active" data-toggle="tab">修改其他</a>
                </li>
            </c:if>

        </ul>
        <div id="myTabContent" class="tab-content">
            <jsp:include page="updateGood.jsp"/>
        </div>
    </c:if>
    <c:if test="${innerGoodBeans.size() > 0}">
        <hr/>
        <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a href="#customerOrder" class="nav-link active" data-toggle="tab">
                    客户报价单
                </a>
            </li>
            <li class="nav-item">
                <a href="#innerOrder" class="nav-link" data-toggle="tab">报价单底单</a>
            </li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="container tab-pane active" id="customerOrder">
                <div class="text-center">
                    <span class="logo"><img src="${basepath}/img/logo.png" class="log-img img-thumbnail"> </span>
                    <label class="h4 text-success">
                        &nbsp;&nbsp;无&nbsp;锡&nbsp;泰&nbsp;全&nbsp;金&nbsp;属&nbsp;制&nbsp;品&nbsp;有&nbsp;限&nbsp;公&nbsp;司&nbsp;报&nbsp;价&nbsp;单
                    </label>
                </div>
                <table class="table-responsive table-striped table-hover table-bordered order-table">
                        <%--<caption>订单详情</caption>--%>
                    <tr class="tr-title">
                        <td class="td-0-5">序号</td>
                        <td class="td-1">种类</td>
                        <td class="td-1">材质</td>
                        <td class="td-1-5">规格</td>
                        <td class="td-1">数量</td>
                        <td class="td-1 lit-small-font">重量(Kg)</td>
                        <td class="td-1 lit-small-font">单价(￥)</td>
                        <td class="td-1 lit-small-font">金额(￥)</td>
                        <td class="td-1 liter-small-font">公差产地</td>
                        <td class="td-1-5">备注</td>
                        <td class="td-1-5">操作</td>
                    </tr>
                    <c:if test="${customerGoodBeans != null}">
                        <c:forEach var="good" items="${customerGoodBeans}" varStatus="index">
                            <tr class="outter-items-good">
                                <td class="td-0-5">${index.index+1}</td>
                                <td class="td-1">${good.goodType}</td>
                                <td class="td-1">
                                    <c:if test="${good.texture != '其他'}">
                                        ${good.texture}
                                    </c:if>
                                </td>
                                <td class="td-1-5">${good.spec}</td>
                                <td class="td-1">${good.amountAndUnit}</td>
                                <td class="td-1">
                                    <c:if test="${good.weight!=0}">
                                        ${good.weight}
                                    </c:if>
                                </td>
                                <td class="td-1">${good.priceAndUnit}</td>
                                <td class="td-1">${good.sums}</td>
                                <td class="td-1 liter-small-font">${good.realThickChandi}</td>
                                <td class="td-1-5">${good.others}</td>
                                <td class="td-1-5">
                                    <a href="${basePath}order/updateGood/${good.goodId}.html" class="btn-sm btn-info good-update">修改</a>
                                    <a href="${basePath}order/deleteGood/${good.goodId}.html" class="btn-sm btn-danger good-delete">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr class="tr-title">
                        <td class="td-0-5">合计</td>
                        <td class="td-1-5" colspan="3"></td>
                            <%--<td class="col-1"></td>
                            <td class="col-2"></td>--%>
                        <td class="td-1">
                            <fmt:formatNumber type="number" maxFractionDigits="2"
                                              value="${order.totalAmount}"/></td>
                        <td class="td-1">
                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${order.totalWeight}"/>
                        </td>
                        <td class="td-1"></td>
                        <td class="td-1"> <fmt:formatNumber type="currency" maxFractionDigits="2" value="${order.totalSums}"/></td>
                        <td class="td-1"></td>
                        <td class="td-1-5"></td>
                        <td class="td-1-5"></td>
                    </tr>
                </table>
            </div>
            <div class="container tab-pane fade" id="innerOrder">
                <div class="text-center">
                    <span class="logo"><img src="${basepath}/img/logo.png" class="log-img img-thumbnail"> </span>
                    <label class="h4 text-success">
                        &nbsp;&nbsp;无&nbsp;锡&nbsp;泰&nbsp;全&nbsp;金&nbsp;属&nbsp;制&nbsp;品&nbsp;有&nbsp;限&nbsp;公&nbsp;司&nbsp;报&nbsp;价&nbsp;底&nbsp;单
                    </label>
                </div>
                <table class="table-responsive table-striped table-hover table-bordered order-table">
                        <%--<caption>订单详情</caption>--%>
                    <tr class="tr-title">
                        <td class="td-0-5 small-font">序号</td>
                        <td class="td-0-5 small-font">种类</td>
                        <td class="td-0-5 small-font">材质</td>
                        <td class="td-1 small-font">规格</td>
                        <td class="td-0-7-5 small-font">数量</td>
                        <td class="td-0-5 small-font">重量(Kg)</td>
                        <td class="td-0-7-5 small-font">进价(￥)</td>
                        <td class="td-0-7-5 small-font">进额(￥)</td>
                        <td class="td-0-7-5 small-font">售价(￥)</td>
                        <td class="td-0-7-5 small-font">出额(￥)</td>
                        <td class="td-0-5 small-font">毛利(￥)</td>
                        <td class="td-0-7-5 small-font">公差产地</td>
                        <td class="td-0-5 small-font">供应商</td>
                        <td class="td-1 small-font">备注</td>
                        <td class="td-1 small-font">操作</td>
                    </tr>
                    <c:if test="${innerGoodBeans != null}">
                        <c:forEach var="good" items="${innerGoodBeans}" varStatus="index">
                            <tr class="inner-items-good">
                                <td class="td-0-5 small-font">${index.index+1}</td>
                                <td class="td-0-5 small-font">${good.detailGoodType}</td>
                                <td class="td-0-5 small-font">${good.textureType}</td>
                                <td class="td-1 small-font">${good.spec}</td>
                                <td class="td-0-7-5 small-font">${good.amount} &nbsp;${good.unitType}</td>
                                <td class="td-0-5 small-font">
                                    <c:if test="${good.weight != 0}">
                                        ${good.weight}
                                    </c:if>
                                </td>
                                <td class="td-0-7-5 small-font">
                                        ${good.buyPrice}
                                    <c:if test="${good.buyUnit != null}">
                                        /${good.buyUnit}
                                    </c:if>
                                </td>
                                <td class="td-0-7-5 small-font">${good.sumOfBuyMoney}</td>
                                <td class="td-0-7-5 small-font">
                                        ${good.salPrice}
                                    <c:if test="${good.salUnit != null}">
                                        /${good.salUnit}
                                    </c:if>
                                </td>
                                <td class="td-0-7-5 small-font">${good.sumOfSalsMoney}</td>
                                <td class="td-0-5 small-font">${good.profit}</td>
                                <td class="td-0-7-5 small-font">
                                    <c:if test="${good.realThick !=0}">
                                        ${good.realThick}&nbsp;&nbsp;
                                    </c:if>
                                        ${good.chanDi}
                                </td>
                                <td class="td-0-5 small-font">${good.supplier}</td>
                                <td class="td-1 small-font">${good.others}</td>
                                <td class="td-1 small-font">
                                    <a href="${basePath}order/updateGood/${good.goodId}.html" class="btn-sm btn-info good-update">修改</a>
                                    <a href="${basePath}order/deleteGood/${good.goodId}.html" class="btn-sm btn-danger good-delete">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr class="tr-title">
                        <td class="" colspan="4">合计</td>
                        <td class=" small-font"><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${order.totalAmount}"/></td>
                        <td class=" small-font"><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${order.totalWeight}"/></td>
                        <td class=" small-font"></td>
                        <td class=" small-font"><fmt:formatNumber type="currency" maxFractionDigits="2" value="${order.totalCost}"/></td>
                        <td class=" small-font"></td>
                        <td class=" small-font"><fmt:formatNumber type="currency" maxFractionDigits="2" value="${order.totalSums}"/></td>
                        <td class=" small-font">
                            <c:if test="${order.profit == 0}">
                                <fmt:formatNumber type="currency" maxFractionDigits="2" value="${order.totalSums - order.totalCost}"/>
                            </c:if>
                            <c:if test="${order.profit !=0}">
                                <fmt:formatNumber type="currency" maxFractionDigits="2" value="${order.profit}"/>
                            </c:if>
                        </td>
                        <td class="" colspan="4"></td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>


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