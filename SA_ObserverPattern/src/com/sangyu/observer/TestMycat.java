package com.sangyu.observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TestMycat {
	//插入用户实例
	public void InsertUserData() {
		// 用户实例:1000个
      ForumServer server = new ForumServer();
      String[] username_array= new String[1000];
      String[] firstname_array={"赵","钱","孙","李","周","吴","郑","王","冯","陈"};
      String[] lastname_array={"一","二","三","四","五","六","七","八","九","十"};
      for(int i=0;i<firstname_array.length;i++) {
      	for(int j=0;j<lastname_array.length;j++) {
      		for(int k=0;k<lastname_array.length;k++) {
      			username_array[i*100+j*10+k]=firstname_array[i]+lastname_array[j]+lastname_array[k];
      		}
      	}
      }
      for(int i=0;i<username_array.length;i++) {
      	server.registerObserver(new ForumUser(username_array[i]));
      }
	}
	
	//插入栏目
	public void InsertColumnID() {
		// 栏目实例:10个
      ForumColumn column = new ForumColumn();
      String[] columnname_array= {
      		"二手市场(1)","英语学习(2)","校园通知(3)","附近美食(4)","天气物语(5)",
      		"城市风光(6)","学术交流(7)","课程风采(8)","校园一角(9)","学生管理(10)"};
      for(int i=0;i<columnname_array.length;i++) {
      	column.addForumColumn(new ForumColumn(columnname_array[i]));
      }
	}
	
	//插入用户关注
	public void InsertUserColumnConnectionData() {
		// 用户关注实例:8000个，平均每个栏目有800人关注
      UserColumnConnection connection=new UserColumnConnection();
      int[][] UserID_ColumnID= new int[8000][2];
      int temp=0;
      for(int i=1;i<=10;i++) {
      	for(int j=1;j<=800;j++) {
      		int temp1=j+temp;
      		if(temp1>1000)temp1-=1000;
      		UserID_ColumnID[(i-1)*800+j-1][0]=temp1;
      		UserID_ColumnID[(i-1)*800+j-1][1]=i;
      	}
      	temp+=22;
      }
      for(int i=0;i<8000;i++) {
      	System.out.println(UserID_ColumnID[i][0]+" "+UserID_ColumnID[i][1]);
      }
      for(int i=0;i<UserID_ColumnID.length;i++) {
      	connection.addUserColumnConnection(
      			new UserColumnConnection(UserID_ColumnID[i][0], 
      					UserID_ColumnID[i][1]));
      }
	}
	
	//插入栏目文章
	public void InsertContentData() {
		// 栏目内容实例:每个栏目一个，可重复插入，带有标签，一条栏目内容导致800条消息的产生，要达到5000000条的数据量，需重复625次
      ColumnContent columnContent=new ColumnContent();
      for(int i=1;i<=10;i++) {
      	for(int j=1;j<=625;j++) {
      		ColumnContent columnContent1=new ColumnContent("标题","正文:栏目"+i+"第"+j+"次" , i);
              columnContent.addColumnContent(columnContent1);
              
      	}
      }
	}
	
	
	//插入500万条数据（通知消息）
	public void InsertForumSendInfoData() {
		// 消息内容实例:UserID(1-1000),ContentID(1-6250)
		// JDBC 驱动名及数据库 URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:8066/Forum?rewriteBatchedStatements=true";

		// 数据库的用户名与密码，需要根据自己的设置
		final String USER = "mycat";
		final String PASS = "mycat";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rt = null;
		int DataNum=5000000;
		int UserNum=1000;
		int ContentNum=6250;
		int ColumnNum=10;
		int BatchNum=100000;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO ForumSendInfo(InfoID,UserID,ContentID,ColumnID) values(?,?,?,?)";
			pstm = con.prepareStatement(sql);
			int UserID,ContentID,ColumnID;
			Random rand = new Random();
			int countnum=0;
			System.out.println("开始插入数据...");
			System.out.println("已完成"+countnum+"%...");
			for (int i = 1; i <= DataNum; i++) {
				pstm.setInt(1, i);
				UserID = rand.nextInt(UserNum)+1;
				ContentID = rand.nextInt(ContentNum)+1;
				ColumnID=rand.nextInt(ColumnNum)+1;
				pstm.setInt(2, UserID);
				pstm.setInt(3, ContentID);
				pstm.setInt(4, ColumnID);
				pstm.addBatch();
				if (i % BatchNum == 0) {
					System.out.println("已完成"+i*100/DataNum+"%...");
					pstm.executeBatch();
					countnum++;
				}
			}
			pstm.executeBatch();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
}
