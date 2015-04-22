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

public class MemberLoginService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		String memberid = request.getParameter("memberid");
		String password = request.getParameter("password");
		String saveid = request.getParameter("saveid");
		
		MemberDao memberDao = new MemberDao();
		Member member = new Member();
		member.setMemberid(memberid);
		member.setPassword(password);
		
		Connection conn = DBConnect.getConnection();
		memberDao.login(conn,member);    		
		
		System.out.println("**login.......memberid:"+memberid+", name:"+member.getName());
	
		/*Cookie cookie1 = new Cookie("LOGIN","success");
		Cookie cookie2 = null;
		try {
			cookie2 = new Cookie("UNAME",URLEncoder.encode(member.getName(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.addCookie(cookie1);
		response.addCookie(cookie2);*/
		
		HttpSession session = request.getSession();
		session.setAttribute("UNAME", member.getName());
		String viewPage = "index.jsp";
		
		return viewPage;		
	}

}
