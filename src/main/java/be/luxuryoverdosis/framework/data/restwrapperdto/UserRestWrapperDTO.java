package be.luxuryoverdosis.framework.data.restwrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserDTO;

public class UserRestWrapperDTO extends BaseRestWrapperDTO {
	private UserDTO userDTO;
	private ArrayList<UserDTO> userDTOList;

	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ArrayList<UserDTO> getUserDTOList() {
		return userDTOList;
	}
	public void setUserDTOList(ArrayList<UserDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}
}
