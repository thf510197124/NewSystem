<%@ page import="com.taiquan.domain.customerEnums.PositionType" %>
<%@ page import="com.taiquan.domain.customerEnums.PhoneType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
%>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <h5 class="text-info text-center">联系人</h5><hr/>
    </div>
    <c:forEach var="employee" items="${employees}" varStatus="status">
        <div class="employee col-lg-6 col-md-6 col-sm-12 col-xs-12" id="${status.index}-emp">
                <h6 class="text-info">联系人${status.index + 1}</h6>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <%--<span class="input-group-addon"></span>--%>
                            <span type="text" class="form-control input">${employee.name}</span>
                            <span class="input-group-addon">性别</span>
                            <span class="form-control input">
                                <c:if test="${employee.gendarMale eq false}">男</c:if>
                                <c:if test="${employee.gendarMale eq true}">女</c:if>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">职位</span>
                            <span class="form-control input auto-hidden">${employee.positionType}</span>
                            <span class="input-group-addon">年龄</span>
                            <span class="form-control input auto-hidden">${employee.age}</span>
                        </div>
                    </div>
                </div>
                <c:if test="${employee.email != null}">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">邮箱</span>
                                <span type="email" class="form-control input">${employee.email}</span>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:forEach var="contact" items="${employee.contacts}" varStatus="index">
                    <div class="row contacstPad">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon input" >${contact.phoneType}</span>
                                <span type="text" class="form-control input phone-number" >${contact.phoneNumber}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${employee.others != null}">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">备注</span>
                                <span type="text" class="form-control input">${employee.others}</span>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">添加时间</span>
                            <span type="email" class="form-control input">${employee.addDate}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-4 col-md-4 col-sm-5 col-xs-5">
                        <div class="input-group">
                            <button class="form-control btn-danger deleteEmployee" type="button" id="${basePath}/${empController}/deleteEmployee/${employee.employeeId}.html">删除</button>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="col-lg-4 col-md-4 col-sm-5 col-xs-5">
                        <div class="input-group">
                            <button class="form-control btn-success updateEmployee" type="button" id="${basePath}/${empController}/updateEmployee/${employee.employeeId}.html">修改</button>
                        </div>
                    </div>
                </div>
            </div>
        <div class="employee hidden-employee col-lg-6 col-md-6 col-sm-12 col-xs-12" id="${status.index}-hidEmp">
                <h6 class="text-info">修改联系人</h6>
                <form class="updateEmp-form">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">姓名</span>
                                <input type="text" class="form-control" placeholder="姓名" name="name"  id="emp-name" value="${employee.name}"/>
                                <span class="input-group-addon">性别</span>
                                <select class="form-control" name="gendarMale"  id="emp-gendarMale">
                                    <option value="true" ${employee.gendarMale eq true ? 'selected':''}>女</option>
                                    <option value="false" ${employee.gendarMale eq false ? 'selected':''}>男</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">职位</span>
                                <select class="form-control" name="positionType" id="emp-positionType">
                                    <c:forEach var="entry" items="<%=PositionType.values()%>">
                                        <option value="${entry.ordinal()}" ${employee.positionType.ordinal() eq entry.ordinal() ? 'selected':''}>
                                                ${entry.name()}
                                        </option>
                                    </c:forEach>
                                </select>
                                <span class="input-group-addon">年龄</span>
                                <select class="form-control" name="age" id="emp-age">
                                    <option value="30岁以下" ${employee.age eq '30岁以下' ? 'selected':''}>30岁以下</option>
                                    <option value="30~50岁" ${employee.age eq '30~50岁' ? 'selected':''}>30~50岁</option>
                                    <option value="50岁以下" ${employee.age eq '50岁以下' ? 'selected':''}>50岁以下</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row emp-email">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">邮箱</span>
                                <input type="email" class="form-control" placeholder="邮箱" name="email" id="emp-email" value="${employee.email}"/>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${employee.contacts}" var="contact" varStatus="index">
                        <div class="row contact-update" id="contact-update">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="input-group">
                                    <input type="text" name="contactes[${index.index}].contactsId" value="${contact.contactsId}" id="contactId" hidden/>
                                    <select class="input-group-addon" name="contactes[${index.index}].phoneType" id="emp-phoneType">
                                        <c:forEach var="entry" items="<%=PhoneType.values()%>">
                                            <option value="${entry.ordinal()}" ${contact.phoneType.ordinal() eq entry.ordinal() ? 'selected':''}>${entry.name()}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="text" class="form-control" placeholder="联系号码" name="contactes[${index.index}].phoneNumber"  value="${contact.phoneNumber}"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 com-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon">备注</span>
                                <input id="form-contactDetails" type="textarea" placeholder="备注或特殊号码等"
                                          class="form-control" rows="2" name="others" value="${employee.others}">
                            </div>
                        </div>
                    </div>
                    <%--//可以把10个list集合都展示出来，然后通过添加联系号码逐次展示每个表单框；看起来不行--%>
                    <div class="row">
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            <div class="input-group">
                                <button type="button" class="form-control btn-success addUpdateContacts">添加号码</button>
                            </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-1 col-xs-1"></div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            <div class="input-group">
                                <button class="form-control btn-success updateEmpPost" type="button" id="${basePath}/${empController}/updateEmployee/${employee.employeeId}.json">修改</button>
                            </div>
                        </div>

                    </div>
                </form>
            </div>

    </c:forEach>
</div>