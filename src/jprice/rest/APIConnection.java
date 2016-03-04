package jprice.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Creates a new connection with the requested API
 * 
 * @author JPrice
 */
public class APIConnection implements CRUD {

	private String base;
	private String endpoint;
	private String urlParams;
	private int timeout;

	public APIConnection(String _base, String _endpoint, int _timeout) {
		base = _base;
		endpoint = _endpoint;
		urlParams = "";
		timeout = _timeout;
	}

	public APIConnection(String _base, String _endpoint, String _urlParams, int _timeout) {
		this(_base, _endpoint, _timeout);
		urlParams = _urlParams;
	}

	/**
	 * Builds the url as www.base.com/endpoint/?urlParams
	 */
	private String buildURL() {
		return base + endpoint + urlParams;
	}

	/**
	 * Builds the url as www.base.com/endpoint/pk/?urlParams
	 */
	private String buildURL(int _pk) {
		return base + endpoint + _pk + "/" + urlParams;
	}

	/**
	 * Supported CRUD Operations
	 */
	@Override
	public Response create(String _requestData) {
		JsonRequest request = new JsonRequest(this, buildURL(), "POST", _requestData);
		return post(request);
	}

	@Override
	public Response list() {
		JsonRequest request = new JsonRequest(this, buildURL(), "GET");
		return get(request);
	}

	@Override
	public Response retrieve(int _pk) {
		JsonRequest request = new JsonRequest(this, buildURL(_pk), "GET");
		return get(request);
	}

	@Override
	public Response update(int _pk, String _requestData) {
		JsonRequest request = new JsonRequest(this, buildURL(_pk), "PUT", _requestData);
		return put(request);
	}

	/**
	 * Currently not supported
	 */
	@Override
	public Response destroy(int _pk) {
		return null;
	}

	/**
	 * Hits the API with the given request and returns a response
	 * 
	 * @param _request
	 *            The request data passed to the api
	 * @return the response the server sends back
	 */
	private Response get(Request _request) {
		try {
			HttpURLConnection connection = _request.getHttpConnection();
			connection.connect();

			String content = "";
			int responseCode = connection.getResponseCode();

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				content = sb.toString();
				return new Response(responseCode, content);
			default:
				return new Response(responseCode, "Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Hits the API with the given request and attempts to create a new object
	 * in the database.
	 * 
	 * @param _request
	 *            The request data passed to the api
	 * @return the newly created object
	 */
	private Response post(Request _request) {
		try {
			HttpURLConnection connection = _request.getHttpConnection();
			Request.writeBytes(connection, _request.getRequestData());

			String content = "";
			int responseCode = connection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			}

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				connection.disconnect();
				content = sb.toString();
				return new Response(responseCode, content);
			default:
				return new Response(responseCode, "Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Hits the API with the given request and attempts to update an existing
	 * object in the database.
	 * 
	 * @param _request
	 *            The request data passed to the api
	 * @return the object that has been updatedF
	 */
	private Response put(Request _request) {
		try {
			HttpURLConnection connection = _request.getHttpConnection();
			Request.writeBytes(connection, _request.getRequestData());

			String content = "";
			int responseCode = connection.getResponseCode();

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				connection.disconnect();
				content = sb.toString();
				return new Response(responseCode, content);
			default:
				return new Response(responseCode, "Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getTimeout() {
		return timeout;
	}

}
