package be.luxuryoverdosis.framework.business.service;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.luxuryoverdosis.framework.base.Config;
import be.luxuryoverdosis.framework.logging.Logging;

public abstract class BaseSpringServiceLocator {
	public abstract String[] getConfigLocations();

	private static ApplicationContext applicationContext;
	
	public BaseSpringServiceLocator() {
		try {
			setApplicationContext(new ClassPathXmlApplicationContext(getConfigLocations()));
		}
		catch (BeansException e) {
			e.printStackTrace();
		}
		Logging.debug(this, "Initializing applicationContext");
	}
	
	public static Object getBean(String beanName) {
		return BaseSpringServiceLocator.getApplicationContext().getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> beanClass) {
		return BaseSpringServiceLocator.getApplicationContext().getBean(beanClass);
	}
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		BaseSpringServiceLocator.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static String getMessage(String key) {
		return getMessage(key, null, null);
	}
	
	public static String getMessage(String key, Object[] arg) {
		return getMessage(key, arg, null);
	}
	
	public static String getMessage(String key, Object[] arg, Locale locale) {
		if(locale == null) {
			String fmt_locale = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE);
			if(fmt_locale.length() == 2) {
				locale = new Locale(fmt_locale.substring(0, 2));
			}
			if(fmt_locale.length() == 5) {
				locale = new Locale(fmt_locale.substring(0, 2), fmt_locale.substring(3, 5));
			}
			if(fmt_locale.length() == 8) {
				locale = new Locale(fmt_locale.substring(0, 2), fmt_locale.substring(3, 5), fmt_locale.substring(6, 8));
			}
			if(locale == null) {
				locale = Locale.getDefault();
			}
		}		
		
		return applicationContext.getMessage(key, arg, locale);
	}
}
