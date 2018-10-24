package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumColumn {
	//栏目属性：ID、名字
	private int ColumnID;
	private String ColumnName;
	
	public ForumColumn() {
		super();
	}
	public ForumColumn(int columnID, String columnName) {
		super();
		ColumnID = columnID;
		ColumnName = columnName;
	}
	public ForumColumn(String columnName) {
		super();
		ColumnName = columnName;
	}
	public int getColumnID() {
		return ColumnID;
	}
	public void setColumnID(int columnID) {
		ColumnID = columnID;
	}
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	
	public List<ForumColumn> readForumColumn() {
		// 读取数据库表ForumColumn
		DBHelper dbHelper = new DBHelper();
		ResultSet rs = dbHelper.DBExecuteQuery("select * from ForumColumn;");

		int ColumnID;
		String ColumnName;
		List<ForumColumn> forumcolumns = new ArrayList<ForumColumn>();
		try {
			while (rs.next()) {
				// 获取name这列数据
				ColumnID=rs.getInt("ColumnID");
				ColumnName = rs.getString("ColumnName");
				forumcolumns.add(new ForumColumn(ColumnID,ColumnName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forumcolumns;
	}

	public void addForumColumn(ForumColumn forumColumn) {
		// 添加栏目属性到数据库表ForumColumn
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("insert into ForumColumn(ColumnID,ColumnName) values('0','" 
					+ forumColumn.getColumnName() + "');");
	}
	
	public void delForumColumn(ForumColumn forumColumn) {
		// 删除数据库表ForumColumn栏目
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("delete from ForumColumn where ColumnName='"+forumColumn.getColumnName()+"';");
	}
	
}
