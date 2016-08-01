<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户</title>
<%@include file="/WEB-INF/views/common/include-datatables-css.jsp"%>
</head>
<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="" role="tabpanel" data-example-id="togglable-tabs">
				<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
					<li   class="active"><a href="${ctx}/sys/sysUser/find" role="tab">列表</a>
					</li>
					<li><c:choose>
							<c:when test="${action=='edit'}">
								<a href="${ctx}/sys/sysUser/form?action='edit'&id=${sysUser.id}"
									role="tab">编辑</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/sys/sysUser/form?id=${sysUser.id}" role="tab">新增</a>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</div>
		</div>
		<div id="myTabContent" class="tab-content">
			<div role="tabpanel" class="tab-pane fade active in" id="listPanel"
				aria-labelledby="home-tab">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							<i class="glyphicon glyphicon-th-large"></i>用户列表
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_conent">
						    <form:form id="searchForm" modelAttribute="sysUser" action="${ctx}/urlPrefix/find/" method="post" class="form-horizontal form-label-left">

							<button type="submit" class="btn btn-success">查询</button>
							</form:form>
                             <div class="table-responsive">
                                    <table id="dataTable" class="table table-striped table-bordered"
                                        cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>登录名</th>
                                                <th>最近一次登录IP</th>
                                                <th>最近一次登录时间</th>
                                                <th>创建时间</th>
                                                <th>状态</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody>
                                </table>
                            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/include-js.jsp"%>
	<%@include file="/WEB-INF/views/common/include-datatables.jsp"%>
    <script>
		$(function() {
            var table = $.My.dataTable({
                "ajax" : {
                    "url" : "${ctx}/sys/sysUser/findAll",
                    "data" : function(d) {
                    }
                },
                "columnDefs" : [{"searchable" : false,"orderable" : false,"targets" : 0},
                        {
                            "targets" : 6,
                            "width" : 200,
                            "render" : function(data, type, row) {
                                return $.My.opColumn({
                                            action : ['edit','delete'],
                                            url : [
                                                    '${ctx}/sys/sysUser/form?action=edit&id='+ data,
                                                    '${ctx}/sys/sysUser/delete?id='+ data ]
                                        });
                            }
                        } ],
                "select" : true,
                "paging":true,
                "scrollCollapse": true,
                "order" : [ [ 1, "desc" ] ],
                "columns" : [ {"data" : "id"},
                                         {
                                            "data" : "loginName"
                                          }	,
                                         {
                                            "data" : "lastLoginIp"
                                          }	,
                                         {
                                            "data" : "lastLoginDate"
                                          }	,
                                         {
                                            "data" : "createDate"
                                          }	,
                                         {
                                            "data" : "status"
                                          }	,
                                          {"data" : "id"}
                ]
            });
            //隐藏分页下拉列表
            $('#dataTable_length').css('display', 'none');
            //设置序号列
            table.on('order.dt search.dt', function() {
                table.column(0, {
                    search : 'applied',
                    order : 'applied'
                }).nodes().each(function(cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();

            $('#btnSubmit').bind('click', function() {
                table.draw();
            });

            //删除后回调
            $.My.freshDataTable = function(){
                table.draw();
            }

        });
	</script>
</body>
</html>