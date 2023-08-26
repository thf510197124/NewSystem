<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basepath",basePath);
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户注册</title>
    <link rel="stylesheet" href="${basepath}css/bootstrap.min.css">
    <link rel="stylesheet" href="${basepath}css/main.css">

</head>
<body class="log-body">
    <div class="logpage">
        <div class="userlog">
            <form:form action="${basepath}/user/register.html" modelAttribute="user1" method="post">
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">用&nbsp;&nbsp;户&nbsp;&nbsp;名</span>
                            <form:input class="form-control" path="username" type="text" id="username"/>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                            <form:input class="form-control" path="password" type="password" id="password"/>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                            <input class="form-control" name="repasswordConfirm" type="password" id="repassword"/>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
                            <form:input class="form-control" path="name" type="text" id="name"/>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">手机号&nbsp;码</span>
                            <form:input class="form-control" path="phoneNumber" type="text" id="phoneNumber"/>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">管&nbsp;&nbsp;理&nbsp;&nbsp;&nbsp;员</span>
                            <select name="mgrname" class="form-control" id="mgrname">
                                <c:forEach var="mgrname" items="${mgrnames}">
                                    <option value="${mgrname}">${mgrname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                </div>
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <div class="input-group input-group-lg">
                            <button type="submit" class="btn btn-success">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <div class="input-group input-group-lg">
                            <button type="button" onclick="submitMgr()" class="btn btn-success">注册管理员</button>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
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
