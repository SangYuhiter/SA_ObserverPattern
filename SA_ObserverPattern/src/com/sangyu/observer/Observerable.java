package com.sangyu.observer;

public abstract class Observerable {

	public abstract void registerObserver(ForumUser forumUser);
    public abstract void removeObserver(ForumUser forumUser);
    public abstract void notifyObserver();
}
