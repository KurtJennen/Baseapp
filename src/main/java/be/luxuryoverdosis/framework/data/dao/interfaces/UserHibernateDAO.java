package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;

public interface UserHibernateDAO {
	public User createOrUpdate(User user);
	public User read(int id);
	public User readName(String name);
	public void delete(int id);
	
	public ArrayList<User> list();
	public ArrayList<UserDTO> listDTO();
	public ArrayList<UserDTO> listDTO(String searchValue);
	
	public long count(String name, int id);
}
