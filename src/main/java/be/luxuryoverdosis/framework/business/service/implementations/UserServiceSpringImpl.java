package be.luxuryoverdosis.framework.business.service.implementations;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum;
import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.service.interfaces.SearchService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.document.UserDocument;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.data.to.UserRole;
import be.luxuryoverdosis.framework.data.wrapperdto.ListUserWrapperDTO;
import be.luxuryoverdosis.framework.data.wrapperdto.LoginWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.navigator.menu.MenuRepository;

@Service
public class UserServiceSpringImpl implements UserService {
	@Value("${security.activation}")
	private boolean activation;
	@Value("${security.default.role}")
	private String defaultRoleName;
	@Value("${security.activate.role}")
	private String activateRoleName;
	@Value("${security.deactivate.role}")
	private String deactivateRoleName;
	@Resource
	private UserHibernateDAO userHibernateDAO;
	@Resource
	private RoleHibernateDAO roleHibernateDAO;
	@Resource
	private UserRoleHibernateDAO userRoleHibernateDAO;
	@Resource
	private BatchJobInstanceHibernateDAO batchJobInstanceHibernateDAO;
	@Resource
	private MenuService menuService;
	@Resource
	private DocumentService documentService;
	@Resource
	private SearchService searchService;
	@Resource
	private JavaMailSender javaMailSender;
	
	public boolean isActiviation() {
		return activation;
	}
	
	@Transactional
	public UserDTO createOrUpdateDTO(final UserDTO userDTO) {
		Logging.info(this, "Begin createUserDTO");
		
		User user = new User();
		if(userDTO.getId() > 0) {
			user = this.read(userDTO.getId());
		}
		user = UserFactory.produceUser(user, userDTO);
		if(userDTO.isRegister()) {
			user = this.createOrUpdate(user, new String[]{defaultRoleName});
		} else {
			user = this.createOrUpdate(user, userDTO.getLinkedRoleIds(), userDTO.getUnlinkedRoleIds());
		}
		
		Logging.info(this, "End createUserDTO");
		return this.readDTO(user.getId());
	}
	
	@Transactional(readOnly=true)
	public UserDTO readDTO(final int id) {
		Logging.info(this, "Begin readUserDTO");
		
		User user = this.read(id);
		
		UserDTO userDTO = UserFactory.produceUserDTO(user);
		userDTO.setActivation(activation);
		userDTO.setRoles(getMaxRangRoles(userDTO));
		
		Logging.info(this, "End readUserDTO");
		return userDTO;
	}
	
	@Transactional(readOnly=true)
	public UserDTO readNameDTO(final String name) {
		Logging.info(this, "Begin readUserDTO");
		
		User user = userHibernateDAO.readName(name);
		UserDTO userDTO = null;
		
		if(user != null) {
			userDTO = UserFactory.produceUserDTO(user);
			userDTO.setActivation(activation);
			userDTO.setRoles(getMaxRangRoles(userDTO));
		}
		
		Logging.info(this, "End readUserDTO");
		return userDTO;
	}
	
	@Transactional
	public User createOrUpdate(final User user, final int[] linkedRoleIds, final int[] unlinkedRoleIds) {
		Logging.info(this, "Begin createUser");
		
		validate(user);
		
		User result = userHibernateDAO.createOrUpdate(user);
		if(result != null) {
			sendUserMail(result);
		}
		
		if(linkedRoleIds != null) {
			for (int i = 0; i < linkedRoleIds.length; i++) {
				if(linkedRoleIds[i] > 0) {
					userRoleHibernateDAO.delete(user.getId(), linkedRoleIds[i]);
				}
			}
		}
		
		if(unlinkedRoleIds != null) {
			for (int i = 0; i < unlinkedRoleIds.length; i++) {
				if(unlinkedRoleIds[i] > 0) {
					UserRole userRole = new UserRole();
					userRole.setUser(user);
					userRole.setRole(roleHibernateDAO.read(unlinkedRoleIds[i]));
					userRoleHibernateDAO.createOrUpdate(userRole);
				}
			}
		}
		
		if(userRoleHibernateDAO.countUser(user.getId()) == 0) {
			throw new ServiceException("errors.selected.one.more.role");
		}
		
		Logging.info(this, "End createUser");
		return result;
	}
	
	@Transactional
	public User createOrUpdate(final User user, final String[] unlinkedRoleNames) {
		Logging.info(this, "Begin createUser");
		
		validate(user);
		
		User result = userHibernateDAO.createOrUpdate(user);
		if(result != null) {
			sendUserMail(result);
		}
		
		userRoleHibernateDAO.delete(user.getId());
		
		if(unlinkedRoleNames != null) {
			for (int i = 0; i < unlinkedRoleNames.length; i++) {
				Role role = roleHibernateDAO.readName(unlinkedRoleNames[i]);
				if(role != null) {
					UserRole userRole = new UserRole();
					userRole.setUser(user);
					userRole.setRole(role);
					userRoleHibernateDAO.createOrUpdate(userRole);
				}
			}
		}
		
		if(userRoleHibernateDAO.countUser(user.getId()) == 0) {
			throw new ServiceException("errors.selected.one.more.role");
		}
		
		Logging.info(this, "End createUser");
		return result;
	}
	
