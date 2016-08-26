<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>菜单列表</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
</head>

<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>
<div class="container-fluid">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
            <div class="search_panel">
                <div class="search_content">
                    <form:form id="searchForm" modelAttribute="menu" action="${ctx}/menu/findAll" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

                        <div class="row">
                            <div class="form-group  col-xs-6 col-md-4">
                                <label for="menuName">菜单名称</label>
                                <div class="input-prepend input-group">
                                    <form:input path="menuName" htmlEscape="false" maxlength="50"
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
                    <h2><i class="glyphicon glyphicon-th-large"></i>菜单管理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="table_content">
                    <div class="tabletools">
                         <button type="button" class="btn btn-success">新增</button>
                    </div>
                    <!--data table-->
                    <table id="dataTable" class="table table-striped table-bordered jambo_table bulk_action" cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" id="check-all" class="flat">
                            </th>
                            <th class="column-title">名称</th>
                            <th class="column-title">链接</th>
                            <th class="column-title">排序</th>
                            <th class="column-title">是否隐藏</th>
                            <th class="column-title">权限标识</th>
                            <th class="no-link last"><span class="nobr">操作</span></th>
                            <th class="bulk-actions" colspan="7">
                                <a class="antoo" style="color:#fff; font-weight:500;">( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="menu" items="${page.result}">
                                <tr>
                                    <td>
                                        <input type='checkbox' class='flat' name='item' value='${menu.menuId}'/>
                                    </td>
                                    <td>${menu.menuName}</td>
                                    <td>${menu.href}</td>
                                    <td>${menu.sort}</td>
                                    <td>${menu.isShow}</td>
                                    <td>${menu.permission}</td>
                                    <td>
                                        <a class="fa fa-edit" href="${ctx}/menu/delete/${menu.menuId}">修改</a> |
                                        <a class="fa fa-remove" href="${ctx}/menu/delete/${menu.menuId}">删除</a>
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
        $("#searchForm").attr("action", "${ctx}/menu/findAll");
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>