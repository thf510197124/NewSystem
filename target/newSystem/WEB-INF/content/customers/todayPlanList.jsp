<%@ page import="com.taiquan.domain.customerEnums.CustomerType" %>
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
        <h3 class="text-center text-danger">今日计划</h3>
    </div>
    <hr/>
    <div class="select-item">
        <form action="<%=basePath%>customer/todayPlanList.html" method="post">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">客户类型</span>
                        <select class="form-control" name="customerType">
                            <option value="20">请选择</option>
                            <c:forEach var="entry" items="<%=CustomerType.values()%>">
                                <option value="${entry.ordinal()}">${entry.name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">下次联系</span>
                        <input type="text" class="form-control form_next" placeholder="请输入下次联系的日期" data-date-format="yyyy-mm-dd"
                                name="nextDate" value="<%=today%>"/>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">只看今天？</span>
                        <select class="form-control" name="isOnlyDate">
                            <option value="false">否</option>
                            <option value="true">是</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">更新时间</span>
                        <input type="text" class="form-control form_estab" placeholder="请输入更新时间" name="updateDate" data-date-format="yyyy-mm-dd"/>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">只看当天添加？</span>
                        <select class="form-control" name="onlyUpdate">
                            <option value="false">否</option>
                            <option value="true">是</option>
                        </select>
                    </div>
                </div>
                <c:if test="${user.mgr}">
                    <div class="col-lg-4 col-md-4 col-md-6 col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">看下属的吗？</span>
                            <select class="form-control" name="onlyMe">
                                <option value="false">不看</option>
                                <option value="true">看</option>
                            </select>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                    <button class="btn btn-success btn-lg" type="submit">提交</button>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
            </div>
        </form>
    </div>
    <hr/>
    <c:forEach var="customer" items="${customerPage.thisPageList}" varStatus="status">
            <div class="span-control">
                <a href="<%=basePath%>company/customerDetails/${customer.customerId}/${(customerPage.pageNo-1)*customerPage.pageSize + status.index}.html" target="_blank">${customer.company.companyName}</a>
            </div>
        </c:forEach>
    <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div style="height:30px"></div></div>
        </div>
    <div class="row page-item text-center">
            <div class="col-lg-2 col-md-2"></div>
            <div></div>
            <c:if test="${customerPage.hasPrePage()}">
                <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                    <a href="<%=basePath%>${controller}/${thisView}/${customerPage.pageNo -1}.html" class="btn btn-info">上一页</a><!--这里需要使用ajax-->
                </div>
            </c:if>
            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                <button class="btn btn-info" disabled>第${customerPage.pageNo}页</button>
            </div>

            <c:if test="${customerPage.hasNextPage()}">
                <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                    <a href="<%=basePath%>${controller}/${thisView}/${customerPage.pageNo+1}.html" class="btn btn-info">下一页</a><!--这里需要使用ajax-->
                </div>
            </c:if>

            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                <button class="btn btn-info" disabled>共${customerPage.allPageNo}页</button>
            </div>
            <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                <div class="input-group">
                    <span class=""><button type="button" class="btn btn-info" disabled>跳转</button></span>
                    <input type="number" min="1" max="${customerPage.allPageNo}" step="1" class="pageTo form-control input-group-sm" placeholder="跳转到第">
                    <span class="">
                        <button type="button" class="btn btn-info btn-pageTo" id="<%=basePath%>${controller}/${thisView}" disabled>页 &nbsp;&nbsp;GO</button>
                    </span>
                </div>
            </div>
            <div class="col-lg-2 col-md-2"></div>
        </div>
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