	public void validate(final User user) {
		if(userHibernateDAO.count(user.getName(), user.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.user"});
		}
		if(StringUtils.isEmpty(user.getName())) {
			throw new ServiceException("errors.required", new String[] {"security.name.unique"});
		}
		if(StringUtils.isEmpty(user.getUserName())) {
			throw new ServiceException("errors.required", new String[] {"security.username"});
		}
		if(StringUtils.isEmpty(user.getEncryptedPassword())) {
			throw new ServiceException("errors.required", new String[] {"security.password"});
		}
		if(StringUtils.isEmpty(user.getEmail())) {
			throw new ServiceException("errors.required", new String[] {"security.email"});
		}
		
		if(user.getDateExpiration() == null) {
			user.setDateExpiration(DateTool.getDefaultDateFromCalendar());
		}
	}
	
	@Transactional(readOnly=true)
	public User read(final int id) {
		Logging.info(this, "Begin readUser");
		User result = null;
		result = userHibernateDAO.read(id);
		Logging.info(this, "End readUser");
		return result;
	}
	
	@Transactional(readOnly=true)
	public User readName(final String name) {
		Logging.info(this, "Begin readNameUser");
		User result = null;
		result = userHibernateDAO.readName(name);
		Logging.info(this, "End readNameUser");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteUser");
		menuService.deleteForUser(id);
		userRoleHibernateDAO.delete(id);
		userHibernateDAO.delete(id);
		Logging.info(this, "End deleteUser");
	}

	@Transactional(readOnly=true)
	public ArrayList<User> list() {
		Logging.info(this, "Begin listUser");
		ArrayList<User> arrayList = null;
		arrayList = userHibernateDAO.list();
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<UserDTO> listDTO() {
		Logging.info(this, "Begin listUser");
		ArrayList<UserDTO> arrayList = null;
		arrayList = userHibernateDAO.listDTO();
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<UserDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listUser");
		ArrayList<UserDTO> arrayList = null;
		arrayList = userHibernateDAO.listDTO(searchValue);
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@Transactional
	public LoginWrapperDTO getLoginWrapperDTO(String name, MenuRepository menuRepository) {
		Logging.info(this, "Begin getLoginWrapperDTO");
		
		LoginWrapperDTO loginWrapperDTO = new LoginWrapperDTO();
		loginWrapperDTO.setActivation(activation);
		
		UserDTO userDTO = this.readNameDTO(name);
		if(userDTO != null) {
			ArrayList<String> roleNames = getMaxRangRoles(userDTO);
			userDTO.setRoles(roleNames);
			loginWrapperDTO.setUserDTO(userDTO);
			
			int days = daysBeforeDeactivate(userDTO);
			loginWrapperDTO.setDays(days);
			if(userDTO != null && days == 0) {
				deactivate(userDTO.getId());
			}
			
			loginWrapperDTO.setMenuRepository(menuService.produceAlterredMenu(menuRepository, userDTO.getId()));
		}
		
		Logging.info(this, "End getLoginWrapperDTO");
		return loginWrapperDTO;
	}

	private ArrayList<String> getMaxRangRoles(UserDTO userDTO) {
		ArrayList<UserRoleDTO> userRolesList = userRoleHibernateDAO.listDTO(userDTO.getId());
		HashMap<Integer, RoleNameEnum> maxRangRoles = new HashMap<Integer, RoleNameEnum>();
		
		for (UserRoleDTO userRoleDTO : userRolesList) {
			RoleNameEnum roleNameEnum = RoleNameEnum.convert(userRoleDTO.getRoleName());
			
			if(maxRangRoles.containsKey(roleNameEnum.getType())) {
				if (roleNameEnum.getRang() < maxRangRoles.get(roleNameEnum.getType()).getRang()) {
					maxRangRoles.put(roleNameEnum.getType(), roleNameEnum);
				}
			} else {
				maxRangRoles.put(roleNameEnum.getType(), roleNameEnum);
			}
		}
		
		ArrayList<String> roleNames = new ArrayList<String>();
		for (RoleNameEnum value : maxRangRoles.values()) {
			roleNames.add(value.getCode());
		}
		return roleNames;
	}
	
	@Transactional(readOnly=true)
	public ListUserWrapperDTO getListUserWrapperDTO(SearchSelect searchSelect, SearchCriteria searchCriteria) {
		Logging.info(this, "Begin getListUserDTO");
		ListUserWrapperDTO listUserWrapperDTO = new ListUserWrapperDTO();
		listUserWrapperDTO.setSearchUserList(searchService.search(searchSelect, searchCriteria));
		listUserWrapperDTO.setBatchJobInstanceExportList(batchJobInstanceHibernateDAO.list(BaseConstants.JOB_EXPORT_USER));
		listUserWrapperDTO.setBatchJobInstanceImportList(batchJobInstanceHibernateDAO.list(BaseConstants.JOB_IMPORT_USER));
		Logging.info(this, "End getListUserDTO");
		return listUserWrapperDTO;
	}
	
	@Transactional(readOnly=true)
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countUser");
		Long countUser = new Long(0);
		countUser = userHibernateDAO.count(name, id);
		Logging.info(this, "End countUser");
		return countUser.longValue();
	}
	
	private void sendUserMail(final User user) {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			
			StringBuffer text = new StringBuffer();
			text.append(addMailKeyText("mail.user.text.create", user.getName()));
			text.append(addMailKeyText("mail.user.text1.create", user.getUserName()));
			text.append(addMailKeyText("mail.user.text2.create", Encryption.decode(user.getEncryptedPassword())));
			text.append(addMailKeyText("mail.user.text3.create", user.getEmail()));
			
			ArrayList<UserRoleDTO> userRolesList = userRoleHibernateDAO.listDTO(user.getId());
			for (UserRoleDTO userRoleDTO : userRolesList) {
				text.append(addMailKeyText("mail.user.text4.create", userRoleDTO.getRoleNameAsKey()));
			}
			
			helper.setTo(user.getEmail());
			helper.setFrom(BaseConstants.MAIL_FROM);
			helper.setSubject(BaseSpringServiceLocator.getMessage("mail.user.subject.create", new Object[]{user.getName()}));
			helper.setText(text.toString(), true);
			
			//javaMailSender.send(msg);
		} catch (Exception e) {
			throw new ServiceException("mail.send.error");
		}
	}
	
	private StringBuffer addMailKeyText(final String key, final String value) {
		StringBuffer text = new StringBuffer();
		return text.append(BaseSpringServiceLocator.getMessage(key))
			.append(BaseConstants.MAIL_DELIMITER_DOUBLEPOINT)
			.append(value)
			.append(BaseConstants.MAIL_DELIMITER_BR);
	}
	
	@Transactional
	public User activate(final int id, final int period) {
		Logging.info(this, "Begin activate");
		User user = userHibernateDAO.read(id);
		
		Calendar defaultCalendar = DateTool.getDefaultCalendar();
		
		Calendar expCalendar = DateTool.getCalendar(user.getDateExpiration());
		
		if(defaultCalendar.compareTo(expCalendar) == 0) {
			Calendar calendar = Calendar.getInstance();
			if(period == YEAR) {
				calendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_YEAR);
			} else {
				calendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_HALF_YEAR);
			}
			user.setDateExpiration(calendar.getTime());
		} else {
			if(period == YEAR) {
				expCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_YEAR);
			} else {
				expCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_HALF_YEAR);
			}
			user.setDateExpiration(expCalendar.getTime());
		}
