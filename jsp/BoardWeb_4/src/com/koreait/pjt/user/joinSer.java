package com.koreait.pjt.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/join")
public class joinSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// static이 없다면 따로
		ViewResolver.forward("user/join", request, response);
		
	}
	
		
	 //delete방식은 get방식이 편함 , select는 get

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw); //암호화기법
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setNm(nm);
		param.setEmail(email);
		
		int result = UserDAO.insUser(param);
		
		if(result != 1) {
			//에러발생
			request.setAttribute("msg", "에러가 발생하였습니다.");
			request.setAttribute("data", param);
			doGet(request, response);
			return;
		}
		System.out.println("result : " + result);
		
		response.sendRedirect("/login");
		
	}
}