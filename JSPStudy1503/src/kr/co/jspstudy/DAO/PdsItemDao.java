package kr.co.jspstudy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.jspstudy.DBLoader.JDBCUtil;
import kr.co.jspstudy.VO.PdsItem;

public class PdsItemDao {
	private static PdsItemDao instance = new PdsItemDao();
	public static PdsItemDao getInstance(){
		return instance;
	}
	private PdsItemDao(){}
	
	public int insert(Connection conn,PdsItem pdsItem) throws SQLException{
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			String sql ="insert into pds_item(article_id,filename,realpath,filesize,description) "
					+ "values(?,?,?,?,?)";			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, pdsItem.getArticle_id());
			pstmt.setInt(1, 2);
			pstmt.setString(2, pdsItem.getFilename());
			pstmt.setString(3, pdsItem.getRealpath());
			pstmt.setLong(4, pdsItem.getFilesize());
			pstmt.setString(5, pdsItem.getDescription());
			result =pstmt.executeUpdate();	
			
			return result;
		}finally{
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}	
	}
	
	
	
}
