package jprice.rest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class ModelService implements CRUD {

	private APIConnection api;

	public ModelService(APIConnection _api) {
		api = _api;
	}

	public abstract String getEndpoint();

	private URL buildURL() {
		try {
			return new URL(api.getBaseURL() + getEndpoint() + api.getURLParams());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private URL buildURL(int _id) {
		try {
			return new URL(api.getBaseURL() + getEndpoint() + _id + "/" + api.getURLParams());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Response create(String _requestData) {
		JsonRequest request = new JsonRequest(buildURL(), "POST", _requestData);
		return api.post(request);
	}

	@Override
	public Response list() {
		JsonRequest request = new JsonRequest(buildURL(), "GET", "");
		return api.get(request);
	}

	@Override
	public Response retrieve() {
		JsonRequest request = new JsonRequest(buildURL(), "GET", "");
		return api.get(request);
	}

	@Override
	public Response retrieve(int _id) {
		JsonRequest request = new JsonRequest(buildURL(_id), "GET", "");
		return api.get(request);
	}

	@Override
	public Response update(int _id, String _requestData) {
		JsonRequest request = new JsonRequest(buildURL(_id), "PUT", _requestData);
		return api.put(request);
	}

	@Override
	public Response destroy(int _id) {
		return null;
	}
}
