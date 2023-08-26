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
    <link rel="stylesheet" href="${basepath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basepath}/css/main.css">

</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">供应商列表</h3>
    </div>
    <c:if test="${msg != null}">
        <div>
            <h5 class="text-center text-success">${msg}</h5>
        </div>
    </c:if>
    <hr/>
        <c:forEach var="supplier" items="${supplierPage.thisPageList}" varStatus="status">
            <div class="span-control">
                <a href="<%=basePath%>supply/supplierDetail/${supplier.supplierId}/${(supplierPage.pageNo-1)*supplierPage.pageSize + status.index}.html">
                        ${supplier.supplierName}
                </a>
                <button id="<%=basePath%>supply/deleteSupplier/${supplier.supplierId}.html" class="btn btn-danger float-right delete-import">删除</button>
            </div>
        </c:forEach>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div style="height:30px"></div></div>
        </div>
        <div class="row page-item text-center">
            <div class="col-lg-2 col-md-2"></div>
            <div></div>
            <c:if test="${supplierPage.hasPrePage()}">
                <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                    <a href="<%=basePath%>${controller}/${thisView}/${supplierPage.pageNo -1}.html" class="btn btn-info">上一页</a><!--这里需要使用ajax-->
                </div>
            </c:if>
            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                <button class="btn btn-info" disabled>第${supplierPage.pageNo}页</button>
            </div>

            <c:if test="${supplierPage.hasNextPage()}">
                <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                    <a href="<%=basePath%>${controller}/${thisView}/${supplierPage.pageNo+1}.html" class="btn btn-info">下一页</a><!--这里需要使用ajax-->
                </div>
            </c:if>

            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                <button class="btn btn-info" disabled>共${supplierPage.allPageNo}页</button>
            </div>
            <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                <div class="input-group">
                    <span class=""><button type="button" class="btn btn-info" disabled>跳转</button></span>
                    <input type="number" min="1" max="${supplierPage.allPageNo}" step="1" class="pageTo form-control input-group-sm" placeholder="跳转到第">
                    <span class="">
                        <button type="button" class="btn btn-info btn-pageTo" id="<%=basePath%>${controller}/${thisView}/" disabled>页 &nbsp;&nbsp;GO</button>
                    </span>
                </div>
            </div>
            <div class="col-lg-2 col-md-2"></div>
        </div>
    </div>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myscript.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dynamicTip.js"></script>
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