package be.luxuryoverdosis.framework.logging;

import org.apache.log4j.Logger;

public final class Logging {
	private Logging() {
	}
	
	//debug
	public static void debug(final Object object, final String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.debug(message);
	}
	
	public static void debug(final Object object, final Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.debug(object, throwable);
	}
	
	//info
	public static void info(final Object object, final String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.info(message);
	}
	
	public static void info(final Object object, final Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.info(object, throwable);
	}
	
	//warn
	public static void warn(final Object object, final String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.warn(message);
	}
	
	public static void warn(final Object object, final Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.warn(object, throwable);
	}
	
	//error
	public static void error(final Object object, final String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.error(message);
	}
	
	public static void error(final Object object, final Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.error(object, throwable);
	}
	
	//fatal
	public static void fatal(final Object object, final String message) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.fatal(message);
	}
	
	public static void fatal(final Object object, final Throwable throwable) {
		Logger logger = Logger.getLogger(object.getClass());
		logger.fatal(object, throwable);
	}
}
