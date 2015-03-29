package kr.co.jspstudy.Service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.GuestBookMessage;

public class GuestBookWrite implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		String guest_name = request.getParameter("guest_name");
    	String password = request.getParameter("password");
    	String message = request.getParameter("message");	    	
    	
    	GuestBookMessage guestbook = new GuestBookMessage(guest_name, password, message);
    	
    	Connection conn = DBConnect.getConnection();
    	GuestBookMessageDao guestbookDao = new GuestBookMessageDao();	    	
    	boolean result = guestbookDao.write(conn, guestbook);
    	System.out.println("result:"+result);
    	
    	request.setAttribute("result",result);
    	
    	String viewPage ="guestbook.do";
    	
    	return viewPage;
	}

}
