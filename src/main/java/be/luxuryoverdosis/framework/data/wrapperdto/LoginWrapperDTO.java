package be.luxuryoverdosis.framework.data.wrapperdto;

import be.luxuryoverdosis.framework.data.to.User;
import net.sf.navigator.menu.MenuRepository;

public class LoginWrapperDTO {
	private User user;
	private int days;
	private MenuRepository menuRepository;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
