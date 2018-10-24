package com.sangyu.observer;

public class ForumDBCreate {

	// ��̳��Ŀ����ĿID����������name��
	static final String sqlCreatForumColumn = "create table ForumColumn(ColumnID int primary key AUTO_INCREMENT,ColumnName varchar(20));";
	static final String sqlDropForumColumn = "drop table ForumColumn;";
	
	// ��Ŀ���ݣ�����ID����������title,content����ĿID�����������
	static final String sqlCreatColumnContent = "create table ColumnContent(ContentID int primary key AUTO_INCREMENT,ContentTitle varchar(20),ContentText text,ColumnID int);";
	static final String sqlDropColumnContent = "drop table ColumnContent;";

	// ��̳�û����û�ID����������name��
	static final String sqlCreateForumUser = "create table ForumUser(UserID int primary key AUTO_INCREMENT,UserName varchar(20));";
	static final String sqlDropForumUser = "drop table ForumUser;";

	// �û�--��̳��Ŀ��ע��ϵ
	static final String sqlCreateUserColumnConnection = "create table UserColumnConnection(ConnectionID int primary key AUTO_INCREMENT,UserID int,ColumnID int);";
	static final String sqlDropUserColumnConnection = "drop table UserColumnConnection;";
	
	// ��̳������Ϣ��
	static final String sqlCreateForumSendInfo = "create table ForumSendInfo(InfoID int primary key AUTO_INCREMENT,UserID int,ContentID int);";
	static final String sqlDropForumSendInfo = "drop table ForumSendInfo;";

	// �������ݿ���ر�
	public void CreateTables() {
		// ���ݿ⽨��
		DBHelper dbHelper = new DBHelper();
		dbHelper.DBExecuteUpdate(sqlDropForumColumn);
		dbHelper.DBExecuteUpdate(sqlDropColumnContent);
		dbHelper.DBExecuteUpdate(sqlDropForumUser);
		dbHelper.DBExecuteUpdate(sqlDropUserColumnConnection);
		dbHelper.DBExecuteUpdate(sqlDropForumSendInfo);
		dbHelper.DBExecuteUpdate(sqlCreatForumColumn);
		dbHelper.DBExecuteUpdate(sqlCreatColumnContent);
		dbHelper.DBExecuteUpdate(sqlCreateForumUser);
		dbHelper.DBExecuteUpdate(sqlCreateUserColumnConnection);
		dbHelper.DBExecuteUpdate(sqlCreateForumSendInfo);
	}
}
