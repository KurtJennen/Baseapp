package be.luxuryoverdosis.framework.base;

import be.luxuryoverdosis.framework.BaseConstants;

public class MenuLevel {
	private final static int MAX_LEVEL = 7;
	private final static String DEFAULT_LEVEL = "00";
	
	private String[] levels;

	public MenuLevel() {
		super();
		levels = new String[MAX_LEVEL];
		reset();
	}

	public void reset() {
		for (int i = 0; i < levels.length; i++) {
			levels[i] = DEFAULT_LEVEL;
		}
	}
	
	public void reset(int level) {
		for (int i = level + 1; i < levels.length; i++) {
			levels[i] = DEFAULT_LEVEL;
		}
	}
	
	public String current() {
		return current(MAX_LEVEL);
	}
	
	public String current(final int level) {
		StringBuffer order = new StringBuffer();
		
		for(int i = 0; i < MAX_LEVEL; i++) {
			if(i <= level) {
				order.append(levels[i]);
			} else {
				order.append(DEFAULT_LEVEL);
			}
			
			if(i < MAX_LEVEL - 1) {
				order.append(BaseConstants.SLASH);
			}
		}
		
		return order.toString();
	}
	
	public String next(final int level) {
		int nextLevelForLevel = Integer.valueOf(levels[level]);
		nextLevelForLevel++;
		levels[level] = String.format("%2s", nextLevelForLevel).replace(BaseConstants.SPACE, BaseConstants.ZERO);
		
		reset(level);
		
		return current(level);
	}
	
	public String previous(final int level) {
		int nextLevelForLevel = Integer.valueOf(levels[level]);
		if(nextLevelForLevel > 0) {
			nextLevelForLevel--;
			levels[level] = String.format("%2s", nextLevelForLevel).replace(BaseConstants.SPACE, BaseConstants.ZERO);
		}
		
		reset(level);
		
		return current(level);
	}
	
	

}
 