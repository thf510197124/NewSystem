<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.taiquan.domain.order.enums.goodDetailType.GoodDetailType" %>
<%@ page import="com.taiquan.domain.order.Texture" %>
<%@ page import="com.taiquan.domain.order.enums.TextureType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(date);
    session.setAttribute("basepath",basePath);
%>

<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>客户管理系统</title>
    <link rel="stylesheet" href="${basepath}css/bootstrap.css">
    <link rel="stylesheet" href="${basepath}css/main.css">
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="container">
    <div class="text-title">
        <h3 class="text-center text-danger">客户查询</h3>
    </div>
    <hr/>
    <!--关键字查询-->
    <form  action="<%=basePath%>supply/queryByKeyWords.html" method="get">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 name-tip">
                <h4 class="text-success">供应商名称查询</h4>
                <div class="input-group">
                    <input type="text" class="form-control supplier" placeholder="请输入供应商关键字" name="supplyName" id="<%=basePath%>"/>
                    <button class="btn btn-success input-group-append" type="submit">查询</button>
                </div>
                <div class="tb-supplier"></div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>supply/queryByGoodAndTextureType.html" method="get">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4 class="text-success">根据供应产品和材质查询</h4>
                <c:if test="${queryByGoodAndTextureTypeError !=null && queryByGoodAndTextureTypeError != '' }">
                    <h4 class="text-danger">${queryByGoodAndTextureTypeError}</h4>
                </c:if>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">具体产品及材质</span>
                    </div>
                    <input class="form-control search-detail-good-type" list="goodType-list" name="goodDetailType">
                    <datalist id="goodType-list">
                        <c:forEach items="<%=GoodDetailType.values()%>" var="entry">
                            <option value="${entry.name()}">${entry.name()}</option>
                        </c:forEach>
                    </datalist>
                    <input class="form-control search-texture" list="textureType-list" id="textureType" name="textureType">
                    <datalist id="textureType-list">
                        <c:forEach var="entry" items="${textures}">
                            <option value="${entry.textureName}">${entry.textureName}</option>
                        </c:forEach>
                    </datalist>
                    <input type="submit" class="btn btn-success" value="查询"/>
                </div>
            </div>
        </div>
    </form>
    <hr/>
    <form action="<%=basePath%>supply/addTexture.html" method="post">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4 class="text-success">添加不锈钢材质</h4>
                <c:if test="${addTextMsg != null}">
                    <h5 class="text-center text-danger">${addTextMsg}</h5>
                </c:if>
                <div class="input-group">
                    <input class="form-control add-texture" id="texture-name" list="texture-name-list" placeholder="输入材质名称" name="textureName">
                    <datalist id="texture-name-list">
                        <c:forEach var="entry" items="${textures}">
                            <option value="${entry.textureName}">${entry.textureName}</option>
                        </c:forEach>
                    </datalist>
                    <input class="form-control add-texture" id="texture-density" placeholder="输入密度" name="density" type="number" step="0.01">
                    <input class="form-control add-texture" id="texture-alias" placeholder="输入别名" name="alias">
                    <input class="form-control add-texture" id="texture-guobiao" placeholder="输入国标材质" name="guobiao">
                    <input type="submit" class="btn btn-success" value="添加"/>
                </div>
            </div>
        </div>
    </form>

</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myscript.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dynamicTip.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/distpicker.data.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/distpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/distpicker/main.js"></script>
<script type="text/javascript">
    function validatelt(inputelement,err){
        if(inputelement.validity.patternMismatch){
            inputelement.setCustomValidity(err);
        }else{
            inputelement.setCustomValidity("");
            return true;
        }
    }
</script>

</body>
</html>