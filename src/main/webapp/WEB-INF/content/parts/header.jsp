<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basepath",basePath);
%>

<nav class="navbar navbar-expand-md bg-light navbar-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
        <ul class="navbar-nav nav-pills">
            <li class="nav-item">
                <a class="navbar-brand" href="#">
                    <img src="${basepath}img/logo.png" alt="logo" style="width:40px;">
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">客户管理</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${basepath}company/addCompany.html">添加客户</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}company/allCustomerList.html">所有客户</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}customer/todayContacted.html">今日成果</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}customer/queryCustomer.html">客户查询</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}customer/needTodayContact.html">今日计划</a>
                    <div class="dropdown-divider"></div>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">供应商管理</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${basepath}supply/addSupplier.html">添加供应商</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}supply/allSuppliers.html">供应商列表</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${basepath}supply/querySupplier.html">供应商查询</a>
                    <div class="dropdown-divider"></div>
                </div>
            </li>
            <c:if test="${user.mgr}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">用户管理</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${basepath}user/passUser.html">员工批复</a>
                        <div class="dropdown-divider"></div>
                    </div>
                </li>
            </c:if>
            <li class="nav-item nav-serch name-tip" id="<%=basePath%>" >
                <form action="${basepath}company/getCustomerByName.html" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control input-group-sm" id="in-name" name="companyName" placeholder="客户查询" list="tbcontent"/>
                        <button class="btn btn-info input-group-append search-name" type="submit">搜索</button>
                    </div>
                </form>
                <div id="tbcontent">
                </div>
            </li>

            <li class="nav-item">
                <div class="input-group">
                    <span class="input-group-append">&nbsp;</span>
                    <span class="form-control nav-user">
                        <c:if test="${user.mgr}">管理员</c:if>
                        <c:if test="${!user.mgr}">用户</c:if>${user.name},您好
                    </span>
                    <a href="<%=basePath%>user/logout.html" class="btn btn-info logout input-group-append">退出</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
