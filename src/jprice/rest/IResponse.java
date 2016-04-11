package jprice.rest;

import java.util.List;
import java.util.Map;

public interface IResponse {

	int getResponseCode();

	String getContent();

	String getContentType();

	String getRequestMethod();

	Map<String, List<String>> getHeaderFields();

}
