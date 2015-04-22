package kr.co.jspstudy.DBLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection(){
		Connection conn = null;
		
		String jdbcDriver ="jdbc:mysql://localhost:3306/jspstudy?"+
							"useUnicode=true&characterEncoding=utf8";
		String dbUser ="jspstudy";
		String dbPW = "1004";
		
		try {
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPW);
			//System.out.println("Success DB Connnect");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
