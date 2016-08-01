<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="menuApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单管理</title>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<link rel="stylesheet"
	href="/jquery-ztree/3.5.12/css/metroStyle/metroStyle.css"
	type="text/css">
</head>
<body style="background: #F7F7F7">

	<div class="row">

		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="" role="tabpanel" data-example-id="togglable-tabs">
				<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
					<li><a href="${ctx}/sysMenu/find" role="tab">列表</a></li>
					<li class="active"><c:choose>
							<c:when test="${action=='edit'}">
								<a href="${ctx}/sysMenu/form?action='edit'&id=${sysMenu.id}"
									role="tab">编辑</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/sysMenu/form?id=${sysMenu.id}" role="tab">新增</a>
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
										<i class="glyphicon glyphicon-th-large"></i>菜单信息
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

									<div class="col-md-3 col-sm-3 col-xs-3">
										<ul id="tree" class="ztree"></ul>
									</div>

									<div class="col-md-6 col-sm-6 col-xs-6">
										<form:form id="inputForm" modelAttribute="sysMenu"
											action="${ctx}/sysMenu/save" method="post"
											class="form-horizontal form-label-left">
											<form:hidden path="id" />
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="name"> 名称 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="name"
														class="form-control col-md-7 col-xs-12"
														data-validate-length-range="2" placeholder="请输入菜单名称"
														required="required" />
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="parentId">上级菜单 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<input name="parentName" required="required"
														placeholder="从菜单树中选择上级节点"
														class="form-control col-md-7 col-xs-12"
														readonly="readonly" />
													<form:hidden path="parentId" />
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="href">链接<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="href" required="required"
														class="form-control col-md-7 col-xs-12" />
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="number">目标 </label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="target"
														class="form-control col-md-7 col-xs-12" />
												</div>
											</div>
											<%-- <div class="item form-group">
					                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">图标
					                        </label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
					                          <form:input  path="icon" required="required" placeholder="请选择适合的图标" class="form-control col-md-7 col-xs-12"/>
					                        </div>
					                      </div> --%>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="occupation">排序<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="sort" required="required"
														data-validate-pattern="numeric"
														data-validate-minmax="1,1000"
														class="optional form-control col-md-7 col-xs-12 " />
												</div>
											</div>
											<div class="item form-group">
												<label for="isShow" class="control-label col-md-3">是否可见</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													是: <input type="radio" class="flat" name=isShow
															id="genderM" value="1" checked="" required />
													否: <input
															type="radio" class="flat" name="isShow" id="genderF"
															value="0" />
													</p>

												</div>
											</div>
											<div class="item form-group">
												<label for="password2"
													class="control-label col-md-3 col-sm-3 col-xs-12">权限标识</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:input path="permission"
														class="form-control col-md-7 col-xs-12" />
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="remarks">备注 </label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:textarea class="form-control col-md-7 col-xs-12"
														path="remarks" data-parsley-trigger="keyup"
														data-parsley-minlength="20" data-parsley-maxlength="100"
														data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.."
														data-parsley-validation-threshold="10"></form:textarea>
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
			</div>

		</div>
	</div>



	<%@include file="/WEB-INF/views/common/include-js.jsp"%>
	<script type="text/javascript"
		src="/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript"
		src="/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript"
		src="/jquery-ztree/3.5.12/js/jquery.ztree.exedit-3.5.min.js"></script>
	<SCRIPT type="text/javascript">
	
		var zTree_Menu;
		var setting = {	
				 data: {
			            simpleData: {
			                enable: true
			            }
			        },
			     callback:{
			    	 onClick:clickNode
			     }   
		};
		
		  //加载ztree
	    function onloadZTree() {
	        var ztreeNodes;
	        $.ajax({
	            async: true,
	            cache: false,
	            type: 'post',
	            dataType: "json",
	            url: "${ctx}/sysMenu/loadTree",
	            error: function () {
	               
	            },
	            success: function (data) {
	                ztreeNodes = data;
	                $.fn.zTree.init($("#tree"), setting, ztreeNodes);
	                zTree_Menu = $.fn.zTree.getZTreeObj("tree");
	                expandAllFlag();
	            }
	        });
	    }

	    //展开全部分类
	    function expandAllFlag() {
	        zTree_Menu.expandAll(true);
	    }
	    
	    function clickNode(event, treeId, treeNode){
	    	$('input[name=parentName]').val(treeNode.name);
	    	$('#parentId').val(treeNode.id);
	    }

		$(document).ready(function(){
			 onloadZTree();
		});
	</SCRIPT>
	<script>
      $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
          submit = false;
        }
        if (submit)
          this.submit();
        return false;
      });
    </script>
</body>
</html>
