<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典</title>
<%@include file="/WEB-INF/views/common/include.jsp"%>
</head>
<body style="background: #F7F7F7">
	<sys:message type="${type}" content="${content}"></sys:message>
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="" role="tabpanel" data-example-id="togglable-tabs">
				<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
					<li class="active"><a href="${ctx}/sysDict/find" role="tab">列表</a>
					</li>
					<li><c:choose>
							<c:when test="${action=='edit'}">
								<a href="${ctx}/sysDict/form?action='edit'&id=${sysDict.id}"
									role="tab">编辑</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/sysDict/form?id=${sysDict.id}" role="tab">新增</a>
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
							<i class="glyphicon glyphicon-th-large"></i>菜单列表
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_conent">
						<form:form id="searchForm" modelAttribute="sysDict"
							action="${ctx}/sysDict/find/" method="post"
							class="form-horizontal form-label-left">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3" for="label">名称
								</label>
								<div class="col-md-3 col-sm-3">
									<input type="text" name="label" id="label" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3" for="dictType">类型
								</label>
								<div class="col-md-3 col-sm-3">
									<input type="text" name="dictType" id="dictType" class="form-control">
								</div>
								<button type="submit" class="btn btn-success">查询</button>
							</div>
						</form:form>
						<table id="dataTable" class="table table-striped table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>值</th>
									<th>类型</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody></tbody>
							<!-- tbody是必须的 -->
						</table>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/include-js.jsp"%>
	<%@include file="/WEB-INF/views/common/include-datatables.jsp"%>
	<script>
		$(document).ready(
				function() {

					var table = $.My.dataTable({
						"ajax" : {
							"url" : "${ctx}/sysDict/findAll",
							"data" : function(d) {
								d.label = $('#label').val();
								d.dataType = $('#dataType').val();
							}
						},
						"columnDefs" : [
								{
									"searchable" : false,
									"orderable" : false,
									"targets" : 0
								},
								{
									"targets" : 4,
									"width" : 200,
									"render" : function(data, type, row) {

										return $.My
												.opColumn({
													action : ['edit','delete' ],
													url : [
															'${ctx}/sysDict/form?action=edit&id='
																	+ data,
															'${ctx}/sysDict/delete?id='
																	+ data ]
												});
									}
								} ],
						"select" : {
							style : 'os',
							selector : 'td:first-child'
						},
						select : true,
						"order" : [ [ 1, "desc" ] ],
						"columns" : [ {
							"data" : "id"
						}, {
							"data" : "label"
						}, {
							"data" : "value"
						}, {
							"data" : "dictType"
						}, {
							"data" : "id"
						} ]
						
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
