<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-05-20
  Time: 16:56
  左侧菜单
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="index.html" class="site_title"> <i class="fa fa-anchor"></i>
                <span>Fortran</span></a>
        </div>

        <div class="clearfix"></div>
        <!-- menu profile quick info -->
        <div class="profile">
            <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>欢迎,<shiro:principal/></span>
                <h2></h2>
            </div>
        </div>
        <!-- /menu profile quick info -->

        <br/>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <h3>&nbsp;</h3>
                <ul class="nav side-menu">
                    <c:forEach var="menu" items="${menus}" varStatus="status">
                        <li>
                            <a href="javascript:;" onclick="findChildMenu('${menu.menuId}')"><i class="fa fa-home"></i>${menu.menuName}<span
                                    class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu" id="menu_${menu.menuId}">
                            </ul>
                        </li>
                    </c:forEach>


                    <%--<li><a><i class="fa fa-home"></i>系统管理<span
                            class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href='${ctx}/sys/sysUser/find' target="mainFrame">用户管理</a></li>
                            <li><a href='${ctx}/sys/sysRole/find' target="mainFrame">角色管理</a></li>
                            <li><a href='${ctx}/sys/sysMenu/find' target="mainFrame">菜单管理</a></li>
                            <li><a href='${ctx}/sys/sysLog/find' target="mainFrame">日志管理</a></li>
                            <li><a href='${ctx}/sys/sysDict/find' target="mainFrame">数据字典管理</a></li>
                        </ul></li>

                    <li><a><i class="fa fa-edit"></i>代码生成<span
                            class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="${ctx}/genCode/find" target="mainFrame">代码生成</a></li>
                        </ul></li>--%>
                </ul>
            </div>
        </div>
        <!-- /sidebar menu -->

        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings"> <span
                    class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
            <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
        </a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
                class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
        </a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
                class="glyphicon glyphicon-off" aria-hidden="true"></span>
        </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>
<script>


    function findChildMenu(pid){
        if (pid=='')return;
        $.ajax({
            url:'${ctx}/user/getMenuById',
            data:{pid:pid},
            method:'get',
            dataType:'json',
            success : function (response) {
                if (response.code == 200){
                    $('#menu_'+pid).html("");
                    $.each(response.data, function(idx, obj) {
                        var html = '<li><a href="${ctx}'+obj.href+'" target="mainFrame">'+obj.menuName+'</a></li>';
                       $('#menu_'+pid).append(html);
                    });
                }
            },
            error:function () {
                throw new Error('load menu error');
            }
        });

    }

</script>