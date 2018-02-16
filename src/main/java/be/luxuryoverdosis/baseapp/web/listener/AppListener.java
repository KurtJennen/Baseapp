package be.luxuryoverdosis.baseapp.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceConstants;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.baseapp.business.service.interfaces.SqlExecuterService;
import be.luxuryoverdosis.framework.base.Config;
import be.luxuryoverdosis.framework.logging.Logging;

public class AppListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext servletContext = sce.getServletContext();
			String dbType = servletContext.getInitParameter("dbType");
			String locale = servletContext.getInitParameter(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE);
			String localizationContext = servletContext.getInitParameter(javax.servlet.jsp.jstl.core.Config.FMT_LOCALIZATION_CONTEXT);
			Config config = Config.getInstance();
			config.addKeyValue("dbType", dbType);
			config.addKeyValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE, locale);
			config.addKeyValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALIZATION_CONTEXT, localizationContext);
			
			SpringServiceLocator.getSpringServiceLocator();
			
			SqlExecuterService sqlExecuterService = (SqlExecuterService)SpringServiceLocator.getBean(SpringServiceConstants.SQL_EXECUTER_SERVICE);
			sqlExecuterService.execute();
		} catch (Exception e) {
			Logging.error(this, "AppListener error" + e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}