package com.sangyu.observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8066/Forum?useSSL=false";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "mycat";
	static final String PASS = "mycat";

	//数据库连接
	public Connection DBConnect() {
		Connection con =null;
		try {
			//加载驱动程序
			Class.forName(JDBC_DRIVER);
			
			//System.out.println("连接数据库...");
			con = DriverManager.getConnection(DB_URL, USER,PASS);
			//if (!con.isClosed())
				//System.out.println("数据库连接成功!");
		} catch (ClassNotFoundException e) {
			// 数据库驱动类异常处理
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			//数据库连接失败异常处理
			System.out.println("Sorry,can't connect the database!");
			e.printStackTrace();
		}
		return con;
	}
	
	//创建statement类对象，用来执行SQL语句
	public Statement DBStatement(Connection con) {
		//声明查询语句
		Statement stmt=null;
		try {
			stmt=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	//执行SQL语句查询
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
	
	//执行SQL语句更新
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
	
	//关闭资源
	public void DBClose(ResultSet rs,Statement stmt,Connection con) {
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(con!=null)con.close();
			//System.out.println("数据库成功关闭！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
