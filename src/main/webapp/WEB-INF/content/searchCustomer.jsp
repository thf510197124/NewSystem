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
    session.setAttribute("basePath",basePath);
    String importMsg="";
    if(request.getSession().getAttribute("msg")!=null){
        importMsg=request.getSession().getAttribute("msg").toString();
    }
    request.getSession().setAttribute("msg", "");
%>

<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>客户管理系统</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>css/main.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">客户查询</h3>
    </div>
    <hr/>
    <h5 class="text-success search-title">批量上传客户</h5>
    <font id="importMsg" color="red"><%=importMsg%></font>
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <form action="<%=basePath%>customer/upLoadCustomer.html" method="post"
              enctype="multipart/form-data" onsubmit="return check();">
            <input id="excel_file" type="file" name="filename" accept="xls" size="80"/>
            <input id="excel_button" type="submit" value="导入Excel">
        </form>
    </div>
    <hr/>
    <!--联系人关键字查询-->
    <form  action="<%=basePath%>customer/queryByEmployee.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h5 class="text-success search-title">联系人查询</h5>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入联系人名字" name="employeeName"/>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                    <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span>--%>
                </div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>customer/queryByAddress.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h5 class="text-success search-title">地址查询</h5>
                <div class="input-group custom-control-inline" data-toggle="distpicker">
                    <select class="form-control" id="province3" data-province="浙江省" name="province"></select>
                    <select class="form-control" id="city3" data-city="杭州市" name="city"></select>
                    <select class="form-control" id="district3" data-district="西湖区" name="district"></select>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                </div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>customer/queryByAddressRound.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h5 class="text-success search-title">附近客户查询</h5>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入地址" name="address"/>
                    <input type="number" step="1" class="form-control" placeholder="附近多远" name="round"/>
                    <span class="btn btn-info input-group-append">公里范围</span>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                </div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>customer/queryByKeyWords.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h5 class="text-success search-title">关键字查询</h5>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入关键字" name="keywords"/>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                </div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>customer/queryByPhoneNumber.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h5 class="text-success search-title">电话查询</h5>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入电话" name="phone"/>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                </div>
            </div>
        </div>
    </form>
    <hr/>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myscript.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dynamicTip.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/distpicker.data.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/distpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/main.js"></script>
<script type="text/javascript">
    function validatelt(inputelement,err){
        if(inputelement.validity.patternMismatch){
            inputelement.setCustomValidity(err);
        }else{
            inputelement.setCustomValidity("");
            return true;
        }
    }
    function check() {
        var excel_file = $("excel_file").val();
        if (excel_file === "" || excel_file.length === 0){
            alert("请选择文件路径！");
            return false
        }else {
            return true;
        }
    }
</script>

</body>
</html>