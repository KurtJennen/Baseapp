package be.luxuryoverdosis.framework.data.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.UserDTO;


public class BaseInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;
	
	public static final String USERADD = "userAdd";
	public static final String USERUPDATE = "userUpdate";
	public static final String DATEADD = "dateAdd";
	public static final String DATEUPDATE = "dateUpdate";

	@Override
	public boolean onSave(final Object entity, final Serializable id, final Object[] state, final String[] propertyNames, final Type[] types) {
		return setBaseProperties(state, propertyNames);
	}

	@Override
	public boolean onFlushDirty(final Object entity, final Serializable id, final Object[] currentState, final Object[] previousState, final String[] propertyNames, final Type[] types) {
		return setBaseProperties(currentState, propertyNames);
	}

	private boolean setBaseProperties(final Object[] state, final String[] propertyNames) {
		boolean changed = false;
		String userName = BaseConstants.JOB_USER_ROOT;
		
		Date now = new Date();
		UserDTO userDTO = ThreadManager.getUserFromThread();
		if (userDTO != null) {
			userName = userDTO.getName();
		}
		
		boolean userAddChanged = onCreate(state, propertyNames, USERADD, userName);
		boolean userUpdateChanged = onUpdate(state, propertyNames, USERUPDATE, userName);
		boolean dateAddChanged = onCreate(state, propertyNames, DATEADD, now);
		boolean dateUpdateChanged = onUpdate(state, propertyNames, DATEUPDATE, now);
		
		if (userAddChanged || userUpdateChanged || dateAddChanged || dateUpdateChanged) {
			changed = true;
		}
		
		return changed;
	}
	
	private boolean onCreate(final Object[] state, final String[] propertyNames, final String property, final Object value) {
		boolean changed = false;
		
		int index = ArrayUtils.indexOf(propertyNames, property);
		
		if (index != -1) {
			if (state[index] == null) {
				state[index] = value;
				changed = true;
			}
		}
		
		return changed;
	}
	
	private boolean onUpdate(final Object[] state, final String[] propertyNames, final String property, final Object value) {
		boolean changed = false;
		
		int index = ArrayUtils.indexOf(propertyNames, property);
		
		if (index != -1) {
			state[index] = value;
			changed = true;
		}
		
		return changed;
	}
}
