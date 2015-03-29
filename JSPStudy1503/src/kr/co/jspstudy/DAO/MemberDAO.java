package kr.co.jspstudy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.jspstudy.VO.Member;



public class MemberDAO {
	public void login(Connection conn,Member member){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			String sql="select * from member where memberid=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberid());
			pstmt.setString(2, member.getPassword());
			
			rs =pstmt.executeQuery();
			if(rs.next()){
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try { rs.close();} catch (SQLException e) {	e.printStackTrace();}
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();}
		}
	}
	
	public boolean register(Connection conn,Member member){
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql="insert into member(memberid,password,name,email) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberid());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			
			int resultCnt = pstmt.executeUpdate();			
			if(resultCnt>0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();}
		}
		
		return result;
		
	}
}
