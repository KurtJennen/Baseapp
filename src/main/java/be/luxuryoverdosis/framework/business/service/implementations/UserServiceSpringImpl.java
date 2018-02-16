package be.luxuryoverdosis.framework.business.service.implementations;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dao.interfaces.MenuHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.document.UserDocument;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class UserServiceSpringImpl implements UserService {
	@Resource
	private UserHibernateDAO userHibernateDAO;
	@Resource
	private RoleHibernateDAO roleHibernateDAO;
	@Resource
	private MenuHibernateDAO menuHibernateDAO;
	@Resource
	private DocumentService documentService;
	
	@Transactional
	public UserDTO createOrUpdateDTO(final UserDTO userDTO) {
		Logging.info(this, "Begin createUserDTO");
		
		UserTO userTO = new UserTO();
		if(userDTO.getId() > 0) {
			userTO = this.read(userDTO.getId());
		}
		userTO = UserFactory.produceUser(userTO, userDTO);
		if(userDTO.getRoleId() > 0) {
			userTO.setRole(roleHibernateDAO.read(userDTO.getRoleId()));
		}
		
		userTO = this.createOrUpdate(userTO);
		
		Logging.info(this, "End createUserDTO");
		return this.readDTO(userTO.getId());
	}
	
	@Transactional(readOnly=true)
	public UserDTO readDTO(final int id) {
		Logging.info(this, "Begin readUserDTO");
		
		UserTO userTO = this.read(id);
		
		UserDTO userDTO = UserFactory.produceUserDTO(userTO);
		
		Logging.info(this, "End readUserDTO");
		return userDTO;
	}
	
	@Transactional
	public UserTO createOrUpdate(final UserTO userTO) {
		Logging.info(this, "Begin createUser");
		if(userHibernateDAO.count(userTO.getName(), userTO.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.user"});
		}
		if(userTO.getName() == null || StringUtils.isEmpty(userTO.getName())) {
			throw new ServiceException("errors.required", new String[] {"security.name.unique"});
		}
		if(userTO.getUserName() == null || StringUtils.isEmpty(userTO.getUserName())) {
			throw new ServiceException("errors.required", new String[] {"security.username"});
		}
		if(userTO.getEncryptedPassword() == null || StringUtils.isEmpty(userTO.getEncryptedPassword())) {
			throw new ServiceException("errors.required", new String[] {"security.password"});
		}
		if(userTO.getEmail() == null || StringUtils.isEmpty(userTO.getEmail())) {
			throw new ServiceException("errors.required", new String[] {"security.email"});
		}
		
		//userTO.setEncryptedPassword(Encryption.encode(userTO.getEncryptedPassword()));
		
		if(userTO.getDateExpiration() == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(2100, 0, 1);
			userTO.setDateExpiration(calendar.getTime());
		}
		
		if(userTO.getRole() == null) {
			userTO.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_NORMALE_GEBRUIKER));
		}
		
		UserTO result = null;
		result = userHibernateDAO.createOrUpdate(userTO);
		if(result != null) {
			sendUserMail(result);
		}
		
		Logging.info(this, "End createUser");
		return result;
	}
	
	@Transactional(readOnly=true)
	public UserTO read(final int id) {
		Logging.info(this, "Begin readUser");
		UserTO result = null;
		result = userHibernateDAO.read(id);
		Logging.info(this, "End readUser)");
		return result;
	}
	
	@Transactional(readOnly=true)
	public UserTO readName(final String name) {
		Logging.info(this, "Begin readNameUser");
		UserTO result = null;
		result = userHibernateDAO.readName(name);
		Logging.info(this, "End readNameUser");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteUser");
		menuHibernateDAO.deleteForUser(id);
		userHibernateDAO.delete(id);
		Logging.info(this, "End deleteUser");
	}

	@Transactional(readOnly=true)
	public ArrayList<UserTO> list() {
		Logging.info(this, "Begin listUser");
		ArrayList<UserTO> arrayList = null;
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
	
	@Transactional(readOnly=true)
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countUser");
		Long countUser = new Long(0);
		countUser = userHibernateDAO.count(name, id);
		Logging.info(this, "End countUser");
		return countUser.longValue();
	}
	
	private void sendUserMail(final UserTO userTO) {
		try {
			JavaMailSenderImpl sender = (JavaMailSenderImpl)BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SENDER_SERVICE);
			
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			
			StringBuffer text = new StringBuffer();
			text.append(addMailKeyText("mail.user.text.create", userTO.getName()));
			text.append(addMailKeyText("mail.user.text1.create", userTO.getUserName()));
			text.append(addMailKeyText("mail.user.text2.create", Encryption.decode(userTO.getEncryptedPassword())));
			text.append(addMailKeyText("mail.user.text3.create", userTO.getEmail()));
			text.append(addMailKeyText("mail.user.text4.create", userTO.getRole().getName()));
			
			helper.setTo(userTO.getEmail());
			helper.setFrom(BaseConstants.MAIL_FROM);
			helper.setSubject(BaseSpringServiceLocator.getMessage("mail.user.subject.create", new Object[]{userTO.getName()}));
			helper.setText(text.toString(), true);
			
			sender.send(msg);
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
	public UserTO activate(final int id, final int period) {
		Logging.info(this, "Begin activate");
		UserTO userTO = userHibernateDAO.read(id);
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.clear();
		defaultCalendar.set(2100, 0, 1);
		
		Calendar expCalendar = Calendar.getInstance();
		expCalendar.clear();
		expCalendar.setTime(userTO.getDateExpiration());
		
		if(defaultCalendar.compareTo(expCalendar) == 0) {
			Calendar calendar = Calendar.getInstance();
			if(period == YEAR) {
				calendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_YEAR);
			} else {
				calendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_HALF_YEAR);
			}
			userTO.setDateExpiration(calendar.getTime());
		} else {
			if(period == YEAR) {
				expCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_YEAR);
			} else {
				expCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_HALF_YEAR);
			}
			userTO.setDateExpiration(expCalendar.getTime());
		}
		userTO.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_UITGEBREIDE_GEBRUIKER));
		
		Logging.info(this, "Begin activate");
		
		return userHibernateDAO.createOrUpdate(userTO);
	}
	
	@Transactional
	public UserTO deactivate(final int id) {
		Logging.info(this, "Begin deactivate");
		UserTO userTO = userHibernateDAO.read(id);
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2100, 0, 1);
		userTO.setDateExpiration(calendar.getTime());
		userTO.setRole(roleHibernateDAO.readName(BaseConstants.ROLE_NORMALE_GEBRUIKER));
		
		Logging.info(this, "Begin deactivate");
		
		return userHibernateDAO.createOrUpdate(userTO);
	}
	
	@Transactional
	public int daysBeforeDeactivate(final UserTO userTO) {
		Logging.info(this, "Begin daysBeforeDeactivate");
		
		int days = -1;
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.add(Calendar.DAY_OF_YEAR, DAYS_OF_WARNING);
		
		Calendar expCalendar = Calendar.getInstance();
		expCalendar.setTime(userTO.getDateExpiration());
		
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
	
	@Transactional(readOnly=true)
	public void writeDocument(final File file, final OutputStream outputStream) {
		documentService.writeDocument(file, outputStream);
	}
}
