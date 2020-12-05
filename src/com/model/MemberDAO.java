package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	PreparedStatement psmt = null;
	Connection conn = null;
	int cnt = 0;
	ResultSet rs = null;
	MemberDTO info =null;
	ArrayList<MemberDTO> list = null;
	
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
	
	
	public int join(MemberDTO dto) {
		
		conn();
		try {
			String sql = "insert into member values (?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,dto.getEmail());
			psmt.setString(2,dto.getPw());
			psmt.setString(3,dto.getTel());
			psmt.setString(4,dto.getAddr());
			
			cnt=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	public MemberDTO login(MemberDTO dto) {
		
		conn();
		
		try {
			String sql = "select * from member where email = ? and pw =? ";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,dto.getEmail());
			psmt.setString(2,dto.getPw());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString(1);
				String pw = rs.getString(2);
				String tel = rs.getString(3);
				String addr = rs.getString(4);
				
				info = new MemberDTO(email, pw, tel, addr);
			}
			} catch (SQLException e) {
				
			e.printStackTrace();
		}finally {
			close();
		}
		return info;
	}
	
	public int update(MemberDTO dto) {
		
		conn();
		try {
			String sql = "update member set pw=?, tel=?, addr=? where email=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPw());
			psmt.setString(2, dto.getTel());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getEmail());
			
			cnt = psmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
		
		
		
		
	}
	
	public ArrayList<MemberDTO> select() {
		conn();
		try {
			String sql = "select * from member";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				String email = rs.getString(1);
				String pw = rs.getString(2);
				String tel = rs.getString(3);
				String addr = rs.getString(4);
				
				info = new MemberDTO(email, pw, tel, addr);
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
		
	}
	
}
