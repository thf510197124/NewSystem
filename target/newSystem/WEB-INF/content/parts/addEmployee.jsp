<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.customerEnums.PositionType" %>
<%@ page import="com.taiquan.domain.customerEnums.PhoneType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    session.setAttribute("basePath",basePath);
%>
<form:form modelAttribute="employeeBean" id="${basePath}/${empController}/addEmployee.json" class="addEmployee-form">
    <h5 class="text-center text-info">添加联系人</h5>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="input-group">
                <span class="input-group-addon">姓名</span>
                <form:input type="text" class="form-control" placeholder="姓名" path="name"  id="emp-name"/>
                <span class="input-group-addon">性别</span>
                <form:select class="form-control" path="gendarMale"  id="emp-gendarMale">
                    <form:option value="false">男</form:option>
                    <form:option value="true">女</form:option>
                </form:select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="input-group">
                <span class="input-group-addon">职位</span>
                <form:select class="form-control" path="positionType" id="emp-positionType">
                    <c:forEach var="entry" items="<%=PositionType.values()%>">
                        <option value="${entry.ordinal()}">${entry.name()}</option>
                    </c:forEach>
                </form:select>
                <span class="input-group-addon">年龄</span>
                <form:select class="form-control" path="age" id="emp-age">
                    <form:option value="未知" selected="selected">未知</form:option>
                    <form:option value="30岁以下">30岁以下</form:option>
                    <form:option value="30~50岁">30~50岁</form:option>
                    <form:option value="50岁以下">50岁以下</form:option>
                </form:select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="input-group">
                <span class="input-group-addon">邮箱</span>
                <form:input type="email" class="form-control" placeholder="邮箱" path="email" id="emp-email"/>
            </div>
        </div>
    </div>
    <div class="row contactPad">
       <%-- <c:forEach varStatus="index" var="contacts" items="${employeeBean.contactes}" ><!--读取到的是空的，不显示一下内容-->--%>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="input-group">
                    <form:select class="input-group-addon" path="contactes[0].phoneType" id="emp-phoneType">
                        <c:forEach var="entry" items="<%=PhoneType.values()%>">
                            <option value="${entry.ordinal()}">${entry.name()}</option>
                        </c:forEach>
                    </form:select>
                    <form:input type="text" class="form-control" placeholder="联系号码（数字）" path="contactes[0].phoneNumber"  id="emp-name"/>
                </div>
            </div>
        <%--</c:forEach>--%>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 com-xs-12">
            <div class="input-group">
                <span class="input-group-addon">备注</span>
                <form:textarea name="contactDetails" id="form-contactDetails" type="textarea" placeholder="备注或特殊号码"
                               class="form-control" rows="2" path="others"/>
            </div>
        </div>
    </div>
    <%--//可以把10个list集合都展示出来，然后通过添加联系号码逐次展示每个表单框；看起来不行--%>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
            <div class="input-group">
                <button type="button" class="form-control btn-success" id="addContacts">添加联系方式</button>
            </div>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-1 col-xs-1"></div>
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
            <div class="input-group">
                <button class="form-control btn-success" type="button" id="addEmployee">添加</button>
            </div>
        </div>
    </div>
</form:form>
