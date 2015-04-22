package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.ArticleDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Util.CastingAfterNullCheck;
import kr.co.jspstudy.VO.Article;

public class FreeBoardModifyService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");	
		int article_id = CastingAfterNullCheck.toInteger(request.getParameter("article_id"));	
		
		Article article = new Article();
		article.setArticle_id(article_id);
		article.setTitle(title);
		article.setContent(content);
		
		Connection conn = DBConnect.getConnection();
		
    	try {
			int result = ArticleDao.getInstance().modify(conn, article);
		} catch (SQLException e) {			
			e.printStackTrace();
		}    	
    	
    	//redirect일 때 , /freeboard/viewDetail.do 앞에 /를 쓰면 contextPath가 없어진다.
    	//그리고 현재 디렉토리내에서 움직이게 된다. freeboard도 쓰면 안된다구..
		return "viewDetail.do?article_id="+article_id; 
	}

}
