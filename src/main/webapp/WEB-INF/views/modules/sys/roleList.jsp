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


<div class="container-fluid">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
            <div class="search_panel">
                <div class="search_content">
                    <form:form id="searchForm" modelAttribute="role" action="${ctx}/role/findAll" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

                        <div class="row">
                            <div class="form-group  col-xs-6 col-md-4">
                                <label for="roleName">角色名称</label>
                                <div class="input-prepend input-group">
                                    <form:input path="roleName" htmlEscape="false" maxlength="50"
                                                class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group  col-xs-6 col-md-4">
                                <button type="submit" class="btn btn-success">查询</button>
                            </div>

                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
            <div class="table_panel">
                <div class="table_title">
                    <h2><i class="glyphicon glyphicon-th-large"></i>角色管理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="table_content">
                    <div class="tabletools">
                        <button type="button" class="btn btn-success">新增</button>
                    </div>
                    <!--data table-->
                    <table id="dataTable" class="table table-striped table-bordered jambo_table bulk_action"
                           cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" id="check-all" class="flat">
                            </th>
                            <th class="column-title">角色名称</th>
                            <th class="column-title">备注</th>
                            <th class="column-title">状态</th>
                            <th class="no-link last"><span class="nobr">操作</span></th>
                            <th class="bulk-actions" colspan="5">
                                <a class="antoo" style="color:#fff; font-weight:500;">( <span
                                        class="action-cnt"> </span> ) <i
                                        class="fa fa-chevron-down"></i></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.result}" var="role">
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
                                    <a href="${ctx}/role/form/edit?id=${role.roleId}" class="fa fa-edit">修改</a> |
                                    <a href="${ctx}/role/delete/${role.roleId}" class="fa fa-remove"
                                       onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="page">${page}</div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/common/js.jsp" %>
<script>
    $(function () {

    });

    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/role/findAll");
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>