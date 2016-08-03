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
    <link href="/css/login.css" rel="stylesheet">
</head>
<div class="login-top">
        <a href="javascript:;;">下载 Testin Pro 客户端</a>
</div>
<div class="login-centent">
        <h2 class="login-title">Testin Pro自动化测试系统</h2>
    <div class="login-box">
        <div class="login-box-t"></div>
        <div class="login-box-m">
            <div class="login-box-main">
                <div class="login-box-left">
                    <c:if test="${not empty enterpriseInfo.logo}">
                        <img src="${enterpriseInfo.logo}">
                    </c:if>
                    <c:if test="${empty enterpriseInfo.logo}">
                        <img src="">
                    </c:if>
                </div>
                <div class="login-box-right">
                    <form method="post" action="${ctx}/login">
                        <input type="hidden" id="imageCodeAuth" name="imageCodeAuth" value="${imageCodeAuth}"/>

                        <h3>用户登录</h3>

                        <div class="input-box" id="email_div">
        							<span class="input-before">
        								<img src="/images/login/user_icon.png"/>
        							</span>
                            <input type="text" value="${loginName}" placeholder="输入您的用户名" id="loginName" name="loginName"/>
                        </div>
                        <div class="input-box" id="pwd_div">
        							<span class="input-before">
        								<img src="/images/login/password_icon.png"/>
        							</span>
                            <input type="password" value="${pwd}" placeholder="请输入登录密码" id="loginPwd" name="loginPwd"/>
                        </div>
                        <div class="v-code">
                            <div class="input-box" style="width: 170px;" id="image_code_div">
	        							<span class="input-before">
	        								<img src="/images/login/vcode_icon.png"/>
	        							</span>
                                <input type="text" value="" placeholder="验证码" id="image_code" name="imageCode"
                                       maxlength="4"/>
                            </div>
                            <div class="v-codeb-box">
                                <img src="${ctx}/common/getimagecode" id="image_code_pic">
                            </div>
                        </div>
                        <p class="error-text" id="login_err_msg"></p>

                        <div class="login-final">
                            <button type="submit" class="login-button">登录</button>
                            <a class="extra-link" href="/account/forgot.htm">忘记密码？</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="login-box-b"></div>
    </div>
    <div class="login-box-shadow">
        <img src="/images/login/login_box-shadow.png">
    </div>
    <sys:message content="${content}" type="${type}"/>
</div>
<div class="support-footer">
    <p class="support-text"><a href="">Testin云测</a>提供技术支持</p>
</div>

<%@include file="/WEB-INF/views/common/js.jsp"%>
  </body>
</html>
