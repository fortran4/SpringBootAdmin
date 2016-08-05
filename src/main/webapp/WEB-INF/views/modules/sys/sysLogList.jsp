<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-05-21
  Time: 22:11
  系统日志列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html ng-app="logApp">
<head>
<title>系统日志查询</title>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<script>
$(document).ready(function() {
	
	var table = $.My.dataTable({
		   "ajax": {
	        	"url":"${ctx}/sysLog/findAll",
	        	"data": function(d){
	        		d.beginDate = $('#beginDate').val();
	        		d.endDate = $('#endDate').val();
	        	}
	        },
	        "columnDefs": [ {
	            "searchable": false,
	            "orderable": false,
	            "targets": 0
	        },{
	        	 "targets": 1,
	        	 "visible": false
	        }],
	        "order": [[ 1, "desc" ]],
	        "columns": [
					  {"data": "id"},
	                  {"data": "type"},
	                  {"data": "createBy"},
	                  {"data": "requestUri"},
	                  {"data": "method"},
	                  {"data": "remoteAddr"},
	                  {"data": "createDate"}
	              ]
	});
	
   //隐藏分页下拉列表
    $('#dataTable_length').css('display','none');
  
   //设置序号列
   table.on( 'order.dt search.dt', function () {
	   table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw(); 
   
   $('#btnSubmit').bind('click',function(){
	   table.draw();
   });
});
</script>
</head>
<body ng-controller="logController">

	<ul class="nav nav-tabs">
		<li class="active">日志管理</li>
	</ul>

	<div>
		<!--query form start-->
		<div class="row-fluid">
			<div class="col-lg-10">
				<form:form id="searchForm" modelAttribute="sysLog"
					action="${ctx}/sysLog/find/" method="post"
					class="form-search paddingTop10">
					<ul class="ul-form">
						<li><label>开始时间：</label> <input id="beginDate"
							name="beginDate" type="text" readonly="readonly" maxlength="20"
							class="input-mini"
							value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</li>
						<li><label>结束时间：</label> <input id="endDate" name="endDate"
							type="text" readonly="readonly" maxlength="20"
							class="input-mini"
							value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</li>
						<li class="btns"><input id="btnSubmit"
							class="btn btn-primary" type="button" value="查询" /> <input
							id="excelExport" class="btn btn-warning" type="button"
							value="导出Excel" /></li>
						<li class="clearfix"></li>
					</ul>
				</form:form>
			</div>
		</div>
		<!--query form end-->

		<!--table start-->
		<div class="row-flui">
			<div class="col-lg-10">
				<table id="dataTable" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>序号</th>
							<th>日志类型</th>
							<th>操作用户</th>
							<th>访问URI</th>
							<th>请求方式</th>
							<th>操作者IP</th>
							<th>操作时间</th>
						</tr>
					</thead>
					 <tbody></tbody>
					  <!-- tbody是必须的 -->
				</table>
			</div>
		</div>
		<!--table end-->
	</div>
</body>
</html>
