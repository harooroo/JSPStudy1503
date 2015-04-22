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
    	
    	//redirect�� �� , /freeboard/viewDetail.do �տ� /�� ���� contextPath�� ��������.
    	//�׸��� ���� ���丮������ �����̰� �ȴ�. freeboard�� ���� �ȵȴٱ�..
		return "viewDetail.do?article_id="+article_id; 
	}

}
