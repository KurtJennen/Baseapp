package be.luxuryoverdosis.framework.base;

import java.util.HashMap;
import java.util.Map;

import be.luxuryoverdosis.framework.logging.Logging;

public class Config {
	private static Config configInstance;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	static {
		try {
			configInstance = new Config();
		} catch (Exception e) {
			Logging.error(Config.class, "Config error" + e.getMessage());
		}
	}
	
	public static Config getInstance() {
		return configInstance;
	}
	public void addKeyValue(final String key, final Object value) {
		map.put(key, value);
	}
	public Object getValue(final String key) {
		return map.get(key);
	}
	public Map<String, Object> getMap() {
		return map;
	}
}
