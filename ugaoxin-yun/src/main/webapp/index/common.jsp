<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <%=basePath%> -->
<%-- <c:set var="basePath" value="${pageContext.request.contextPath}"/> --%>
  
    <script src="<%=basePath%>/static/js/jquery.min.js"></script>
    <script src="<%=basePath%>/static/main/js/popper.min.js"></script>
    <script src="<%=basePath%>/static/main/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>/static/main/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="<%=basePath%>/static/main/js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
    <script type="text/javascript" src="<%=basePath%>/static/main/js/plugins/chart.js"></script>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/main/css/main.css">