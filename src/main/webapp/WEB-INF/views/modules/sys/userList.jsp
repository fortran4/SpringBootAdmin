<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户列表</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
</head>
<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>

<div class="container-fluid">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
            <div class="search_panel">
                <div class="search_content">
                    <form:form id="searchForm" modelAttribute="user" action="${ctx}/user/findAll" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

                        <div class="row">
                            <div class="form-group  col-xs-6 col-md-4">
                                <label for="loginName">用户名</label>
                                <div class="input-prepend input-group">
                                    <form:input path="loginName" htmlEscape="false" maxlength="50"
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
                    <h2><i class="glyphicon glyphicon-th-large"></i>用户管理</h2>
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
                            <th class="column-title">登录名</th>
                            <th class="column-title">真实姓名</th>
                            <th class="column-title">角色</th>
                            <th class="column-title">电话</th>
                            <th class="column-title">邮箱</th>
                            <th class="column-title">性别</th>
                            <th class="column-title">生日</th>
                            <th class="no-link last"><span class="nobr">操作</span></th>
                            <th class="bulk-actions" colspan="9">
                                <a class="antoo" style="color:#fff; font-weight:500;">( <span
                                        class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${page.result}">
                            <tr>
                                <td>
                                    <input type='checkbox' class='flat' name='item' value='${user.userId}'/>
                                </td>
                                <td>${user.loginName}</td>
                                <td>${user.realName}</td>
                                <td>${user.roleName}</td>
                                <td>${user.phone}</td>
                                <td>${user.email}</td>
                                <td>${user.gender}</td>
                                <td>${user.birthday}</td>
                                <td>
                                    <a href="${ctx}/user/form/edit?id=${role.roleId}" class="fa fa-edit">修改</a> |
                                    <a href="${ctx}/user/delete/${role.roleId}" class="fa fa-remove"
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
        $("#searchForm").attr("action", "${ctx}/user/findAll");
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>