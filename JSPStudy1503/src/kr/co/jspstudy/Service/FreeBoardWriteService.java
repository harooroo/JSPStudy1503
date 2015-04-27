package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.ArticleDao;
import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Util.CastingAfterNullCheck;
import kr.co.jspstudy.VO.Article;

public class FreeBoardWriteService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,HttpServletResponse response) {	
		Connection conn = DBConnect.getConnection();
		
		String writer_name= (String) request.getSession().getAttribute("UNAME");		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
	
		int article_id = CastingAfterNullCheck.toInteger(request.getParameter("article_id"));
		int group_id = CastingAfterNullCheck.toInteger(request.getParameter("group_id"));
		int parent = CastingAfterNullCheck.toInteger(request.getParameter("parent"));
		int depth = CastingAfterNullCheck.toInteger(request.getParameter("depth"));
		int indent = CastingAfterNullCheck.toInteger(request.getParameter("indent"));
		int read_count = 0;
		Date posting_date = null;
		
		System.out.println("FreeBoardWriteService: UNAME:"+writer_name);
		System.out.println("FreeBoardWriteService: article_id:"+article_id);
		//��� �϶�
		if(article_id !=0){			
			parent = article_id;    //�θ�۹�ȣ ����
			depth = depth+1;     //��������. �θ� depth+1
			indent = indent+1;   //�鿩���� ����. �θ� indent+1					
		}
		
		Article article = new Article(article_id, group_id, parent, depth, indent, posting_date, read_count, writer_name, title, content);
				
    	try {
    		if(article_id !=0){ //��� �ۼ� ��
    			ArticleDao.getInstance().insertReply(conn, article);
    		}else{  //���� �ۼ�  ��
    			ArticleDao.getInstance().insert(conn, article);
    		}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}    	    	
    	
		return "list.do";
	}

}
