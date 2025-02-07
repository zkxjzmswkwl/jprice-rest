package jprice.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Response {

	public HttpURLConnection http;

	public Response(Request _request) {
		try {
			http = (HttpURLConnection) new URL(_request.url).openConnection();
			http.setReadTimeout(5000);
			http.setRequestMethod(_request.method.toString());
			http.setRequestProperty("Content-Type", _request.contentType);
			http.setRequestProperty("Accept", _request.contentType);
			// if (_request.headers.keys() != null) {
			// for (String property : _request.headers.keys()) {
			// http.setRequestProperty(property, _request.headers.get(property));
			// }
			// }
			http.setDoOutput(true);
			http.setAllowUserInteraction(false);
			http.setInstanceFollowRedirects(false);
			http.connect();
			if (_request.body != null) {
				writeBytes((String) _request.body);
			}
		} catch (Exception e) {
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

	public URL getURL() {
		return http.getURL();
	}

	public int getResponseCode() {
		try {
			return http.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * @return
	 *         Gets the HTTP response message, if any, returned along with the
	 *         response code from a server.
	 * @throws IOException
	 */
	public String getResponseMessage() throws IOException {
		return http.getResponseMessage();
	}

	/**
	 * @return
	 *         When HTTP response code in range 200-299, return true. Else false.
	 */
	public boolean ok() {
		return (getResponseCode() >= 200 && getResponseCode() <= 299);
	}

	public String getContentType() {
		return http.getContentType();
	}

	public String getRequestMethod() {
		return http.getRequestMethod();
	}

	public String getContent() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
		return sb.toString();
	}

	public String toJSONString() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(getContent());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return jsonObj.toJSONString();
	}

}
