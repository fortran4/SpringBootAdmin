<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2016-08-01
  Time: 11:47
  js 文件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var queryMethodArr = {   //查询条件
        "等于":"=",
        "模糊":"like",
        "两者之间":"between"
    };

    var formTypeArr = {  //表单类型
        "文本框":"text",
        "日期":"date",
        "下拉列表":"select",
        "单选":"radio",
        "多选":"checkbook",
        "文本区":"textarea"
    }

    var validatorDataTypeArr = { //验证数据类型对应 type="email"
        "默认":"",
        "邮箱":"email",
        "电话":"tel",
        "网址":"url",
        "数字":"number",
        "日期":"date"
    }
</script>
