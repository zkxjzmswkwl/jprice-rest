package jprice.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates a new connection with the requested API
 * 
 * @author JPrice
 */
public class APIConnection {

	protected URL baseURL;
	protected String urlParams;
	protected int timeout;
	protected HttpURLConnection httpConnection;

	public APIConnection(String _base, int _timeout) {
		try {
			baseURL = new URL(_base);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		urlParams = "";
		timeout = _timeout;
	}

	public APIConnection(String _base, String _urlParams, int _timeout) {
		this(_base, _timeout);
		urlParams = _urlParams;
	}

	private void writeBytes(String data) {
		try {
			OutputStream os = httpConnection.getOutputStream();
			os.write(data.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hits the API with the given request and returns a response
	 * 
	 * @param _request
	 *            The request data passed to the api
	 * @return the response the server sends back
	 */
	public Response get(Request _request) {
		try {
			httpConnection = _request.getHttpConnection();
			httpConnection.setReadTimeout(timeout);
			httpConnection.connect();

			String content = "";
			int responseCode = httpConnection.getResponseCode();

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
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
	public Response post(Request _request) {
		try {
			httpConnection = _request.getHttpConnection();
			httpConnection.setReadTimeout(timeout);
			writeBytes(_request.getRequestData());

			String content = "";
			int responseCode = httpConnection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			}

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				httpConnection.disconnect();
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
	public Response put(Request _request) {
		try {
			httpConnection = _request.getHttpConnection();
			httpConnection.setReadTimeout(timeout);
			writeBytes(_request.getRequestData());

			String content = "";
			int responseCode = httpConnection.getResponseCode();

			switch (responseCode) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				httpConnection.disconnect();
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
	
	public URL getBaseURL() {
		return baseURL;
	}
	
	public String getURLParams() {
		return urlParams;
	}

	public int getTimeout() {
		return timeout;
	}

}
