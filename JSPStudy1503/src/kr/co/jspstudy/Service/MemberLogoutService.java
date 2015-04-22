package kr.co.jspstudy.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jspstudy.DAO.MemberDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.Member;

public class MemberLogoutService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		
		/*Cookie cookie1 = new Cookie("LOGIN","");
		Cookie cookie2 = new Cookie("UNAME","");			
		cookie1.setMaxAge(0); // ƒÌ≈∞ªË¡¶			
		cookie2.setMaxAge(0); // ƒÌ≈∞ªË¡¶
		response.addCookie(cookie1);
		response.addCookie(cookie2);*/
		
		HttpSession session = request.getSession(false);
		session.invalidate();		
		
		String viewPage = "index.jsp";
		
		return viewPage;		
	}

}
