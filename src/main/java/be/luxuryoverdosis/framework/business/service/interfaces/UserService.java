package be.luxuryoverdosis.framework.business.service.interfaces;

import java.io.File;
import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.data.wrapperdto.ListUserWrapperDTO;
import be.luxuryoverdosis.framework.data.wrapperdto.LoginWrapperDTO;
import net.sf.navigator.menu.MenuRepository;

public interface UserService {
	public static final int YEAR = 0;
	public static final int HALF_YEAR = 1;
	public static final int DAYS_OF_YEAR = 365;
	public static final int DAYS_OF_HALF_YEAR = 183;
	public static final int DAYS_OF_WARNING = 10;
	
	public UserDTO createOrUpdateDTO(UserDTO userDTO);
	public UserDTO readDTO(int id);
	
	public User createOrUpdate(User user, String[] unlinkedRoleNames);
	public User createOrUpdate(User user, int[] linkedRoleIds, int[] unlinkedRoleIds);
	public User read(int id);
	public User readName(String name);
	public void delete(int id);
	
	public ArrayList<User> list();
	public ArrayList<UserDTO> listDTO();
	public ArrayList<UserDTO> listDTO(String searchValue);
	
	public LoginWrapperDTO getLoginWrapperDTO(String name, MenuRepository menuRepository);
	public ListUserWrapperDTO getListUserWrapperDTO(SearchSelect searchSelect, SearchCriteria searchCriteria);
	
	public long count(String name, int id);
	
	public File createDocument(int documentId);
	
	public boolean isActiviation();
	public User activate(int id, int period);
	public User deactivate(int id);
	public int daysBeforeDeactivate(User user);
}
