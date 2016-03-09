package jprice.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings({ "unchecked", "serial" })
public class DataModel extends JSONObject {

	private JSONParser parser;

	public DataModel() {
		parser = new JSONParser();
	}

	public DataModel(JSONObject obj) {
		this();
		putAll(obj);
	}

	public DataModel(String listName, JSONArray arr) {
		this();
		put(listName, arr);
	}

	public DataModel(String jsonString) {
		this();
		try {
			JSONObject obj = (JSONObject) parser.parse(jsonString);
			putAll(obj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public DataModel(Response _res) {
		this();
		try {
			JSONObject obj = (JSONObject) parser.parse(_res.getContent());

			for (Object key : obj.keySet()) {
				put((String) key, obj.get(key));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void listFields() {
		for (Object key : keySet()) {
			System.out.printf("%s: %s\n", key, get(key));
		}
		System.out.println();
	}

	public String serialize() {
		return toJSONString();
	}

	public String toString() {
		return serialize();
	}
}
