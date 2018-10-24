package com.sangyu.observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {
	// JDBC �����������ݿ� URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8066/Forum?useSSL=false";

	// ���ݿ���û��������룬��Ҫ�����Լ�������
	static final String USER = "mycat";
	static final String PASS = "mycat";

	//���ݿ�����
	public Connection DBConnect() {
		Connection con =null;
		try {
			//������������
			Class.forName(JDBC_DRIVER);
			
			//System.out.println("�������ݿ�...");
			con = DriverManager.getConnection(DB_URL, USER,PASS);
			//if (!con.isClosed())
				//System.out.println("���ݿ����ӳɹ�!");
		} catch (ClassNotFoundException e) {
			// ���ݿ��������쳣����
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			//���ݿ�����ʧ���쳣����
			System.out.println("Sorry,can't connect the database!");
			e.printStackTrace();
		}
		return con;
	}
	
	//����statement���������ִ��SQL���
	public Statement DBStatement(Connection con) {
		//������ѯ���
		Statement stmt=null;
		try {
			stmt=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	//ִ��SQL����ѯ
	public ResultSet DBExecuteQuery(String sql) {
		Connection con = this.DBConnect();
		Statement stmt = this.DBStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//ִ��SQL������
	public void DBExecuteUpdate(String sql) {
		Connection con = this.DBConnect();
		Statement stmt = this.DBStatement(con);
		ResultSet rs = null;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.DBClose(rs, stmt, con);
	}
	
	//�ر���Դ
	public void DBClose(ResultSet rs,Statement stmt,Connection con) {
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(con!=null)con.close();
			//System.out.println("���ݿ�ɹ��رգ���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
