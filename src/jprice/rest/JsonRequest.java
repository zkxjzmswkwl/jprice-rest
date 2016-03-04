package jprice.rest;

public class JsonRequest extends AbstractRequest {
	
	public JsonRequest(APIConnection _apiConnection, String _requestURL, String _requestMethod) {
		super(_apiConnection, _requestURL, _requestMethod);
	}

	public JsonRequest(APIConnection _apiConnection, String _requestURL, String _requestMethod, String _requestData) {
		super(_apiConnection, _requestURL, _requestMethod, _requestData);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

}
