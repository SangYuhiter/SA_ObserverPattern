package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.spy.memcached.MemcachedClient;

public class Demo {

	public static void main(String[] args) throws SQLException {
		// ���ݿ��ؽ�
//		ForumDBCreate forumDBCreate = new ForumDBCreate();
//		forumDBCreate.CreateTables();
		
		//���ݲ���
//		TestMycat testMycat =new TestMycat();
//		testMycat.InsertForumSendInfoData();
		
		
		//���ݿ����1000����Ŀ����
//		DBHelper dbHelper = new DBHelper();
//		int InfoID,UserID,ContentID,ColumnID;
//		int temp=1;
//		Long beginTime1 = System.currentTimeMillis();
//		for(int i=1;i<=10;i++) {
//			ResultSet rSet=dbHelper.DBExecuteQuery("select * from ForumSendInfo where ColumnID = "+i+" limit 1;");
//			//System.out.println("��Ŀ"+i+"�Ĳ�ѯ���:");
//			//System.out.println("count\tInfoID\tUserID\tContentID\tColumnID");
//			try {
//				while (rSet.next()) {
//					InfoID=rSet.getInt("InfoID");
//					ContentID=rSet.getInt("ContentID");
//					UserID=rSet.getInt("UserID");
//					ColumnID=i;
//					if(temp==1) {
//						System.out.println(InfoID+"#"+UserID+"#"+ContentID+"#"+ColumnID);
//						break;
//					}
//					temp++;
//				}
//			}catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		Long endTime1 = System.currentTimeMillis();
//		System.out.println("���ݿ��ѯ����ʱ��:"+(endTime1-beginTime1)+"ms");
		
		
		//��Memcached�ж�ȡ����
//		Long beginTime2 = System.currentTimeMillis();
//		TestMemcached testMemcached =new TestMemcached();
//		MemcachedClient mcc=testMemcached.MemDataTrans();
//		Long endTime2 = System.currentTimeMillis();
//		System.out.println("���ݶ���memcache����ʱ��:"+(endTime2-beginTime2)+"ms");
//		String mccGet;
//		int QueryColumnID,QueryContentIndex;
//		Long beginTime3 = System.currentTimeMillis();
//		for(int i=1;i<=10;i++) {
//			System.out.println("��Ŀ"+i+"�Ĳ�ѯ���:");
//			QueryColumnID=i;
//			QueryContentIndex=1;
//			mccGet=mcc.get(String.valueOf(QueryColumnID)+"#"+String.valueOf(QueryContentIndex)).toString();
//			System.out.println(mccGet);
//		}
//		testMemcached.MemClose(mcc);
//		Long endTime3 = System.currentTimeMillis();
//		System.out.println("memcache��ѯ����ʱ��:"+(endTime3-beginTime3)+"ms");
//		System.out.println("hello");

		// ���Ը���Ч��
		
		ColumnContent columnContent = new ColumnContent();

		ColumnContent columnContent1 = new ColumnContent("����¡����ݿ⡷", "���о�����ۣ������ݿ⡷һ��������ۣ���������ϵ13012345678", 1);
		columnContent.addColumnContent(columnContent1);
//		System.out.println("----------------------------------------------");
//		ColumnContent columnContent2 = new ColumnContent("������ϰ��", "Ӣ��ѧϰ���������������ڻ�Ҿ���������ϰ�", 2);
//		ColumnContent columnContent3 = new ColumnContent("У԰�˶���", "��������ҵ��ѧ�˶�����һ�ܺ����", 3);
//		columnContent.addColumnContent(columnContent2);
//		columnContent.addColumnContent(columnContent3);
		
	}
}
