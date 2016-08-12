/**
 * 封装dataTable.js
 *
 */

(function ($) {

    var my = {};

    my.table = function (options) {

        var opts = $.extend({}, my.table.defaults, options);

        return $('#' + opts.domId).DataTable({
            "serverSide" : opts.serverSide,
            "searching" : opts.searching,
            "scrollX" : opts.scrollX,
            "scrollY" : opts.scrollY,
            "info" : opts.info,
            "paging" : opts.paging,
            "ajax" : opts.ajax,
            "columnDefs" : opts.columnDefs,
            "order" : opts.order,
            "ordering" :opts.ordering,
            "columns" : opts.columns,
            "language" : {
                "lengthMenu" : "_MENU_ 条记录每页",
                "zeroRecords" : "没有找到记录",
                "info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty" : "无记录",
                "infoFiltered" : "(从 _MAX_ 条记录过滤)",
                "paginate" : {
                    "first" : "第一页",
                    "previous" : "上一页",
                    "next" : "下一页",
                    "last" : "最后一页"
                }
            }
        });
    };


    my.table.reload = function (param){
        my.table.settings()[0].ajax.data = param;
        my.table.ajax.reload();
    }

    my.table.defaults = {
        "domId" : "dataTable",// table dom id
        "serverSide" : true,// 由服务器处理分页
        "searching" : false,// 隐藏搜索框
        "scrollX" : true,// 水平滚动条
        "scrollY" : false,// 垂直滚动条
        "info" : false,// 分页描述信息
        "paging" : true,// 分页
        "pagingType" : "full_numbers",
        "order" : [ [ 1, "desc" ] ],
        "ordering":false,//不排序
        "ajax" : {
            "url":"",
            "data":{}
        },
        "columnDefs" : {},
        "columns" : []// 列定义
    };



    $.my = my;

})(jQuery);