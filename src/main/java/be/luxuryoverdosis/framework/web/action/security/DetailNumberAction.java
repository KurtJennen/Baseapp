package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.NumberService;
import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailNumberForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailNumberAction extends DispatchAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
		//return new ActionRedirect(mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		NumberDTO numberDTO = numberService.readDTO(id);
		DetailNumberForm numberForm = (DetailNumberForm) form;
		numberForm.setObjectId(numberDTO.getId());
		numberForm.setApplicationCode(numberDTO.getApplicationCode());
		numberForm.setYear(numberDTO.getYear());
		numberForm.setNumber(numberDTO.getNumber());
		numberForm.setType(numberDTO.getType());
		
		if(BaseWebConstants.SAVE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.number")));
		}
		if(BaseWebConstants.UPDATE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.number")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		DetailNumberForm numberForm = (DetailNumberForm) form;
		numberForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("read"));
		//ActionMessages actionMessages = new ActionMessages();
		
		DetailNumberForm numberForm = (DetailNumberForm) form;
		
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
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.number")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.SAVE);
		} else {
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.number")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.UPDATE);
		}
		
		//numberForm.setObjectId(numberDTO.getId());
		
		actionRedirect.addParameter("objectId", numberDTO.getId());
		
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return actionRedirect;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		//ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//NumberService numberService = (NumberService)SpringServiceLocator.getBean(SpringServiceConstants.NUMBER_SERVICE);
		NumberService numberService = BaseSpringServiceLocator.getBean(NumberService.class);
		numberService.delete(id);
		
		//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.number")));
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		//return list(mapping, form, request, response);
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.DELETE);
		return actionRedirect;
	}
}
