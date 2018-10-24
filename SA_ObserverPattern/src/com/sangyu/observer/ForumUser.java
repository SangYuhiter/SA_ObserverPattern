package com.sangyu.observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ForumUser extends Observer {

	private int UserID;
	private String UserName;

	public ForumUser() {
		super();
	}

	public ForumUser(int userID, String userName) {
		UserID = userID;
		UserName = userName;
	}

	public ForumUser(String userName) {
		UserName = userName;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public List<ForumUser> readForumUser() {
		// 读取数据库表ForumUser
		DBHelper dbHelper = new DBHelper();
		Connection con = dbHelper.DBConnect();
		Statement stmt = dbHelper.DBStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from ForumUser limit 1000;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int UserID;
		String UserName;
		List<ForumUser> forumUsers = new ArrayList<ForumUser>();
		try {
			while (rs.next()) {
				UserID = rs.getInt("UserID");
				UserName = rs.getString("UserName");
				forumUsers.add(new ForumUser(UserID, UserName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.DBClose(rs, stmt, con);
		return forumUsers;
	}

	public void addForumUser(ForumUser forumUser) {
		// 添加项目内容属性到数据库表ForumUser
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("insert into ForumUser(UserID,UserName) values('0','"+ forumUser.getUserName() + "');");
	}

	public void delForumUser(ForumUser forumUser) {
		// 删除数据库表ForumUser项目内容属性
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("delete from ForumUser where UserName='" + forumUser.getUserName() + "';");
	}

	@Override
	public void update() {
		ColumnContent columnContent = new ColumnContent();
		List<ColumnContent> columnContents = columnContent.readColumnContent();

		int QueryColumnID = columnContents.get(columnContents.size() - 1).getColumnID();
		String QueryContentTitle = columnContents.get(columnContents.size() - 1).getContentTitle();
		String QueryContentText = columnContents.get(columnContents.size() - 1).getContentText();
		int QueryContentID = columnContents.get(columnContents.size() - 1).getContentID();
		int QueryUserID = this.UserID;

		DBHelper dbHelper = new DBHelper();
		Connection con = dbHelper.DBConnect();
		Statement stmt = dbHelper.DBStatement(con);
		Statement stmtUser = dbHelper.DBStatement(con);
		Statement stmtColumn = dbHelper.DBStatement(con);
		ResultSet rs = null, rsUser = null, rsColumn = null;
		int UserID = 0;
		int ColumnID = 0;
		String sQueryColumnID = String.valueOf(QueryColumnID);
		String sQueryUserID = String.valueOf(QueryUserID);
		try {
			rs = stmt.executeQuery("select UserID,ColumnID from UserColumnConnection where ColumnID= " + sQueryColumnID
					+ " and UserID=" + sQueryUserID + ";");
			while (rs.next()) {
				UserID = rs.getInt("UserID");
				ColumnID = rs.getInt("ColumnID");
				if (UserID != 0 && ColumnID != 0) {
					rsUser = stmtUser.executeQuery("select UserName from ForumUser where UserID=" + sQueryUserID + ";");
					rsUser.next();
					String UserName = rsUser.getString("UserName");
					rsColumn = stmtColumn
							.executeQuery("select ColumnName from ForumColumn where ColumnID=" + sQueryColumnID + ";");
					rsColumn.next();
					String ColumnName = rsColumn.getString("ColumnName");
					System.out.println(UserName + "订阅的" + ColumnName + "栏目已更新！");
					System.out.println("更新标题：《" + QueryContentTitle + "》");
					System.out.println("内容：" + QueryContentText);
					// 将此条消息插入ForumSendInfo数据库
//					File file = new File("Info.txt");
//					Writer out = new FileWriter(file,true);
//					String data = sQueryUserID+" "+QueryContentID+"\n";
//					out.write(data);
//					out.close();
//					dbHelper.DBExecuteUpdate("insert into ForumSendInfo(UserID,ContentID) values('" + QueryUserID
//							+ "','" + QueryContentID + "');");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.DBClose(rs, stmt, con);

	}
}
