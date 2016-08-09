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
                roleName: {
                    validators: {
                        notEmpty: {
                            message: '请输入角色名称'
                        },
                        stringLength: {
                            min: 1,
                            max: 50,
                            message: '角色名称必须在1-50个字符之间'
                        }
                    }
                },
                remarks: {
                    validators: {
                        stringLength: {
                            max: 200,
                            message: '备注最大字符长度为200'
                        }
                    }
                }
            }
        });
    });
</script>