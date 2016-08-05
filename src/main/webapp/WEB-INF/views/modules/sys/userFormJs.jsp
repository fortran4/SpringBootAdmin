<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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