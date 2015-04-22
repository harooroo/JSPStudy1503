package kr.co.jspstudy.Service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.MemberDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.Member;

public class MemberRegisterService implements JSPService{

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		String viewPage ="";
		
		String memberid = request.getParameter("memberid");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("name:"+name);
		    		
		Member member = new Member(memberid,password,name,email);
		MemberDao memberDao = new MemberDao();
		
		Connection conn = DBConnect.getConnection();
		boolean result = memberDao.register(conn, member);    		
		
		if(result){
			viewPage = "login.jsp";
		}else{
			viewPage ="register.jsp";
		}    		
		return viewPage;
	}

}
