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
	<!-- 로그인 정보 -->
	<c:choose>		
		<c:when test="${loginTeacher != null}">
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
		</c:when>
		
		<c:when test="${loginStudent != null}">
			<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
		</c:when>
	</c:choose>

	<h1>Test List</h1>
	

	<form method="get" action="${pageContext.request.contextPath}/test/testList">
		<c:if test="${rowPerPage == 5}">
			<select name="rowPerPage">
				<option value="5" selected="selected">5</option>
				<option value="10">10</option>
				<option value="20">20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 10}">
			<select name="rowPerPage">
				<option value="5">5</option>
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 20}">
			<select name="rowPerPage">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="20" selected="selected">20</option>
			</select>
		</c:if>
			
		<input type="text" name="searchWord" value="${searchWord}">
		<button type="submit">검색</button>
	</form>
	
	
	<table border="1">
		<tr>
			<th>teacherName</th>
			<th>testTitle</th>
			<th>testDate</th>
			<th>
				<c:choose>
					<c:when test="${loginTeacher != null}">
						수정
					</c:when>
					
					<c:when test="${loginStudent != null}">
						응시
					</c:when>
				</c:choose>		
			</th>
		</tr>
		
		<c:forEach var="t" items="${list}">
			<tr>
				<td>${t.teacherName}</td>
				<td><a href="${pageContext.request.contextPath}/teacher/testOne?testNo=${t.testNo}">${t.testTitle}</a></td>
				<td>${t.testDate}</td>
				<td>
					<c:choose>
						<c:when test="${loginTeacher != null}">
							<a href="${pageContext.request.contextPath}/test/modifyTest?teacherNo=${loginTeacher.teacherNo}&testNo=${t.testNo}">수정</a>
						</c:when>
						
						<c:when test="${loginStudent != null}">
							<a href="${pageContext.request.contextPath}/test/takeTest?studentNo=${loginStudent.studentNo}&testNo=${t.testNo}">응시</a>
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	
			<div>
		<!-- 처음으로 -->
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		
		<!-- 이전(-10) -->
		<c:choose>
			<c:when test="${currentPage - 10 >= 1}">
				<a href="${pageContext.request.contextPath}/test/testList?currentPage=${map.startPage-10}&searchWord=${searchWord}">이전</a>
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
				<a href="${pageContext.request.contextPath}/test/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:if>
		</c:forEach>
		
		<!-- 다음(+10) -->
		<c:choose>
			<c:when test="${currentPage + 10 < map.lastPage}">
				<a href="${pageContext.request.contextPath}/test/testList?currentPage=${map.startPage+10}&searchWord=${searchWord}">다음</a>
			</c:when>
			<c:otherwise>
				<a href="#">다음</a>
			</c:otherwise>
		</c:choose>
		
		<!-- 끝으로 -->
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=${map.lastPage}&searchWord=${searchWord}">끝으로</a>
	</div>
	
	<div>
		<p4>시험 회차 등록하기</p4>
		<form method ="post" action="${pageContext.request.contextPath}/teacher/addTest" id="addTestForm">
			<table border="1">
				<input type ="hidden" name = "teacherNo" value="${loginTeacher.teacherNo}">
				<tr>
					<th>teacherName</th>
					<td>
						<input type = "text" name = "teacherName" value="${loginTeacher.teacherName}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>testTitle</th>
					<td><input type = "text" name = "testTitle"></td>
				</tr>
				<tr>
					<th>testDate</th>
					<td><input type="date" name = "testDate"></td>
				</tr>
				
			</table>
			<button type = "submit">시험등록</button>
		</form>
		
	<br>
	</div>
	
	
</body>
</html>