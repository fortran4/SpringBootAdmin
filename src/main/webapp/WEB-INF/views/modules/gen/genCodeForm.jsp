<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>代码生成</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
    <%@include file="genCodeFormJs.jsp"%>
</head>
<body style="background: #F7F7F7">
<sys:message type="${type}" content="${content}"></sys:message>
<div class="row">
        <div class="x_panel">
            <div class="x_title">
                <h2>
                    <i class="glyphicon glyphicon-th-large"></i>代码生成
                </h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form:form modelAttribute="gen" class="form-horizontal form-label-left" id="frm"  action="${ctx}/genCode/save" method="post">

                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">表名</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <select class="select2_multiple form-control" required="required" name="tableName" multiple="multiple">
                                    <c:forEach items="${tables}" var="table">
                                        <option value="${table}">${table}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">类名</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <input type="text" class="form-control" required="required" name="className" placeholder="类名">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">功能名</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <input type="text" class="form-control"  name="funcName" placeholder="功能名">
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">模块名</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <input type="text" class="form-control" required="required" name="moduleName" placeholder="模块名">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">子模块名</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <input type="text" class="form-control" name="subModuleName" placeholder="子模块名">
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">生成方案</label>
                            <div class="col-md-6 col-sm-6 col-xs-3">
                                <select class="form-control"  name="scheme">
                                        <option value="">增删改查</option>
                                        <option value="jsp">只生成页面</option>
                                        <option value="web">只生成Controller</option>
                                        <option value="service">只生成Service</option>
                                        <option value="dao">只生成Dao</option>
                                        <option value="domain">只生成Domain</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <table id="dataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>列名</th>
                            <th>数据库类型</th>
                            <th>Java类型</th>
                            <th>主键</th>
                            <th>可空</th>
                            <th>注释</th>
                            <th>设置</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                        <!-- tbody是必须的 -->
                    </table>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div style="float: right;">
                            <button id="send" type="submit" class="btn btn-success">保存</button>
                        </div>
                    </div>
                </form:form>
            </div>
    </div>
</div>

<%@include file="/WEB-INF/views/common/js.jsp" %>
<script>

    function format ( d ) {
        // `d` is the original data object for the row
        var _htmlForQueryMethod = '<select id="queryMethod" name="queryMethod_' + d.field + '">';
        $.each(queryMethodArr, function(name, value) {
            _htmlForQueryMethod += '<option value="'+value+'">'+name+'</option>';
        });
        _htmlForQueryMethod += '</select>';

        var _htmlForformType = '<select id="formType" name="formType_' + d.field + '">';
        $.each(formTypeArr, function(name, value) {
            _htmlForformType += '<option value="'+value+'">'+name+'</option>';
        });
        _htmlForformType += '</select>';


        var _htmlForDataType = '<select id="dataType" name="dataType_' + d.field + '">';
        $.each(validatorDataTypeArr, function(name, value) {
            _htmlForDataType += '<option value="'+value+'">'+name+'</option>';
        });
        _htmlForDataType += '</select>';

        return '<table class="table table-striped" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
                '<tr>'+
                '<td nowrap>查询字段:</td>'+
                '<td width="300px"><input type="checkbox" value="true"  name="isQuery_' + d.field + '" class="editor-active"></td>'+
                '<td nowrap>查询方式:</td>'+
                '<td width="300px">'+_htmlForQueryMethod+'</td>'+
                '<td nowrap>列表字段:</td>'+
                '<td width="300px"><input type="checkbox"  value="true" checked="true" name="isList_' + d.field + '" class="editor-active"></td>'+
                '<td nowrap>表单类型:</td>'+
                '<td width="300px">'+_htmlForformType+'</td>'+
                '</tr>'+
                '<tr>'+
                '<td nowrap>必填:</td>'+
                '<td width="300px"><input type="checkbox"  value="required" checked="true"  name="isRequired_' + d.field + '" class="editor-active"></td>'+
                '<td nowrap>数据类型:</td>'+
                '<td width="300px">'+_htmlForDataType+'</td>'+
                '<td nowrap>数据范围:</td>'+
                '<td width="300px"><input type="text"  name="lengthRange_' + d.field + '" class="editor-active"></td>'+
                '<td nowrap>数据字典类型（checkbox,radio,select有效）</td>'+
                '<td width="300px"><input type="text"  name="dictData_' + d.field + '" class="editor-active"></td>'+
                '</tr>'+
                '</table>';
    }

    $(function () {
        $(".select2_multiple").select2({
            maximumSelectionLength: 1,
            placeholder: "暂只支持最大量1个",
            allowClear: true
        });

        //默认不必作为条件的字段
        var exclude = ['id','create_by','create_date','update_by','update_date','remarks','del_flag'];

        var table = $.My.dataTable({
            "ajax": {
                "url": "${ctx}/genCode/findColumn",
                "data": function (d) {
                    d.tableName = $.trim($('.select2_multiple').val());
                }
            },
            "orderable": false,
             scrollY: '50vh',
            scrollCollapse: true,
            "info": false,
            "paging": false,
            "columns": [{
                "data": "field",
                "orderable":      false
            }, {
                "data": "jdbcType",
                "orderable":      false
            }, {
                "data": "javaType",
                "orderable":      false
            }, {
                "data": "isKey",
                "orderable":      false
            }, {
                "data": "isNull",
                "orderable":      false
            }, {
                "data": "comment",
                "orderable":      false
            },{
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }]

        });


        $('#dataTable tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row( tr );

            if ( row.child.isShown() ) {
                // This row is already open - close it
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                // Open this row
                row.child( format(row.data()) ).show();
                tr.addClass('shown');
            }
        } );

        //隐藏分页下拉列表
        $('#dataTable_length').css('display', 'none');

        $('.select2_multiple').bind('change', function () {
            table.draw();
        });

    });

    $('form').submit(function (e) {
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