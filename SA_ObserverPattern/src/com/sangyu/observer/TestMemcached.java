package com.sangyu.observer;

import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.spy.memcached.MemcachedClient;

public class TestMemcached {
	public MemcachedClient MemConnect() {
		MemcachedClient mcc = null;
		try {
			// 连接本地的 Memcached 服务
			mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			System.out.println("Connection to server sucessful.");
			return mcc;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}

	public void MemClose(MemcachedClient memcachedClient) {
		memcachedClient.shutdown();
	}

	public MemcachedClient MemDataTrans() {
		MemcachedClient mcc = this.MemConnect();
		//从数据库都取数据到内存
		DBHelper dbHelper = new DBHelper();
		int InfoID, UserID, ContentID, ColumnID;
		String sInfoID, sUserID, sContentID, sColumnID,sKey,sValues;
		int ContentIndex;
		String sContentIndex;
		for(int i=1;i<=10;i++) {
			ResultSet rSet=dbHelper.DBExecuteQuery("select * from ForumSendInfo where ColumnID = "+i+" limit 1000;");
			ContentIndex=1;
			try {
				while (rSet.next()) {
					InfoID = rSet.getInt("InfoID");
					ContentID = rSet.getInt("ContentID");
					UserID = rSet.getInt("UserID");
					ColumnID = rSet.getInt("ColumnID");
					
					sInfoID = String.valueOf(InfoID); 
					sContentID = String.valueOf(ContentID);
					sUserID=String.valueOf(UserID);
					sColumnID=String.valueOf(ColumnID);
					sContentIndex=String.valueOf(ContentIndex);
					
					sKey=sColumnID+"#"+sContentIndex;
					sValues= sInfoID+"#"+sUserID+"#"+sContentID+"#"+sColumnID;
					mcc.set(sKey, 1000, sValues);
					ContentIndex++;
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mcc;
	}
}
