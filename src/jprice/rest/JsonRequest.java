package jprice.rest;

import java.net.URL;

public class JsonRequest extends AbstractRequest {

	public JsonRequest(URL _requestURL, String _requestMethod) {
		super(_requestURL, _requestMethod);
	}

	public JsonRequest(URL _requestURL, String _requestMethod, String _requestData) {
		super(_requestURL, _requestMethod, _requestData);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

}
