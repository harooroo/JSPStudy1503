package kr.co.jspstudy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.jspstudy.DBLoader.JDBCUtil;
import kr.co.jspstudy.VO.GuestBookMessage;

public class GuestBookMessageDao {
	//목록 
	public ArrayList<GuestBookMessage> list(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GuestBookMessage> guestList = new ArrayList<GuestBookMessage>();
				
		try {
			String sql="select message_id,guest_name,message "
					+ "from guestbook_message "
					+ "order by message_id desc";
			pstmt = conn.prepareStatement(sql);
					
			rs =pstmt.executeQuery();
			while(rs.next()){
				guestList.add(new GuestBookMessage(rs.getInt("message_id"), rs.getString("guest_name"),rs.getString("message")));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		return guestList;
	}
	
	//작성
	public boolean write(Connection conn,GuestBookMessage guestbook){
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql="insert into guestbook_message(guest_name,password,message) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, guestbook.getGuest_name());
			pstmt.setString(2, guestbook.getPassword());
			pstmt.setString(3, guestbook.getMessage());
			
			int resultCnt = pstmt.executeUpdate();			
			if(resultCnt>0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return result;		
	}
	
	//삭제
	public boolean delete(Connection conn,int message_id) {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			String sql = "delete from guestbook_message where message_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,message_id);	

			int resultCnt = pstmt.executeUpdate();
			if (resultCnt > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}

		return result;
	}
	
}
