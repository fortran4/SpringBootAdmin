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
                menuName: {
                    validators: {
                        notEmpty: {
                            message: '请输入菜单名称'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名必须在6-30个字符之间'
                        }
                    }
                },
                parentId: {
                    validators: {
                        notEmpty: {
                            message: '请选择父级菜单'
                        }
                    }
                },
                href: {
                    validators: {
                        notEmpty: {
                            message: '请输入链接地址'
                        }
                    }
                },
                sort: {
                    validators: {
                        number: {
                            message: '请输入排序数字'
                        }
                    }
                },
                permission: {
                    validators: {
                        notEmpty: {
                            message: '请输入权限标识'
                        }
                    }
                }
            }
        });
    });
</script>