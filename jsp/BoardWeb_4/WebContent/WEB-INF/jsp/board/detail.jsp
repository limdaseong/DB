<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="/list">리스트</a>
		<c:if test="${loginUser.i_user == data.i_user }">
			<a href="/regmod?i_board=${data.i_board }">수정</a>
			<form id="delFrm" action="/board/del" method="post">
				<input type="hidden" name="i_board" value="${data.i_board }">
				<a href="#" onclick="submitDel()">삭제</a>
			</form>		
		</c:if>
	</div>
	
	<div>
		<button onclick="doLike(${data.i_board})">
			<span></span>
		</button>
	</div>
	<div>제목: ${data.title }</div>
	<div>작성일시: ${data.r_dt }</div>
	<div>작성자: ${data.nm }</div>
	<div>조회수: ${data.hits }</div>
	<hr>
	<div> ${data.ctnt }</div>
	
	<script>
		function submitDel() {
			if(confirm('삭제?')){
				delFrm.submit()
			}
		}
	</script>
	
			
</body>
</html>