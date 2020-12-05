package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageDAO {

	PreparedStatement psmt = null;
	Connection conn = null;
	int cnt = 0;
	ResultSet rs = null;
	MessageDTO info =null;
	ArrayList<MessageDTO> list = null;
	
	public void conn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insert(MessageDTO dto) {
		conn();
		
		try {
			String sql = "insert into message values(num.nextval, ?,?,?, sysdate)";
			psmt= conn.prepareStatement(sql);
			psmt.setString(1,dto.getSend());
			psmt.setString(2,dto.getReceive());
			psmt.setString(3, dto.getMessage());
			
			cnt = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
		
}

	
	
	public ArrayList<MessageDTO> select(String receive) {
	
		list = new ArrayList<MessageDTO>();
		
		try {
			conn(); 
			
			
			String sql = "select * from message where receive = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, receive);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt(1);
				String send = rs.getString(2);
				String receive_email = rs.getString(3);
				String message = rs.getString(4);
				String date =  rs.getString(5);
				
				info = new MessageDTO(num, send, receive_email, message, date);
				list.add(info);
				
			}	
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close();
		}return list;
		
		
		}	
		
		public int deleteAll(String receive) {
			conn();
			
			try {
				String sql = "delete from message where receive=?";
				psmt =conn.prepareStatement(sql);
				psmt.setString(1, receive);
				cnt = psmt.executeUpdate();//°ªÀÌ ¹Ù²î¸é ¾÷µ«
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
			
			
		}
	
	
}
	


