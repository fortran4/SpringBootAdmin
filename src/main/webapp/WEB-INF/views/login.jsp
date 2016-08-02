<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-05-18
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <%@include file="/WEB-INF/views/common/taglib.jsp"%>
    <%@include file="/WEB-INF/views/common/css.jsp"%>

</head>
<body class="login">
    <sys:message content="${content}" type="${type}"/>
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">

        <div class="animate form login_form">
          <section class="login_content">
            <form id="loginForm" class="form-horizontal" action="${ctx}/login" method="post">
              <h1>Zcoder Admin System</h1>
              <div>
                <input name="loginName" type="text" class="form-control" placeholder="账号：" required="" />
              </div>
              <div>
                <input name="loginPwd" type="password" class="form-control" placeholder="密码：" required="" />
              </div>
              <div>
                <button type="submit" class="btn btn-primary">登录</button>
                <a class="reset_pass" href="#">忘记密码?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
               <%-- <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>--%>
                <div class="clearfix"></div>
                <br />
               <%-- <div>
                  <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                  <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                </div>--%>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
    <%@include file="/WEB-INF/views/common/js.jsp"%>
  </body>
</html>
