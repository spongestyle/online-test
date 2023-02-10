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
	<!-- 로그인 전 -->
	<c:if test="${loginEmp == null}">
		<h1>사원 로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/employee/loginEmp">
			<table>
				<tr>
					<th>empId</th>
					<td>
						<input type ="text" name="empId">
					</td>
				</tr>
				<tr>	
					<th>empPw</th>
					<td>
						<input type ="password" name="empPw">
					</td>	
				</tr>
			</table>
			<button type =submit>로그인</button>
		</form>
	</c:if>
	
	<a href="${pageContext.request.contextPath}/home">이전</a>
	
	<!-- 로그인되있는 상태라면 -->
	
	
	
	
	<c:if test="${loginEmp != null }">
	<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
		<h1>${loginEmp.empName}님 반갑습니다</h1>
	
	</c:if>
</body>
</html>