package be.luxuryoverdosis.framework.data.wrapperdto;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import net.sf.navigator.menu.MenuRepository;

public class LoginWrapperDTO {
	private UserDTO userDTO;
	private boolean activation;
	private int days;
	private MenuRepository menuRepository;
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(final UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(final boolean activation) {
		this.activation = activation;
	}
	public int getDays() {
		return days;
	}
	public void setDays(final int days) {
		this.days = days;
	}
	public MenuRepository getMenuRepository() {
		return menuRepository;
	}
	public void setMenuRepository(final MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
}
