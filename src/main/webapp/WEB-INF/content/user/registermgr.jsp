<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: WIN10
  Date: 2018/11/18
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
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
    <title>管理员注册</title>
</head>
<body>
<h4>管理员注册</h4>
    <form:form action="${basepath}user/registermgr.html" modelAttribute="manager" method="post">
        <div>
            <label for="username">用户名</label>
            <form:input path="username" type="text" id="username" />
        </div>
        <div>
            <label for="password">密码</label>
            <form:input path="password" type="password" id="password"/>
        </div>
        <div>
            <label for="name">姓名</label>
            <form:input path="name" type="text" id="password"/>
        </div>
        <div>
            <label for="phoneNumber">手机号</label>
            <form:input path="phoneNumber" type="text" id="password"/>
        </div>
        <div>
            <input type="submit" value="注册"/>
        </div>
    </form:form>
</body>
</html>
