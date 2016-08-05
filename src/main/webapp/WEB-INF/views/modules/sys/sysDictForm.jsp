<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="dictApp">
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
					<li><a href="${ctx}/sysDict/find" role="tab">列表</a>
					</li>
					<li  class="active"><c:choose>
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
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="listPanel"
					aria-labelledby="home-tab">
					<br>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										<i class="glyphicon glyphicon-th-large"></i>字典信息
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<form:form id="inputForm" modelAttribute="sysDict" role="form"
										action="${ctx}/sysDict/save" method="post"
										class="form-horizontal form-label-left">
										<form:hidden path="id" />
										<div class="item form-group">
											<label for="value"
												class="control-label col-md-3 col-sm-3 col-xs-12">键值<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:input  path="value"
													required="required"
													placeholder="请输入键值"
													data-validate-pattern="numeric"
													data-validate-minmax="0,100"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="item form-group">
											<label for="label"
												class="control-label col-md-3 col-sm-3 col-xs-12">标签<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:input id="label" path="label"
													required="required" data-validate-length-range="1,50"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="item form-group">
											<label for="dictType"
												class="control-label col-md-3 col-sm-3 col-xs-12">类型<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:input  path="dictType"
													required="required" data-validate-length-range="1,50"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="item form-group">
											<label for="description"
												class="control-label col-md-3 col-sm-3 col-xs-12">描述:</label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:input  path="description"
													htmlEscape="false" data-validate-length-range="1,50"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="item form-group">
											<label for="sort"
												class="control-label col-md-3 col-sm-3 col-xs-12">排序:</label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:input  path="sort"
													data-validate-pattern="numeric"
													data-validate-minmax="1,1000"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="item form-group">
											<label for="remarks"
												class="control-label col-md-3 col-sm-3 col-xs-12">备注:</label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<form:textarea  path="remarks"
													htmlEscape="false" rows="3" data-validate-length-range="1,200"
													class="form-control col-md-7 col-xs-12" />
											</div>
										</div>
										<div class="ln_solid"></div>
											<div class="form-group">
												<div class="col-md-6 col-md-offset-3">
													<button id="send" type="submit" class="btn btn-success">保存</button>
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