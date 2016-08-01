<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典</title>
<%@include file="/WEB-INF/views/common/include.jsp"%>
</head>
<body class="main-bg">
	<sys:message type="${type}" content="${content}"></sys:message>
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="" role="tabpanel" data-example-id="togglable-tabs">
				<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
					<li><a href="${ctx}/sys/sysUser/find" role="tab">列表</a>
					</li>
					<li  class="active"><c:choose>
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
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="listPanel"
					aria-labelledby="home-tab">
					<br>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										<i class="glyphicon glyphicon-th-large"></i>用户信息
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<form:form id="inputForm" modelAttribute="sysUser" role="form"
										action="${ctx}/sys/sysUser/save" method="post"
										class="form-horizontal form-label-left">
										<form:hidden path="id" />

													<div class="item form-group">
													<label for="id"
														class="control-label col-md-3 col-sm-3 col-xs-12"><span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="id" maxLength="11"
																placeholder="请输入"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="userId"
														class="control-label col-md-3 col-sm-3 col-xs-12">用户信息表ID<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="userId" maxLength="11"
																placeholder="请输入用户信息表ID"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="loginName"
														class="control-label col-md-3 col-sm-3 col-xs-12">登录名<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="loginName" maxLength="0"
																placeholder="请输入登录名"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="loginPwd"
														class="control-label col-md-3 col-sm-3 col-xs-12">登录密码<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="loginPwd" maxLength="0"
																placeholder="请输入登录密码"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="lastLoginIp"
														class="control-label col-md-3 col-sm-3 col-xs-12">最近一次登录IP<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="lastLoginIp" maxLength="0"
																placeholder="请输入最近一次登录IP"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="lastLoginDate"
														class="control-label col-md-3 col-sm-3 col-xs-12">最近一次登录时间<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="lastLoginDate" maxLength="0"
																placeholder="请输入最近一次登录时间"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="createBy"
														class="control-label col-md-3 col-sm-3 col-xs-12">创建人ID<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="createBy" maxLength="11"
																placeholder="请输入创建人ID"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="createDate"
														class="control-label col-md-3 col-sm-3 col-xs-12">创建时间<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="createDate" maxLength="0"
																placeholder="请输入创建时间"
																class="form-control col-md-7 col-xs-12" />




													</div>
												</div>
													<div class="item form-group">
													<label for="status"
														class="control-label col-md-3 col-sm-3 col-xs-12">0正常：-1：失效<span
														class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">

															<form:input  path="status" maxLength="6"
																placeholder="请输入0正常：-1：失效"
																class="form-control col-md-7 col-xs-12" />

													</div>
												</div>
										<div class="ln_solid"></div>
											<div class="form-group">
												<div class="col-md-6 col-md-offset-3">
													<shiro:hasPermission name="sys:sysUser:edit">
														<button id="send" type="submit" class="btn btn-success">保存/button>
													</shiro:hasPermission>
													<button type="button" class="btn btn-primary">取消</button>
												</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<%@include file="/WEB-INF/views/common/include-js.jsp"%>
<%@include file="/WEB-INF/views/common/include-custom-validator.jsp"%>
</body>
</html>