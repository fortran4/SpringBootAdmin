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
<div class="container-fluid">
    <div class="row">
        <sys:message type="${type}" content="${content}"></sys:message>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
            <div class="search_panel">
                <div class="search_content">
                    <form id="searchForm" action="${ctx}/role/findAll" method="post"
                          class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

                        <div class="row">
                            <div class="form-group  col-xs-6 col-md-4">
                                <div class="input-prepend input-group">
                                    <input name="roleName" id="roleNameQuery" htmlEscape="false" maxlength="50"
                                           class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group  col-xs-6 col-md-4">
                                <button type="submit" class="btn btn-success">查询</button>
                            </div>

                        </div>
                    </form>
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
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#roleForm">新增
                        </button>
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
                                <td>
                                    <input type='checkbox' class='flat' name='item' value='${role.roleId}'/>
                                </td>
                                <td>${role.roleName}</td>
                                <td>${role.remarks}</td>
                                <td>
                                    <c:if test="${role.status == '0'}">
                                        <span class="label label-success">启用</span>
                                    </c:if>
                                    <c:if test="${role.status != '0'}">
                                        <span class="label label-danger">禁用</span>
                                    </c:if>
                                </td>
                                <td>
                                    <a href="${ctx}/role/form/edit?id=${role.roleId}" class="fa fa-edit">修改</a>
                                    <c:if test="${role.status == '0'}">
                                        | <a href="javascript:;" class="fa fa-remove"
                                           onclick="return confirmx('确认要禁用该角色吗？', '${ctx}/role/stop/${role.roleId}')">禁用</a>
                                    </c:if>
                                    <c:if test="${role.status != '0'}">
                                        | <a href="javascript:;" class="fa fa-remove"
                                             onclick="return confirmx('确认要启用该角色吗？', '${ctx}/role/start/${role.roleId}')">启用</a>
                                    </c:if>
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

<!--add form begin-->
<div id="roleForm" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form:form id="addForm" modelAttribute="role" role="form" action="${ctx}/role/save" method="post"
                       class="form-horizontal form-label-left">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增角色</h4>
                </div>
                <div class="modal-body">
                    <form:hidden path="roleId"/>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="roleName">角色名称<span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input path="roleName" placeholder="请输入角色名称" class="form-control col-md-7 col-xs-9"
                                        maxlength="30"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="remarks">备注<span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:textarea path="remarks" placeholder="请输入备注" class="form-control" maxlength="200"
                                           rows="3"></form:textarea>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!--//add form end-->


<%@include file="/WEB-INF/views/common/js.jsp" %>
<script>
    $(function () {
        $('#addForm').formValidation({
            fields: {
                roleName: {
                    validators: {
                        notEmpty: {
                            message: '请输入角色名称'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '角色名称必须在2-30个字符之间'
                        }
                    }
                },
                remarks: {
                    validators: {
                        notEmpty: {
                            message: '请输入备注'
                        }, stringLength: {
                            min: 1,
                            max: 200,
                            message: '备注必须在1-200个字符之间'
                        }
                    }
                }
            }
        });

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