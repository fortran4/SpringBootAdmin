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
    <%@include file="/WEB-INF/views/common/taglib.jsp" %>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
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
                    <img src="">
                </div>
                <div class="login-box-right">
                    <sys:message content="${content}" type="${type}"/>
                    <form method="post" action="${ctx}/login" id="loginForm" class="form-horizontal">
                        <h3></h3>
                        <div class="form-group has-feedback">
                            <input type="text" class="form-control has-feedback-left" placeholder="输入您的用户名"
                                   name="loginName"/>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="clear"></div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control has-feedback-left" placeholder="请输入登录密码"
                                   name="loginPwd"/>
                            <span class="fa fa-lock form-control-feedback left" aria-hidden="true"></span>
                        </div>

                        <div class="clear"></div>

                        <div class="v-code form-group has-feedback">
                            <div class="input-box" style="width: 132px;" id="image_code_div">
                                <input type="text" value="" class="form-control has-feedback-left" placeholder="验证码"
                                       id="validateCode" name="validateCode"
                                       maxlength="4"/>
                                <span class="fa fa-picture-o form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="v-codeb-box">
                                <img src="${ctx}/common/getimagecode" id="validate_code_pic">
                            </div>
                        </div>

                        <p class="error-text" id="login_err_msg"></p>

                        <div class="form-group">
                            <div style="float: right;display: inline">
                                <button type="submit" class="btn btn-primary">登录</button>
                                <a class="extra-link" href="/account/forgot.htm">忘记密码？</a>
                            </div>
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
</div>
<div class="support-footer">
    <p class="support-text"><a href="">Testin云测</a>提供技术支持</p>
</div>
<script src="/js/formValidation/vendor/jquery/jquery.min.js"></script>
<script src="/js/formValidation/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/formValidation/js/formValidation.js"></script>
<script src="/js/formValidation/framework/bootstrap.js"></script>
<script src="/js/formValidation/js/language/zh_CN.js"></script>
<script>
    $(function () {

        $('#loginForm').formValidation({
            fields: {
                loginName: {
                    validators: {
                        notEmpty: {
                            message: '请输入用户名'
                        },
                        stringLength: {
                            min: 6,
                            max: 12,
                            message: '用户名必须在6-12个字符之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '用户名只支持数字，字母，下划线的组合'
                        }
                    }
                },
                loginPwd: {
                    validators: {
                        notEmpty: {
                            message: '请输入密码'
                        }, stringLength: {
                            min: 6,
                            max: 12,
                            message: '密码必须在6-12个字符之间'
                        }
                    }
                },
                validateCode: {
                    validators: {
                        notEmpty: {
                            message: '请输入验证码'
                        }, stringLength: {
                            min: 4,
                            max: 4,
                            message: '验证码长度为4个字符'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>
