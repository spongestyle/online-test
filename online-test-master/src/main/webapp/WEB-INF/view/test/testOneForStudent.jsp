<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function (){
	
	let questionCount = "<c:out value='${list.size()}'/>";
	let answerList = document.querySelectorAll('.answer');
	
	$("#addPaperBtn").on("click", function(){
		for(let i = 0; i<questionCount; i++){
			<!-- 체크안된 항목 찾기-->
			
			<!-- 체크된 라디오 버튼값 가져오기 -->
			let answerNo = $("input[name=answer" + (i+1) + "]:checked").val();
			
			<!-- 히든으로 저장해두기 -->
			$(answerList[i]).val(answerNo);
		}
		
		$("#addPaperForm").submit();
	});	
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	
	<h1>시험 보기</h1>
	<form id="addPaperForm" method="post" action="${pageContext.request.contextPath}/paper/addPaper">
		<input type="hidden" class="testNo" name="testNo" value="${testNo}">
		<c:forEach var="questionList" items="${list}" varStatus="i">
			<input type="hidden" class="questionNo" name="questionNo" value="${questionList.questionList.questionNo}">
			<input type="hidden" class="answer" name="answer" value="">
			<div>
				<table border="1" style="width : 200px">
					<tr>
						<th>${questionList.questionList.questionTitle}</th>
					</tr>
					<c:forEach var="exampleList" items="${questionList.exampleList}">
						<tr>
							<td>
								<input type="radio" id="answer${i.count}" name="answer${i.count}" value="${exampleList.exampleIdx}">&nbsp;${exampleList.exampleTitle}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
		</c:forEach>
		
		<button type="button" id="addPaperBtn">제출하기</button>
	</form>
</body>
</html>