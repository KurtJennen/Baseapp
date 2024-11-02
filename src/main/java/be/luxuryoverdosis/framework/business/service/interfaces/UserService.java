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
	int YEAR = 0;
	int HALF_YEAR = 1;
	int DAYS_OF_YEAR = 365;
	int DAYS_OF_HALF_YEAR = 183;
	int DAYS_OF_WARNING = 10;
	
	void validate(User user);
	
	UserDTO createOrUpdateDTO(UserDTO userDTO);
	UserDTO createOrUpdateDTO(UserDTO userDTO,  String[] unlinkedRoleName);
	UserDTO readDTO(int id);
	UserDTO readNameDTO(String name);
	
	User createOrUpdate(User user, int[] linkedRoleIds, int[] unlinkedRoleIds);
	User createOrUpdate(User user, String[] unlinkedRoleNames);
	User read(int id);
	User readName(String name);
	void delete(int id);
	
	ArrayList<User> list();
	ArrayList<UserDTO> listDTO();
	ArrayList<UserDTO> listDTO(String searchValue);
	
	LoginWrapperDTO getLoginWrapperDTO(String name, MenuRepository menuRepository);
	ListUserWrapperDTO getListUserWrapperDTO(SearchSelect searchSelect, SearchCriteria searchCriteria);
	
	long count(String name, int id);
	
	File createDocument(int documentId);
	File createDocumentAndConvertToPdf(int documentId);
	
	boolean isActiviation();
	User activate(int id, int period);
	User deactivate(int id);
	int daysBeforeDeactivate(UserDTO userDTO);
}
