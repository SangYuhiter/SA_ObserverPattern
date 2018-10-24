package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//��Ŀ������
public class ColumnContent {
	private int ContentID;
	private String ContentTitle;
	private String ContentText;
	private int ColumnID;
	
	public ColumnContent() {
		super();
	}
	public ColumnContent(int contentID, String contentTitle, String contentText, int columnID) {
		ContentID = contentID;
		ContentTitle = contentTitle;
		ContentText = contentText;
		ColumnID = columnID;
	}
	public ColumnContent(String contentTitle, String contentText, int columnID) {
		ContentTitle = contentTitle;
		ContentText = contentText;
		ColumnID = columnID;
	}
	public int getContentID() {
		return ContentID;
	}
	public void setContentID(int contentID) {
		ContentID = contentID;
	}
	public String getContentTitle() {
		return ContentTitle;
	}
	public void setContentTitle(String contentTitle) {
		ContentTitle = contentTitle;
	}
	public String getContentText() {
		return ContentText;
	}
	public void setContentText(String contentText) {
		ContentText = contentText;
	}
	public int getColumnID() {
		return ColumnID;
	}
	public void setColumnID(int columnID) {
		ColumnID = columnID;
	}
	
	public List<ColumnContent> readColumnContent() {
		// ��ȡ���ݿ��ColumnContent
		DBHelper dbHelper = new DBHelper();
		ResultSet rs = dbHelper.DBExecuteQuery("select * from ColumnContent;");

		int ContentID;
		String ContentTitle;
		String ContentText;
		int ColumnID;
		List<ColumnContent> columncontents = new ArrayList<ColumnContent>();
		try {
			while (rs.next()) {
				ContentID=rs.getInt("ContentID");
				ContentTitle=rs.getString("ContentTitle");
				ContentText=rs.getString("ContentText");
				ColumnID=rs.getInt("ColumnID");
				columncontents.add(new ColumnContent(ContentID, ContentTitle, ContentText, ColumnID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columncontents;
	}
	
	public void addColumnContent(ColumnContent columnContent) {
		//�����Ŀ�������Ե����ݿ��ColumnContent
		ForumServer forumServer=new ForumServer();
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("insert into ColumnContent(ContentID,ContentTitle,ContentText,ColumnID) values('0','" 
					+ columnContent.getContentTitle() +"','"+columnContent.getContentText()+"','"+columnContent.getColumnID()+ "');");
		forumServer.notifyObserver();
	}
	
	public void delColumnContent(ColumnContent columnContent) {
		//ɾ�����ݿ��ColumnContent��Ŀ��������
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate("delete from ColumnContent where ContentTitle='"+columnContent.getContentTitle()+"';");
	}
	
}
