<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户信息</title>
	
	<link rel="stylesheet" href="resource/css/note.css">
	
	<script type="text/javascript" src="resource/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="resource/js/note.js"></script>
</head>
<body>
	用户信息：<br>
	姓名：${user.name }<br>
	年龄：${user.age }<br>
	
	<script type="text/javascript">
	/* $(window).bind('beforeunload',function(){ 
		return '您输入的内容尚未保存，确定离开此页面吗？'; 
		});  */
	/* document.window.onbeforeunload = function (){
		return '您输入的内容尚未保存，确定离开此页面吗？'; 
	}; */
	
	$(function (){
	    note_init("公告信息","你有一个新信息",200,300);  
	});
	
	</script>
	
</body>
</html>