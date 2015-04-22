package kr.co.jspstudy.Service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DBLoader.DBConnect;

public class GuestBookDeleteService implements JSPService{

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		int message_id =0;
    	String message_id_string = request.getParameter("message_id");
    	if(message_id_string.equals("") || message_id_string != null){
    		message_id = Integer.parseInt(request.getParameter("message_id"));	
    	}	    	    		    	
    	    	
    	Connection conn = DBConnect.getConnection();
    	GuestBookMessageDao guestbookDao = new GuestBookMessageDao();	    	
    	boolean result = guestbookDao.delete(conn, message_id);
    	System.out.println("result:"+result);
    	
    	request.setAttribute("result",result);
    	String viewPage ="guestbook.do";
    	
    	return viewPage;
	}

}
