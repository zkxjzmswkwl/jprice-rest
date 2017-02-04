package jprice.rest;

import java.util.HashMap;

public class Headers {

	protected HashMap<String, String> headers;
	
	public Headers() {
		headers = new HashMap<String, String>();
	}

	/**
	 * Creates a copy of another headers instance
	 * 
	 * @param _headers
	 */
	public Headers(Headers _headers) {
		headers = _headers.headers;
	}

	/**
	 * Initializes a new headers instance
	 * 
	 * @param _headers
	 */
	public Headers(HashMap<String, String> _headers) {
		headers = _headers;
	}

	public void append(String _name, String _value) {
		// Appends a header to existing list of header
		// values for a given header name.
		if (has(_name)) {
			headers.replace(_name, _value);
		} else {
			headers.put(_name, _value);
		}
	}

	public void delete(String _name) {
		// Deletes all header values for the given name.
		headers.remove(_name);
	}

	public String get(String _name) {
		// Returns first header that matches given name.
		return headers.get(_name);
	}

	public void set(String _name, String _value) {
		// Sets or overrides header value for given name.
		headers.replace(_name, _value);
	}

	public boolean has(String _name) {
		// Checks for existence of header by given name.
		return headers.containsValue(_name);
	}

}
