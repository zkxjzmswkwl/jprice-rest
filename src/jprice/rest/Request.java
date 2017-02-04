package jprice.rest;

public class Request {

	protected String url;
	protected RequestMethod method;
	protected String contentType;
	protected Object body;

	public Request(String _url, RequestMethod _method, String _contentType,
			Object _body) {
		url = _url;
		method = _method;
		contentType = _contentType;
		body = _body;
	}

}
