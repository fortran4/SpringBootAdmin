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
<!--nav-->
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2><i class="glyphicon glyphicon-th-large"></i>日志管理</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/log/findAll">日志列表</a></li>
			</ul>
			<br/>
			<!--search form-->
			<form:form id="searchForm" modelAttribute="log" action="${ctx}/log/findAll" method="post"
					   class="form-horizontal form-label-left">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<label class="control-label col-md-3 col-sm-3 col-xs-3">日志类型</label>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<form:input path="type" htmlEscape="false" maxlength="50"
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
			<table id="dataTable" class="table table-striped table-bordered table-responsive table-hover" cellspacing="0" width="100%">
				<thead>
				<tr>
					<th>请求链接</th>
					<th>请求方法</th>
					<th>请求参数</th>
					<th>请求终端</th>
					<th>请求终端IP</th>
					<th>响应信息</th>
					<th>异常信息</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="log">
					<tr>
						<td>${log.requestUri}</td>
						<td>${log.method}</td>
						<td>${log.params}</td>
						<td>${log.userAgent}</td>
						<td>${log.remoteAddr}</td>
						<td>${log.response}</td>
						<td>${log.exception}</td>
						<td>
							<a href="${ctx}/log/form/view?id=${log.logId}" class="fa fa-pencil-square-o">查看</a>
							<a href="${ctx}/log/delete/${log.logId}" class="fa fa-remove"
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
		$("#searchForm").attr("action","${ctx}/log/findAll");
		$("#searchForm").submit();
		return false;
	}
</script>
</body>
</html>