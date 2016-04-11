package jprice.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Response implements IResponse {

	public HttpURLConnection http;

	public Response(HttpURLConnection _http) {
		http = _http;
	}

	@Override
	public int getResponseCode() {
		try {
			return http.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String getContent() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toJSONString() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(getContent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObj.toJSONString();
	}

	@Override
	public String getContentType() {
		return http.getContentType();
	}

	@Override
	public String getRequestMethod() {
		return http.getRequestMethod();
	}

	@Override
	public Map<String, List<String>> getHeaderFields() {
		return http.getHeaderFields();
	}

}
