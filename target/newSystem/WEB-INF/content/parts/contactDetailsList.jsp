<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
%>
<h5 class="text-info">联系记录</h5><hr/>
<c:forEach var="cd" items="${contactDetails}">
    <div class="contact-details">
        <div class="contact-day-details">
            <span class="h6">${cd.addDate}:&nbsp;&nbsp;</span>
            <span class="details-details">${cd.contactDetails}</span>
        </div>
        <div class="">
            <div class="text-right h6">
                <a id="<%=basePath%>customer/deleteContactDetails/${cd.contactDetailId}.html" class="btn btn-danger btn-sm details-delete">删除</a>
            </div>
        </div>
    </div>
</c:forEach>
