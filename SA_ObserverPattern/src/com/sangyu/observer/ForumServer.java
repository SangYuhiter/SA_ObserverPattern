package com.sangyu.observer;
import java.util.List;
//论坛服务类
public class ForumServer extends Observerable {
	@Override
	public void registerObserver(ForumUser forumUser) {
		// TODO Auto-generated method stub
		forumUser.addForumUser(forumUser);
	}

	@Override
	public void removeObserver(ForumUser forumUser) {
		// TODO Auto-generated method stub
		forumUser.delForumUser(forumUser);
	}
	
	@Override
	public void notifyObserver() {
		ForumUser forumUser=new ForumUser();
		List<ForumUser> forumUsers= forumUser.readForumUser();
		for(ForumUser fu:forumUsers) {
			fu.update();
		}
	}

}
