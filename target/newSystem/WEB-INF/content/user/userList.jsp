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
    <title>用户批复</title>
    <link rel="stylesheet" href="${basepath}css/bootstrap.min.css">
    <link rel="stylesheet" href="${basepath}css/main.css">
</head>
<body>
    <jsp:include page="../parts/header.jsp"/>
    <div class="container">
        <div class="row text-center">
            <h4 class="text text-success text-center">员工列表</h4>
        </div>
        <c:forEach var="user" items="${users}">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                    <span class="glyphicon glyphicon-user"></span>
                    <span class="user-li">${user.name}</span>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                    <span class="glyphicon glyphicon-remove"></span>
                    <span class="">
                    </span>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                    <span class="glyphicon glyphicon-remove"></span>
                    <span class="">
                        <c:if test="${!user.passed}">
                            <a id="<%=basePath%>user/passUser/${user.userId}.html" href="#" onclick="passUser();return false" class="btn btn-success passUser">
                                批准
                            </a>
                        </c:if>
                        <c:if test="${user.passed}">
                             <a id="<%=basePath%>user/deleteUser/${user.userId}.html" href="#" onclick="deleteUser();return false" class="btn btn-danger deleteUser">
                            删除
                        </a>
                        </c:if>
                    </span>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-remove"></span>
                    <span class="">
                        <button class="btn btn-info userDetail" data-toggle="modal" data-target="#modal-userDetail" id="<%=basePath%>user/userDetails/${user.userId}.json">详情</button>
                    </span>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="modal fade" id="modal-userDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title text-center" id="myModalLabel"></h4>
                </div>
                <div class="modal-body">
                    <jsp:include page="../parts/userDetails.jsp"/>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/myscript.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/dynamicTip.js"></script>
</body>
</html>
