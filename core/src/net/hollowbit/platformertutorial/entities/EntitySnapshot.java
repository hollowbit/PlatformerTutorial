package net.hollowbit.platformertutorial.entities;

import java.util.HashMap;

public class EntitySnapshot {
	
	public String type;
	public float x, y;
	public HashMap<String, String> data;
	
	public EntitySnapshot() {}
	
	public EntitySnapshot( String type, float x, float y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void putFloat (String key, float value) {
		data.put(key, "" + value);
	}
	
	public void putInt (String key, int value) {
		data.put(key, "" + value);
	}
	
	public void putBoolean (String key, boolean value) {
		data.put(key, "" + value);
	}
	
	public void putString (String key, String value) {
		data.put(key, value);
	}
	
	public float getFloat (String key, float defaultValue) {
		if (data.containsKey(key)) {
			try {
				return Float.parseFloat(data.get(key));
			} catch (Exception e) {
				return defaultValue;
			}
		} else 
			return defaultValue;
	}
	
	public int getInt (String key, int defaultValue) {
		if (data.containsKey(key)) {
			try {
				return Integer.parseInt(data.get(key));
			} catch (Exception e) {
				return defaultValue;
			}
		} else 
			return defaultValue;
	}
	
	public boolean getBoolean (String key, boolean defaultValue) {
		if (data.containsKey(key)) {
			try {
				return Boolean.parseBoolean(data.get(key));
			} catch (Exception e) {
				return defaultValue;
			}
		} else 
			return defaultValue;
	}
	
	public String getString (String key, String defaultValue) {
		if (data.containsKey(key)) {
			return data.get(key);
		} else 
			return defaultValue;
	}
	
}
