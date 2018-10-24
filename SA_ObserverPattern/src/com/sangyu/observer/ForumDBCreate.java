package com.sangyu.observer;

public class ForumDBCreate {

	// 论坛栏目（栏目ID（主键），name）
	static final String sqlCreatForumColumn = "create table ForumColumn(ColumnID int primary key AUTO_INCREMENT,ColumnName varchar(20));";
	static final String sqlDropForumColumn = "drop table ForumColumn;";
	
	// 栏目内容（内容ID（主键），title,content，栏目ID（外键））；
	static final String sqlCreatColumnContent = "create table ColumnContent(ContentID int primary key AUTO_INCREMENT,ContentTitle varchar(20),ContentText text,ColumnID int);";
	static final String sqlDropColumnContent = "drop table ColumnContent;";

	// 论坛用户（用户ID（主键），name）
	static final String sqlCreateForumUser = "create table ForumUser(UserID int primary key AUTO_INCREMENT,UserName varchar(20));";
	static final String sqlDropForumUser = "drop table ForumUser;";

	// 用户--论坛栏目关注关系
	static final String sqlCreateUserColumnConnection = "create table UserColumnConnection(ConnectionID int primary key AUTO_INCREMENT,UserID int,ColumnID int);";
	static final String sqlDropUserColumnConnection = "drop table UserColumnConnection;";
	
	// 论坛推送消息表
	static final String sqlCreateForumSendInfo = "create table ForumSendInfo(InfoID int primary key AUTO_INCREMENT,UserID int,ContentID int);";
	static final String sqlDropForumSendInfo = "drop table ForumSendInfo;";

	// 创建数据库相关表
	public void CreateTables() {
		// 数据库建立
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
