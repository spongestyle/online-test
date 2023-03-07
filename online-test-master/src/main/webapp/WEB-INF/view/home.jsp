<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>home</h1>
	<!-- 로그인 페이지-->
	<a href="${pageContext.request.contextPath}/employee/loginEmp">사원으로 로그인</a>
	<a href="${pageContext.request.contextPath}/teacher/loginTeacher">강사로 로그인</a>
	<a href="${pageContext.request.contextPath}/student/loginStudent">학생으로 로그인</a>
	
	
	
	
	
</body>
</html>