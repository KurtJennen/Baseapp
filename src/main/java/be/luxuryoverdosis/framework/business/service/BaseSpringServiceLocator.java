package be.luxuryoverdosis.framework.business.service;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.Config;
import be.luxuryoverdosis.framework.logging.Logging;

public abstract class BaseSpringServiceLocator {
	public abstract String[] getConfigLocations();

	private static ApplicationContext applicationContext;
	
	public BaseSpringServiceLocator() {
		try {
			setApplicationContext(new ClassPathXmlApplicationContext(getConfigLocations()));
		} catch (BeansException e) {
			e.printStackTrace();
		}
		Logging.debug(this, "Initializing applicationContext");
	}
	
	public static Object getBean(final String beanName) {
		return BaseSpringServiceLocator.getApplicationContext().getBean(beanName);
	}
	
	public static <T> T getBean(final Class<T> beanClass) {
		return BaseSpringServiceLocator.getApplicationContext().getBean(beanClass);
	}
	
	public static void setApplicationContext(final ApplicationContext applicationContext) {
		BaseSpringServiceLocator.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static String getMessage(final String key) {
		return getMessage(key, null, null);
	}
	
	public static String getMessage(final String key, final Object[] arg) {
		return getMessage(key, arg, null);
	}
	
	public static String getMessage(final String key, final Object[] arg, final Locale locale) {
		Locale l = locale;
		
		if (l == null) {
			String fmtLocale = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE);
			if (fmtLocale.length() == BaseConstants.TWEE) {
				l = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE));
			}
			if (fmtLocale.length() == BaseConstants.VIJF) {
				l = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE), fmtLocale.substring(BaseConstants.DRIE, BaseConstants.VIJF));
			}
			if (fmtLocale.length() == BaseConstants.ACHT) {
				l = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE), fmtLocale.substring(BaseConstants.DRIE, BaseConstants.VIJF), fmtLocale.substring(BaseConstants.ZES, BaseConstants.ACHT));
			}
			if (l == null) {
				l = Locale.getDefault();
			}
		}
		
		
		try {
			return applicationContext.getMessage(key, arg, l);
		} catch (NoSuchMessageException e) {
			return key;
		}
		
	}
}
