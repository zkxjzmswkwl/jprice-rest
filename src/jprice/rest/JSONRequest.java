package jprice.rest;

import java.net.URL;

public class JSONRequest extends Request {

	public JSONRequest(URL _requestURL) {
		super(_requestURL);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

}
