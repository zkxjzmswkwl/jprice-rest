package jprice.rest;

public enum RequestMethod {
	
	GET ("GET"), POST ("POST"), PUT("PUT"), DELETE("DELETE");
	
	private String str;
	
	RequestMethod(String _str) {
		str = _str;
	}
	
	public String toString() {
		return str;
	}

}
