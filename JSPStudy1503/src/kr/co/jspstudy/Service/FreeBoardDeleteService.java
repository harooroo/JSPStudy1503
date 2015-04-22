package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.ArticleDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Util.CastingAfterNullCheck;

public class FreeBoardDeleteService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = DBConnect.getConnection();
		int article_id = CastingAfterNullCheck.toInteger(request.getParameter("article_id"));
		
		try {
			ArticleDao.getInstance().delete(conn, article_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "list.do";
	}

}
