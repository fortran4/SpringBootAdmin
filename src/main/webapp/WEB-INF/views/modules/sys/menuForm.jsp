<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>菜单</title>
	<%@include file="/WEB-INF/views/common/css.jsp" %>
	<link href="" rel="stylesheet">
</head>
<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>
<!--nav-->
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2><i class="glyphicon glyphicon-th-large"></i>菜单管理</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<ul class="nav nav-tabs">
				<li><a href="${ctx}/menu/findAll">菜单列表</a></li>
				<c:choose>
					<c:when test="${action == 'edit'}">
						<li class="active"><a href="${ctx}/menu/form/edit?id=${menu.menuId}">编辑</a></li>
					</c:when>
					<c:otherwise>
						<li class="active"><a href="${ctx}/menu/form/add">新增</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<br/>
			<form:form id="inputForm" modelAttribute="menu" role="form"  action="${ctx}/menu/save/${action}" method="post"
					   class="form-horizontal form-label-left">
				<form:hidden path="menuId"/>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="menuName">菜单名称<span
							class="required">*</span>
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="menuName" cssClass="form-control col-md-7 col-xs-9" maxlength="20"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="parentId">上级菜单<span
							class="required">*</span>
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="parentId" cssClass="form-control col-md-7 col-xs-9" maxlength="12"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="href">链接<span
							class="required">*</span>
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="href" cssClass="form-control col-md-7 col-xs-9" maxlength="50"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="sort">排序
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="sort" cssClass="form-control col-md-7 col-xs-9" maxlength="50"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="target">目标
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="target" cssClass="form-control col-md-7 col-xs-9" maxlength="50"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">是否隐藏</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div id="isShow" class="btn-group" data-toggle="buttons">
							<label class="btn btn-default" data-toggle-class="btn-primary"
								   data-toggle-passive-class="btn-default">
								<input type="radio" name="isShow" value="1"> 是
							</label>
							<label class="btn btn-primary" data-toggle-class="btn-primary"
								   data-toggle-passive-class="btn-default">
								<input type="radio" name="isShow" value="0"> 否
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="permission">权限标识
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form:input path="permission" cssClass="form-control col-md-7 col-xs-9" maxlength="50"/>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<button type="button" class="btn btn-primary">重置</button>
						<button type="submit" class="btn btn-success">提交</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/common/js.jsp" %>
<%@include file="menuFormJs.jsp"%>
</body>
</html>