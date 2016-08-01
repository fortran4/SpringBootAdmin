<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-05-19
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <%@include file="/WEB-INF/views/common/include.jsp"%>
</head>
<body>
<!-- content -->
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-lg-12">
            <div class="centering text-center">
                <div class="text-center">
                    <h2 class="without-margin">Don't worry. It's <span class="text-success"><big>403</big></span> error only.</h2>
                    <h4 class="text-success">权限不足</h4>
                </div>
                <div class="text-center">
                    <h3><small>Choose an option below</small></h3>
                </div>
                <hr>
                <ul class="pager">
                    <li><a href="about.html">&larr; About</a></li>
                    <li><a href="dashboard.html">Dashboard</a></li>
                    <li><a href="ui-and-interface.html">UI & Interface</a></li>
                    <li><a href="error-pages.html">Other error pages &rarr;</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
