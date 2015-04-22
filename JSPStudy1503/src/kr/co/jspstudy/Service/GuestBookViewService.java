package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.GuestBookMessage;

public class GuestBookViewService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = DBConnect.getConnection();
    	GuestBookMessageDao guestbookDao = new GuestBookMessageDao();
    	ArrayList<GuestBookMessage> messages = guestbookDao.list(conn);
    	
    	request.setAttribute("messages", messages);
    	String viewPage = "/WEB-INF/guestbook/guestbook.jsp";	   
    	
    	return viewPage;
	}
	
}
