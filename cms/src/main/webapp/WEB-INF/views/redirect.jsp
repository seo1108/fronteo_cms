<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%
	final Log log = LogFactory.getLog(this.getClass());
	log.info("..Redirect.page...");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//(function($){
//	$(document).ready(function () {
		parent.top.location.href="login";
		parent.top.location.reload();
		//window.location="index.html";
//	});
//});
</script>
</head>
<body>
	
</body>
</html>