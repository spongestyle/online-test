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
	<h1>사원추가</h1>
	<div>
		<input type="text" id="id">
		<button type="button" id="ckBtn">중복검사</button>
	</div>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/employee/addEmp">
		<table >
			<tr>
				<th>empId</th>
				<td>
					<input type ="text" id = "empId"name="empId">
				</td>
			</tr>
			<tr>	
				<th>empName</th>
				<td>
					<input type ="text" id="empName" name="empName">
				</td>
			</tr>
			<tr>	
				<th>empPw</th>
				<td>
					<input type ="text" id ="empPw" name="empPw">
				</td>
				
			</tr>
		</table>
		<button type ="submit" id="addBtn">>사원추가</button>
	</form>
</body>
<script>

	$('#ckBtn').click(function(){
		$.ajax({
			url:'idck'
			, type:'get'
			, data : {empId:$('#id').val()}
			, success:function(model){ // model : 'YES' / 'NO'
				if(model=='YES') {
					// 사용가능한 아이디
					$('#empId').val($('#id').val());
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