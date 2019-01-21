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
</head>
<body>
	用户信息：<br>
	<c:if test="${fn:length(users) ne 1 }">
		<c:forEach items="${users}" var="user">
			姓名：${user.name }<br>
			年龄：${user.age }<br>
			<hr>
		</c:forEach>
	</c:if>
</body>
</html>