<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/7
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<s:if test="exception.errorMessages!=''&& exception.errorMessages != null">
    <s:property value="exception.errorMessages"></s:property>
</s:if>
<s:else>
    <s:property value="exception.message"></s:property>
</s:else>
</body>
</html>
