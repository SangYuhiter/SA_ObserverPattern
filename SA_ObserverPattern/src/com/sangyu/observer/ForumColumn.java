package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumColumn {
	//��Ŀ���ԣ�ID������
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
		// ��ȡ���ݿ��ForumColumn
		DBHelper dbHelper = new DBHelper();
		ResultSet rs = dbHelper.DBExecuteQuery("select * from ForumColumn;");

		int ColumnID;
		String ColumnName;
		List<ForumColumn> forumcolumns = new ArrayList<ForumColumn>();
		try {
			while (rs.next()) {
				// ��ȡname��������
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
		// �����Ŀ���Ե����ݿ��ForumColumn
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("insert into ForumColumn(ColumnID,ColumnName) values('0','" 
					+ forumColumn.getColumnName() + "');");
	}
	
	public void delForumColumn(ForumColumn forumColumn) {
		// ɾ�����ݿ��ForumColumn��Ŀ
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("delete from ForumColumn where ColumnName='"+forumColumn.getColumnName()+"';");
	}
	
}