//		user.setRole(roleHibernateDAO.readName(RoleNameEnum.UITGEBREIDE_GEBRUIKER.getCode()));
		
		Logging.info(this, "Begin activate");
		
		return this.createOrUpdate(user, new String[]{activateRoleName});
	}
	
	@Transactional
	public User deactivate(final int id) {
		Logging.info(this, "Begin deactivate");
		User user = userHibernateDAO.read(id);
		
		user.setDateExpiration(DateTool.getDefaultDateFromCalendar());
//		user.setRole(roleHibernateDAO.readName(RoleNameEnum.NORMALE_GEBRUIKER.getCode()));
		
		Logging.info(this, "Begin deactivate");
		
		return this.createOrUpdate(user, new String[]{deactivateRoleName});
	}
	
	@Transactional
	public int daysBeforeDeactivate(final UserDTO userDTO) {
		Logging.info(this, "Begin daysBeforeDeactivate");
		
		int days = -1;
		
		if(userDTO == null) {
			return 0;
		}
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_WARNING);
		
		Calendar expCalendar = Calendar.getInstance();
		expCalendar.setTime(userDTO.getDateExpiration());
		
		if(defaultCalendar.after(expCalendar)) {
			int daysExp = expCalendar.get(Calendar.DAY_OF_YEAR);
			int daysDefault = defaultCalendar.get(Calendar.DAY_OF_YEAR);
			days = daysExp - daysDefault + DAYS_OF_WARNING;
			if (days < 0) {
				days = 0;
			}
		}
		
		Logging.info(this, "Begin daysBeforeDeactivate");
		
		return days;
	}
	
	@Transactional(readOnly=true)
	public File createDocument(final int documentId) {
		//Template
		Document document = documentService.read(documentId);
		
		//Data
		UserDocument userDocument = new UserDocument();
		userDocument.setUsers(userHibernateDAO.list());
		
		return documentService.createDocument(document, userDocument, UserDocument.class);
	}
	
//	private JavaMailSenderImpl getJavaMailSenderImpl() {
//		return (JavaMailSenderImpl)BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SENDER_SERVICE);
//	}
	
}
