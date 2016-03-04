package models;

import java.util.HashMap;

import jprice.rest.AbstractModel;

public class Person extends AbstractModel {
	
	public Person(HashMap<String, Object> _fields) {
		super(_fields);
	}
	
	public Person(int _id, String _firstName, String _lastName, int _age, String _gender) {
		super(new HashMap<String, Object>());
		fields.put("id", _id);
		fields.put("first_name", _firstName);
		fields.put("last_name", _lastName);
		fields.put("age", _age);
		fields.put("gender", _gender);
	}

}
