<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- empMenu -->
	<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
	
	<h1>Student List</h1>
	
	<a href="${pageContext.request.contextPath}/student/addStudent">학생등록</a>
	
	<form method="get" action="${pageContext.request.contextPath}/student/studentList">
		<c:if test="${rowPerPage == 5}">
			<select name ="rowPerPage">
				<option value="5" selected="selected">5</option>
				<option value="10" >10</option>
				<option value="20" >20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 10}">
			<select name ="rowPerPage">
				<option value="5" >5</option>
				<option value="10" selected="selected">10</option>
				<option value="20" >20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 20}">
			<select name ="rowPerPage">
				<option value="5" >5</option>
				<option value="10" >10</option>
				<option value="20" selected="selected">20</option>
			</select>
		</c:if>
	
		<input type ="text" name="searchWord" value="${searchWord }">
		<button type ="submit">검색</button>
	</form>

	
	<table border="1">
		<tr>
			<th>studentId</th>
			<th>studentName</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach var="s" items="${list}">
			<tr>
				<td>${s.studentId}</td>
				<td>${s.studentName}</td>
				<td><a href="${pageContext.request.contextPath}/student/removeStudent?studentNo=${s.studentNo}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 -->
	<div>
		<!-- 처음으로 -->
		<a href="${pageContext.request.contextPath}/student/studentList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		
		<!-- 이전(-10) -->
		<c:choose>
			<c:when test="">
				<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${map.startPage-10}&searchWord=${searchWord}">이전</a>
			</c:when>
			<c:otherwise>
				<a href="#">이전</a>
			</c:otherwise>
		</c:choose>
		
		<!-- startPage <= currentPage <= endPage -->
		<c:forEach var="i" begin="${map.startPage}" end="${map.endPage}" step="1">
				<c:if test="${currentPage == i}">
					<a href="#" style="color : red">${i}</a>
				</c:if>
				<c:if test="${currentPage != i}">
					<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
				</c:if>
		</c:forEach>
			
		<!-- 다음(+10) -->
		<c:choose>
			<c:when test="${currentPage + 10 < map.lastPage}">
				<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${map.startPage+10}&searchWord=${searchWord}">다음</a>
			</c:when>
			<c:otherwise>
				<a href="#">다음</a>
			</c:otherwise>
		</c:choose>
	
		<!-- 끝으로 -->
		<a href="${pageContext.request.contextPath}/teacher/teacherList?currentPage=${map.lastPage}&searchWord=${searchWord}">끝으로</a>

	</div>

	
</body>
</html>