package jprice.rest;

import java.util.HashMap;

public abstract class AbstractModel {

	protected HashMap<String, Object> fields;
	
	public AbstractModel(HashMap<String, Object> _fields) {
		fields = _fields;
	}
	
	public HashMap<String, Object> getFields() {
		return fields;
	}
	
	public String serialize() {
		JsonBuilder builder = new JsonBuilder(fields);
		return builder.build();
	}
}
