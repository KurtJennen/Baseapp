package be.luxuryoverdosis.framework.business.service.implementations;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.service.interfaces.SearchService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.document.UserDocument;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.data.wrapperdto.ListUserWrapperDTO;
import be.luxuryoverdosis.framework.data.wrapperdto.LoginWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.navigator.menu.MenuRepository;

@Service
public class UserServiceSpringImpl implements UserService {
	@Resource
	private UserHibernateDAO userHibernateDAO;
	@Resource
	private RoleHibernateDAO roleHibernateDAO;
	@Resource
	private BatchJobInstanceHibernateDAO batchJobInstanceHibernateDAO;
	@Resource
	private MenuService menuService;
	@Resource
	private DocumentService documentService;
	@Resource
	private SearchService searchService;
	@Resource(name=BaseSpringServiceConstants.SENDER_SERVICE)
	private JavaMailSender javaMailSender;
	
	@Transactional
	public UserDTO createOrUpdateDTO(final UserDTO userDTO) {
		Logging.info(this, "Begin createUserDTO");
		
		User user = new User();
		if(userDTO.getId() > 0) {
			user = this.read(userDTO.getId());
		}
		user = UserFactory.produceUser(user, userDTO);
		if(userDTO.getRoleId() > 0) {
			user.setRole(roleHibernateDAO.read(userDTO.getRoleId()));
		}
		
		user = this.createOrUpdate(user);
		
		Logging.info(this, "End createUserDTO");
		return this.readDTO(user.getId());
	}
	
	@Transactional(readOnly=true)
	public UserDTO readDTO(final int id) {
		Logging.info(this, "Begin readUserDTO");
		
		User user = this.read(id);
		
		UserDTO userDTO = UserFactory.produceUserDTO(user);
		
		Logging.info(this, "End readUserDTO");
		return userDTO;
	}
	
	@Transactional
	public User createOrUpdate(final User user) {
		Logging.info(this, "Begin createUser");
		if(userHibernateDAO.count(user.getName(), user.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.user"});
		}
		if(user.getName() == null || StringUtils.isEmpty(user.getName())) {
			throw new ServiceException("errors.required", new String[] {"security.name.unique"});
		}
		if(user.getUserName() == null || StringUtils.isEmpty(user.getUserName())) {
			throw new ServiceException("errors.required", new String[] {"security.username"});
		}
		if(user.getEncryptedPassword() == null || StringUtils.isEmpty(user.getEncryptedPassword())) {
			throw new ServiceException("errors.required", new String[] {"security.password"});
		}
		if(user.getEmail() == null || StringUtils.isEmpty(user.getEmail())) {
			throw new ServiceException("errors.required", new String[] {"security.email"});
		}
		
		//user.setEncryptedPassword(Encryption.encode(user.getEncryptedPassword()));
		
		if(user.getDateExpiration() == null) {
			user.setDateExpiration(DateTool.getDefaultDateFromCalendar());
		}
		
		if(user.getRole() == null) {
			user.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_NORMALE_GEBRUIKER));
		}
		
		User result = null;
		result = userHibernateDAO.createOrUpdate(user);
		if(result != null) {
			sendUserMail(result);
		}
		
		Logging.info(this, "End createUser");
		return result;
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
		User user = userHibernateDAO.readName(name);
		loginWrapperDTO.setUser(user);
		
		int days = daysBeforeDeactivate(user);
		loginWrapperDTO.setDays(days);
		if(user != null && days == 0) {
			deactivate(user.getId());
		}
		
		if(user != null) {
			loginWrapperDTO.setMenuRepository(menuService.produceAlterredMenu(menuRepository, user.getId()));
		}
		
		Logging.info(this, "End getLoginWrapperDTO");
		return loginWrapperDTO;
	}
	
	@Deprecated
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
			text.append(addMailKeyText("mail.user.text4.create", user.getRole().getName()));
			
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
		user.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_UITGEBREIDE_GEBRUIKER));
		
		Logging.info(this, "Begin activate");
		
		return userHibernateDAO.createOrUpdate(user);
	}
	
	@Transactional
	public User deactivate(final int id) {
		Logging.info(this, "Begin deactivate");
		User user = userHibernateDAO.read(id);
		
		user.setDateExpiration(DateTool.getDefaultDateFromCalendar());
		user.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_NORMALE_GEBRUIKER));
		
		Logging.info(this, "Begin deactivate");
		
		return userHibernateDAO.createOrUpdate(user);
	}
	
	@Transactional
	public int daysBeforeDeactivate(final User user) {
		Logging.info(this, "Begin daysBeforeDeactivate");
		
		int days = -1;
		
		if(user == null) {
			return 0;
		}
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_WARNING);
		
		Calendar expCalendar = Calendar.getInstance();
		expCalendar.setTime(user.getDateExpiration());
		
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
