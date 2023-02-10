<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<h1>강사추가</h1>
	
	<div>
		<input type="text" id="id">
		<button type="button" id="ckBtn">중복검사</button>
	</div>
	
	<div style="color : red">${errorMsg}</div>
	<form id="addForm" method="post" action="${pageContext.request.contextPath}/teacher/addTeacher">
		<table border="1">
			<tr>
				<td>teacherId</td>
				<td><input type="text" id ="teacherId" name="teacherId"></td>
			</tr>
			<tr>
				<td>teacherName</td>
				<td><input type="text" id="teacherName" name="teacherName"></td>
			</tr>	
			
			<tr>
				<td>teacherPw</td>
				<td><input type="password" id="teacherPw" name="teacherPw"></td>
			</tr>
		</table>
		<button id="addBtn" type="submit">강사추가</button>
	</form>
</body>
<script>

	$('#ckBtn').click(function(){
		$.ajax({
			url:'idck'
			, type:'get'
			, data : {teacherId:$('#id').val()}
			, success:function(model){ // model : 'YES' / 'NO'
				if(model=='YES') {
					// 사용가능한 아이디
					$('#teacherId').val($('#id').val());
				} else {
					// 사용중인 아이디
					alert($('#id').val()+'는 사용중인 아이디입니다');
				}
			}
		});
	});
	
	

	$('#addBtn').click(function(){
		console.log("form action 전송");
		
	})

</script>
</html>