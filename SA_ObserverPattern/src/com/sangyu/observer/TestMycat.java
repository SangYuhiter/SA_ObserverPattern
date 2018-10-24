package com.sangyu.observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TestMycat {
	//�����û�ʵ��
	public void InsertUserData() {
		// �û�ʵ��:1000��
      ForumServer server = new ForumServer();
      String[] username_array= new String[1000];
      String[] firstname_array={"��","Ǯ","��","��","��","��","֣","��","��","��"};
      String[] lastname_array={"һ","��","��","��","��","��","��","��","��","ʮ"};
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
	
	//������Ŀ
	public void InsertColumnID() {
		// ��Ŀʵ��:10��
      ForumColumn column = new ForumColumn();
      String[] columnname_array= {
      		"�����г�(1)","Ӣ��ѧϰ(2)","У԰֪ͨ(3)","������ʳ(4)","��������(5)",
      		"���з��(6)","ѧ������(7)","�γ̷��(8)","У԰һ��(9)","ѧ������(10)"};
      for(int i=0;i<columnname_array.length;i++) {
      	column.addForumColumn(new ForumColumn(columnname_array[i]));
      }
	}
	
	//�����û���ע
	public void InsertUserColumnConnectionData() {
		// �û���עʵ��:8000����ƽ��ÿ����Ŀ��800�˹�ע
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
	
	//������Ŀ����
	public void InsertContentData() {
		// ��Ŀ����ʵ��:ÿ����Ŀһ�������ظ����룬���б�ǩ��һ����Ŀ���ݵ���800����Ϣ�Ĳ�����Ҫ�ﵽ5000000���������������ظ�625��
      ColumnContent columnContent=new ColumnContent();
      for(int i=1;i<=10;i++) {
      	for(int j=1;j<=625;j++) {
      		ColumnContent columnContent1=new ColumnContent("����","����:��Ŀ"+i+"��"+j+"��" , i);
              columnContent.addColumnContent(columnContent1);
              
      	}
      }
	}
	
	
	//����500�������ݣ�֪ͨ��Ϣ��
	public void InsertForumSendInfoData() {
		// ��Ϣ����ʵ��:UserID(1-1000),ContentID(1-6250)
		// JDBC �����������ݿ� URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:8066/Forum?rewriteBatchedStatements=true";

		// ���ݿ���û��������룬��Ҫ�����Լ�������
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
			System.out.println("��ʼ��������...");
			System.out.println("�����"+countnum+"%...");
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
					System.out.println("�����"+i*100/DataNum+"%...");
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
