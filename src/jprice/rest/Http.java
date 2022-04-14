package jprice.rest;

public class Http {
	public Response get(String _url) {
		Request request = new Request(_url, RequestMethod.GET, "application/json", null);
		Response response = new Response(request);
		return response;
	}
	
	public Response post(String _url, Object _body) {
		Request request = new Request(_url, RequestMethod.POST, "application/json", _body);
		Response response = new Response(request);
		return response;
	}
}
