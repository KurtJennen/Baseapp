package be.luxuryoverdosis.baseapp.web.util;

import java.text.ParseException;

import org.displaytag.decorator.TableDecorator;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.to.UserTO;

public class UserTableDecorator extends TableDecorator {
	public String getDateExpiration() throws ParseException {
		UserTO userTO = (UserTO)getCurrentRowObject();
		return DateTool.formatUtilDate(userTO.getDateExpiration());
	}
}
