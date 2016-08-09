<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="消息类型：info、success、danger"%>
<c:if test="${not empty content}">
    <div class="alert alert-${type} alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
        </button>
        <c:if test="${type=='info'}">
            <strong>提示:</strong> ${content}
        </c:if>
        <c:if test="${type=='warning'}">
            <strong>警告:</strong> ${content}
        </c:if>
        <c:if test="${type=='danger'}">
            <strong>错误:</strong> ${content}
        </c:if>
    </div>
</c:if>