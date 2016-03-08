package jprice.rest;

import java.net.HttpURLConnection;
import java.net.URL;

public interface Request {
	
	HttpURLConnection getHttpConnection();
	
	URL getRequestURL();

	String getRequestMethod();

	String getContentType();
	
	String getRequestData();

}
