package be.luxuryoverdosis.baseapp.business.service;

public class SpringServiceLocator extends be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator {
	private final static String SPRING_DB_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_db.xml";
	private final static String SPRING_DAO_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_dao.xml";
	private final static String SPRING_SERVICE_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_service.xml";
	private final static String SPRING_JOB_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_job.xml";
	private final static String SPRING_SEARCH_USER_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_search_user.xml";
	private final static String SPRING_BATCH_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_batch.xml";
	private final static String SPRING_WS_XML = "be/luxuryoverdosis/resources/spring/baseapp_spring_ws.xml";

	private static SpringServiceLocator springServiceLocator;
	
	static {
		springServiceLocator = new SpringServiceLocator();
	}
	
	public static SpringServiceLocator getSpringServiceLocator() {
		return springServiceLocator;
	}
	
	public String [] getConfigLocations() {
		return new String[] {SPRING_DB_XML, SPRING_DAO_XML, SPRING_SERVICE_XML, SPRING_JOB_XML, SPRING_SEARCH_USER_XML, SPRING_BATCH_XML, SPRING_WS_XML};
	}
	
}
