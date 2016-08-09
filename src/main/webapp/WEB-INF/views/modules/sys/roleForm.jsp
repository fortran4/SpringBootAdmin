<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
</head>
<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>
<!--nav-->
<div class="row">
    <div class="x_panel">
        <div class="x_title">
            <h2><i class="glyphicon glyphicon-th-large"></i>角色管理</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <ul class="nav nav-tabs">
                <li><a href="${ctx}/role/findAll">角色列表</a></li>
                <c:choose>
                    <c:when test="${action == 'edit'}">
                        <li class="active"><a href="${ctx}/role/form/edit?id=${role.roleId}">编辑</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="active"><a href="${ctx}/role/form/add">新增</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <br/>
            <form:form id="inputForm" modelAttribute="role" role="form"
                       action="${ctx}/role/save" method="post" enctype="multipart/form-data"
                       class="form-horizontal form-label-left">
                <form:hidden path="roleId"/>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="roleName">角色名称<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="roleName" cssClass="form-control col-md-7 col-xs-9" maxlength="12"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="remarks">备注<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:textarea path="remarks"></form:textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary">重置</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/common/js.jsp" %>
<%@include file="roleFormJs.jsp"%>
</body>
</html>