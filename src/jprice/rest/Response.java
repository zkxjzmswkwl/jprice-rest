package jprice.rest;

public class Response {

	private int responseCode;
	private String content;

	public Response(int _responseCode, String _content) {
		responseCode = _responseCode;
		content = _content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getContent() {
		return content;
	}

}
