package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.ArticleDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Util.CastingAfterNullCheck;
import kr.co.jspstudy.VO.Article;

public class FreeBoardModifyViewService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = DBConnect.getConnection();		
		int article_id = CastingAfterNullCheck.toInteger(request.getParameter("article_id"));
		Article article = null;
		
		try {
			article = ArticleDao.getInstance().viewDetail(conn, article_id);
			//article.setContent(article.getContent().replaceAll("\r\n", "<br>"));
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		request.setAttribute("article", article);		
		return "/WEB-INF/freeboard/freeboardModify.jsp";
	}

}
