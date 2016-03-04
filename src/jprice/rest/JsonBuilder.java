package jprice.rest;

import java.util.HashMap;

public class JsonBuilder {

	private HashMap<String, Object> map;

	public JsonBuilder() {
		map = new HashMap<String, Object>();
	}
	
	public JsonBuilder(HashMap<String, Object> _map) {
		map = _map;
	}
	
	public void addInteger(String _key, int _int) {
		map.put(_key, _int);
	}
	
	public void addDouble(String _key, double _double) {
		map.put(_key, _double);
	}
	
	public void addFloat(String _key, float _float) {
		map.put(_key, _float);
	}
	
	public void addString(String _key, String _string) {
		map.put(_key, _string);
	}
	
	public void addBoolean(String _key, boolean _bool) {
		map.put(_key,  _bool);
	}
	
	public boolean isInteger(Object _value) {
		if (_value instanceof Integer) {
			return true;
		}
		return false;
	}
	
	public boolean isDouble(Object _value) {
		if (_value instanceof Double) {
			return true;
		}
		return false;
	}
	
	public boolean isFloat(Object _value) {
		if (_value instanceof Float) {
			return true;
		}
		return false;
	}
	
	public boolean isString(Object _value) {
		if (_value instanceof String) {
			return true;
		}
		return false;
	}

	public boolean isBoolean(Object _value) {
		if (_value instanceof Boolean) {
			return true;
		}
		return false;
	}

	public String build() {
		StringBuilder sb = new StringBuilder();
		int keynum = 0;

		sb.append("{");
		for (Object key : map.keySet()) {
			Object value = map.get(key);
			
			if (keynum > 0) {
				sb.append(",");
			}
			// append key
			sb.append("\"" + key + "\":");
			// append value
			if (isString(value)) {
				sb.append("\"");
			}
			sb.append(value);
			if (isString(value)) {
				sb.append("\"");
			}
			keynum++;
		}
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toString() {
		return build();
	}

}
