<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>学生成绩信息</title>
</head>
<body>
	学生成绩信息：<br>
	<c:forEach items="${scores}" var="score">
		姓名：${score.name }<br>
		课程：${score.course }<br>
		成绩：${score.score }<br>
		<hr>
	</c:forEach>
</body>
</html>