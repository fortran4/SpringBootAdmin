<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-05-20
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="index.html" class="site_title"> <i class="fa fa-anchor"></i>
                <span>Zcoder</span></a>
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
            <div class="menu_section" ng-app="menuApp" ng-controller="menuCtrl">
                <h3>&nbsp;</h3>
                <ul class="nav side-menu">

                    <li ng-repeat="menu in menus">
                        <a><i class="fa fa-home"></i>{{menu.name}}<span
                                class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li ng-repeat="chlid in menus">
                                <if test="chlid.parentId == menu.id">
                                    <a href='${ctx}{{chlid.href}}' target="mainFrame">{{chlid.name}}</a>
                                </if>

                            </li>
                        </ul>
                    </li>

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
<script src="/js/angularjs/angular.min.js"></script>
<script>
    var userName = '${username}';
    var menuApp = angular.module('menuApp', []);
    menuApp.controller('menuCtrl', function ($scope, $http) {
        $http.get("${ctx}/user/getMenu", {params: {userName:userName}
        }).success(function (response) {
                    console.log(response)
                    $scope.menus = response.menus;
                });
    });
</script>