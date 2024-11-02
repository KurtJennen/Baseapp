package be.luxuryoverdosis.framework.business.thread;

import be.luxuryoverdosis.framework.data.dto.UserDTO;

public final class ThreadManager {
	private ThreadManager() {
	}
	
	private static final ThreadLocal<UserDTO> THREAD_LOCAL = new ThreadLocal<UserDTO>();
	
	public static void setUserOnThread(final UserDTO userDTO) {
		THREAD_LOCAL.set(userDTO);
	}
	
	public static UserDTO getUserFromThread() {
		return (UserDTO) THREAD_LOCAL.get();
	}
}
