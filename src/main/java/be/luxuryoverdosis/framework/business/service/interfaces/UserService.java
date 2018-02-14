package be.luxuryoverdosis.framework.business.service.interfaces;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.UserTO;

public interface UserService {
	public static final int YEAR = 0;
	public static final int HALF_YEAR = 1;
	public static final int DAYS_OF_YEAR = 365;
	public static final int DAYS_OF_HALF_YEAR = 183;
	public static final int DAYS_OF_WARNING = 10;
	
	public UserDTO createOrUpdateDTO(UserDTO userDTO);
	public UserDTO readDTO(int id);
	
	public UserTO createOrUpdate(UserTO userTO);
	public UserTO read(int id);
	public UserTO readName(String name);
	public void delete(int id);
	
	public ArrayList<UserTO> list();
	public ArrayList<UserDTO> listDTO();
	public ArrayList<UserDTO> listDTO(String searchValue);
	
	public long count(String name, int id);
	
	public File createDocument(int documentId);
	public void writeDocument(final File file, final OutputStream outputStream);
	
	public UserTO activate(int id, int period);
	public UserTO deactivate(int id);
	public int daysBeforeDeactivate(UserTO userTO);
}
