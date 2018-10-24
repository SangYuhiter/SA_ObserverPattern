package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserColumnConnection {

	private int ConnectionID;
	private int UserID;
	private int ColumnID;
	public UserColumnConnection() {
		super();
	}
	public UserColumnConnection(int connectionID, int userID, int columnID) {
		ConnectionID = connectionID;
		UserID = userID;
		ColumnID = columnID;
	}
	public UserColumnConnection(int userID, int columnID) {
		UserID = userID;
		ColumnID = columnID;
	}
	
	public int getConnectionID() {
		return ConnectionID;
	}
	public void setConnectionID(int connectionID) {
		ConnectionID = connectionID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getColumnID() {
		return ColumnID;
	}
	public void setColumnID(int columnID) {
		ColumnID = columnID;
	}
	
	public List<UserColumnConnection> readUserColumnConnection() {
		// 读取数据库表UserColumnConnection
		DBHelper dbHelper = new DBHelper();
		ResultSet rs = dbHelper.DBExecuteQuery("select * from UserColumnConnection;");

		int ConnectionID;
		int UserID;
		int ColumnID;
		List<UserColumnConnection> userColumnConnections = new ArrayList<UserColumnConnection>();
		try {
			while (rs.next()) {
				ConnectionID=rs.getInt("ConnectionID");
				UserID=rs.getInt("UserID");
				ColumnID=rs.getInt("ColumnID");
				userColumnConnections.add(new UserColumnConnection(ConnectionID, UserID, ColumnID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userColumnConnections;
	}
	
	public void addUserColumnConnection(UserColumnConnection userColumnConnection) {
		//添加项目内容属性到数据库表UserColumnConnection
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("insert into UserColumnConnection(ConnectionID,UserID,ColumnID) values('0','" 
					+userColumnConnection.getUserID()+"','"+userColumnConnection.getColumnID()+ "');");
	}
	
	public void delUserColumnConnection(UserColumnConnection userColumnConnection) {
		//删除数据库表UserColumnConnection项目内容属性
		DBHelper dbHelper = new DBHelper();
		String sUserID=userColumnConnection.getUserID()+"";
		String sColumnID=userColumnConnection.getColumnID()+"";
		dbHelper.DBExecuteUpdate("delete from UserColumnConnection where UserID="+sUserID+" and ColumnID="+sColumnID+ ";");
	}
}
