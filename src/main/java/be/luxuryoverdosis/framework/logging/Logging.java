package be.luxuryoverdosis.framework.logging;

import org.apache.log4j.Logger;

public class Logging {
	//debug
	public static void debug(Object object, String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.debug(message);
	}
	
	public static void debug(Object object, Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.debug(object, throwable);
	}
	
	//info
	public static void info(Object object, String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.info(message);
	}
	
	public static void info(Object object, Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.info(object, throwable);
	}
	
	//warn
	public static void warn(Object object, String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.warn(message);
	}
	
	public static void warn(Object object, Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.warn(object, throwable);
	}
	
	//error
	public static void error(Object object, String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.error(message);
	}
	
	public static void error(Object object, Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.error(object, throwable);
	}
	
	//fatal
	public static void fatal(Object object, String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.fatal(message);
	}
	
	public static void fatal(Object object, Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.fatal(object, throwable);
	}
}
