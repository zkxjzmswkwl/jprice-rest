package jprice.rest;

import java.net.URL;

public interface IRequest {

	URL getRequestURL();

	String getRequestMethod();

	String getContentType();

	String getRequestData();

}
