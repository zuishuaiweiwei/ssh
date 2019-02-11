<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 8/1
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    response.sendRedirect(basePath + "sys/login_loginUi.action");
%>
