package com.edu.pitt.dbmi.hbService.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edu.pitt.dbmi.hbService.models.Person;

public class Database {
	public String serverName = "localhost";
	public String dbName = "rim20";
	public int port = 1521;
	public String userName;
	public String password;
	
	int LOAD_SOURCE = 1;
	int LOAD_DATE = 2;
	int RESEARCH_ID = 3;
	int SOURCE_ID = 4;
	int SOURCE_TYPE = 5;
	int SOURCE_NAME = 6;
	
	public Connection conn;
	public Statement stmt;
	
	public Database() {}
	public Database(String serverName, String dbName, int port, String userName, String password) {
		this.serverName = serverName;
		this.dbName = dbName;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:rim20/password@localhost:1521:xe";
			conn = DriverManager.getConnection(url);
			if(conn != null)
				System.out.println("connection successful");
			stmt = conn.createStatement();			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void disconnect() {
		try {
			stmt.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("disconnected");
	}
	
	public ResultSet queryAll() {
		ResultSet rslt = null;
		String query = "SELECT * from hb_person";  
		Statement stmt;
		try {
			stmt = conn.prepareStatement(query);
			rslt = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rslt;
	}
	
	public ResultSet queryBySourceId(int sourceId) {
		ResultSet rslt = null;
		String query = "SELECT * from hb_person WHERE source_id = ?";  
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sourceId); 
			rslt = pstmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rslt;
	}
	
	public ResultSet queryAllBySourceType(String sourceType) {
		ResultSet rslt = null;
		String sourceTypeCap = sourceType.toUpperCase();
		String query = "SELECT * from hb_person WHERE source_type = ?";  
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sourceTypeCap);
			rslt = pstmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rslt;
	}
	
	public ResultSet queryBySource(String sourceType, int sourceId) {
		ResultSet rslt = null;
		String sourceTypeCap = sourceType.toUpperCase();
		String query = "SELECT * from hb_person WHERE source_type = ? AND source_id = ?";  
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sourceTypeCap);
			pstmt.setInt(2, sourceId); 
			rslt = pstmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rslt;
	}
	
	public List<Person> getResults(ResultSet rslt) throws SQLException {
		if(rslt == null)
			return null;
		List<Person> list = new ArrayList<Person>();
		while(rslt.next()) {
			Person person = new Person(
					rslt.getString(LOAD_SOURCE), 
					rslt.getString(LOAD_DATE), 
					rslt.getInt(RESEARCH_ID),
					rslt.getInt(SOURCE_ID), 
					rslt.getString(SOURCE_TYPE),
					rslt.getString(SOURCE_NAME));
			list.add(person);
		}
		return list;
	}
	
	public ResultSet queryByResearchId(int researchId) {
		ResultSet rslt = null;
		String query = "SELECT * from hb_person WHERE research_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, researchId);
			rslt = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rslt;
	}
	
	public ResultSet addSource(String sourceType, int sourceId, int researchId) {
		ResultSet rslt = null;
		String loadSource = "HBSERVICE";
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		String query = "INSERT INTO hb_person(load_source, load_date, research_id, source_id, source_type, source_name) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loadSource);
			pstmt.setString(2, dateFormat.format(date));
			pstmt.setInt(3, researchId);
			pstmt.setInt(4, sourceId);
			pstmt.setString(5, sourceType.toUpperCase());
			pstmt.setString(6, "Testing");
			rslt = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rslt;
	}
	
	/*public static void main(String args[]) throws SQLException {
		connect();
		String query = "select * from hb_person";
		ResultSet rslt = queryDb(query);
		while(rslt.next()) {
			System.out.println(rslt.getString(1) + rslt.getString(2) + rslt.getLong(3) + rslt.getLong(4) + rslt.getString(5) + rslt.getString(6));
		}
	}*/
}
