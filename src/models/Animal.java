package models;

import java.util.HashMap;

import jprice.rest.AbstractModel;

public class Animal extends AbstractModel {

	public Animal(HashMap<String, Object> _fields) {
		super(_fields);
	}
	
	public Animal(int _id, String _name, int _type, String _gender) {
		super(new HashMap<String, Object>());
		fields.put("id", _id);
		fields.put("name", _name);
		fields.put("type", _type);
		fields.put("gender", _gender);
		fields.put("owner", null);
	}
}
