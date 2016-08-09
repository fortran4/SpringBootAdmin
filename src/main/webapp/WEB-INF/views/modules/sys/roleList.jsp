<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色列表</title>
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
                <li class="active"><a href="${ctx}/role/findAll">角色列表</a></li>
                <li><a href="${ctx}/role/form/add">新增</a></li>
            </ul>
            <br/>
            <!--search form-->
            <form:form id="searchForm" modelAttribute="role" action="${ctx}/role/findAll" method="post"
                       class="form-horizontal form-label-left">
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <label class="control-label col-md-3 col-sm-3 col-xs-3">角色名称</label>
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <form:input path="roleName" htmlEscape="false" maxlength="50"
                                    class="form-control"/>
                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3">
                        <button type="submit" class="btn btn-success">查询</button>
                    </div>
                </div>
            </form:form>
            <br/>
            <!--data table-->
            <table id="dataTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>角色名称</th>
                    <th>备注</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="role">
                    <tr>
                        <td>${role.roleName}</td>
                        <td>${role.remarks}</td>
                        <td>
                            <c:if test="${role.status == '0'}">
                                启用
                            </c:if>
                            <c:if test="${role.status != '0'}">
                                禁用
                            </c:if>
                        </td>
                        <td>
                            <a href="${ctx}/role/form/edit?id=${role.roleId}" class="fa fa-pencil-square-o">修改</a>
                            <a href="${ctx}/role/delete/${role.roleId}" class="fa fa-remove"
                               onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row">
                <div class="col-xs-12 text-right">
                    <nav>${page}</nav>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/common/js.jsp" %>
<script type="javascript">
    $(function(){

    });

    function page(n,s){
        if(n) $("#pageNo").val(n);
        if(s) $("#pageSize").val(s);
        $("#searchForm").attr("action","${ctx}/role/findAll");
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>