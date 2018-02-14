package be.luxuryoverdosis.framework.business.thread;

import be.luxuryoverdosis.framework.data.to.UserTO;

public class ThreadManager {
	private static final ThreadLocal<UserTO> THREAD_LOCAL = new ThreadLocal<UserTO>();
	
	public static void setUserOnThread(UserTO userTO) {
		THREAD_LOCAL.set(userTO);
	}
	
	public static UserTO getUserFromThread() {
		return (UserTO) THREAD_LOCAL.get();
	}
}
