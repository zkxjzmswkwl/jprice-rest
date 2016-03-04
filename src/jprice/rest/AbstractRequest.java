package jprice.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * A base class for requests
 * 
 * @author JPrice
 */
public abstract class AbstractRequest implements Request {

	protected APIConnection apiConnection;
	protected HttpURLConnection httpConnection;
	protected String requestURL;
	protected String requestMethod;
	protected String requestData;

	/**
	 * I still have yet to decide if this constructor is useful or not
	 */
	// public AbstractRequest(Request _request) {
	// apiConnection = _request.getAPIConnection();
	// httpConnection = _request.getHttpConnection();
	// requestURL = _request.getRequestURL();
	// requestMethod = _request.getRequestMethod();
	// requestData = _request.getRequestData();
	// }

	/**
	 * Creates a new request and sets connection headers
	 * 
	 * @param _apiConnection
	 * @param _requestURL
	 * @param _requestMethod
	 */
	public AbstractRequest(APIConnection _apiConnection, String _requestURL, String _requestMethod) {
		apiConnection = _apiConnection;
		requestURL = _requestURL;
		requestMethod = _requestMethod;

		try {
			httpConnection = (HttpURLConnection) new URL(requestURL).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setConnectionHeaders();
	}

	/**
	 * Creates a new request with request data and sets connection headers
	 * 
	 * @param _apiConnection
	 * @param _requestURL
	 * @param _requestMethod
	 * @param _requestData
	 */
	public AbstractRequest(APIConnection _apiConnection, String _requestURL, String _requestMethod,
			String _requestData) {
		this(_apiConnection, _requestURL, _requestMethod);
		requestData = _requestData;
	}

	/**
	 * Sets the connection headers for the current connection
	 */
	private void setConnectionHeaders() {
		try {
			httpConnection.setRequestMethod(getRequestMethod());
			httpConnection.setRequestProperty("Content-Type", getContentType());
			httpConnection.setRequestProperty("Accept", getContentType());
			httpConnection.setRequestProperty("Content-length", "0");
			httpConnection.setUseCaches(false);
			httpConnection.setAllowUserInteraction(false);
			httpConnection.setReadTimeout(apiConnection.getTimeout());
			httpConnection.setDoOutput(true);
			httpConnection.setInstanceFollowRedirects(false);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}

	@Override
	public APIConnection getAPIConnection() {
		return apiConnection;
	}

	public HttpURLConnection getHttpConnection() {
		return httpConnection;
	}

	@Override
	public String getRequestURL() {
		return requestURL;
	}

	@Override
	public String getRequestMethod() {
		return requestMethod;
	}

	@Override
	public String getRequestData() {
		return requestData;
	}

}
