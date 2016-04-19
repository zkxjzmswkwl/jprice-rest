package jprice.rest;

import java.net.URL;
import java.util.HashMap;

import com.sun.istack.internal.Nullable;

public interface IRequest {

	URL getRequestURL();

	String getRequestMethod();
	
	@Nullable
	HashMap<String, String> customRequestHeaders();

	String getContentType();

	String getRequestData();

}
