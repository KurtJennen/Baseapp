package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.User;

public class MenuWrapperDTO {
	private ArrayList<User> userList;
	private ArrayList<MenuDTO> menuDTOList;
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	public ArrayList<MenuDTO> getMenuDTOList() {
		return menuDTOList;
	}
	public void setMenuDTOList(ArrayList<MenuDTO> menuDTOList) {
		this.menuDTOList = menuDTOList;
	}
}
