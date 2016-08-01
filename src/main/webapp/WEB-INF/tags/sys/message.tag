<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="消息类型：info、success、danger"%>
<c:if test="${not empty content}">
	 <div class="alert alert-${type}">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <strong>${content}</strong>
     </div>
</c:if>