<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>数据字典</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
    <link href="/js/fileUpload/css/fileinput.css" rel="stylesheet">
    <link href="" rel="stylesheet">
</head>
<body class="main-bg">
<sys:message type="${type}" content="${content}"></sys:message>
<!--nav-->
<div class="row">
    <div class="x_panel">
        <div class="x_title">
            <h2><i class="glyphicon glyphicon-th-large"></i>用户管理</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <ul class="nav nav-tabs">
                <li><a href="${ctx}/user/findUsers">用户列表</a></li>
                <li class="active"><a href="${ctx}/user/add">新增</a></li>
            </ul>
            <br/>
            <form:form id="inputForm" modelAttribute="user" role="form"
                       action="${ctx}/user/save" method="post" enctype="multipart/form-data"
                       class="form-horizontal form-label-left">
                <form:hidden path="userId"/>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="loginName">用户名<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="loginName" cssClass="form-control col-md-7 col-xs-9" maxlength="12"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="loginPwd">登录密码<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:password path="loginPwd" cssClass="form-control col-md-7 col-xs-9" maxlength="12"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="realName">真实姓名<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="realName" cssClass="form-control col-md-7 col-xs-9" maxlength="12"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="realName">头像<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="file" class="form-control" name="headImg" data-min-file-count="1">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phone">手机
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="phone" cssClass="form-control col-md-7 col-xs-9" maxlength="11"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="email" cssClass="form-control col-md-7 col-xs-9"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">性别</label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div id="gender" class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default" data-toggle-class="btn-primary"
                                   data-toggle-passive-class="btn-default">
                                <input type="radio" name="gender" value="1"> &nbsp; 男 &nbsp;
                            </label>
                            <label class="btn btn-primary" data-toggle-class="btn-primary"
                                   data-toggle-passive-class="btn-default">
                                <input type="radio" name="gender" value="0"> 女
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary">重置</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/common/js.jsp" %>
<script src="/js/fileUpload/js/fileinput.js"></script>
<script src="/js/fileUpload/js/fileinput_locale_zh.js"></script>
<script>
    $(function () {

        $('#inputForm').formValidation({
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
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
                realName: {
                    validators: {
                        notEmpty: {
                            message: '请输入真实姓名'
                        }, stringLength: {
                            max: 20,
                            message: '真实姓名最大长度为20个字符'
                        }
                    }
                },
                phone: {
                    validators: {
                        phone: {
                            message: '请输入正确的手机号'
                        }
                    }
                },
                email: {
                    validators: {
                        emailAddress: {
                            message: '请输入正确的邮箱地址'
                        }
                    }
                }
            }
        });
    });

    $("input[name=headImg]").fileinput({
        showUpload: false,
        showCaption: false,
        browseClass: "btn btn-primary btn-lg",
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
    });
</script>
</body>
</html>