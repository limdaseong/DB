package com.koreait.pjt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class MyUtils {
	
	public static int getIntParameter(HttpServletRequest request, String keyNm) {
		return parseStrToInt(request.getParameter(keyNm));
	}
	
	public static int parseStrToInt(String str) {
		return parseStrToInt(str, 0);
	}
		// 이름이 같은 메소드들을 오버로딩이라고 한다
	public static int parseStrToInt(String str, int defNo) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return defNo;
		}
	}
	
	// 오버라이딩은 부모Class에서 정의한 메서드를 자식 Class에서 변경하는 것

	
	
	
	// 로그인 확인 용도 / return true: 로그인이 안됨!, false: 로그인 된 상태
	public static boolean isLogout(HttpServletRequest request) throws IOException {
		if(null == getLoginUser(request)) {
			return true;
		}
		return false;
	}
	
	// 로그인 정보를 가져오려고 쓰는 것
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
	
	
	public static String encryptString(String str) {
		 String sha = "";

	       try{
	          MessageDigest sh = MessageDigest.getInstance("SHA-256");
	          sh.update(str.getBytes());
	          byte byteData[] = sh.digest();
	          StringBuffer sb = new StringBuffer(); // 문자열을 합치게 하는 것이다(문자열계의 배열)
	          // sb라는 객체에 자기가 알아서 문자열을 늘린다 (for문에서는 StringBuffer가 사용되지 않는다)
	          
	          for(int i = 0 ; i < byteData.length ; i++){
	              sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	          }

	          sha = sb.toString();

	      }catch(NoSuchAlgorithmException e){
	          //e.printStackTrace();
	          System.out.println("Encrypt Error - NoSuchAlgorithmException");
	          sha = null;
	      }

	      return sha;
	}
}
