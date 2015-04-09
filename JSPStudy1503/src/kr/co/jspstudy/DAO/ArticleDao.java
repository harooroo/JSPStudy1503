package kr.co.jspstudy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.co.jspstudy.DBLoader.JDBCUtil;
import kr.co.jspstudy.VO.Article;


public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance(){
		return instance;
	}
	private ArticleDao(){}
	
	public int selectCount(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
				
		try {
			String sql = "select count(*) from article";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} finally{
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}		
	}
	
	public List<Article> select(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			String sql = "select * from article " +
					"order by family desc, depth asc " +
					"limit ? , ?";
			pstmt.setInt(1, firstRow -1);
			pstmt.setInt(2, endRow -firstRow +1);
			pstmt = conn.prepareStatement(sql);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return null;
	}
}
