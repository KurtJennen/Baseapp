package be.luxuryoverdosis.framework.data.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;


public class BaseInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;
	
	public static final String USERADD = "userAdd";
	public static final String USERUPDATE = "userUpdate";
	public static final String DATEADD = "dateAdd";
	public static final String DATEUPDATE = "dateUpdate";

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		return setBaseProperties(state, propertyNames);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, 	String[] propertyNames, Type[] types) {
		return setBaseProperties(currentState, propertyNames);
	}

	private boolean setBaseProperties(Object[] state, String[] propertyNames) {
		boolean changed = false;
		String userName = BaseConstants.JOB_USER_ROOT;
		
		Date now = new Date();
		User user = ThreadManager.getUserFromThread();
		if(user != null) {
			userName = user.getName();
		}
		
		boolean userAddChanged = onCreate(state, propertyNames, USERADD, userName);
		boolean userUpdateChanged = onUpdate(state, propertyNames, USERUPDATE, userName);
		boolean dateAddChanged = onCreate(state, propertyNames, DATEADD, now);
		boolean dateUpdateChanged = onUpdate(state, propertyNames, DATEUPDATE, now);
		
		if(userAddChanged || userUpdateChanged || dateAddChanged || dateUpdateChanged) {
			changed = true;
		}
		
		return changed;
	}
	
	private boolean onCreate(Object[] state, String[] propertyNames, String property, Object value) {
		boolean changed = false;
		
		int index = ArrayUtils.indexOf(propertyNames, property);
		
		if(index != -1) {
			if(state[index] == null) {
				state[index] = value;
				changed = true;
			}
		}
		
		return changed;
	}
	
	private boolean onUpdate(Object[] state, String[] propertyNames, String property, Object value) {
		boolean changed = false;
		
		int index = ArrayUtils.indexOf(propertyNames, property);
		
		if(index != -1) {
			state[index] = value;
			changed = true;
		}
		
		return changed;
	}
}
