package be.luxuryoverdosis.framework.web.action.ajaxaction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;

import be.luxuryoverdosis.framework.web.action.BaseAction;
import net.sf.json.JSONArray;

public class AjaxAction extends BaseAction {	
	public ActionForward sendAsJson(HttpServletResponse response, Object object) throws Exception {
		
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		
		JSONArray jsonArray = JSONArray.fromObject(object);
		
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
		out.flush();
		
		return null;
	}
}
