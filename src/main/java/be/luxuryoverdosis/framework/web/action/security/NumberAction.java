package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.NumberService;
import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.Number;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.NumberForm;
import be.luxuryoverdosis.framework.web.jmesa.NumberJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class NumberAction extends DispatchAction {
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		ArrayList<Number> numberList = new ArrayList<Number>();
		numberList = numberService.list();
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.NUMBER_LIST, request, response);
		NumberJmesaTemplate numberJmesaTemplate = new NumberJmesaTemplate(tableFacade, numberList, request);
		String html = numberJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.NUMBER_LIST, html);
		//JMesa End
        
        return (mapping.findForward("list"));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
				
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		NumberDTO numberDTO = numberService.readDTO(id);
		NumberForm numberForm = (NumberForm) form;
		numberForm.setObjectId(numberDTO.getId());
		numberForm.setApplicationCode(numberDTO.getApplicationCode());
		numberForm.setYear(numberDTO.getYear());
		numberForm.setNumber(numberDTO.getNumber());
		numberForm.setType(numberDTO.getType());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		NumberForm numberForm = (NumberForm) form;
		numberForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		NumberForm numberForm = (NumberForm) form;
		
		NumberDTO numberDTO = new NumberDTO();
		numberDTO.setId(numberForm.getObjectId());
		numberDTO.setApplicationCode(numberForm.getApplicationCode());
		numberDTO.setYear(numberForm.getYear());
		numberDTO.setNumber(numberForm.getNumber());
		numberDTO.setType(numberForm.getType());
		
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		
		numberDTO = numberService.createOrUpdateDTO(numberDTO);
		if(numberForm.getObjectId() < 0) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.number")));
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.number")));
		}
		
		numberForm.setObjectId(numberDTO.getId());
		
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		numberService.delete(id);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		return list(mapping, form, request, response);
	}
}
