<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 16/8/9
  Time: 下午3:57
  全局错误页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/common/taglib.jsp"%>
    <%@include file="/WEB-INF/views/common/css.jsp"%>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
            <div class="col-middle">
                <div class="text-center">
                    <h1 class="error-number">${status}</h1>
                    <h2>${error}</h2>
                    <p> ${error}. <a href="#">Report this?</a>
                    </p>
                    <div class="mid_center">
                        <h3>Search</h3>
                        <form>
                            <div class="col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <span class="input-group-btn">
                              <button class="btn btn-default" type="button" onclick="javacript:history.go(-1)">返回!</button>
                          </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
    </div>
</div>

</body>
</html>