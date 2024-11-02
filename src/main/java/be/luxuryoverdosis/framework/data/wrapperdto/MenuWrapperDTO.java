package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.User;

public class MenuWrapperDTO {
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(final ArrayList<User> userList) {
		this.userList = userList;
	}
	public ArrayList<MenuDTO> getMenuDTOList() {
		return menuDTOList;
	}
	public void setMenuDTOList(final ArrayList<MenuDTO> menuDTOList) {
		this.menuDTOList = menuDTOList;
	}
}
