package be.luxuryoverdosis.framework.business.thread;

import be.luxuryoverdosis.framework.data.to.User;

public class ThreadManager {
	private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<User>();
	
	public static void setUserOnThread(User user) {
		THREAD_LOCAL.set(user);
	}
	
	public static User getUserFromThread() {
		return (User) THREAD_LOCAL.get();
	}
}
