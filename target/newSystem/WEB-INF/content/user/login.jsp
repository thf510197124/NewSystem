<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录页面</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/main.css">
    <link rel="stylesheet" href="<%=basePath%>fontawesome/css/font-awesome.css">

    <style>
        i{
            font-size:50px;
        }
    </style>
</head>
<body class="log-body">
<div class="logpage">
    <div class="userlog">
        <form:form action="${basePath}user/login.html" modelAttribute="user" method="post">
            <div class="row big-row">
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div  class="input-group input-group-lg">
                        <span class="input-group-addon">用&nbsp;&nbsp;户&nbsp;&nbsp;名</span>
                        <form:input path="username" class="form-control " type="text" id="username" />
                    </div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
            </div>
            <div class="row big-row">
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div  class="input-group input-group-lg">
                        <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                        <form:input path="password" class="form-control for-width" type="password" id="password" />
                    </div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
            </div>
            <div class="row big-row">
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon">用户身份</span>
                            <%--<form:select path="mgr" class="form-control ">
                                <form:option value="true" selected="selected">管理员</form:option>
                                <form:option value="false">普通用户</form:option>
                            </form:select>--%>
                        <div class="user-diff form-control">
                            <div class="row">
                                <div class="col-6">
                                    <label class="radio-block">
                                        <i class="fa fa-check-square-o login-icon" id="mgr-mgr"></i>
                                        <input name="mgr" class="radio" type="radio" value="true" checked="checked" />管理员
                                    </label>
                                </div>
                                <div class="col-6">
                                    <label class="radio-block">
                                        <i class="fa fa-square-o login-icon" id="mgr-user"></i>
                                        <input name="mgr" class="radio" type="radio" value="false" />用&nbsp;&nbsp;&nbsp;&nbsp;户
                                    </label>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
            </div>
            <div class="row big-row">
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                    <div class="input-group">
                        <button class="btn btn-info" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                    <div class="input-group">
                        <a href="user/register.html" class="btn btn-danger">注册用户</a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myscript.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dynamicTip.js"></script>
</body>
</html>
