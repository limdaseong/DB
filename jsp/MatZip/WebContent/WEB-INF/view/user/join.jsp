<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div class="main">
	<div>
		<h1>회원가입</h1>
		<form id="frm" class="frm" action="/user/joinProc" method="post">
			<div id="IdChkResult" class="msg"></div>
			<div><input type="text" name="user_id" placeholder="아이디">
				<button type="button" onclick="chkId()">중복 확인</button>
			</div>
			<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
			<div><input type="password" name="user_pwre" placeholder="비밀번호 확인"></div>
			<div><input type="text" name="nm" placeholder="이름"></div>
			<div><input type="submit" value="회원가입"></div>
		</form>
		<div><a href="/user/login">로그인</a></div>
	
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		function chkId() {
			const user_id = frm.user_id.value
			axios.get('/user/ajaxIdChk', {
				params: {
					user_id
				}
			}).then(function(res) {
				if(res.data.result == 2) {
					IdChkResult.innerText = '사용할 수 있는 아이디입니다.'
				} else if(res.data.result == 3) {
					IdChkResult.innerText = '중복된 아이디입니다.'
				}
			})
		}
	</script>
</div>