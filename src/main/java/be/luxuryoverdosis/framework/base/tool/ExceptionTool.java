package be.luxuryoverdosis.framework.base.tool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public class ExceptionTool {
	public static String convertExceptionToString(Exception e, String key, Object[] arg) {
		String message = e.getMessage();
		if (message == null) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			message = writer.toString();
		}
		if(message == null) {
			message = BaseSpringServiceLocator.getMessage(key,  arg);
		}
		
		return message;
	}
	
	public static String convertExceptionToString(Exception e, String key) {
		return convertExceptionToString(e, key, null);
	}
	     
}
