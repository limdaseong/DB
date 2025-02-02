package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	public static String routerChk(HttpServletRequest request) {
		// 로그인 되어 있으면 login, join 접속 x

		// 로그인에 따른 접속 가능여부 판단
		// (로그인이 안 되어 있으면 접속할 수 있는 주소만 나머지 전부 로그인이 되어 있어야 함)
		// 문자열이 리턴되면 그 문자열로  sendRedirect 함
		
		String[] chkUserUriArr = {"login", "loginProc", "join", "joinProc", "ajaxIdChk"};
		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");
		
		if(targetUri.length < 3) { return null; }
		
		if(isLogout) { // 로그아웃 상태
			
			if(targetUri[1].equals(ViewRef.URI_USER)) {
					for(String uri : chkUserUriArr) {
						if(uri.equals(targetUri[2])) {
							return null; // 이것을 만났다면 chkUserUriArr이 아닌거임
						}
					}
				}
			return "/user/login";
		} else { // 로그인 되어있는 상태
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return "/restaurant/restMap";
					}
				}
			}
			return null; //null이 리턴되면 아무 일 없음
		}
	}
}
