package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbCon {
	public static Connection getCon() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "hr";
		String password = "koreait2020";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("접속 성공!"); 
		return con;		
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) { try {rs.close(); } catch(Exception e) {} }  
		if(ps != null) { try {ps.close(); } catch(Exception e) {} }  
		if(con != null) { try {con.close(); } catch(Exception e) {}	} 
	}
	
	
	public static void close(Connection con, PreparedStatement ps) {
		if(ps != null) { try {ps.close(); } catch(Exception e) {} }  
		if(con != null) { try {con.close(); } catch(Exception e) {}	} 
	}
	

}