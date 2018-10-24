package com.sangyu.observer;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.spy.memcached.MemcachedClient;

public class Demo {

	public static void main(String[] args) throws SQLException {
		// 数据库重建
//		ForumDBCreate forumDBCreate = new ForumDBCreate();
//		forumDBCreate.CreateTables();
		
		//数据插入
//		TestMycat testMycat =new TestMycat();
//		testMycat.InsertForumSendInfoData();
		
		
		//数据库读出1000条栏目数据
//		DBHelper dbHelper = new DBHelper();
//		int InfoID,UserID,ContentID,ColumnID;
//		int temp=1;
//		Long beginTime1 = System.currentTimeMillis();
//		for(int i=1;i<=10;i++) {
//			ResultSet rSet=dbHelper.DBExecuteQuery("select * from ForumSendInfo where ColumnID = "+i+" limit 1;");
//			//System.out.println("栏目"+i+"的查询结果:");
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
//		System.out.println("数据库查询所用时间:"+(endTime1-beginTime1)+"ms");
		
		
		//从Memcached中读取数据
//		Long beginTime2 = System.currentTimeMillis();
//		TestMemcached testMemcached =new TestMemcached();
//		MemcachedClient mcc=testMemcached.MemDataTrans();
//		Long endTime2 = System.currentTimeMillis();
//		System.out.println("数据读入memcache所用时间:"+(endTime2-beginTime2)+"ms");
//		String mccGet;
//		int QueryColumnID,QueryContentIndex;
//		Long beginTime3 = System.currentTimeMillis();
//		for(int i=1;i<=10;i++) {
//			System.out.println("栏目"+i+"的查询结果:");
//			QueryColumnID=i;
//			QueryContentIndex=1;
//			mccGet=mcc.get(String.valueOf(QueryColumnID)+"#"+String.valueOf(QueryContentIndex)).toString();
//			System.out.println(mccGet);
//		}
//		testMemcached.MemClose(mcc);
//		Long endTime3 = System.currentTimeMillis();
//		System.out.println("memcache查询所用时间:"+(endTime3-beginTime3)+"ms");
//		System.out.println("hello");

		// 测试更新效果
		
		ColumnContent columnContent = new ColumnContent();

		ColumnContent columnContent1 = new ColumnContent("五成新《数据库》", "现有旧书出售，《数据库》一本，可议价，有意者联系13012345678", 1);
		columnContent.addColumnContent(columnContent1);
//		System.out.println("----------------------------------------------");
//		ColumnContent columnContent2 = new ColumnContent("听力练习角", "英语学习爱好者周六下午在活动室举行听力练习活动", 2);
//		ColumnContent columnContent3 = new ColumnContent("校园运动会", "哈尔滨工业大学运动会于一周后举行", 3);
//		columnContent.addColumnContent(columnContent2);
//		columnContent.addColumnContent(columnContent3);
		
	}
}
