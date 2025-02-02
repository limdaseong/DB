package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {
	
	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	
	//           /user/login
	public String login(HttpServletRequest request) { // request만 해줌
		String error = request.getParameter("error");
		
		if(error != null) {
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인해 주세요.");
				break;
			case "3":
				request.setAttribute("msg", "비밀번호를 확인해 주세요");
				break;
			}
		}
		
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
		
		// 화면 열 때 필수!!
	}
	
	public String loginProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw= request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param);
		
		if(result == 1) {
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param); // 4개의 값을 한 곳으로 모아서 보내줌
			return "redirect:/restaurant/restMap";
		} else {
			return "redirect:/user/login?user_id=" + user_id + "&error=" + result;
		}
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		service.join(param);
		
		return "redirect:/user/login";
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		
		int result = service.login(param);
		
		return String.format("ajax:{\"result\": %s}", result);
	}
	// ajax 통신은 refresh 하지 않고 기능을 바꾸게 하려고 하는 것이다
	
	public String logout(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.invalidate(); //세션에 있던 값을 다 삭제
		
		return "redirect:/user/login";
	}
}
