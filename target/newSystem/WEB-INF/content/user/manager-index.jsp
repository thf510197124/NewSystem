<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: WIN10
  Date: 2018/11/18
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员首页</title>
    <link rel="stylesheet" href="${basepath}css/bootstrap.css">
    <link rel="stylesheet" href="${basepath}css/main.css">

</head>
<body>
    <jsp:include page="../parts/header.jsp"/>
    <script type="text/javascript" src="${basepath}js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${basepath}js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basepath}js/myscript.js"></script>
    <script type="text/javascript" src="${basepath}js/dynamicTip.js"></script>
</body>
</html>
