<%@ page import="com.taiquan.domain.customerEnums.CustomerType" %>
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
%>
<form:form modelAttribute="contDetailsBean" id="${basePath}customer/addContactDetail.html" class="contDetailsForm">
    <h5 class="text-center text-info">添加联系情况</h5>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="input-group">
                <span class="input-group-addon">客户类型</span>
                <form:select class="form-control callbackCustomerType" path="customerType" id="form-customerType" value="${customerType}">
                    <c:forEach var="entry" items="<%=CustomerType.values()%>">
                        <c:if test="${customerType == entry.ordinal()}">
                            <option value="${entry.ordinal()}" selected="selected">${entry.name()}</option>
                        </c:if>
                        <c:if test="${customerTpe != entry.ordinal()}">
                            <option value="${entry.ordinal()}">${entry.name()}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="input-group">
                <span class="input-group-addon">下次联系</span>
                <%--<form:input type="date" class="form-control" placeholder="请输入下次联系的日期" path="nextDate" min="<%=today%>" value="2050-12-31" id="form-nextDate"/>--%>
                <form:input type="text" class="form-control form_next callbackNextDate" data-date-format="yyyy-mm-dd" value="${nextDate}" path="nextDate" id="form-nextDate"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 com-xs-12">
            <div class="input-group">
                <span class="input-group-addon">联系详情</span>
                <form:textarea  id="form-contactDetails" type="textarea" placeholder="请输入联系详情"
                               class="form-control" rows="2" path="contactDetails"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-2 com-xs-2"></div>
        <div class="col-lg-6 col-md-6 col-sm-8 com-xs-8">
            <div class="input-group">
                <button type="button" class="form-control btn-success" id="addContactDetails">添加</button>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-2 com-xs-2"></div>
    </div>
</form:form>
