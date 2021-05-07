package be.luxuryoverdosis.framework.data.wrapperdto;

import be.luxuryoverdosis.framework.data.to.User;
import net.sf.navigator.menu.MenuRepository;

public class LoginWrapperDTO {
	private User user;
	private boolean activation;
	private int days;
	private MenuRepository menuRepository;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public MenuRepository getMenuRepository() {
		return menuRepository;
	}
	public void setMenuRepository(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
}
