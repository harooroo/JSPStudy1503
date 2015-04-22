package kr.co.jspstudy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.jspstudy.DBLoader.JDBCUtil;
import kr.co.jspstudy.VO.Article;


public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance(){
		return instance;
	}
	private ArticleDao(){}
	
	//총게시물 수 
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
	
	//리스트
	public List<Article> select(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("firstRow:"+firstRow);
		System.out.println("endRow:"+endRow);
		
		try {			
			String sql = "select article_id,group_id,parent,depth,indent, " +
					"posting_date,read_count,writer_name,title,content " +
					"from article " +
					"order by group_id desc, depth asc " +
					"limit ? , ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
		    
			ArrayList<Article> list = new ArrayList<>();
			while(rs.next()){				
				Article article = new Article(rs.getInt("article_id"), rs.getInt("group_id"), rs.getInt("parent"), rs.getInt("depth"), rs.getInt("indent"), 
						rs.getDate("posting_date"), rs.getInt("read_count"), rs.getString("writer_name"), rs.getString("title"), rs.getString("content"));
				list.add(article);
			}
			
			return list;			
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}	
	
	}
	
	//원글작성
	public int insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn.setAutoCommit(false);
		int result =0;
		
		try{
			String sql ="insert into article(parent,depth,indent,writer_name,title,content) " +
					"values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,article.getParent());
			pstmt.setInt(2,article.getDepth());
			pstmt.setInt(3,article.getIndent());
			pstmt.setString(4,article.getWriter_name());
			pstmt.setString(5,article.getTitle());
			pstmt.setString(6,article.getContent());			
			result = pstmt.executeUpdate();
			
			String sql2 ="select LAST_INSERT_ID()";
			int article_id = 0;
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article_id = rs.getInt(1);
			}
			String sql3 ="update article set group_id=? where article_id=?";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, article_id);
			pstmt.setInt(2, article_id);
			result = pstmt.executeUpdate();	
			
			conn.commit();						
		} catch(SQLException e){
			conn.rollback();
			e.printStackTrace();
		} finally{
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return result;		
	}
	
	// 답글작성
	public int insertReply(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		conn.setAutoCommit(false);
		int result = 0;

		try {			
			//같은 group_id의 depth조정
			String sql ="update article set depth=depth+1 " +
					"where group_id=? and depth >= ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1,article.getGroup_id());		
			pstmt.setInt(2,article.getDepth());		
			result = pstmt.executeUpdate();	
			//글 작성			
			String sql2 = "insert into article(parent,depth,indent,writer_name,title,content,group_id) "
					+ "values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, article.getParent());
			pstmt.setInt(2, article.getDepth());
			pstmt.setInt(3, article.getIndent());
			pstmt.setString(4, article.getWriter_name());
			pstmt.setString(5, article.getTitle());
			pstmt.setString(6, article.getContent());
			pstmt.setInt(7, article.getGroup_id());
			result = pstmt.executeUpdate();			
			
			conn.commit();
		} catch (SQLException e) {			
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}

		return result;
	}
		
	//글보기
	public Article viewDetail(Connection conn,int article_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article = null;
		try{
			String sql = "select * from article where article_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int group_id = rs.getInt("group_id");
				int parent = rs.getInt("parent");
				int depth = rs.getInt("depth");
				int indent = rs.getInt("indent");
				Date posting_date = rs.getDate("posting_date");
				int read_count = rs.getInt("read_count");
				String writer_name = rs.getString("writer_name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				article = new Article(article_id, group_id, parent, depth, indent, posting_date, read_count, writer_name, title, content);
			}
			return article;
		}finally{
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
	}
	
	//수정
	public int modify(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;		
		int result =0;
		
		try{
			String sql ="update article set title=? , content=? " +
					"where article_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,article.getTitle());
			pstmt.setString(2,article.getContent());			
			pstmt.setInt(3, article.getArticle_id());
			result = pstmt.executeUpdate();						
		} finally{
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return result;		
	}
	
	//삭제
		public int delete(Connection conn, int article_id) throws SQLException {
			PreparedStatement pstmt = null;		
			ResultSet rs = null;			
			conn.setAutoCommit(false);
			int result =0;
			
			try{
				//자식글이 있는지 확인
				String sql ="select * from article where parent =? order by depth asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, article_id);
				rs = pstmt.executeQuery();
				
				//자식글 중 가장 위에 있는 글만 "원본글삭제" 표시해주면 됨.
				if(rs.next()){					
					String sql2 = "update article set title=? where article_id=?";					
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "(원본글 삭제)"+rs.getString("title"));
					pstmt.setInt(2, rs.getInt("article_id"));
					result = pstmt.executeUpdate();
				}
				
				String sql3 ="delete from article where article_id=?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1,article_id);					
				result = pstmt.executeUpdate();						
				
				conn.commit();				
			} finally{
				conn.rollback();
				JDBCUtil.close(pstmt);
				JDBCUtil.close(conn);
			}
			
			return result;		
		}
}
