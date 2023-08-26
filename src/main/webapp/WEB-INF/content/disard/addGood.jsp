<%@ page import="com.taiquan.domain.order.enums.*" %>
<%@ page import="com.taiquan.domain.order.enums.goodDetailType.*" %>
<%@ page import="com.taiquan.domain.order.enums.unit.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/5 0005
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath",basePath);
%>
<div class="add-good-ban" >
    <h4 class="text-info">添加不锈钢板、卷、带</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addBan.html" modelAttribute="banBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="plainType" class="form-control">
                        <c:forEach var="entry" items="<%=PlainType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:select path="textureType" class="form-control good-texture" id="plain-texture">
                        <c:forEach var="entry" items="<%=TextureType.values()%>">
                            <form:option value="${entry.name()}" id="${entry.density}">${entry.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-225 col-md-225 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">厚度(mm)</span>
                    <form:input path="thick" type="number" step="0.01" id="plain-thick" class="form-control" oninvalid="validatelt(this,'只能输入数字')" />
                </div>
            </div>
            <div class="col-lg-225 col-md-225 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">公差(mm)</span>
                    <form:input path="realThick" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')"/>
                </div>
            </div>
            <div class="col-lg-225 col-md-225 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">宽度(mm)</span>
                    <form:input path="width" type="number" step="1" id="plain-width" class="form-control" oninvalid="validatelt(this,'只能输入数字')" />
                </div>
            </div>
            <div class="col-lg-225 col-md-225 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">长度(mm)</span>
                    <form:input path="length" type="number" id="plain-length" class="form-control"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="banAmount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="plain-amount"/>
                    <form:select path="amountUnit" class="form-control" id="plain-unit">
                        <c:forEach var="entry" items="<%=BanJuanUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">重量(Kg)</span>
                    <form:input path="weight" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-weight"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-in-price" />
                    <span class="input-group-addon">元/</span>
                    <select name="buyUnit" class="form-control" id="plain-in-unit">
                        <c:forEach var="entry" items="<%=BanJuanUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-out-price"/>
                    <span class="input-group-addon">元/</span>
                    <select name="salUnit" class="form-control" id="plain-out-unit">
                        <c:forEach var="entry" items="<%=BanJuanUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-in-money"/>
                </div>
            </div>

            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">出货金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-out-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="plain-profit"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">产地</span>
                    <form:input path="chanDi"  class="form-control" />
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 name-tip">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="add-good-guan" >
    <h4 class="text-info">添加不锈钢管</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addGuan.html" modelAttribute="guanBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="pupeType" class="form-control">
                        <c:forEach var="entry" items="<%=PupeType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:select path="textureType" class="form-control good-texture" id="guan-texture">
                        <c:forEach var="entry" items="<%=TextureType.values()%>">
                            <form:option value="${entry.name()}" id="${entry.density}">${entry.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">规格(mm)</span>
                    <form:input path="spec" class="form-control"  id="guan-spec"/>
                </div>
                <div class="input-group" hidden="hidden">
                    <span class="input-group-addon">外径(mm)</span>
                    <form:input path="diameter" type="number" step="0.1" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-diameter"/>
                </div>
                <div class="input-group" hidden="hidden">
                    <span class="input-group-addon">外径2(mm)</span>
                    <form:input path="diameter2" type="number" step="0.1" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-diameter2"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6" hidden="hidden">
                <div class="input-group">
                    <span class="input-group-addon">厚度(mm)</span>
                    <form:input path="thick" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-thick"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">公差(mm)</span>
                    <form:input path="realThick" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-realThick"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">每只长度(mm)</span>
                    <form:input path="length" type="number" step="1" oninvalid="validatelt(this,'只能输入数字')" class="form-control" id="guan-length"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="amount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="guan-amount"/>
                    <form:select path="amountUnit" class="form-control" id="guan-unit">
                        <c:forEach var="entry" items="<%=GuanUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">重量(Kg)</span>
                    <form:input path="weight" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-weight"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-in-price"/>
                    <span class="input-group-addon">元/</span>
                    <select name="buyUnit" class="form-control" id="guan-in-unit">
                        <c:forEach var="entry" items="<%=GuanUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-out-price" />
                    <span class="input-group-addon">元/</span>
                    <select name="salUnit" class="form-control" id="guan-out-unit">
                        <c:forEach var="entry" items="<%=GuanUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-in-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">出货金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-out-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="guan-profit"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="add-good-xing" >
    <h4 class="text-info">添加不锈钢型材</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addXing.html" modelAttribute="xingBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="xingCaiType" class="form-control" id="xing-xingtype">
                        <c:forEach var="entry" items="<%=XingCaiType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:select path="textureType" class="form-control good-texture" id="xing-texture">
                        <c:forEach var="entry" items="<%=TextureType.values()%>">
                            <form:option value="${entry.name()}" id="${entry.density}">${entry.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">规格</span>
                    <form:input path="spec" class="form-control" oninvalid="validatelt(this,'只能输入数字')" list="cao-list" id="xing-spec"/>
                </div>
                <datalist id="cao-list">
                    <c:forEach var="entry" items="<%=CaogangType.values()%>">
                        <option value="${entry.name}" id="${entry.weight}">
                    </c:forEach>
                </datalist>
                <datalist id="gong-list">
                    <c:forEach var="entry" items="<%=GongZiType.values()%>">
                        <option value="${entry.name}" id="${entry.weight}">
                    </c:forEach>
                </datalist>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="amount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="xing-amount"/>
                    <form:select path="amountUnit" class="form-control" id="xing-unit">
                        <c:forEach var="entry" items="<%=XingUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">重量(Kg)</span>
                    <form:input path="weight" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-weight" />
                </div>
            </div>
        </div>
        <div class="row">

            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-in-price"/>
                    <span class="input-group-addon">元/</span>
                    <select name="buyUnit" class="form-control" id="xing-in-unit">
                        <c:forEach var="entry" items="<%=XingUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-in-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">出货售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-out-price"/>
                    <span class="input-group-addon">元/</span>
                    <select name="salUnit" class="form-control" id="xing-out-unit">
                        <c:forEach var="entry" items="<%=XingUnitType.values()%>">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-out-money"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="xing-profit"/>
                </div>
            </div>

            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 name-tip name-tip">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-1 col-md-1 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="add-good-ling" >
    <h4 class="text-info">添加不锈钢零部件</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addLing.html" modelAttribute="lingBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="lingBuJianType" class="form-control">
                        <c:forEach var="entry" items="<%=LingBuJianType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:select path="textureType" class="form-control good-texture" id="ling-texture">
                        <c:forEach var="entry" items="<%=TextureType.values()%>">
                            <form:option value="${entry.name()}">${entry.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">规格</span>
                    <form:input path="spec" class="form-control" oninvalid="validatelt(this,'只能输入数字')" />
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="amount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="ling-amount"/>
                    <form:select path="amountUnit" class="form-control" id="ling-unit">
                        <c:forEach var="entry" items="<%=LingUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="ling-in-price"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="ling-out-price"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="ling-in-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="ling-out-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="ling-profit"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 name-tip">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="add-good-jia" >
    <h4 class="text-info">添加不锈钢加工</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addJiaGong.html" modelAttribute="jiaGongBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="jiaGongType" class="form-control">
                        <c:forEach var="entry" items="<%=JiaGongType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="amount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-amount"/>
                    <form:select path="amountUnit" class="form-control">
                        <c:forEach var="entry" items="<%=JiaGongUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-in-price"/>
                </div>
            </div>

            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-out-price"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-in-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">出货金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-out-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="jiagong-profit"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 name-tip">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="add-good-other" >
    <h4 class="text-info">其他产品</h4>
    <hr/>
    <form:form action="${basePath}order/addOrder/addOtherServic.html" modelAttribute="otherServicBean" method="post">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <form:select path="otherServiceType" class="form-control">
                        <c:forEach var="entry" items="<%=OtherServiceType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <form:input path="amount" type="number" class="form-control good-amount" oninvalid="validatelt(this,'只能输入数字')" id="other-amount"/>
                    <form:select path="amountUnit" class="form-control">
                        <c:forEach var="entry" items="<%=OtherServicUnitType.values()%>">
                            <form:option value="${entry.name()}">${entry.name()}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进价</span>
                    <form:input path="buyPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="other-in-price"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">售价</span>
                    <form:input path="salPrice" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="other-out-price"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">进货金额</span>
                    <form:input path="sumOfBuyMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="other-in-money"/>
                </div>
            </div>

            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">出货金额</span>
                    <form:input path="sumOfSalsMoney" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="other-out-money"/>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 ">
                <div class="input-group">
                    <span class="input-group-addon">毛利</span>
                    <form:input path="profit" type="number" step="0.01" class="form-control" oninvalid="validatelt(this,'只能输入数字')" id="other-profit"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 name-tip">
                <div class="input-group input-supplier">
                    <span class="input-group-addon">供应商</span>
                    <form:input path="supplierName"  class="form-control supplier" id="<%=basePath%>" type="text"/>
                </div>
                <div class="tb-supplier">
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                <div class="input-group">
                    <span class="input-group-addon">备注</span>
                    <form:input path="others"  class="form-control" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                <div class="input-group">
                    <input type="submit" value="添加" class="form-control btn-primary">
                </div>
            </div>
        </div>
    </form:form>
</div>