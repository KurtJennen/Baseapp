package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;

public interface UserHibernateDAO {
	User createOrUpdate(User user);
	User read(int id);
	User readName(String name);
	void delete(int id);
	
	ArrayList<User> list();
	ArrayList<UserDTO> listDTO();
	ArrayList<UserDTO> listDTO(String searchValue);
	
	long count(String name, int id);
}
