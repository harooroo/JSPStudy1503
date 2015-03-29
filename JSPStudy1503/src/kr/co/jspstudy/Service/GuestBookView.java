package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.GuestBookMessage;

public class GuestBookView implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = DBConnect.getConnection();
    	GuestBookMessageDao guestbookDao = new GuestBookMessageDao();
    	ArrayList<GuestBookMessage> messages = guestbookDao.list(conn);
    	
    	request.setAttribute("messages", messages);
    	String viewPage = "guestbook.jsp";	   
    	
    	return viewPage;
	}
	
}
