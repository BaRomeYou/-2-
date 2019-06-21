package com.encore.coffee.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sound.midi.Sequence;

public class CoffeeDAO {//ȸ�� ���� �α��� 

	Connection conn;
	// Statement stmt;
	PreparedStatement stmt;
	ResultSet rs;

	Properties pro; // DB���Ӱ��� ���� ���� ��ü

	public CoffeeDAO() {
		try {
			pro = new Properties(); // �Ӽ� ��� ��ü
			pro.load(new FileReader("conn/conn.properties"));
			// �Ӽ� driver, url,user,password
			Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // ������

	private void connect() {
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disconnect() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean findLogin(String id, String pass) { //�α���
			connect();

		

		try {
			String sql = "select count(*) from member where id=? and pass=?";
			stmt = conn.prepareStatement(sql); // DB�� sql�� ����
			stmt.setString(1, id);
			stmt.setString(2, pass); // ����ǥ ����ŭ ����
			// rs = stmt.executeQuery(sql);
			
			rs = stmt.executeQuery(); // �̹� ������ sql �� ����, �ߺ� ���� X
			if (rs.next()) { // ������ 1�� �����ϱ� ������ ���������� if�ȿ� �־��ִ� ���� ����
				// rs.getInt("count(*)") ==> Error �Լ����� �÷������� ������� ����
				// rs.getInt("cnt") ==> O <- ���� ���� ����
				memberVO vo = new memberVO();
				int cnt = rs.getInt(1);
				if (cnt > 0)
					return true;
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	return false;
	
}
	public Boolean create(memberVO vo) { //ȸ������ 
		connect();
		
	try {
		String sql = "insert into member (id,num, name, pwd, birth, gender, phone, mail, freq) values(?,?,?,?,?,?,?,?,?)";
		stmt.executeUpdate();
		stmt.setString(1,vo.getId());
		stmt.setInt(2,vo.getNo());
		stmt.setString(3,vo.getName());
		stmt.setString(4,vo.getPwd());
		stmt.setString(5,vo.getBirth());
		stmt.setString(6,vo.getGender());
		stmt.setString(7,vo.getPhone());
		stmt.setString(8,vo.getMail());
		stmt.setInt(9,vo.getFreq());
		return true;
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		disconnect();
	}
		return false;
	}
	
	
	
}