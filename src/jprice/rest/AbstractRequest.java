package jprice.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * A base class for requests
 * 
 * @author JPrice
 */
public abstract class AbstractRequest implements Request {

	protected URL requestURL;
	protected String requestMethod;
	protected String requestData;
	protected HttpURLConnection httpConnection;

	/**
	 * Creates a new request and sets connection headers
	 * 
	 * @param _apiConnection
	 * @param _requestURL
	 * @param _requestMethod
	 */
	public AbstractRequest(URL _requestURL, String _requestMethod) {
		requestURL = _requestURL;
		requestMethod = _requestMethod;
		requestData = "";
		try {
			httpConnection = (HttpURLConnection) requestURL.openConnection();
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
	public AbstractRequest(URL _requestURL, String _requestMethod, String _requestData) {
		this(_requestURL, _requestMethod);
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
			httpConnection.setDoOutput(true);
			httpConnection.setInstanceFollowRedirects(false);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}

	public HttpURLConnection getHttpConnection() {
		return httpConnection;
	}

	@Override
	public URL getRequestURL() {
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
