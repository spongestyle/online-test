<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">학생 비밀번호수정</a>
	
	<!-- 
		지나간 시험(table : test) 리스트+점수  - 점수확인버튼 - 제출답안지확(table : paper)
		오늘날짜 시험 리스트는 응시버튼 - 시험지출력(table : question X(조인) example) - 답안지 제출(table :paper)
		-->
	<a href="${pageContext.request.contextPath}/student/testList">학생시험관리</a> 
	
	<a href="${pageContext.request.contextPath}/student/logout">학생로그아웃</a>


</div>
