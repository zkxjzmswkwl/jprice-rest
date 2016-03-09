package jprice.rest;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface IResponse {

	int getResponseCode();

	String getContent();

	String getContentType();

	String getRequestMethod();

	JSONObject asJSONObject();

	String asJSONString();

	Map<String, List<String>> getHeaderFields();

}
