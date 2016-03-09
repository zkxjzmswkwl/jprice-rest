package jprice.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public abstract class Request implements IRequest {

	protected HttpURLConnection http;

	protected URL requestURL;
	protected String requestMethod;
	protected String requestData;

	public Request(URL _requestURL) {
		requestURL = _requestURL;
		initConnection();
	}

	public void initConnection() {
		try {
			http = (HttpURLConnection) requestURL.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the connection headers for the current connection
	 */
	private void setConnectionHeaders() {
		try {
			http.setReadTimeout(5000);
			http.setRequestMethod(requestMethod);
			http.setRequestProperty("Content-Type", getContentType());
			http.setRequestProperty("Accept", getContentType());
			http.setDoOutput(true);
			http.setAllowUserInteraction(false);
			http.setInstanceFollowRedirects(false);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}

	private void connect(String _requestMethod) {
		requestMethod = _requestMethod;
		setConnectionHeaders();
		try {
			http.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeBytes(String data) {
		try {
			OutputStream os = http.getOutputStream();
			os.write(data.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Response get() {
		connect("GET");
		return new Response(http);
	}

	public Response post(String requestData) {
		connect("POST");
		writeBytes(requestData);
		return new Response(http);
	}

	public Response put(String requestData) {
		connect("PUT");
		writeBytes(requestData);
		return new Response(http);
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
