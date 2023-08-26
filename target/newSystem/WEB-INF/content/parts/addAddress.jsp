<%@ page import="com.taiquan.domain.customerEnums.AddressType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
%>

<div class="row address-form" id="${basePath}company/addAddress.json">
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="input-group">
            <span class="input-group-addon">地址类型</span>
            <select name="addressType" class="form-control" id="addressType">
                <c:forEach items="<%=AddressType.values()%>" var="addressType">
                    <option value="${addressType.ordinal()}">${addressType.name()}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
        <div class="input-group">
            <span class="input-group-addon">客户地址</span>
            <input type="text" class="form-control address-string" placeholder="请输入注册地址" name="simple" id="${basePath}validate/address.json"/>
        </div>
    </div>
    <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12">
        <div class="float-right">
            <button class="addAddress-submit btn btn-success" type="button">添加</button>
        </div>
    </div>
</div>

